/**
 * Aardvark.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

/**
 *
 * This is a class Aardvark for animals of the Bear species. It has abstract class Animal as super class
 * and it implements the interfaces Carnivore and Herbivore since Aardvark is an omnivore. Hence, it
 * implements three methods, getName from Animals, goHunt from Carnivore and goGraze from Herbivore.
 *
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

public class Aardvark extends Animal implements Omnivore{
    /**
     * Constructor for Aardvark class. It can be instantiated to
     * create an object for any animal that is a Aardvark.
     * It calls super to pass name, species and home to the
     * super class Animals' constructor.
     *
     * @param   name             Name of Aardvark
     * @param   home             Home of the Aardvark
     */
    public Aardvark(String name,  String home) {
        super(name, "Aardvark", home);
    }

    /**
     * Returns name of the Aardvark
     *
     * @param   none
     */
    @Override
    public String getName() {
        return Name;
    }

    /**
     * This is to send the Aardvark to graze and print
     * that with Aardvark's name
     *
     * @param   none
     */
    @Override
    public void goGraze() {
        System.out.println("I am "+ Name + " going to graze");
    }

    /**
     * This is to send the Aardvark for hunting and print
     * that with Aardvark's name
     *
     * @param   none
     */
    @Override
    public void goHunt() {
        System.out.println("I am "+ Name + " going to hunt");
    }

    @Override
    public void goEat() {
        if (AreYouHome){
            goGraze();
        }
        else {
            goHunt();
        }
    }
}
