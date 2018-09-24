/**
 * Lion.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

/**
 *
 * This is a class Lion for animals of the Lion species. It has abstract class Animal as super class
 * and it implements the interface Carnivore since Lion is a carnivore. Hence, it implements two methods,
 * getName from Animals and goHunt from Carnivore.
 *
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

public class Lion extends Animal implements Carnivore{

    /**
     * Constructor for Lion class. It can be instantiated to
     * create an object for any animal that is a Lion.
     * It calls super to pass name, species and home to the
     * super class Animals' constructor.
     *
     * @param   name             Name of Lion
     * @param   home             Home of the Lion
     */
    public Lion(String name,  String home) {
        super(name,"Lion",home);
    }

    /**
     * Returns name of the Lion
     *
     * @param   none
     */
    public String getName() {
        return Name;
    }

    /**
     * This is to send the tiger for hunting and print
     * that with Lion's name
     *
     * @param   none
     */
    public void goHunt() {
        System.out.println("I am "+ Name + " going to hunt");
    }
}
