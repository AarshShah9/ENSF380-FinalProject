package edu.ucalgary.oop;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Define a class called "FoxTest" that tests the "Fox" class as well as all the
 * inherited methods from the abstract class "Animal"
 * 
 * @version 2.0
 * @author Aarsh
 * @date 2023-04-05
 */
public class FoxTest {

    /**
     * Test the constructor of the "Fox" class
     */
    @Test
    public void testConstructorGoodData() {
        // Create a new fox object and check if the attributes are correct
        Fox fox = new Fox(1, "Tony");
        assertEquals(1, (int) fox.getAnimalID());
        assertEquals("Tony", fox.getAnimalName());
        assertEquals(AnimalType.FOX, fox.getAnimalType());
        assertEquals(FeedingType.NOCTURNAL, fox.getAnimalFeedingType());
        assertFalse(fox.getOrphaned());
    }

    /**
     * Test the constructor of the "Fox" class with invalid input
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorBadData() {
        Fox invalidCoyote = new Fox(2, "");

    }

    /**
     * Test the getAnimalID method from the Animal class
     */
    @Test
    public void testGetAnimalID() {
        // Test getAnimalID getter method by creating a new fox object and checking if
        // the ID is correct
        Fox fox = new Fox(1, "Tony");
        assertEquals(1, (int) fox.getAnimalID());
    }

    /**
     * Test the getAnimalName method from the Animal class
     */
    @Test
    public void testGetAnimalName() {
        // Test getAnimalName getter method by creating a new fox object and checking if
        // the name is correct
        Fox fox = new Fox(1, "Tony");
        assertEquals("Tony", fox.getAnimalName());
    }

    /**
     * Test the getAnimalType method from the Animal class
     */
    @Test
    public void testGetAnimalType() {
        // Test getAnimalType getter method by creating a new fox object and checking if
        // the type is correct
        Fox fox = new Fox(1, "Tony");
        assertEquals(AnimalType.FOX, fox.getAnimalType());
    }

    /**
     * Test the getAnimalFeedingType method from the Animal class
     */
    @Test
    public void testGetAnimalFeedingType() {
        // Test getAnimalFeedingType getter method by creating a new fox object and
        // checking if the feeding type is correct
        Fox fox = new Fox(1, "Tony");
        assertEquals(FeedingType.NOCTURNAL, fox.getAnimalFeedingType());
    }

    /**
     * Test the getOrphaned method from the Animal class
     */
    @Test
    public void testGetOrphaned() {
        // Test getOrphaned getter method by creating a new fox object and checking if
        // the orphaned status is correct
        Fox fox = new Fox(1, "Tony");
        assertFalse(fox.getOrphaned());
    }

    /**
     * Test the getFeedTime method from the Animal class
     */
    @Test
    public void testGetFeedTime() {
        // Test getFeedTime getter method by creating a new fox object and checking if
        // the feed time is correct
        Fox fox = new Fox(1, "Tony");
        assertEquals(5, (int) fox.getFeedTime());
    }

    /**
     * Test the getFeedPrepTime method from the Animal class
     */
    @Test
    public void testGetFeedPrepTime() {
        // Test getFeedPrepTime getter method by creating a new fox object and checking
        // if the feed prep time is correct
        Fox fox = new Fox(1, "Tony");
        assertEquals(5, (int) fox.getFeedPrepTime());
    }

    /**
     * Test the getCleanTime method from the Animal class
     */
    @Test
    public void testGetCleanTime() {
        // Test getCleanTime getter method by creating a new fox object and checking if
        // the clean time is correct
        Fox fox = new Fox(1, "Tony");
        assertEquals(5, (int) fox.getCleanTime());
    }

    @Test
    public void testSetOrphaned() {
        // Test setOrphaned setter method by creating a new fox object and checking if
        // the orphaned status is correct
        Fox fox = new Fox(1, "Tony");
        fox.setOrphaned(true);
        assertTrue(fox.getOrphaned());
    }
}
