/**
 * ConsumerProducer.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
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


public class Consumer extends Thread {
    static int port = 1065;

    static String o = "";

    // name of thread P<Integer> or C<Integer>
    final String threadName;

    // array list to store the items which would accessed by producer and consumer
    static ArrayList<Integer> item1;
    static ArrayList<Integer> item2;
    static ArrayList<Integer> item3;
    static int capacityOfBuffer;

    // Static Semaphore on which the implementation is synchronized.
    static Semaphore mutex = new Semaphore(1);


    // Number of times production or consumption happens
    static int count = 0;

    /**
     * Constructor
     * @param threadName String with C or P indicating Consumer or Producer
     * @param itemsThreadCanProduceInOneGo number of items to produce or consume at a time
     */

    public Consumer(String threadName) {
        this.threadName = threadName;
    }

    public void consume() {
        try {
            sleep(1000);

            Socket socket = new Socket(InetAddress.getLocalHost(), port);

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            String write_line = threadName;
            printWriter.write(write_line);
            System.out.println("Writing : " + write_line);
            printWriter.close();

            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        synchronized (o) {
            if (threadName.contains("C")) {
                consume();
                o.notifyAll();
                try {
                    o.wait();
                }
                catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }


    /**
     * Checks if any argument is a non number then throw the exception
     */
    public static void areArgumentsNumbers(String argument0, String argument1) {//throws NoNumberArgumentException {

        // Integer.parseInt will throw an exception if non int provided to it
        // Hence we throw an exception when Integer's exception is caught.
        try {
            Integer.parseInt(argument0);
            Integer.parseInt(argument1);
        }
        catch (NumberFormatException notIntException) {
            notIntException.printStackTrace();
            //throw new NoNumberArgumentException("Argument is not a number");
        }

    }

    /**
     * Checks if any argument is negative then throw an exception.
     */
    public static void areArgumentsNegative(int argument0, int argument1) {//throws NegativeNumberArgumentException {

        if (argument0 < 0 || argument1 < 0) {
            //throw new NegativeNumberArgumentException("Argument is negative");
        }
    }

    /**
     * Main function.
     */
    public static void main(String args[]) {

        int num_consumers = 3;


        if (args.length < 2) {
            capacityOfBuffer = 100;
        }
        else {
            int argument0, argument1, argument2;

            //try {

            /* Throw exceptions */
            areArgumentsNumbers(args[0], args[1]);

            argument0 = Integer.parseInt(args[0]);
            argument1 = Integer.parseInt(args[1]);

            areArgumentsNegative(argument0, argument1);

            num_consumers = argument0;
            capacityOfBuffer = argument1;
            if (num_consumers > capacityOfBuffer){
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
        Consumer consumers[] = new Consumer[num_consumers];

        for (int consumerCount = 0; consumerCount < num_consumers; consumerCount++) {
            consumers[consumerCount] = new Consumer("C" + (consumerCount + 1));
        }

        // Set buffer capacity
        //StorageLocal.setBufferCapacity(capacityOfBuffer);



        // Start all producers
        for (int consumerCount = 0; consumerCount < num_consumers; consumerCount++) {
            consumers[consumerCount].start();
        }
    }
}