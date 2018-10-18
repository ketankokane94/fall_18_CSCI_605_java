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
                    // Till you find a file
                    while (!(new File(commandArray[index]).exists())) {
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

                    // If string before the first file has a - then it is not a regex
                    if (commandArray[index - 1].contains("-")) {
                        System.out.println("No regular expression to check");
                        continue;
                    }


                    // Regex is last string before first file name
                    String reg = commandArray[index - 1];
                    System.out.println(reg);
                    System.out.println(index);

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