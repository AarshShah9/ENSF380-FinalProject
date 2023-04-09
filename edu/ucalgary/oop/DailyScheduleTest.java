package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.HashMap;

public class DailyScheduleTest {
    /**
     * @version 1.0
     * @author Aarsh Shah: (UCID: 30150079) - William Fraser: (UCID: 30158991) -
     *         Sarim Sheikh: (UCID: 30143892) - Nicola Savino (UCID: 30129329)
     * @date 2023-03-21
     * @Description Tests the DailySchedule class
     */

    // Tests the constructor with good input
    @Test
    public void testConstructorGoodInput() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<Animal>();
        ArrayList<Treatment> treatments = new ArrayList<Treatment>();
        animals.add(new Beaver(1, "Beaver"));
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(1, "Rebandage leg wound", 20, 1));
        treatments.add(new Treatment(1, 1, 1, 3));
        HashMap<Integer, ArrayList<ScheduleItem>> expected = new HashMap<Integer, ArrayList<ScheduleItem>>();
        ArrayList<String> name = new ArrayList<String>();
        name.add("Beaver");
        ArrayList<ScheduleItem> expectedItem1 = new ArrayList<ScheduleItem>();
        expectedItem1.add(new ScheduleItem(name, 1, "Cage cleaning - beaver", 0, 24, 5, 0));
        ArrayList<ScheduleItem> expectedItem2 = new ArrayList<ScheduleItem>();
        expectedItem2.add(new ScheduleItem(name, 1, "Rebandage leg wound", 3, 1, 20, 0));
        ArrayList<ScheduleItem> expectedItem3 = new ArrayList<ScheduleItem>();
        expectedItem3.add(new ScheduleItem(name, 1, "Feeding - beaver", 8, 3, 5, 0));
        expected.put(0, expectedItem1);
        expected.put(3, expectedItem2);
        expected.put(8, expectedItem3);
        try {
            DailySchedule schedule = new DailySchedule(animals, tasks, treatments, date);
            assertEquals(expected.size(), schedule.getScheduledTasks().size());
            assertEquals(expectedItem1, schedule.getScheduledTasks().get(0));
            assertEquals(expectedItem2, schedule.getScheduledTasks().get(3));
            assertEquals(expectedItem3, schedule.getScheduledTasks().get(8));
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    // Tests that the constructor will throw an exception if the schedule is not
    // possilbe
    // Tests based on having 6 25 min tasks that must be done in one hour
    // Even with an extra volunteer, this is not possible
    @Test
    public void testConstructorBadInput() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<Animal>();
        animals.add(new Coyote(1, "coyote1"));
        animals.add(new Coyote(2, "coyote2"));
        animals.add(new Coyote(3, "coyote3"));
        animals.add(new Coyote(4, "coyote4"));
        animals.add(new Coyote(5, "coyote5"));
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(5, "Flush neck wound", 25, 1));
        ArrayList<Treatment> treatments = new ArrayList<Treatment>();
        treatments.add(new Treatment(1, 1, 5, 0));
        treatments.add(new Treatment(1, 2, 5, 0));
        treatments.add(new Treatment(1, 3, 5, 0));
        treatments.add(new Treatment(1, 4, 5, 0));
        treatments.add(new Treatment(1, 5, 5, 0));
        try {
            DailySchedule schedule = new DailySchedule(animals, tasks, treatments, date);
        } catch (ImpossibleScheduleException e) {
            assertEquals("Impossible schedule", e.getMessage());
        } catch (Exception e) {
        }
    }

    // Tests the getter for the current date
    @Test
    public void testGetCurrDate() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<Animal>();
        ArrayList<Treatment> treatments = new ArrayList<Treatment>();
        animals.add(new Fox(7, "Slinky"));
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(2, "Rebandage leg wound", 20, 1));
        tasks.add(new Task(11, "feed fox", 10, 3));
        try {
            DailySchedule schedule = new DailySchedule(animals, tasks, treatments, date);
            assertEquals(date, schedule.getDate());
        } catch (Exception e) {
        }
    }

    // Tests the getter for the animals arraylist
    @Test
    public void testGetAnimals() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> expected = new ArrayList<Animal>();
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Treatment> treatments = new ArrayList<Treatment>();
        ArrayList<Animal> animals = new ArrayList<Animal>();
        Fox fox = new Fox(1, "Slinky");
        animals.add(fox);
        expected.add(fox);
        try {
            DailySchedule schedule = new DailySchedule(animals, tasks, treatments, date);
            assertEquals(expected, schedule.getAnimals());
        } catch (Exception e) {
        }
    }

    // Tests the getter for the tasks arraylist
    @Test
    public void testGetTasks() {
        LocalDate date = LocalDate.now();
        ArrayList<Task> expected = new ArrayList<Task>();
        ArrayList<Animal> animals = new ArrayList<Animal>();
        ArrayList<Treatment> treatments = new ArrayList<Treatment>();
        ArrayList<Task> tasks = new ArrayList<Task>();
        Task task = new Task(1, "Rebandage leg wound", 20, 1);
        tasks.add(task);
        expected.add(task);
        try {
            DailySchedule schedule = new DailySchedule(animals, tasks, treatments, date);
            assertEquals(expected, schedule.getTasks());
        } catch (Exception e) {
        }
    }

    // Tests the getter for the treatments arraylist
    @Test
    public void testGetTreatments() {
        LocalDate date = LocalDate.now();
        ArrayList<Treatment> expected = new ArrayList<Treatment>();
        ArrayList<Animal> animals = new ArrayList<Animal>();
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Treatment> treatments = new ArrayList<Treatment>();
        treatments.add(new Treatment(1, 1, 1, 0));
        expected.add(new Treatment(1, 1, 1, 0));
        try {
            DailySchedule schedule = new DailySchedule(animals, tasks, treatments, date);
            assertEquals(expected, schedule.getTreatments());
        } catch (Exception e) {
        }
    }

    // Tests the getVolunteersNeeded method
    // Tests by adding a task that takes 70 mins, so will therefore require a bonus
    // volunteer
    // Also test that there will be no bonus volunteer needed given a task that
    // takes 20 mins
    @Test
    public void testGetVolunteersNeeded() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<Animal>();
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Treatment> treatments = new ArrayList<Treatment>();
        ArrayList<Treatment> treatments2 = new ArrayList<Treatment>();
        ArrayList<Task> tasks2 = new ArrayList<Task>();
        animals.add(new Fox(1, "Slinky"));
        tasks.add(new Task(1, "Rebandage leg wound", 40, 1));
        tasks.add(new Task(2, "Somethign else", 40, 1));
        tasks2.add(new Task(1, "Rebandage leg wound", 20, 1));
        treatments.add(new Treatment(1, 1, 1, 7));
        treatments.add(new Treatment(1, 1, 2, 7));
        treatments2.add(new Treatment(1, 1, 1, 7));
        boolean[] expected = new boolean[24];
        expected[7] = true;
        boolean[] expected2 = new boolean[24];
        try {
            DailySchedule schedule = new DailySchedule(animals, tasks, treatments, date);
            DailySchedule schedule2 = new DailySchedule(animals, tasks2, treatments2, date);
            assertArrayEquals(expected, schedule.getVolunteersNeeded());
            assertArrayEquals(expected2, schedule2.getVolunteersNeeded());
        } catch (Exception e) {
        }
    }

    // Tests the setVolunteersNeeded method
    @Test
    public void testSetVolunteersNeeded() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<Animal>();
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Treatment> treatments = new ArrayList<Treatment>();
        boolean[] expected = new boolean[24];
        for (int i = 0; i < 24; i++) {
            if (i == 7)
                expected[i] = true;
        }
        try {
            DailySchedule schedule = new DailySchedule(animals, tasks, treatments, date);
            schedule.setVolunteersNeeded(expected);
            assertEquals(expected, schedule.getVolunteersNeeded());
        } catch (Exception e) {
        }
    }

    // Tests the getScheduledTasks method
    @Test
    public void testGetScheduledTasks() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<Animal>();
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Treatment> treatments = new ArrayList<Treatment>();
        animals.add(new Fox(1, "Slinky"));
        HashMap<Integer, ArrayList<ScheduleItem>> expected = new HashMap<Integer, ArrayList<ScheduleItem>>();
        ArrayList<ScheduleItem> items = new ArrayList<ScheduleItem>();
        ArrayList<String> name = new ArrayList<String>();
        name.add("Slinky");
        items.add(new ScheduleItem(name, 1, "Feeding - fox", 0, 3, 5, 5));
        items.add(new ScheduleItem(name, 1, "Cage cleaning - fox", 0, 24, 5, 0));
        expected.put(0, items);
        try {
            DailySchedule schedule = new DailySchedule(animals, tasks, treatments, date);
            for (int i = 0; i < 2; i++) {
                assertTrue(expected.get(0).get(i).getDescription()
                        .equals(schedule.getScheduledTasks().get(0).get(i).getDescription())
                        && expected.get(0).get(i).getDuration() == schedule.getScheduledTasks().get(0).get(i)
                                .getDuration()
                        && expected.get(0).get(i).getMaxWindow() == schedule.getScheduledTasks().get(0).get(i)
                                .getMaxWindow()
                        && expected.get(0).get(i).getPrepTime() == schedule.getScheduledTasks().get(0).get(i)
                                .getPrepTime()
                        && expected.get(0).get(i).getQuantity() == schedule.getScheduledTasks().get(0).get(i)
                                .getQuantity());
            }
        } catch (Exception e) {
        }
    }

    // Tests the setScheduledTasks method
    @Test
    public void testSetScheduledTasks() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<Animal>();
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Treatment> treatments = new ArrayList<Treatment>();
        HashMap<Integer, ArrayList<ScheduleItem>> expected = new HashMap<Integer, ArrayList<ScheduleItem>>();
        ArrayList<ScheduleItem> items = new ArrayList<ScheduleItem>();
        ArrayList<String> name = new ArrayList<String>();
        name.add("Slinky");
        items.add(new ScheduleItem(name, 1, "Feeding - fox", 0, 3, 5, 5));
        items.add(new ScheduleItem(name, 1, "Cage cleaning - fox", 0, 24, 5, 0));
        expected.put(0, items);
        try {
            DailySchedule schedule = new DailySchedule(animals, tasks, treatments, date);
            schedule.setScheduledTasks(expected);
            assertEquals(expected, schedule.getScheduledTasks());
        } catch (Exception e) {
        }
    }

    // Tests that feedings are split correctly if they take up too much time
    // Determines this by checking that the size of the two scheduled feedings are
    // equal
    @Test
    public void testFeedingSplit() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<Animal>();
        animals.add(new Coyote(1, "1"));
        animals.add(new Coyote(2, "2"));
        animals.add(new Coyote(3, "3"));
        animals.add(new Coyote(4, "4"));
        animals.add(new Coyote(5, "5"));
        animals.add(new Coyote(6, "6"));
        animals.add(new Coyote(7, "7"));
        animals.add(new Coyote(8, "8"));
        animals.add(new Coyote(9, "9"));
        animals.add(new Coyote(10, "10"));
        animals.add(new Coyote(11, "11"));
        animals.add(new Coyote(12, "12"));
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Treatment> treatments = new ArrayList<Treatment>();
        ArrayList<String> names = new ArrayList<String>();
        try {
            DailySchedule schedule = new DailySchedule(animals, tasks, treatments, date);
            assertEquals(schedule.getScheduledTasks().get(19).size(),
                    schedule.getScheduledTasks().get(20).size());
        } catch (Exception e) {
        }
    }

}
