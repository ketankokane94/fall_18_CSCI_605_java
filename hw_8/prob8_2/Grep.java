import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Grep {
    static List<String> allowedFlags = new ArrayList<>();
    static List<String> flags ;
    static List<String> commandArray;
    static String fileName ;
    static String pattern;
    static boolean hasL,hasC,hasW,hasQ;

    public static void main(String args[]) {
        allowedFlags.add("-w");
        allowedFlags.add("-c");
        allowedFlags.add("-q");
        allowedFlags.add("-l");
        // Loop runs till user inputs "exit"
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("%");
            flags  = new ArrayList<>();
            String command = scanner.nextLine();
            convertCommandStringIntoArrayListForParsing(command);

            if (!checkIfCommandIsValid()) {
                if (commandArray.get(0).equals("exit")) {
                    break;
                }
                continue;
            }
            if (!checkIfFlagsAreValid()) {
                continue;
            }
                setRequiredFlagVariables();
                exceuteFlagsOnFileForGivenPattern();
        }
    }

    private static void convertCommandStringIntoArrayListForParsing(String command) {
        commandArray = new ArrayList<>(Arrays.asList(command.split(" ")));
    }

    private static void exceuteFlagsOnFileForGivenPattern()  {
        File file = new File(fileName);
        if (file ==null && !file.exists()) {
            System.err.println("file not found");
            return;
        }
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        if (hasQ){
            return;
        }
        if (hasL){
            try {
                executeL(fileReader,pattern);
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                return;
            }

        }
        if (hasW){
            try {
                executeW(fileReader,pattern);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (hasC){
            try {
                executeC(fileReader,pattern);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void executeC(BufferedReader fileReader, String pattern) throws IOException {
        int foundInstances = 0;
        String line;
        String  regex = ".*" + pattern + ".*";
        Pattern pat =  Pattern.compile(regex);
        while ((line = fileReader.readLine())!=null){
            if(pat.matcher(line).matches()){
                foundInstances++;
            }

        }
        System.out.println(foundInstances);
    }

    private static void executeW(BufferedReader fileReader, String pattern) throws IOException {
        StringBuilder output = new StringBuilder();
        String line;
        String regex = "\\b"+pattern+"\\b";
        int foundInstances = 0;
        Pattern pat =  Pattern.compile(regex);
        while ((line = fileReader.readLine())!=null){
            Matcher matcher = pat.matcher(line);
            if(matcher.find()){
                foundInstances++;
                while (matcher.find())
                    foundInstances++;
                output.append(line).append("\n");

            }
        }
        if (hasC){
        System.out.println(foundInstances);
        hasC = false;
        return;
        }
        System.out.println(output.toString());
    }

    private static void executeL(BufferedReader fileReader, String pattern) throws IOException {
        String line;
        String regex = ".*" + pattern + ".*";
        Pattern pat =  Pattern.compile(regex);
        while ((line = fileReader.readLine())!=null){
            if(pat.matcher(line).matches()){
               System.out.println(fileName);
               return;
            }
        }
    }

    private static void setRequiredFlagVariables() {
        for (String flag: flags){
            switch (flag){
                case "-l":
                {
                    hasL=true;
                    break;
                }
                case "-w":
                {
                    hasW=true;
                    break;
                }
                case "-c":
                {
                    hasC=true;
                    break;
                }
                case "-q":
                {
                    hasQ=true;
                    break;
                }
            }
        }
    }

    private static boolean checkIfCommandIsValid() {

        if (!commandArray.get(0).equals("grep")){
            if (!commandArray.get(0).equals("exit"))
            System.err.println("unknown command "+ commandArray.get(0));
            return false;
        }

        // grep -someFlag -regexFileName
        return commandArray.size() >= 3;
    }

    private static boolean checkIfFlagsAreValid() {

        commandArray.remove(0);
        int sizeOfOptions = commandArray.size();
        for (String flag : commandArray){
            if (allowedFlags.contains(flag)){
                flags.add(flag);
                //commandArray.remove(flag);
            }

        }
        for (int index = 0; index < flags.size() ; index++) {
            commandArray.remove(0);
        }

        if (commandArray.size() != 2){
            // means something extra was also provided
            return false;
        }
        fileName = commandArray.get(commandArray.size() - 1);
        pattern = commandArray.get(commandArray.size() - 2);
        return true;
    }
}