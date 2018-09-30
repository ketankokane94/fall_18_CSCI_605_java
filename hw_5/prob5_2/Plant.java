/**
 * Plant.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

import java.util.Random;

/**
 *
 * This is an abstract class Plant that has the common information and functions of all species.
 * It will enforce all species class that extends it to implement the common abstract methods
 * i.e. all the common features for all species.
 *
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

public abstract class Plant extends LivingBeing{

    // Stores species of the plant i.e. Palm Tree
    String Species;
    // Stores type of plant i.e. tree or flower pot
    String type;

    /**
     * Constructor for Plant class. Cannot create an object
     * since abstract. Hence, constructor is only called when
     * a subclass e.g. Palm Tree uses super.
     *
     * @param   species          Species of the plant
     */

    public Plant(String species, String type) {
        super(species);
        Species = species;
        this.type = type;
    }

    /**
     * Abstract method getName
     *
     * @param
     */
    public abstract String getType();

    /**
     * Returns a string with species and name of the plant
     *
     */
    public String whoAreYou(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("I am a ");
        stringBuilder.append(getSpecies());
        stringBuilder.append(" and my Id : ");
        stringBuilder.append(Id);
        return stringBuilder.toString();
    }

    /**
     * Moves plant to new position if not a tree
     *
     * @param   x   x of position to move to
     * @param   y   y of position to move to
     */
    public void move(int x, int y)
    {
        // Tree cannot be moved
        if(type.equalsIgnoreCase("tree"))
        {
            System.out.println("I am a tree so cannot be moved");
        }
        else {
            // Set x and y of Object to new positions, x and y, passed as params
            this.x = x;
            this.y = y;

            System.out.println("I am at new position : (" + this.x + "," + this.y + ")");

        }
    }

}
