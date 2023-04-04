package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

public class CoyoteTest {
    /**
     * @version 1.0
     * @author Aarsh @ Nick
     * @date 2023-03-22
     */

    @Test
    public void testConstructor() {
        // Test valid input
        Coyote coyote = new Coyote(1, "Tony");
        assertEquals(1, (int) coyote.getAnimalID());
        assertEquals("Tony", coyote.getAnimalName());
        assertEquals(AnimalType.COYOTE, coyote.getAnimalType());
        assertEquals(FeedingType.CREPUSCULAR, coyote.getAnimalFeedingType());
        assertTrue(coyote.getOrphaned());
    }

    @Test
    public void testConstructorBadInput() {
        Coyote invalidBeaver = new Coyote(2, "");

    }
}
