package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.time.LocalDate;

public class SchedularTest {
    /**
     * @version 1.0
     * @author Sarim
     * @date 2023-03-21
     * @description Tests the Schedular class
     */
    private static final String[] TASK_TYPES = { "Feeding", "Cleaning", "Medical" };

    // Test the Task constructor with valid data
    @Test
    public void testSchedularConstructorValidData() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        Animal testAnimal = new Raccoon(0, "Raccoon", "RACCOON", "NOCTURNAL", true);
        animals.add(testAnimal);
        Task testTask = new Task(1, "Test", 1, 1, TASK_TYPES[0]);
        tasks.add(testTask);
        Schedular temp = new Scheduler(date, animals, tasks);
        assertEquals(date, Schedular.getDate());
        assertEquals(animals, Schedular.getAnimals());
        assertEquals(tasks, Schedular.getTasks());
    }

    // Test the Task constructor with invalid data
    @Test(expected = IllegalArgumentException.class)
    public void testTaskConstructorBadData() {
        Schedular temp = new Scheduler(0, "animals", "tasks");
    }

    // Test the getDate function
    @Test
    public void testGetDate() {
        LocalDate expResult = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        Animal testAnimal = new Raccoon(0, "Raccoon", "RACCOON", "NOCTURNAL", true);
        animals.add(testAnimal);
        Task testTask = new Task(1, "Test", 1, 1, TASK_TYPES[0]);
        tasks.add(testTask);
        Schedular temp = new Scheduler(expResult, animals, tasks);
        LocalDate result = Schedular.getDate();
        System.out.println("getDate");
        assertEquals("getDate() value was incorrect: ", expResult, result);
    }

    // Test the getAnimals function
    @Test
    public void testGetAnimals() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        Animal testAnimal = new Raccoon(0, "Raccoon", "RACCOON", "NOCTURNAL", true);
        animals.add(testAnimal);
        Task testTask = new Task(1, "Test", 1, 1, TASK_TYPES[0]);
        tasks.add(testTask);
        Schedular temp = new Scheduler(date, animals, tasks);
        ArrayList<Animal> result = Schedular.getAnimals();
        System.out.println("getAnimals");
        assertEquals("getAnimals() value was incorrect: ", animals, result);
    }

    // Test the setAnimals function
    @Test
    public void testSetAnimals() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        Animal testAnimal = new Raccoon(0, "Raccoon", "RACCOON", "NOCTURNAL", true);
        animals.add(testAnimal);
        Task testTask = new Task(1, "Test", 1, 1, TASK_TYPES[0]);
        tasks.add(testTask);
        Schedular temp = new Scheduler(date, animals, tasks);
        Animal test = new Raccoon(5, "Remy", "RACCOON", "NOCTURNAL", false);
        animals.set(0, test);
        setAnimals(animals);
        ArrayList<Animal> result = Schedular.getAnimals();
        System.out.println("setAnimals");
        assertEquals("setAnimals() value was incorrect: ", animals, result);
    }

    // Test the getTasks function
    @Test
    public void testGetTasks() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        Animal testAnimal = new Raccoon(0, "Raccoon", "RACCOON", "NOCTURNAL", true);
        animals.add(testAnimal);
        Task testTask = new Task(1, "Test", 1, 1, TASK_TYPES[0]);
        tasks.add(testTask);
        Schedular temp = new Scheduler(date, animals, tasks);
        ArrayList<Task> result = Schedular.getTasks();
        System.out.println("getTasks");
        assertEquals("getTasks() value was incorrect: ", tasks, result);
    }

    // Test the setTasks function
    @Test
    public void testSetTasks() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        Animal testAnimal = new Raccoon(0, "Raccoon", "RACCOON", "NOCTURNAL", true);
        animals.add(testAnimal);
        Task testTask = new Task(1, "Test", 1, 1, TASK_TYPES[0]);
        tasks.add(testTask);
        Schedular temp = new Scheduler(date, animals, tasks);
        Task test = new Task(5, "Test1", false, 2, 5, TASK_TYPES[1]);
        tasks.set(0, test);
        setAnimals(animals);
        ArrayList<Animal> result = Schedular.getAnimals();
        System.out.println("setTasks");
        assertEquals("setTasks() value was incorrect: ", tasks, result);
    }

    // Test the calculateSchedule function, checks if it is properly returned
    @Test
    public void testCalculateSchedule() {
        LocalDate date = LocalDate.now();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        Animal testAnimal = new Raccoon(0, "Raccoon", "RACCOON", "NOCTURNAL", false);
        animals.add(testAnimal);
        Task testTask = new Task(1, "Test", 1, 1, "FEEDING");
        tasks.add(testTask);
        DailySchedule daily = calculateSchedule()
        DailySchedule expected = new DailySchedule(animals, tasks, date)
        assertEquals(expected, daily);
    }
}
