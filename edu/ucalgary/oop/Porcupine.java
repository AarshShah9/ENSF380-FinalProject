package edu.ucalgary.oop;

/**
 * Define a class called "Porcupine" that extends the abstract class "Animal"
 * 
 * @version 2.0
 * @author Aarsh Shah: (UCID: 30150079) - William Fraser: (UCID: 30158991) -
 *         Sarim Sheikh: (UCID: 30143892) - Nicola Savino (UCID: 30129329)
 * @date 2023-04-05
 */
public class Porcupine extends Animal {
    /**
     * Constructor that takes an "id" and "animalName" parameter and throws an
     * exception if the inputs are invalid
     * 
     * @param id         The unique identifier of the animal
     * @param animalName The name of the animal
     */
    public Porcupine(int id, String animalName) throws IllegalArgumentException {
        // Call the constructor of the parent class "Animal" with additional parameters
        // specific to a "Porcupine"
        super(id, animalName, AnimalType.PORCUPINE, 5, 0, 10, FeedingType.CREPUSCULAR);
    }

}
