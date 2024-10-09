package edu.bbte.idde.bnim2219.swing;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

// main frame that shows up on application start
public class MainFrame extends JFrame {
    public static final Integer DEFAULT_WIDTH = 800;
    public static final Integer DEFAULT_HEIGHT = 600;

    public MainFrame(){
        setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("To-Do List");

        DisplayPanel displayPanel = new DisplayPanel(this);
        add(displayPanel, BorderLayout.CENTER);

        ButtonPanel buttonPanel = new ButtonPanel(displayPanel);
        add(buttonPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
