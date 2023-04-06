package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.time.LocalDate;

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

        System.out.println("setAnimals");
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
        System.out.println("getTasks");
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

        System.out.println("setTasks");
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
        System.out.println("getTreatments");
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

        System.out.println("setTreatments");
        assertEquals("setTreatments() value was incorrect: ", treatments, result);
    }

    // Test the calculateSchedule function, checks if it is properly returned
    // @Test
    // public void testCalculateSchedule() {
    // LocalDate date = LocalDate.now();
    // ArrayList<Animal> animals = new ArrayList<>();
    // ArrayList<Task> tasks = new ArrayList<>();
    // ArrayList<Treatment> treatments = new ArrayList<>();

    // Animal testAnimal = new Raccoon(0, "Raccoon");
    // animals.add(testAnimal);

    // Scheduler temp = new Scheduler(date, tasks, treatments, animals);
    // Task testTask = new Task(1, "Test", 1, 1);
    // tasks.add(testTask);
    // temp.calculateSchedule();
    // DailySchedule expected;
    // try {
    // expected = new DailySchedule(animals, tasks, treatments, date);
    // assertEquals(expected, temp);
    // } catch (Exception e) {
    // System.out.println("Error: " + e.getLocalizedMessage());
    // }
    // }

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
        scheduler.getFromSQL("oop", "password");

        // Ensure that the scheduler's lists are not null
        assertNotNull(scheduler.getAnimals());
        assertNotNull(scheduler.getTasks());
        assertNotNull(scheduler.getTreatments());
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

        // Get the treatment and change its start hour to 2
        scheduler.changeTreatmentStart(3, 1, 2);

        // Ensure that the treatment's start hour was changed to 2
        assertEquals(2, scheduler.getTreatments().get(0).getStartHour());

        // Attempt to change the start hour of a non-existent treatment and ensure that
        // it was not changed
        scheduler.changeTreatmentStart(1, 2, 10);
        assertEquals(2, scheduler.getTreatments().get(0).getStartHour());
    }
}
