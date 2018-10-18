import java.util.Scanner;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Grep {
    public static void main(String args[]) {
        // Loop runs till user inputs "exit"
        while (true) {
            System.out.print("%");

            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();

            String commandArray[] = command.split(" ");

            // For command required by user entered as grep
            if (commandArray[0].equals("grep")) {
                try {
                    boolean invalidCommand = false;

                    // Check for any invalid command
                    int arrayIndex = 0;
                    while (arrayIndex < commandArray.length) {
                        // If string of the form - followed by any characters
                        // And is not -c or -w or -l or -q
                        String stringToCheck = commandArray[arrayIndex];
                        if (stringToCheck.charAt(0) == '-'
                                && !(stringToCheck.equals("-c"))
                                && !(stringToCheck.equals("-w"))
                                && !(stringToCheck.equals("-l"))
                                && !(stringToCheck.equals("-q"))) {
                            System.out.println("invalid command : " + stringToCheck);
                            invalidCommand = true;
                            break;
                        }
                        arrayIndex++;
                    }

                    if (invalidCommand) {
                        continue;
                    }

                    // If file given directly after grep i.e. no reg ex
                    if (new File(commandArray[1]).exists()) {
                        System.out.println("Regular Expression missing");
                        continue;
                    }


                    boolean hasL = false;
                    boolean hasC = false;
                    boolean hasW = false;
                    boolean hasQ = false;
                    boolean noFileFound = false;

                    int index = 1;

                    String stringToCheck = commandArray[index];

                    // Till you find a command -c or -w or -l or -q
                    // Will not be executed even once for no commands i.e. only grep
                    while ((stringToCheck.equals("-c"))
                            || (stringToCheck.equals("-w"))
                            || (stringToCheck.equals("-l"))
                            || (stringToCheck.equals("-q"))) {//!(new File(commandArray[index]).exists())) {

                        if (commandArray[index].equals("-c")) {
                            hasC = true;
                        }
                        if (commandArray[index].equals("-w")) {
                            hasW = true;
                        }
                        if (commandArray[index].equals("-l")) {
                            hasL = true;
                        }
                        if (commandArray[index].equals("-q")) {
                            hasQ = true;
                        }

                        index++;
                        stringToCheck = commandArray[index];

                        // If index = length of array then no file found
                        if (index == commandArray.length) {
                            noFileFound = true;
                            break;
                        }
                    }

                    if (noFileFound) {
                        System.out.println("No file found");
                        continue;
                    }

                    // If string at index when loop ends should be the regular expression
                    // If it is a file then no regular expression was given
                    if ((new File(commandArray[index])).exists()) {
                        System.out.println("No regular expression to check");
                        continue;
                    }

                    // Regex is first string where loop ends
                    String reg = commandArray[index];
                    System.out.println(reg);
                    System.out.println(index);
                    // To go to first file
                    index++;

                    // Remaining all must be files
                    while (index < commandArray.length) {

                        // Stores count of lines matching regex
                        int lineCount = 0;
                        // Flag to store if atleast one line matched in file
                        boolean atleastOneMatch = false;

                        // If this file does not exist
                        if (!((new File(commandArray[index])).exists())) {
                            System.out.println(commandArray[index] + " : No such file or directory");
                            index++;
                            continue;
                        }

                        String filename = commandArray[index];
                        // File Reader
                        BufferedReader fileReader = new BufferedReader(new FileReader(filename));

                        //DataInputStream fileScanner = new DataInputStream(new FileInputStream(commandArray[commandArray.length - 1]));
                        String line;

                        // Loop reading one line of the file at a time
                        while ((line = fileReader.readLine()) != null) {

                            String regex = "";

                            // has neither of -c -l -q and -w
                            boolean hasNone = !hasL && !hasC && !hasW && !hasQ;

                            // If -q then do not do anything
                            if (hasQ) {
                                continue;
                            }

                            if (hasW) {
                                regex = ".*\\s" + reg + "\\s.*";
                            }
                            // If no -c -l -w and -q then just print line that matches
                            else {
                                regex = ".*" + reg + ".*";
                            }

                            try {
                                if (Pattern.matches(regex, line)) {
                                    // Has -c and -l
                                    if (hasC && hasL) {
                                        lineCount++;
                                        atleastOneMatch = true;
                                    }
                                    // Has -c
                                    else if (hasC){
                                        lineCount++;
                                    }
                                    // Has -l
                                    else if (hasL){
                                        atleastOneMatch = true;
                                    }
                                    // Has neither -c nor -l
                                    else {
                                        // Print file name
                                        System.out.print(filename + " : ");
                                        // Print line
                                        System.out.println(line);
                                    }
                                }
                            }
                            catch (PatternSyntaxException pse) {
                                pse.printStackTrace();
                            }
                        }
                        // If -q then do nothing
                        if (hasQ) {
                            break;
                        }
                        // Has -c print filename followed by count
                        if (hasC) {
                            // Print file name
                            System.out.print(filename + " : ");
                            // Print count
                            System.out.println(lineCount);
                        }
                        // Has l and at least one match found then print file name
                        if (hasL && atleastOneMatch) {
                            // Print file name
                            System.out.println(filename);
                        }
                        index++;
                    }
                }
                catch (IOException ioE) {
                    ioE.printStackTrace();
                }
            }
            else if (commandArray[0].equals("exit")) {
                break;
            }
            else {
                System.out.println("Invalid Command");
            }
        }
    }
}