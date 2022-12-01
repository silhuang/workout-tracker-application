package ui;

import exceptions.UnrealisticRepsException;
import model.Move;
import model.Track;
import model.Workout;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//References:
// https://www.youtube.com/watch?v=KOI1WbkKUpQ - Override toString method in Track and Move class
public class WorkoutViewer {
    private JSplitPane splitPane;
    private Workout workout;
    private DefaultListModel tracks;
    private JList trackList;
    private DefaultListModel moves;
    private JList moveList;
    private ListSelectionModel listSelectionModel;


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


    public void addTrackToTrackList() {
        String trackTitle;
        trackTitle = JOptionPane.showInputDialog(null,
                "Please enter the name of your new track:", null,
                JOptionPane.OK_CANCEL_OPTION);
        Track newTrack = new Track(trackTitle);
        workout.addTrack(newTrack);
        tracks.addElement(newTrack);
    }

    public void deleteTrackFromTrackList() {
        Track trackToDelete = (Track) tracks.getElementAt(trackList.getSelectedIndex());
        JOptionPane.showConfirmDialog(null,
                "Are you sure you would like to delete this track?", null,
                JOptionPane.YES_NO_OPTION);
        workout.deleteTrack(trackToDelete);
        tracks.removeElement(trackToDelete);
    }

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

    public void deleteMoveFromMoveList() {
        Track selectedTrack = (Track) tracks.getElementAt(trackList.getSelectedIndex());
        Move moveToDelete = (Move) moves.getElementAt(moveList.getSelectedIndex());
        JOptionPane.showConfirmDialog(null,
                "Are you sure you would like to delete this move?", null,
                JOptionPane.YES_NO_OPTION);
        selectedTrack.deleteMove(moveToDelete);
        moves.removeElement(moveToDelete);

    }




    public JSplitPane getSplitPane() {
        return splitPane;
    }

    public JList getTrackList() {
        return trackList;
    }

    public DefaultListModel getTracks() {
        return tracks;
    }

    public JList getMoveList() {
        return moveList;
    }

    public DefaultListModel getMoves() {
        return moves;
    }

    public Workout getWorkout() {
        return workout;
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
