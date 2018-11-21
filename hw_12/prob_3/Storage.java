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

    String line = "";

    static int port = 1065;

    static ServerSocket serverSocket;

    String threadName = "";

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
     * @param line : the data read over TCP/IP
     */

    public Storage(String line) {
        this.line = line;
    }

    /**
     * Helper function Consume the items produced by the producer
     */
    public void consume() {
        System.out.println(count + ". " + threadName + " consumed " );
        for (int i = 0; i < 2 && item1.size() > 0; i++) {
            item1.remove(0);
        }
        
        for (int i = 0; i < 5 && item2.size() > 0 ; i++) {
            item2.remove(0);
        }

        for (int i = 0; i < 3  && item3.size() > 0 ; i++) {
            item3.remove(0);
        }

        count++;

    }

    /**
     * Run method to delegate task to every thread. Updates storage based on TCP/IP data recieved from
     * producer or consumer
     */
    public void run() {

        /* Logic to update item lists here */

        if(line.charAt(0) == 'C' || line.charAt(0) == 'P') {

            // Get data into an array
            String lineArray [] = line.split(" ");

            // First value is the thread name
            threadName = lineArray[0];

            // For consumer simply call consume to consume and update storage
            if(threadName.contains("C")) {
                consume();
            }
            // For producer update storage based on string sent by producer
            else {
                for(int arrayIndex = 1; arrayIndex < lineArray.length; arrayIndex++) {

                    // For item1
                    if (lineArray[arrayIndex].equals("item1")) {
                        int num_items = Integer.parseInt(lineArray[arrayIndex + 1]);
                        for (int index = 0; index < num_items; index++) {
                            item1.add(0, 1);
                        }
                        System.out.println("item1 added " + num_items + " times");
                    }

                    // For item2
                    if (lineArray[arrayIndex].equals("item2")) {
                        int num_items = Integer.parseInt(lineArray[arrayIndex + 1]);
                        for (int index = 0; index < num_items; index++) {
                            item2.add(0, 1);
                        }
                        System.out.println("item2 added " + num_items + " times");
                    }

                    // For item3
                    if (lineArray[arrayIndex].equals("item3")) {
                        int num_items = Integer.parseInt(lineArray[arrayIndex + 1]);
                        for (int index = 0; index < num_items; index++) {
                            item3.add(0, 1);
                        }
                        System.out.println("item3 added " + num_items + " times");
                    }

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

                /* Get the capacity from producer at the start over TCP/IP */

                // Set sockets and readers
                serverSocket = new ServerSocket(port);
                Socket socket = serverSocket.accept();

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

                // Read data when sent
                String line;
                line = reader.readLine();

                // Close reader
                reader.close();

                if(line.contains("Capacity")) {
                    capacityOfBuffer = Integer.parseInt(line.split(" ")[1]);
                    System.out.println("Capacity = " + capacityOfBuffer);
                    //String write_line = "success";
                    //printWriter.write(write_line);
                }
                else {
                    capacityOfBuffer = 100;
                    System.out.println("Taking capacity as default 100");
                }

                // Close streams and sockets
                printWriter.close();
                serverSocket.close();
                socket.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            // Initialize storages
            item1 = new ArrayList<>(capacityOfBuffer);
            item2 = new ArrayList<>(capacityOfBuffer);
            item3 = new ArrayList<>(capacityOfBuffer);

            // Start reading data when sent over TCP
            Storage newStorage = new Storage("");
            newStorage.updateStorage();
        //}
    }
}