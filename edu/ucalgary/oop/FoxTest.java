package edu.ucalgary.oop;

import org.junit.Test;

import static org.junit.Assert.*;

public class FoxTest {
    /**
     * @version 1.0
     * @author Aarsh @ Nick
     * @date 2023-03-22
     */

    @Test
    public void testConstructorGoodData() {
        // Test valid input
        Fox fox = new Fox(1, "Tony");
        assertEquals(1, (int) fox.getAnimalID());
        assertEquals("Tony", fox.getAnimalName());
        assertEquals(AnimalType.FOX, fox.getAnimalType());
        assertEquals(FeedingType.NOCTURNAL, fox.getAnimalFeedingType());
        assertTrue(fox.getOrphaned());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorBadData() {
        Fox invalidCoyote = new Fox(2, "");

    }

    @Test
    public void testGetAnimalID() {
        // Test getAnimalID getter method by creating a new fox object and checking if
        // the ID is correct
        Fox fox = new Fox(1, "Tony");
        assertEquals(1, (int) fox.getAnimalID());
    }

    @Test
    public void testGetAnimalName() {
        // Test getAnimalName getter method by creating a new fox object and checking if
        // the name is correct
        Fox fox = new Fox(1, "Tony");
        assertEquals("Tony", fox.getAnimalName());
    }

    @Test
    public void testGetAnimalType() {
        // Test getAnimalType getter method by creating a new fox object and checking if
        // the type is correct
        Fox fox = new Fox(1, "Tony");
        assertEquals(AnimalType.FOX, fox.getAnimalType());
    }

    @Test
    public void testGetAnimalFeedingType() {
        // Test getAnimalFeedingType getter method by creating a new fox object and
        // checking if the feeding type is correct
        Fox fox = new Fox(1, "Tony");
        assertEquals(FeedingType.NOCTURNAL, fox.getAnimalFeedingType());
    }

    @Test
    public void testGetOrphaned() {
        // Test getOrphaned getter method by creating a new fox object and checking if
        // the orphaned status is correct
        Fox fox = new Fox(1, "Tony");
        assertTrue(fox.getOrphaned());
    }

}
