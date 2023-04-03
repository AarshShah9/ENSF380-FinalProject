package edu.ucalgary.oop;

import javax.sound.midi.Soundbank;
import javax.sound.midi.SoundbankResource;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @name GUI
 * @version 1.0.0
 * @author Nicola Savino
 * @since 2020-11-20
 * 
 * This class is the main GUI class for the EWR schedule manager. It contains the main method and the GUI constructor.
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

    private JMenuBar menuBar;

    JCheckBoxMenuItem volunteerCheck;

    /**
     * @version 1.0.0
     * @author Nicola Savino
     * @since 2020-11-20
     * 
     * This is the main method for the EWR schedule manager. It creates a new GUI object and sets it visible.
     * 
     */
    public GUI() {
        super("EWR schedule manager");
        setupGUI();
        //setSize(WIDTH,HEIGHT);
        setMinimumSize(new Dimension(400, 400));
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        System.out.println(getLayout());
        
    }

    /**
     * @version 1.0.0
     * @author Nicola Savino
     * @since 2020-11-20
     * 
     * This method sets up the GUI by creating the menu bar, the top panel, the buttons panel and the schedule panel.
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
        createButton.setText("Create Schedule");
        createButton.addActionListener(createButtonEvent -> getSchedule());

        //manage schedule
        JButton manageButton = new JButton();
        manageButton.setText("Manage Schedule");
        manageButton.addActionListener(manageButtonEvent -> manageSchedule());

        //print schedule
        JButton printButton = new JButton();
        printButton.setText("Print Schedule");
        printButton.addActionListener(printButtonEvent -> printSchedule());

        JPanel volunteerPanel = new JPanel();
        JLabel volunteerLabel = new JLabel("Volunteer requested:");
        volunteerCheck = new JCheckBoxMenuItem();

        volunteerPanel.add(volunteerLabel);
        volunteerPanel.add(volunteerCheck);


        buttonsPanel.add(createButton);
        buttonsPanel.add(manageButton);
        buttonsPanel.add(printButton);
        buttonsPanel.add(volunteerPanel);


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
     * This method prints the schedule to the console.
     */
    public void getSchedule() {

        JTextArea scheduleText = new JTextArea();

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
        scrollPane.setMaximumSize(new Dimension((int) (schedulePanel.getWidth() * ((double) 0.5)), (int) (schedulePanel.getHeight() * ((double) 0.6))));
        scrollPane.setPreferredSize(new Dimension((int) (WIDTH * ((double) 0.8)), (int) (HEIGHT * ((double) 0.6))));
        

        schedulePanel.add(scrollPane);
        
        // Update the GUI
        this.revalidate();
        this.repaint();
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
        JMenuItem print_scheduleItem = new JMenuItem ("Print Schedule");
        print_scheduleItem.addActionListener(printButtonEvent -> printSchedule());
        fileMenu.add (print_scheduleItem);
        JMenuItem create_scheduleItem = new JMenuItem ("Create Schedule");
        create_scheduleItem.addActionListener(createButtonEvent -> getSchedule());
        fileMenu.add (create_scheduleItem);
        JMenuItem exitItem = new JMenuItem ("Exit");
        exitItem.addActionListener(exitButtonEvent -> this.dispose());
        fileMenu.add (exitItem);


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

        //construct menubar
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
     * This method is called when the print schedule button is pressed. 
     * It checks if the volunteer checkbox is checked, and if it is not, 
     * it will display a popup window asking the user to confirm that a volunteer is present.
     */
    public void printSchedule() {

        if (!volunteerCheck.isSelected()) {

            JLabel volunteerLabel = new JLabel("Please confirm that a volunteer is present");
            volunteerLabel.setHorizontalAlignment(SwingConstants.CENTER);
            JOptionPane.showMessageDialog(this, volunteerLabel, getTitle(), JOptionPane.WARNING_MESSAGE);
        }
    }


    /**
     * @version 1.0.0
     * @author Nicola Savino
     * @since 2020-11-20
     * 
     * This method is called when the manage schedule button is pressed.
     * It will open a new window where the user can manage the schedule.
     */
    public void manageSchedule() {
        // TODO Auto-generated method stub

        JDialog manageFrame = new JDialog(this, "Manage Schedule", true);
        manageFrame.setLayout(new BorderLayout());
        manageFrame.setLocationRelativeTo(this);
        

        JComboBox selectedTime = new JComboBox();
        selectedTime.addItem("8:00 AM");
        selectedTime.addItem("9:00 AM");
        selectedTime.addItem("10:00 AM");
        selectedTime.addItem("11:00 AM");
        selectedTime.addItem("12:00 PM");
        selectedTime.addItem("1:00 PM");
        selectedTime.addItem("2:00 PM");

        JComboBox newTime = new JComboBox();
        newTime.addItem("8:00 AM");
        newTime.addItem("9:00 AM");
        newTime.addItem("10:00 AM");
        newTime.addItem("11:00 AM");
        newTime.addItem("12:00 PM");
        newTime.addItem("1:00 PM");
        newTime.addItem("2:00 PM");

        
        // selectedTime.addItemListener(e -> {
        //     if (e.getStateChange() == ItemEvent.SELECTED) {
        //         System.out.println(selectedTime.getSelectedItem());
        //     }
        // });

        
        // newTime.addItemListener(e -> {
        //     if (e.getStateChange() == ItemEvent.SELECTED) {
        //         System.out.println(newTime.getSelectedItem());
        //     }
        // });

        
        
        

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



        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(confirmButtonEvent -> {
            // TODO Auto-generated method stub
            System.out.println(selectedTime.getSelectedItem());
            System.out.println(newTime.getSelectedItem());
            manageFrame.dispose();
        });
        manageFrame.add(confirmButton, BorderLayout.SOUTH);

        manageFrame.add(managePanel, BorderLayout.CENTER);

        manageFrame.pack();
        

        manageFrame.setVisible(true);

    }


    /**
     * @version 1.0.0
     * @author Nicola Savino
     * @since 2020-11-20
     * 
     * @param args
     * Main method
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    
    }

}

