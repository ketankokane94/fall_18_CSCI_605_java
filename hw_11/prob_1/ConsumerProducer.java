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


public class ConsumerProducer extends Thread {
    // name of thread P<Integer> or C<Integer>
    final String threadName;

    // number of items produced or consumed
    final int itemsThreadCanProduceInOneGo;

    // array list to store the items which would accessed by producer and consumer
    static ArrayList<Integer> item1;
    static ArrayList<Integer> item2;
    static ArrayList<Integer> item3;


    // Static Semaphore on which the implementation is synchronized.
    static Semaphore mutex = new Semaphore(1);


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
     * Helper function to produce the required item when the producer is running.
     */
    public void produce() {
        int itemToProduce =  getItemToProduce();
        // Produce
        switch (itemToProduce){
            case 1:{
                // TODO change 100 to a variable

                if (item1.size() + itemsThreadCanProduceInOneGo <= 100){
                    for (int i = 0; i < itemsThreadCanProduceInOneGo; i++) {
                        item1.add(0,1);
                    }
                }
                break;
            }
            case 2:{
                if (item2.size() + itemsThreadCanProduceInOneGo <= 100){
                    for (int i = 0; i < itemsThreadCanProduceInOneGo; i++) {
                        item2.add(0,1);
                    }
                }
                break;
            }
            case 3:{
                if (item3.size() + itemsThreadCanProduceInOneGo <= 100){
                    for (int i = 0; i < itemsThreadCanProduceInOneGo; i++) {
                        item3.add(0,1);
                    }
                }
                break;
            }
        }
        count++;
        System.out.println(count + ". " + threadName + " produced item "+ itemToProduce );
    }


    /**
     * Helper function Consume the items produced by the producer
     */
    public void consume() {
        System.out.println(count + ". " + threadName + " consumed " );
        for (int i = 0; i < 2 && item1.size() > 0; i++) {
            item1.remove(0);
        }

        System.out.println(item2.size());

        for (int i = 0; i < 5 && item2.size() > 0 ; i++) {
            item2.remove(0);
        }

        for (int i = 0; i < 3  && item3.size() > 0 ; i++) {
            item3.remove(0);
        }

        count++;

    }

    public void run()
    {

        while (true) {
                mutex.acquire(1);
                if (threadName.contains("C")) {
                    if (item1.size() >= 2 && item2.size() >= 5 && item3.size() >= 3) {
                        consume();
                    }
                }
                else
                    if (threadName.contains("P")) {
                        produce();
                }
                mutex.release(1);
        }
    }

    /**
     * helper function to decide which item has to be produced next
     * @return
     */
    private int getItemToProduce()  {

        // if item 2 is running low produce item2
        if (item2.size()-5 <= 5)
            return 2;

        // if item 3 is running low produce item2
        if (item3.size()-3 <= 3)
            return 3;

        // if item 1 is running low produce item2
        if (item1.size()-2 <= 2)
            return 1;

    // based on the size decide which item should be produced next
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
            notIntException.printStackTrace();
            throw new NoNumberArgumentException("Argument is not a number");
        }

    }

    /**
     * Checks if any argument is negative then throw an exception.
     */
    public static void areArgumentsNegative(int argument0, int argument1, int argument2, int argument3, int argument4) throws NegativeNumberArgumentException {

        if (argument0 < 0 || argument1 < 0 || argument2 < 0 || argument3 < 0 || argument4 < 0) {
            throw new NegativeNumberArgumentException("Argument is negative");
        }
    }

    /**
     * Main function.
     */
    public static void main(String args[]) {
        // optimal number of threads = 2
        int num_consumers = 2;
        int num_producers = 3;
        int num_produced = 4;
        int num_consumed = 4;
        item1 = new ArrayList<>(100);
        item2 = new ArrayList<>(100);
        item3 = new ArrayList<>(100);


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
    }
}