package edu.ucalgary.oop;

import java.time.LocalTime;

public class Treatment {
    private final int ANIMAL_ID;
    private final int TASK_ID;
    private final int START_HOUR;

    public Treatment(int animalID, int taskID, int startHour) {
        this.ANIMAL_ID = animalID;
        this.TASK_ID = taskID;
        this.START_HOUR = startHour;
    }

    public int getAnimalID() {
        return ANIMAL_ID;
    }

    public int getTaskID() {
        return TASK_ID;
    }

    public int getStartHour() {
        return START_HOUR;
    }
}
