/**
A class representing a treatment for an animal at a specific start hour
*/
package edu.ucalgary.oop;

/**
 * A class representing a treatment for an animal at a specific start hour
 * 
 * @version 2.0
 * @author Aarsh Shah: (UCID: 30150079) - William Fraser: (UCID: 30158991) -
 *         Sarim Sheikh: (UCID: 30143892) - Nicola Savino (UCID: 30129329)
 * @date 2023-04-05
 */
public class Treatment {
    private final int TREATMENT_ID; // the ID of the animal being treated
    private final int ANIMAL_ID; // the ID of the animal being treated
    private final int TASK_ID; // the ID of the task to be performed during the treatment
    private int startHour; // the hour at which the treatment starts

    /**
     * Constructs a new Treatment object with the given parameters
     * 
     * @param animalID  the ID of the animal being treated
     * @param taskID    the ID of the task to be performed during the treatment
     * @param startHour the hour at which the treatment starts
     * @throws IllegalArgumentException if the start hour is not between 0 and 23
     */
    public Treatment(int TreatmentID, int animalID, int taskID, int startHour) throws IllegalArgumentException {
        if (startHour < 0 || startHour > 23)
            throw new IllegalArgumentException("Start hour must be between 0 and 23 it was " + startHour);

        this.TREATMENT_ID = TreatmentID;
        this.ANIMAL_ID = animalID;
        this.TASK_ID = taskID;
        this.startHour = startHour;
    }

    /**
     * Returns the ID of the treatment
     * 
     * @return the treatment ID
     */
    public int getTreatmentID() {
        return TREATMENT_ID;
    }

    /**
     * Returns the ID of the animal being treated
     * 
     * @return the animal ID
     */
    public int getAnimalID() {
        return ANIMAL_ID;
    }

    /**
     * Returns the ID of the task to be performed during the treatment
     * 
     * @return the task ID
     */
    public int getTaskID() {
        return TASK_ID;
    }

    /**
     * Returns the hour at which the treatment starts
     * 
     * @return the start hour
     */
    public int getStartHour() {
        return startHour;
    }

    /**
     * Sets the hour at which the treatment starts
     * 
     * @param hour the new start hour
     */
    public void setStartHour(int hour) throws IllegalArgumentException {
        if (hour < 0 || hour > 23)
            throw new IllegalArgumentException("Start hour must be between 0 and 23 it was " + hour);
        this.startHour = hour;
    }
}