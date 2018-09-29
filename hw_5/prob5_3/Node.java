public class Node {
    Node Previous;
    Object Data;
    Node Next;

    public Node(Object data) {
        Data = data;
    }

    public Node(Node previous, Object data) {
        Previous = previous;
        Data = data;
    }

    public Node(Object data, Node next) {
        Data = data;
        Next = next;
    }

    public Node(Node previous, Object data, Node next) {
        Previous = previous;
        Data = data;
        Next = next;
    }
}
