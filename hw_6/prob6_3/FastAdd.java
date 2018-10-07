/**
 * FastAdd.java
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

public class FastAdd {
    /**
     * Main function
     * @param args command line arguments(ignored)
     */
    public static void main(String [] args) {
        Storage<String> myLinkedList = new Storage<>();
        test(myLinkedList);
        myLinkedList = new Storage<>();
        exampleOfHowToUseIt(myLinkedList);
    }

    /**
     * Example of how to use the linked list functions
     * @param aStorage Linked list
     */
    private static void exampleOfHowToUseIt(Storage aStorage) {

        /** String **/
        aStorage = new Storage<>();
        System.out.println("aStorage is Empty ? "+ aStorage.isEmpty());

        // Add
        aStorage.add("b");
        aStorage.add("a");
        aStorage.add("c");
        System.out.println("aStorage: " + aStorage );

        // Contains
        System.out.println("aStorage contains a : " + aStorage.contains("a"));

        // Get and size
        for (int index = 0; index < aStorage.size(); index++) {
            System.out.println("Value at position " + index + " : " + aStorage.get());
        }

        // Sort
        aStorage.sort();
        System.out.print("Sorted aStorage :\t");
        for (int index = 0; index < aStorage.size(); index++) {
            System.out.print(aStorage.get() + " ");
        }

        System.out.println();

        /** Integer **/
        Storage<Integer> bStorage = new Storage<>();

        // Add
        bStorage.add(2);
        bStorage.add(1);
        bStorage.add(3);
        System.out.println("bStorage: " + bStorage );

        // Contains
        System.out.println("bStorage contains 2 : " + bStorage.contains(2));

        // Get and size
        for (int index = 0; index < bStorage.size(); index++) {
            System.out.println("Value at position " + index + " : " + bStorage.get());
        }

        // Sort
        bStorage.sort();
        System.out.print("Sorted bStorage :\t");
        for (int index = 0; index < bStorage.size(); index++) {
            System.out.print(bStorage.get() + " ");
        }
    }

    private static void test(Storage aStorage) {
        if (! testClear()) {
            System.out.println("testClear() failed!");
        }

        if(! testContains()) {
            System.out.println("testContains() failed!");
        }

        if(! testAdd()) {
            System.out.println("testAdd() failed!");
        }
        if(! testGet()) {
            System.out.println("testGet() failed!");
        }
        if(! testSort()) {
            System.out.println("testSort() failed!");
        }
    }

    /**
     * testAdd method to test add method of the data structure
     * @return
     */
    public static boolean testAdd()     {
        Storage<String> myLinkedList = new Storage<>();
        String theStrings[] = { "a", "b", "c" ,"c"};
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            myLinkedList.add(theStrings[index]);

        for ( int index = 0; index < theStrings.length; index ++ )
            rValue &= myLinkedList.contains(theStrings[index]);

        return rValue;
    }

    /**
     * test method to clear the list
     * @return
     */
    private static boolean testClear() {
        Storage <String> myLinkedList = new Storage<>();
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
        Storage <String> myLinkedList = new Storage<>();
        boolean emptyAtStart = myLinkedList.size() == 0;
        myLinkedList.add("one");
        boolean addedOne = myLinkedList.size() == 1 && myLinkedList.get().equals("one");
        boolean containsElement = myLinkedList.contains("one");
        return emptyAtStart && addedOne && containsElement;
    }

    /**
     * test method to get the list
     * @return
     */
    private static boolean testGet() {
        Storage <String> myLinkedList = new Storage<>();
        String theStrings[] = { "a", "b", "c" };
        boolean rValue = true;

        for ( int index = 0; index < theStrings.length; index ++ )
            myLinkedList.add(theStrings[index]);
        // Get should get us all values in the same order
        for ( int index = 0; index < theStrings.length; index ++ )
            rValue &= myLinkedList.get().equals(theStrings[index]);
        return rValue;
    }

    /**
     * test method to sort the list
     * @return
     */
    private static boolean testSort() {
        Storage <String> myLinkedList = new Storage<>();

        String theStrings[] = { "b", "c", "a" };
        boolean rValue = true;


        for ( int index = 0; index < theStrings.length; index ++ )
            myLinkedList.add(theStrings[index]);

        myLinkedList.sort();

        // Array with the strings sorted
        String sortedStrings [] = { "a", "b", "c" };

        // Get already tested
        // If we get the strings as in the sorted array sortedStrings then success
        for ( int index = 0; index < theStrings.length; index ++ )
            rValue &= myLinkedList.get().equals(sortedStrings[index]);

        int theIntegers[] = {1,7,9,2,3};
        Storage<Integer> list = new Storage<Integer>();

        for ( int index = 0; index < theIntegers.length; index ++ )
            list.add(theIntegers[index]);

        list.sort();
        int sortedIntegers[] = {1,2,3,7,9};
        for ( int index = 0; index < sortedIntegers.length; index ++ )
            rValue &= list.get().equals(sortedIntegers[index]);

        return rValue;
    }
}
