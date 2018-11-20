/**
 * ConsumerProducer.java
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
 * producers can produce 3 different items, consumer can consume only when certain number of items are present,
 * in this instance 2 of item1 5 of item2 3 of item3. In one go.
 * Used one single semaphore to maintain the working of the producer and consumer, resulting that no two threads can work simultaneously
 */


public class Storage extends Thread {

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
     * @param threadName String with C or P indicating Consumer or Producer
     * @param itemsThreadCanProduceInOneGo number of items to produce or consume at a time
     */

    public Storage() {
        //this.threadName = threadName;
        //this.itemsThreadCanProduceInOneGo = itemsThreadCanProduceInOneGo;
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

    public void updateStorage()
    {

        while (true) {
            try {
                serverSocket = new ServerSocket(port);
                Socket socket = serverSocket.accept();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = bufferedReader.readLine();
                System.out.println("Reading : " + line);
                bufferedReader.close();

                /* Logic to update item lists here */

                if(line.charAt(0) == 'C' || line.charAt(0) == 'P') {
                    String lineArray [] = line.split(" ");
                    threadName = lineArray[0];

                    if(threadName.contains("C")) {
                        consume();
                    }
                    else {
                        for(int arrayIndex = 1; arrayIndex < lineArray.length; arrayIndex++) {
                            if (lineArray[arrayIndex].equals("item1")) {
                                int num_items = Integer.parseInt(lineArray[arrayIndex + 1]);
                                for (int index = 0; index < num_items; index++) {
                                    item1.add(0, 1);
                                }
                                System.out.println("item1 added " + num_items + " times");
                            }
                            if (lineArray[arrayIndex].equals("item2")) {
                                int num_items = Integer.parseInt(lineArray[arrayIndex + 1]);
                                for (int index = 0; index < num_items; index++) {
                                    item2.add(0, 1);
                                }
                                System.out.println("item2 added " + num_items + " times");
                            }
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
                serverSocket = new ServerSocket(port);
                Socket socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                //Scanner sc = new Scanner(System.in);
                String line;
                //System.out.print("Write : ");
                //String write_line = "abc";//sc.nextLine();
                //printWriter.write(write_line);
                line = reader.readLine();
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
                printWriter.close();
                serverSocket.close();
                socket.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            item1 = new ArrayList<>(capacityOfBuffer);
            item2 = new ArrayList<>(capacityOfBuffer);
            item3 = new ArrayList<>(capacityOfBuffer);

            Storage newStorage = new Storage();
            newStorage.updateStorage();
        //}
    }
}