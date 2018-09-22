package parallelProject.hw_4;

import java.util.Vector;

public class Zoo {
    public static void main(String args[]){
        Vector<Animal> animals = new Vector<Animal>();
        animals.add(new Tiger("richard parker","Denver"));
        animals.add(new Tiger("ajoba","new york"));
        animals.add(new Lion("simba","rochester"));
        animals.add(new Gazelle("Gaza","Herd"));
        animals.add(new Giraffe("GG","Herd"));

        for (Animal animal: animals){
            System.out.println(animal.whoAreYou());
            if (animal instanceof Tiger){
                ((Tiger) animal).goHunt();
            }
            if (animal instanceof Giraffe){
                ((Giraffe) animal).goGraze();
            }
            System.out.println(animal.goHome());
        }
    }
}
