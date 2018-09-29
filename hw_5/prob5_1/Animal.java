/**
 * Animal.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

import java.util.Random;

/**
 *
 * This is an abstract class Animal that has the common information and functions of all species.
 * It will enforce all species class that extends it to implement the common abstract methods
 * i.e. all the common features for all species.
 *
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

public abstract class Animal {

    // Stores name of the animal.
    String Name;
    // Stores species of the animal i.e. Tiger or Giraffe etc.
    String Species;
    // Stores home of that animal
    String Home;
    // Stores status of whether at home or not for the animal
    boolean AreYouHome = false;
    // stores the Id of the animal
    int Id;

    /**
     * Constructor for Animal class. Cannot create an object
     * since abstract. Hence, constructor is only called when
     * a subclass e.g. Tiger uses super.
     *
     * @param   name             Name of animal
     * @param   species          Species of the animal
     * @param   home             Home of the animal
     */

    public Animal(String name, String species,String home) {
        Id = generateId();
        Name = name;
        Species = species;
        Home = home;
        // Random value between 0 and 1 for home status of animal
        int homeStatus = new Random().nextInt(2);
        if(homeStatus == 1) {
            AreYouHome = true;
        }
        
    }

    /**
     * Returns the species of the current animal
     *
     * @param   none
     */
    public String getSpecies() {
        return Species;
    }

    /**
     * Abstract method getName
     *
     * @param   none
     */
    public abstract String getName();

    /**
     * Changes home status of the animal and
     * returns the status as string to be printed
     *
     * @param   none
     */
    public String goHome(){
        AreYouHome = true;
        return "I am in "+ Home;
    }

    /**
     * Returns a string with species and name of the animal
     *
     * @param   none
     */
    public String whoAreYou(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("I am a ");
        stringBuilder.append(getSpecies());
        stringBuilder.append(" my name is ");
        stringBuilder.append(getName());
        stringBuilder.append(" and my Id : ");
        stringBuilder.append(Id);
        return stringBuilder.toString();
    }

    /**
     * Returns yes or no for whether animal is hungry randomly
     *
     * @param   none
     */
    public String AreYouHungry()
    {
        // Random value
        int hungerStatus = new Random().nextInt() % 2;
        if(hungerStatus == 0)
            return "No";
        else
            return "Yes";
    }

    /**
     * Returns yes or no for whether animal is at home based on
     * animal's home status
     *
     * @param   none
     */
    public String AreYouHome()
    {
        if(!AreYouHome)
            return "No";
        else
            return "Yes";
    }

    private int generateId(){
        return new Object().hashCode();
    }

}
