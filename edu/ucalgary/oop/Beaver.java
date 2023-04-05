package edu.ucalgary.oop;

/**
 * Define a class called "Beaver" that extends the abstract class "Animal"
 * 
 * @version 2.0
 * @author Aarsh
 * @date 2023-04-05
 */
public class Beaver extends Animal {
    /**
     * Constructor that takes an "id" and "animalName" parameter and throws an
     * exception if the inputs are invalid
     * 
     * @param id         The unique identifier of the animal
     * @param animalName The name of the animal
     */
    public Beaver(int id, String animalName) throws IllegalArgumentException {
        // Call the constructor of the parent class "Animal" with additional parameters
        // specific to a "Beaver"
        super(id, animalName, AnimalType.BEAVER, 5, 0, 5, FeedingType.DIURNAL);
    }

}
