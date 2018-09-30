/**
 * Alpaca.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

/**
 *
 * This is a class Alpaca for animals of the Alpaca species. It has abstract class Animal as super class
 * and it implements the interface Herbivore since Alpaca is a herbivore. Hence, it implements two methods,
 * getName from Animals and goGraze from Herbivore.
 *
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

public class Alpaca extends Animal implements Herbivore {


    /**
     * Constructor for Alpaca class. It can be instantiated to
     * create an object for any animal that is a Alpaca.
     * It calls super to pass name, species and home to the
     * super class Animals' constructor.
     *
     * @param   name             Name of Alpaca
     * @param   home             Home of the Alpaca
     */
    public Alpaca(String name,String home) {
        super(name, "Alpaca", home);
    }

    /**
     * Returns name of the Alpaca
     *
     * @param   none
     */
    @Override
    public String getName() {
        return Name;
    }

    /**
     * This is to send the Alpaca to graze and print
     * that with Alpaca's name
     *
     * @param   none
     */
    @Override
    public void goGraze() {
        System.out.println("I am "+ Name + " going to graze");
    }
}
