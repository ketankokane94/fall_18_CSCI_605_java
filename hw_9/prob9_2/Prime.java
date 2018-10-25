import java.util.BitSet;

class Prime extends Thread{
    int start, end;

    public static BitSet notPrimeNumbers;
    public Prime(int start, int end) {
        this.start = start;
        this.end = end;
    }


    public void run(){
        int limiter = end/2;
        for (int index = 2; index < limiter; index++) {
            int multiplier = 2;
            int number = index * multiplier;
            while (number <= end){
            notPrimeNumbers.set(number);
                multiplier++;
                number = index * multiplier;
            }
        }
    }

    public static void main(String args[]){

        int N=10000000;
        notPrimeNumbers  = new BitSet(N);
        long startTime = System.currentTimeMillis();
        int start=2,end, numberOfThreads = 2;
        end =  N/numberOfThreads;
        Prime prime[] = new Prime[numberOfThreads];
        for (int index = 0; index < numberOfThreads-1; index++) {
            new Prime(start,end).run();
            start = end + 1;
            end = end + end;
        }
        start = end + 1;
        end = N;
        new Prime(start,end).run();

        long endTime = System.currentTimeMillis();
        int count = 0;
        for (int i1 = 2; i1 <= N; i1++) {
            if (!notPrimeNumbers.get(i1)){
                count++;
            }
        }
        //664,579 , 1379 sequential
        //664579 ,
        System.out.println("number of prime numbers from " + count);
        System.out.println("Took "+(endTime - startTime) + " ms");
    }
}
