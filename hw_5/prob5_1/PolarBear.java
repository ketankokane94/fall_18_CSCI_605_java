/**
 * PolarBear.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

import java.util.Random;

/**
 *
 * This is a class Bear for animals of the Bear species. It has abstract class Animal as super class
 * and it implements the interfaces Carnivore and Herbivore since Bear is an omnivore. Hence, it
 * implements three methods, getName from Animals, goHunt from Carnivore and goGraze from Herbivore.
 *
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

public class PolarBear extends Animal implements Omnivore {

    /**
     * Constructor for Bear class. It can be instantiated to
     * create an object for any animal that is a Bear.
     * It calls super to pass name, species and home to the
     * super class Animals' constructor.
     *
     * @param   name             Name of Bear
     * @param   home             Home of the Bear
     */
    public PolarBear(String name,  String home) {
        super(name, "Bear", home);
    }

    /**
     * Returns name of the Bear
     *
     * @param   none
     */
    public String getName() {
        return Name;
    }


    /**
     *
     */
    @Override
    public void goEat() {

        int mood = new Random().nextInt(2);
        if (mood == 0) {
            goHunt();
        } else {
            goGraze();
        }
    }

    /**
     *
     */
    @Override
    public void goHunt() {
        System.out.println("I am "+ Name + " going to hunt");
    }

    /**
     *
     */
    @Override
    public void goGraze() {
        System.out.println("I am "+ Name + " going to graze");
    }
}
