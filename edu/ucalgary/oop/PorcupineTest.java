package edu.ucalgary.oop;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Define a class called "PorcupineTest" that tests the "Porcupine" class
 * 
 * @version 2.0
 * @author Aarsh Shah: (UCID: 30150079) - William Fraser: (UCID: 30158991) -
 *         Sarim Sheikh: (UCID: 30143892) - Nicola Savino (UCID: 30129329)
 * @date 2023-04-05
 */
public class PorcupineTest {

    /**
     * Test the constructor of the "Porcupine" class
     */
    @Test
    public void testConstructorGoodData() {
        // create a new porcupine object
        Porcupine porcupine = new Porcupine(1, "Tony");

        // check all the attributes of the porcupine object to ensure they are correct
        assertEquals("The wrong Animal ID was returned", 1, (int) porcupine.getAnimalID());
        assertEquals("The wrong Animal Name was returned", "Tony", porcupine.getAnimalName());
        assertEquals("The wrong Animal Type was returned", AnimalType.PORCUPINE, porcupine.getAnimalType());
        assertEquals("The wrong Animal Feeding Type was returned", FeedingType.CREPUSCULAR,
                porcupine.getAnimalFeedingType());
        assertFalse("The animal should not be set as Orphaned", porcupine.getOrphaned());
    }

    /**
     * Test the constructor of the "Porcupine" class with invalid input
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorBadData() {
        // Test invalid input - feeding type not capitalized
        Porcupine invalidPorcupine = new Porcupine(2, "");
    }
}
