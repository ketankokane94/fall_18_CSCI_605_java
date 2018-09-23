//package parallelProject.hw_4;

public class Bear extends Animal implements Herbivore, Carnivore {

    public Bear(String name,  String home) {
        super(name, "Bear", home);
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

}
