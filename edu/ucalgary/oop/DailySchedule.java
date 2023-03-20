package edu.ucalgary.oop;

import java.time.LocalDate;
import java.util.ArrayList;

public class DailySchedule {
    private ArrayList<Task> tasks;
    private ArrayList<Animal> animals;
    private final LocalDate DATE;
    private Boolean backupVolunteerPresent;

    public DailySchedule(LocalDate day, String[] newAnimals, String[] newTasks) throws IllegalArgumentException {
        // TODO implement constructor
        // Change this up, it's just a placeholder to avoid final variable not
        // initialized error
        this.DATE = day;
        this.tasks = new ArrayList<>();
        this.animals = new ArrayList<>();
        this.backupVolunteerPresent = false;
    }

    public void calculateSchedule() {
        // TODO implement method
    }

    public LocalDate getDate() {
        return DATE;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(ArrayList<Animal> newAnimals) {
        this.animals = newAnimals;
    }

    public Boolean getBackupVolunteerPresent() {
        return backupVolunteerPresent;
    }

    public void setBackupVolunteerPresent(Boolean present) {
        this.backupVolunteerPresent = present;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> newTasks) {
        this.tasks = newTasks;
    }

    public Task getNextTask() {
        // TODO implement method
        return null;
    }

}
