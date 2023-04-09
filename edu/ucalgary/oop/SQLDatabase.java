package edu.ucalgary.oop;

import java.util.ArrayList;
import java.sql.*;

/**
 * Define a class called "SQLDatabase" that connects to the database and adds
 * the animals, tasks, and treatments to the ArrayLists in the Scedular class
 * 
 * @version 2.0
 * @author Aarsh
 * @date 2023-04-05
 */
public class SQLDatabase {
    private ArrayList<Animal> animals;
    private ArrayList<Task> tasks;
    private ArrayList<Treatment> treatments;
    final private Connection DB_CONNECT;

    /**
     * Constructs a new SQLDatabase object with the given params
     * 
     * @param dbName        the name of the database to connect to
     * @param user          the username to connect to the database
     * @param password      the password to connect to the database
     * @param animaList     the ArrayList of animals
     * @param taskList      the ArrayList of tasks
     * @param treatmentList the ArrayList of treatments
     * 
     * @throws SQLException if there is an error connecting to the
     *                      database
     */
    public SQLDatabase(String dbName, String user, String password, ArrayList<Animal> animaList,
            ArrayList<Task> taskList,
            ArrayList<Treatment> treatmentList) throws SQLException {

        // set the ArrayLists to the given ArrayLists memory locations
        this.animals = animaList;
        this.tasks = taskList;
        this.treatments = treatmentList;

        try {
            // connect to the database
            this.DB_CONNECT = DriverManager.getConnection(String.format("jdbc:mysql://localhost/%s", dbName), user,
                    password);

            // add the animals, tasks, and treatments to the ArrayLists
            addAnimalsSQL();
            addTasksSQL();
            addTreatmentSQL();

            // check if any animals are orphaned
            checkOrphans();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }


    /**
     * addAnimalsSQL adds the animals from the database to the ArrayList of animals
     * in the SQLDatabase object
     * 
     * @throws SQLException
     */
    private void addAnimalsSQL() throws SQLException {

        try {
            // Create a statement object
            Statement stmt1 = this.DB_CONNECT.createStatement();
            // select all the animals from the database ANIMALS table
            String query1 = "SELECT * FROM ANIMALS";
            // execute the query and store the result in a ResultSet object
            ResultSet rs = stmt1.executeQuery(query1);

            // loop through the ResultSet object and add the animals to the ArrayList
            // depennding on the animal type
            while (rs.next()) {
                String name = rs.getString("AnimalSpecies");
                try {
                    switch (name) {
                        case "coyote":
                            animals.add(new Coyote(rs.getInt("AnimalID"), rs.getString("AnimalNickname")));
                            break;
                        case "fox":
                            animals.add(new Fox(rs.getInt("AnimalID"), rs.getString("AnimalNickname")));
                            break;
                        case "porcupine":
                            animals.add(new Porcupine(rs.getInt("AnimalID"), rs.getString("AnimalNickname")));
                            break;
                        case "beaver":
                            animals.add(new Beaver(rs.getInt("AnimalID"), rs.getString("AnimalNickname")));
                            break;
                        default:
                            System.out.println("Unknown animal: " + name);
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException("Issue parsing animal info from SQL Database" + e.getMessage());
                }

            }
        } catch (Exception e) {
            throw new SQLException("Invalid Animal Input");

        }
    }

    /**
     * addTasksSQL adds the tasks from the database to the ArrayList of tasks in
     * the SQLDatabase object
     * 
     * @throws SQLException
     */
    private void addTasksSQL() throws SQLException {
        try {
            // Create a statement object
            Statement stmt2 = this.DB_CONNECT.createStatement();
            // select all the tasks from the database TASKS table
            String query2 = "SELECT * FROM TASKS";
            // execute the query and store the result in a ResultSet object
            ResultSet rs = stmt2.executeQuery(query2);

            // loop through the ResultSet object and add the tasks to the ArrayList
            while (rs.next()) {
                Task newTask = new Task(rs.getInt("TaskID"), rs.getString("Description"),
                        rs.getInt("Duration"), rs.getInt("MaxWindow"));
                tasks.add(newTask);
            }
        } catch (Exception e) {
            throw new SQLException("Issue parsing task info from SQL Database" + e.getMessage());
        }

    }

    /**
     * addTreatmentSQL adds the treatments from the database to the ArrayList of
     * treatments in the SQLDatabase object
     * 
     * @throws SQLException
     */
    private void addTreatmentSQL() throws SQLException {
        try {
            // Create a statement object
            Statement stmt3 = this.DB_CONNECT.createStatement();
            // select all the treatments from the database TREATMENTS table
            String query3 = "SELECT * FROM TREATMENTS";
            // execute the query and store the result in a ResultSet object
            ResultSet rs = stmt3.executeQuery(query3);

            // loop through the ResultSet object and add the treatments to the ArrayList
            while (rs.next()) {
                Treatment newTreatment = new Treatment(rs.getInt("TreatmentID"), rs.getInt("AnimalID"),
                        rs.getInt("TaskID"),
                        rs.getInt("StartHour"));
                treatments.add(newTreatment);
            }
        } catch (IllegalArgumentException e) {
            throw new SQLException("Invalid Start Hour Treatment Input from the SQL Database" + e.getMessage());
        } catch (Exception e) {
            throw new SQLException("Issue parsing treatment info from SQL Database" + e.getMessage());
        }
    }

    /**
     * updates the database with the new start hour of the treatment
     * 
     * @param animalID     the animal ID of the treatment
     * @param taskID       the task ID of the treatment
     * @param newStartHour the new start hour of the treatment
     * 
     * @throws SQLException if there is an error updating the database
     */
    public void updateDatabase(int treatmentID, int newStartHour) throws SQLException {
        try {
            // Create a statement object
            Statement stmt = this.DB_CONNECT.createStatement();
            // update the treatment in the database
            String query = String.format("UPDATE TREATMENTS SET StartHour = %d WHERE TreatmentID = %d",
                    newStartHour, treatmentID);
            // execute the query
            stmt.executeUpdate(query);
        } catch (Exception e) {
            throw new SQLException("Issue updating treatment info in SQL Database" + e.getMessage());
        }
    }

    /**
     * Returns the list of treatments in the SQLDatabase object
     * 
     * @return Returns the list of treatments in the SQLDatabase object.
     */
    public ArrayList<Treatment> getTreatments() {
        return this.treatments;
    }

    /**
     * Sets the list of treatments in teh SQLDatabase object to the argument passed
     * through
     * 
     * @param newTreatments the new ArrayList of treatments to set in the Database
     */
    public void setTreatments(ArrayList<Treatment> newTreatments) {
        this.treatments = newTreatments;
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
    public Connection getDBConnect() {
        return this.DB_CONNECT;
    }

    /**
     * Determines if any of the animals are orphans
     * 
     */
    private void checkOrphans() {
        // loops through and checks if any of the animals are orphans
        for (Treatment treatment : this.treatments) {
            if (treatment.getTaskID() == 1) {
                this.animals.get(treatment.getAnimalID() - 1).setOrphaned(true);
            }
        }
    }
}