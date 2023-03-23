package edu.ucalgary.oop;

import org.junit.Test;

import static org.junit.Assert.*;

public class PorcupineTest {
    /**
     * @version 1.0
     * @author Aarsh @ Nick
     * @date 2023-03-22
     */

    @Test
    public void testConstructorGoodData() {
        // Test valid input
        Porcupine porcupine = new Porcupine(1, "Tony", "PORCUPINE", "CREPUSCULAR", true);
        assertEquals(1, porcupine.getAnimalID());
        assertEquals("Tony", porcupine.getAnimalName());
        assertEquals(AnimalType.PORCUPINE, porcupine.getAnimalType());
        assertEquals(FeedingType.CREPUSCULAR, porcupine.getAnimalFeedingType());
        assertTrue(porcupine.getOrphaned());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorBadData() {
        // Test invalid input - feeding type not capitalized
        Porcupine invalidPorcupine = new Porcupine(2, "Bobby", "PORCUPINE", "CREPUSCULAR", null);

    }
}
