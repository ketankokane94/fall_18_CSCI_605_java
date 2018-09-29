public class Node<E> {
    Node Previous;
    E Data;
    Node Next;

    public Node(E data) {
        Data = data;
    }

    public Node(Node previous, E data) {
        Previous = previous;
        Data = data;
    }

    public Node(E data, Node next) {
        Data = data;
        Next = next;
    }

    public Node(Node previous, E data, Node next) {
        Previous = previous;
        Data = data;
        Next = next;
    }
}
