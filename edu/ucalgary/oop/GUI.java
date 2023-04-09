package edu.ucalgary.oop;

import javax.swing.*;


import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * @name GUI
 * @version 1.0.0
 * @author Nicola Savino
 * @since 2020-11-20
 * 
 *        This class is the main GUI class for the EWR schedule manager. It
 *        contains the main method and the GUI constructor.
 * 
 * @extends JFrame
 * 
 */
public class GUI extends JFrame {

    // Constants
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;

    // Variables
    private JLabel topHeader;

    private JPanel topPanel;
    private JPanel schedulePanel;
    private JPanel buttonsPanel;

    private boolean[] neededVolunteers = new boolean[24];
    private boolean saveFlag = false;


    private JMenuBar menuBar;

    private Scheduler scheduler;

    private String user;
    private String password;

    JTextArea scheduleText = new JTextArea();

    /**
     * @version 1.0.0
     * @author Nicola Savino
     * @since 2020-11-20
     * 
     *        This is the main method for the EWR schedule manager. It creates a new
     *        GUI object and sets it visible.
     * 
     */
    public GUI() {
        super("EWR schedule manager");
        login();
        setupGUI();
        //setSize(WIDTH,HEIGHT);
        setMinimumSize(new Dimension(400, 400));
        pack();
        scheduleText.setEditable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

    }

    public void login() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setResizable(false);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);

        JPanel loginPanel = new JPanel();

        loginPanel.setBorder(BorderFactory.createTitledBorder("Login"));

        
        
        JPanel usernamePanel = new JPanel();
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField("oop");
        usernameField.setPreferredSize(new Dimension(100, 20));
        
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);


        JPanel passwordPanel = new JPanel();
        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordField = new JPasswordField("password");
        passwordField.setPreferredSize(new Dimension(100, 20));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);



        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            this.user = new String(usernameField.getText());
            this.password = new String(passwordField.getText());
            if (authenticate(this.user, this.password)) {
                loginFrame.dispose();

                this.setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid username or password");
            }
        });

        

        loginPanel.add(usernamePanel, BorderLayout.NORTH);
        loginPanel.add(passwordPanel, BorderLayout.SOUTH);
        
        loginFrame.add(loginButton, BorderLayout.SOUTH);
        loginFrame.add(loginPanel, BorderLayout.CENTER);

        loginFrame.pack();
    }

        private boolean authenticate(String username, String password) {
            try {

                this.scheduler = new Scheduler(LocalDate.now(), new ArrayList<Task>(), new ArrayList<Treatment>(), new ArrayList<Animal>());
                
                scheduler.getFromSQL(username, password);
                
            } catch (IllegalArgumentException e) {
                
                return false;
            }
            
            return true;
        }
    /**
     * @version 1.0.0
     * @author Nicola Savino
     * @since 2020-11-20
     * 
     *        This method sets up the GUI by creating the menu bar, the top panel,
     *        the buttons panel and the schedule panel.
     */
    public void setupGUI() {

        buildMenuBar();

        topHeader = new JLabel("EWR schedule manager");

        topHeader.setHorizontalAlignment(SwingConstants.CENTER);
        topHeader.setVerticalAlignment(SwingConstants.CENTER);

        topPanel = new JPanel();
        menuBar.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, Color.BLACK));
        topPanel.setPreferredSize(new Dimension(WIDTH, 100));
        topPanel.setLayout(new BorderLayout());
        topPanel.add(menuBar, BorderLayout.NORTH);
        topPanel.add(topHeader, BorderLayout.CENTER);


        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4, 1));
        buttonsPanel.setBorder(BorderFactory.createTitledBorder("Options"));
        buttonsPanel.setPreferredSize(new Dimension((int) (WIDTH * ((double) 0.2)), (int) (HEIGHT * ((double) 0.8))));


        //options buttons
        //create schedule
        JButton createButton = new JButton();
        createButton.setText("New Schedule");
        createButton.addActionListener(createButtonEvent -> getSchedule());

        //manage schedule
        JButton manageButton = new JButton();
        manageButton.setText("Change Schedule");
        manageButton.addActionListener(manageButtonEvent -> manageSchedule());

        //print schedule
        JButton printButton = new JButton();
        printButton.setText("Save Schedule");
        printButton.addActionListener(printButtonEvent -> saveSchedule());


        buttonsPanel.add(createButton);
        buttonsPanel.add(manageButton);
        buttonsPanel.add(printButton);
        

        schedulePanel = new JPanel();

        schedulePanel.setLayout(new BorderLayout());
        schedulePanel.setBorder(BorderFactory.createTitledBorder("Schedule"));
        schedulePanel.setPreferredSize(new Dimension((int) (WIDTH * ((double) 0.8)), (int) (HEIGHT * ((double) 0.8))));

        this.add(topPanel, BorderLayout.NORTH);
        this.add(schedulePanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.EAST);


        this.setBackground(Color.WHITE);

    }


    /**
     * @version 1.0.0
     * @author Nicola Savino
     * @since 2020-11-20
     * 

     * This method builds the GUI menu bar
     * 
     * 
     */
    public void buildMenuBar() {
        //construct menu bar items

        //construct file menu
        JMenu fileMenu = new JMenu ("File");
        JMenuItem print_scheduleItem = new JMenuItem ("Save Schedule");
        print_scheduleItem.addActionListener(printButtonEvent -> saveSchedule());
        fileMenu.add(print_scheduleItem);
        JMenuItem create_scheduleItem = new JMenuItem("New Schedule");
        create_scheduleItem.addActionListener(createButtonEvent -> getSchedule());
        fileMenu.add(create_scheduleItem);
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(exitButtonEvent -> this.dispose());
        fileMenu.add(exitItem);


        //Construct help menu
        JMenu helpMenu = new JMenu ("Help");
        JMenuItem contentsItem = new JMenuItem ("Contents");
        helpMenu.add (contentsItem);
        
        //about menu
        JMenuItem aboutItem = new JMenuItem ("About");
        aboutItem.addActionListener(aboutItemEvent -> {
            JPanel aboutPanel = new JPanel();
            aboutPanel.setLayout(new GridLayout(3, 1, 0, 10));
            JLabel aboutTitle = new JLabel("EWR Schedule Manager v1.0.0");
            JLabel aboutAuthors = new JLabel("Created by William Fraser, Nicola Savino, Aarsh Shah, Sarim Sheik");
            JLabel aboutDescription = new JLabel("This program is used to create and manage the schedule for the EWR volunteer program.");
            aboutTitle.setHorizontalAlignment(SwingConstants.CENTER);
            aboutAuthors.setHorizontalAlignment(SwingConstants.CENTER);
            aboutDescription.setHorizontalAlignment(SwingConstants.CENTER);
            aboutPanel.add(aboutTitle);
            aboutPanel.add(aboutAuthors);
            aboutPanel.add(aboutDescription);
            JOptionPane.showMessageDialog(this, aboutPanel, getTitle() + " - About", JOptionPane.INFORMATION_MESSAGE);
        });
        helpMenu.add (aboutItem);

        // construct menubar
        menuBar = new JMenuBar();

        menuBar.setPreferredSize(new Dimension(WIDTH, 40));
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        
    }

        /**
     * @version 1.0.0
     * @author Nicola Savino
     * @since 2020-11-20
     * 
     *        This method prints the schedule to the console.
     */
    public void getSchedule() {

        if (this.saveFlag == false) {
            scheduler = new Scheduler(LocalDate.now(), new ArrayList<Task>(), new ArrayList<Treatment>(), new ArrayList<Animal>());
            scheduler.getFromSQL(this.user, this.password);
        }
        //Create the schedule
        String status = scheduler.calculateSchedule();
        neededVolunteers = scheduler.getVolunteersNeeded();
        if (status.equals("Success")) {
            JOptionPane.showMessageDialog(this, "Schedule Generated Successfully", status, MessageType.INFO.ordinal());
        }
        else {
            JOptionPane.showMessageDialog(this, "Error calculating schedule: " + status, status, MessageType.ERROR.ordinal());
        }

        if (scheduleText.getText() != "") {
            scheduleText.selectAll();
            scheduleText.replaceSelection("");
        }

        try {
            File file = new File("schedule.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = reader.readLine();
            while (line != null) {
                scheduleText.append(line + "\n");
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(scheduleText);
        
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setMaximumSize(new Dimension((int) (schedulePanel.getWidth() * ((double) 0.5)),
                (int) (schedulePanel.getHeight() * ((double) 0.6))));
        scrollPane.setPreferredSize(new Dimension((int) (WIDTH * ((double) 0.8)), (int) (HEIGHT * ((double) 0.6))));

        if (schedulePanel.getComponentCount() > 0) {
            schedulePanel.remove(0);
        }
        
        schedulePanel.add(scrollPane);
        

        // Update the GUI
        this.revalidate();
        this.repaint();
        this.saveFlag = false;
    }


    /**
     * @version 1.0.0
     * @author Nicola Savino
     * @since 2020-11-20
     * 
     *        This method is called when the print schedule button is pressed.
     *        It checks if the volunteer checkbox is checked, and if it is not,
     *        it will display a popup window asking the user to confirm that a
     *        volunteer is present.
     */


    /**
     * @version 1.0.0
     * @author Nicola Savino
     * @since 2020-11-20
     * 
     * This method is called when the manage schedule button is pressed.
     * It will open a new window where the user can manage the schedule.
     */
    public void manageSchedule() {
        if (scheduleText.isShowing() == false) {
            JOptionPane.showMessageDialog(this, "There is no schedule to manage. Please generate a schedule first", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog manageFrame = new JDialog(this, "Manage Schedule", true);
        manageFrame.setLayout(new BorderLayout());
        manageFrame.setLocationRelativeTo(this);
        

        // This code finds the treatment start time for each animal and task at a specific time
        // The user will be able to change the treatment start time for each animal and task at a specific time
        // The time will be in order

    JComboBox<String> selectedTime = new JComboBox<String>();
        
     
       
        ArrayList<Treatment> treatments = scheduler.getTreatments();
        ArrayList<Animal> animals = scheduler.getAnimals();
        ArrayList<Task> tasks = scheduler.getTasks();

        //Builds the list of times to select from
        //  - Each ComboBox Item is a string of the format "HH:MM - Treatment Name - Animal Name"
        //  - Each ComboBox Item is also stored in the arraylist "times"
        //  - The user will be able to change the treatment start time for each animal and task at a specific time
        //  - time will be in order

        ArrayList<String> currentTimes = new ArrayList<>();

        // For each treatment, get the animal name and task name
        for (Treatment t : treatments) {
            int animalID = t.getAnimalID();
            int taskID = t.getTaskID();
            int treatmentID = t.getTreatmentID();
            LocalTime startHour = LocalTime.of(t.getStartHour(), 0);

            String animalName = "";
            String taskName = "";

            for (Animal a : animals) {
                if (a.getAnimalID() == animalID) {
                    animalName = a.getAnimalName();
                }
            }

            for (Task task : tasks) {
                if (task.getTaskID() == taskID) {
                    taskName = task.getDescription();
                }
            }

            // Add the formatted string to the list of times
            selectedTime.addItem(startHour.toString() + " - " + taskName + " - " + animalName + " - " + treatmentID);
            

        }

        //creates an arraylist of LocalTimes to store each hour in the day
        JComboBox<LocalTime> newTime = new JComboBox<LocalTime>();
        ArrayList<LocalTime> hours = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            hours.add(LocalTime.of(i, 0));
            newTime.addItem(hours.get(i));
        }

        JPanel managePanel = new JPanel();
       
        JPanel currentTimePanel = new JPanel();

        JLabel currentTimeLabel = new JLabel("Current Time: ");

        currentTimePanel.add(currentTimeLabel);
        currentTimePanel.add(selectedTime);

        JPanel newTimePanel = new JPanel();

        JLabel newTimeLabel = new JLabel("New Time: ");

        newTimePanel.add(newTimeLabel);
        newTimePanel.add(newTime);

        managePanel.add(currentTimePanel);
        managePanel.add(newTimePanel);

        JPanel buttonPanel = new JPanel();



        JButton changeTimeButton = new JButton("Change Time");
        changeTimeButton.addActionListener(confirmButtonEvent -> {
            
            // TODO Auto-generated method stub
            // get the treatment
            Object treatment = selectedTime.getSelectedItem();
            // parse the start time
            Object startTime = Integer.parseInt(treatment.toString().substring(0, 2));

            // parse the treatment name
            String treatmentName = treatment.toString().substring(8);

            treatmentName = treatmentName.substring(0, treatmentName.indexOf("-") - 1);

            
            // parse the treatment ID
            String treatmentID = treatment.toString().substring(treatment.toString().lastIndexOf("-") + 1).strip();

            
            int treatmentIDInt = Integer.parseInt(treatmentID);

            // get new time
            Object newTimeComboObject = newTime.getSelectedItem();
            Object newTimeObject = Integer.parseInt(newTimeComboObject.toString().substring(0, 2));
            

            // update the schedule
            scheduler.changeTreatmentStart(Integer.parseInt(startTime.toString()), treatmentIDInt, Integer.parseInt(newTimeObject.toString()));
            
            // close the window

            selectedTime.removeItem(treatment);
            if (selectedTime.getItemCount() == 0) {
                buttonPanel.remove(changeTimeButton);
                manageFrame.pack();
            }
            
        });

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(confirmButtonEvent -> {
            manageFrame.dispose();
        });

        buttonPanel.add(changeTimeButton);
        buttonPanel.add(confirmButton);
        manageFrame.add(buttonPanel, BorderLayout.SOUTH);

        manageFrame.add(managePanel, BorderLayout.CENTER);

        manageFrame.pack();

        manageFrame.setVisible(true);
    }

    public void saveSchedule() {
        if (scheduleText.isShowing() == false) {
            JOptionPane.showMessageDialog(this, "There is no schedule to save. Please generate a schedule first", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean volunteersNeeded = false;
        System.out.println(neededVolunteers.length);
        for (int i = 0; i < neededVolunteers.length; i++) {
            if (neededVolunteers[i] == true) {
                volunteersNeeded = true;
                break;
            }
        }
        if (volunteersNeeded == false) {
            this.saveFlag = true;
            getSchedule();
            return;
        }

        JDialog confirmVolunteers = new JDialog(this, this.getTitle() + " - Confirm Volunteers", true);
        
        JLabel confirmLabel = new JLabel("Please confirm that the following volunteers are present:");
        confirmVolunteers.add(confirmLabel, BorderLayout.NORTH);

        ArrayList<JCheckBox> volunteerChecks = new ArrayList<JCheckBox>(); 
        for (int i = 0; i < neededVolunteers.length; i++) {
            if (neededVolunteers[i] == true) {
                JPanel instancePanel = new JPanel();
                JLabel volunteerLabel = new JLabel(LocalTime.of(i, 0).toString());
                JCheckBox volunteerCheck = new JCheckBox();
                volunteerChecks.add(volunteerCheck);
                instancePanel.add(volunteerLabel);
                instancePanel.add(volunteerCheck);
                confirmVolunteers.add(instancePanel);
                volunteerLabel.setHorizontalAlignment(SwingConstants.CENTER);
            }
        }

        JButton confirmButton = new JButton("Confirm");

        
        confirmButton.addActionListener(e -> {
            for (int i = 0; i < volunteerChecks.size(); i++) {
                if (volunteerChecks.get(i).isSelected() == false) {
                    JOptionPane.showMessageDialog(this, "Please confirm that all volunteers are present.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            this.saveFlag = true;
            getSchedule();
            confirmVolunteers.dispose();
        });

        confirmVolunteers.add(confirmButton, BorderLayout.SOUTH);
        confirmVolunteers.pack();
        confirmVolunteers.setVisible(true);

    }


    /**
     * @version 1.0.0
     * @author Nicola Savino
     * @since 2020-11-20
     * 
     * @param args
     *             Main method
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            new GUI();
        });

    }

}
