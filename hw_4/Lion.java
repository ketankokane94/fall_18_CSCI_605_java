

public class Lion extends Animal implements Carnivore{

    public Lion(String name, int age) {
        super(name,age);
    }

    @Override
    public String getSpecies() {
        return "Lion";
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public int getAge() {
        return Age;
    }

    @Override
    public void goHunt() {

    }
}
