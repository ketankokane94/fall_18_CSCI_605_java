/**
 * Giraffe.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

/**
 *
 * This is a class Giraffe for animals of the Giraffe species. It has abstract class Animal as super class
 * and it implements the interface Herbivore since Giraffe is a herbivore. Hence, it implements two methods,
 * getName from Animals and goGraze from Herbivore.
 *
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

public class Giraffe extends Animal implements Herbivore {

    /**
     * Constructor for Giraffe class. It can be instantiated to
     * create an object for any animal that is a Giraffe.
     * It calls super to pass name, species and home to the
     * super class Animals' constructor.
     *
     * @param   name             Name of Giraffe
     * @param   home             Home of the Giraffe
     */
    public Giraffe(String name,  String home) {
        super(name, "Giraffe", home);
    }

    /**
     * Returns name of the Giraffe
     *
     * @param   none
     */
    public String getName() {
        return Name;
    }

    /**
     * This is to send the giraffe to graze and print
     * that with Giraffe's name
     *
     * @param   none
     */
    public void goGraze() {
        System.out.println("I am "+ Name + " going to graze");
    }
}
