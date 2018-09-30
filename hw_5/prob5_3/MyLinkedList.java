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
public class MyLinkedList<E> {
    private Node Start;
    private Node End;
    private int Size;

    /**
     * returns the number of elements in teh list
     * @return
     */
    public int size() {
        return Size;
    }

    /**
     * adds the new node to the end of the list
     * @param e
     * @return
     */
    public boolean add(E e){
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
     * clears the entire list
     */

    public void clear(){
        Start = End = null;
        Size=0;
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
    @Override
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
     * Removes element from the front of the list
     * @return
     */
    public E remove(){
        Node temp = null;
        // check if atleast one element is present in the list
        if (size() > 0){
            temp = Start;
            Start = Start.Next;
            Size--;
            return (E) temp.Data;
        }
       return null;

    }

    /**
     * adds the element in the list at the given index
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        // check if the index is in the range of the
        if (index == 0){
            addFirst(e);
        }
        else if(index == size()-1){
            addLast(e);
        }
        else if (index < Size){

            Node newNode = createANewNode(e);
            Node temp = Start;
            while (index > 0) {
                temp = temp.Next;
                index --;
            }
            Node Previous = temp.Previous;
            temp.Previous = newNode;
            newNode.Next = temp;
            newNode.Previous = Previous;
            Previous.Next = newNode;
            Size++;
        }
    }

    /**
     * removes the element from the list of the given index
     * @param index
     * @return
     */
    public E remove(int index){
        Size--;
        Node temp = Start;
        if (index == 0){

            Start = Start.Next;
            return (E) temp.Data;
        }
        else if (index == size()){
            temp = End;
            End = End.Previous;
            return (E) temp.Data;
        }
        else {
            while (index > 0){
                temp = temp.Next;
                index--;
            }
            temp.Next.Previous = temp.Previous;
            temp.Previous.Next = temp.Next;
            return (E) temp.Data;
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
