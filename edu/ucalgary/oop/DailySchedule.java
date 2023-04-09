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
 * @author Aarsh Shah: (UCID: 30150079) - William Fraser: (UCID: 30158991) -
 *         Sarim Sheikh: (UCID: 30143892) - Nicola Savino (UCID: 30129329)
 * @since 2023-03-30
 * @version 1.0
 */
public class DailySchedule {
    private final LocalDate CURR_DATE;
    private HashMap<Integer, ArrayList<ScheduleItem>> scheduledTasks = new HashMap<Integer, ArrayList<ScheduleItem>>();
    private final ArrayList<Animal> ANIMALS;
    private final ArrayList<Task> TASKS;
    private final ArrayList<Treatment> TREATMENTS;
    private boolean[] volunteersNeeded = new boolean[24];

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
        this.ANIMALS = animals;
        this.TREATMENTS = treatments;
        this.TASKS = tasks;
        ArrayList<ScheduleItem> scheduleItems = new ArrayList<ScheduleItem>();

        // Add treatments to the scheduledItems array
        addTreatments(scheduleItems);

        // Add inferred tasks to the scheduledItems array
        addInferredTasks(scheduleItems);

        // groups the feeding that have prep times
        combineFeedings(scheduleItems, "Feeding - coyote");
        combineFeedings(scheduleItems, "Feeding - fox");

        // schedules the tasks
        scheduleTasks(scheduleItems);

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

    /**
     * adds all of the treatments to the scheduleItems array
     * 
     * @param scheduleItems the ArrayList of ScheduleItems
     * @return void
     */
    private void addTreatments(ArrayList<ScheduleItem> scheduleItems) {
        for (Treatment treatment : TREATMENTS) {
            ArrayList<String> name = new ArrayList<String>();
            name.add(ANIMALS.get(treatment.getAnimalID() - 1).getAnimalName());
            scheduleItems.add(new ScheduleItem(name, 1,
                    TASKS.get(treatment.getTaskID() - 1).getDescription(),
                    treatment.getStartHour(),
                    TASKS.get(treatment.getTaskID() - 1).getMaxWindow(),
                    TASKS.get(treatment.getTaskID() - 1).getDuration(),
                    0));
        }
    }

    /**
     * adds all of the inferred tasks to the scheduleItems array
     * 
     * @param scheduleItems the ArrayList of ScheduleItems
     * @return void
     */
    private void addInferredTasks(ArrayList<ScheduleItem> scheduleItems) {
        for (Animal animal : ANIMALS) {
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
     * @param tasks       the ArrayList of ScheduleItems
     * @param description the description of the task we are looking to group
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
     * @param ScheduleItem item the treatment to be scheduled
     * @return true if the treatment can be scheduled with an extra volunteer
     * @return false if the treatment can be scheduled without
     */
    private boolean validateAddition(ScheduleItem item) throws ImpossibleScheduleException {
        int timeTaken = 0;
        int addHour = 1;
        int maxWindow = item.getMaxWindow();
        // If the start hour is not in the hash map and the time is less than 60 return
        // false
        if (!scheduledTasks.containsKey(item.getStartHour()) && (item.getDuration() + item.getPrepTime() <= 60))
            return false;
        // If the start hour is not in the hash map and the time is less than 120 return
        // true
        else if (!scheduledTasks.containsKey(item.getStartHour()) && (item.getDuration() + item.getPrepTime() <= 120))
            return true;
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
     * Splits a feeding into two feedings that are then to be scheduled
     * at different times
     * 
     * @param ScheduleItem item
     * @return ArrayList<ScheduleItem> of the two feedings
     */
    private ArrayList<ScheduleItem> splitFeeding(ScheduleItem item) {
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
                item.getDescription(), item.getStartHour() + 1,
                item.getMaxWindow() - 1, item.getDuration() / 2 + item.getDuration() % 2,
                item.getPrepTime());
        ArrayList<ScheduleItem> temp = new ArrayList<ScheduleItem>();
        temp.add(item1);
        temp.add(item2);
        return temp;
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
     * @param scheduleItems the arraylist of ScheduleItems to be scheduled
     * @throws ImpossibleScheduleException
     * @returns void
     */
    private void scheduleTasks(ArrayList<ScheduleItem> scheduleItems) throws ImpossibleScheduleException {
        ArrayList<Integer> maxWindowArr = getMaxWindows(scheduleItems);
        for (Integer maxWindow : maxWindowArr) {
            Iterator<ScheduleItem> it = scheduleItems.iterator();
            while (it.hasNext()) {
                ScheduleItem item = it.next();
                if (item.getMaxWindow() == maxWindow) {
                    if (item.getPrepTime() > 0) {
                        try {
                            if (validateAddition(item)) {
                                ArrayList<ScheduleItem> splitItems = splitFeeding(item);
                                if (validateAddition(splitItems.get(0))) {
                                    volunteersNeeded[splitItems.get(0).getStartHour()] = true;
                                } else if (validateAddition(splitItems.get(1))) {
                                    volunteersNeeded[splitItems.get(1).getStartHour()] = true;
                                }
                                it.remove();
                                addSplitItems(splitItems);
                            } else {
                                if (scheduledTasks.get(item.getStartHour()) == null) {
                                    scheduledTasks.put(item.getStartHour(), new ArrayList<ScheduleItem>());
                                }
                                scheduledTasks.get(item.getStartHour()).add(item);
                                it.remove();
                            }
                        } catch (ImpossibleScheduleException e) {
                            ArrayList<ScheduleItem> splitItems = splitFeeding(item);
                            if (validateAddition(splitItems.get(0))) {
                                volunteersNeeded[splitItems.get(0).getStartHour()] = true;
                            } else if (validateAddition(splitItems.get(1))) {
                                volunteersNeeded[splitItems.get(1).getStartHour()] = true;
                            }
                            addSplitItems(splitItems);
                            it.remove();
                        }
                    } else {
                        if (validateAddition(item)) {
                            volunteersNeeded[item.getStartHour()] = true;
                        }
                        if (scheduledTasks.get(item.getStartHour()) == null) {
                            scheduledTasks.put(item.getStartHour(), new ArrayList<ScheduleItem>());
                        }
                        scheduledTasks.get(item.getStartHour()).add(item);
                        it.remove();
                    }
                }
            }
        }

    }

    /**
     * Adds the split items to the scheduledTasks hashmap
     * 
     * @return void
     * @param splitItems The arraylist of split items to be added
     */
    private void addSplitItems(ArrayList<ScheduleItem> splitItems) {
        for (int i = 0; i < splitItems.size(); i++) {
            if (scheduledTasks.get(splitItems.get(i).getStartHour()) == null) {
                scheduledTasks.put(splitItems.get(i).getStartHour(), new ArrayList<ScheduleItem>());
            }
            scheduledTasks.get(splitItems.get(i).getStartHour()).add(splitItems.get(i));
        }
    }

    /**
     * Returns an arraylist that contains all of the max windows for the tasks
     * in ascending order
     * Used to scehdule lowest max window tasks first
     * 
     * @params scheduleItems the arraylist of ScheduleItems to be scheduled
     * @return ArrayList<ArrayList<Treatment>> scheduledTasks
     */
    private ArrayList<Integer> getMaxWindows(ArrayList<ScheduleItem> scheduleItems) {
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
                if (volunteersNeeded[i])
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
    }

    // Getters and Setters

    /**
     * gets volunteersNeeded array
     * 
     * @param none
     * @return boolean[] volunteersNeeded
     */
    public boolean[] getVolunteersNeeded() {
        return volunteersNeeded;
    }

    /**
     * gets scheduledTasks hashmap
     * 
     * @param none
     * @return HashMap<Integer, ArrayList<ScheduleItem>> scheduledTasks
     */
    public HashMap<Integer, ArrayList<ScheduleItem>> getScheduledTasks() {
        return scheduledTasks;
    }

    /**
     * gets animals arraylist
     * 
     * @param none
     * @return ArrayList<Animal> ANIMALS
     */
    public ArrayList<Animal> getAnimals() {
        return ANIMALS;
    }

    /**
     * gets tasks arraylist
     * 
     * @param none
     * @return ArrayList<Task> TASKS
     */
    public ArrayList<Task> getTasks() {
        return TASKS;
    }

    /**
     * gets treatments arraylist
     * 
     * @param none
     * @return ArrayList<Treatment> TREATMENTS
     */
    public ArrayList<Treatment> getTreatments() {
        return TREATMENTS;
    }

    /**
     * gets date
     * 
     * @param none
     * @return LocalDate CURR_DATE
     */
    public LocalDate getDate() {
        return CURR_DATE;
    }

    /**
     * sets volunteersNeeded array
     * 
     * @param boolean[] volunteersNeeded
     * @return void
     */
    public void setVolunteersNeeded(boolean[] volunteersNeeded) {
        this.volunteersNeeded = volunteersNeeded;
    }

    /**
     * sets scheduledTasks hashmap
     * 
     * @param HashMap<Integer, ArrayList<ScheduleItem>> scheduledTasks
     * @return void
     */
    public void setScheduledTasks(HashMap<Integer, ArrayList<ScheduleItem>> scheduledTasks) {
        this.scheduledTasks = scheduledTasks;
    }

    // public static void main(String[] args) {
    // ArrayList<Animal> animals = new ArrayList<Animal>();
    // ArrayList<Task> tasks = new ArrayList<Task>();
    // ArrayList<Treatment> treatments = new ArrayList<Treatment>();
    // animals.add(new Coyote(1, "fox"));
    // tasks.add(new Task(1, "medical", 5, 3));
    // treatments.add(new Treatment(1, 1, 5));
    // DailySchedule schedule;
    // try {
    // schedule = new DailySchedule(animals, tasks, treatments, LocalDate.now());

    // } catch (ImpossibleScheduleException e) {
    // System.out.println(e.getMessage());
    // } catch (IOException e) {
    // System.out.println("IOError");
    // }
    // }

}
