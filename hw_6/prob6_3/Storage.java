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
public class Storage<E> implements StorageI<E>{
    private Node Start;
    private int Size;
    private int Index;
    /**
     * Non parameterized constructor for MyLinkedList
     */
    public Storage() {
        Index = 0;
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
        return "Storage";
    }


    /**
     * Adds the new node to the end of the list
     * @param e
     * @return
     */
    @Override
    public boolean add(E e) {
        // check if this is the first node in the list
        if (Start == null)
        {
           Start = addNewNode(Start,e);
           return  true;
        }
        else {
            // this is not a new node
            addNewNode(Start,e);
            return  true;
        }
    }

    public Node addNewNode(Node node,E e){
        if (node == null)
        {
            Size++;
            Node newNode = createANewNode(e,Size);
            node = newNode;
           // End = newNode;
            return  node;
        }

        if(isLeftGreaterThanRight(e, (E) node.Data)){
            node.Next = addNewNode(node.Next,e);
        }
        else {
            node.Previous = addNewNode(node.Previous,e);

        }
        return node;
    }

    /**
     * Get element at getIndex position
     * @return list array element at get
     */
    @Override
    public E get() {
        E payload;
        // check if the index length has overgrown
        if (Index == Size + 1){
            Index = 0;
        }
        Index++;
        return InorderTraverse(Start,Index);
    }

    private E InorderTraverse(Node node,int index){
        if(node!=null){
            if(node.NodeIndex == index){
                return (E) node.Data;
            }
            else{
                 E e = InorderTraverse(node.Previous, index);
                if (e==null){
                    return InorderTraverse(node.Next,index);
                }
                else {
                    return e;
                }
            }
        }
        return null;
    }

    private E InorderTraverse(Node node,E data){
        if(node!=null){
            if(node.Data.toString().equals(data.toString())){
                return (E) node.Data;
            }
            else{
                 E e = InorderTraverse(node.Previous, data);
                if (e == null){
                    return InorderTraverse(node.Next,data);
                }
                else {
                    return e;
                }
            }
        }
        return null;
    }

    private void UpdateIndex(Node node){
        if(node!=null){
            UpdateIndex(node.Previous);
            node.NodeIndex = Index;
            Index++;
            UpdateIndex(node.Next);
        }
    }

    /**
     * clears the entire list
     */
    @Override
    public void clear(){
        Start =  null;
        Size=0; Index=0;
    }

    /**
     * Checks if given value is present in list
     * @param e value to find
     * @return true if value found else false
     */
    @Override
    public boolean contains(E e) {
            if(InorderTraverse(Start,e)!=null){
                return true;
            }
            else {
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

    /**
     * Sorts the elements in the list
     */
    @Override
    public void sort() {
        Index=1;
        UpdateIndex(Start);
        Index = 0;
       return;
    }


    private boolean isLeftGreaterThanRight(E left, E right) {
        return (left.toString().compareTo(right.toString()) > 0);
    }


    /**
     * Helper function to create a new Node
     * @param e
     * @return
     */
    private Node createANewNode(E e,int index) {
        Node node = new Node(e,index);
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
        printInOrder(sb,Start.Previous);
        sb.append(node.Data).append(" --> ");
        printInOrder(sb,Start.Next);
        sb.append("NULL");
        return  sb.toString();
    }

    private void printInOrder(StringBuilder sb, Node node) {
        if(node!=null){
            printInOrder(sb,node.Previous);
            sb.append(node.Data).append(" --> ");
            printInOrder(sb,node.Next);
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
