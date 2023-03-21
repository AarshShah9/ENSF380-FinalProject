package edu.ucalgary.oop;

import java.util.ArrayList;
import java.sql.*;

public class SQLDatabase {
    private ArrayList<Animal> animals;
    private ArrayList<Task> tasks;

    final private Connection DB_CONNECT;

    /**
     * Constructs a new SQLDatabase object with the given database name
     * initializes the animals and tasks ArrayLists
     * Attempts to connect to the specified database using JDBC and the 'student' and 'ensf' for username and password
     * @param dbName the name of the database to connect to
     * @throws IllegalArgumentException
     * @throws SQLException if there is an error connecting to the database
     */
    public SQLDatabase(String dbName) throws IllegalArgumentException {
        this.animals = new ArrayList<Animal>();
        this.tasks = new ArrayList<Task>();

        try {
            this.DB_CONNECT = DriverManager.getConnection(String.format("jdbc:mysql://localhost/%s", dbName), "student", "ensf");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Invalid Database Input");
        }
    }

    /**
     * Returns the list of animals in the SQLDatabase object
     * @return Returns the list of animals in the SQLDatabase object.
     */
    public ArrayList<Animal> getAnimals() {
        return this.animals;
    }

    /**
     * Sets the list of animals in teh SQLDatabase object to the argument passed through
     * @param newAnimals the new ArrayList of animals to set in the Database
     */
    public void setAnimals(ArrayList<Animal> newAnimals) {
        this.animals = newAnimals;
    }

    /**
     * Returns the list of tasks in the SQLDatabase object
     * @return Returns the list of tasks in the SQLDatabase object.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Sets the list of tasks in teh SQLDatabase object to the argument passed through
     * @param newTasks the new ArrayList of tasks to set in the Database
     */
    public void setTasks(ArrayList<Task> newTasks) {
        this.tasks = newTasks;
    }

    /**
     * returns the variable used to store the connection information of the SQL Database
     * @return the connection object for the current database connection
     */
    public Connection getConnection() {
        return this.DB_CONNECT;
    }
}