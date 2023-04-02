package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class SQLDatabaseTest {
    /**
     * @version 1.0
     * @author Aarsh @ Nick
     * @date 2023-03-22
     */

    /**
     * Test case for the SQLDatabase constructor with valid input.
     * Creates an instance of the SQLDatabase class with a valid database name and
     * asserts that the connection is not null.
     */
    @Test
    public void testConstructorGoodInput() {
        SQLDatabase db = new SQLDatabase("ewr", new ArrayList<Animal>(), new ArrayList<Task>(), new ArrayList<Treatment>());
        assertNotNull("Expected Database to exist", db.getConnection());
    }

    /**
     * Test case for the SQLDatabase constructor with invalid input.
     * Creates an instance of the SQLDatabase class with an invalid database name
     * and asserts that the connection is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorBadInput() {
        SQLDatabase db = new SQLDatabase("3badName", new ArrayList<Animal>(), new ArrayList<Task>(), new ArrayList<Treatment>());
        assertNull("Database should be null", db.getConnection());

    }

    /**
     * Test case for the getConnection() method of the SQLDatabase class.
     * Creates an instance of the SQLDatabase class with a valid database name and
     * asserts that the connection is not null.
     */
    @Test
    public void testGetConnection() {
        SQLDatabase db = new SQLDatabase("ewr", new ArrayList<Animal>(), new ArrayList<Task>(), new ArrayList<Treatment>());
        assertNotNull("Expected Database to exist", db.getConnection());
    }

    /**
     * Test case for the getAnimals() method of the SQLDatabase class.
     * Creates an instance of the SQLDatabase class, sets the list of animals, and
     * asserts that the list of animals contains the correct values.
     */
    @Test
    public void testGetAnimals() {
        SQLDatabase db = new SQLDatabase("ewr", new ArrayList<Animal>(), new ArrayList<Task>(), new ArrayList<Treatment>());
        ArrayList<Animal> animals = new ArrayList<Animal>();
        Animal beaver = new Beaver(1, "John", AnimalType.COYOTE.toString());
        Animal coyote = new Coyote(2, "Tim", AnimalType.COYOTE.toString());

        animals.add(beaver);
        animals.add(coyote);
        db.setAnimals(animals);
        assertNotNull(animals);
        assertEquals(2, db.getAnimals().size());
        assertTrue(db.getAnimals().contains(beaver));
        assertTrue(db.getAnimals().contains(coyote));
    }

    /**
     * Test case for the setAnimals() method of the SQLDatabase class.
     * Creates an instance of the SQLDatabase class, sets the list of animals, and
     * asserts that the list of animals contains the correct values.
     */
    @Test
    public void testSetAnimals() {
        SQLDatabase db = new SQLDatabase("ewr", new ArrayList<Animal>(), new ArrayList<Task>(), new ArrayList<Treatment>());
        ArrayList<Animal> animals = new ArrayList<Animal>();
        Animal beaver = new Beaver(1, "John", AnimalType.COYOTE.toString());
        Animal coyote = new Coyote(2, "Tim", AnimalType.COYOTE.toString());
        animals.add(beaver);
        animals.add(coyote);
        db.setAnimals(animals);

        assertNotNull(animals);
        assertEquals(2, db.getAnimals().size());
        assertTrue(db.getAnimals().contains(beaver));
        assertTrue(db.getAnimals().contains(coyote));
    }

    /**
     * Test case for the getTasks() method of the SQLDatabase class.
     * Creates an instance of the SQLDatabase class and asserts that the list of
     * tasks is not null and has a size of 0.
     */
    @Test
    public void testGetTasks() {
        SQLDatabase db = new SQLDatabase("ewr", new ArrayList<Animal>(), new ArrayList<Task>(), new ArrayList<Treatment>());
        assertNotNull(db.getTasks());
        assertEquals(0, db.getTasks().size());
    }

    /**
     * Test case for the setTasks() method of the SQLDatabase class.
     * Creates an instance of the SQLDatabase class, sets the list of tasks, and
     * asserts that the list of tasks contains the correct values.
     */
    @Test
    public void testSetTasks() {
        SQLDatabase db = new SQLDatabase("ewr", new ArrayList<Animal>(), new ArrayList<Task>(), new ArrayList<Treatment>());
        ArrayList<Task> tasks = new ArrayList<Task>();
        Task task1 = new Task(1, "Feed Beaver", 30, 45);
        Task task2 = new Task(2, "Treat Coyote", 60, 30);
        tasks.add(task1);
        tasks.add(task2);
        db.setTasks(tasks);
        assertNotNull(tasks);
        assertEquals(2, db.getTasks().size());
        assertTrue(db.getTasks().contains(task1));
        assertTrue(db.getTasks().contains(task2));
    }
}
