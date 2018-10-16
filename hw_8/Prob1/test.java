import java.io.*;

public class test {
    static long odd = 1;
    static long even = 0;
    public static void main(String args[])  {
        Reader reader = new StringReader("3.1415926535");
        try {
            countEvenOddDigitInPI(reader);
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

    private static void countEvenOddDigitInPI(Reader reader) throws IOException {
        BufferedReader  bufferedReader = new BufferedReader(reader);
        int digit;
        bufferedReader.read();
        bufferedReader.read();
        while (true) {
            digit = bufferedReader.read();
            if(digit==-1)
                break;
            if (digit != -1 && digit % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }

    }
}
