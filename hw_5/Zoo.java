/**
 * Zoo.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

import java.util.Vector;
import java.lang.Math;
import java.util.Random;

/**
 *
 * This program creates a vector using Animal class to create objects of various animal species and
 * use these objects to print information regarding the animal with the use of inheritance, abstract
 * classes and intefaces.
 *
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

public class Zoo {

    /**
     * The main Program
     *
     * @params  args     command Line arguments(ignored)
     */
    public static void main(String args[]){
        // Vector to store all animals
        Vector<Animal> animals = new Vector<Animal>();

        // All species like Tiger, Giraffe, Bear, etc. have Animal
        // as their super class and hence their constructor can be
        // used to create and store object of Animals in the vector.
        animals.add(new Tiger("richard parker","Denver"));
        animals.add(new Tiger("ajoba","new york"));
        animals.add(new Lion("simba","rochester"));
        animals.add(new Gazelle("Gaza","Herd"));
        animals.add(new Giraffe("GG","Herd"));
        animals.add(new PolarBear("Bageera","Cave"));
        animals.add(new Alpaca("Apli","Alps"));
        animals.add(new Aardvark("rakhshak","rack"));


        // Loop to run through all animals in the animals Vector.
        for (Animal animal: animals){
            // Calls whoAreYou method of Animals class using current object
            // in the vector to print info about the animal.
            System.out.println(animal.whoAreYou());

            // Calls AreYouHungry method of Animals class to check if animal
            // is hungry
            if(animal.AreYouHungry() == "Yes") {
                // We use instanceof to check which species the animal belongs
                // to and accordingly send it to hunt or graze.
                if (animal instanceof Tiger) {
                    ((Tiger) animal).goHunt();
                }
                if (animal instanceof Giraffe) {
                    ((Giraffe) animal).goGraze();
                }
                if (animal instanceof PolarBear) {
                    // Random value b/w 0 and 1 for mood of bear to graze or hunt
                    int mood = new Random().nextInt(2);
                    if (mood == 0) {
                        ((PolarBear) animal).goHunt();
                    } else {
                        ((PolarBear) animal).goGraze();
                    }
                }
            }
            else {
                System.out.println("I am not hungry");
            }

            // Calls AreYouHome method of Animals class to check if animal is at
            // it's home.
            if(animal.AreYouHome() == "No") {
                System.out.println("I am not home");
                // Send animal home by calling goHome method of Animal class.
                System.out.println(animal.goHome());
            }
            else {
                System.out.println("I am home");
            }
        }
    }
}
