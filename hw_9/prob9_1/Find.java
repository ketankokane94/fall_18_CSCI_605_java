/**
 * Find.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

/**
 * Implementation of find funtionality of UNIX
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

public class Find {
    // mark this as static because all instances of find will have the same allowed flags
    private static HashMap<String,String> allowedFlags;
    private List<String> passedFlags;
    private String directoryName,fileNameToLookFor,typeOfFile;
    Stack<String> nextInputFlagShouldMatchThis ;

    /**
     * constructor
     */
    public Find() {
        allowedFlags = Find.getImplementedFlagsForFind();
    }

    /**
     * main driver class of the utility, get the input, parse it, execute it
     * @param args
     */
    public static void main(String args[]) {
        // parse the argument
        Find find = new Find();
        try {
            find.parseTheArguments(args);
            // do some execution only if the directory on which operation is to be done exists and there is some operation
            // which needs to be performed
            if (find.checkIfTheProvidedDirectoryExists() && (find.passedFlags != null && find.passedFlags.size() > 0)){
                find.execute();
            }
            else {
                throw new FileNotFoundException("Provided directory does not exist  "+find.directoryName);
            }
        } catch (InValidCommandException e) {
            System.err.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     * function which iterates through a given directory and performs actions based on given commands
     * @param directory
     * @param stringBuilder
     */
    private void iterateDirectory(File directory, StringBuilder stringBuilder) {
        if (directory!= null && directory.exists()){
            // get the list of all the files / directories in the current directory
            File[] listOfFiles = directory.listFiles();
            for (File file : listOfFiles){
                // check if the only directory is to be worked with ? by default utility works with files and directories
                if (typeOfFile.equals("-d") || !file.isDirectory()){
                    try {
                        // this is to read all the basic attributes of the files in one go
                        BasicFileAttributes basicFileAttributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);

                        executePassedFlagsCommand(file,basicFileAttributes,stringBuilder);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    // means this file is a directory
                    iterateDirectory(file,stringBuilder);
                }
            }
        }
    }

    /**
     *  does the job of printing required information about the files
     * @param file
     * @param basicFileAttributes
     * @param stringBuilder
     */
    private void executePassedFlagsCommand(File file, BasicFileAttributes basicFileAttributes, StringBuilder stringBuilder) {
        // -name states that work with only files whoes name matches the given regex
        if (passedFlags.contains("-name") && !file.getName().contains(fileNameToLookFor)){
            // means name parameter was provided and current file name does not match the
            // file name user is looking for
            return;

        }
        stringBuilder.append(file.getName()).append("\t");

        for(String flag : passedFlags){
            switch (flag){
                case "-length": {
                    stringBuilder.append(basicFileAttributes.size());
                    break;
                }
                case "-date":{
                    stringBuilder.append(new SimpleDateFormat().format(basicFileAttributes.lastModifiedTime().toMillis()));
                    break;
                }
            }
            stringBuilder.append("\t");
        }
        stringBuilder.append("\n");
    }

    /**
     * function which starts the iterative call to list all the directories
     * and prints the result
     */
    private void execute() {
        StringBuilder stringBuilder =  new StringBuilder();
        File directory = new File(directoryName);
        iterateDirectory(directory,stringBuilder);
        printResult(stringBuilder);
    }

    /**
     * simple helper function to print the result in required format
     * @param stringBuilder
     */
    private static void printResult(StringBuilder stringBuilder) {
        System.out.println(stringBuilder.toString());
    }

    /**
     * checks if the directory provided by the user exists or no
     * @return
     * @throws InValidCommandException
     */
    private  boolean checkIfTheProvidedDirectoryExists() throws InValidCommandException {
            if (directoryName!=null && !directoryName.isEmpty()){
                return new File(directoryName).exists();
            }else {
                throw new InValidCommandException("No Directory was passed");
            }
    }

    /**
     * main parser of the utility which handles the argument passed by the user and converts them to what utiliity requires
     * @param commandLineParameters
     * @throws InValidCommandException
     */
    public void parseTheArguments(String[] commandLineParameters) throws InValidCommandException {

        directoryName="";
        fileNameToLookFor="";
        typeOfFile="-f"; // by default look for file, if -d look for directories
        passedFlags = new ArrayList<>();
        nextInputFlagShouldMatchThis = new Stack<>();

        // Director [-some_flag ] hence the minimum length is 2
        if (commandLineParameters.length < 2) {
            throw new InValidCommandException("InValid Command " + String.join(" ", commandLineParameters));
        }

        // directory name is always the 1 param, this can be kept dynamic by adding the -flag to specify the same
        directoryName = commandLineParameters[0];

        String option;
        // iterate through the rest of the parameters , things that can be found are flags and there corresponding values
        // so ex -type -f, so once type is encountered its known that next symbol is either -f -d
        // so used stack to remember the previous flag and used regex to match the current expected flag
        for (int index = 1; index < commandLineParameters.length; index++) {
            String flag = commandLineParameters[index];
            if (!nextInputFlagShouldMatchThis.empty()) {
                // if stack is not empty means the current flag is dependent on previous, use regex to match the flag
                if(!Pattern.matches(nextInputFlagShouldMatchThis.pop(),flag)){
                    throw new InValidCommandException(nextInputFlagShouldMatchThis.peek() + " cannot be followed "+ flag);
                }
                else {
                    checkIfTheValueNeedsToBeStored(flag);
                }
            } else if (allowedFlags.containsKey(flag)) {
                passedFlags.add(flag);
                option = allowedFlags.get(flag);
                if (option != null) {
                    nextInputFlagShouldMatchThis.push(flag);
                    nextInputFlagShouldMatchThis.push(option);
                }
            }
        }
    }

    /**
     * helper function which stores the values of the flags provided by the user
     * @param flag
     */
    private void checkIfTheValueNeedsToBeStored(String flag) {
        String previousFlag = nextInputFlagShouldMatchThis.pop();
         switch (previousFlag){
             case "-type" :
             {
                typeOfFile = flag;break;
             }
             case "-name":
             {
                 fileNameToLookFor = flag;break;
             }
         }
    }

    /**
     *  stores all the flags that the utility has implemented
     * @return
     */
    private static HashMap<String,String> getImplementedFlagsForFind() {
        // this function maps command implemented by the find program with any regex required to find the corresponding flags
        // for ex -name commands needs to be followed by directoryName, hence used a regex to allow that
        HashMap<String,String> allowedFlags= new HashMap<>();
        allowedFlags.put("-length",null);
        allowedFlags.put("-name",".+");
        allowedFlags.put("-type","-f|-d");
        allowedFlags.put("-date",null);
        return allowedFlags;
    }
    // TODO: need to remove the find from the command line argument
}
