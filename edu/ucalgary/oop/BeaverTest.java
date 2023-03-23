package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

public class BeaverTest {

    @Test
    public void testConstructor() {
        // Test valid input
        Beaver beaver = new Beaver(1, "Benny", "BEAVER", "DIURNAL", false);
        assertEquals(1, beaver.getAnimalID());
        assertEquals("Benny", beaver.getAnimalName());
        assertEquals(AnimalType.COYOTE, beaver.getAnimalType());
        assertEquals(FeedingType.DIURNAL, beaver.getAnimalFeedingType());
        assertFalse(beaver.getOrphaned());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorBadInput() {
        // Test invalid input - orphaned cannot be null, and incorrect feeding type
        Beaver invalidBeaver = new Beaver(2, "Bobby", "Beaver", "herbivore", null);

    }
}
