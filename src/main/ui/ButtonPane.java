package ui;

import ui.WorkoutViewerGUI;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;

import static javax.swing.JSplitPane.VERTICAL_SPLIT;

public class ButtonPane extends JFrame {
    private JButton addTrackButton;
    private JButton loadTrackButton;
    private JButton addMoveButton;
    private JButton deleteMoveButton;
    private JSplitPane buttonPane;

    public ButtonPane() {

        WorkoutViewerGUI workoutViewer = new WorkoutViewerGUI();
        JSplitPane top = workoutViewer.getSplitPane();
        top.setBorder(null);

        //TODO: not recognizing correct constructor
        //buttonPane = new JSplitPane(VERTICAL_SPLIT, top)

        pack();
        setVisible(true);
    }

}
