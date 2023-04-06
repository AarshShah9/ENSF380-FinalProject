package edu.ucalgary.oop;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Define a class called "RaccoonTest" that tests the "Raccoon" class
 * 
 * @version 2.0
 * @author Aarsh Shah: (UCID: 30150079) - William Fraser: (UCID: 30158991) -
 *         Sarim Sheikh: (UCID: 30143892) - Nicola Savino (UCID: 30129329)
 * @date 2023-04-05
 */
public class RaccoonTest {

    /**
     * Test the constructor of the "Raccoon" class
     */
    @Test
    public void testConstructorGoodData() {
        // create a new raccoon object
        Raccoon raccoon = new Raccoon(1, "Tony");

        // check all the attributes of the raccoon object to ensure they are correct
        assertEquals("The wrong Animal ID was returned", 1, (int) raccoon.getAnimalID());
        assertEquals("The wrong Animal Name was returned", "Tony", raccoon.getAnimalName());
        assertEquals("The wrong Animal Type was returned", AnimalType.RACCOON, raccoon.getAnimalType());
        assertEquals("The wrong Animal Feeding Type was returned", FeedingType.NOCTURNAL,
                raccoon.getAnimalFeedingType());
        assertFalse("The animal should not be set as Orphaned", raccoon.getOrphaned());
    }

    /**
     * Test the constructor of the "Raccoon" class with invalid input
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorBadData() {
        // Test invalid input - feeding type incorrect
        Raccoon invalidRaccoon = new Raccoon(2, "");

    }
}
