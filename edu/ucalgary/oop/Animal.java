package edu.ucalgary.oop;

import java.time.LocalTime;

public abstract class Animal {
    private final int ANIMAL_ID;
    private final String ANIMAL_NAME;
    private final AnimalType ANIMAL_TYPE;
    private final FeedingType ANIMAL_FEEDING_TYPE;
    private final Boolean ORPHANED;
    public static final LocalTime FEED_WINDOW = LocalTime.of(3, 0, 0);

    public Animal(int id, String animalName, String animalType, String animalFeedingType, Boolean orphaned)
            throws IllegalArgumentException {
        // Implement this constructor
        // Change this up, it's just a placeholder to avoid final variable not
        // initialized error
        this.ANIMAL_ID = id;
        this.ANIMAL_NAME = animalName;
        this.ANIMAL_TYPE = AnimalType.valueOf(animalType);
        this.ANIMAL_FEEDING_TYPE = FeedingType.valueOf(animalFeedingType);
        this.ORPHANED = orphaned;

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
