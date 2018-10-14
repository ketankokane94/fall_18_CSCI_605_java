public class MyHashSet<E> implements SetI<E> {

    /*
    maintain an 2 D array to store the buckets -- > array of what ? ans array of E
    why 2D Array to handle the collision

    What is load factor ???

     */

    public MyHashSet() {
        numberOfElementsInHashSet = 0;
        numberOfBuckets = 16;
        buckets = new Node[numberOfBuckets];
    }

    private Node buckets[];
    private int numberOfBuckets;
    private int numberOfElementsInHashSet;

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
            insertTheElementAtGivenIndex(ele,generatedHashCode);
            numberOfElementsInHashSet++;
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(SetI c) {
        return false;
    }

    @Override
    public void clear() {
        buckets = new Node[numberOfBuckets];
        numberOfElementsInHashSet = 0;
    }

    @Override
    public boolean contains(Object ele) {
        // calculate the hash code of the  object
        int generateHashCode = calculateHashCode((E) ele);

        if (buckets[generateHashCode] == null)
            return false;
        return checkIfElementIsPresentAtElement((E) ele,generateHashCode);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean remove(Object ele) {
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
        return false;
    }

    @Override
    public boolean containsAll(SetI c) {
        return false;
    }

    private void iterateThroughTheList(Node node, StringBuilder sb) {
        while (node!=null && node.Payload != null){
            sb.append(node.Payload);
            sb.append("-->");
        }
    }

    private boolean checkIfElementIsPresentAtElement(E ele, int generateHashCode) {
        // traverse through the entire linked list at a index to check if the element is present or not
        Node node = buckets[generateHashCode];
        while (node != null){
            // need a better object equality here
            // need a null check for Payload
            if(node.Payload.toString().compareTo(ele.toString()) == 0){
                return true;
            }
            node = node.Next;
        }
        return false;
    }

    private void insertTheElementAtGivenIndex(E ele, int generateHashCode) {
        // if no element is present at the index simply add the new node there
        if(buckets[generateHashCode]==null){
            buckets[generateHashCode] = new Node(ele);
        }
        else {
            // this part of the code is to handle the collision
            Node node = buckets[generateHashCode];
            while (node.Next != null){
                node = node.Next;
            }
            // now temp is pointing to the last node
            node.Next = new Node(ele);
        }
    }
    private int calculateHashCode(E ele) {
        // if the object is null then always 0 as the hashcode or use hashcode of the object
        // mod function is to keep the hashcode bound to size of the array as we use it as your array index
        return ele == null ? 0 : ele.hashCode() < 0 ? (ele.hashCode() * -1 ) % (numberOfBuckets - 1) : ele.hashCode() % (numberOfBuckets - 1);
    }
}