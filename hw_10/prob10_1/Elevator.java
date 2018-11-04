/**
 * Elevator.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

/**
 * Program to use multithreading to create a simulation for an elevator.
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

import java.util.Scanner;
import java.io.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Elevator extends Thread {
    String info;

    // Static variable on which we synchronize.
    static String o = "";

    static int storageSpace;

    int person_floor;

    static boolean somePersonWaiting;

    static boolean elevatorWaiting;

    static int elevator_floor;

    boolean inElevator;

    // Number of times production or consumption happens
    static int count;

    /**
     * Constructor
     * @param info String with C or P indicating Consumer or Producer
     * @param howMany number of items to produce or consume at a time
     */
    public Elevator(String info, int floor) {
        this.info = info;
        elevatorWaiting = false;
        somePersonWaiting = false;
        if (info.contains("E")) {
            // Floor for start of elevator
            elevator_floor = floor;
        }
        else if (info.contains("P")) {
            // Floor on which person is or wants to go
            person_floor = floor;
            inElevator = false;
        }
        count = 0;
    }

    /**
     * Helper function to update storage and other info on production of howMany items
     */
    public void exitElevator() {
        // Exit elevator
        count--;

        System.out.println(info + " exited elevator ");
    }

    /**
     * Helper function to update storage and other info on consumption of howMany items
     */
    public void enterElevator() {
        // Enter Elevator
        count++;

        System.out.println(info + " entered elevator ");
    }

    /**
     * Run method for the threads
     */
    public void run()
    {

        // Loop to make process happen in tandem endlessly
        while (true) {
            synchronized (o) {
                if (info.contains("P")) {
                    // if elevator is on the floor that person is on
                    if (elevator_floor == person_floor) {
                        // if there is space in the elevator
                        if (!inElevator && count < storageSpace) {
                            enterElevator();
                        }
                        else if (inElevator) {
                            exitElevator();
                        }
                    }

                    if (elevatorWaiting) {
                        o.notifyAll();
                    }
                    try {
                        o.wait();
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
                else if (info.contains("E")) {
                    elevator_floor++;
                    o.notifyAll();
                }
            }
        }
    }

    /**
     * Main function.
     */
    public static void main(String args[]) {
        Elevator.storageSpace = 3;
        ExecutorService executorService = new ThreadPoolExecutor(5, 10, 2000, null, null);
        ThreadFactory threadFactory = ((ThreadPoolExecutor) executorService).getThreadFactory();

        for (int i  = 0; i < 10; i++) {
            String information;
            int floor;
            if (i == 0) {
                information = "E";
                floor = 0;
            }
            else {
                information = "P" + i;
                floor = ((int)(Math.random() * 10000)) % 3;
            }
            executorService.execute(threadFactory.newThread(new Elevator(information, floor)));
        }


    }
}