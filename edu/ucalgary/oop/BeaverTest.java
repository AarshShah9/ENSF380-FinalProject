package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

public class BeaverTest {
    /**
     * @version 1.0
     * @author Aarsh @ Nick
     * @date 2023-03-22
     */

    @Test
    public void testConstructor() {
        // Test valid input
        Beaver beaver = new Beaver(1, "Benny");
        assertEquals(1, (int) beaver.getAnimalID());
        assertEquals("Benny", beaver.getAnimalName());
        assertEquals(AnimalType.BEAVER, beaver.getAnimalType());
        assertEquals(FeedingType.DIURNAL, beaver.getAnimalFeedingType());
        assertFalse(beaver.getOrphaned());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorBadInput() {
        // Test invalid input - orphaned cannot be null, and incorrect feeding type
        Beaver invalidBeaver = new Beaver(2, "");

    }
}
