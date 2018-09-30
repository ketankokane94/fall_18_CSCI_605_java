/**
 * SchoolOfGoldFish.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

/**
 *
 * This is a class SchoolOfGoldFish for animals of the SchoolOfGoldFish species. It has abstract class Animal as super class
 * Hence, it implements method getName from Animals and appends number of fish to id and overrides whoAreYou.
 *
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

public class SchoolOfGoldFish extends Animal{

    // Id for fish
    String Id;

    /**
     * Constructor for SchoolOfGoldFish class. It can be instantiated to
     * create an object for any animal that is a SchoolOfGoldFish.
     * It calls super to pass name, species and home to the
     * super class Animals' constructor.
     *
     * @param   name             Name of SchoolOfGoldFish
     * @param   home             Home of the SchoolOfGoldFish
     */
    public SchoolOfGoldFish(String name, String home, int numberOfFish) {
        super(name,"GoldFish",home);
        Id = "Id : " + super.Id + " Num of Fish : " +  numberOfFish;
    }

    /**
     * Returns name of the SchoolOfGoldFish
     *
     */
    public String getName() {
        return Name;
    }

    /**
     * Returns a string with species and name of the Gold Fish.
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
}