package edu.ucalgary.oop;

/**
 * Represents a Task object with relevant task properties such as task ID,
 * description, duration, maximum window, and task
 * type.
 * 
 * @author Aarsh
 */
public class Task {
    // Unique identifier for the task
    private final int TASK_ID;
    // A brief description of the task
    private final String DESCRIPTION;
    // Duration of the task in minutes
    private final int DURATION;
    // Maximum time window for completing the task in minutes
    private final int MAX_WINDOW;
    // Type of task as a String
    private final String TASK_TYPE;

    /**
     * Constructs a new Task object with the specified properties.
     *
     * @param taskID          Unique identifier for the task
     * @param description     Brief description of the task
     * @param volunteerNeeded Indicates whether a backup volunteer is needed
     * @param duration        Duration of the task in minutes
     * @param maxWindow       Maximum time window for completing the task in minutes
     * @param taskType        Type of task
     * @throws IllegalArgumentException If the specified task type is invalid
     */
    public Task(int taskID, String description, int duration, int maxWindow, String taskType)
            throws IllegalArgumentException {
        // Validate the task type by comparing it with the known task types
        Boolean validType = false;
        for (TaskType type : TaskType.values()) {
            if (type.toString().toLowerCase().compareTo(taskType.toLowerCase()) == 0) {
                validType = true;
            }
        }

        // If the task type is not valid, throw an exception
        if (validType == false) {
            throw new IllegalArgumentException("Invalid task type");
        } else {
            this.TASK_TYPE = taskType.toUpperCase();
        }

        // Initialize the instance variables with the provided values
        this.TASK_ID = taskID;
        this.DESCRIPTION = description;
        this.DURATION = duration;
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
        return DURATION;
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
     * Returns the type of the task.
     *
     * @return The task type
     */
    public String getTaskType() {
        return TASK_TYPE;
    }

    /**
     * Returns the unique identifier for the task.
     *
     * @return The task ID
     */
    public int getTaskID() {
        return TASK_ID;
    }
}
