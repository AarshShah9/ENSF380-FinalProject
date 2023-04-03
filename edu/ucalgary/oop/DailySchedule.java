package edu.ucalgary.oop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Iterator;

/**
 * The DailySchedule class represents a schedule for a given day
 * 
 * @author William Fraser
 * @since 2023-03-30
 * @version 1.0
 */
public class DailySchedule {
    private final LocalDate CURR_DATE;
    private HashMap<Integer, ArrayList<ScheduleItem>> scheduledTasks;
    private ArrayList<Animal> animals;
    private ArrayList<Task> tasks;
    private ArrayList<Treatment> treatments;
    private ArrayList<ScheduleItem> scheduleItems;
    private int numCoyotes = 0;
    private int numFoxes = 0;

    /**
     * Constructs a new DailySchedule object with the given ArrayLists of animals,
     * tasks, and treatments
     * 
     * @param animals    the ArrayList of animals
     * @param tasks      the ArrayList of tasks
     * @param treatments the ArrayList of treatments
     * @param date       the current date
     * @throws ImpossibleScheduleException if the schedule is impossible to create
     */
    public DailySchedule(ArrayList<Animal> animals, ArrayList<Task> tasks, ArrayList<Treatment> treatments,
            LocalDate date) throws ImpossibleScheduleException {
        this.CURR_DATE = date;
        this.animals = animals;
        this.treatments = treatments;
        this.tasks = tasks;

        // Add treatments to the scheduledItems array
        addTreatments();

        // Add inferred tasks to the scheduledItems array
        addInferredTasks();

        // groups the feeding that have prep times
        groupFeedings();

        scheduleTasks();
    }

    private void addTreatments() {
        for (Treatment treatment : treatments) {
            scheduleItems.add(new ScheduleItem(
                    animals.get(treatment.getAnimalID() - 1).getAnimalName(),
                    1, tasks.get(treatment.getTaskID() - 1).getDescription(),
                    treatment.getStartHour(),
                    tasks.get(treatment.getTaskID()).getMaxWindow(),
                    tasks.get(treatment.getTaskID()).getDuration(),
                    0));
        }
    }

    private void addInferredTasks() {
        for (Animal animal : animals) {
            inferTaskFromAnimal(animal);
            if (animal.getAnimalType() == AnimalType.COYOTE) {
                if (!animal.getOrphaned())
                    numCoyotes++;
            } else if (animal.getAnimalType() == AnimalType.FOX) {
                if (!animal.getOrphaned())
                    numFoxes++;
            }
        }
    }

    private void groupFeedings() {
        Iterator<ScheduleItem> it = scheduleItems.iterator();
        ScheduleItem previous = null;
        int i = 0;
        int j = 0;
        if (it.hasNext())
            previous = it.next();
        while (it.hasNext() && i < numCoyotes) {
            ScheduleItem current = it.next();
            if (current.getDescription() == "Feeding - coyote") {
                current = new ScheduleItem(previous.getName() + ", " + current.getName(),
                        previous.getQuantity() + 1, previous.getDescription(), previous.getStartHour(),
                        previous.getMaxWindow(), previous.getDuration() + current.getDuration(),
                        previous.getPrepTime());
                scheduleItems.remove(previous);
                previous = current;
            } else if (current.getDescription() == "Feeding - fox" && j < numFoxes) {
                current = new ScheduleItem(previous.getName() + ", " + current.getName(),
                        previous.getQuantity() + 1, previous.getDescription(), previous.getStartHour(),
                        previous.getMaxWindow(), previous.getDuration() + current.getDuration(),
                        previous.getPrepTime());
                scheduleItems.remove(previous);
                previous = current;
                j++;
            } else
                continue;
        }
    }

    private void inferTaskFromAnimal(Animal animal) {
        scheduleItems.add(new ScheduleItem(animal.getAnimalName(),
                1, "Cage cleaning - " + animal.getAnimalType().toString().toLowerCase(),
                0, 24, animal.CLEAN_TIME, 0));
        if (!animal.getOrphaned())
            scheduleItems.add(new ScheduleItem(animal.getAnimalName(),
                    1, "Feeding - " + animal.getAnimalType().toString().toLowerCase(),
                    animal.ANIMAL_FEEDING_TYPE.getFeedStartTime(), animal.FEED_WINDOW,
                    animal.FEED_TIME, animal.FEED_PREP_TIME));
    }

    /**
     * Validates scheduling a treatment
     * 
     * @throws ImpossibleScheduleException if the treatment cannot be scheduled
     * @params none
     * @return true if the treatment can be scheduled with an extra volunteer
     * @return false if the treatment can be scheduled without
     */
    private boolean validateAddition(ScheduleItem item) throws ImpossibleScheduleException {
        int timeTaken = 0;
        int addHour = 1;
        int maxWindow = item.getMaxWindow();
        for (ScheduleItem task : scheduledTasks.get(item.getStartHour())) {
            timeTaken += task.getDuration();
        }
        int firstTimeTaken = timeTaken;
        // loop tries to find a start hour within the max window that will not require
        // an extra volunteer
        while (addHour < maxWindow) {
            // If the time taken is greater than 60 minutes, check the rest of the hours
            // within the max window
            if (timeTaken + item.getDuration() > 60) {
                timeTaken = 0;
                for (ScheduleItem task : scheduledTasks.get(item.getStartHour() + addHour)) {
                    timeTaken += task.getDuration();
                }
                addHour++;
            }
            // If the time taken for any start hour within the max window is <= 60 minutes
            // then return false to the treatment at that start hour with no extra volunteer
            else if (timeTaken + item.getDuration() <= 60) {
                item.setStartHour(item.getStartHour() + addHour);
                return false;
            }
        }
        // Loop will now try to find a start hour within the max hour with an extra
        // volunteer
        addHour = 1;
        timeTaken = firstTimeTaken;
        while (addHour < maxWindow) {
            if (timeTaken + item.getDuration() > 120) {
                timeTaken = 0;
                for (ScheduleItem task : scheduledTasks.get(item.getStartHour())) {
                    timeTaken += task.getDuration();
                }
                addHour++;
            } else if (timeTaken + item.getDuration() > 60 &&
                    timeTaken + item.getDuration() <= 120) {
                item.setStartHour(item.getStartHour() + addHour);
                return true;
            }
        }

        // If the time taken for any start hour within the max window impossible
        // even with an extra volunteer, throw an exception with a description of the
        // issue
        String message = "Impossible to schedule " +
                item.getDescription() + " at hour: " + item.getStartHour() +
                " because it would exceed the maximum time even with an extra volunteer.\n" +
                "Try changing the start hour of ";
        for (ScheduleItem task : scheduledTasks.get(item.getStartHour())) {
            message += task.getDescription() + ", ";
        }
        message += "or " + item.getDescription();

        throw new ImpossibleScheduleException(message);
    }

    public void calculateSchedule() throws ImpossibleScheduleException {
        HashMap<Integer, ArrayList<ScheduleItem>> scheduledTasks = new HashMap<Integer, ArrayList<ScheduleItem>>();
        boolean[] bonusVolunteers = new boolean[24];
        ArrayList<Integer> maxWindowArr = getMaxWindows();
        int i = 0;
        while (scheduleItems.size() > 0) {
            int maxWindow = maxWindowArr.get(i);
            for (ScheduleItem item : scheduleItems) {
                if (item.getMaxWindow() == maxWindow) {
                    bonusVolunteers[item.getStartHour()] = validateAddition(item);
                    if (scheduledTasks.get(item.getStartHour()) == null) {
                        scheduledTasks.put(item.getStartHour(), new ArrayList<ScheduleItem>());
                    }
                    scheduledTasks.get(item.getStartHour()).add(item);
                    scheduleItems.remove(item);
                } else
                    continue;
            }
            i++;
        }

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
                    if (treatment.getTaskID() != tasks.size() - 9 ||
                            treatment.getTaskID() != tasks.size() - 8) {
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
                    } else
                        continue;
                } else {
                    addTreatmentWithPrepTime(treatment);
                }
            }

        }
        return scheduledTasks;
    }

    /**
     * Returns an arraylist that contains all of the max windows for the tasks
     * in ascending order
     * Used to scehdule lowest max window tasks first
     * 
     * @params none
     * @return ArrayList<ArrayList<Treatment>> scheduledTasks
     */
    private ArrayList<Integer> getMaxWindows() {
        ArrayList<Integer> maxWindowArr = new ArrayList<>();
        for (ScheduleItem item : scheduleItems) {
            if (!maxWindowArr.contains(item.getMaxWindow())) {
                maxWindowArr.add(item.getMaxWindow());
            }
        }
        Collections.sort(maxWindowArr);
        return maxWindowArr;
    }

    /**
     * Adds the inferred tasks to the tasks ArrayList
     * 
     * @params none
     * @return void
     */
    // private void addInferredTasks() {
    // tasks.add(new Task(tasks.size() + 1, "Feeding - coyote", 5, 3)); // size - 9
    // tasks.add(new Task(tasks.size() + 1, "Feeding - fox", 5, 3)); // size - 8
    // tasks.add(new Task(tasks.size() + 1, "Feeding - raccoon", 5, 3)); // size - 7
    // tasks.add(new Task(tasks.size() + 1, "Feeding - beaver", 5, 3)); // size - 6
    // tasks.add(new Task(tasks.size() + 1, "Feeding - porcupine", 5, 3)); // size -
    // 5
    // tasks.add(new Task(tasks.size() + 1, "Cage cleaning - coyote", 5, 24)); //
    // size - 4
    // tasks.add(new Task(tasks.size() + 1, "Cage cleaning - fox", 5, 24)); // size
    // - 3
    // tasks.add(new Task(tasks.size() + 1, "Cage cleaning - raccoon", 5, 24)); //
    // size - 2
    // tasks.add(new Task(tasks.size() + 1, "Cage cleaning - beaver", 5, 24)); //
    // size - 1
    // tasks.add(new Task(tasks.size() + 1, "Cage cleaning - porcupine", 10, 24));
    // // size
    // }

    /**
     * Adds the inferred treatments to the treatments ArrayList
     * 
     * @params none
     * @return void
     */
    // private void addInferredTreatments() {
    // for (Animal animal : animals) {
    // if (animal.getAnimalType() == AnimalType.COYOTE) {
    // addCoyoteTreatments(animal);
    // } else if (animal.getAnimalType() == AnimalType.FOX) {
    // addFoxTreatments(animal);
    // } else if (animal.getAnimalType() == AnimalType.RACCOON) {
    // addRaccoonTreatments(animal);
    // } else if (animal.getAnimalType() == AnimalType.BEAVER) {
    // addBeaverTreatments(animal);
    // } else if (animal.getAnimalType() == AnimalType.PORCUPINE) {
    // addPorcupineTreatments(animal);
    // }

    // }
    // }

    /**
     * Adds the inferred treatments for a coyote to the treatments ArrayList
     * 
     * @param animal
     * @return void
     */
    private void addCoyoteTreatments(Animal animal) {
        if (!animal.getOrphaned()) {
            treatments.add(new Treatment(animal.getAnimalID(), tasks.size() - 9,
                    animal.getAnimalFeedingType().getFeedStartTime()));
        }
        treatments.add(new Treatment(animal.getAnimalID(), tasks.size() - 4, 0));
    }

    /**
     * Adds the inferred treatments for a fox to the treatments ArrayList
     * 
     * @param animal
     * @return void
     */
    private void addFoxTreatments(Animal animal) {
        if (!animal.getOrphaned()) {
            treatments.add(new Treatment(animal.getAnimalID(), tasks.size() - 8,
                    animal.getAnimalFeedingType().getFeedStartTime()));
        }
        treatments.add(new Treatment(animal.getAnimalID(), tasks.size() - 3, 0));
    }

    /**
     * Adds the inferred treatments for a raccoon to the treatments ArrayList
     * 
     * @param animal
     * @return void
     */
    private void addRaccoonTreatments(Animal animal) {
        if (!animal.getOrphaned()) {
            treatments.add(new Treatment(animal.getAnimalID(), tasks.size() - 7,
                    animal.getAnimalFeedingType().getFeedStartTime()));
        }
        treatments.add(new Treatment(animal.getAnimalID(), tasks.size() - 2, 0));
    }

    /**
     * Adds the inferred treatments for a beaver to the treatments ArrayList
     * 
     * @param animal
     * @return void
     */
    private void addBeaverTreatments(Animal animal) {
        if (!animal.getOrphaned()) {
            treatments.add(new Treatment(animal.getAnimalID(), tasks.size() - 6,
                    animal.getAnimalFeedingType().getFeedStartTime()));
        }
        treatments.add(new Treatment(animal.getAnimalID(), tasks.size() - 1, 0));
    }

    /**
     * Adds the inferred treatments for a porcupine to the treatments ArrayList
     * 
     * @param animal
     * @return void
     */
    private void addPorcupineTreatments(Animal animal) {
        if (!animal.getOrphaned()) {
            treatments.add(new Treatment(animal.getAnimalID(), tasks.size() - 5,
                    animal.getAnimalFeedingType().getFeedStartTime()));
        }
        treatments.add(new Treatment(animal.getAnimalID(), tasks.size(), 0));
    }

    /**
     * Validates scheduling a treatment
     * 
     * @throws ImpossibleScheduleException if the treatment cannot be scheduled
     * @params none
     * @return true if the treatment can be scheduled with an extra volunteer
     * @return false if the treatment can be scheduled without
     */
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

        // If the time taken for any start hour within the max window impossible
        // even with an extra volunteer, throw an exception with a description of the
        // issue
        throw new ImpossibleScheduleException("Impossible to schedule " +
                tasks.get(treatment.getTaskID() - 1).getDescription() + " at hour: "
                + treatment.getStartHour() +
                " because it would exceed the maximum time even with an extra volunteer.");
    }

    private void addTreatmentWithPrepTime(Treatment treatment) {
        // Determine if feedings must be split over multiple hourse or not
        // if (scheduledTasks.containsValue(treatment.getTaskID()))
        // if (!scheduledTasks.containsKey(treatment.getStartHour())) {
        // tasks.get(treatment.getTaskID() - 1).setDuration(15);
        // }
        // int preptime;
        // if (tasks.get(treatment))
    }

}
