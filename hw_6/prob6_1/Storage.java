/**
 * Storage.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

/**
 * Driver class of the implemented data structure
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */
public class Storage {
    /**
     * main method
     * @param args
     */
    public static void main(String args[]){

        MyLinkedList<String> myLinkedList = new MyLinkedList();
        testAdd();
        exampleOfHowToUseIt(myLinkedList);
        myLinkedList = new MyLinkedList();
        test(myLinkedList);

    }

    /**
     * tests the add, remove Index, clear and addIndex method
     * @param aStorage
     */
    public static void test(MyLinkedList<String> aStorage)   {
        if ( ! testContains() )
            System.err.println("testContains failed");
        if ( ! testRemove() )
            System.err.println("testRemove failed");
        if ( ! testRemoveAll() )
            System.err.println("testRemoveAll failed");
        if ( ! testAdd() )
            System.err.println("testAdd failed");
        if ( ! testAddAll() )
            System.err.println("testAdd failed");
        if ( ! testClear() )
            System.err.println("testClear failed");
    }

    /**
     * example of how to use the data structure
     * @param aStorage
     */
    public static void exampleOfHowToUseIt( MyLinkedList<String> aStorage)   {
        aStorage = new MyLinkedList<String>();
        MyLinkedList<String> bStorage = new MyLinkedList<String>();
        aStorage.add("a");
        System.out.println("aStorage: " + aStorage );
        bStorage.add("b");
        bStorage.add("a");
        bStorage.add("c");
        System.out.println("bStorage: " + bStorage );
        if ( ! aStorage.addAll(aStorage) )
            System.out.println("You can not add yourself to yourself.");
        aStorage.addAll(bStorage);
        System.out.println("aStorage: " + aStorage );
        aStorage.removeAll(bStorage);
        System.out.println("aStorage: " + aStorage );

    }

    /**
     * testAdd method to test add and remove method of the data structure
     * @return
     */
    public static boolean testAdd()     {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        String theStrings[] = { "a", "b", "c" };
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            myLinkedList.add(theStrings[index]);
        // Contains already tested
        for ( int index = 0; index < theStrings.length; index ++ )
            rValue &= myLinkedList.contains(theStrings[index]);
        //myLinkedList.add("c");

        return rValue;
    }

    /**
     * test method to clear the list
     * @return
     */
    private static boolean testClear() {
        MyLinkedList <String> myLinkedList = new MyLinkedList<>();
        boolean emptyStart = myLinkedList.size() == 0;
        myLinkedList.add("one");
        boolean oneAfterAdd = myLinkedList.size() == 1;
        myLinkedList.clear();
        boolean emptyAfterClear = myLinkedList.size() == 0;
        return emptyStart && oneAfterAdd && emptyAfterClear;
    }

    /**
     * test method to check if storage contains element
     * @return
     */
    private static boolean testContains() {
        MyLinkedList <String> myLinkedList = new MyLinkedList<>();
        boolean emptyAtStart = myLinkedList.size() == 0;
        myLinkedList.add("one");
        boolean addedOne = myLinkedList.size() == 1 && myLinkedList.element().equals("one");
        boolean containsElement = myLinkedList.contains("one");
        return emptyAtStart && addedOne && containsElement;
    }

    /**
     * test method AddAll
     * @return
     */
    private static boolean testAddAll() {
        MyLinkedList <String> myLinkedList = new MyLinkedList<>();
        String [] store = {"a", "b", "c"};
        MyLinkedList <String> newLinkedList = new MyLinkedList<>();

        // Adds all elements that are not already present
        myLinkedList.addAll(newLinkedList);

        // removeAll is already tested
        // After addAll all the elements were either added or already present
        // Hence removeAll will return true if all elements were removed
        boolean rValue = myLinkedList.removeAll(newLinkedList);

        return  rValue;
    }

    /**
     * test method Remove
     * @return
     */
    private static boolean testRemove() {
        MyLinkedList <String> myLinkedList = new MyLinkedList<>();
        myLinkedList.add("one");
        // Contains is already tested
        boolean hasElement = myLinkedList.contains("one");
        boolean removedElement = myLinkedList.remove("one");
        return hasElement && removedElement;
    }

    /**
     * test method RemoveAll
     * @return
     */
    private static boolean testRemoveAll() {
        MyLinkedList <String> myLinkedList = new MyLinkedList<>();
        String [] store = {"a", "b", "c"};
        MyLinkedList <String> newLinkedList = new MyLinkedList<>();
        // Add is already tested
        for(int index = 0; index < store.length; index++) {
            myLinkedList.add(store[index]);
            newLinkedList.add(store[index]);
        }

        myLinkedList.removeAll(newLinkedList);

        boolean rValue = true;
        // Contains has already been tested
        // If none of the values are present then removeAll worked
        for(int index = 0; index < store.length; index++) {
            rValue &= !(myLinkedList.contains(store[index]));
        }
        return  rValue;
    }
}
