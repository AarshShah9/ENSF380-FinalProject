package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;
public class BeaverTest {

    @Test
    public void testConstructor() {
        //Test valid input
        Beaver beaver = new Beaver(1, "Benny", "BEAVER", "CREPUSCULAR", false);
        assertEquals(1, beaver.getAnimalID());
        assertEquals("Benny", beaver.getAnimalName());
        assertEquals(AnimalType.COYOTE, beaver.getAnimalType());
        assertEquals(FeedingType.CREPUSCULAR, beaver.getAnimalFeedingType());
        assertFalse(beaver.getOrphaned());
    }

    @Test
    public void testConstructorBadInput() {
        // Test invalid input - orphaned cannot be null
        try {
            Beaver invalidBeaver = new Beaver(2, "Bobby", "Beaver", "herbivore", null);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }
}
