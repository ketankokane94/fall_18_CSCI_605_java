import java.io.*;

public class PiEvenOddImprovement {
    // initialise it to one because we know 3 is always going to be there
    static long odd = 1;
    static long even = 0;

    public static void main(String args[])  {
        Reader reader = new StringReader("3.1415926535");
        File PIFile = getRequiredFile();
       // reader = new InputStreamReader(PIFile);
            countEvenOddDigitInPI(reader);
            printResult();
    }

    private static File getRequiredFile() {
            return null;
    }


    private static void printResult() {
        StringBuilder stringBuilder = new StringBuilder("even = ");
        stringBuilder.append(even).append("\n");
        stringBuilder.append("odd  = ").append(odd).append("\n");
        stringBuilder.append("odd/even  = ").append((double)odd/even);
        System.out.println(stringBuilder.toString());
    }

    private static void countEvenOddDigitInPI(Reader reader)   {

        BufferedReader  bufferedReader = new BufferedReader(reader);
        short digit;
        // TODO: FIND BETTER WAY TO SKIP 3. FROM THE INPUT
        try {
            bufferedReader.read();
            bufferedReader.read();
            while (true) {
                // convert to short because the range of digit is from 48 to 57
                digit = (short) bufferedReader.read();
                // TODO: find a better mechanism to detect the end of file
                if (digit != -1)
                    checkIfDigitIsEvenOrOdd(digit);
                else
                    break;
            }
        }
        catch (Exception IOException){
                    IOException.printStackTrace();
        }

    }

    private static void checkIfDigitIsEvenOrOdd(short digit) {
        if (digit != -1 && digit % 2 == 0) {
            even++;
        } else {
            odd++;
        }
    }
}
