public class Main {
    public static void main(String args[]){

        MyLinkedList<String> ll = new MyLinkedList();

        ll.addE("Hello"); //0
        ll.addE("World");//1
        ll.addE("Ketan");//0
        ll.addE("kokane");//0
        ll.add(1,"balbhim");
        ll.remove(1);
        System.out.println(ll);

    }
}
