package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

// References:
// BoxLayoutDemo.java:
// https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/
// uiswing/examples/layout/BoxLayoutDemoProject/src/layout/BoxLayoutDemo.java
// https://mkyong.com/swing/java-swing-joptionpane-showinputdialog-example/
public class MainMenuGUI extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
    private JComponent mainMenu;
    private JLabel title;
    private Icon crunches;
    private Icon russianTwists;
    private JLabel crunchesGIF;
    private JButton createWorkoutButton;
    private JButton loadWorkoutButton;
    private JButton quitButton;
    private WorkoutViewerGUI workoutViewer;
    //private WorkoutTrackerGUI workoutTracker;

    // MODIFIES: this
    // EFFECTS: creates a new main menu screen for Workout Tracker application
    public MainMenuGUI() {
//        workoutTracker = new WorkoutTrackerGUI();
//        workoutTracker.addComponentToPane(getContentPane());
        addComponentsToPane(getContentPane());

        workoutViewer = new WorkoutViewerGUI();
        add(workoutViewer);

        pack();
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    // Method based on addComponentsToPane method in BoxLayoutDemo project
    // Additional code referenced from:
    // https://stackoverflow.com/questions/22675289/is-it-possible-to-resize-the-text-in-a-jlabel
    // https://stackoverflow.com/questions/2975489/what-is-the-best-way-to-put-spaces-between-objects-can-a-swing-jseparator-objec
    // EFFECTS: adds all main menu components onto the content pane
    public void addComponentsToPane(Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        title = new JLabel("Workout Tracker App");
        title.setFont(new Font("SanSerif", Font.BOLD, 40));

        crunches = createScaledImageIcon("animations/crunches.gif");
        crunchesGIF = new JLabel(crunches);

        createWorkoutButton = new JButton("Create New Workout");
        addFunctionalityToFirstButton(createWorkoutButton);
        createWorkoutButton.setLayout(new CardLayout());

        loadWorkoutButton = new JButton("Load Saved Workout");
        quitButton = new JButton("Quit application");

        add(Box.createHorizontalStrut(1));
        addComponent(title, pane);
        add(Box.createHorizontalStrut(5));
        addComponent(crunchesGIF, pane);
        add(Box.createHorizontalStrut(20));
        addComponent(createWorkoutButton, pane);
        addComponent(loadWorkoutButton, pane);
        addComponent(quitButton, pane);

    }

    private void addFunctionalityToFirstButton(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title;
                title = JOptionPane.showInputDialog("Please enter the name of your new workout:");
                // TODO: need to get user input
                workoutViewer.setTitle(title);
                workoutViewer.setVisible(true);
                setVisible(false);
            }
        });
    }

    // Method modified from addButton method in BoxLayoutDemoProject
    // MODIFIES: this
    // EFFECTS: adds the given component to the container
    private static void addComponent(JComponent component, Container container) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        component.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        container.add(component);
    }

    // Method based on createImageIcon method from CustomIconDemo class:
    // https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html
    // Additional code referenced from:
    // https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
    // EFFECTS: returns an ImageIcon from specified file, or null if the path was invalid
    protected static ImageIcon createScaledImageIcon(String path) {
        java.net.URL imgURL = ImagePath.class.getResource(path);
        if (imgURL != null) {
            ImageIcon unscaledImageIcon = new ImageIcon(imgURL);
            Image unscaledImage = unscaledImageIcon.getImage();
            Image scaledImage = unscaledImage.getScaledInstance(235, 143, Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
            return scaledImageIcon;
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }


}
