package edu.ucalgary.oop;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private JLabel topHeader;
    public GUI() {
        super("EWR schedule manager");
        setupGUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setupGUI() {
        topHeader = new JLabel("EWR schedule manager");
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }
}

