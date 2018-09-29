public class Camel extends Animal implements Herbivore {

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
