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
     * Attempts to connect to the specified database using JDBC and the 'student'
     * and 'ensf' for username and password
     * 
     * @param dbName the name of the database to connect to
     * @throws IllegalArgumentException
     * @throws SQLException             if there is an error connecting to the
     *                                  database
     */
    public SQLDatabase(String dbName) throws IllegalArgumentException {
        this.animals = new ArrayList<Animal>();
        this.tasks = new ArrayList<Task>();

        try {
            this.DB_CONNECT = DriverManager.getConnection(String.format("jdbc:mysql://localhost/%s", dbName), "student",
                    "ensf");

            // Extract this to getAnimalsSQL() method
            // ________________________________________________________
            Statement stmt1 = this.DB_CONNECT.createStatement();
            String query1 = "SELECT * FROM ANIMALS";
            ResultSet rs1 = stmt1.executeQuery(query1);

            while (rs1.next()) {
                String name = rs1.getString("AnimalSpecies");
                try {
                    // or do something like this
                    // switch (name) {
                    // case "coyote":
                    // animals.add(new Coyote());
                    // break;
                    // case "fox":
                    // animals.add(new Fox());
                    // break;
                    // case "porcupine":
                    // animals.add(new Porcupine());
                    // break;
                    // default:
                    // System.out.println("Unknown animal: " + name);
                    // }
                    Class<?> cls = Class.forName(name.substring(0, 1).toUpperCase() + name.substring(1));
                    Animal animal = (Animal) cls.newInstance();
                    animals.add(animal);
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    System.out.println("Unknown animal: " + name);
                }
                animals.add(null); // need to add new animal objects to the arraylist depending on the type of
                                   // animal
            }
            // Extract this to getTasksSQL() method
            // ________________________________________________________
            Statement stmt2 = this.DB_CONNECT.createStatement();
            String query2 = "SELECT * FROM TASKS";
            ResultSet rs2 = stmt2.executeQuery(query2);

            while (rs2.next()) {
                Task newTask = new Task(rs2.getInt("TaskID"), rs2.getString("Description"),
                        rs2.getInt("Duration"), rs2.getInt("MaxWindow"), "Non-Medical");
                tasks.add(newTask);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Invalid Database Input");
        }
    }

    /**
     * Returns the list of animals in the SQLDatabase object
     * 
     * @return Returns the list of animals in the SQLDatabase object.
     */
    public ArrayList<Animal> getAnimals() {
        return this.animals;
    }

    /**
     * Sets the list of animals in teh SQLDatabase object to the argument passed
     * through
     * 
     * @param newAnimals the new ArrayList of animals to set in the Database
     */
    public void setAnimals(ArrayList<Animal> newAnimals) {
        this.animals = newAnimals;
    }

    /**
     * Returns the list of tasks in the SQLDatabase object
     * 
     * @return Returns the list of tasks in the SQLDatabase object.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Sets the list of tasks in teh SQLDatabase object to the argument passed
     * through
     * 
     * @param newTasks the new ArrayList of tasks to set in the Database
     */
    public void setTasks(ArrayList<Task> newTasks) {
        this.tasks = newTasks;
    }

    /**
     * returns the variable used to store the connection information of the SQL
     * Database
     * 
     * @return the connection object for the current database connection
     */
    public Connection getConnection() {
        return this.DB_CONNECT;
    }
}