/**
 * Skunk.java
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

public class Skunk extends  Animal implements Omnivore {

    public Skunk(String name,  String home) {
        super(name, "Skunk", home);
    }

    /**
     * Returns name of the skunk
     *
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
