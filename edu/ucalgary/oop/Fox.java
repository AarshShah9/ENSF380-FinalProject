package edu.ucalgary.oop;

public class Fox extends Animal {

    public Fox(int id, String animalName)
            throws IllegalArgumentException {
        super(id, animalName, AnimalType.FOX, 5, 5, 5, FeedingType.NOCTURNAL);
        // porpcupines are crepuscular
        // foxes & racoons are noctural
        // beavers are diurnal
    }

}
