package ui;

import exceptions.UnrealisticRepsException;
import model.Move;
import model.Track;
import model.Workout;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.console.WorkoutTrackerApp;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import static javax.swing.JSplitPane.VERTICAL_SPLIT;

public class MainWorkoutViewer extends JFrame implements ListSelectionListener {
    private JSplitPane entirePane;
    private JButton addTrackButton;
    private JButton deleteTrackButton;
    private JButton addMoveButton;
    private JButton deleteMoveButton;
    private JLabel label;
    private JMenuBar menuBar;
    private JMenu quitApplication;
    private JButton loadApplication;
    private WorkoutViewer workoutViewer;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_FILE = "./data/workout.json";


    public MainWorkoutViewer(String title) {
        setTitle(title);
        workoutViewer = new WorkoutViewer();
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
        setJMenuBar(createMenuBar());
        askToSave();
    }

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

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if (workoutViewer.getTracks().isEmpty() || workoutViewer.getTrackList().getSelectedIndex() == -1) {
                deleteTrackButton.setEnabled(false);
            }
            if (workoutViewer.getMoves().isEmpty() || workoutViewer.getTrackList().getSelectedIndex() == -1) {
                deleteMoveButton.setEnabled(false);
            }
        }
    }

    public JMenuBar createMenuBar() {
        menuBar = new JMenuBar();
        loadApplication = new JButton("Load Saved Workout");
        addLoadWorkoutFunction();
        menuBar.add(loadApplication);
        return menuBar;
    }

    private void addLoadWorkoutFunction() {
        loadApplication.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Workout savedWorkout = jsonReader.read();
                    for (Track t : savedWorkout) {
                        workoutViewer.getTracks().addElement(t);
                        for (Move m : t.getMoves()) {
                            workoutViewer.getMoves().addElement(m);
                        }
                    }
                    JOptionPane.showMessageDialog(null,
                            "Loaded " + "\"" + savedWorkout.getWorkoutTitle() + "\"" + " from " + JSON_FILE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null,
                            "An error occurred when trying to load saved workout from " + JSON_FILE);
                } catch (UnrealisticRepsException exception) {
                    System.out.println("The saved workout contains a move with an invalid number of reps!");
                }
            }
        });
    }


    private void askToSave() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int selection = JOptionPane.showConfirmDialog(null,
                        "Would you like to save your workout?",
                        null, JOptionPane.YES_NO_CANCEL_OPTION);
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
                }
            }

            ;
        });
    }


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


