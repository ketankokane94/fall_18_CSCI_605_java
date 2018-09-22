package parallelProject.hw_4;

public abstract class Animal {

    String Name;
    String Species;
    String Home;
    boolean AreYouHome = false;

    public Animal(String name, String species,String home) {
        Name = name;
        Species = species;
        Home = home;
    }

    public String getSpecies() {
        return Species;
    }

    public abstract String getName();

    public String goHome(){
        AreYouHome = true;
        return "I am in "+ Home;
    }

    public String whoAreYou(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("I am a ");
        stringBuilder.append(getSpecies());
        stringBuilder.append(" my name is ");
        stringBuilder.append(getName());
        return stringBuilder.toString();
    }
    //TODO add go Home
    public abstract String AreYouHungry();

}
