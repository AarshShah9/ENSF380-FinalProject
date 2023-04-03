package edu.ucalgary.oop;

public class Raccoon extends Animal {
    public Raccoon(int id, String animalName)
            throws IllegalArgumentException {
        super(id, animalName, AnimalType.RACCOON, 5, 0, 5, FeedingType.NOCTURNAL);
    }

}
