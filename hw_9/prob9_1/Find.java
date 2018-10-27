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
            if (find.checkIfTheProvidedDirectoryExists()){
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
        if (directory.exists()){
            File[] listOfFiles = directory.listFiles();
            for (File file : listOfFiles){
                if (!file.isDirectory()){
                    try {
                        BasicFileAttributes basicFileAttributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                        getDetailsBasedOnFlags(file,basicFileAttributes,stringBuilder);
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

    private void getDetailsBasedOnFlags(File file,BasicFileAttributes basicFileAttributes, StringBuilder stringBuilder) {
        if (file.isHidden()){
            return;
        }
        stringBuilder.append(file.getName()).append("\t");
        for(String flag : passedFlags){
            switch (flag){
                case "length": {
                    stringBuilder.append(basicFileAttributes.size());
                    break;
                }
                case "date":{
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
        // remove initialization after testing is complete
        directoryName="";
        fileNameToLookFor="";
        typeOfFile="";
        passedFlags = new ArrayList<>();
        nextInputFlagShouldMatchThis = new Stack<>();
        String input = String.join(" ", commandLineParameters);

        if (commandLineParameters.length < 3) {
            throw new InValidCommandException("InValid Command " + input);
        }
        if (!commandLineParameters[0].equals("find")) {
            throw new InValidCommandException("InValid Command " + input);
        }
        directoryName = commandLineParameters[1];
        String option;
        for (int index = 2; index < commandLineParameters.length; index++) {
            String flag = commandLineParameters[index];
            if (!nextInputFlagShouldMatchThis.empty()) {
                if(!Pattern.matches(nextInputFlagShouldMatchThis.pop(),flag)){
                    throw  new InValidCommandException("");
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
        HashMap<String,String> allowedFlags= new HashMap<>();
        allowedFlags.put("-length",null);
        allowedFlags.put("-name",".+");
        allowedFlags.put("-type","-f|-d");
        allowedFlags.put("-date",null);
        allowedFlags.put("find",null);
        return allowedFlags;
    }
}
