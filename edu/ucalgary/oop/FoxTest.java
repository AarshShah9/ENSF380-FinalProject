package edu.ucalgary.oop;

import org.junit.Test;

import static org.junit.Assert.*;

public class FoxTest {

    @Test
    public void testConstructor() {
        //Test valid input
        Fox fox = new Fox(1, "Tony", "FOX", "DIURNAL", true);
        assertEquals(1, fox.getAnimalID());
        assertEquals("Tony", fox.getAnimalName());
        assertEquals(AnimalType.FOX, fox.getAnimalType());
        assertEquals(FeedingType.DIURNAL, fox.getAnimalFeedingType());
        assertTrue(fox.getOrphaned());
    }

    @Test
    public void testConstructorBadInput() {
        // Test invalid input - feeding type not capitalized
        try {
            Fox invalidCoyote = new Fox(2, "Bobby", "FOX", "diurnal", null);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

}
