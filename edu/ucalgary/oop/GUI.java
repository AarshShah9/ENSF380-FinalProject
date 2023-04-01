package edu.ucalgary.oop;

import javax.swing.*;


import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class GUI extends JFrame {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;

    private JLabel topHeader;

    private JPanel topPanel;
    private JPanel schedulePanel;
    private JPanel buttonsPanel;

    private JMenuBar menuBar;

    JCheckBoxMenuItem volunteerCheck;

    public GUI() {
        super("EWR schedule manager");
        setupGUI();
        
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setupGUI() {
        
        buildMenuBar();

        topHeader = new JLabel("EWR schedule manager");
        
        topHeader.setHorizontalAlignment(SwingConstants.CENTER);
        topHeader.setVerticalAlignment(SwingConstants.CENTER);

        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        
        topPanel.add(menuBar, BorderLayout.NORTH);
        topPanel.add(topHeader, BorderLayout.SOUTH);
        //topPanel.setPreferredSize(new Dimension(WIDTH, (int) (HEIGHT * ((double) 0.2))));

        

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4, 1));
        buttonsPanel.setBorder(BorderFactory.createTitledBorder("Options"));
        buttonsPanel.setPreferredSize(new Dimension((int) (WIDTH * ((double) 0.2)), (int) (HEIGHT * ((double) 0.6))));

        JButton createButton = new JButton();
        createButton.setText("Create Schedule");
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
        schedulePanel.setLayout(new FlowLayout());
        schedulePanel.setBorder(BorderFactory.createTitledBorder("Schedule"));
        schedulePanel.setPreferredSize(new Dimension((int) (WIDTH * ((double) 0.8)), (int) (HEIGHT * ((double) 0.6))));

        this.add(topPanel, BorderLayout.NORTH);
        this.add(schedulePanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.EAST);

    
        getSchedule();
       
    }

    public void getSchedule() {

        JTextArea scheduleText = new JTextArea();;

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
        scrollPane.setMaximumSize(new Dimension((int) (WIDTH * ((double) 0.8)), (int) (HEIGHT * ((double) 0.6))));
        scrollPane.setPreferredSize(schedulePanel.getPreferredSize());

        schedulePanel.add(scrollPane);
    }

    public void buildMenuBar() {
        //construct menu bar items
        JMenu fileMenu = new JMenu ("File");
        JMenuItem print_scheduleItem = new JMenuItem ("Print Schedule");
        fileMenu.add (print_scheduleItem);
        JMenuItem create_scheduleItem = new JMenuItem ("Create Schedule");
        fileMenu.add (create_scheduleItem);
        JMenuItem exitItem = new JMenuItem ("Exit");
        fileMenu.add (exitItem);
        JMenu helpMenu = new JMenu ("Help");
        JMenuItem contentsItem = new JMenuItem ("Contents");
        helpMenu.add (contentsItem);
        JMenuItem aboutItem = new JMenuItem ("About");
        helpMenu.add (aboutItem);

        //construct menubar
        menuBar = new JMenuBar();
        menuBar.add (fileMenu);
        menuBar.add (helpMenu);
        this.setJMenuBar(menuBar);
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

            JFrame volunteerConfirmation = new JFrame("Volunteer Confirmation");
            volunteerConfirmation.setSize(300, 100);
            volunteerConfirmation.setLocationRelativeTo(this);
            volunteerConfirmation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            volunteerConfirmation.setVisible(true);
            volunteerConfirmation.setResizable(false);

            
            JLabel volunteerLabel = new JLabel("Please confirm that a volunteer is present");
            
            volunteerLabel.setPreferredSize(new Dimension(300, 50));
            //volunteerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            volunteerLabel.setHorizontalAlignment(SwingConstants.CENTER);
            volunteerConfirmation.add(volunteerLabel, BorderLayout.NORTH);

            JButton closePopup = new JButton("Ok");
            closePopup.setMaximumSize(new Dimension(100, 50));
            closePopup.setMargin(new Insets(0, 10, 0, 10));;
            closePopup.addActionListener(volunteerConfirmEvent -> volunteerConfirmation.dispose());
            volunteerConfirmation.add(closePopup, BorderLayout.SOUTH);

            // Disable the main frame
            setEnabled(false);

            // Add a window listener to the popup frame
            volunteerConfirmation.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    // Re-enable the main frame when the popup frame is closed
                    setEnabled(true);
                    setVisible(true);
                }
            });
        
        }
    }

    public void createSchedule() {
        //TODO
    }

    public void exit() {
        //TODO
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }

}

