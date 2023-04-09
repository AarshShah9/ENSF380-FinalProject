/**
 * An abstract class representing an animal. Contains properties 
 * and methods that are common among all animals.
 * 
 * @version 2.0
 * @author Aarsh Shah: (UCID: 30150079) - William Fraser: (UCID: 30158991) - Sarim Sheikh: (UCID: 30143892) - Nicola Savino (UCID: 30129329)
 * @date 2023-04-05
 */
package edu.ucalgary.oop;

public abstract class Animal {
    protected final Integer ANIMAL_ID; // Unique ID of the animal
    protected final String ANIMAL_NAME; // Name of the animal
    protected final AnimalType ANIMAL_TYPE; // Type of the animal
    protected final FeedingType ANIMAL_FEEDING_TYPE; // Feeding type of the animal
    protected Boolean orphaned; // True if the animal is orphaned
    public static final Integer FEED_WINDOW = 3; // The time window within which an animal should be fed
    protected final Integer FEED_TIME; // The time at which the animal should be fed
    protected final Integer FEED_PREP_TIME; // The time required to prepare the animal's food
    protected final Integer CLEAN_TIME; // The time required to clean the animal's enclosure

    /**
     * Constructor to create a new Animal object.
     * 
     * @param id           the unique ID of the animal
     * @param animalName   the name of the animal
     * @param animalType   the type of the animal
     * @param feedTime     the time at which the animal should be fed
     * @param feedPrepTime the time required to prepare the animal's food
     * @param cleanTime    the time required to clean the animal's enclosure
     * @param feedingType  the feeding type of the animal
     * @throws IllegalArgumentException if any of the inputs are invalid
     */
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
        this.orphaned = false;
    }

    /**
     * Getter method to get the unique ID of the animal.
     * 
     * @return the unique ID of the animal
     */
    public Integer getAnimalID() {
        return this.ANIMAL_ID;
    }

    /**
     * Getter method to get the name of the animal.
     * 
     * @return the name of the animal
     */
    public String getAnimalName() {
        return this.ANIMAL_NAME;
    }

    /**
     * Getter method to get the type of the animal.
     * 
     * @return the type of the animal
     */
    public AnimalType getAnimalType() {
        return this.ANIMAL_TYPE;
    }

    /**
     * Getter method to get the feeding type of the animal.
     * 
     * @return the feeding type of the animal
     */
    public FeedingType getAnimalFeedingType() {
        return this.ANIMAL_FEEDING_TYPE;
    }

    /**
     * Getter method to check if the animal is orphaned.
     * 
     * @return true if the animal is orphaned, false otherwise
     */
    public Boolean getOrphaned() {
        return this.orphaned;
    }

    /**
     * 
     * Returns the feeding time for this animal in minutes.
     * 
     * @return an integer representing the feeding time in minutes
     */
    public Integer getFeedTime() {
        return this.FEED_TIME;
    }

    /**
     * 
     * Returns the feeding preparation time for this animal in minutes.
     * 
     * @return an integer representing the feeding preparation time in minutes
     */
    public Integer getFeedPrepTime() {
        return this.FEED_PREP_TIME;
    }

    /**
     * 
     * Returns the cleaning time for this animal in minutes.
     * 
     * @return an integer representing the cleaning time in minutes
     */
    public Integer getCleanTime() {
        return this.CLEAN_TIME;
    }

    /**
     * 
     * Sets the orphaned status of this animal.
     * 
     * @param orphaned a boolean representing whether the animal is orphaned or not
     */
    public void setOrphaned(Boolean orphaned) {
        this.orphaned = orphaned;
    }

}
