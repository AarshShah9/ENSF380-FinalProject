package edu.ucalgary.oop;

public class Beaver extends Animal {

    public Beaver(int id, String animalName)
            throws IllegalArgumentException {
        super(id, animalName, AnimalType.BEAVER, 5, 0, 5, FeedingType.DIURNAL);
    }

}
