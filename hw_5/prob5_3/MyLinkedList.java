public class MyLinkedList<E> {
    private Node Start;
    private Node End;
    private int Size;

    public int size() {
        return Size;
    }

    public boolean addE(E e){
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
           addFirst(e);
            return  true;
        }
    }

    public void addFirst(E e) {
        Node  newNode = createANewNode(e);
        Node temp = Start;
        Start = newNode;
        newNode.Next = temp;
        temp.Previous = newNode;
        Size++;
    }

    public void addLast(E e){
        Node  newNode = createANewNode(e);
        Node temp = End;
        End = newNode;
        newNode.Previous = temp;
        temp.Next = newNode;
        Size++;
    }

    public void clear(){
        Start = End = null;
        Size=0;
    }

    private Node createANewNode(E e) {
        Node node = new Node(e);
        return  node;
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

    public E remove(){
        Node temp = null;
        // check if atleast one element is present in the list
        if (size() > 0){
            temp = Start;
            Start = Start.Next;
            Start.Previous = null;
            Size--;
        }

        return (E) temp.Data;
    }

    public void add(int index, E e) {
        // check if the index is in the range of the
        if (index == 0){
            addFirst(e);
        }
        else if(index == size()-1){
            addLast(e);
        }
        else {

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

    public E element(){
        return (E) Start.Data;
    }

}
