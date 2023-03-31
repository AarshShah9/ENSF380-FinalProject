package edu.ucalgary.oop;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private JLabel topHeader;
    public GUI() {
        super("EWR schedule manager");
        setupGUI();
        setSize(1000,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setupGUI() {
        
        topHeader = new JLabel("EWR schedule manager");

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(topHeader);

        JPanel schedulePanel = new JPanel();
        schedulePanel.setLayout(new FlowLayout());
        schedulePanel.add(new JLabel("Schedule"));

        this.add(topPanel, BorderLayout.NORTH);
        this.add(schedulePanel, BorderLayout.WEST);

    }

    public void getSchedule() {

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }
}

