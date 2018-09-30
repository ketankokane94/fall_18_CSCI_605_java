/**
 * LivingBeing.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

import java.util.Random;

/**
 *
 * This is an abstract class LivingBeing that has the common information and functions of all species.
 * It will enforce all species class that extends it to implement the common abstract methods
 * i.e. all the common features for all species.
 *
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

public abstract class LivingBeing {

    // Stores species of the LivingBeing i.e. Tiger or Giraffe etc.
    String Species;

    // stores the Id of the LivingBeing
    int Id;
    // Stores x position of LivingBeing
    int x;
    // Stores y pos of LivingBeing
    int y;

    /**
     * Constructor for LivingBeing class. Cannot create an object
     * since abstract. Hence, constructor is only called when
     * a subclass e.g. Tiger uses super.
     *
     * @param   species          Species of the LivingBeing
     */

    public LivingBeing( String species) {
        Id = generateId();
        Species = species;

        // Random value between 0 and 100 for x position of LivingBeing
        x = new Random().nextInt(101);
        // Random value between 0 and 100 for y position of LivingBeing
        y = new Random().nextInt(101);

        
    }

    /**
     * Returns the species of the current LivingBeing
     * @return
     */
    public String getSpecies() {
        return Species;
    }

    /**
     * Returns a string with species and name of the LivingBeing
     *
     * @param
     */
    public abstract String whoAreYou();

    /**
     * Returns current position of animal
     *
     */
    public int[] getPosition()
    {
        int [] pos = {x, y};
        return pos;
    }

    /**
     * Prints current position of LivingBeing
     *
     * @param
     */
    public String showCurrentPosition()
    {
        return "at position : (" + this.x + "," + this.y + ")";
    }

    /**
     * Moves LivingBeing to new position
     *
     * @param   x   x of position to move to
     * @param   y   y of position to move to
     */
    public abstract void move(int x, int y);

    /**
     * Returns hash code of the object created for this class for
     * LivingBeing's serial id
     *
     * @param
     */
    private int generateId(){
        return new Object().hashCode();
    }

}
