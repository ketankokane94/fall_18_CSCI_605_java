import java.util.Scanner;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ConsumerProducer extends Thread {
    String info;
    static String o = "";
    int howMany;
    static int storageSpaceLeft;
    static int storageSpace;
    static boolean someConsumerWaiting;
    static boolean someProducerWaiting;
    static int count;

    public ConsumerProducer(String info, int howMany) {
        this.info = info;
        this.howMany = howMany;
        someConsumerWaiting = false;
        someProducerWaiting = false;
        count = 0;
    }

    public void produce() {
        // Produce
        storageSpaceLeft -= howMany;

        count++;

        System.out.println(count + ". " + info + " produced " + howMany);
    }

    public void consume() {
        // Consume
        storageSpaceLeft += howMany;

        count++;

        System.out.println(count + ". " + info + " consumed " + howMany);
    }

    public void run()
    {
        //random();

        while (true) {
            synchronized (o) {
                if (count == 10) {
                    o.notifyAll();
                    break;
                }
                if (info.contains("C")) {
                    if (storageSpace - storageSpaceLeft >= howMany) {

                        consume();

                        //System.out.println("Producers waiting : " + someProducerWaiting);

                    }

                    // Wake up producer
                    if (someProducerWaiting) {
                        o.notifyAll();
                        someProducerWaiting = false;
                    }

                    try {
                        someConsumerWaiting = true;

                        // Wait
                        System.out.println(info + " waiting");
                        o.wait();
                    }
                    catch (InterruptedException ie) {
                        someConsumerWaiting = false;
                        ie.printStackTrace();
                    }

                }
                else if (info.contains("P")) {
                    if (storageSpaceLeft >= howMany) {

                        produce();

                        //System.out.println("Consumers waiting : " + someConsumerWaiting);

                    }

                    // Wake up consumer
                    if (someConsumerWaiting) {
                        o.notifyAll();
                        someConsumerWaiting = false;
                    }

                    try {
                            // Wait
                            someProducerWaiting = true;
                            System.out.println(info + " waiting");
                            o.wait();
                        } catch (InterruptedException ie) {
                            someProducerWaiting = false;
                            ie.printStackTrace();
                        }
                }
                //System.out.println(info + " Storage Space Left : " + storageSpaceLeft);
            }
        }
        System.out.println(" Done " + info);
    }

    public static void main(String args[]) {
       // Grep.o = ""
        ConsumerProducer.storageSpace = 100;
        ConsumerProducer.storageSpaceLeft = 100;
        ConsumerProducer obj2 = new ConsumerProducer("C1" , 4);
        ConsumerProducer obj4 = new ConsumerProducer("C2" , 4);
        ConsumerProducer obj1 = new ConsumerProducer("P1", 2);
        ConsumerProducer obj3 = new ConsumerProducer("P2", 2);

        obj2.start();
        obj1.start();
        obj3.start();
        obj4.start();
    }
}