package edu.ucalgary.oop;

import org.junit.Test;

import static org.junit.Assert.*;

public class RaccoonTest {
    @Test
    public void testConstructorGoodData() {
        // Test valid input
        Raccoon raccoon = new Raccoon(1, "Tony", "RACCOON", "NOCTURNAL", true);
        assertEquals(1, raccoon.getAnimalID());
        assertEquals("Tony", raccoon.getAnimalName());
        assertEquals(AnimalType.RACCOON, raccoon.getAnimalType());
        assertEquals(FeedingType.NOCTURNAL, raccoon.getAnimalFeedingType());
        assertTrue(raccoon.getOrphaned());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorBadData() {
        // Test invalid input - feeding type incorrect
        Raccoon invalidRaccoon = new Raccoon(2, "Bobby", "RACCOON", "incorrectfeedtype", null);

    }
}
