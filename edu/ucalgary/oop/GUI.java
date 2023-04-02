package edu.ucalgary.oop;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
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
        setMinimumSize(new Dimension(400, 300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
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
        menuBar.setBorder(null);
        topPanel.setPreferredSize(new Dimension(WIDTH, 100));
        topPanel.setLayout(new BorderLayout());
        topPanel.add(menuBar, BorderLayout.NORTH);
        topPanel.add(topHeader, BorderLayout.CENTER);
        
        
        

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4, 1));
        buttonsPanel.setBorder(BorderFactory.createTitledBorder("Options"));
        buttonsPanel.setPreferredSize(new Dimension((int) (WIDTH * ((double) 0.2)), (int) (HEIGHT * ((double) 0.8))));

        JButton createButton = new JButton();
        createButton.setText("Create Schedule");
        createButton.addActionListener(createButtonEvent -> getSchedule());
        JButton manageButton = new JButton();
        manageButton.setText("Manage Schedule");
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
     * This method prints the schedule to the console.
     * 
     * 
     */
    public void buildMenuBar() {
        //construct menu bar items
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


        JMenu helpMenu = new JMenu ("Help");
        JMenuItem contentsItem = new JMenuItem ("Contents");
        helpMenu.add (contentsItem);
        JMenuItem aboutItem = new JMenuItem ("About");
        helpMenu.add (aboutItem);

        //construct menubar
        menuBar = new JMenuBar();

        
        menuBar.setPreferredSize(new Dimension(WIDTH, 30));

        menuBar.setBorder(null);
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add (fileMenu);
        menuBar.add (helpMenu);
        this.setJMenuBar(menuBar);
        System.out.println(getJMenuBar());
        
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
     * @param args
     * Main method
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    
    }

}

