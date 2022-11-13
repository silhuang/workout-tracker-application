package ui;

import javax.swing.*;

public class WorkoutTrackerGUI extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
    private JComponent mainMenu;

    public WorkoutTrackerGUI() {
        super("Workout Tracker App");

        // Set up content pane for main menu
        mainMenu = new MainMenuGUI();
        mainMenu.setOpaque(true);
        setContentPane(mainMenu);

        pack();
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

}
