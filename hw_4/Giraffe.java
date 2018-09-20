public class Giraffe extends Animal implements Herbivore{


    public Giraffe(String name, int age) {
        super(name,age);
    }

    @Override
    public String getSpecies() {
        return "Giraffe";
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
    public void goGraze() {

    }
}
