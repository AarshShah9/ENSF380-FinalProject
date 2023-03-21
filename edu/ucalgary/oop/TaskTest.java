package edu.ucalgary.oop;

import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;

import java.beans.Transient;

public class TaskTest {
    private static final String[] TASK_TYPES = { "Feeding", "Cleaning", "Medical" };

    // Test the Task constructor
    @Test
    public void testTaskConstructorGoodData() {
        Task task = new Task(1, "Test", true, 1, 1, TASK_TYPES[0]);
        assertEquals(1, task.getTaskID());
        assertEquals("Test", task.getDescription());
        assertEquals(true, task.getBackupVolunteerNeeded());
        assertEquals(1, task.getDuration());
        assertEquals(1, task.getMaxWindow());
        assertEquals(TASK_TYPES[0], task.getTaskType());
    }

    // Test the Task constructor with bad data
    @Test(expected = IllegalArgumentException.class)
    public void testTaskConstructorBadData() {
        Task task = new Task(1, "Test", true, 1, 1, "Bad");
    }

    // Test each getter method individually
    @Test
    public void testGetTaskID() {
        Task task = new Task(1, "Test", true, 1, 1, TASK_TYPES[0]);
        assertEquals(1, task.getTaskID());
    }

    @Test
    public void testGetDescription() {
        Task task = new Task(1, "Test", true, 1, 1, TASK_TYPES[0]);
        assertEquals("Test", task.getDescription());
    }

    @Test
    public void testGetBackupVolunteerNeeded() {
        Task task = new Task(1, "Test", true, 1, 1, TASK_TYPES[0]);
        assertEquals(true, task.getBackupVolunteerNeeded());
    }

    @Test
    public void testGetDuration() {
        Task task = new Task(1, "Test", true, 1, 1, TASK_TYPES[0]);
        assertEquals(1, task.getDuration());
    }

    @Test
    public void testGetMaxWindow() {
        Task task = new Task(1, "Test", true, 1, 1, TASK_TYPES[0]);
        assertEquals(1, task.getMaxWindow());
    }

    @Test
    public void testGetTaskType() {
        Task task = new Task(1, "Test", true, 1, 1, TASK_TYPES[0]);
        assertEquals(TASK_TYPES[0], task.getTaskType());
    }

}