package edu.ucalgary.oop;

public class Porcupine extends Animal {

    public Porcupine(int id, String animalName)
            throws IllegalArgumentException {
        super(id, animalName, AnimalType.PORCUPINE, 5, 0, 10, FeedingType.CREPUSCULAR);
    }

}
