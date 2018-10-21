
import java.io.*;
import java.util.zip.GZIPInputStream;

public class Helper {

    public static Reader getStream(String args[]) throws IOException {
        Reader reader = null;
        if (args.length > 0) {
            // means some command line argument was passed to this program
            if(checkIfFileNameIsPassed(args[0])) {
                File file = getFile(args[0]);
                reader = new InputStreamReader(new FileInputStream(file));
                if (args[0].contains(".tz")) {
                    reader = new InputStreamReader(new GZIPInputStream(new FileInputStream(file)));
                }
            }
            else {
                // some string is passed in the command line argument parse it
                reader = new StringReader(args[0]);
            }
        }
        else {
            // means no argument was passed by the command line so the string would be read from stdin
            reader = new InputStreamReader(System.in);

        }
    return reader;
    }

    private static File getFile(String fileName) throws IOException {
            File fileToBeReturned = null;
            fileToBeReturned = new File(new File("").getCanonicalPath()+ fileName);
            return fileToBeReturned;
    }

    private static boolean checkIfFileNameIsPassed(String argument) {
        // not a good way to check if the argument passed is file name or no, but based on specs given is OK
        return argument != null && argument.contains(".txt");
    }
}

