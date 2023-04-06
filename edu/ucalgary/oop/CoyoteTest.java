package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Define a class called "CoyoteTest" that tests the "Coyote" class
 * 
 * @version 2.0
 * @author Aarsh Shah: (UCID: 30150079) - William Fraser: (UCID: 30158991) -
 *         Sarim Sheikh: (UCID: 30143892) - Nicola Savino (UCID: 30129329)
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
        assertEquals("The wrong Animal ID was returned", 1, (int) coyote.getAnimalID());
        assertEquals("The wrong Animal Name was returned", "Tony", coyote.getAnimalName());
        assertEquals("The wrong Animal Type was returned", AnimalType.COYOTE, coyote.getAnimalType());
        assertEquals("The wrong Animal Feeding Type was returned", FeedingType.CREPUSCULAR,
                coyote.getAnimalFeedingType());
        assertFalse("The animal should not be set as Orphaned", coyote.getOrphaned());
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
