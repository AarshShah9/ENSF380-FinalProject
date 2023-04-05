package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Define a class called "CoyoteTest" that tests the "Coyote" class
 * 
 * @version 2.0
 * @author Aarsh @ Nick
 * @date 2023-03-22
 */
public class CoyoteTest {

    /**
     * Test the constructor of the "Coyote" class
     */
    @Test
    public void testConstructor() {
        // create a new coyote object
        Coyote coyote = new Coyote(1, "Tony");

        // check all the attributes of the coyote object
        assertEquals(1, (int) coyote.getAnimalID());
        assertEquals("Tony", coyote.getAnimalName());
        assertEquals(AnimalType.COYOTE, coyote.getAnimalType());
        assertEquals(FeedingType.CREPUSCULAR, coyote.getAnimalFeedingType());
        assertTrue(coyote.getOrphaned());
    }

    /**
     * Test the constructor of the "Coyote" class with invalid input
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorBadInput() {
        // Test invalid input for the constructor
        Coyote invalidBeaver = new Coyote(2, "");

    }
}
