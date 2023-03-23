package edu.ucalgary.oop;

import org.junit.Test;

import static org.junit.Assert.*;

public class FoxTest {

    @Test
    public void testConstructorGoodData() {
        // Test valid input
        Fox fox = new Fox(1, "Tony", "FOX", "NOCTURNAL", true);
        assertEquals(1, fox.getAnimalID());
        assertEquals("Tony", fox.getAnimalName());
        assertEquals(AnimalType.FOX, fox.getAnimalType());
        assertEquals(FeedingType.NOCTURNAL, fox.getAnimalFeedingType());
        assertTrue(fox.getOrphaned());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorBadData() {
        // Test invalid input - feeding type not capitalized, incorrect animal type
        Fox invalidCoyote = new Fox(2, "Bobby", "FOX", "random", null);

    }

    @Test
    public void testGetAnimalID() {
        Fox fox = new Fox(1, "Tony", "FOX", "NOCTURNAL", true);
        assertEquals(1, fox.getAnimalID());
    }

    @Test
    public void testGetAnimalName() {
        Fox fox = new Fox(1, "Tony", "FOX", "NOCTURNAL", true);
        assertEquals("Tony", fox.getAnimalName());
    }

    @Test
    public void testGetAnimalType() {
        Fox fox = new Fox(1, "Tony", "FOX", "NOCTURNAL", true);
        assertEquals(AnimalType.FOX, fox.getAnimalType());
    }

    @Test
    public void testGetAnimalFeedingType() {
        Fox fox = new Fox(1, "Tony", "FOX", "NOCTURNAL", true);
        assertEquals(FeedingType.NOCTURNAL, fox.getAnimalFeedingType());
    }

    @Test
    public void testGetOrphaned() {
        Fox fox = new Fox(1, "Tony", "FOX", "NOCTURNAL", true);
        assertTrue(fox.getOrphaned());
    }

}
