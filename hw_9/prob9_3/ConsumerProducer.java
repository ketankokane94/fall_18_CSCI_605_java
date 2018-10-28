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

    public static void areArgumentsNumbers(String argument0, String argument1, String argument2, String argument3, String argument4) throws NoNumberArgumentException {
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
    public static void areArgumentsNegative(int argument0, int argument1, int argument2, int argument3, int argument4) throws NegativeNumberArgumentException {
        if (argument0 < 0 || argument1 < 0 || argument2 < 0 || argument3 < 0 || argument4 < 0) {
            throw new NegativeNumberArgumentException("Argument is negative");
        }
    }

    public static void main(String args[]) {
       // Grep.o = ""
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

                // Throw exceptions
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