/**
 * Prime.java
 * 
 * Version :
 *          1.0
 * Revisions : 
 *          1.0
 */


 /**
  * This program Finds the sum of the prime factors of a given number 
  *
  * @author Ketan Balbhim Kokane
  * @author Ameya Deepak Nagnur
  */

  public class Prime {
    //Array to store all prime numbers from 2 to N
    public static int[] primeNumbersUptoN = new int[4];
    //Array to store all the prime factorials from index 1 and their sum at index 0
    public static int[] result = new int[5];
    
    /**
     * The main Program 
     * 
     * @param  args     command Line arguments(ignored) 
     */

    public static void main(String args[]) {
        int internalArrayIndex = 0;
        
        //Store number in array primeNumbersUptoN if it is prime
        for (int index = 2; index <= 10; index++) {
            if (isPrime(index)) {
                primeNumbersUptoN[internalArrayIndex] = index;
                internalArrayIndex++;
            }
        }

        //If prime number divides N then add it to result and check again from start for N/(the prime number)
        for (int index = 2; index <= 10; index++) {
            int N = index;
            int primeNumbersIndex = 0, resultIndex = 1;
            while (N > 1) {
                if (N % primeNumbersUptoN[primeNumbersIndex] == 0) {
                    //result at 0 stores sum of prime factorials
                    result[0] += primeNumbersUptoN[primeNumbersIndex];
                    //result from 1 to end of array stores all the prime factorials found
                    result[resultIndex] = primeNumbersUptoN[primeNumbersIndex];
                    //Divide N by the prime factorial found to find next prime factorial which can repeat
                    N = N / primeNumbersUptoN[primeNumbersIndex];
                    resultIndex++;
                    primeNumbersIndex = 0;
                }
                else {
                    primeNumbersIndex++;
                }

            }
            //Print prime factorials of number index and their sum
            printResult(result,index);
            result = new int[5];
        }

    }

    /**
     * Check if number n is prime or not by checking if it is divisible
     * by any number from 2 to n - 1
     *
     * @param   n   number to check for prime
     */
    
    public static boolean isPrime(int n) {

        for (int index = 2; index < n; index++) {
            if (n % index == 0)
                return false;
        }
        return true;
    }

    /**
     * Print prime factorials of number index and their sum
     * 
     * @param     result    Array with prime factorials of index and their sum
     * @param      index     Number for which the sum of prime factorials has been found
     */
    
    public static void printResult(int[] result, int index){
        
        //Print the result in the required format Eg. The sum of all primes for 4: 4    (2 + 2)
        System.out.print("The sum of all primes for "+ index+ ": "+result[0] + "  (");
        for (int resultIndex = 1; resultIndex < result.length; resultIndex++) {
            
            if(result[resultIndex]!=0)
            {
                if(resultIndex > 1)
                    System.out.print(" + ");
                System.out.print(result[resultIndex]);
            }
                
            
        }
        System.out.print(")");
        System.out.println();
    }

}
