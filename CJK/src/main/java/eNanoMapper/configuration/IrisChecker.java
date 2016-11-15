package eNanoMapper.configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author JKChang
 * @date 15-Nov-2016
 * @Description: Checking if the iris file is acceptable.
 *
 */

public class IrisChecker {

    public void IrisChecker(File file) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        int lineNum = 1;

        while (line != null) {
            String instruction = line.trim();
            if (instruction.length() == 0) {
                System.out.println(file.getName() + ",  skip the empty line at " + lineNum+ "\n");
                line = reader.readLine();
                lineNum++;
                continue;
            }

            char first = instruction.charAt(0);
            char second = instruction.charAt(1);
            char third = instruction.charAt(2);

            if (first == '+') {
                if (!containBracket(instruction)) {
                    //  reader.close();
                    System.out.println(file.getName() + ",  Invalid configuration input at line " + lineNum + " should have a SuperClass");
                    System.out.println(instruction + "\n");
                    line = reader.readLine();
                    lineNum++;
                    continue;
                    // throw new Exception("Invalid configuration input at line " + lineNum + "should have a SuperClass");
                }

                int indexColon = instruction.indexOf(')') + 1;
                if (instruction.charAt(indexColon) != ':') {
                    //    reader.close();
                    System.out.println(file.getName() + ",  Invalid configuration input at line " + lineNum + " should have a SubClass");
                    System.out.println(instruction+ "\n");
                    line = reader.readLine();
                    lineNum++;
                    continue;
                    // throw new Exception("Invalid configuration input at line " + lineNum + "should have a SubClass");
                }

            } else if (first == '-') {
                if (containBracket(instruction)) {
                    //    reader.close();
                    System.out.println(file.getName() + ",  Invalid configuration input at line " + lineNum + " should not have a SuperClass");
                    System.out.println(instruction+ "\n");
                    line = reader.readLine();
                    lineNum++;
                    continue;
                    //  throw new Exception("Invalid configuration input at line " + lineNum + "should not have a SuperClass");
                }
            } else {  // first char should be +/-
                //    reader.close();
                System.out.println(file.getName() + ",  Invalid configuration input at line " + lineNum + " first character should be '+'  or '-'");
                System.out.println(instruction+ "\n");
                line = reader.readLine();
                lineNum++;
                continue;
                // throw new Exception("Invalid configuration input at line " + lineNum + "first character should be '+'  or '-'");
            }

            line = reader.readLine();
            lineNum++;

        }
        reader.close();

    }

    public boolean containBracket(String s) {
        if (s.contains("(") && s.contains(")")) {
            return true;
        } else {
            return false;
        }
    }

}
