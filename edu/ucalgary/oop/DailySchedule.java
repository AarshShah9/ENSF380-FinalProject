package edu.ucalgary.oop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalTime;
import java.util.Iterator;

public class DailySchedule {
    private final LocalDate CURR_DATE;
    private final HashMap<LocalTime, ArrayList<String>> SCHEDULED_TASKS;
    private ArrayList<Animal> animals;
    private ArrayList<Task> tasks;
    private ArrayList<Treatment> treatments;

    public DailySchedule(ArrayList<Animal> animals, ArrayList<Task> tasks, ArrayList<Treatment> treatments,
            LocalDate date) {
        this.CURR_DATE = date;
        this.animals = animals;
        this.treatments = treatments;
        this.tasks = tasks;

        // Add inferred tasks
        tasks.add(new Task(tasks.size() + 1, "Feeding - coyote", 5, 3));
        tasks.add(new Task(tasks.size() + 1, "Feeding - fox", 5, 3));
        tasks.add(new Task(tasks.size() + 1, "Feeding - raccoon", 5, 3));
        tasks.add(new Task(tasks.size() + 1, "Feeding - beaver", 5, 3));
        tasks.add(new Task(tasks.size() + 1, "Feeding - porcupine", 5, 3));

        this.SCHEDULED_TASKS = scheduleTasks();
    }

    private ArrayList<String> scheduleTasks() {

    }

}
