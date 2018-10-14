/**
 * Storage.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

/**
 * Actual implementation Class which defines the internal structure of nodes in the List
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */
public class Storage<E>{
    private Node Start;
    private Node End;
    private int Size;
    private Node currentNodeToReturn;
    /**
     * Non parameterized constructor for MyLinkedList
     */
    public Storage() {
        Size = 0;
    }

    /**
     * returns the number of elements in teh list
     * @return
     */

    public int size() {
        return Size;
    }


    public String getClassName() {
        return "Storage";
    }

    /**
     * add the new node to the front of the list
     * @param e
     */
    public void addFirst(E e) {
        Node  newNode = createANewNode(e);
        Node temp = Start;
        Start = newNode;
        newNode.Next = temp;
        temp.Previous = newNode;
        Size++;
    }

    /**
     * adds the element to the end of the list
     * @param e
     */
    public void addLast(E e){
        Node  newNode = createANewNode(e);
        Node temp = End;
        End = newNode;
        newNode.Previous = temp;
        temp.Next = newNode;
        Size++;
    }

    /**
     * Adds the new node to the end of the list
     * @param e
     * @return
     */

    public boolean add(E e) {
        Node  newNode = createANewNode(e);

        // check if this is the first node in the list
        if (Start == null)
        {
            Start = newNode;
            End = newNode;
            Size++;
            return  true;
        }
        else {
            // this is not a new node
            addLast(e);
            return  true;
        }
    }

    /**
     * Adds the elements of the storage if not present already
     * @param storage Storage whose elements are to be added
     * @return true if at least one element added else false
     */
    public boolean addAll(Storage<E> storage) {
        int addCount = 0;

        Node temp = storage.Start;
        while (temp != null) {
            // If element already there then do not add, else add
            // i.e. if its a subset or an equivalent storage then nothing added
            if (!(this.contains((E) temp.Data))) {
                if(add((E) temp.Data)) {
                    addCount++;
                }
            }

            temp = temp.Next;
        }
        if(addCount > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Get element at getIndex position
     * @return list array element at get
     */

    public E get() {
        E payload;
        // check if this is the first call to get method
        if (currentNodeToReturn == null){
            currentNodeToReturn = Start;
        }
        payload = (E) currentNodeToReturn.Data;
        currentNodeToReturn = currentNodeToReturn.Next;
        return payload;
    }

    /**
     * clears the entire list
     */

    public void clear(){
        Start = End = null;
        Size=0;
    }

    /**
     * Checks if given value is present in list
     * @param e value to find
     * @return true if value found else false
     */

    public boolean contains(E e) {
        if(size() == 0) {
            return false;
        }
        // Found at Start
        if(Start.Data.toString().equals(e.toString())) {
            return true;
        }

        // If there is more than one element then only check further
        if(Start != End) {
            // Found at End
            if (End.Data.toString().equals(e.toString())) {
                return true;
            }

            // If not found at end or start
            int found = 0;
            Node temp = Start.Next;
            while (temp != End) {
                if (temp.Data.toString().equals(e.toString())) {
                    found = 1;
                    break;
                }
                temp = temp.Next;
            }
            if (found == 1) {
                return true;
            } else {
                return false;
            }
        }
        else {
            // Only one element is present which is not required element
            return false;
        }
    }

    /**
     * Checks if list is empty
     * @return true if size is 0 else false
     */

    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Helper function to create a new Node
     * @param e
     * @return
     */
    private Node createANewNode(E e) {
        Node node = new Node(e);
        return  node;
    }

    /**
     * To String function to print the entire list
     * @return
     */
    public String toString() {
        Node node = Start;
        StringBuilder sb = new StringBuilder();
        sb.append("# of elements: "+Size);
        sb.append("| --> ");
        while (node != null){
            sb.append(node.Data).append(" --> ");
            node = node.Next;
        }
        sb.append("NULL");

        return  sb.toString();
    }

    /**
     * Removes all elements from list that are in storage.
     * @param storage List with the elements to remove.
     * @return true if number of elements removed is same as size of storage.
     */
    public boolean removeAll(Storage<E> storage) {
        int removeCount = 0;

        Node temp = storage.Start;
        while(temp != null) {
            if(remove((E) temp.Data)) {
                removeCount++;
            }
            temp = temp.Next;
        }
        if(removeCount == storage.size()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Finds the given data and removes that node
     * @param e Data to be removed
     * @return true if removed else false
     */
    public boolean remove(E e) {
        // Empty list
        if(size() == 0) {
            return false;
        }

        // Found at the start
        if(Start.Data.toString().equals(e.toString())) {
            // Make second node as start
            Start = Start.Next;
            //Start.Previous = null;

            Size--;
            return true;
        }

        // If there is more than one element then check further
        if(Start != End) {
            // Found at the end
            if (End.Data.toString().equals(e.toString())) {
                // Make second last node as end
                // We reach here only if Start != End so End.Previous is never null
                End = End.Previous;
                End.Next = null;
                Size--;
                return true;
            }

            // Element anywhere between Start and End
            Node temp = Start.Next;
            int found = 0;
            while (temp != End) {
                // Since in case of string == won't work while in other cases equals won't work
                if (temp.Data.toString().equals(e.toString())) {
                    // Make next and previous of temp point to each other
                    temp.Next.Previous = temp.Previous;
                    temp.Previous.Next = temp.Next;
                    Size--;
                    found = 1;
                    break;
                }
                temp = temp.Next;
            }
            if (found == 1) {
                // Element found and removed
                return true;
            } else {
                return false;
            }
        }
        else {
            // Only one element which is not required element
            return false;
        }
    }

    /**
     * Returns the element from the from front of the list
     * @return
     */
    public E element(){
        if (Size > 0)
        return (E) Start.Data;
        return null;
    }

}
