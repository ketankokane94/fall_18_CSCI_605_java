/**
 * Carnivore.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

/**
 *
 * This is an interface Carnivore for all species that are Carnivore. Every species that is a Carnivore eg. Tiger
 * will implement this interface and compulsorily has to implement all it's methods.
 * This is an interface because a class cannot extend more than one class but can implement more than one interface
 * like in the case of an Omnivore eg. Bear.
 *
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

public interface Carnivore {

    /**
     * Method goHunt
     *
     * @param   none
     */
    public void goHunt();
}
