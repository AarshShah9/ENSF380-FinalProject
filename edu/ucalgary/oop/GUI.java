package edu.ucalgary.oop;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GUI extends JFrame {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;

    private JLabel topHeader;

    private JScrollPane schedulePanel;

    private JMenuBar menu;

    public GUI() {
        super("EWR schedule manager");
        setupGUI();
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setupGUI() {
        
        buildMenuBar();

        topHeader = new JLabel("EWR schedule manager");

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(topHeader);

        schedulePanel = new JScrollPane();
        schedulePanel.setLayout(new ScrollPaneLayout());


        Border border = BorderFactory.createTitledBorder("Schedule");
        schedulePanel.setBounds(0, 0, 200, 200);
        schedulePanel.setBorder(border);
        schedulePanel.setPreferredSize(new Dimension(200, 200));
        schedulePanel.setAlignmentY(TOP_ALIGNMENT);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(schedulePanel, BorderLayout.WEST);

        try {
            getSchedule();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getSchedule() throws FileNotFoundException {

        BufferedReader reader;

        File schedule = new File("schedule.txt");
        if (!schedule.exists()) {
            throw new FileNotFoundException("File does not exist");
            
        }
        System.out.println("File exists");
        
        JLabel items = new JLabel();
        items.setVerticalAlignment(JLabel.TOP);

        try {
            reader = new BufferedReader(new FileReader(schedule));
            String line = "empty";
            while (line != null) {
                line = reader.readLine();
                if (line != null) {
                    System.out.println(line);
                    items.add(new JLabel(line));
                }
            }
            schedulePanel.add(items);

        } catch (IOException e) {
            e.printStackTrace();
        }

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
        menu = new JMenuBar();
        menu.add (fileMenu);
        menu.add (helpMenu);
        this.setJMenuBar(menu);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }
}

