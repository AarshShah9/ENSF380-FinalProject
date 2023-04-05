package edu.ucalgary.oop;

import java.util.ArrayList;
import java.sql.*;

public class SQLDatabase {
    private ArrayList<Animal> animals;
    private ArrayList<Task> tasks;
    private ArrayList<Treatment> treatments;
    final private Connection DB_CONNECT;

    /**
     * Constructs a new SQLDatabase object with the given database name
     * initializes the animals and tasks ArrayLists
     * Attempts to connect to the specified database using JDBC and the 'student'
     * and 'ensf' for username and password
     * 
     * @param dbName the name of the database to connect to
     * @throws SQLException if there is an error connecting to the
     *                      database
     */
    public SQLDatabase(String dbName, String user, String password, ArrayList<Animal> animaList,
            ArrayList<Task> taskList,
            ArrayList<Treatment> treatmentList) throws SQLException {

        this.animals = animaList;
        this.tasks = taskList;
        this.treatments = treatmentList;

        try {
            this.DB_CONNECT = DriverManager.getConnection(String.format("jdbc:mysql://localhost/%s", dbName), user,
                    password);
            addAnimalsSQL();
            addTasksSQL();
            addTreatmentSQL();
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
            Statement stmt1 = this.DB_CONNECT.createStatement();
            String query1 = "SELECT * FROM ANIMALS";
            ResultSet rs = stmt1.executeQuery(query1);

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
            Statement stmt2 = this.DB_CONNECT.createStatement();
            String query2 = "SELECT * FROM TASKS";
            ResultSet rs = stmt2.executeQuery(query2);

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
            Statement stmt3 = this.DB_CONNECT.createStatement();
            String query3 = "SELECT * FROM TREATMENTS";
            ResultSet rs = stmt3.executeQuery(query3);

            while (rs.next()) {
                Treatment newTreatment = new Treatment(rs.getInt("AnimalID"), rs.getInt("TaskID"),
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
     * @return void
     */
    private void checkOrphans() {
        for (Treatment treatment : this.treatments) {
            if (treatment.getTaskID() == 1) {
                this.animals.get(treatment.getAnimalID() - 1).setOrphaned(true);
            }
        }
    }
}