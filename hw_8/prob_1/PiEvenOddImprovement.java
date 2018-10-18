import java.io.*;


public class PiEvenOddImprovement {
    static long odd = 0;
    static long even = 0;

    public static void main(String args[])  {
        try {
            Reader stream = Helper.getStream(args);
            countEvenOddDigitInPI(stream);
            printResult();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            while (true) {
                // convert to short because the range of digit is from 48 to 57
                digit = (short) bufferedReader.read();
                // TODO: find a better mechanism to detect the end of file
                if (checkDigitIsValid(digit))
                    checkIfDigitIsEvenOrOdd(digit);
                else
                    break;
            }
        }
        catch (Exception IOException){
                    IOException.printStackTrace();
        }

    }

    private static boolean checkDigitIsValid(short digit) {
        return  digit != -1;
    }

    private static void checkIfDigitIsEvenOrOdd(short digit) {
        if (!(digit >= 49 && digit <= 57))
            return;
        if (digit % 2 == 0) {
            even++;
        } else {
            odd++;
        }
    }
}
