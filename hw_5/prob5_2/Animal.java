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

public abstract class Animal extends LivingBeing{

    // Stores name of the animal.
    String Name;

    // Stores home of that animal
    String Home;

    // Coordinates of home of animal
    int x1_home = 21;
    int x2_home = 41;
    int y1_home = 32;
    int y2_home = 52;

    // Stores status of whether at home or not for the animal
    boolean AreYouHome = false;

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
        super(species);
        Name = name;
        Home = home;

        // Set coordinates randomly for home
        setHomeCoordinates();

        // Check if animal's position is in home boundaries and set
        // AreYouHome status
        AreYouHome = (x >= x1_home && x <= x2_home) && (y >= y1_home && y <= y2_home);
        
    }

    /**
     * Abstract method getName
     *
     * @param
     */
    public abstract String getName();

    /**
     * Changes home status of the animal and
     * returns the status as string to be printed
     *
     */
    public String goHome(){
        // Take random value within home coordinates and move to that position
        // Takes random value in range of home x and home y and adds that to smaller x and y
        int x_new = x1_home + new Random().nextInt((x2_home - x1_home) + 1);
        int y_new = y1_home + new Random().nextInt((y2_home - y1_home) + 1);
        move(x_new, y_new);

        AreYouHome = true;
        return "I am in "+ Home + " at position : (" + this.x + "," + this.y + ")";
    }

    /**
     * Returns a string with species and name of the animal
     *
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
     */
    public String AreYouHungry()
    {
        // Random value between 0 and 1 for hunger status of animal
        int hungerStatus = new Random().nextInt(2) ;
        if(hungerStatus == 0)
            return "No";
        else
            return "Yes";
    }

    /**
     * Returns yes or no for whether animal is at home based on
     * animal's home status
     *
     */
    public String AreYouHome()
    {
        if(!AreYouHome)
            return "No";
        else
            return "Yes";
    }

    /**
     * Moves animal to new position
     *
     * @param   x   x of position to move to
     * @param   y   y of position to move to
     */
    public void move(int x, int y)
    {
        // Set x and y of Object to new positions, x and y, passed as params
        this.x = x;
        this.y = y;

        System.out.println("I am " + Name + " at new position : (" + this.x + "," + this.y + ")");

        // Check if animal's position is in home boundaries and set
        // AreYouHome status
        AreYouHome = (x >= x1_home && x <= x2_home) && (y >= y1_home && y <= y2_home);
    }

    /**
     * Returns hash code of the object created for this class for
     * animal's serial id
     *
     */
    private int generateId(){
        return new Object().hashCode();
    }

    /**
     * Sets home coordinates randomly
     *
     */
    public void setHomeCoordinates()
    {
        // Random values for x home coordinates between 0 and 100
        int firstX = new Random().nextInt(101);
        int secondX = new Random().nextInt(101);
        if(firstX <= secondX)
        {
            x1_home = firstX;
            x2_home = secondX;
        }
        else
        {
            x1_home = secondX;
            x2_home = firstX;
        }

        // Random values for y home coordinates between 0 and 100
        int firstY = new Random().nextInt(101);
        int secondY = new Random().nextInt(101);
        if(firstY <= secondY)
        {
            y1_home = firstY;
            y2_home = secondY;
        }
        else
        {
            y1_home = secondY;
            y2_home = firstY;
        }
    }

}
