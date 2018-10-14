public class test {

    public static void main(String args[]){
        MyHashSet<String> myHashSet = new MyHashSet();
        System.out.println("myHashSet.isEmpty() = "+myHashSet.isEmpty());
        System.out.println("Add Ketan "+ myHashSet.add("Ketan"));
        System.out.println("Add Kokane "+myHashSet.add("kokane"));
        System.out.println("Contains "+ myHashSet.contains("Ketan"));
        System.out.println("myHashSet.size() = " + myHashSet.size());
        System.out.println("myHashSet.isEmpty() = "+myHashSet.isEmpty());
        myHashSet.clear();
        System.out.println("After doing myHashSet.clear()");
        System.out.println("myHashSet.size() = " + myHashSet.size());
        System.out.println("myHashSet.isEmpty() = "+myHashSet.isEmpty());
        System.out.println(myHashSet.hashCode());
        System.out.println("remove should fail because set is empty");
        System.out.println("myHashSet.remove  (Ketan)"+myHashSet.remove("Ketan"));
        System.out.println("myHashSet.size() = " + myHashSet.size());
        System.out.println("Add Ketan "+ myHashSet.add("Ketan"));
        System.out.println("myHashSet.remove  (Ketan)"+myHashSet.remove("Ketan"));
        System.out.println("myHashSet.size() = " + myHashSet.size());
        System.out.println(myHashSet.add(null));
        System.out.println("myHashSet.size() = " + myHashSet.size());
        System.out.println();
        System.out.println();
       // System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();
       // System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();


    }
}
