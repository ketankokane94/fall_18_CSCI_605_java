/**
 * Omnivore.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

/**
 *
 * This is an interface Omnivore for all species that are Herbivore and carnivore.
 * like in the case of an Omnivore eg. Bear.
 *
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

public interface Omnivore extends Herbivore, Carnivore {

    /**
     *  go Eat method should be implemented by animals based on their eating preference
     */
    void goEat();
}
