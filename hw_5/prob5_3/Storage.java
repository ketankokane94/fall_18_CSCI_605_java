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
     * testAdd method to test add and remove method of the data structure
     * @return
     */
    public static boolean testAdd()     {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        String theStrings[] = { "a", "b", "c" };
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            myLinkedList.add(theStrings[index]);
        for ( int index = 0; index < theStrings.length; index ++ )
            rValue &= myLinkedList.remove().equals(theStrings[index]);
        rValue &= myLinkedList.remove() == null;
        myLinkedList.add("c");

        return rValue;
    }

    /**
     * tests the add, remove Index, clear and addIndex method
     * @param aStorage
     */
    public static void test(MyLinkedList<String> aStorage)   {
        if ( ! testAdd() )
            System.err.println("testAdd failed");
        if ( ! testRemoveIndex() )
            System.err.println("testRemoveIndex failed");
        if ( ! testAddIndex() )
            System.err.println("testAddIndex failed");
        if ( ! testClear() )
            System.err.println("testClear failed");
    }

    /**
     * example of how to use the data structure
     * @param aStorage
     */
    public static void exampleOfHowToUseIt( MyLinkedList<String> aStorage)   {
        aStorage.add("a");
        aStorage.add(0, "0");
        aStorage.add(aStorage.size(), "1");
        aStorage.add(aStorage.size() + 1, "2");
        System.out.println("aStorage: " + aStorage );

    }

    /**
     * test method to clear the list
     * @return
     */
    private static boolean testClear() {
    MyLinkedList <String> myLinkedList = new MyLinkedList<>();
    myLinkedList.add("one");
    myLinkedList.clear();
    return myLinkedList.size()==0;


    }

    /**
     * testAddIndex
     * @return
     */
    private static boolean testAddIndex() {
        MyLinkedList <String> myLinkedList = new MyLinkedList<>();
        myLinkedList.add("one");
        myLinkedList.add(0,"Two");
        return myLinkedList.element().equals("Two");
    }

    /**
     * testRemoveIndex
     * @return
     */
    private static boolean testRemoveIndex() {
        MyLinkedList <String> myLinkedList = new MyLinkedList<>();
        myLinkedList.add("one");
        myLinkedList.add(0,"Two");
        return  myLinkedList.remove(0).equals("Two");
    }
}
