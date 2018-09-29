public class Alpaca extends Animal implements Herbivore {


    /**
     * Constructor for Animal class. Cannot create an object
     * since abstract. Hence, constructor is only called when
     * a subclass e.g. Alpaca uses super.
     *
     * @param name    Name of animal
     * @param home    Home of the animal
     */
    public Alpaca(String name,String home) {
        super(name, "Alpaca", home);
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
