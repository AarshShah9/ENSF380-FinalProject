package edu.ucalgary.oop;

public class ScheduleItem {
    private String name;
    private int quantity;
    private String description;
    private int startHour;
    private int maxWindow;
    private int duration;
    private int prepTime;

    public ScheduleItem(String name, int quantity, String description, int startHour, int maxWindow, int duration,
            int prepTime) {
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.startHour = startHour;
        this.maxWindow = maxWindow;
        this.duration = duration;
        this.prepTime = prepTime;
    }
}
