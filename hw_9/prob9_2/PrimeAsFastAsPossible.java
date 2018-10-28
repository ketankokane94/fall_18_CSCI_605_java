/**
 * PrimeAsFastAsPossible.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

/**
 * Experimentation to find prime numbers upto N using different number of threads and finding the optimum result
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

import java.util.BitSet;

class PrimeAsFastAsPossible extends Thread{

    public static int notPrimeNumbers;
    public static Object keyUsedForSynchronization;
    public static BitSet primeNumberStore;
    public static long result[];

    int start, end;

    /**
     * constructor which assigns each thread a range of numbers which it has to work with
     * @param start
     * @param end
     */
    public PrimeAsFastAsPossible(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * run method of thread which cancels out all the numbers within range which are multiple of 2 upto N
     */
    public void run(){
        int numberOfPrimes = 0;
        int halfOfEnd = end/2;
        for (int index = 2; index < halfOfEnd; index++) {
            int multiplier = start/index < 2 ? 2: start/index;
            int number = index * multiplier;
            while (number <= end){
                primeNumberStore.set(number);
            multiplier++;
            number = index * multiplier;
            }
        }
        synchronized (keyUsedForSynchronization){
            notPrimeNumbers+=numberOfPrimes;
        }
    }

    /**
     * Checks if any argument is a non number then throw the exception
     */
    public static void areArgumentsNumbers(String argument) throws NoNumberArgumentException {

        // Integer.parseInt will throw an exception if non int provided to it
        // Hence we throw an exception when Integer's exception is caught.
        try {
            int a = Integer.parseInt(argument);
        }
        catch (NumberFormatException notIntException) {
            //notIntException.printStackTrace();
            throw new NoNumberArgumentException("Argument is not a number");
        }

    }

    /**
     * Checks if any argument is negative then throw an exception.
     */
    public static void areArgumentsNegative(int argument) throws NegativeNumberArgumentException {

        if (argument < 0) {
            throw new NegativeNumberArgumentException("Argument is negative");
        }
    }

    /**
     * main driver method of the program which initialises few properties of the experiment
     * @param args
     * @throws InterruptedException
     */
    public static void main(String args[]) throws InterruptedException {
        // input format : N minNumberOfThread maxNumnerOfThread showExecutionLog[y|n]

        int N=10000000;
        int maxNumberOfThread = 20,minNumberOfThread=1;
        boolean showLog = true;
        if (args.length == 0){
            System.out.println("No arguments provided program will run with Default parameters N = 10000000");
        }
        else {
            try {

                // Throw Exceptions
                areArgumentsNumbers(args[0]);

                N = Integer.parseInt(args[0]);

                areArgumentsNegative(N);

                if (args.length >= 3) {
                    // Throw exceptions
                    areArgumentsNumbers(args[1]);
                    areArgumentsNumbers(args[2]);

                    minNumberOfThread = Integer.parseInt(args[1]);
                    maxNumberOfThread = Integer.parseInt(args[2]);

                    // Throw exceptions
                    areArgumentsNegative(minNumberOfThread);
                    areArgumentsNegative(maxNumberOfThread);

                    if (minNumberOfThread > maxNumberOfThread) {
                        minNumberOfThread = 1;
                        maxNumberOfThread = 20;
                    }
                }
                if (args.length == 4) {
                    showLog = args[3].equalsIgnoreCase("y");
                }
            }
            catch (NoNumberArgumentException noNumberArgumentException) {
                noNumberArgumentException.printStackTrace();
                System.exit(0);
            }
            catch (NegativeNumberArgumentException negativeNumberArgumentException) {
                negativeNumberArgumentException.printStackTrace();
                System.exit(0);
            }
        }
        result = new long[maxNumberOfThread];
        keyUsedForSynchronization = new Object();

        for (int numberOfThreads = minNumberOfThread; numberOfThreads < maxNumberOfThread; numberOfThreads++) {
            runExperiment(N,numberOfThreads,showLog);
        }
        printResult(minNumberOfThread);

    }

    /**
     * Helper function to find the thread which required the least amount of time and prints the result
     * @param minNumberOfThread
     */
    private static void printResult(int minNumberOfThread) {
        long minimum = result[minNumberOfThread];
        int threadNumber = minNumberOfThread;
        for (int index = minNumberOfThread; index < result.length; index++) {
            if (result[index] < minimum){
                minimum = result[index];
                threadNumber = index;
            }
        }

        System.out.println("The optimal of threads = "+threadNumber+" took "+ result[threadNumber]+ " ms");
    }

    /**
     * function which starts the experiment based on the provided parameters
     * @param N find prime numbers upto this number
     * @param numberOfThreads number of threads to be used
     * @param showLog should the execution log be displayed
     * @throws InterruptedException
     */
    private static void runExperiment(int N,int numberOfThreads,boolean showLog) throws InterruptedException {
        {
            primeNumberStore = new BitSet(N);
            notPrimeNumbers= 0;
            long startTime = System.currentTimeMillis();
            int start = 2;
            int range = N / numberOfThreads;
            int end = range;
            // create a thread array of the threads to be used
            PrimeAsFastAsPossible prime[] = new PrimeAsFastAsPossible[numberOfThreads];
            for (int index = 0; index < numberOfThreads - 1; index++) {
                prime[index] = new PrimeAsFastAsPossible(start, end);
                prime[index].start();
                start = end + 1;
                end = end + range;
            }
            // specify the end of the last thread correctly
            if (numberOfThreads != 1) {
                end = N;
            }
            // initialise the last or the only thread
            prime[numberOfThreads - 1] = new PrimeAsFastAsPossible(start, end);
            prime[numberOfThreads - 1].start();

            // wait for the all the threads to be completed before printing the result
            for (int index = 0; index < numberOfThreads; index++) {
                prime[index].join();
            }

            long endTime = System.currentTimeMillis();
            int count = 0;

            // count the number of prime numbers found
            for (int index = 2; index <= N; index++) {
                if (!primeNumberStore.get(index)) {
                    count++;
                }
            }
            // based on the flag provided decide if the below result needs to be printed for every thread or not
            if (showLog){
                System.out.println("number of threads used " + numberOfThreads);
                System.out.println("number of prime numbers from 2 to "+ N + " = " + count);
                System.out.println("Took " + (endTime - startTime) + " ms");
            }
            // log the time in the array to allow later for finding the min time
            result[numberOfThreads] = endTime-startTime;
        }
    }
}
