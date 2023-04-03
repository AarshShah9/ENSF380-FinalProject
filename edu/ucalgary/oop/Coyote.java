package edu.ucalgary.oop;


public class Coyote extends Animal {

    public Coyote(int id, String animalName)
            throws IllegalArgumentException {
        super(id, animalName, AnimalType.COYOTE, 5, 10, 5, FeedingType.CREPUSCULAR);
    }

}
