/**
 * Tiger.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

/**
 *
 * This is a class Tiger for animals of the Tiger species. It has abstract class Animal as super class
 * and it implements the interface Carnivore since Tiger is a carnivore. Hence, it implements two methods,
 * getName from Animals and goHunt from Carnivore.
 *
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

public class Tiger extends Animal implements Carnivore{

    /**
     * Constructor for Tiger class. It can be instantiated to
     * create an object for any animal that is a tiger.
     * It calls super to pass name, species and home to the
     * super class Animals' constructor.
     *
     * @param   name             Name of tiger
     * @param   home             Home of the tiger
     */
    public Tiger(String name, String home) {
        super(name,"Tiger",home);
    }

    /**
     * Returns name of the tiger
     *
     * @param   none
     */
    public String getName() {
        return Name;
    }

    /**
     * This is to send the tiger for hunting and print
     * that with Tiger's name
     *
     * @param   none
     */
    public void goHunt() {
        System.out.println("I am "+ Name + " going to hunt");
    }
}