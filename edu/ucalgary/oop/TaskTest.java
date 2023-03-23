package edu.ucalgary.oop;

// Importing required libraries for testing
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The TaskTest class is designed to test the Task class by using a series of
 * test methods
 * to check the validity and correctness of the class's functionality.
 * 
 * @version 1.0
 * @author Aarsh
 * @date 2023-03-22
 */
public class TaskTest {
    // Array of valid task types for testing purposes
    private static final String[] TASK_TYPES = { "Feeding", "Cleaning", "Medical" };

    /**
     * Tests the Task constructor with good data, ensuring that the Task object is
     * properly created.
     * Verifies if the provided data is stored correctly in the object's properties.
     */
    @Test
    public void testTaskConstructorGoodData() {
        // Creating a new Task object with valid data
        Task task = new Task(1, "Test", true, 1, 1, TASK_TYPES[0]);

        // Assertions to check if the Task object was created and the properties were
        // set correctly
        assertEquals(1, task.getTaskID());
        assertEquals("Test", task.getDescription());
        assertEquals(true, task.getBackupVolunteerNeeded());
        assertEquals(1, task.getDuration());
        assertEquals(1, task.getMaxWindow());
        assertEquals(TASK_TYPES[0], task.getTaskType());
    }

    /**
     * Tests the Task constructor with bad data (invalid task type), expecting an
     * IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTaskConstructorBadData() {
        // Creating a new Task object with invalid data (task type)
        Task task = new Task(1, "Test", true, 1, 1, "Bad");
    }

    /**
     * Tests the getTaskID() method to ensure it returns the correct task ID.
     */
    @Test
    public void testGetTaskID() {
        // Creating a new Task object with valid data
        Task task = new Task(1, "Test", true, 1, 1, TASK_TYPES[0]);

        // Asserting that the correct task ID is returned
        assertEquals(1, task.getTaskID());
    }

    /**
     * Tests the getDescription() method to ensure it returns the correct task
     * description.
     */
    @Test
    public void testGetDescription() {
        // Creating a new Task object with valid data
        Task task = new Task(1, "Test", true, 1, 1, TASK_TYPES[0]);

        // Asserting that the correct task description is returned
        assertEquals("Test", task.getDescription());
    }

    /**
     * Tests the getBackupVolunteerNeeded() method to ensure it returns the correct
     * backup volunteer needed status.
     */
    @Test
    public void testGetBackupVolunteerNeeded() {
        // Creating a new Task object with valid data
        Task task = new Task(1, "Test", true, 1, 1, TASK_TYPES[0]);

        // Asserting that the correct backup volunteer needed status is returned
        assertEquals(true, task.getBackupVolunteerNeeded());
    }

    /**
     * Tests the getDuration() method to ensure it returns the correct task
     * duration.
     */
    @Test
    public void testGetDuration() {
        // Creating a new Task object with valid data
        Task task = new Task(1, "Test", true, 1, 1, TASK_TYPES[0]);

        // Asserting that the correct task duration is returned
        assertEquals(1, task.getDuration());
    }

    /**
     * Tests the getMaxWindow() method to ensure it returns the correct max window
     * value.
     */

    @Test
    public void testGetMaxWindow() {
        // Creating a new Task object with valid data
        Task task = new Task(1, "Test", true, 1, 1, TASK_TYPES[0]);

        // Asserting that the correct max window value is returned
        assertEquals(1, task.getMaxWindow());
    }

    /**
     * 
     * Tests the getTaskType() method to ensure it returns the correct task type.
     */
    @Test
    public void testGetTaskType() {
        // Creating a new Task object with valid data
        Task task = new Task(1, "Test", true, 1, 1, TASK_TYPES[0]);

        // Asserting that the correct task type is returned
        assertEquals(TASK_TYPES[0], task.getTaskType());
    }

}