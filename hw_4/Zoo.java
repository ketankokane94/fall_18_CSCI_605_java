//package parallelProject.hw_4;

import java.util.Vector;
import java.lang.Math;

public class Zoo {
    public static void main(String args[]){
        Vector<Animal> animals = new Vector<Animal>();
        animals.add(new Tiger("richard parker","Denver"));
        animals.add(new Tiger("ajoba","new york"));
        animals.add(new Lion("simba","rochester"));
        animals.add(new Gazelle("Gaza","Herd"));
        animals.add(new Giraffe("GG","Herd"));
        animals.add(new Bear("Bageera","Cave"));

        for (Animal animal: animals){
            System.out.println(animal.whoAreYou());
            if(animal.AreYouHungry() == "Yes") {
                if (animal instanceof Tiger) {
                    ((Tiger) animal).goHunt();
                }
                if (animal instanceof Giraffe) {
                    ((Giraffe) animal).goGraze();
                }
                if (animal instanceof Bear) {
                    int mood = ((int) ((Math.random()) * 100) % 2);
                    if (mood == 0) {
                        ((Bear) animal).goHunt();
                    } else {
                        ((Bear) animal).goGraze();
                    }
                }
            }
            else {
                System.out.println("I am not hungry");
            }
            if(animal.AreYouHome() == "No") {
                System.out.println("I am not home");
                System.out.println(animal.goHome());
            }
            else {
                System.out.println("I am home");
            }
        }
    }
}
