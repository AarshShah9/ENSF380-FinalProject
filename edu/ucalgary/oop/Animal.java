package edu.ucalgary.oop;

public abstract class Animal {
    protected final Integer ANIMAL_ID;
    protected final String ANIMAL_NAME;
    protected final AnimalType ANIMAL_TYPE;
    protected final FeedingType ANIMAL_FEEDING_TYPE;
    protected final Boolean ORPHANED;
    public static final Integer FEED_WINDOW = 3;
    protected final Integer FEED_TIME;
    protected final Integer FEED_PREP_TIME;
    protected final Integer CLEAN_TIME;

    public Animal(int id, String animalName, AnimalType animalType, Integer feedTime, Integer feedPrepTime,
            Integer cleanTime, FeedingType feedingType)
            throws IllegalArgumentException {
        if (animalName == null || animalName.equals("") || animalType == null || feedTime == null
                || feedPrepTime == null
                || cleanTime == null || feedingType == null)
            throw new IllegalArgumentException("Invalid input to Animal constructor");

        this.ANIMAL_ID = id;
        this.ANIMAL_NAME = animalName;
        this.ANIMAL_TYPE = animalType;
        this.ANIMAL_FEEDING_TYPE = feedingType;
        this.FEED_TIME = feedTime;
        this.FEED_PREP_TIME = feedPrepTime;
        this.CLEAN_TIME = cleanTime;

        // TODO - add a regex check to see if the animal is orphaned or not
        if (animalName.contains("Annie")) {
            this.ORPHANED = true;
        } else
            this.ORPHANED = false;

    }

    public Integer getAnimalID() {
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

    public Integer getFeedTime() {
        return this.FEED_TIME;
    }

    public Integer getFeedPrepTime() {
        return this.FEED_PREP_TIME;
    }

    public Integer getCleanTime() {
        return this.CLEAN_TIME;
    }

}
