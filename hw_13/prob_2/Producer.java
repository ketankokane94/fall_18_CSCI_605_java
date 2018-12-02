/**
 * Producer.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

import java.util.ArrayList;


/**
 * simple implementation of consumer producer problem using semaphores
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

/**
 * working:
 * producers can produce 3 different items, consumer can consume only when certain number of items are present,
 * in this instance 2 of item1 5 of item2 3 of item3. In one go.
 * Used one single semaphore to maintain the working of the producer and consumer, resulting that no two threads can work simultaneously
 */


public class Producer extends Thread {
    // name of thread P<Integer> or C<Integer>
    final String threadName;

    static Object o;

    // number of items produced or consumed
    final int itemsThreadCanProduceInOneGo;

    // array list to store the items which would accessed by producer and consumer
    static ArrayList<Integer> item1;
    static ArrayList<Integer> item2;
    static ArrayList<Integer> item3;
    static int capacityOfBuffer;

    // Static Semaphore on which the implementation is synchronized.
    //static Semaphore mutex = new Semaphore(1);


    // Number of times production or consumption happens
    static int count = 0;

    /**
     * Constructor
     * @param threadName String with C or P indicating Consumer or Producer
     * @param itemsThreadCanProduceInOneGo number of items to produce or consume at a time
     */

    public Producer(String threadName, int itemsThreadCanProduceInOneGo) {
        this.threadName = threadName;
        this.itemsThreadCanProduceInOneGo = itemsThreadCanProduceInOneGo;
    }

    /**
     * Checks if any argument is a non number then throw the exception
     */
    public static void areArgumentsNumbers(String argument0, String argument1, String argument2) {//throws NoNumberArgumentException {

        // Integer.parseInt will throw an exception if non int provided to it
        // Hence we throw an exception when Integer's exception is caught.
        try {
            Integer.parseInt(argument0);
            Integer.parseInt(argument1);
            Integer.parseInt(argument2);
        }
        catch (NumberFormatException notIntException) {
            notIntException.printStackTrace();
            //throw new NoNumberArgumentException("Argument is not a number");
        }

    }

    /**
     * Checks if any argument is negative then throw an exception.
     */
    public static void areArgumentsNegative(int argument0, int argument1, int argument2) {//throws NegativeNumberArgumentException {

        if (argument0 < 0 || argument1 < 0 || argument2 < 0) {
            //throw new NegativeNumberArgumentException("Argument is negative");
        }
    }

    /**
     * Main function.
     */
    public static void main(String args[]) {

        int num_produced = 2;
        int num_producers = 4;


        if (args.length < 3) {
            capacityOfBuffer = 100;
        }
        else {
            int argument0, argument1, argument2;

            //try {

                /* Throw exceptions */
                areArgumentsNumbers(args[0], args[1], args[2]);

                argument0 = Integer.parseInt(args[0]);
                argument1 = Integer.parseInt(args[1]);
                argument2 = Integer.parseInt(args[2]);

                areArgumentsNegative(argument0, argument1, argument2);

                num_producers = argument1;
                capacityOfBuffer = argument2;
                if (num_produced > capacityOfBuffer){
                    System.err.println("Buffer underflow");
                    System.exit(1);
                }

            //}
            /*catch (NoNumberArgumentException noNumberArgumentException) {
                noNumberArgumentException.printStackTrace();
                System.exit(0);
            }
            catch (NegativeNumberArgumentException negativeNumberArgumentException) {
                negativeNumberArgumentException.printStackTrace();
                System.exit(0);
            }*/
        }
        StorageLocal.item1 = new ArrayList<>(capacityOfBuffer);
        StorageLocal.item2 = new ArrayList<>(capacityOfBuffer);
        StorageLocal.item3 = new ArrayList<>(capacityOfBuffer);

        // Create producers
        StorageLocal producers[] = new StorageLocal[num_producers];

        for (int producerCount = 0; producerCount < num_producers; producerCount++) {
            producers[producerCount] = new StorageLocal("P" + (producerCount + 1), num_produced);
        }

        // Set buffer capacity
        StorageLocal.setBufferCapacity(capacityOfBuffer);



        // Start all producers
        for (int producerCount = 0; producerCount < num_producers; producerCount++) {
            producers[producerCount].start();
        }
    }
}