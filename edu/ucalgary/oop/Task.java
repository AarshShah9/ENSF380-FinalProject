package edu.ucalgary.oop;

/**
 * Represents a Task object with relevant task properties such as task ID,
 * description, duration, maximum window, and task
 * type.
 * 
 * @author Aarsh Shah: (UCID: 30150079) - William Fraser: (UCID: 30158991) -
 *         Sarim Sheikh: (UCID: 30143892) - Nicola Savino (UCID: 30129329)
 * @version 1.0
 * @date 2023-04-05
 */
public class Task {
    // Unique identifier for the task
    private final int TASK_ID;
    // A brief description of the task
    private final String DESCRIPTION;
    // Duration of the task in minutes
    private int duration;
    // Maximum time window for completing the task in minutes
    private final int MAX_WINDOW;

    /**
     * Constructs a new Task object with the specified properties.
     *
     * @param taskID      Unique identifier for the task
     * @param description Brief description of the task
     * @param duration    Duration of the task in minutes
     * @param maxWindow   Maximum time window for completing the task in minutes
     */
    public Task(int taskID, String description, int duration, int maxWindow) {
        // Initialize the instance variables with the provided values
        this.TASK_ID = taskID;
        this.DESCRIPTION = description;
        this.duration = duration;
        this.MAX_WINDOW = maxWindow;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description
     */
    public String getDescription() {
        return DESCRIPTION;
    }

    /**
     * Returns the duration of the task in minutes.
     *
     * @return The task duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Returns the maximum time window for completing the task in minutes.
     *
     * @return The maximum time window
     */
    public int getMaxWindow() {
        return MAX_WINDOW;
    }

    /**
     * Returns the unique identifier for the task.
     *
     * @return The task ID
     */
    public int getTaskID() {
        return TASK_ID;
    }

    /**
     * Sets the duration of the task in minutes.
     * 
     * @return null
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
}
