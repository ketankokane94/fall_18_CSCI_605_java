/**
 * PolarBear.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

/**
 *
 * This is a class PolarBear for animals of the Polar Bear species. It has abstract class Animal as super class
 * and it implements the interface Carnivore since Polar Bear is a carnivore. Hence, it implements two methods,
 * getName from Animals and goHunt from Carnivore.
 *
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

public class PolarBear extends Animal implements Carnivore {

    /**
     * Constructor for PolarBear class. It can be instantiated to
     * create an object for any animal that is a tiger.
     * It calls super to pass name, species and home to the
     * super class Animals' constructor.
     *
     * @param   name             Name of polar bear
     * @param   home             Home of the polar bear
     */
    public PolarBear(String name,  String home) {
        super(name, "Bear", home);
    }

    /**
     * Returns name of the Polar Bear
     *
     * @param   none
     */
    public String getName() {
        return Name;
    }

    /**
     * This is to send the Polar Bear for hunting and print
     * that with Bear's name
     *
     * @param   none
     */
    public void goHunt() {
        System.out.println("I am "+ Name + " going to hunt");
    }

}
