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


    public Find() {
        allowedFlags = Find.getImplementedFlagsForFind();
    }

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

    private void test1() throws InValidCommandException {
        parseTheArguments(new String[]{"find",".","-name","ketan"});
        parseTheArguments(new String[]{"find","/ketan/kokane","-name","ketan","-type","-f"});
        parseTheArguments(new String[]{"find","/ketan/kokane","-name","ketan"});
        parseTheArguments(new String[]{"find","/ketan/kokane","","ketan"});
    }


    private void iterateDirectory(File directory, StringBuilder stringBuilder) {
        if (directory!= null && directory.exists()){
            File[] listOfFiles = directory.listFiles();
            for (File file : listOfFiles){
                if (typeOfFile.equals("-d") || !file.isDirectory()){
                    try {
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

    private void executePassedFlagsCommand(File file, BasicFileAttributes basicFileAttributes, StringBuilder stringBuilder) {
        // TODO: remove below code later
        if (file.isHidden()){
            return;
        }
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

    private void execute() {
        StringBuilder stringBuilder =  new StringBuilder();
        File directory = new File(directoryName);
        iterateDirectory(directory,stringBuilder);
        printResult(stringBuilder);
    }

    private void test() {
        passedFlags = new ArrayList<>();
        directoryName = ".";
        passedFlags.add("name");
        passedFlags.add("length");
        passedFlags.add("date");
    }

    private static void printResult(StringBuilder stringBuilder) {
        System.out.println(stringBuilder.toString());
    }

    private  boolean checkIfTheProvidedDirectoryExists() throws InValidCommandException {
            if (directoryName!=null && !directoryName.isEmpty()){
                return new File(directoryName).exists();
            }else {
                throw new InValidCommandException("No Directory was passed");
            }
    }

    public void parseTheArguments(String[] commandLineParameters) throws InValidCommandException {
        // TODO: remove initialization after testing is complete
        directoryName="";
        fileNameToLookFor="";
        typeOfFile="-f"; // by default look for file, if -d look for directories
        passedFlags = new ArrayList<>();
        nextInputFlagShouldMatchThis = new Stack<>();

        // find Director [-some_flag ] hence the minimum length is 3
        if (commandLineParameters.length < 3) {
            throw new InValidCommandException("InValid Command " + String.join(" ", commandLineParameters));
        }
        if (!commandLineParameters[0].equals("find")) {
            throw new InValidCommandException("InValid Command " + String.join(" ", commandLineParameters));
        }
        // directory name is always the 2 param, this can be kept dynamic by adding the -flag to specify the same
        directoryName = commandLineParameters[1];

        String option;
        // iterate through the rest of the parameters , things that can be found are flags and there corresponding values
        // so ex -type -f, so once type is encountered its known that next symbol is either -f -d
        // so used stack to remember the previous flag and used regex to match the current expected flag
        for (int index = 2; index < commandLineParameters.length; index++) {
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

    private static HashMap<String,String> getImplementedFlagsForFind() {
        // this function maps command implemented by the find program with any regex required to find the corresponding flags
        // for ex -name commands needs to be followed by directoryName, hence used a regex to allow that
        HashMap<String,String> allowedFlags= new HashMap<>();
        allowedFlags.put("-length",null);
        allowedFlags.put("-name",".+");
        allowedFlags.put("-type","-f|-d");
        allowedFlags.put("-date",null);
        allowedFlags.put("find",null);
        return allowedFlags;
    }
    // TODO: need to remove the find from the command line argument
}
