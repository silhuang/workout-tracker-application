package ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JSplitPane.VERTICAL_SPLIT;

public class ButtonPane extends JFrame {
    private JButton addTrackButton;
    private JButton deleteTrackButton;
    private JButton addMoveButton;
    private JButton deleteMoveButton;
    private JSplitPane buttonPane;
    private JLabel label;
    private JLabel trackLabel;
    private JLabel moveLabel;

    private JMenuBar menuBar;
    private JMenu trackMenu;
    private JMenu moveMenu;
    private MenuItem addTrackMenuItem;
    private MenuItem deleteTrackMenuItem;
    private MenuItem addMoveMenuItem;
    private MenuItem deleteMoveMenuItem;
    private JMenu quitApplication;
    private JMenu loadApplication;

    private Icon crunches;
    private JLabel crunchesGIF;

    public ButtonPane(String title) {
        setTitle(title);

        WorkoutViewerGUI workoutViewer = new WorkoutViewerGUI();
        JSplitPane top = workoutViewer.getSplitPane();
        top.setBorder(null);

        addTrackButton = new JButton("Add new track");
        deleteTrackButton = new JButton("Delete track");
        trackLabel = new JLabel();
        trackLabel.setLayout(new BoxLayout(trackLabel, BoxLayout.Y_AXIS));
        trackLabel.add(addTrackButton);
        trackLabel.add(deleteTrackButton);

        addMoveButton = new JButton("Add new move");
        deleteMoveButton = new JButton("Delete move");
        moveLabel = new JLabel();
        moveLabel.setLayout(new BoxLayout(moveLabel, BoxLayout.Y_AXIS));
        moveLabel.add(addMoveButton);
        moveLabel.add(deleteMoveButton);


        label = new JLabel();
        label.setLayout(new FlowLayout());
        label.add(trackLabel);
        label.add(moveLabel);

        buttonPane = new JSplitPane(VERTICAL_SPLIT, top, label);

        buttonPane.setOneTouchExpandable(true);
        buttonPane.setDividerLocation(300);

        getContentPane().add(buttonPane);
        setJMenuBar(createMenuBar());
    }

    public JMenuBar createMenuBar() {
        menuBar = new JMenuBar();

        trackMenu = new JMenu("Track Options");
        addTrackMenuItem = new MenuItem("Add new track");
        deleteTrackMenuItem = new MenuItem("Delete track");
        // TODO: unable to call add method on JMenu object - can't be casted to suggested types either
        //trackMenu.add((PopupMenu) addTrackMenuItem);


        moveMenu = new JMenu("Move Options");
        addMoveMenuItem = new MenuItem("Add new move");
        deleteMoveMenuItem = new Menu("Delete move");

        quitApplication = new JMenu("Quit Application");
        askToSave(quitApplication);


        crunches = createScaledImageIcon("animations/crunches.gif");
        crunchesGIF = new JLabel(crunches);
        loadApplication = new JMenu("Load Saved Workout");

        menuBar.add(trackMenu);
        menuBar.add(moveMenu);
        menuBar.add(quitApplication);
        menuBar.add(loadApplication);

        return menuBar;
    }

    //TODO: confirm Dialog not showing up
    private void askToSave(JMenu m) {
        m.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(null,
                        "Would you like to save your workout?",
                        null, JOptionPane.YES_NO_CANCEL_OPTION);

            }
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
