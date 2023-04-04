package edu.ucalgary.oop;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Iterator;
import java.io.*;
import java.util.Arrays;

/**
 * The DailySchedule class represents a schedule for a given day
 * 
 * @author William Fraser
 * @since 2023-03-30
 * @version 1.0
 */
public class DailySchedule {
    private final LocalDate CURR_DATE;
    private HashMap<Integer, ArrayList<ScheduleItem>> scheduledTasks = new HashMap<Integer, ArrayList<ScheduleItem>>();
    private ArrayList<Animal> animals;
    private ArrayList<Task> tasks;
    private ArrayList<Treatment> treatments;

    private boolean[] bonusVolunteers = new boolean[24];

    private ArrayList<ScheduleItem> scheduleItems = new ArrayList<ScheduleItem>();

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
            LocalDate date) throws ImpossibleScheduleException, IOException {
        this.CURR_DATE = date;
        this.animals = animals;
        this.treatments = treatments;
        this.tasks = tasks;

        // Add treatments to the scheduledItems array
        addTreatments();

        // Add inferred tasks to the scheduledItems array
        addInferredTasks();

        // groups the feeding that have prep times
        combineFeedings(scheduleItems, "Feeding - coyote");
        combineFeedings(scheduleItems, "Feeding - fox");

        // schedules the tasks
        scheduleTasks();

        // groups the tasks that have the same name
        groupLikeTasks();

        // creates a text file containing the schedule
        try {
            printSchedule();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public boolean[] getVolunteersNeeded() {
        return bonusVolunteers;
    }

    /**
     * adds all of the treatments to the scheduleItems array
     * 
     * @param none
     * @return void
     */
    private void addTreatments() {
        for (Treatment treatment : treatments) {
            ArrayList<String> name = new ArrayList<String>();
            name.add(animals.get(treatment.getAnimalID() - 1).getAnimalName());
            scheduleItems.add(new ScheduleItem(name, 1,
                    tasks.get(treatment.getTaskID() - 1).getDescription(),
                    treatment.getStartHour(),
                    tasks.get(treatment.getTaskID() - 1).getMaxWindow(),
                    tasks.get(treatment.getTaskID() - 1).getDuration(),
                    0));
        }
    }

    /**
     * adds all of the inferred tasks to the scheduleItems array
     * 
     * @param none
     * @return void
     */
    private void addInferredTasks() {
        for (Animal animal : animals) {
            scheduleItems.add(new ScheduleItem(new ArrayList<String>(Arrays.asList(animal.getAnimalName())),
                    1, "Cage cleaning - " + animal.getAnimalType().toString().toLowerCase(),
                    0, 24, animal.CLEAN_TIME, 0));
            if (!animal.getOrphaned())
                scheduleItems.add(new ScheduleItem(new ArrayList<String>(Arrays.asList(animal.getAnimalName())),
                        1, "Feeding - " + animal.getAnimalType().toString().toLowerCase(),
                        animal.ANIMAL_FEEDING_TYPE.getFeedStartTime(), animal.FEED_WINDOW,
                        animal.FEED_TIME, animal.FEED_PREP_TIME));
        }
    }

    /**
     * Groups the similar tasks together
     * 
     * @return void
     * @params none
     */
    private void combineFeedings(ArrayList<ScheduleItem> tasks, String description) {
        Iterator<ScheduleItem> it = tasks.iterator();
        ScheduleItem previous = null;
        if (it.hasNext())
            previous = it.next();
        while (it.hasNext()) {
            if (previous.getDescription().equals(description)) {
                ScheduleItem current = it.next();
                if (previous.getDescription().equals(current.getDescription())) {
                    previous.addName(current.getName());
                    previous.addQuantity(current.getQuantity());
                    previous.addDuration(current.getDuration());
                    it.remove();
                } else
                    continue;
            } else {
                previous = it.next();
            }
        }
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

        // If the start hour is not in the scheduledTasks HashMap, return false
        if (!scheduledTasks.containsKey(item.getStartHour()))
            return false;
        // If the start hour is in the scheduledTasks HashMap, check if the time taken
        // of that hour

        for (ScheduleItem task : scheduledTasks.get(item.getStartHour())) {
            timeTaken += task.getDuration() + task.getPrepTime();
        }
        // If the time taken is <= 60 minutes, return false
        if (timeTaken + item.getDuration() + item.getPrepTime() <= 60)
            return false;

        // loop tries to find a start hour within the max window that will not require
        // an extra volunteer
        int firstTimeTaken = timeTaken;
        while (addHour < maxWindow) {
            // If the time taken is greater than 60 minutes, check the rest of the hours
            // within the max window
            if (timeTaken + item.getDuration() + item.getPrepTime() > 60) {
                timeTaken = 0;
                if (scheduledTasks.containsKey(item.getStartHour() + addHour)) {
                    for (ScheduleItem task : scheduledTasks.get(item.getStartHour() + addHour)) {
                        timeTaken += task.getDuration() + task.getPrepTime();
                    }
                    addHour++;
                }
            }
            // If the time taken for any start hour within the max window is <= 60 minutes
            // then return false to the treatment at that start hour with no extra volunteer
            if (timeTaken + item.getDuration() + item.getPrepTime() <= 60) {
                item.setStartHour(item.getStartHour() + addHour);
                return false;
            } else
                continue;
        }
        // Check if it can be done with a bonus volunteer at original start hour.
        if (item.getDuration() + item.getPrepTime() + firstTimeTaken <= 120) {
            return true;
        }
        // Loop will now try to find a start hour within the max hour with an extra
        // volunteer
        addHour = 1;
        timeTaken = firstTimeTaken;
        while (addHour < maxWindow) {
            if (timeTaken + item.getDuration() + item.getPrepTime() > 120) {
                timeTaken = 0;
                if (scheduledTasks.containsKey(item.getStartHour() + addHour)) {
                    for (ScheduleItem task : scheduledTasks.get(item.getStartHour())) {
                        timeTaken += task.getDuration() + task.getPrepTime();
                    }
                    addHour++;
                }
            }
            if (timeTaken + item.getDuration() + item.getPrepTime() > 60 &&
                    timeTaken + item.getDuration() + item.getPrepTime() <= 120) {
                item.setStartHour(item.getStartHour() + addHour);
                return true;
            } else
                continue;
        }

        // If the time taken for any start hour within the max window impossible
        // even with an extra volunteer, throw an exception with a description of the
        // issue
        String message = "Impossible to schedule " +
                item.getDescription() + " - " + item.getName().toString() +
                " at hour: " + item.getStartHour() +
                " because it would exceed the maximum time even with an extra volunteer.\n" +
                "Try changing the start hour of ";
        for (ScheduleItem task : scheduledTasks.get(item.getStartHour())) {
            message += task.getDescription() + " - " +
                    task.getName().toString() + ", ";
        }
        message += "or " + item.getDescription() + " - " +
                item.getName().toString() + ".";

        throw new ImpossibleScheduleException(message);
    }

    /**
     * Splits a feeding block into two feedings and appends them to the
     * end of the scheduleItems list
     * 
     * @param ScheduleItem item
     * @return void
     */
    private void splitFeeding(ScheduleItem item) {
        int numOne = item.getQuantity() / 2;
        int numTwo = item.getQuantity() - numOne;
        ArrayList<String> name1 = new ArrayList<String>();
        ArrayList<String> name2 = new ArrayList<String>();
        for (int i = 0; i < numOne; i++) {
            name1.add(item.getName().get(i));
        }
        for (int i = numOne; i < item.getQuantity(); i++) {
            name2.add(item.getName().get(i));
        }
        ScheduleItem item1 = new ScheduleItem(name1, numOne,
                item.getDescription(), item.getStartHour(), item.getMaxWindow(),
                item.getDuration() / 2, item.getPrepTime());

        ScheduleItem item2 = new ScheduleItem(name2, numTwo,
                item.getDescription(), item.getStartHour() + item.getDuration() / 2,
                item.getMaxWindow(), item.getDuration() / 2 + item.getDuration() % 2,
                item.getPrepTime());
        scheduleItems.remove(item);
        scheduleItems.add(item1);
        scheduleItems.add(item2);
    }

    /**
     * Groups all like tasks in scheduledTasks
     * 
     * @param none
     * @return void
     */
    private void groupLikeTasks() {
        for (int i = 0; i < 24; i++) {
            if (scheduledTasks.containsKey(i)) {
                Iterator<ScheduleItem> it = scheduledTasks.get(i).iterator();
                ScheduleItem previous = null;
                if (it.hasNext())
                    previous = it.next();
                while (it.hasNext()) {
                    ScheduleItem current = it.next();
                    if (previous.getDescription().equals(current.getDescription())) {
                        previous.addName(current.getName());
                        previous.addQuantity(current.getQuantity());
                        previous.addDuration(current.getDuration());
                        it.remove();

                    } else
                        previous = current;
                }
            }
        }
    }

    /**
     * Schedules the tasks.
     * This method will schedule the tasks in the scheduleItems arraylist into the
     * scheduledTasks hashmap, with the hour as the key and the tasks as the value.
     * 
     * Schedules the tasks with the smallest window first
     * If the grouped feedings (coyote and fox) are not able to be scheduled
     * they will be split and then retried.
     * 
     * @throws ImpossibleScheduleException
     * @returns void
     */
    private void scheduleTasks() throws ImpossibleScheduleException {
        ArrayList<Integer> maxWindowArr = getMaxWindows();
        int i = 0;
        while (scheduleItems.size() > 0) {
            int maxWindow = maxWindowArr.get(i);
            Iterator<ScheduleItem> it = scheduleItems.iterator();
            while (it.hasNext()) {
                ScheduleItem item = it.next();
                if (item.getMaxWindow() == maxWindow) {
                    if (item.getDescription() == "Feeding - coyote" ||
                            item.getDescription() == "Feeding - fox") {
                        try {
                            bonusVolunteers[item.getStartHour()] = validateAddition(item);
                            if (bonusVolunteers[item.getStartHour()])
                                splitFeeding(item);
                            else {
                                if (scheduledTasks.get(item.getStartHour()) == null) {
                                    scheduledTasks.put(item.getStartHour(), new ArrayList<ScheduleItem>());
                                }
                                scheduledTasks.get(item.getStartHour()).add(item);
                                it.remove();
                                continue;
                            }
                        } catch (ImpossibleScheduleException e) {
                            splitFeeding(item);
                            e.printStackTrace();
                            continue;
                        }
                    }
                    bonusVolunteers[item.getStartHour()] = validateAddition(item);
                    if (scheduledTasks.get(item.getStartHour()) == null) {
                        scheduledTasks.put(item.getStartHour(), new ArrayList<ScheduleItem>());
                    }
                    scheduledTasks.get(item.getStartHour()).add(item);
                    it.remove();
                } else
                    continue;
            }
            i++;
        }

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
     * Creates a txt file that contains the schedule
     * 
     * @params none
     * @return void
     */
    private void printSchedule() throws IOException {
        File file = new File("schedule.txt");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Schedule for " + CURR_DATE.toString() + "\n\n");
        for (int i = 0; i < 24; i++) {
            if (scheduledTasks.containsKey(i)) {
                if (bonusVolunteers[i])
                    bw.write(LocalTime.of(i, 0).toString() + "[+ Backup Volunteer]\n");
                else
                    bw.write(LocalTime.of(i, 0).toString() + "\n");

                Iterator<ScheduleItem> it = scheduledTasks.get(i).iterator();
                while (it.hasNext()) {
                    ScheduleItem item = it.next();

                    bw.write(String.format("* %s (%d: %s)\n",
                            item.getDescription(), item.getQuantity(),
                            String.join(", ", item.getName())));
                }
                bw.newLine();
            }
        }
        bw.close();
        System.out.println("Sucess");
    }

    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<Animal>();
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Treatment> treatments = new ArrayList<Treatment>();
        animals.add(new Coyote(1, "fox"));
        tasks.add(new Task(1, "medical", 5, 3));
        treatments.add(new Treatment(1, 1, 5));
        DailySchedule schedule;
        try {
            schedule = new DailySchedule(animals, tasks, treatments, LocalDate.now());

        } catch (ImpossibleScheduleException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("IOError");
        }
    }

    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<Animal>();
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Treatment> treatments = new ArrayList<Treatment>();
        animals.add(new Coyote(1, "fox"));
        tasks.add(new Task(1, "medical", 5, 3));
        treatments.add(new Treatment(1, 1, 5));
        DailySchedule schedule;
        try {
            schedule = new DailySchedule(animals, tasks, treatments, LocalDate.now());

        } catch (ImpossibleScheduleException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("IOError");
        }
    }
}
