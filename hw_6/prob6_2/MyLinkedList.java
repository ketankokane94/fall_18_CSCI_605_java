/**
 * MyLinkedList.java
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
public class MyLinkedList<E> implements StorageI<E>{
    private Node Start;
    private Node End;
    private int Size;

    /**
     * Non parameterized constructor for MyLinkedList
     */
    public MyLinkedList() {
        Size = 0;
    }

    /**
     * returns the number of elements in teh list
     * @return
     */
    @Override
    public int size() {
        return Size;
    }

    @Override
    public String getClassName() {
        return "MyLinkedList";
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
    @Override
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

    @Override
    public E get() {
        return null;
    }

    /**
     * clears the entire list
     */
    @Override
    public void clear(){
        Start = End = null;
        Size=0;
    }

    /**
     * Checks if given value is present in list
     * @param e value to find
     * @return true if value found else false
     */
    @Override
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
    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void sort() {

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
     * Returns the element from the from front of the list
     * @return
     */
    public E element(){
        if (Size > 0)
        return (E) Start.Data;
        return null;
    }

}
