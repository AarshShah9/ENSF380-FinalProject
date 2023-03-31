package edu.ucalgary.oop;

import java.time.LocalTime;

public abstract class Animal {
    protected final int ANIMAL_ID;
    protected final String ANIMAL_NAME;
    protected final AnimalType ANIMAL_TYPE;
    protected FeedingType ANIMAL_FEEDING_TYPE;
    protected final Boolean ORPHANED;
    public static final LocalTime FEED_WINDOW = LocalTime.of(3, 0, 0);

    public Animal(int id, String animalName, String animalType)
            throws IllegalArgumentException {
        this.ANIMAL_ID = id;
        this.ANIMAL_NAME = animalName;
        this.ANIMAL_TYPE = AnimalType.valueOf(animalType);

        // TODO - add a regex check to see if the animal is orphaned or not
        if (animalName.contains("Annie")) {
            this.ORPHANED = true;
        } else
            this.ORPHANED = false;

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
