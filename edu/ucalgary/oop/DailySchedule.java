package edu.ucalgary.oop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

public class DailySchedule {
    private final LocalDate CURR_DATE;
    private HashMap<Integer, ArrayList<Treatment>> scheduledTasks;
    private ArrayList<Animal> animals;
    private ArrayList<Task> tasks;
    private ArrayList<Treatment> treatments;

    public DailySchedule(ArrayList<Animal> animals, ArrayList<Task> tasks, ArrayList<Treatment> treatments,
            LocalDate date) throws ImpossibleScheduleException {
        this.CURR_DATE = date;
        this.animals = animals;
        this.treatments = treatments;
        this.tasks = tasks;

        // Add inferred tasks
        addInferredTasks();

        // Add those to treatments
        addInferredTreatments();

        this.scheduledTasks = scheduleTasks();
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

    private boolean validateTreatmentAdd(Treatment treatment, int maxWindow) throws ImpossibleScheduleException {
        int timeTaken = 0;
        int addHour = 1;
        for (Treatment t : scheduledTasks.get(treatment.getStartHour())) {
            timeTaken += tasks.get(t.getTaskID() - 1).getDuration();
        }
        int firstTimeTaken = timeTaken;
        // loop tries to find a start hour within the max window that will not require
        // an extra volunteer
        while (addHour < maxWindow) {
            // If the time taken is greater than 60 minutes, check the rest of the hours
            // within the max window
            if (timeTaken + tasks.get(treatment.getTaskID() - 1).getDuration() > 60) {
                timeTaken = 0;
                for (Treatment t : scheduledTasks.get(treatment.getStartHour() + addHour)) {
                    timeTaken += tasks.get(t.getTaskID() - 1).getDuration();
                }
                addHour++;
            }
            // If the time taken for any start hour within the max window is <= 60 minutes
            // then return false to the treatment at that start hour with no extra volunteer
            else if (timeTaken + tasks.get(treatment.getTaskID() - 1).getDuration() <= 60) {
                treatment.setStartHour(treatment.getStartHour() + addHour);
                return false;
            }
        }
        // Loop will now try to find a start hour within the max hour with an extra
        // volunteer
        addHour = 1;
        timeTaken = firstTimeTaken;
        while (addHour < maxWindow) {
            if (timeTaken + tasks.get(treatment.getTaskID() - 1).getDuration() > 120) {
                timeTaken = 0;
                for (Treatment t : scheduledTasks.get(treatment.getStartHour() + addHour)) {
                    timeTaken += tasks.get(t.getTaskID() - 1).getDuration();
                }
                addHour++;
            } else if (timeTaken + tasks.get(treatment.getTaskID() - 1).getDuration() > 60 &&
                    timeTaken + tasks.get(treatment.getTaskID() - 1).getDuration() <= 120) {
                treatment.setStartHour(treatment.getStartHour() + addHour);
                return true;
            }
        }

        // If the time taken for any start hour within the max window is > 120 minutes
        throw new ImpossibleScheduleException("Impossible to schedule " +
                tasks.get(treatment.getTaskID() - 1).getDescription() + " at hour: "
                + treatment.getStartHour() +
                " because it would exceed the maximum time even with an extra volunteer.");
    }

    private HashMap<Integer, ArrayList<Treatment>> scheduleTasks() throws ImpossibleScheduleException {
        HashMap<Integer, ArrayList<Treatment>> scheduledTasks = new HashMap<Integer, ArrayList<Treatment>>();
        boolean[] bonusVolunteers = new boolean[24];
        ArrayList<Integer> maxWindowArr = getMaxWindows();
        int i = 0;
        while (treatments.size() > 0) {
            int maxWindow = maxWindowArr.get(i);
            for (Treatment treatment : treatments) {
                if (tasks.get(treatment.getTaskID() - 1).getMaxWindow() == maxWindow) {
                    boolean bonusVolunteerNeeded = validateTreatmentAdd(treatment, maxWindow);
                    if (bonusVolunteerNeeded) {
                        bonusVolunteers[treatment.getStartHour()] = true;
                    }
                    if (scheduledTasks.containsKey(treatment.getStartHour())) {
                        scheduledTasks.get(treatment.getStartHour()).add(treatment);
                    } else {
                        ArrayList<Treatment> taskList = new ArrayList<>();
                        taskList.add(treatment);
                        scheduledTasks.put(treatment.getStartHour(), taskList);
                    }
                    treatments.remove(treatment);
                    break;
                } else {
                    continue;
                }
            }
        }
        return scheduledTasks;
    }

}
