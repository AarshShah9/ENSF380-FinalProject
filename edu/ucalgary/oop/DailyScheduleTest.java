package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;

public class DailyScheduleTest {
    /**
     * @version 1.0
     * @author Will
     * @date 2023-03-21
     * @Description Tests the DailySchedule class
     */

    // Tests the constructor with good input
    // Uses example data from sql and expected output from the txt file
    @Test
    public void testConstructorGoodInput() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<Animal>();
        ArrayList<Treatment> treatments = new ArrayList<Treatment>();
        animals.add(new Fox(7, "Slinky", "fox"));
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(2, "Rebandage leg wound", 20, 1));
        tasks.add(new Task(11, "feed fox", 10, 3));
        DailySchedule schedule = new DailySchedule(animals, tasks, treatments, date);
        assertEquals(2, schedule);
        assertEquals(date, schedule);
        assertEquals("00:00\n* Feeding - fox (Slinky)\n", schedule);
        assertEquals("19:00\n* Rebandage leg wound (Slinky)\n", schedule);
    }

    // Tests that the constructor will throw an exception if the schedule is not
    // possilbe
    // Tests based on having 6 25 min tasks that must be done in one hour
    // Even with an extra volunteer, this is not possible
    // @Test(expected = ImpossibleScheduleException.class)
    // public void testConstructorBadInput() {
    // LocalDate date = LocalDate.now();
    // ArrayList<Animal> animals = new ArrayList<Animal>();
    // animals.add(new Coyote(5, "Eraser1", "coyote", "CREPUSCULAR", false));
    // animals.add(new Coyote(5, "Eraser2", "coyote", "CREPUSCULAR", false));
    // animals.add(new Coyote(5, "Eraser3", "coyote", "CREPUSCULAR", false));
    // animals.add(new Coyote(5, "Eraser4", "coyote", "CREPUSCULAR", false));
    // animals.add(new Coyote(5, "Eraser5", "coyote", "CREPUSCULAR", false));
    // animals.add(new Coyote(5, "Eraser6", "coyote", "CREPUSCULAR", false));
    // ArrayList<Task> tasks = new ArrayList<Task>();
    // tasks.add(new Task(5, "Flush neck wound", 25, 1, "MEDICAL"));
    // tasks.add(new Task(5, "Flush neck wound", 25, 1, "MEDICAL"));
    // tasks.add(new Task(5, "Flush neck wound", 25, 1, "MEDICAL"));
    // tasks.add(new Task(5, "Flush neck wound", 25, 1, "MEDICAL"));
    // tasks.add(new Task(5, "Flush neck wound", 25, 1, "MEDICAL"));
    // tasks.add(new Task(5, "Flush neck wound", 25, 1, "MEDICAL"));
    // DailySchedule schedule = new DailySchedule(animals, tasks, LocalDate.now());
    // }

    // Tests the getter for the current date
    @Test
    public void testGetCurrDate() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<Animal>();
        ArrayList<Treatment> treatments = new ArrayList<Treatment>();
        animals.add(new Fox(7, "Slinky", "fox"));
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(2, "Rebandage leg wound", 20, 1));
        tasks.add(new Task(11, "feed fox", 10, 3));
        DailySchedule schedule = new DailySchedule(animals, tasks, treatments, date);
        assertEquals(date, schedule);
    }

    // Tests the getter for the task and time ArrayList
    @Test
    public void testGetTaskAndTime() {
        LocalDate date = LocalDate.now();
        ArrayList<String> expected = new ArrayList<String>();
        ArrayList<Treatment> treatments = new ArrayList<Treatment>();

        expected.add("00:00\n* Feeding - fox (Slinky)\n");
        expected.add("19:00\n* Rebandage leg wound (Slinky)\n");
        ArrayList<Animal> animals = new ArrayList<Animal>();
        animals.add(new Fox(7, "Slinky", "fox"));
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(2, "Rebandage leg wound", 20, 1));
        tasks.add(new Task(11, "feed fox", 10, 3));
        DailySchedule schedule = new DailySchedule(animals, tasks, treatments, date);
        assertEquals(expected, schedule);
    }

}
