public class Aardvark extends Animal implements Omnivore{
    /**
     * Constructor for Animal class. Cannot create an object
     * since abstract. Hence, constructor is only called when
     * a subclass e.g. Tiger uses super.
     *
     * @param name    Name of animal
     * @param home    Home of the animal
     */
    public Aardvark(String name,  String home) {
        super(name, "Aardvark", home);
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public void goGraze() {
        System.out.println("I am "+ Name + " going to graze");
    }

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
