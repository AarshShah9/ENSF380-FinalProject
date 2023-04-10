package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * @version 1.0
 * @author Aarsh Shah: (UCID: 30150079) - William Fraser: (UCID: 30158991) -
 *         Sarim Sheikh: (UCID: 30143892) - Nicola Savino (UCID: 30129329)
 * @date 2023-04-05
 */
public class SchedulerTest {

    // Test the Schedular constructor with valid data
    @Test
    public void testSchedulerConstructorValidData() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Treatment> treatments = new ArrayList<>();

        animals.add(new Raccoon(0, "Raccoon"));
        tasks.add(new Task(1, "Test", 20, 5));
        treatments.add(new Treatment(1, 1, 1, 1));

        Scheduler temp = new Scheduler(date, tasks, treatments, animals);
        assertEquals(date, temp.getDate());
        assertEquals(animals, temp.getAnimals());
        assertEquals(tasks, temp.getTasks());
        assertEquals(treatments, temp.getTreatments());
    }

    // Test the getDate function
    @Test
    public void testGetDate() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Treatment> treatments = new ArrayList<>();

        animals.add(new Raccoon(0, "Raccoon"));
        tasks.add(new Task(1, "Test", 20, 5));
        treatments.add(new Treatment(1, 1, 1, 1));

        Scheduler temp = new Scheduler(date, tasks, treatments, animals);
        LocalDate result = temp.getDate();
        assertEquals("getDate() value was incorrect: ", date, result);
    }

    // Test the getAnimals function
    @Test
    public void testGetAnimals() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Treatment> treatments = new ArrayList<>();

        animals.add(new Raccoon(0, "Raccoon"));
        tasks.add(new Task(1, "Test", 20, 5));
        treatments.add(new Treatment(1, 1, 1, 1));

        Scheduler temp = new Scheduler(date, tasks, treatments, animals);
        ArrayList<Animal> result = temp.getAnimals();
        System.out.println("getAnimals");
        assertEquals("getAnimals() value was incorrect: ", animals, result);
    }

    // Test the setAnimals function
    @Test
    public void testSetAnimals() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Treatment> treatments = new ArrayList<>();

        animals.add(new Raccoon(0, "Raccoon"));
        tasks.add(new Task(1, "Test", 20, 5));
        treatments.add(new Treatment(1, 1, 1, 1));

        Scheduler temp = new Scheduler(date, tasks, treatments, animals);

        Animal test = new Raccoon(5, "Remy");
        animals.set(0, test);
        temp.setAnimals(animals);
        ArrayList<Animal> result = temp.getAnimals();

        assertEquals("setAnimals() value was incorrect: ", animals, result);
    }

    // Test the getTasks function
    @Test
    public void testGetTasks() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Treatment> treatments = new ArrayList<>();

        animals.add(new Raccoon(0, "Raccoon"));
        tasks.add(new Task(1, "Test", 20, 5));
        treatments.add(new Treatment(1, 1, 1, 1));

        Scheduler temp = new Scheduler(date, tasks, treatments, animals);
        ArrayList<Task> result = temp.getTasks();
        assertEquals("getTasks() value was incorrect: ", tasks, result);
    }

    // Test the setTasks function
    @Test
    public void testSetTasks() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Treatment> treatments = new ArrayList<>();

        animals.add(new Raccoon(0, "Raccoon"));
        tasks.add(new Task(1, "Test", 20, 5));
        treatments.add(new Treatment(1, 1, 1, 1));

        Scheduler temp = new Scheduler(date, tasks, treatments, animals);

        Task test = new Task(5, "Test1", 2, 0);
        tasks.set(0, test);
        temp.setTasks(tasks);
        ArrayList<Task> result = temp.getTasks();

        assertEquals("setTasks() value was incorrect: ", tasks, result);
    }

    // Test the getTreatments function
    @Test
    public void testGetTreatments() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Treatment> treatments = new ArrayList<>();

        animals.add(new Raccoon(0, "Raccoon"));
        tasks.add(new Task(1, "Test", 20, 5));
        treatments.add(new Treatment(1, 1, 1, 1));

        Scheduler temp = new Scheduler(date, tasks, treatments, animals);
        ArrayList<Treatment> result = temp.getTreatments();
        assertEquals("getTreatments() value was incorrect: ", treatments, result);
    }

    // Test the setTreatments function
    @Test
    public void testSetTreatments() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Treatment> treatments = new ArrayList<>();

        animals.add(new Raccoon(0, "Raccoon"));
        tasks.add(new Task(1, "Test", 20, 5));
        treatments.add(new Treatment(1, 1, 1, 1));

        Scheduler temp = new Scheduler(date, tasks, treatments, animals);

        Treatment test = new Treatment(1, 0, 0, 0);
        treatments.set(0, test);
        temp.setTreatments(treatments);
        ArrayList<Treatment> result = temp.getTreatments();

        assertEquals("setTreatments() value was incorrect: ", treatments, result);
    }

    @Test
    public void testCalculateSchedulePossible() {
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
        Scheduler temp = new Scheduler(date, tasks, treatments, animals);
        String message = temp.calculateSchedule();
        assertEquals("The message returned is not 'Success'", "Success", message);
        assertEquals("The scheduledTasks hashmap is not the expected size",
                expected.size(), temp.getDailySchedule().getScheduledTasks().size());
        assertEquals("The schdeuled tasks at hour 0 are not the expected tasks",
                expectedItem1, temp.getDailySchedule().getScheduledTasks().get(0));
        assertEquals("The schdeuled tasks at hour 3 are not the expected tasks",
                expectedItem2, temp.getDailySchedule().getScheduledTasks().get(3));
        assertEquals("The schdeuled tasks at hour 8 are not the expected tasks",
                expectedItem3, temp.getDailySchedule().getScheduledTasks().get(8));
    }

    @Test
    public void testCalculateScheduleImpossible() {
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
        Scheduler temp = new Scheduler(date, tasks, treatments, animals);
        String message = temp.calculateSchedule();
        assertNotEquals("The message should not be Success", "Success", message);
        assertNull("The dailySchedule should be null", temp.getDailySchedule());
    }

    // Tests the getDailySchedule method
    @Test
    public void testGetDailySchedule() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Treatment> treatments = new ArrayList<>();

        animals.add(new Raccoon(0, "Raccoon"));
        tasks.add(new Task(1, "Test", 20, 1));
        treatments.add(new Treatment(1, 1, 1, 1));

        Scheduler temp = new Scheduler(date, tasks, treatments, animals);
        temp.calculateSchedule();
        DailySchedule result = temp.getDailySchedule();

        ArrayList<String> expectedName = new ArrayList<>();
        expectedName.add("Raccoon");
        ScheduleItem expected = new ScheduleItem(expectedName, 1, "Test", 1, 1, 20, 0);
        assertEquals("getDailySchedule should return a DailySchedule object with the correct ScheduleItem",
                expected, result.getScheduledTasks().get(1).get(0));
    }

    // Tests the setDailySchedule method
    @Test
    public void testSetDailySchedule() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Treatment> treatments = new ArrayList<>();

        animals.add(new Raccoon(0, "Raccoon"));
        tasks.add(new Task(1, "Test", 20, 5));
        treatments.add(new Treatment(1, 1, 1, 1));

        Scheduler temp = new Scheduler(date, tasks, treatments, animals);
        try {
            DailySchedule expected = new DailySchedule(animals, tasks, treatments, date);
            temp.setDailySchedule(expected);
            DailySchedule result = temp.getDailySchedule();
            assertEquals("setDailySchedule should set the dailySchedule to the given DailySchedule object",
                    expected, result);
        } catch (Exception e) {
            fail("DailySchedule constructor threw an exception");
        }
    }

    private String user = "oop";
    private String password = "password";

    /**
     * Creates an instances of the Scheduler class and tests the getFromSQL method
     * which retrieves data from an SQL database
     */
    @Test
    public void testGetFromSQL() {
        // Create empty lists of tasks, treatments, and animals
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Treatment> treatments = new ArrayList<Treatment>();
        ArrayList<Animal> animals = new ArrayList<Animal>();

        // Create a new scheduler with today's date and the empty lists
        Scheduler scheduler = new Scheduler(LocalDate.now(), tasks, treatments, animals);

        // Retrieve data from an SQL database
        scheduler.getFromSQL(this.user, this.password);

        // Ensure that the scheduler's lists are not null
        assertNotNull("Scheduler pulls no animals from the database", scheduler.getAnimals());
        assertNotNull("Scheduler pulls no tasks from the database", scheduler.getTasks());
        assertNotNull("Scheduler pulls no treatments from the database", scheduler.getTreatments());
    }

    /**
     * Creates an instances of the Scheduler class and tests the
     * changeTreatmentStart method
     */
    @Test
    public void testChangeTreatmentStart() {
        // Create an empty list of tasks and animals, and a list of treatments with a
        // single treatment at hour 3
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Treatment> treatments = new ArrayList<Treatment>();
        treatments.add(new Treatment(1, 1, 1, 3));
        ArrayList<Animal> animals = new ArrayList<Animal>();

        // Create a new scheduler with today's date and the treatments and animals lists
        Scheduler scheduler = new Scheduler(LocalDate.now(), tasks, treatments, animals);
        scheduler.getFromSQL(this.user, this.password);

        // Get the treatment and change its start hour to 2
        scheduler.changeTreatmentStart(3, 1, 2);

        // Ensure that the treatment's start hour was changed to 2
        assertEquals("The ChangeTreatmentStart method doesn't successfully change the start time", 2,
                scheduler.getTreatments().get(0).getStartHour());

        // Attempt to change the start hour of a non-existent treatment and ensure that
        // it was not changed
        scheduler.changeTreatmentStart(1, 2, 10);
        assertEquals("The ChangeTreatmentStart method doesn't successfully 'fail' to change the start time", 2,
                scheduler.getTreatments().get(0).getStartHour());
    }
}
