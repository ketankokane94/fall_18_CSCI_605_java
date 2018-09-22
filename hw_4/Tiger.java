package parallelProject.hw_4;

public class Tiger extends Animal implements Carnivore{

    public Tiger(String name, String home) {
        super(name,"Tiger",home);
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