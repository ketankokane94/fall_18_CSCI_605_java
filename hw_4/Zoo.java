import java.util.Vector;

public class Zoo {
    public static void main(String args[]){
        Vector<Animal> animals = new Vector<Animal>();
        animals.add(new Tiger("Ketya",18));
        animals.add(new Tiger("ajoba",90));
        animals.add(new Lion("Simba",-1));
        animals.add(new Giraffe("GG",0));
        for (Animal animal: animals){
            System.out.println(animal.whoAreYou() );
        }
    }
}
