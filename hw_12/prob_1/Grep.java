/**
 * Grep.java
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
import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;


public class Grep {
    // mark this as static because all instances of find will have the same allowed flags
    private static HashMap<String,String> allowedFlags;
    private List<String> passedFlags;
    private String wordToLookFor;
    private List<String> fileNames;
    private int output_code;
    private boolean cValue,lValue,wValue;


    /**
     * constructor
     */
    public Grep() {
        allowedFlags = Grep.getImplementedFlagsForFind();
    }

    /**
     * main driver class of the utility, get the input, parse it, execute it
     * @param args
     */
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        // parse the argument
        while(true){
            Grep grep = new Grep();
            grep.output_code = grep.run(scanner);
            // handle echo here somewhere
        }

    }

    private int run(Scanner scanner) {
        try {
            // show % sign
            System.out.print("% ");
            // get the input from user
            String input = scanner.nextLine();
            // convert it to string array and pass it to parse method
            parseTheArguments(input.split(" "));
            // do some execution only if  there is some operation which needs to be performed
            if (passedFlags != null && passedFlags.size() > 0 && !wordToLookFor.isEmpty()){
                // the operation is to be performed on the files or the std input
                if (fileNames.size() > 0 && filesExists() || passedFlags.contains("-"))
                return execute();
            }

        } catch (InValidCommandException e) {
            System.err.println(e.getMessage());
            return 1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private boolean filesExists() {
        boolean returnValue = true;
        for (String filename : fileNames){
            File file = new File(filename);
            returnValue &= file.exists() && file.isFile();
        }
        return returnValue;
    }


    private int execute() throws FileNotFoundException {
        BufferedReader bufferedReader;
        if (passedFlags.contains("-")){
            System.out.println("please enter the text");
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        }else {
            bufferedReader = new BufferedReader(new FileReader(new File(fileNames.get(0))));
        }
        setRequiredFlags();
        executePassedFlagsCommand(bufferedReader);
        return 0;
    }

    private void setRequiredFlags() {
        for (String flag: passedFlags){
            switch (flag){
                case "-c":{
                    cValue = true;
                    break;
                }
                case "-w":{
                    wValue = true;
                    break;
                }
                case "-q":{
                    output_code = 0;
                    return;

                }
                case "-l":{
                    lValue = true;
                    break;
                }

            }
        }
    }

    private void executePassedFlagsCommand(BufferedReader reader) {
        if(passedFlags.contains("-q"))
        {
            return;
        }

        String regexToBeUsed =  wValue ? ".*(^|\\W)" + wordToLookFor + "(\\W|$).*" : ".*" + wordToLookFor + ".*";
        AtomicInteger count = new AtomicInteger();

        reader.lines().forEach(
                line -> {
                    if(Pattern.matches(regexToBeUsed,line)){
                        if (lValue)
                        {
                            System.out.println(fileNames.get(0));
                            lValue = false;
                            return;
                        }

                        if (cValue)
                            count.getAndIncrement();
                        else {
                            System.out.println(line);
                        }
                    }


                }
        );

        if (cValue)
            System.out.println(count);

    }


    /**
     * main parser of the utility which handles the argument passed by the user and converts them to what utiliity requires
     * @param commandLineParameters
     * @throws InValidCommandException
     */
    public void parseTheArguments(String[] commandLineParameters) throws InValidCommandException {

        passedFlags = new ArrayList<>();
        fileNames = new ArrayList<>();
        wordToLookFor = "";

        if (commandLineParameters[0].equals("echo"))
        {
            System.out.println(output_code);
            return;
        }

        for (int index = 1; index < commandLineParameters.length; index++) {
            String flag = commandLineParameters[index];
            if (allowedFlags.containsKey(flag)) {
                passedFlags.add(flag);
            }
            else {
                if (wordToLookFor.isEmpty()){
                    wordToLookFor = flag;
                }
                else {
                    fileNames.add(flag);
                }
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
        allowedFlags.put("-l",null);
        allowedFlags.put("-c",null);
        allowedFlags.put("-w",null);
        allowedFlags.put("-q",null);
        allowedFlags.put("-",null);
        return allowedFlags;
    }

}
