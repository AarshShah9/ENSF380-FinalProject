package edu.ucalgary.oop;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Define a class called "Scheduler" that will be used to calculate the daily
 * schedule
 * 
 * @version 1.0
 * @author Aarsh Shah: (UCID: 30150079) - William Fraser: (UCID: 30158991) -
 *         Sarim Sheikh: (UCID: 30143892) - Nicola Savino (UCID: 30129329)
 * @date 2023-04-05
 */
public class Scheduler {
    private ArrayList<Task> tasks;
    private ArrayList<Animal> animals;
    private ArrayList<Treatment> treatments;
    private final LocalDate DATE;
    private DailySchedule dailySchedule;
    private SQLDatabase db;

    // public static void main(String[] args) {
    // Scheduler newSceduleObj = new Scheduler(LocalDate.now(), new
    // ArrayList<Task>(),
    // new ArrayList<Treatment>(), new ArrayList<Animal>());
    // newSceduleObj.getFromSQL("root", "password");

    // // System.out.println(newSceduleObj.getAnimals().get(0).getAnimalName());
    // newSceduleObj.calculateSchedule();
    // }

    /**
     * This method is the constructor for the Scheduler class which simply sets the
     * date, tasks, treatments, and animals members
     * 
     * @param day        the date of the schedule
     * @param tasks      the tasks of the schedule
     * @param treatments the treatments of the schedule
     * @param animals    the animals of the schedule
     */
    public Scheduler(LocalDate day, ArrayList<Task> tasks, ArrayList<Treatment> treatments, ArrayList<Animal> animals) {
        this.DATE = day;
        this.animals = animals;
        this.tasks = tasks;
        this.treatments = treatments;
    }

    /**
     * This method gets the data from the SQL database
     * 
     * @param user     the username of the database
     * @param password the password of the database
     * @throws IllegalArgumentException if the username or password is incorrect
     */
    public void getFromSQL(String user, String password) throws IllegalArgumentException {
        try {
            // initialize the database and passes it the username, password, and member
            // lists
            this.db = new SQLDatabase("EWR", user, password, animals, tasks, treatments);

        } catch (Exception e) {

            System.out.println("SQLDatabaseException caught: " + e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * This method calculates the daily schedule
     * 
     * @return a string that says if the schedule was calculated successfully or not
     */
    public String calculateSchedule() {
        try {
            // initialize the daily schedule and passes it the animals, tasks, and
            // treatments members
            this.dailySchedule = new DailySchedule(animals, tasks, treatments, DATE);
            return "Success";
        } catch (ImpossibleScheduleException e) {
            return e.getMessage();

        } catch (Exception e) {
            return "IOException caught: " + e.getMessage();
        }
    }

    /**
     * This method allows the user to change the start hour of a task and updates it
     * accordingly in the database
     * 
     * @param currentStartHour the current start hour of the task
     * @param treatmentID      the treatment ID of the task
     * @param newStartHour     the new start hour of the task
     */
    public void changeTreatmentStart(int currentStartHour, int treatmentID, int newStartHour) {
        try {
            for (Treatment treatment : treatments) {
                if (treatment.getTreatmentID() == treatmentID && treatment.getStartHour() == currentStartHour) {
                    try {
                        this.db.updateDatabase(treatmentID, newStartHour);
                        treatment.setStartHour(newStartHour);
                    } catch (Exception e) {
                        System.out.println("SQLDatabaseException caught: " + e.getMessage());
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("newStartHour must be between 0 and 23 it was " + newStartHour);
        } catch (Exception e) {
            System.out.println("IOException caught: " + e.getMessage());
        }
    }

    /**
     * This method gets the volunteers needed
     * 
     * @return an array of booleans for each hour of the day
     */
    public boolean[] getVolunteersNeeded() {
        if (dailySchedule != null) {
            return dailySchedule.getVolunteersNeeded();
        } else {
            return null;
        }
    }

    /**
     * This method gets the database object
     * 
     * @return the database object
     */
    public SQLDatabase getDB() {
        return db;
    }

    /**
     * This method gets the date
     * 
     * @return the date
     */
    public LocalDate getDate() {
        return DATE;
    }

    /**
     * This method gets the animals
     * 
     * @return an ArrayList of animals
     */
    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    /**
     * This method sets the animals
     * 
     * @param newAnimals the animals to set
     */
    public void setAnimals(ArrayList<Animal> newAnimals) {
        this.animals = newAnimals;
    }

    /**
     * This method gets the tasks
     * 
     * @return an ArrayList of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * This method sets the tasks
     * 
     * @param newTasks the tasks to set
     */
    public void setTasks(ArrayList<Task> newTasks) {
        this.tasks = newTasks;
    }

    /**
     * This method gets the treatments
     * 
     * @return an ArrayList of treatments
     */
    public ArrayList<Treatment> getTreatments() {
        return treatments;
    }

    /**
     * This method sets the treatments
     * 
     * @param newTreatments the treatments to set
     */
    public void setTreatments(ArrayList<Treatment> newTreatments) {
        this.treatments = newTreatments;
    }

    /**
     * This method gets the daily schedule
     * 
     * @return the daily schedule
     */
    public DailySchedule getDailySchedule() {
        return dailySchedule;
    }

    /**
     * This method sets the daily schedule
     * 
     * @param newDailySchedule the daily schedule to set
     */
    public void setDailySchedule(DailySchedule newDailySchedule) {
        this.dailySchedule = newDailySchedule;
    }
}
