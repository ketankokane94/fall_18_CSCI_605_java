//package parallelProject.hw_4;

public abstract class Animal {

    String Name;
    String Species;
    String Home;
    boolean AreYouHome = false;

    public Animal(String name, String species,String home) {
        Name = name;
        Species = species;
        Home = home;
        //TODO: change this new Random().nextInt)(1)
        int homeStatus = ((int) ((Math.random()) * 100) % 2);
        if(homeStatus == 1) {
            AreYouHome = true;
        }
        
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
    public String AreYouHungry()
    {
        int hungerStatus = ((int) ((Math.random()) * 100) % 2);
        if(hungerStatus == 0)
            return "No";
        else
            return "Yes";
    }

    public String AreYouHome()
    {
        if(!AreYouHome)
            return "No";
        else
            return "Yes";
    }

}
