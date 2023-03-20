package edu.ucalgary.oop;

import java.util.ArrayList;
import java.sql.*;

public class SQLDatabase {
    private ArrayList<String> animals;
    private ArrayList<String> tasks;

    private Connection dbConnect;

    private String username;
    private String password;
    private String DBURL;


    /**
     * Constructs a new SQLDatabase object with the given database name
     * initializes the animals and tasks ArrayLists
     * Attempts to connect to the specified database using JDBC and the 'studnet' and 'ensf' for username and password
     * @param dbName the name of the database to connect to
     * @throws IllegalArgumentException
     * @throws SQLException if there is an error connecting to the database
     */
    public SQLDatabase(String dbName) throws IllegalArgumentException {
        this.animals = new ArrayList<String>();
        this.tasks = new ArrayList<String>();

        try {
            this.dbConnect = DriverManager.getConnection(String.format("jdbc:mysql://localhost/%s", dbName), "student", "ensf");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Invalid Database Input");
        }
    }

    /**
     * updates the database with new information read in from a text file
     * @param fileName
     */
    public void updateDatabase(String fileName) {
        // TODO implement method
    }

    /**
     * Returns the list of animals in the SQLDatabase object
     * @return Returns the list of animals in the SQLDatabase object.
     */
    public ArrayList<String> getAnimals() {
        return animals;
    }

    /**
     * Sets the list of animals in teh SQLDatabase object to the argument passed through
     * @param newAnimals the new ArrayList of animals to set in the Database
     */
    public void setAnimals(ArrayList<String> newAnimals) {
        this.animals = newAnimals;
    }

    /**
     * Returns the list of tasks in the SQLDatabase object
     * @return Returns the list of tasks in the SQLDatabase object.
     */
    public ArrayList<String> getTasks() {
        return tasks;
    }

    /**
     * Sets the list of tasks in teh SQLDatabase object to the argument passed through
     * @param newTasks the new ArrayList of tasks to set in the Database
     */
    public void setTasks(ArrayList<String> newTasks) {
        this.tasks = newTasks;
    }

    /**
     * returns the variable used to store the connection information of the SQL Database
     * @return the connection object for the current database connection
     */
    public Connection getConnection() {
        return this.dbConnect;
    }
}