package edu.ucalgary.oop;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Scheduler {
    private ArrayList<Task> tasks;
    private ArrayList<Animal> animals;
    private ArrayList<Treatment> treatments;
    private LocalDate date;
    private DailySchedule dailySchedule;

    public static void main(String[] args) {
        Scheduler newSceduleObj = new Scheduler(LocalDate.now(), "root", "password");

        // System.out.println(newSceduleObj.getAnimals().get(0).getAnimalName());
        newSceduleObj.calculateSchedule();
    }

    public Scheduler(LocalDate day, ArrayList<Task> tasks, ArrayList<Treatment> treatments, ArrayList<Animal> animals) {
        this.date = day;
        this.animals = animals;
        this.tasks = tasks;
        this.treatments = treatments;
    }

    public void getFromSQL(LocalDate day, String user, String password) throws IllegalArgumentException {
        this.date = day;
        this.animals = new ArrayList<Animal>();
        this.tasks = new ArrayList<Task>();
        this.treatments = new ArrayList<Treatment>();
        try {
            SQLDatabase db = new SQLDatabase("EWR", user, password, animals, tasks, treatments);
        } catch (Exception e) {
            System.out.println("SQLDatabaseException caught: " + e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }

    public String calculateSchedule() {
        try {
            this.dailySchedule = new DailySchedule(animals, tasks, treatments, DATE);
            return "Success";
        } catch (ImpossibleScheduleException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "IOException caught: " + e.getMessage();
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

    public boolean[] getVoluneersNeded() {
        if (dailySchedule != null) {
            return dailySchedule.getVolunteersNeeded();
        } else {
            return null;
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
