package edu.ucalgary.oop;

import java.time.LocalTime;

public abstract class Animal {
    private int ANIMAL_ID;
    private String ANIMAL_NAME;
    private AnimalType ANIMAL_TYPE;
    private FeedingType ANIMAL_FEEDING_TYPE;
    private Boolean ORPHANED;
    public static final LocalTime FEED_WINDOW = LocalTime.of(3, 0, 0);

    public Animal(int id, String animalName, String animalType, String animalFeedingType, Boolean orphaned)
            throws IllegalArgumentException {
        // Implement this constructor

    }

    public int getAnimalID() {
        return this.ANIMAL_ID;
    }

    public String getAnimalName() {
        return this.ANIMAL_NAME;
    }

    public AnimalType getAnimalType() {
        return this.ANIMAL_TYPE;
    }

    public FeedingType getAnimalFeedingType() {
        return this.ANIMAL_FEEDING_TYPE;
    }

    public Boolean getOrphaned() {
        return this.ORPHANED;
    }

}
