//package parallelProject.hw_4;

public class Gazelle extends Animal implements Herbivore {
    public Gazelle(String name,  String home) {
        super(name, "Gazelle", home);
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public void goGraze() {System.out.println("I am "+ Name + " going to graze");}
}
