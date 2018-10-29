/**
 * ConsumerProducer.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

/**
 * Program to use multithreading to create producers producing items and consumers consuming them
 * while notifying each other.
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

import java.util.Scanner;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ConsumerProducer extends Thread {
    String info;

    // Static variable on which we synchronize.
    static String o = "";
    // number of items produced or consumed
    int howMany;

    static int storageSpaceLeft;
    static int storageSpace;

    static boolean someConsumerWaiting;
    static boolean someProducerWaiting;

    // Number of times production or consumption happens
    static int count;

    /**
     * Constructor
     * @param info String with C or P indicating Consumer or Producer
     * @param howMany number of items to produce or consume at a time
     */
    public ConsumerProducer(String info, int howMany) {
        this.info = info;
        this.howMany = howMany;
        someConsumerWaiting = false;
        someProducerWaiting = false;
        count = 0;
    }

    /**
     * Helper function to update storage and other info on production of howMany items
     */
    public void produce() {
        // Produce
        storageSpaceLeft -= howMany;

        count++;

        System.out.println(count + ". " + info + " produced " + howMany);
    }

    /**
     * Helper function to update storage and other info on consumption of howMany items
     */
    public void consume() {
        // Consume
        storageSpaceLeft += howMany;

        count++;

        System.out.println(count + ". " + info + " consumed " + howMany);
    }

    /**
     * Run method for the threads
     */
    public void run()
    {

        // Loop to make process happen in tandem endlessly
        while (true) {
            synchronized (o) {
                if (count == 10000) {
                    // Notify any thread that is waiting since
                    // we have finished process as many times as count
                    o.notifyAll();
                    break;
                }

                // For consumer
                if (info.contains("C")) {
                    // storageSpace - storageSpaceLeft gives number of items in storage
                    if (storageSpace - storageSpaceLeft >= howMany) {

                        // Consume if number of items in storage is atleast = howMany
                        consume();

                        //System.out.println("Producers waiting : " + someProducerWaiting);

                    }

                    /* Wake up producers if any waiting */

                    if (someProducerWaiting) {
                        o.notifyAll();
                        someProducerWaiting = false;
                    }

                    try {
                        someConsumerWaiting = true;

                        /* Wait */

                        //System.out.println(info + " waiting");
                        o.wait();
                    }
                    catch (InterruptedException ie) {
                        someConsumerWaiting = false;
                        ie.printStackTrace();
                    }

                }
                // For producer
                else if (info.contains("P")) {
                    if (storageSpaceLeft >= howMany) {

                        // Produce if number of spaces available is atleast = howMany
                        produce();

                        //System.out.println("Consumers waiting : " + someConsumerWaiting);

                    }

                    /* Wake up consumers if any waiting */
                    if (someConsumerWaiting) {
                        o.notifyAll();
                        someConsumerWaiting = false;
                    }

                    try {

                            /* Wait */

                            someProducerWaiting = true;
                            //System.out.println(info + " waiting");
                            o.wait();

                        } catch (InterruptedException ie) {
                            someProducerWaiting = false;
                            ie.printStackTrace();
                        }
                }
                //System.out.println(info + " Storage Space Left : " + storageSpaceLeft);
            }
        }
        //System.out.println(" Done " + info);
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
        int num_producers = 2;
        int num_produced = 4;
        int num_consumed = 4;

        if (args.length < 5) {
            ConsumerProducer.storageSpace = 100;
            ConsumerProducer.storageSpaceLeft = 100;
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

                ConsumerProducer.storageSpace = argument4;
                ConsumerProducer.storageSpaceLeft = argument4;
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