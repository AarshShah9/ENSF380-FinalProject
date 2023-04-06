package edu.ucalgary.oop;

// Importing required libraries for testing
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The TaskTest class is designed to test the Task class by using a series of
 * test methods
 * to check the validity and correctness of the class's functionality.
 * 
 * @version 1.1
 * @author Aarsh Shah: (UCID: 30150079) - William Fraser: (UCID: 30158991) -
 *         Sarim Sheikh: (UCID: 30143892) - Nicola Savino (UCID: 30129329)
 * @date 2023-03-22
 */
public class TaskTest {
    // Array of valid task types for testing purposes

    /**
     * Tests the Task constructor with good data, ensuring that the Task object is
     * properly created.
     * Verifies if the provided data is stored correctly in the object's properties.
     */
    @Test
    public void testTaskConstructorGoodData() {
        // Creating a new Task object with valid data
        Task task = new Task(1, "Test", 1, 1);

        // Assertions to check if the Task object was created and the properties were
        // set correctly
        assertEquals("The TaskID isn't as expected", 1, task.getTaskID());
        assertEquals("This wrong description is returned", "Test", task.getDescription());
        assertEquals("The duration isn't as expected", 1, task.getDuration());
        assertEquals("The max window isn't as expected", 1, task.getMaxWindow());
    }

    /**
     * Tests the getTaskID() method to ensure it returns the correct task ID.
     */
    @Test
    public void testGetTaskID() {
        // Creating a new Task object with valid data
        Task task = new Task(1, "Test", 1, 1);

        // Asserting that the correct task ID is returned
        assertEquals("The TaskID isn't as expected", 1, task.getTaskID());
    }

    /**
     * Tests the getDescription() method to ensure it returns the correct task
     * description.
     */
    @Test
    public void testGetDescription() {
        // Creating a new Task object with valid data
        Task task = new Task(1, "Test", 1, 1);

        // Asserting that the correct task description is returned
        assertEquals("This wrong description is returned", "Test", task.getDescription());
    }

    /**
     * Tests the getDuration() method to ensure it returns the correct task
     * duration.
     */
    @Test
    public void testGetDuration() {
        // Creating a new Task object with valid data
        Task task = new Task(1, "Test", 1, 1);

        // Asserting that the correct task duration is returned
        assertEquals("The duration isn't as expected", 1, task.getDuration());
    }

    /**
     * Tests the getMaxWindow() method to ensure it returns the correct max window
     * value.
     */

    @Test
    public void testGetMaxWindow() {
        // Creating a new Task object with valid data
        Task task = new Task(1, "Test", 1, 1);

        // Asserting that the correct max window value is returned
        assertEquals("The max window isn't as expected", 1, task.getMaxWindow());
    }

}