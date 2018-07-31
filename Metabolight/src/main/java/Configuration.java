import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;


/**
 * This helper class reads Slimmer configuration files that specify which part of the
 * ontology is to be kept or removed. The configuration file uses a line-based
 * instruction format. Each line specifies part of the ontology to be kept or removed.
 * <p>
 * An example is:
 * <pre>
 * +S(<target class>):<super class> comments
 * +U(<target class>):<super class> comments
 * +D(<target class>):<super class> comments
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
    public Set<Instruction> getTreePartToSave() {
        return irisToSave;
    }

    public Set<Instruction> getTreePartRemove() {
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

            // Check add/remove instructor
            char addRemoveInstruct = instruction.charAt(0);
            if (addRemoveInstruct != '+' && addRemoveInstruct != '-') {
                reader.close();
                throw new Exception("Invalid configuration input at line " + lineNumber + ": first character should be '+' or '-' ");
            }

            // Check direction instructor
            char directionInstruct = instruction.charAt(1);
            if (directionInstruct != 'S' && directionInstruct != 'D' && directionInstruct != 'U') {
                reader.close();
                throw new Exception("Invalid configuration input a the line " + lineNumber + ": Direction instructor should be 'S', 'D' or 'U' ");
            }

            // Check target IRI
            if (false) {
                reader.close();
                throw new Exception("Invalid configuration input a the line " + lineNumber + ": expected Target class IRI");
            }


            // Check Superclass IRI
            if (false) {
                reader.close();
                throw new Exception("Invalid configuration input a the line " + lineNumber + ": expected Superclass IRI");
            }

            // LOADING


//            Instruction.Scope scope = Instruction.Scope.SINGLE;
//            String newSuperClass = null;


            if (directionInstruct != ':' && directionInstruct != '(') {
                if (directionInstruct == 'U') {
                    scope = Instruction.Scope.UP;
                    if (instruction.charAt(2) != ':') {
                        reader.close();
                        throw new Exception("Invalid configuration input at line " + lineNumber + ": expected ':' at position 3.");
                    }
                    startURI = 3;

                } else if (directionInstruct == 'D') {
                    scope = Instruction.Scope.DOWN;
                    startURI = 3;
                    int indexCloseSuper = instruction.indexOf(')');
                    if (instruction.charAt(2) == '(' && indexCloseSuper != -1) {
                        newSuperClass = instruction.substring(3, indexCloseSuper);
                        startURI = indexCloseSuper + 2;
                    } else if (instruction.charAt(2) != ':') {
                        reader.close();
                        throw new Exception("Invalid configuration input at line " + lineNumber + ": expected ':' at position 3.");
                    }

                } else {
                    reader.close();
                    throw new Exception("Invalid configuration input at line " + lineNumber + ": second instruction should be 'U', 'D', or empty.");
                }

            } else { //SINGLE
                int indexCloseSuper = instruction.indexOf(')');
                if (instruction.charAt(1) == '(' && indexCloseSuper != -1) {
                    newSuperClass = instruction.substring(2, indexCloseSuper);
                    startURI = indexCloseSuper + 2;
                }
            }

            String iri = instruction.substring(startURI);
            int index = iri.indexOf(' ');
            String comment = "";
            if (index != -1) {
                comment = iri.substring(index).trim();
                iri = iri.substring(0, index);
            }
            Instruction ins = new Instruction(iri, scope, comment);
            if (newSuperClass != null) {
                ins.setNewSuperClass(newSuperClass);
                Instruction superIns = new Instruction(newSuperClass, Instruction.Scope.SINGLE, "Used as Superclass");
                irisToSave.add(superIns);
            }
            if (addRemoveInstruct == '+') {
                irisToSave.add(ins);
            } else {
                irisToRemove.add(ins);
            }

            line = reader.readLine();
            lineNumber++;


        }

        reader.close();

    }


}
