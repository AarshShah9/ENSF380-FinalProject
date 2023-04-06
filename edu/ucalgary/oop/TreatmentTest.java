package edu.ucalgary.oop;

// Importing required libraries for testing
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Create a class called "TreatmentTest" that tests the "Treatment" class
 * 
 * @version 2.0
 * @author Aarsh Shah: (UCID: 30150079) - William Fraser: (UCID: 30158991) -
 *         Sarim Sheikh: (UCID: 30143892) - Nicola Savino (UCID: 30129329)
 * @date 2023-04-04
 */

public class TreatmentTest {

    /**
     * Tests the constructor of the Treatment class.
     * It should set the correct values for animal ID, task ID, and start hour.
     */
    @Test
    public void testConstructor() {
        Treatment treatment = new Treatment(1, 1, 2, 3);
        assertEquals(1, treatment.getTreatmentID());
        assertEquals(1, treatment.getAnimalID());
        assertEquals(2, treatment.getTaskID());
        assertEquals(3, treatment.getStartHour());
    }

    /**
     * Tests the getAnimalID method of the Treatment class.
     * It should return the correct animal ID.
     */
    @Test
    public void testGetAnimalID() {
        Treatment treatment = new Treatment(1, 1, 2, 3);
        assertEquals(1, treatment.getAnimalID());
    }

    /**
     * Tests the getTaskID method of the Treatment class.
     * It should return the correct task ID.
     */
    @Test
    public void testGetTaskID() {
        Treatment treatment = new Treatment(1, 1, 2, 3);
        assertEquals(2, treatment.getTaskID());
    }

    /**
     * Tests the setStartHour method of the Treatment class.
     * It should set the start hour to the correct value.
     */
    @Test
    public void testSetStartHour() {
        Treatment treatment = new Treatment(1, 1, 2, 3);
        treatment.setStartHour(4);
        assertEquals(4, treatment.getStartHour());
    }

    /**
     * Tests the getStartHour method of the Treatment class.
     * It should return the correct start hour.
     */
    @Test
    public void testGetStartHour() {
        Treatment treatment = new Treatment(1, 1, 2, 3);
        assertEquals(3, treatment.getStartHour());
    }

    /**
     * Tests the getTreatmentID method of the Treatment class.
     * It should return the correct treatment ID.
     */
    @Test
    public void testGetTreatmentID() {
        Treatment treatment = new Treatment(1, 1, 2, 3);
        assertEquals(1, treatment.getTreatmentID());
    }

}
