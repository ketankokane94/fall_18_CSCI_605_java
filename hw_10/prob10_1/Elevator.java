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
import java.util.concurrent.*;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Elevator extends Thread {
    String info;

    // Static variable on which we synchronize.
    static String o = "";

    static int storageSpace;

    static int top_floor;

    int person_floor;

    int destination_floor;

    static int somePersonWaiting;

    static boolean elevatorWaiting;

    static int num_persons[];

    static int elevator_floor;

    boolean inElevator;

    // Number of times production or consumption happens
    static int count;

    /**
     * Constructor
     * @param info String with C or P indicating Consumer or Producer
     */
    public Elevator(String info, int floor, int destination_floor) {
        this.info = info;
        elevatorWaiting = false;
        somePersonWaiting = 0;
        if (info.contains("E")) {
            // Floor for start of elevator
            elevator_floor = floor;
        }
        else if (info.contains("P")) {
            // Floor on which person is or wants to go
            person_floor = floor;
            inElevator = false;

            // Increment count for that floor
            num_persons[floor]++;
        }
        this.destination_floor = destination_floor;
        count = 0;
    }

    /**
     * Helper function to update storage and other info on production of howMany items
     */
    public void exitElevator() {
        // Exit elevator
        count--;

        // Swap destination and current floor for loop to go on with new results
        int temp = destination_floor;
        destination_floor = person_floor;
        person_floor = temp;

        inElevator = false;

        num_persons[destination_floor]++;

        System.out.println(info + " exited elevator ");
    }

    /**
     * Helper function to update storage and other info on consumption of howMany items
     */
    public void enterElevator() {
        // Enter Elevator
        count++;

        inElevator = true;

        num_persons[person_floor]--;

        System.out.println(info + " entered elevator ");
    }

    /**
     * Run method for the threads
     */
    public void run()
    {
        int ctr = 0;
        // Loop to make process happen in tandem endlessly
        while (ctr < 10) {
            synchronized (o) {
                if (info.contains("P")) {
                    // if elevator is on the floor that person is on
                    // if there is space in the elevator
                    if (elevator_floor == person_floor && !inElevator && count < storageSpace) {

                        enterElevator();

                    }
                    else if (elevator_floor == destination_floor && inElevator) {
                        exitElevator();
                    }

                    //if (elevatorWaiting) {
                    //o.notifyAll();
                    //System.out.println("Notified all");
                    //}
                    try {
                        //somePersonWaiting++;
                        o.wait();
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                        //somePersonWaiting--;
                    }
                }
                else if (info.contains("E")) {
                    if (elevator_floor == top_floor) {
                        elevator_floor--;
                    }
                    else {
                        elevator_floor++;
                    }
                    System.out.println("Elevator is on floor " + elevator_floor);

                    o.notifyAll();

                    try {
                        elevatorWaiting = true;
                        System.out.println("Elevator waiting for 1 second");
                        o.wait(1000);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                    elevatorWaiting = false;
                }
                ctr++;
            }
        }
    }

    /**
     * Main function.
     */
    public static void main(String args[]) {
        int num_threads = 5;

        Elevator.storageSpace = 3;
        Elevator.top_floor = 3;
        Elevator.num_persons =  new int[5];
        ExecutorService executorService = Executors.newFixedThreadPool(num_threads);
        ThreadFactory threadFactory = ((ThreadPoolExecutor) executorService).getThreadFactory();

        for (int i  = 0; i < num_threads; i++) {
            String information;
            int floor;
            int destination_floor;
            if (i == 0) {
                information = "E";
                floor = 0;
                destination_floor = 0;
            }
            else {
                information = "P" + i;
                floor = ((int)(Math.random() * 10000)) % 4;
                if (floor != 0) {
                    destination_floor = 0;
                }
                else {
                    destination_floor = 3;
                }
            }
            executorService.execute(threadFactory.newThread(new Elevator(information, floor, destination_floor)));
            System.out.println("Created " + information + " with floor " + floor);
        }
        executorService.shutdown();

    }
}