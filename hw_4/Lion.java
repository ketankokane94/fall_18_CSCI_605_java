package parallelProject.hw_4;

public class Lion extends Animal implements Carnivore{

    public Lion(String name,  String home) {
        super(name,"Lion",home);
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
