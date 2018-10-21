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
            System.err.println(e.getMessage());
        } catch (EmptyFileException e) {
            System.err.println(e.getMessage());
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
              //  int d  = bufferedReader.read();
                digit = (short) bufferedReader.read();
                // TODO: find a better mechanism to detect the end of file
                if (checkDigitIsValid(digit)) {
                    checkIfDigitIsEvenOrOdd(digit);
                }
                else {
                    break;
                }
            }
        }
        catch (IOException IOException){
                    IOException.printStackTrace();
        } catch (NoNumbersException e) {
            System.err.println(e.getMessage());
        }

    }

    private static boolean checkDigitIsValid(short digit) {
        return  digit != -1;
    }

    private static void checkIfDigitIsEvenOrOdd(short digit) throws NoNumbersException {
        if (!(digit >= 48 && digit <= 57))
            if (digit != 46)
            {
                System.err.println(digit);
                throw new NoNumbersException(" NoNumbersException null is empty");
            }

        if (digit % 2 == 0) {
            even++;
        } else {
            odd++;
        }
    }
}
