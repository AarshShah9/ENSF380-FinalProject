package edu.ucalgary.oop;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Define a class called "FoxTest" that tests the "Fox" class as well as all the
 * inherited methods from the abstract class "Animal"
 * 
 * @version 2.0
 * @author Aarsh Shah: (UCID: 30150079) - William Fraser: (UCID: 30158991) -
 *         Sarim Sheikh: (UCID: 30143892) - Nicola Savino (UCID: 30129329)
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
        assertEquals("The wrong Animal ID was returned", 1, (int) fox.getAnimalID());
        assertEquals("The wrong Animal Name was returned", "Tony", fox.getAnimalName());
        assertEquals("The wrong Animal Type was returned", AnimalType.FOX, fox.getAnimalType());
        assertEquals("The wrong Animal Feeding Type was returned", FeedingType.NOCTURNAL, fox.getAnimalFeedingType());
        assertFalse("The animal should not be set as Orphaned", fox.getOrphaned());
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
        assertEquals("The getAnimalID method doesn't work as expected", 1, (int) fox.getAnimalID());
    }

    /**
     * Test the getAnimalName method from the Animal class
     */
    @Test
    public void testGetAnimalName() {
        // Test getAnimalName getter method by creating a new fox object and checking if
        // the name is correct
        Fox fox = new Fox(1, "Tony");
        assertEquals("The getAnimalName method doesn't work as expected", "Tony", fox.getAnimalName());
    }

    /**
     * Test the getAnimalType method from the Animal class
     */
    @Test
    public void testGetAnimalType() {
        // Test getAnimalType getter method by creating a new fox object and checking if
        // the type is correct
        Fox fox = new Fox(1, "Tony");
        assertEquals("The getAnimalType method doesn't work as expected", AnimalType.FOX, fox.getAnimalType());
    }

    /**
     * Test the getAnimalFeedingType method from the Animal class
     */
    @Test
    public void testGetAnimalFeedingType() {
        // Test getAnimalFeedingType getter method by creating a new fox object and
        // checking if the feeding type is correct
        Fox fox = new Fox(1, "Tony");
        assertEquals("The getAnimalFeedingType method doesn't work as expected", FeedingType.NOCTURNAL,
                fox.getAnimalFeedingType());
    }

    /**
     * Test the getOrphaned method from the Animal class
     */
    @Test
    public void testGetOrphaned() {
        // Test getOrphaned getter method by creating a new fox object and checking if
        // the orphaned status is correct
        Fox fox = new Fox(1, "Tony");
        assertFalse("The testGetOrphaned method doesn't work as expected", fox.getOrphaned());
    }

    /**
     * Test the getFeedTime method from the Animal class
     */
    @Test
    public void testGetFeedTime() {
        // Test getFeedTime getter method by creating a new fox object and checking if
        // the feed time is correct
        Fox fox = new Fox(1, "Tony");
        assertEquals("The getFeedTime method doesn't work as expected", 5, (int) fox.getFeedTime());
    }

    /**
     * Test the getFeedPrepTime method from the Animal class
     */
    @Test
    public void testGetFeedPrepTime() {
        // Test getFeedPrepTime getter method by creating a new fox object and checking
        // if the feed prep time is correct
        Fox fox = new Fox(1, "Tony");
        assertEquals("The getFeedPrepTime method doesn't work as expected", 5, (int) fox.getFeedPrepTime());
    }

    /**
     * Test the getCleanTime method from the Animal class
     */
    @Test
    public void testGetCleanTime() {
        // Test getCleanTime getter method by creating a new fox object and checking if
        // the clean time is correct
        Fox fox = new Fox(1, "Tony");
        assertEquals("The getCleanTime method doesn't work as expected", 5, (int) fox.getCleanTime());
    }

    @Test
    public void testSetOrphaned() {
        // Test setOrphaned setter method by creating a new fox object and checking if
        // the orphaned status is correct
        Fox fox = new Fox(1, "Tony");
        fox.setOrphaned(true);
        assertTrue("The testSetOrphaned method doesn't work as expected", fox.getOrphaned());
    }
}
