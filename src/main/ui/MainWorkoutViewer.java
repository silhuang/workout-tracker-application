package ui;

import exceptions.UnrealisticRepsException;
import model.Move;
import model.Track;
import model.Workout;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import static javax.swing.JSplitPane.VERTICAL_SPLIT;

// REFERENCES:

// SplitPaneDemo2.java:
// https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/
// examples/components/SplitPaneDemo2Project/src/components/SplitPaneDemo2.java

// How to Use Buttons, Check Boxes, and Radio Buttons:
// https://docs.oracle.com/javase/tutorial/uiswing/components/button.html
// ButtonDemo.java:
// https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/
// examples/components/ButtonDemoProject/src/components/ButtonDemo.java

// Represents the entire workout viewer; a frame containing the top splitPane with the track-list and move-list,
// and buttons on the bottom pane
public class MainWorkoutViewer extends JFrame {
    private JSplitPane entirePane;
    private JButton addTrackButton;
    private JButton deleteTrackButton;
    private JButton addMoveButton;
    private JButton deleteMoveButton;
    private JLabel label;
    private WorkoutViewer workoutViewer;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_FILE = "./data/workout.json";

    // MODIFIES: this
    // EFFECTS: creates and shows the main workout viewer frame, with top pane containing the
    // track-list and move-list split pane, and bottom pane with buttons
    public MainWorkoutViewer(String title) {
        setTitle(title);
        setSize(500, 400);

        workoutViewer = new WorkoutViewer();
        workoutViewer.getWorkout().setWorkoutTitle(title);
        jsonWriter = new JsonWriter(JSON_FILE);
        jsonReader = new JsonReader(JSON_FILE);
        JSplitPane top = workoutViewer.getSplitPane();
        top.setBorder(null);

        createTrackButtons();
        createMoveButtons();

        label = new JLabel();
        label.setLayout(new GridLayout(2, 2));
        label.add(addTrackButton);
        label.add(addMoveButton);
        label.add(deleteTrackButton);
        label.add(deleteMoveButton);

        entirePane = new JSplitPane(VERTICAL_SPLIT, top, label);
        entirePane.setOneTouchExpandable(true);
        entirePane.setDividerLocation(250);

        getContentPane().add(entirePane);
        askToSave();
    }

    // MODIFIES: this
    // EFFECTS: adds the add/delete track functionality to the "Add new track" and "Delete track" buttons, respectively
    private void createTrackButtons() {
        addTrackButton = new JButton("Add new track");
        addTrackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workoutViewer.addTrackToTrackList();
            }
        });

        deleteTrackButton = new JButton("Delete track");
        deleteTrackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workoutViewer.deleteTrackFromTrackList();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adds the add/delete move functionality to the "Add new move" and "Delete move" buttons, respectively
    private void createMoveButtons() {
        addMoveButton = new JButton("Add new move");
        addMoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    workoutViewer.addMoveToMoveList();
                } catch (UnrealisticRepsException ex) {
                    System.out.println("An unrealistic number of reps has been entered, please try again!");
                }
            }
        });

        deleteMoveButton = new JButton("Delete move");
        deleteMoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workoutViewer.deleteMoveFromMoveList();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: loads from file all tracks and moves of each track in previously saved workout
    // in main workout viewer window
    public void loadSavedWorkout() {
        try {
            Workout savedWorkout = jsonReader.read();
            String savedTitle = savedWorkout.getWorkoutTitle();
            setTitle(savedTitle);
            for (Track t : savedWorkout) {
                workoutViewer.getTracks().addElement(t);
                for (Move m : t.getMoves()) {
                    workoutViewer.getMoves().addElement(m);
                }
            }
            JOptionPane.showMessageDialog(null,
                    "Loaded " + "\"" + savedWorkout.getWorkoutTitle() + "\"" + " from " + JSON_FILE,
                    null, JOptionPane.INFORMATION_MESSAGE);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(null,
                    "An error occurred when trying to load saved workout from " + JSON_FILE);
        } catch (UnrealisticRepsException exception) {
            System.out.println("The saved workout contains a move with an invalid number of reps!");
        }
    }

    // EFFECTS: provides user with the option to save the workout
    private void askToSave() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int selection = JOptionPane.showOptionDialog(null,
                        "Would you like to save your workout?",
                        null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, null, null);
                processUserSelection(selection);
            }
        });

    }

    // EFFECTS: processes user selection related to the choice of saving the workout
    private void processUserSelection(int selection) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        if (selection == JOptionPane.YES_OPTION) {
            Workout workoutToSave = workoutViewer.getWorkout();
            try {
                jsonWriter.open();
                jsonWriter.write(workoutToSave);
                jsonWriter.close();
                JOptionPane.showMessageDialog(null,
                        "\"" + workoutToSave.getWorkoutTitle() + "\"" + " has been saved to " + JSON_FILE);

            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null,
                        "Unable to save " + "\"" + workoutToSave.getWorkoutTitle() + "\"" + " to " + JSON_FILE);
            }
        } else if (selection == JOptionPane.CANCEL_OPTION) {
            setVisible(true);
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }
        workoutViewer.getWorkout().endWorkout();
    }


}


