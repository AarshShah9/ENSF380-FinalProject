package edu.ucalgary.oop;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Scheduler {
    private ArrayList<Task> tasks;
    private ArrayList<Animal> animals;
    private ArrayList<Treatment> treatments;

    private LocalDate DATE;
    private DailySchedule dailySchedule;

    public Scheduler(LocalDate day, String user, String password) {
        this.DATE = day;
        this.animals = new ArrayList<Animal>();
        this.tasks = new ArrayList<Task>();
        this.treatments = new ArrayList<Treatment>();
        try {
            SQLDatabase db = new SQLDatabase("EWR", user, password, animals, tasks, treatments);
            db.addAnimalsSQL();
            db.addTasksSQL();
            db.addTreatmentSQL();
        } catch (Exception e) {
            System.out.println("SQLDatabaseException caught: " + e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }

    public void calculateSchedule() {
        try {
            this.dailySchedule = new DailySchedule(this.animals, this.tasks, this.treatments, this.DATE);
        } catch (Exception e) {
            System.out.println("ImpossibleScheduleException caught: " + e.getMessage());
        }
    }

    public void changeTreatmentStart(int animalID, int taskID, int newStartHour) {
        try {
            for (Treatment treatment : treatments) {
                if (treatment.getAnimalID() == animalID && treatment.getTaskID() == taskID) {
                    treatment.setStartHour(newStartHour);
                }
            }
        } catch (Exception e) {
            System.out.println("IOException caught: " + e.getMessage());
        }
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
