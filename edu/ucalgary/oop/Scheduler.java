package edu.ucalgary.oop;

import java.time.LocalDate;
import java.util.ArrayList;

public class Scheduler {
    private ArrayList<Task> tasks;
    private ArrayList<Animal> animals;
    private ArrayList<Treatment> treatments;
    private LocalDate DATE;
    private DailySchedule dailySchedule;

    public Scheduler(LocalDate day) {
        this.DATE = day;
        this.animals = new ArrayList<Animal>();
        this.tasks = new ArrayList<Task>();
        this.treatments = new ArrayList<Treatment>();
        try {
            SQLDatabase db = new SQLDatabase("EWR", animals, tasks, treatments);
        } catch (Exception e) {
            System.out.println("SQLDatabaseException caught: " + e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }

    public DailySchedule calculateSchedule() {
        try {
            this.dailySchedule = new DailySchedule(animals, tasks, treatments, DATE);
        } catch (Exception e) {
            System.out.println("ImpossibleScheduleException caught: " + e.getMessage());
        }
        return dailySchedule;
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

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> newTasks) {
        this.tasks = newTasks;
    }

    public ArrayList<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(ArrayList<Treatment> newTreatments) {
        this.treatments = newTreatments;
    }
}
