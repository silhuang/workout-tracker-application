package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// REFERENCES:

// BoxLayoutDemo.java:
// https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/
// uiswing/examples/layout/BoxLayoutDemoProject/src/layout/BoxLayoutDemo.java
// How to Use BoxLayout:
// https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html

// https://mkyong.com/swing/java-swing-joptionpane-showinputdialog-example/

// DialogDemo.java:
// https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/
// examples/components/DialogDemoProject/src/components/DialogDemo.java
// How To Use Dialogs:
// https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html

// CustomIconDemo.java:
// https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/
// examples/components/CustomIconDemoProject/src/components/CustomIconDemo.java
// How To Use Icons:
// https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/

// Main Menu of the Workout Tracker Graphical User Interface Application
public class MainMenu extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
    private JLabel title;
    private Icon crunches;
    private JLabel crunchesGIF;
    private JButton createWorkoutButton;
    private JButton loadWorkoutButton;
    private JButton quitButton;
    private MainWorkoutViewer workoutViewer;
    private String workoutTitle;


    // MODIFIES: this
    // EFFECTS: creates a new main menu screen for Workout Tracker application
    public MainMenu() {
        addComponentsToPane(getContentPane());

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
        addFunctionalityToLoadButton(loadWorkoutButton);

        add(Box.createHorizontalStrut(1));
        addComponent(title, pane);
        add(Box.createHorizontalStrut(1));
        addComponent(crunchesGIF, pane);
        add(Box.createHorizontalStrut(1));
        addComponent(createWorkoutButton, pane);
        addComponent(loadWorkoutButton, pane);
        add(Box.createHorizontalStrut(10));
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter workout title when the "Create New Workout" button is clicked;
    //          creates and shows a new workout viewer when selection is confirmed
    private void addFunctionalityToFirstButton(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workoutTitle = JOptionPane.showInputDialog(null,
                        "Please enter the name of your new workout:",
                        null, JOptionPane.OK_CANCEL_OPTION);
                setVisible(false);
                workoutViewer = new MainWorkoutViewer(workoutTitle);
                workoutViewer.setVisible(true);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: loads previously saved workout from file when the "Load Saved Workout" button is clicked
    private void addFunctionalityToLoadButton(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                workoutViewer = new MainWorkoutViewer(workoutTitle);
                workoutViewer.loadSavedWorkout();
                workoutViewer.setVisible(true);
            }
        });
    }

    // Method modified from addButton method in BoxLayoutDemoProject
    // MODIFIES: this
    // EFFECTS: adds the given component to the container
    private static void addComponent(JComponent component, Container container) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        component.setAlignmentY(Component.CENTER_ALIGNMENT);
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
