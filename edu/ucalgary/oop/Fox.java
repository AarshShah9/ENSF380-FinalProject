package edu.ucalgary.oop;

import java.time.LocalTime;

public class Fox extends Animal {
    // public static final LocalTime FEED_TIME = LocalTime.of(0, 5, 0, 0);
    // public static final LocalTime FEED_PREP_TIME = LocalTime.of(0, 5, 0, 0);
    // public static final LocalTime CLEAN_TIME = LocalTime.of(0, 5, 0, 0);

    public Fox(int id, String animalName, String animalType)
            throws IllegalArgumentException {
        super(id, animalName, animalType, 5, 5, 5, FeedingType.NOCTURNAL);
        // porpcupines are crepuscular
        // foxes & racoons are noctural
        // beavers are diurnal
    }

}
