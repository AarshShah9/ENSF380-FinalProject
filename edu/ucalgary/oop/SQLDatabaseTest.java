package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class SQLDatabaseTest {

    private static final String url = "jdbc:mysql://localhost";
    private static final String user = "student";
    private static final String password = "ensf";



    @Test
    public void testConstructorGoodInput() {
        SQLDatabase db = new SQLDatabase("competition");
        assertNotNull("Expected Database to exist", db.getConnection());
    }

    @Test
    public void testConstructorBadInput() {
        SQLDatabase db = new SQLDatabase("badName");
        assertNull("Database should be null", db.getConnection());
    }

    @Test
    public void testGetAnimals() {
        SQLDatabase db = new SQLDatabase("competition");
        ArrayList<String> animals = new ArrayList<String>();
        animals.add("dog");
        animals.add("cat");
        db.setAnimals(animals);
        assertEquals(2, db.getAnimals().size());
        assertTrue(db.getAnimals().contains("dog"));
        assertTrue(db.getAnimals().contains("cat"));
    }

    @Test
    public void testSetAnimals() {}

    @Test
    public void testGetTasks() {
        SQLDatabase database = new SQLDatabase("competition");
        assertNotNull(database.getTasks());
        assertEquals(0, database.getTasks().size());
    }

    @Test
    public void testSetTasks() {
        SQLDatabase db = new SQLDatabase("competition");
        ArrayList<String> tasks = new ArrayList<String>();
        tasks.add("feed the animals");
        tasks.add("clean the cages");
        db.setTasks(tasks);
        assertEquals(2, db.getTasks().size());
        assertTrue(db.getTasks().contains("feed the animals"));
        assertTrue(db.getTasks().contains("clean the cages"));
    }
}
