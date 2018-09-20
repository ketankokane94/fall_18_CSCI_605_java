public abstract class Animal {

    String Name;
    String Species;

    public Animal(String name, String species) {
        Name = name;
        Species = species;
    }

    public abstract String getSpecies();

    public abstract String getName();

    public abstract int getAge();

    public String whoAreYou(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("I am a ");
        stringBuilder.append(getSpecies());
        stringBuilder.append(" my name is ");
        stringBuilder.append(getName());
        return stringBuilder.toString();
    }

}
