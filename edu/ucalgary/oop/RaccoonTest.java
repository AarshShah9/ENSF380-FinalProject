package edu.ucalgary.oop;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Define a class called "RaccoonTest" that tests the "Raccoon" class
 * 
 * @version 2.0
 * @author Aarsh @ Nick
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
        assertEquals(1, (int) raccoon.getAnimalID());
        assertEquals("Tony", raccoon.getAnimalName());
        assertEquals(AnimalType.RACCOON, raccoon.getAnimalType());
        assertEquals(FeedingType.NOCTURNAL, raccoon.getAnimalFeedingType());
        assertTrue(raccoon.getOrphaned());
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
