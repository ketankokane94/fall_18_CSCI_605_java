/**
 * PalmTree.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

/**
 *
 * This is a class PalmTree for plants of the Palm Tree species. It has abstract class Plant as super class.
 * Hence, it implements method getType from Plant.
 *
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

public class PalmTree extends Plant {

    /**
     * Constructor for PalmTree class. It can be instantiated to
     * create an object for any plant that is a PalmTree.
     * It calls super to pass type of plant i.e. Tree or
     * flower pot to the super class Plant's constructor.
     *
     * @param   type             Type of PalmTree
     */
    public PalmTree(String type) {
        super("PalmTree", type);
    }

    /**
     * Returns type of plant i.e. Tree or flower pot
     *
     * @return  type    type of plant
     */
    @Override
    public String getType() {
        return type;
    }
}