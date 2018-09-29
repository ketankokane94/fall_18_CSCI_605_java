import java.util.LinkedList;

public class MyLinkedList {
    private Node Start;
    private Node End;

    public int size() {
        return Size;
    }

    private int Size;


    public boolean addE(Object e){
        Node  newNode = createANewNode(e);
        Size++;
        // check if this is the first node in the list
        if (Start == null)
        {
            Start = newNode;
            End = newNode;
            return  true;
        }
        else {
            // this is not a new node
           addFirst(e);
            return  true;
        }
    }

    public void addFirst(Object e) {
        Node  newNode = createANewNode(e);
        Node temp = Start;
        Start = newNode;
        newNode.Next = temp;
        temp.Previous = newNode;
    }

    public void addLast(Object e){
        Node  newNode = createANewNode(e);
        Node temp = End;
        End = newNode;
        newNode.Previous = temp;
        temp.Next = newNode;
    }

    public void clear(){
        Start = End = null;
        Size=0;
    }

    private Node createANewNode(Object e) {
        Node node = new Node(e);
        return  node;
    }

    public LinkedList reverse(){
        return  null;
    }

    @Override
    public String toString() {
        Node node = Start;
        StringBuilder sb = new StringBuilder();
        while (node != null){
            sb.append(node.Data).append(" ");
            node = node.Next;
        }

        return  sb.toString();
    }

    public Object remove(){
        Node temp;
        // check if atleast one element is present in the list
        if (size() > 0){
            temp = Start;
            Start = Start.Next;
            Start.Previous = null;
            Size--;
        }

        return temp;
    }

    public void add(int index, Object e){
        // check if the index is in the range of the
    }

    /*


void    add(int index, E element)
E       element()
E       remove(int index)
     */
}
