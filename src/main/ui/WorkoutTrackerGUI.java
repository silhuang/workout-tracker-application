package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class WorkoutTrackerGUI extends JPanel implements ItemListener {
    MainMenuGUI mainMenu;
    WorkoutViewerGUI workoutViewer;

    public void addComponentToPane(Container pane) {
        mainMenu = new MainMenuGUI();
        workoutViewer = new WorkoutViewerGUI();
        setLayout(new CardLayout());
        add(mainMenu);
        add(workoutViewer);

        pane.add(this, BorderLayout.CENTER);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        CardLayout c = (CardLayout) getLayout();
        c.show(this, (String) e.getItem());
    }

    public void setNewWorkoutViewer(String title) {
        workoutViewer.setTitle(title);
    }
}
