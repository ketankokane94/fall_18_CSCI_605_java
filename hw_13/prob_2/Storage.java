/**
 * Storage.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.rmi.*;


/**
 * simple implementation of consumer producer problem using semaphores
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

/**
 * working:
 * Server that sits on a machine and updates item1, 2 and 3 storages based on strings recieved through
 * TCP/IP.
 */


public class Storage extends Thread {

    static Object o = new Object();

    String line = "";

    static int port = 1065;

    static ServerSocket serverSocket;

    String threadName = "";

    // array list to store the items which would accessed by producer and consumer
    static ArrayList<Integer> item1;
    static ArrayList<Integer> item2;
    static ArrayList<Integer> item3;
    static int capacityOfBuffer;

    static boolean lists_created = false;

    static String success_status = "";

    // Static Semaphore on which the implementation is synchronized.
    //static Semaphore mutex = new Semaphore(1);


    // Number of times production or consumption happens
    static int count = 0;

    /**
     * Constructor
     * @param line : the data read over RMI
     */

    public Storage(String line) {
        this.line = line;
    }

    /**
     * Helper function to Consume the items produced by the producer
     */
    public void consume() {
        if (item1.size() >= 2 && item2.size() >= 5 && item3.size() >= 3) {
            System.out.println(count + ". " + threadName + " consumed ");
            for (int i = 0; i < 2 && item1.size() > 0; i++) {
                item1.remove(0);
            }

            for (int i = 0; i < 5 && item2.size() > 0; i++) {
                item2.remove(0);
            }

            for (int i = 0; i < 3 && item3.size() > 0; i++) {
                item3.remove(0);
            }

            count++;

            success_status = "Success";
        }
        else {
            success_status = "Unsuccessful";
        }

    }

    /**
     * Helper function to produce the items
     */
    public void produce(String lineArray[]) {
        System.out.println(threadName + " : ");
        for (int arrayIndex = 1; arrayIndex < lineArray.length; arrayIndex++) {

            // For item1
            if (lineArray[arrayIndex].equals("item1")) {
                int num_items = Integer.parseInt(lineArray[arrayIndex + 1]);
                for (int index = 0; index < num_items; index++) {
                    item1.add(0, 1);
                }
                System.out.println("item1 added " + num_items + " times");
                success_status = "Success";
            }

            // For item2
            if (lineArray[arrayIndex].equals("item2")) {
                int num_items = Integer.parseInt(lineArray[arrayIndex + 1]);
                for (int index = 0; index < num_items; index++) {
                    item2.add(0, 1);
                }
                System.out.println("item2 added " + num_items + " times");
                success_status = "Success";
            }

            // For item3
            if (lineArray[arrayIndex].equals("item3")) {
                int num_items = Integer.parseInt(lineArray[arrayIndex + 1]);
                for (int index = 0; index < num_items; index++) {
                    item3.add(0, 1);
                }
                System.out.println("item3 added " + num_items + " times");
                success_status = "Success";
            }

        }
    }

    /**
     * Run method to delegate task to every thread. Updates storage based on TCP/IP data recieved from
     * producer or consumer
     */
    public void run() {

        /* Logic to update item lists here */

        synchronized (o) {

            if (line.charAt(0) == 'C' || line.charAt(0) == 'P') {

                // Get data into an array
                String lineArray[] = line.split(" ");

                // First value is the thread name
                threadName = lineArray[0];

                // For consumer simply call consume to consume and update storage
                if (threadName.contains("C")) {
                    consume();
                }
                // For producer update storage based on string sent by producer
                else {
                    produce(lineArray);
                }
            }
        }
    }

    /**
     * Get data through TCP/IP from producer or consumer and run threads to update storage
     */
    public void updateStorage()
    {

        while (true) {
            try {
                // Set sockets
                serverSocket = new ServerSocket(port);
                Socket socket = serverSocket.accept();

                // Set reader and read data when sent
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                line = bufferedReader.readLine();
                System.out.println("Reading : " + line);

                // Run threads
                Storage storage = new Storage(line);
                storage.start();

                // Close readers and sockets
                bufferedReader.close();

                socket.close();
                serverSocket.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Main function.
     */
    public static void main(String args[]) {
        //while (true) {
            try {

                //Scanner sc  = new Scanner(System.in);
                //System.out.print("Port number : ");
                //port = sc.nextInt();

                /* Get the capacity from producer at the start over RMI call to updateCapacity */

                // Object for capacity update
                ConsumerProducerInterface storageObject = new ConsumerProducerImplementation();
                Naming.rebind("//10.181.94.174/capacityObject", storageObject);

                sleep(1000);

            }
            catch (Exception e) {
                System.out.println("RMI Exception :");
                e.printStackTrace();
            }

            if(capacityOfBuffer == 0) {
                capacityOfBuffer = 100;
            }


            lists_created = true;

            // Initialize storages
            item1 = new ArrayList<>(capacityOfBuffer);
            item2 = new ArrayList<>(capacityOfBuffer);
            item3 = new ArrayList<>(capacityOfBuffer);

            // Bind objects for producer and consumer.

        try {

            // Object for producer
            ConsumerProducerInterface producerObject = new ConsumerProducerImplementation();
            Naming.rebind("//10.181.94.174/producerObject", producerObject);

            // Object for consumer
            ConsumerProducerInterface consumerObject = new ConsumerProducerImplementation();
            Naming.rebind("//10.181.94.174/consumerObject", consumerObject);

        }
        catch (Exception e) {
            System.out.println("RMI Exception :");
            e.printStackTrace();
        }

            // Start reading data when sent over TCP
            //Storage newStorage = new Storage("");
            //newStorage.updateStorage();
        //}
    }
}