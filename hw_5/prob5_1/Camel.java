/**
 * Camel.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */
public class Camel extends Animal implements Herbivore {

    /**
     * Constructor for Animal class. Cannot create an object
     * since abstract. Hence, constructor is only called when
     * a subclass e.g. Camel uses super.
     *
     * @param name    Name of animal
     * @param home    Home of the animal
     */

    public Camel(String name, String home) {
        super(name, "Camel", home);
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public void goGraze() {
        System.out.println("I am "+ Name + " going to graze");
    }
}
