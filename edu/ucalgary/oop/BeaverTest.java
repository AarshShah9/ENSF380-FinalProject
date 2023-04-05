package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Define a class called "BeaverTest" that tests the "Beaver" class
 * 
 * @version 2.0
 * @author Aarsh @ Nick
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
        assertEquals(1, (int) beaver.getAnimalID());
        assertEquals("Benny", beaver.getAnimalName());
        assertEquals(AnimalType.BEAVER, beaver.getAnimalType());
        assertEquals(FeedingType.DIURNAL, beaver.getAnimalFeedingType());
        assertFalse(beaver.getOrphaned());
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
