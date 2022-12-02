package ui;

import exceptions.UnrealisticRepsException;
import model.Move;
import model.Track;
import model.Workout;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;

// REFERENCES:
// Override toString method in Track and Move class- https://www.youtube.com/watch?v=KOI1WbkKUpQ

// JOptionPane Class documentation - https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html
// How to Use Split Panes:
// https://docs.oracle.com/javase/tutorial/uiswing/components/splitpane.html
// SplitPaneDemo.java:
// https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/
// examples/components/SplitPaneDemoProject/src/components/SplitPaneDemo.java

// How to Use Lists:
// https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
// ListDemo.java:
// https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/
// examples/components/ListDemoProject/src/components/ListDemo.java

// Represents the workout viewer window where tracks and moves are displayed, and can be added/deleted
public class WorkoutViewer {
    private JSplitPane splitPane;
    private Workout workout;
    private DefaultListModel tracks;
    private JList trackList;
    private DefaultListModel moves;
    private JList moveList;
    private ListSelectionModel listSelectionModel;

    // MODIFIES: this
    // EFFECTS: creates and shows a new workout viewer with an empty track-list
    // and move-list on each side of a split pane
    public WorkoutViewer() {
        workout = new Workout("myWorkout");
        tracks = new DefaultListModel<>();
        trackList = new JList(tracks);
        trackList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel = trackList.getSelectionModel();
        listSelectionModel.addListSelectionListener(new MovesHandler());

        JScrollPane tracksScrollPane = new JScrollPane(trackList);

        moves = new DefaultListModel<>();
        moveList = new JList(moves);
        moveList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane movesScrollPane = new JScrollPane(moveList);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tracksScrollPane, movesScrollPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(200);
    }

    // MODIFIES: this
    // EFFECTS: listens for list selection events in tracks;
    // updates the list of moves displayed everytime a different track is selected
    class MovesHandler implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {

            Track newSelectedTrack = (Track) tracks.getElementAt(trackList.getSelectedIndex());
            ArrayList<Move> movesOfSelectedTrack = newSelectedTrack.getMoves();

            moves.removeAllElements();
            for (Move m : movesOfSelectedTrack) {
                moves.addElement(m);
            }

        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new track with title based on user input after the "Ok" option is selected,
    // otherwise does nothing
    public void addTrackToTrackList() {
        String trackTitle;
        trackTitle = JOptionPane.showInputDialog(null,
                "Please enter the name of your new track:", null,
                JOptionPane.OK_CANCEL_OPTION);
        Track newTrack = new Track(trackTitle);
        workout.addTrack(newTrack);
        tracks.addElement(newTrack);
    }

    // MODIFIES: this
    // EFFECTS: deletes selected track after the "Yes" option is pressed, otherwise does nothing
    public void deleteTrackFromTrackList() {
        Track trackToDelete = (Track) tracks.getElementAt(trackList.getSelectedIndex());
        int selection = JOptionPane.showConfirmDialog(null,
                "Are you sure you would like to delete this track?",
                null, JOptionPane.YES_NO_OPTION);
        if (selection == JOptionPane.YES_OPTION) {
            workout.deleteTrack(trackToDelete);
            moves.removeAllElements();
            tracks.removeElement(trackToDelete);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new move to the selected track with name and reps based on user input
    // after the "Ok" option is selected, otherwise does nothing
    public void addMoveToMoveList() throws UnrealisticRepsException {
        Track selectedTrack = (Track) tracks.getElementAt(trackList.getSelectedIndex());
        String name = JOptionPane.showInputDialog(null,
                "Please enter the name of your new move:",
                null, JOptionPane.OK_CANCEL_OPTION);
        int reps = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Please enter the number of reps of your new move:",
                null, JOptionPane.OK_CANCEL_OPTION));
        Move newMove = new Move(name, reps);
        selectedTrack.addMove(newMove);
        moves.addElement(newMove);
    }

    // MODIFIES: this
    // EFFECTS: deletes selected move after the "Yes" option is pressed, otherwise does nothing
    public void deleteMoveFromMoveList() {
        Track selectedTrack = (Track) tracks.getElementAt(trackList.getSelectedIndex());
        Move moveToDelete = (Move) moves.getElementAt(moveList.getSelectedIndex());
        int selection = JOptionPane.showConfirmDialog(null,
                "Are you sure you would like to delete this move?",
                null, JOptionPane.YES_NO_OPTION);
        if (selection == JOptionPane.YES_OPTION) {
            selectedTrack.deleteMove(moveToDelete);
            moves.removeElement(moveToDelete);
        }
    }

    // EFFECTS: returns the split pane containing the tracks and moves
    public JSplitPane getSplitPane() {
        return splitPane;
    }

    // EFFECTS: returns the default list model containing tracks data
    public DefaultListModel getTracks() {
        return tracks;
    }

    // EFFECTS: returns the default list model containing moves data
    public DefaultListModel getMoves() {
        return moves;
    }

    // EFFECTS: returns the workout that represents the current workout viewer
    public Workout getWorkout() {
        return workout;
    }

}
