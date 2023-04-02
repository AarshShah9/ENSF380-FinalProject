package edu.ucalgary.oop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.Collections;

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
        addInferredTasks();

        // Add those to treatments
        addInferredTreatments();

        this.SCHEDULED_TASKS = new HashMap<>();
    }

    private void addInferredTasks() {
        tasks.add(new Task(tasks.size() + 1, "Feeding - coyote", 5, 3)); // size - 9
        tasks.add(new Task(tasks.size() + 1, "Feeding - fox", 5, 3)); // size - 8
        tasks.add(new Task(tasks.size() + 1, "Feeding - raccoon", 5, 3)); // size - 7
        tasks.add(new Task(tasks.size() + 1, "Feeding - beaver", 5, 3)); // size - 6
        tasks.add(new Task(tasks.size() + 1, "Feeding - porcupine", 5, 3)); // size - 5
        tasks.add(new Task(tasks.size() + 1, "Cage cleaning - coyote", 5, 24)); // size - 4
        tasks.add(new Task(tasks.size() + 1, "Cage cleaning - fox", 5, 24)); // size - 3
        tasks.add(new Task(tasks.size() + 1, "Cage cleaning - raccoon", 5, 24)); // size - 2
        tasks.add(new Task(tasks.size() + 1, "Cage cleaning - beaver", 5, 24)); // size - 1
        tasks.add(new Task(tasks.size() + 1, "Cage cleaning - porcupine", 10, 24)); // size
    }

    private void addInferredTreatments() {
        for (Animal animal : animals) {
            if (animal.getAnimalType() == AnimalType.COYOTE) {
                addCoyoteTreatments(animal);
            } else if (animal.getAnimalType() == AnimalType.FOX) {
                addFoxTreatments(animal);
            } else if (animal.getAnimalType() == AnimalType.RACCOON) {
                addRaccoonTreatments(animal);
            } else if (animal.getAnimalType() == AnimalType.BEAVER) {
                addBeaverTreatments(animal);
            } else if (animal.getAnimalType() == AnimalType.PORCUPINE) {
                addPorcupineTreatments(animal);
            }
        }
    }

    private void addCoyoteTreatments(Animal animal) {
        if (!animal.getOrphaned()) {
            treatments.add(new Treatment(animal.getAnimalID(), tasks.size() - 9,
                    animal.getAnimalFeedingType().getFeedStartTime()));
        }
        treatments.add(new Treatment(animal.getAnimalID(), tasks.size() - 4, 0));
    }

    private void addFoxTreatments(Animal animal) {
        if (!animal.getOrphaned()) {
            treatments.add(new Treatment(animal.getAnimalID(), tasks.size() - 8,
                    animal.getAnimalFeedingType().getFeedStartTime()));
        }
        treatments.add(new Treatment(animal.getAnimalID(), tasks.size() - 3, 0));
    }

    private void addRaccoonTreatments(Animal animal) {
        if (!animal.getOrphaned()) {
            treatments.add(new Treatment(animal.getAnimalID(), tasks.size() - 7,
                    animal.getAnimalFeedingType().getFeedStartTime()));
        }
        treatments.add(new Treatment(animal.getAnimalID(), tasks.size() - 2, 0));
    }

    private void addBeaverTreatments(Animal animal) {
        if (!animal.getOrphaned()) {
            treatments.add(new Treatment(animal.getAnimalID(), tasks.size() - 6,
                    animal.getAnimalFeedingType().getFeedStartTime()));
        }
        treatments.add(new Treatment(animal.getAnimalID(), tasks.size() - 1, 0));
    }

    private void addPorcupineTreatments(Animal animal) {
        if (!animal.getOrphaned()) {
            treatments.add(new Treatment(animal.getAnimalID(), tasks.size() - 5,
                    animal.getAnimalFeedingType().getFeedStartTime()));
        }
        treatments.add(new Treatment(animal.getAnimalID(), tasks.size(), 0));
    }

    private ArrayList<Integer> getMaxWindows() {
        ArrayList<Integer> maxWindowArr = new ArrayList<>();
        for (Task task : tasks) {
            if (!maxWindowArr.contains(task.getMaxWindow())) {
                maxWindowArr.add(task.getMaxWindow());
            }
        }
        Collections.sort(maxWindowArr);
        return maxWindowArr;
    }

    private ArrayList<String> scheduleTasks() {
        HashMap<LocalTime, ArrayList<String>> scheduledTasks = new HashMap<LocalTime, ArrayList<String>>();
        ArrayList<Integer> maxWindowArr = getMaxWindows();
        int i = 0;
        while (treatments.size() > 0) {
            int maxWindow = maxWindowArr.get(i);
            for (Treatment treatment : treatments) {
                if (tasks.get(treatment.getTaskID() - 1).getMaxWindow() == maxWindow) {
                    if (scheduledTasks.containsKey(treatment.getStartTime())) {
                        scheduledTasks.get(treatment.getStartTime()).add(
                                treatment.getAnimalID() + " " + tasks.get(treatment.getTaskID() - 1).getTaskName());
                    } else {
                        ArrayList<String> taskList = new ArrayList<>();
                        taskList.add(
                                treatment.getAnimalID() + " " + tasks.get(treatment.getTaskID() - 1).getTaskName());
                        scheduledTasks.put(treatment.getStartTime(), taskList);
                    }
                    treatments.remove(treatment);
                    break;
                } else {
                    continue;
                }
            }
        }
    }

}
