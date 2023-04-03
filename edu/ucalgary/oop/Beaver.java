package edu.ucalgary.oop;

public class Beaver extends Animal {
    // public static final LocalTime FEED_TIME = LocalTime.of(0, 5, 0, 0);
    // public static final LocalTime FEED_PREP_TIME = LocalTime.of(0, 0, 0, 0);
    // public static final LocalTime CLEAN_TIME = LocalTime.of(0, 5, 0, 0);

    public Beaver(int id, String animalName)
            throws IllegalArgumentException {
        super(id, animalName, AnimalType.BEAVER, 5, 0, 5, FeedingType.DIURNAL);
    }

}
