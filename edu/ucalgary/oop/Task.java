package edu.ucalgary.oop;

public class Task {
    private final int TASK_ID;
    private final Boolean BACKUP_VOLUNTEER_NEEDED;
    private final String DESCRIPTION;
    private final int DURATION;
    private final int MAX_WINDOW;
    private final String TASK_TYPE;

    public Task(int taskID, String description, Boolean volunteerNeeded, int duration, int maxWindow, String taskType)
            throws IllegalArgumentException {
        // TODO implement constructor
        // Change this up, it's just a placeholder to avoid final variable not
        // initialized error
        this.TASK_ID = taskID;
        this.DESCRIPTION = description;
        this.BACKUP_VOLUNTEER_NEEDED = volunteerNeeded;
        this.DURATION = duration;
        this.MAX_WINDOW = maxWindow;
        this.TASK_TYPE = taskType;

    }

    public Boolean getBackupVolunteerNeeded() {
        return BACKUP_VOLUNTEER_NEEDED;
    }

    public String getDescription() {
        return DESCRIPTION;
    }

    public int getDuration() {
        return DURATION;
    }

    public int getMaxWindow() {
        return MAX_WINDOW;
    }

    public String getTaskType() {
        return TASK_TYPE;
    }

    public int getTaskID() {
        return TASK_ID;
    }

}
