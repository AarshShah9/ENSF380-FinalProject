package edu.ucalgary.oop;

import java.util.ArrayList;

public class SQLDatabase {
    private ArrayList<String> animals;
    private ArrayList<String> tasks;

    public SQLDatabase(String fileName) throws IllegalArgumentException {
        animals = new ArrayList<String>();
        tasks = new ArrayList<String>();
    }

    public void updateDatabase(String fileName) {
        // TODO implement method
    }

    public ArrayList<String> getAnimals() {
        return animals;
    }

    public void setAnimals(ArrayList<String> newAnimals) {
        this.animals = newAnimals;
    }

    public ArrayList<String> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<String> newTasks) {
        this.tasks = newTasks;
    }
}
