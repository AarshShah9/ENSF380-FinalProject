package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;
public class CoyoteTest {

    @Test
    public void testConstructor() {
        //Test valid input
        Coyote coyote = new Coyote(1, "Tony", "COYOTE", "DIURNAL", true);
        assertEquals(1, coyote.getAnimalID());
        assertEquals("Tony", coyote.getAnimalName());
        assertEquals(AnimalType.COYOTE, coyote.getAnimalType());
        assertEquals(FeedingType.DIURNAL, coyote.getAnimalFeedingType());
        assertTrue(coyote.getOrphaned());
    }

    @Test
    public void testConstructorBadInput() {
        // Test invalid input - orphaned cannot be null
        try {
            Coyote invalidBeaver = new Coyote(2, "Bobby", "COYOTE", "herbivore", null);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }
}
