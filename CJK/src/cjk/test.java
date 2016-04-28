/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cjk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author jkchang
 */
public class test {

    private Set<Instruction> irisToSave = new HashSet<Instruction>();
    private Set<Instruction> irisToRemove = new HashSet<Instruction>();

    public void removeTreePart(Instruction instruction) {
        irisToSave.add(instruction);
    }

    public void addTreePart(Instruction instruction) {
        irisToRemove.add(instruction);
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
            

        }
    }
}
