package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.beans.Transient;
import java.time.LocalDate;

public class SchedulerTest {
    /**
     * @version 1.0
     * @author Sarim
     * @date 2023-03-20
     */

    private static final String[] TASK_TYPES = { "Feeding", "Cleaning", "Medical" };

    // Test the Task constructor with valid data
    @Test
    public void testSchedulerConstructorValidData() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Treatment> treatments = new ArrayList<>();

        Animal testAnimal = new Raccoon(0, "Raccoon");
        animals.add(testAnimal);
        Task testTask = new Task(1, "Test", 20, 5);
        tasks.add(testTask);
        Treatment testTreatment = new Treatment(1, 1, 1);
        treatments.add(testTreatment);

        Scheduler temp = new Scheduler(date, tasks, treatments, animals);
        assertEquals(date, temp.getDate());
        assertEquals(animals, temp.getAnimals());
        assertEquals(tasks, temp.getTasks());
        assertEquals(treatments, temp.getTreatments());
    }

    // Test the Task constructor with invalid data
    @Test(expected = IllegalArgumentException.class)
    public void testTaskConstructorBadData() {
        Scheduler temp = new Scheduler(LocalDate.now());
    }

    // Test the getDate function
    @Test
    public void testGetDate() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Treatment> treatments = new ArrayList<>();

        Animal testAnimal = new Raccoon(0, "Raccoon");
        animals.add(testAnimal);
        Task testTask = new Task(1, "Test", 20, 5);
        tasks.add(testTask);
        Treatment testTreatment = new Treatment(1, 1, 1);
        treatments.add(testTreatment);

        Scheduler temp = new Scheduler(date, tasks, treatments, animals);
        LocalDate result = temp.getDate();
        System.out.println("getDate");
        assertEquals("getDate() value was incorrect: ", date, result);
    }

    // Test the getAnimals function
    @Test
    public void testGetAnimals() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Treatment> treatments = new ArrayList<>();

        Animal testAnimal = new Raccoon(0, "Raccoon");
        animals.add(testAnimal);
        Task testTask = new Task(1, "Test", 20, 5);
        tasks.add(testTask);
        Treatment testTreatment = new Treatment(1, 1, 1);
        treatments.add(testTreatment);

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

        Animal testAnimal = new Raccoon(0, "Raccoon");
        animals.add(testAnimal);
        Task testTask = new Task(1, "Test", 20, 5);
        tasks.add(testTask);
        Treatment testTreatment = new Treatment(1, 1, 1);
        treatments.add(testTreatment);
        
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

        Animal testAnimal = new Raccoon(0, "Raccoon");
        animals.add(testAnimal);
        Task testTask = new Task(1, "Test", 20, 5);
        tasks.add(testTask);
        Treatment testTreatment = new Treatment(1, 1, 1);
        treatments.add(testTreatment);

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

        Animal testAnimal = new Raccoon(0, "Raccoon");
        animals.add(testAnimal);
        Task testTask = new Task(1, "Test", 20, 5);
        tasks.add(testTask);
        Treatment testTreatment = new Treatment(1, 1, 1);
        treatments.add(testTreatment);

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

        Animal testAnimal = new Raccoon(0, "Raccoon");
        animals.add(testAnimal);
        Task testTask = new Task(1, "Test", 20, 5);
        tasks.add(testTask);
        Treatment testTreatment = new Treatment(1, 1, 1);
        treatments.add(testTreatment);

        Scheduler temp = new Scheduler(date, tasks, treatments, animals);
        ArrayList<Task> result = temp.getTreatments();
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

        Animal testAnimal = new Raccoon(0, "Raccoon");
        animals.add(testAnimal);
        Task testTask = new Task(1, "Test", 20, 5);
        tasks.add(testTask);
        Treatment testTreatment = new Treatment(1, 1, 1);
        treatments.add(testTreatment);

        Scheduler temp = new Scheduler(date, tasks, treatments, animals);

        Treatment test = new Treatment(0, 0, 0);
        treatments.set(0, test);
        temp.setTreatments(treatments);
        ArrayList<Treatment> reuslt = temp.getTreatments();

        System.out.println("setTreatments");
        assertEquals("setTreatments() value was incorrect: ", treatments, reuslt);
    }

    // Test the calculateSchedule function, checks if it is properly returned
    @Test
    public void testCalculateSchedule() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Treatment> treatments = new ArrayList<>();

        Animal testAnimal = new Raccoon(0, "Raccoon");
        animals.add(testAnimal);

        Scheduler temp = new Scheduler(date);
        Task testTask = new Task(1, "Test", 1, 1);
        tasks.add(testTask);
        temp.calculateSchedule();
        DailySchedule expected;
        try {
            expected = new DailySchedule(animals, tasks, treatments, date);
            assertEquals(expected, temp);
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }
}
