/**
 * Herbivore.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

/**
 *
 * This is an interface Herbivore for all species that are Herbivore. Every species that is a Herbivore eg. Giraffe
 * will implement this interface and compulsorily has to implement all it's methods.
 * This is an interface because a class cannot extend more than one class but can implement more than one interface
 * like in the case of an Omnivore eg. Bear.
 *
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

public interface Herbivore {

    /**
     * Method goGraze
     *
     * @param   none
     */
    public void goGraze();
}
