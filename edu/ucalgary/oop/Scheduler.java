package edu.ucalgary.oop;

import java.time.LocalDate;
import java.util.ArrayList;

public class Scheduler {
    private LocalDate currDate;
    private ArrayList<DailySchedule> schedules;

    public Scheduler(String[] animals, String[] tasks) throws IllegalArgumentException {
        // TODO implement constructor
        // Change this up, it's just a placeholder to avoid final variable not
        // initialized error
        this.currDate = LocalDate.now();
        this.schedules = new ArrayList<DailySchedule>();
    }

    public void printOutSchedule(LocalDate chosenDate) {
        // TODO implement method
    }

    public void createFile() {
        // TODO implement method
    }

    public ArrayList<DailySchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(ArrayList<DailySchedule> newSchedules) {
        this.schedules = newSchedules;
    }

    public void addSchedule(DailySchedule newSchedule) {
        this.schedules.add(newSchedule);
    }

    public LocalDate getCurrDate() {
        return currDate;
    }

    public void setCurrDate(LocalDate newCurrDate) {
        this.currDate = newCurrDate;
    }

}
