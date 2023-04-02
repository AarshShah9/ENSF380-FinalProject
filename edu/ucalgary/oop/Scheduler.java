package edu.ucalgary.oop;

import java.time.LocalDate;
import java.util.ArrayList;

public class Scheduler {
    private ArrayList<Task> Tasks;
    private ArrayList<Animal> Animals;
    private ArrayList<Treatment> Treatments;
    private LocalDate DATE;
    private DailySchedule dailySchedule;

    public Scheduler(LocalDate day, ArrayList<Animal> animals, ArrayList<Task> tasks, ArrayList<Treatment> treatments) {
        this.DATE = day;
        this.Animals = animals;
        this.Tasks = tasks;
        this.Treatments = treatments;
    }

    public DailySchedule calculateSchedule() {
        try {
            this.dailySchedule = new DailySchedule(Animals, Tasks, Treatments, DATE);
        }
        catch(Exception e) {
            System.out.println("ImpossibleScheduleException caught: " + e.getMessage());
        }
    }

    public LocalDate getDate() {
        return DATE;
    }

    public ArrayList<Animal> getAnimals() {
        return Animals;
    }

    public void setAnimals(ArrayList<Animal> newAnimals) {
        this.Animals = newAnimals;
    }

    public ArrayList<Task> getTasks() {
        return Tasks;
    }

    public void setTasks(ArrayList<Task> newTasks) {
        this.Tasks = newTasks;
    }

    public ArrayList<Treatment> getTreatments() {
        return Treatments;
    }

    public void setTreatments(ArrayList<Treatment> newTreatments) {
        this.Treatments = newTreatments;
    }
}
