package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Define a class called "BeaverTest" that tests the "Beaver" class
 * 
 * @version 2.0
 * @author Aarsh Shah: (UCID: 30150079) - William Fraser: (UCID: 30158991) -
 *         Sarim Sheikh: (UCID: 30143892) - Nicola Savino (UCID: 30129329)
 * @date 2023-04-05
 */
public class BeaverTest {

    /**
     * Test the constructor of the "Beaver" class
     */
    @Test
    public void testConstructor() {
        // create a new beaver object
        Beaver beaver = new Beaver(1, "Benny");

        // check all the attributes of the beaver object to ensure they are correct
        assertEquals("The wrong Animal ID was returned", 1, (int) beaver.getAnimalID());
        assertEquals("The wrong Animal Name was returned", "Benny", beaver.getAnimalName());
        assertEquals("The wrong Animal Type was returned", AnimalType.BEAVER, beaver.getAnimalType());
        assertEquals("The wrong Animal Feeding Type was returned", FeedingType.DIURNAL, beaver.getAnimalFeedingType());
        assertFalse("The animal should not be set as Orphaned", beaver.getOrphaned());
    }

    /**
     * Test the constructor of the "Beaver" class with invalid input
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorBadInput() {
        // Test invalid input - orphaned cannot be null, and incorrect feeding type
        Beaver invalidBeaver = new Beaver(2, "");

    }
}
