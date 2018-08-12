import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This helper class reads Slimmer configuration files that specify which part of the
 * ontology is to be kept or removed. The configuration file uses a line-based
 * instruction format. Each line specifies part of the ontology to be kept or removed.
 * <p>
 * An example is:
 * <pre>
 * +S:(<target class>):(<super class>) comments
 * +U:(<target class>):(<super class>) comments
 * +D:(<target class>):(<super class>) comments
 * </pre>
 * <p>
 * This configuration file uses a custom syntax which is briefly explained here. By
 * default it removes all content. The first character on a line indicates if the something
 * needs to be included (+) or excluded from a previously defined inclusion (-). The second
 * character indicates whether a whole single(S) upper (U) or down (D) tree should be
 * included or excluded. After the colon the URI of the resource is given to be include or
 * excluded, followed  * by a user-oriented comment. Finally, before the colon and in brackets
 * an optional superclass of this resource can be specified, possibly from other ontologies.
 *
 * @author jkchang
 */


public class Configuration {


    private Set<Instruction> irisToSave = new HashSet<Instruction>();
    private Set<Instruction> irisToRemove = new HashSet<Instruction>();

    // setter
    public void removeTreePart(Instruction instruction) {
        irisToRemove.add(instruction);
    }

    public void addTreePart(Instruction instruction) {
        irisToSave.add(instruction);
    }

    // getter
    public Set<Instruction> getTreePartsToSave() {
        return irisToSave;
    }

    public Set<Instruction> getTreePartsToRemove() {
        return irisToRemove;
    }

    // read & parse the configuration file
    public void read(File file) throws Exception {
        read(new FileReader(file));
    }

    public void read(Reader file) throws Exception {
        BufferedReader reader = new BufferedReader(file);
        String line = reader.readLine();
        int lineNumber = 1;

        while (line != null) {
            String instruction = line.trim().replace("\t", " ");

            // Skip a blank line
            if (instruction.length() == 0) {
                System.out.println("Skipping an unexpected empty line at line " + lineNumber);
                line = reader.readLine();
                lineNumber++;
                continue;
            }

            // matching the pattern
            //([\-\+])(['U','S','D']):\((\S+)\):\((\S+)\)\s*(\S*)
            String pattern = "([\\-,\\+])(['U','S','D']):\\((\\S+)\\):\\((\\S+)\\)\\s*(\\S*)";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(line);

            if (m.find()) {
                char addRemoveInstruct = m.group(1).charAt(0);
                char directionInstruct = m.group(2).charAt(0);
                String targetIRI = m.group(3);
                String superIRI = m.group(4);
                String comment = m.group(5);


                Instruction.Scope scope = Instruction.Scope.DOWN;
                if (directionInstruct == 'S') {
                    scope = Instruction.Scope.SINGLE;
                } else if (directionInstruct == 'U') {
                    scope = Instruction.Scope.UP;
                }

                Instruction ins = new Instruction(targetIRI, scope, comment);

                if (superIRI != null) {
                    ins.setNewSuperClass(superIRI);
                    Instruction superIns = new Instruction(superIRI, Instruction.Scope.SINGLE, "Used as Superclass");
                    irisToSave.add(superIns);
                }

                if (addRemoveInstruct == '+') {
                    irisToSave.add(ins);
                } else {
                    irisToRemove.add(ins);
                }

                line = reader.readLine();
                lineNumber++;
                continue;

            } else {
                // matching the pattern
                String error_pattern = "([\\-,\\+]*)(['U','S','D']*)(\\:*)(\\(\\S+\\))*(\\:)(\\(\\S+\\))*\\s*\\S*";
                //([\-,\+]*)(['U','S','D']*)(\:*)(\(\S+\))*(\:)(\(\S+\))*\s*\S*
                Pattern error_r = Pattern.compile(error_pattern);
                Matcher error_matcher = error_r.matcher(line);

                if (error_matcher.find()) {
                    if (error_matcher.group(1) == null) {
                        reader.close();
                        throw new Exception("Invalid configuration input at line " + lineNumber + ": first character should be '+' or '-' ");
                    } else if (error_matcher.group(2) == null) {
                        reader.close();
                        throw new Exception("Invalid configuration input a the line " + lineNumber + ": direction instructor should be 'S', 'D' or 'U' ");
                    } else if (error_matcher.group(3) == null) {
                        reader.close();
                        throw new Exception("Invalid configuration input a the line " + lineNumber + ": should have a ':' after direction instructor ");
                    } else if (error_matcher.group(4) == null) {
                        reader.close();
                        throw new Exception("Invalid target class input a the line " + lineNumber);
                    } else if (error_matcher.group(5) == null) {
                        reader.close();
                        throw new Exception("Invalid configuration input a the line " + lineNumber + ": should have a ':' between target class and super class");
                    } else if (error_matcher.group(6) == null) {
                        reader.close();
                        throw new Exception("Invalid super class input a the line " + lineNumber);
                    }

                }

            }
        }

        reader.close();
    }
}
