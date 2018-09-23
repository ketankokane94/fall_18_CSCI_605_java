//package parallelProject.hw_4;

public class Giraffe extends Animal implements Herbivore {

    public Giraffe(String name,  String home) {
        super(name, "Giraffe", home);
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
