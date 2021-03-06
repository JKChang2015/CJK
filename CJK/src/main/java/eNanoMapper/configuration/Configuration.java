package eNanoMapper.configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author JKChang
 * @date 15-Nov-2016
 * @Description:
 *
 * This helper class reads Slimmer configuration files that specify which part
 * of the ontology is to be kept or removed. The configuration file uses a
 * line-based instruction format. Each line specifies part of the ontology to be
 * kept or removed. An example is:
 * <add>
 * +D(http://purl.bioontology.org/ontology/npo#NPO_1436):http://www.bioassayontology.org/bao#BAO_0000697
 * add branch +D:http://www.bioassayontology.org/bao#BAO_0000697 add branch
 * +U(http://purl.bioontology.org/ontology/npo#NPO_1436):http://www.bioassayontology.org/bao#BAO_0000697
 * add branch
 * +U(http://purl.bioontology.org/ontology/npo#NPO_1436):http://www.bioassayontology.org/bao#BAO_0000697
 * add branch
 * +(http://purl.bioontology.org/ontology/npo#NPO_1436):http://www.bioassayontology.org/bao#BAO_0000697
 * add single class
 * +(http://purl.bioontology.org/ontology/npo#NPO_1436):http://www.bioassayontology.org/bao#BAO_0000697
 * add single class
 * </add>
 *
 * *<remove>
 * -D(http://purl.bioontology.org/ontology/npo#NPO_1436):http://www.bioassayontology.org/bao#BAO_0000697
 * add branch
 * -:(http://purl.bioontology.org/ontology/npo#NPO_1436):http://www.bioassayontology.org/bao#BAO_0000697
 * add single class
 * </remove>
 *
 */
public class Configuration {

    private Set<Instruction> irisToSave = new HashSet<Instruction>();
    private Set<Instruction> irisToRemove = new HashSet<Instruction>();

    public void removeTreePart(Instruction instruction) {
        irisToRemove.add(instruction);  //  irisToSave.add(instruction);
    }

    public void addTreePart(Instruction instruction) {
        irisToSave.add(instruction);  //  irisToRemove.add(instruction);
    }

    public Set<Instruction> getTreePartsToSave() {
        return irisToSave;
    }

    public Set<Instruction> getTreePartsToRemove() {
        return irisToRemove;
    }

    public void read(File file) throws Exception {
        read(new FileReader(file));
    }

    public void read(Reader file) throws Exception {
        BufferedReader reader = new BufferedReader(file);
        String line = reader.readLine();
        int lineNumber = 1;

        while (line != null) {
            String instruction = line.trim();

            if (instruction.length() == 0) {
                System.out.println("Skipping an unexpected empty line at line " + lineNumber);
                line = reader.readLine();
                lineNumber++;
                continue;
            }

            char addRemoveInstruct = instruction.charAt(0);
            if (addRemoveInstruct != '+' && addRemoveInstruct != '-') {
                reader.close();
                throw new Exception("Invalid configuration input at line " + lineNumber + ": first character should be '+' or '-'.");
            }

            char upDownInstruct = instruction.charAt(1);
            Instruction.Scope scope = Instruction.Scope.SINGLE;
            String newSuperClass = null;
            int startURI = 2;

            if (upDownInstruct == ':' || upDownInstruct == '(' || upDownInstruct == 'U' || upDownInstruct == 'D') {

                if (upDownInstruct == 'U') {  //UP
                    scope = Instruction.Scope.UP;
                    if (instruction.charAt(2) != ':') {
                        reader.close();
                        throw new Exception("Invalid configuration input at line " + lineNumber + ": expected ':' at position 3.");
                    }
                    startURI = 3;

                } else if (upDownInstruct == 'D') { //DOWN
                    scope = Instruction.Scope.DOWN;
                    startURI = 3;
                    int indexCloseSuper = instruction.indexOf(')');
                    if (instruction.charAt(2) == '(' && indexCloseSuper != -1) {
                        newSuperClass = instruction.substring(startURI, indexCloseSuper);
                        startURI = indexCloseSuper + 2;
                    } else if (instruction.charAt(2) != ':') {
                        reader.close();
                        throw new Exception("Invalid configuration input at line " + lineNumber + ": expected ':' at position 3.");
                    }

                } else {  //SINGLE, upDownInstruct = : or (
                    int indexCloseSuper = instruction.indexOf(')');
                    if (instruction.charAt(1) == '(' && indexCloseSuper != -1) {
                        newSuperClass = instruction.substring(startURI, indexCloseSuper);
                        startURI = indexCloseSuper + 2;
                    }

                    String iri = instruction.substring(startURI);
                    int index = iri.indexOf(' ');
                    String comment = " ";
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
            } else {
                reader.close();
                throw new Exception("Invalid configuration input at line " + lineNumber + ": third instruction should be 'U', 'D', ':' or '('.");
            }

            reader.close();
        }
    }

}
