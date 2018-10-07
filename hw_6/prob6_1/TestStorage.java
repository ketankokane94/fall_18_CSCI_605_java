public class TestStorage {

    /**
     * main method
     * @param args
     */
    public static void main(String args[]){

        Storage<String> myLinkedList = new Storage();
        exampleOfHowToUseIt(myLinkedList);
        myLinkedList = new Storage();
        test(myLinkedList);

    }

    /**
     * tests the add, remove Index, clear and addIndex method
     * @param aStorage
     */
    public static void test(Storage<String> aStorage)   {
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
    public static void exampleOfHowToUseIt( Storage<String> aStorage)   {
        aStorage = new Storage<String>();
        Storage<String> bStorage = new Storage<String>();
        aStorage.add("a");
        System.out.println("aStorage: " + aStorage );
        bStorage.add("b");
        bStorage.add("a");
        bStorage.add("c");
        System.out.println("bStorage: " + bStorage );

        System.out.print("bStorage Array : [ ");
        Object [] bStore = bStorage.toArray();
        for(int index = 0; index < bStore.length; index++) {
            System.out.print(bStore[index] + " ");
        }
        System.out.println("]");

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
        Storage<String> myLinkedList = new Storage<>();
        String theStrings[] = { "a", "b", "c","c" };
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
            myLinkedList.add(theStrings[index]);
        // the size should be 3
        rValue &= myLinkedList.size() == 3;
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
        boolean addedOne = myLinkedList.size() == 1 && myLinkedList.element().equals("one");
        boolean containsElement = myLinkedList.contains("one");
        // should return false if the element is not present
        containsElement &= ! myLinkedList.contains("two");
        return emptyAtStart && addedOne && containsElement;
    }

    /**
     * test method AddAll
     * @return
     */
    private static boolean testAddAll() {
        boolean rValue = true;
        Storage <String> myLinkedList = new Storage<>();
        myLinkedList.add("one");myLinkedList.add("two");
        Storage <String> newLinkedList = new Storage<>();
        newLinkedList.add("one");newLinkedList.add("two");
        newLinkedList.add("3");newLinkedList.add("4");
        rValue &= myLinkedList.size() == 2;
        // Adds all elements that are not already present
        myLinkedList.addAll(newLinkedList);
        rValue &= myLinkedList.size() == 4;
        rValue &= myLinkedList.contains("3");
        // After addAll all the elements were either added or already present
        // Hence removeAll will return true if all elements were removed
        rValue &= myLinkedList.removeAll(newLinkedList);

        return  rValue;
    }

    /**
     * test method Remove
     * @return
     */
    private static boolean testRemove() {
        Storage <String> myLinkedList = new Storage<>();
        myLinkedList.add("one");
        myLinkedList.add("one");

        boolean hasElement = myLinkedList.contains("one");
        boolean removedElement = myLinkedList.remove("one");
        
        return hasElement && removedElement;
    }

    /**
     * test method RemoveAll
     * @return
     */
    private static boolean testRemoveAll() {
        Storage <String> myLinkedList = new Storage<>();
        String [] store = {"a", "b", "c"};
        Storage <String> newLinkedList = new Storage<>();
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
