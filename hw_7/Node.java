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
    public E Payload;
    public Node Next;

    public Node(E payload) {
        Payload = payload;
    }
}
