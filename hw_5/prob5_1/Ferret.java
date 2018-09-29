public class Ferret extends Animal implements Carnivore {

    public Ferret(String name, String home) {
        super(name, "Ferret", home);
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public void goHunt() {
        System.out.println("I am "+ Name + " going to hunt");
    }
}
