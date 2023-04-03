package edu.ucalgary.oop;

import java.time.LocalTime;

public class Raccoon extends Animal {
    public static final LocalTime FEED_TIME = LocalTime.of(0, 5, 0, 0);
    public static final LocalTime FEED_PREP_TIME = LocalTime.of(0, 0, 0, 0);
    public static final LocalTime CLEAN_TIME = LocalTime.of(0, 5, 0, 0);

    public Raccoon(int id, String animalName, String animalType)
            throws IllegalArgumentException {
        super(id, animalName, animalType, 5, 0, 5, FeedingType.NOCTURNAL);
    }

}
