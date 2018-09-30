/**
 * Node.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

/**
  * Node Class which defines the internal structure of elements in the List
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

public class Node<E> {
    Node Previous;
    E Data;
    Node Next;

    /**
     * Constructor to create a new Node with the given data
     * @param data
     */
    public Node(E data) {
        Data = data;
    }


    /**
     * Constructor to create a new Node with Previous Node and given data
     * @param previous
     * @param data
     */
    public Node(Node previous, E data) {
        Previous = previous;
        Data = data;
    }

    /**
     * Constructor to create a new Node with Next Node and given data
     * @param data
     * @param next
     */
    public Node(E data, Node next) {
        Data = data;
        Next = next;
    }

    /**
     * Constructor to create a new Node with Previous,Next Node and given data
     * @param previous
     * @param data
     * @param next
     */
    public Node(Node previous, E data, Node next) {
        Previous = previous;
        Data = data;
        Next = next;
    }
}
