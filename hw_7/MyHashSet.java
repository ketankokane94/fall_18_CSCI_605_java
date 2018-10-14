import javafx.css.Size;

public class MyHashSet<E> implements SetI<E>
{
    /*
    maintain an 2 D array to store the buckets -- > array of what ? ans array of E
    why 2D Array to handle the collision
    What is load factor ???
     */

    public MyHashSet() {
        numberOfElementsInHashSet = 0;
        numberOfBuckets = 16;
        buckets = new Storage[numberOfBuckets];
        arrayIndex = 0;
        listIndex = 0;
    }

    private Storage<E> buckets[];
    private int numberOfBuckets;
    private int numberOfElementsInHashSet;
    private int arrayIndex;
    private int listIndex;
    private boolean hasNullElement;

    /**
     *
     * @param ele
     * @return
     */
    @Override
    public boolean add(E ele) {
        // calculate the hash code of the  object
        if (ele == null && !hasNullElement){
                hasNullElement = true;
                return true;
        }
        int generatedHashCode = calculateHashCode(ele);
        // check if the object already exists
        if(!contains(ele)){
            // the logic of inserting the element
            if(buckets[generatedHashCode] == null) {
                buckets[generatedHashCode] = new Storage<>();
            }
                boolean added = buckets[generatedHashCode].add(ele);
                if (added) {
                    numberOfElementsInHashSet++;
                    return true;
                }
            }
            return false;
    }

    @Override
    public boolean addAll(SetI<? extends E> c) {
        int addCount = 0;
        int index = 0;
        MyHashSet<E> newHashSet = (MyHashSet<E>) c;
        while (index < newHashSet.size()) {

            // Get element to be added
            E elementToAdd = newHashSet.get();

            // Add element to current hash set
            if(add(elementToAdd)) {
                addCount++;
            }

            index++;
        }
        // If atleast one added
        if (addCount > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        buckets = new Storage[numberOfBuckets];
        numberOfElementsInHashSet = 0;
    }

    @Override
    public boolean contains(Object ele) {
        // calculate the hash code of the  object
        int generateHashCode = calculateHashCode((E) ele);

        // If empty then definitely does not contain
        if (buckets[generateHashCode] == null)
            return false;
        // Check if element contained in linked list at index  = hash code of element
        return buckets[generateHashCode].contains((E) ele);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean remove(Object ele) {
        if (ele == null && hasNullElement){
            hasNullElement = false;
            return true;
        }
        else {
            // Get hash code of the object
            int generateHashCode = calculateHashCode((E) ele);
            // Remove from linked list at index = hash code of object
            if (buckets[generateHashCode] != null && buckets[generateHashCode].remove((E) ele)) {
                if (buckets[generateHashCode].size() == 0)
                    buckets[generateHashCode] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return numberOfElementsInHashSet;
    }

    @Override
    public Object[] toArray() {
        Object [] arrayOfList = new Object[size()];
        int index = 0;
        arrayIndex = 0;
        listIndex = 0;
        while (index < size()) {
            arrayOfList[index] = this.get();
            index++;
        }
        return arrayOfList;
    }

    @Override
    public boolean removeAll(SetI c) {
        int removeCount = 0;
        int index = 0;
        MyHashSet<E> newHashSet = (MyHashSet<E>) c;
        while (index < newHashSet.size()) {

            // Get element to be added
            E elementToAdd = newHashSet.get();

            // Add element to current hash set
            if(remove(elementToAdd)) {
                removeCount++;
            }

            index++;
        }
        // If all removed
        if (removeCount == newHashSet.size()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(SetI c) {
        int containsCount = 0;
        int index = 0;
        MyHashSet<E> newHashSet = (MyHashSet<E>) c;
        while (index < newHashSet.size()) {

            // Get element to be added
            E elementToAdd = newHashSet.get();

            // Add element to current hash set
            if(contains(elementToAdd)) {
                containsCount++;
            }

            index++;
        }
        // If all found
        if (containsCount == newHashSet.size()) {
            return true;
        }
        return false;
    }

    /*@Override
    public boolean equals(Object o) {

    }*/

    /*
        Helper function to get element
     */
    public E get() {
        // go through the entire array using arrayIndex
        if (size() > 0) {
            while (arrayIndex < numberOfBuckets) {
                if (buckets[arrayIndex] != null && listIndex < buckets[arrayIndex].size()) {
                    E elementToReturn = buckets[arrayIndex].get();
                    listIndex++;
                    return elementToReturn;
                } else {
                    arrayIndex = ++arrayIndex % buckets.length;
                    listIndex = 0;
                }
            }
        }

            return null;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("\n");

        for (int index = 0; index < numberOfBuckets; index++) {
                sb.append(String.format("%5s", index) + ": " );
                sb.append(index==0 ? hasNullElement? "null ": "": "");
                sb.append(buckets[index] == null ? "" : buckets[index]).append("\n");
        }
        return sb.toString();

    }

    private int calculateHashCode(E ele) {
        // if the object is null then always 0 as the hashcode or use hashcode of the object
        // mod function is to keep the hashcode bound to size of the array as we use it as your array index
        return ele == null ? 0 : ele.hashCode() < 0 ? (ele.hashCode() * -1 ) % (numberOfBuckets - 1) : ele.hashCode() % (numberOfBuckets - 1);

    }

    public static void main(String args[] )      {
        SetI<String> aSet = new MyHashSet<String>();
        SetI<String> bSet = new MyHashSet<String>();

        String[] aStrings = { "a", "b", "c" };
        String[] bStrings = { "A", "B", "C" };
        aSet.add(aStrings[0]); aSet.add(aStrings[1]);           // setup a, b
        bSet.add(bStrings[0]); bSet.add(bStrings[1]);           // setup A, B

        System.out.println("aSet = " + aSet );                  // --> a, b

        for (int index = 0; index < aStrings.length; index ++ ) {       // contans a and b, not c
            System.out.println("does " +
                    ( aSet.contains(aStrings[index]) ? "" : " not " ) + "contain: " +
                    aStrings[index] );
        }
        System.out.println("aSet = " + aSet );                  // --> a, b

        System.out.println("aSet.remove(aStrings[0]); = " + aSet.remove(aStrings[0]) ); // contains b
        System.out.println("aSet.remove(aStrings[2]); = " + aSet.remove(aStrings[2]) ); // can not remove x
        System.out.println("aSet = " + aSet );

        aSet.addAll(bSet);                                      // --> b, A, B
        System.out.println("aSet = " + aSet );


        aSet.add(null);                                         // --> b, A, B, null
        System.out.println("aSet = " + aSet );
        System.out.println("aSet.remove(null); = " + aSet.remove(null) );       // can remove null
    }
}
