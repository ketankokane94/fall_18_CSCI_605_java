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
                if (count == 1000) {
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
                        //System.out.println(info + " waiting");
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
        System.out.println(" Done " + info);
    }

    public static void main(String args[]) {
       // Grep.o = ""
        int num_consumers;
        int num_producers;
        int num_produced;
        int num_consumed;

        if (args.length < 5) {
            ConsumerProducer.storageSpace = 100;
            ConsumerProducer.storageSpaceLeft = 100;
            num_consumers = 2;
            num_producers = 2;
            num_consumed = 4;
            num_produced = 2;
        }
        else {
            ConsumerProducer.storageSpace = 100;
            ConsumerProducer.storageSpaceLeft = 100;
            num_consumers = Integer.parseInt(args[0]);
            num_producers = Integer.parseInt(args[1]);
            num_consumed = Integer.parseInt(args[2]);
            num_produced = Integer.parseInt(args[3]);
        }

        ConsumerProducer consumers[] = new ConsumerProducer[num_consumers];

        for (int consumerCount = 0; consumerCount < num_consumers; consumerCount++) {
            consumers[consumerCount] = new ConsumerProducer("C" + (consumerCount + 1), num_consumed);
        }

        ConsumerProducer producers[] = new ConsumerProducer[num_producers];

        for (int producerCount = 0; producerCount < num_producers; producerCount++) {
            producers[producerCount] = new ConsumerProducer("P" + (producerCount + 1), num_produced);
        }

        for (int consumerCount = 0; consumerCount < num_consumers; consumerCount++) {
            consumers[consumerCount].start();
        }

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