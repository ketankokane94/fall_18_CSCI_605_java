import java.util.BitSet;

class Prime extends Thread{
    int start, end;

    public static int notPrimeNumbers;
    public static Object key;

    public Prime(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run(){
        int numberOfPrimes = 0;
        int limiter = end/2;
        BitSet bits = new BitSet();

        for (int index = 2; index < limiter; index++) {
            int multiplier = start/index < 2 ? 2: start/index;
            int number = index * multiplier;
            while (number <= end){
            bits.set(number);
                multiplier++;
                number = index * multiplier;
            }
        }
        for (int i1 = start; i1 <= end; i1++) {
            if (!bits.get(i1)){
                numberOfPrimes++;
            }
        }
        synchronized (key){
            notPrimeNumbers+=numberOfPrimes;
        }
    }

    public static void main(String args[]) throws InterruptedException {
        key = new Object();
        int N=10000000;
        notPrimeNumbers=0;
        long startTime = System.currentTimeMillis();
        int start = 2, numberOfThreads = 12;
        int range =  N/numberOfThreads;
        int end = range;
        Prime prime[] = new Prime[numberOfThreads];
        for (int index = 0; index < numberOfThreads-1; index++) {
            prime[index] = new Prime(start,end);
            prime[index].start();
            start = end + 1;
            end = end + range;
        }
        if (numberOfThreads!=1){
            end = N;
        }


        prime[numberOfThreads-1] = new Prime(start,end);
        prime[numberOfThreads-1].start();


        for (int index = 0; index < numberOfThreads; index++) {
            prime[index].join();
        }

        long endTime = System.currentTimeMillis();
        //int count = 0;
        /*
        for (int i1 = 2; i1 <= N; i1++) {
            if (!notPrimeNumbers.get(i1)){
                count++;
            }
        }*/
        //664,579 , 1379 sequential
        //664579 ,
        System.out.println("number of prime numbers from " + notPrimeNumbers);
        System.out.println("Took "+(endTime - startTime) + " ms");
    }
}
