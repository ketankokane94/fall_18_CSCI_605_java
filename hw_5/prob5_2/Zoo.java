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
        Vector<LivingBeing> beings = new Vector<>();

        // All species like Tiger, Giraffe, Bear, etc. have Animal
        // as their super class and hence their constructor can be
        // used to create and store object of Animals in the vector.
        beings.add(new Tiger("richard parker","Denver"));
        beings.add(new Tiger("ajoba","new york"));
        beings.add(new Lion("simba","rochester"));
        beings.add(new Gazelle("Gaza","Herd"));
        beings.add(new Giraffe("GG","Herd"));
        beings.add(new PolarBear("Bageera","Cave"));
        beings.add(new Alpaca("Apli","Alps"));
        beings.add(new Aardvark("rakhshak","rack"));
        beings.add(new PalmTree("Flower Pot"));
        beings.add(new PalmTree("Tree"));
        beings.add(new SchoolOfGoldFish("Maasa", "Aquarium", 45));


        // Loop to run through all animals in the animals Vector.
        for (LivingBeing being: beings){
            // Calls whoAreYou method of LivingBeing class using current object
            // in the vector to print info about the being.
            System.out.println(being.whoAreYou());

            if(being instanceof Animal) {

                Animal animal = (Animal) being;

                // Print position of animal.
                System.out.println("I am " + animal.getName() + " " + being.showCurrentPosition());

                // Calls AreYouHungry method of Animals class to check if animal
                // is hungrya
                if (animal.AreYouHungry() == "Yes") {
                    // We use instanceof to check which species the animal belongs
                    // to and accordingly send it to hunt or graze.
                    if (animal instanceof Carnivore) {
                        ((Carnivore) animal).goHunt();
                    } else if (animal instanceof Herbivore) {
                        ((Herbivore) animal).goGraze();
                    } else if (animal instanceof Omnivore) {
                        ((Omnivore) animal).goEat();
                    }
                } else {
                    System.out.println("I am not hungry");
                }
                // Calls AreYouHome method of Animals class to check if animal is at
                // it's home.
                if (animal.AreYouHome() == "No") {
                    System.out.println("I am not home");
                    // Send animal home by calling goHome method of Animal class.
                    System.out.println(animal.goHome());
                } else {
                    System.out.println("I am home");
                }

                int random_x = new Random().nextInt(101);
                int random_y = new Random().nextInt( 101);

                // Move animal to new position.
                animal.move(random_x, random_y);
            }
            else if(being instanceof Plant) {

                Plant plant = (Plant) being;

                // Print position of animal.
                System.out.println("I am " + plant.getSpecies() + " " + being.showCurrentPosition());

                int random_x = new Random().nextInt(101);
                int random_y = new Random().nextInt( 101);

                // Move plant to new position.
                plant.move(random_x, random_y);
            }
        }
    }
}
