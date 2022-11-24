package ui;

import model.Workout;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkoutViewer implements ListSelectionListener {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 700;
    private JList trackList;
    private JList moveList;
    private JSplitPane splitPane;

    public WorkoutViewer() {
        trackList = new JList<>();
        trackList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        trackList.addListSelectionListener(this);

        JScrollPane tracksScrollPane = new JScrollPane(trackList);

        moveList = new JList<>();
        moveList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane movesScrollPane = new JScrollPane(moveList);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tracksScrollPane, movesScrollPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(200);
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        trackList = (JList) e.getSource();


    }

    public JSplitPane getSplitPane() {
        return splitPane;
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
