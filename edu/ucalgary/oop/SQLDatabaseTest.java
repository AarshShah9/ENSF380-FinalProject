package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class SQLDatabaseTest {
    /**
     * @version 1.0
     * @author Aarsh @ Nick
     * @date 2023-03-22
     */

    private SQLDatabase db;

    /**
     * Test case for the SQLDatabase constructor with valid input.
     * Creates an instance of the SQLDatabase class with a valid database name and
     * asserts that the connection is not null.
     */
    @Test
    public void testConstructorGoodInput() {
        try {
            this.db = new SQLDatabase("EWR", "oop", "password", new ArrayList<Animal>(), new ArrayList<Task>(),
                    new ArrayList<Treatment>());
            assertNotNull("Expected Database to exist", db.getDBConnect());
        } catch (Exception e) {
            assertEquals("Database does not exist", e.getMessage());
        }

    }

    /**
     * Test case for the SQLDatabase constructor with invalid input.
     * Creates an instance of the SQLDatabase class with an invalid database name
     * and asserts that the connection is null.
     */
    // @Test(expected = SQLException.class)
    // public void testConstructorBadInput() {
    // try {
    // this.db = new SQLDatabase("EWR", "oop", "password", new ArrayList<Animal>(),
    // new ArrayList<Task>(),
    // new ArrayList<Treatment>());
    // assertNull("Database should be null", db.getDBConnect());
    // } catch (IllegalArgumentException e) {

    // } catch (Exception e) {
    // throw new SQLException("Database does not exist");
    // }
    // }

    /**
     * Test case for the getDBConnect() method of the SQLDatabase class.
     * Creates an instance of the SQLDatabase class with a valid database name and
     * asserts that the connection is not null.
     */
    @Test
    public void testGetDBConnect() {
        try {
            this.db = new SQLDatabase("EWR", "oop", "password", new ArrayList<Animal>(), new ArrayList<Task>(),
                    new ArrayList<Treatment>());
            assertNotNull("Expected Database to exist", db.getDBConnect());
        } catch (Exception e) {
            assertEquals("Database does not exist", e.getMessage());
        }

    }

    /**
     * Test case for the getAnimals() method of the SQLDatabase class.
     * Creates an instance of the SQLDatabase class, sets the list of animals, and
     * asserts that the list of animals contains the correct values.
     */
    @Test
    public void testGetAnimals() {
        try {
            this.db = new SQLDatabase("EWR", "oop", "password", new ArrayList<Animal>(), new ArrayList<Task>(),
                    new ArrayList<Treatment>());
            ArrayList<Animal> animals = new ArrayList<Animal>();
            Animal beaver = new Beaver(1, "John");
            Animal coyote = new Coyote(2, "Tim");

            animals.add(beaver);
            animals.add(coyote);
            db.setAnimals(animals);
            assertNotNull(animals);
            assertEquals(2, db.getAnimals().size());
            assertTrue(db.getAnimals().contains(beaver));
            assertTrue(db.getAnimals().contains(coyote));
        } catch (Exception e) {
            assertEquals("Database does not exist", e.getMessage());
        }

    }

    /**
     * Test case for the setAnimals() method of the SQLDatabase class.
     * Creates an instance of the SQLDatabase class, sets the list of animals, and
     * asserts that the list of animals contains the correct values.
     */
    @Test
    public void testSetAnimals() {
        try {
            this.db = new SQLDatabase("EWR", "oop", "password", new ArrayList<Animal>(), new ArrayList<Task>(),
                    new ArrayList<Treatment>());
            ArrayList<Animal> animals = new ArrayList<Animal>();
            Animal beaver = new Beaver(1, "John");
            Animal coyote = new Coyote(2, "Tim");
            animals.add(beaver);
            animals.add(coyote);
            db.setAnimals(animals);

            assertNotNull(animals);
            assertEquals(2, db.getAnimals().size());
            assertTrue(db.getAnimals().contains(beaver));
            assertTrue(db.getAnimals().contains(coyote));
        } catch (Exception e) {
            assertEquals("Database does not exist", e.getMessage());
        }

    }

    /**
     * Test case for the getTasks() method of the SQLDatabase class.
     * Creates an instance of the SQLDatabase class and asserts that the list of
     * tasks is not null and has a size of 0.
     */
    @Test
    public void testGetTasks() {
        try {
            this.db = new SQLDatabase("EWR", "oop", "password", new ArrayList<Animal>(), new ArrayList<Task>(),
                    new ArrayList<Treatment>());
            assertNotNull(db.getTasks());
            assertEquals(0, db.getTasks().size());
        } catch (Exception e) {
            assertEquals("Database does not exist", e.getMessage());
        }

    }

    /**
     * Test case for the setTasks() method of the SQLDatabase class.
     * Creates an instance of the SQLDatabase class, sets the list of tasks, and
     * asserts that the list of tasks contains the correct values.
     */
    @Test
    public void testSetTasks() {
        try {
            this.db = new SQLDatabase("EWR", "oop", "password", new ArrayList<Animal>(), new ArrayList<Task>(),
                    new ArrayList<Treatment>());
        } catch (Exception e) {
            assertEquals("Database does not exist", e.getMessage());
        }
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

    /**
     * Test case for the getTreatments() method of the SQLDatabase class.
     * Creates an instance of the SQLDatabase class and asserts that the list of
     * treatments is not null and has a size of 0.
     */
    @Test
    public void testGetTreatments() {
        //
    }
}
