package edu.ucalgary.oop;

public class ScheduleItem {
    private String name;
    private int quantity;
    private final String DESCRIPTION;
    private int startHour;
    private final int MAX_WINDOW;
    private int duration;
    private int prepTime;

    public ScheduleItem(String name, int quantity, String description, int startHour, int maxWindow, int duration,
            int prepTime) {
        this.name = name;
        this.quantity = quantity;
        this.DESCRIPTION = description;
        this.startHour = startHour;
        this.MAX_WINDOW = maxWindow;
        this.duration = duration;
        this.prepTime = prepTime;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return DESCRIPTION;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getMaxWindow() {
        return MAX_WINDOW;
    }

    public int getDuration() {
        return duration;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }
}
