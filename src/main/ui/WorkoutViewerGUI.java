package ui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class WorkoutViewerGUI extends JPanel implements ListSelectionListener {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 700;
    private JList trackList;
    private JList moveList;
    private JSplitPane splitPane;
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

    public WorkoutViewerGUI() {
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
