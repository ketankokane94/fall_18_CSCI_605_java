/**
 * StorageLocal.java
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
import java.rmi.Naming;
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
 * Sychronized on static object to ensure that one producer completes before other starts
 */


public class StorageLocal extends Thread {
    ServerSocket serverSocket;

    static String o = "";

    static int port = 1065;

    // name of thread P<Integer> or C<Integer>
    final String threadName;

    // number of items produced or consumed
    final int itemsThreadCanProduceInOneGo;

    // array list to store the items which would accessed by producer and consumer
    static ArrayList<Integer> item1;
    static ArrayList<Integer> item2;
    static ArrayList<Integer> item3;
    static int capacityOfBuffer;

    // Number of times production or consumption happens
    static int count = 0;

    /**
     * Constructor
     * @param threadName String with C or P indicating Consumer or Producer
     * @param itemsThreadCanProduceInOneGo number of items to produce or consume at a time
     */

    public StorageLocal(String threadName, int itemsThreadCanProduceInOneGo) {
        this.threadName = threadName;
        this.itemsThreadCanProduceInOneGo = itemsThreadCanProduceInOneGo;
    }

    /**
     * Set buffer capacity and send request to server storage to update capacity
     * @param bufferCapacity capacity of buffer
     */
    public static void setBufferCapacity(int bufferCapacity) {
        try {
            ConsumerProducerInterface consumerProducerObject = (ConsumerProducerInterface) Naming.lookup("//10.181.94.174/capacityObject");

            capacityOfBuffer = consumerProducerObject.updateCapacity(bufferCapacity);

            System.out.println(capacityOfBuffer);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Run method for threads
     */
    public void run() {
        // Synchronization for producers
        synchronized (o) {
            // Produce 11 times
            while (count <= 100) {
                if (threadName.contains("P")) {
                    produce();
                    o.notifyAll();
                    try {
                        o.wait();
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Helper function to produce the required item when the producer is running.
     */
    public void produce() {
        String toUpdateServer = threadName + " ";
        int itemToProduce =  getItemToProduce();
        // Produce
        switch (itemToProduce) {
            case 1:
                if (item1.size() + itemsThreadCanProduceInOneGo <= capacityOfBuffer) {
                    toUpdateServer += "item1 ";
                    toUpdateServer += itemsThreadCanProduceInOneGo;
                }
                break;

            case 2:
                if (item2.size() + itemsThreadCanProduceInOneGo <= capacityOfBuffer){
                    toUpdateServer += "item2 ";
                    toUpdateServer += itemsThreadCanProduceInOneGo;
                }
                break;

            case 3:
                if (item3.size() + itemsThreadCanProduceInOneGo <= capacityOfBuffer){
                    toUpdateServer += "item3 ";
                    toUpdateServer += itemsThreadCanProduceInOneGo;
                }
                break;

        }

        /* Send request to server to update storage */
        try {

            ConsumerProducerInterface produceObject = (ConsumerProducerInterface) Naming.lookup("//10.181.94.174/producerObject");
            String success = produceObject.consumeProduce(toUpdateServer);

            // Add only if server updated successfully.
            if (success.equalsIgnoreCase("Success")) {
                switch (itemToProduce) {
                    case 1:
                        if (item1.size() + itemsThreadCanProduceInOneGo <= capacityOfBuffer) {
                            for (int i = 0; i < itemsThreadCanProduceInOneGo; i++) {
                                item1.add(0, 1);
                            }
                        }
                        break;

                    case 2:
                        if (item2.size() + itemsThreadCanProduceInOneGo <= capacityOfBuffer) {
                            for (int i = 0; i < itemsThreadCanProduceInOneGo; i++) {
                                item2.add(0, 1);
                            }
                        }
                        break;

                    case 3:
                        if (item3.size() + itemsThreadCanProduceInOneGo <= capacityOfBuffer) {
                            for (int i = 0; i < itemsThreadCanProduceInOneGo; i++) {
                                item3.add(0, 1);
                            }
                        }
                        break;

                }
            }
            else {
                System.out.println("Server did not update hence production failed");
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        count++;
        System.out.println(count + ". " + threadName + " produced item "+ itemToProduce );
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

}