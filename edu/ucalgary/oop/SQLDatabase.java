package edu.ucalgary.oop;

import java.util.ArrayList;

public class SQLDatabase {
    private ArrayList<String> animals;
    private ArrayList<String> tasks;

    public SQLDatabase(String fileName) {
        animals = new ArrayList<String>();
        tasks = new ArrayList<String>();
    }
}
