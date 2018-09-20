public class Tiger extends Animal implements Carnivore{

    public Tiger(String name, int age) {
        super(name,age);
    }

    @Override
    public String getSpecies() {
        return "Tiger";
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
