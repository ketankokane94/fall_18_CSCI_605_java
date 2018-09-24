/**
 * Gazelle.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

/**
 *
 * This is a class Gazelle for animals of the Gazelle species. It has abstract class Animal as super class
 * and it implements the interface Herbivore since Gazelle is a herbivore. Hence, it implements two methods,
 * getName from Animals and goGraze from Herbivore.
 *
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

public class Gazelle extends Animal implements Herbivore {

    /**
     * Constructor for Giraffe class. It can be instantiated to
     * create an object for any animal that is a Giraffe.
     * It calls super to pass name, species and home to the
     * super class Animals' constructor.
     *
     * @param   name             Name of Gazelle
     * @param   home             Home of the Gazelle
     */
    public Gazelle(String name,  String home) {
        super(name, "Gazelle", home);
    }

    /**
     * Returns name of the Gazelle
     *
     * @param   none
     */
    public String getName() {
        return Name;
    }

    /**
     * This is to send the Gazelle to graze and print
     * that with Gazelle's name
     *
     * @param   none
     */
    public void goGraze() {
        System.out.println("I am "+ Name + " going to graze");
    }
}
