package edu.ucalgary.oop;

import java.util.ArrayList;

public class ScheduleItem {
    /**
     * The ScheduleItem class is an object that holds all the information
     * that is used to schedule all treatments and tasks for a given day
     * 
     * @author Will
     * @version 1.0
     * @date 2023-04-02
     */
    private ArrayList<String> name;
    private int quantity;
    private final String DESCRIPTION;
    private int startHour;
    private final int MAX_WINDOW;
    private int duration;
    private int prepTime;

    /**
     * Constructs a ScheduleItem object
     * 
     * @param name        - the names of the animals that are being treated/ the
     *                    task that is being performed
     * @param quantity    - the number of animals that the task/treatment is being
     *                    performed on
     * @param description - the description of the task/treatment
     * @param startHour   - the hour that the task/treatment is scheduled to start
     * @param maxWindow   - the maximum amount of time that the task/treatment can
     *                    be delayed
     * @param duration    - the duration of the task/treatment
     * @param prepTime    - the amount of time that is required to prepare for the
     *                    task/treatment
     */
    public ScheduleItem(ArrayList<String> name, int quantity, String description, int startHour, int maxWindow,
            int duration, int prepTime) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
        this.quantity = quantity;
        this.DESCRIPTION = description;
        this.startHour = startHour;
        this.MAX_WINDOW = maxWindow;
        this.duration = duration;
        this.prepTime = prepTime;
    }

    /**
     * Returns the name(s) of the animals that are being treated/ the task that is
     * being performed
     * 
     * @return ArrayList<String>
     */
    public ArrayList<String> getName() {
        return name;
    }

    /**
     * Returns the number of animals that the task/treatment is being performed on
     * 
     * @return int
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the description of the task/treatment
     * 
     * @return String
     */
    public String getDescription() {
        return DESCRIPTION;
    }

    /**
     * Returns the hour that the task/treatment is scheduled to start
     * 
     * @return int
     */
    public int getStartHour() {
        return startHour;
    }

    /**
     * Returns the maximum amount of time that the task/treatment can be delayed
     * 
     * @return int
     */
    public int getMaxWindow() {
        return MAX_WINDOW;
    }

    /**
     * Returns the duration of the task/treatment
     * 
     * @return int
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Returns the amount of time that is required to prepare for the task/treatment
     * 
     * @return int
     */
    public int getPrepTime() {
        return prepTime;
    }

    /**
     * sets the name(s) of the animals that are being treated/ the task that is
     * being performed
     * 
     * @return void
     */
    public void setName(ArrayList<String> name) {
        this.name = name;
    }

    /**
     * sets the number of animals that the task/treatment is being performed on
     * 
     * @return void
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * sets the hour that the task/treatment is scheduled to start
     * 
     * @return void
     */
    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    /**
     * sets the duration of the task/treatment
     * 
     * @return void
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * sets the amount of time that is required to prepare for the task/treatment
     * 
     * @return void
     */
    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    /**
     * Adds the name of an animal to the name ArrayList
     * 
     * @param name - the name of the animal to be added
     * @return void
     */
    public void addName(ArrayList<String> name) {
        this.name.addAll(name);
    }

    /**
     * Adds the quantity of an animal to the quantity variable
     * 
     * @param quantity - the quantity of the animal to be added
     * @return void
     */
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    /**
     * Adds the duration of a task/treatment to the duration variable
     * 
     * @param duration - the duration of the task to be added
     * @return void
     */
    public void addDuration(int duration) {
        this.duration += duration;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ScheduleItem)) {
            return false;
        }
        ScheduleItem other = (ScheduleItem) obj;
        if (this.name.equals(other.name) && this.quantity == other.quantity
                && this.DESCRIPTION.equals(other.DESCRIPTION)
                && this.startHour == other.startHour && this.MAX_WINDOW == other.MAX_WINDOW
                && this.duration == other.duration && this.prepTime == other.prepTime) {
            return true;
        }
        return false;
    }
}
