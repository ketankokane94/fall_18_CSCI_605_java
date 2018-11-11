/**
 * ConsumerProducer.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

import java.util.ArrayList;

/**
 * Program to use multithreading to create producers producing items and consumers consuming them
 * while notifying each other.
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

/**
 * how should the working be
 * consumer should consume only when items are full
 *
 * if 3 producers then all 3 should produce simultaneously
 */


public class ConsumerProducer extends Thread {
    String threadName;

    // arrayList where prdducers can produce items
    static ArrayList<Integer> item1;
    static ArrayList<Integer>  item2;
    static ArrayList<Integer>  item3;
    static ArrayList<Integer> sequenceOfItemProduction;

    // Static variable on which we synchronize.
    static Semaphore ConsumerRunning = new Semaphore(1);
    static Semaphore lock = new Semaphore(3);
    static Semaphore canProduce = new Semaphore(100);

    // number of items produced or consumed
    int itemsThreadCanProduceInOneGo;

    // Number of times production or consumption happens
    static int count = 0;

    /**
     * Constructor
     * @param threadName String with C or P indicating Consumer or Producer
     * @param itemsThreadCanProduceInOneGo number of items to produce or consume at a time
     */
    public ConsumerProducer(String threadName, int itemsThreadCanProduceInOneGo) {
        this.threadName = threadName;
        this.itemsThreadCanProduceInOneGo = itemsThreadCanProduceInOneGo;
    }

    /**
     * Helper function to update storage and other threadName on production of itemsThreadCanProduceInOneGo items
     * @param itemToProduce
     */
    public void produce() {
        int itemToProduce =  getItemToProduce();
        // Produce
        switch (itemToProduce){
            case 1:{
                if (item1.size() + itemsThreadCanProduceInOneGo <= 100){
                    for (int i = 0; i < itemsThreadCanProduceInOneGo; i++) {
                        item1.add(1);
                    }
                }
                else{

                }
                break;
            }
            case 2:{
                if (item2.size() + itemsThreadCanProduceInOneGo <= 100){
                    for (int i = 0; i < itemsThreadCanProduceInOneGo; i++) {
                        item2.add(1);
                    }
                }
                break;
            }
            case 3:{
                if (item3.size() + itemsThreadCanProduceInOneGo <= 100){
                    for (int i = 0; i < itemsThreadCanProduceInOneGo; i++) {
                        item3.add(1);
                    }
                }
                break;
            }
            default:{
                break;
            }
        }
        count++;
        System.out.println(count + ". " + threadName + " produced item "+ itemToProduce );
    }

    /**
     * Helper function to update storage and other threadName on consumption of itemsThreadCanProduceInOneGo items
     */
    public void consume() {
        // Consume
        for (int i = 0; i < 2 ; i++) {
            item1.remove(0);
        }
        for (int i = 0; i < 5 ; i++) {
            item2.remove(0);
        }
        for (int i = 0; i < 3 ; i++) {
            item3.remove(0);
        }

        count++;

        System.out.println(count + ". " + threadName + " consumed " );
    }

    /**
     * Run method for the threads
     */
    public void run()
    {

        // Loop to make process happen in tandem endlessly
        while (true) {
                // For consumer
                if (threadName.contains("C")) {
                    // storageSpace - storageSpaceLeft gives number of items in storage
                    System.out.println("Consumer Runnig " + item1.size() +" "+ item2.size()+ " "+ item3.size() + "  " +lock.availablePermits()+"  " + canProduce.availablePermits());
                    if (item1.size() >= 2 && item2.size() >= 5 && item3.size() >= 3) {

                        //try {

                            ConsumerRunning.acquire(1);
                            lock.acquire(3);
                            consume();
                            canProduce.release(10);
                            lock.release(3);
                            ConsumerRunning.release(1);
                        //} catch (InterruptedException e) {
                        //    e.printStackTrace();
                        //}

                    }
                }
                else if (threadName.contains("P")) {
                    // Produce if number of spaces available is atleast = itemsThreadCanProduceInOneGo
                    //try {
                        // can producer produce any more ?
                        canProduce.acquire(itemsThreadCanProduceInOneGo);
                         //int itemToProduce = getItemToProduce();
                        lock.acquire(1);
                        produce();
                        lock.release(1);

                    //} catch (InterruptedException e) {
                    //    e.printStackTrace();
                    //}
                    // int itemToProduce = getItemToProduce();

                }
        }
    }

    private int getItemToProduce()  {

        if (item2.size()-5 <= 5)
            return 2;

        if (item3.size()-3 <= 3)
            return 3;

        if (item1.size()-2 <= 2)
            return 1;


        if (item1.size() < item2.size()){
            if(item1.size() < item3.size()){
                return 1;
            }
            else {
                return 3;
            }
        }
        else if (item2.size() < item1.size()){
            if(item2.size() < item3.size()){
                return 2;
            }
            else {
                return 3;
            }
        }

    return 1;
    }


    /**
     * Checks if any argument is a non number then throw the exception
     */
    public static void areArgumentsNumbers(String argument0, String argument1, String argument2, String argument3, String argument4) throws NoNumberArgumentException {

        // Integer.parseInt will throw an exception if non int provided to it
        // Hence we throw an exception when Integer's exception is caught.
        try {
            int a0 = Integer.parseInt(argument0);
            int a1 = Integer.parseInt(argument1);
            int a2 = Integer.parseInt(argument2);
            int a3 = Integer.parseInt(argument3);
            int a4 = Integer.parseInt(argument4);
        }
        catch (NumberFormatException notIntException) {
            //notIntException.printStackTrace();
           // throw new NoNumberArgumentException("Argument is not a number");
        }

    }

    /**
     * Checks if any argument is negative then throw an exception.
     */
    public static void areArgumentsNegative(int argument0, int argument1, int argument2, int argument3, int argument4) throws NegativeNumberArgumentException {

        if (argument0 < 0 || argument1 < 0 || argument2 < 0 || argument3 < 0 || argument4 < 0) {
           // throw new NegativeNumberArgumentException("Argument is negative");
        }
    }

    /**
     * Main function.
     */
    public static void main(String args[]) {
        // optimal number of threads = 2
        int num_consumers = 1;
        int num_producers = 3;
        int num_produced = 4;
        int num_consumed = 4;
        item1 = new ArrayList<>(100);
        item2 = new ArrayList<>(100);
        item3 = new ArrayList<>(100);
        sequenceOfItemProduction = new ArrayList<>(3);
        sequenceOfItemProduction.add(1);
        sequenceOfItemProduction.add(2);
        sequenceOfItemProduction.add(3);

        if (args.length < 5) {
           // ConsumerProducer.storageSpace = 100;
          //  ConsumerProducer.storageSpaceLeft = 100;
        }
        else {
            int argument0, argument1, argument2, argument3, argument4;

            try {

                /* Throw exceptions */
                areArgumentsNumbers(args[0], args[1], args[2], args[3], args[4]);

                argument0 = Integer.parseInt(args[0]);
                argument1 = Integer.parseInt(args[1]);
                argument2 = Integer.parseInt(args[2]);
                argument3 = Integer.parseInt(args[3]);
                argument4 = Integer.parseInt(args[4]);

                areArgumentsNegative(argument0, argument1, argument2, argument3, argument4);

               // ConsumerProducer.storageSpace = argument4;
               // ConsumerProducer.storageSpaceLeft = argument4;
                num_consumers = argument0;
                num_producers = argument1;
                num_consumed = argument2;
                num_produced = argument3;

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

        // Create consumers
        ConsumerProducer consumers[] = new ConsumerProducer[num_consumers];

        for (int consumerCount = 0; consumerCount < num_consumers; consumerCount++) {
            consumers[consumerCount] = new ConsumerProducer("C" + (consumerCount + 1), num_consumed);
        }

        // Create producers
        ConsumerProducer producers[] = new ConsumerProducer[num_producers];

        for (int producerCount = 0; producerCount < num_producers; producerCount++) {
            producers[producerCount] = new ConsumerProducer("P" + (producerCount + 1), num_produced);
        }

        // Start all consumers
        for (int consumerCount = 0; consumerCount < num_consumers; consumerCount++) {
            consumers[consumerCount].start();
        }

        // Start all producers
        for (int producerCount = 0; producerCount < num_producers; producerCount++) {
            producers[producerCount].start();
        }

        /*ConsumerProducer obj1 = new ConsumerProducer("P1", 2);
        ConsumerProducer obj3 = new ConsumerProducer("P2", 2);

        obj2.start();
        obj1.start();
        obj3.start();
        obj4.start();*/
    }
}