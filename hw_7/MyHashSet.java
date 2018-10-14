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
        getArrayIndex = 0;
        getListIndex = 0;
    }

    private Storage<E> buckets[];
    private int numberOfBuckets;
    private int numberOfElementsInHashSet;
    private int getArrayIndex;
    private int getListIndex;

    /**
     *
     * @param ele
     * @return
     */
    @Override
    public boolean add(E ele) {
        // calculate the hash code of the  object
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
        while (index < newHashSet.buckets.length) {
            /*
                Add all elements in linked list in newHashset at index to
                linked list at index in current hashset
            */
            if (buckets[index].addAll(newHashSet.buckets[index])) {
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
        // Get hash code of the object
        int generateHashCode = calculateHashCode((E) ele);
        // Remove from linked list at index = hash code of object
        if (buckets[generateHashCode] != null && buckets[generateHashCode].remove((E) ele)) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return numberOfElementsInHashSet;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean removeAll(SetI c) {
        int removeCount = 0;
        int index = 0;
        MyHashSet<E> newHashSet = (MyHashSet<E>) c;
        while (index < newHashSet.buckets.length) {
            /*
                Remove all elements in linked list in newHashset at index from
                linked list at index in current hashset
            */
            if (buckets[index].removeAll(newHashSet.buckets[index])) {
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
        return false;
    }

    /*
        Helper function to get element
     
    private E get() {
        E getElement = this.buckets[getArrayIndex].get();
        getListIndex++;
        if(getListIndex )
    }*/

    private int calculateHashCode(E ele) {
        // if the object is null then always 0 as the hashcode or use hashcode of the object
        // mod function is to keep the hashcode bound to size of the array as we use it as your array index
        return ele == null ? 0 : ele.hashCode() < 0 ? (ele.hashCode() * -1 ) % (numberOfBuckets - 1) : ele.hashCode() % (numberOfBuckets - 1);
    }
}
