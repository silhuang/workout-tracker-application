package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class WorkoutViewerGUI extends JPanel {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
    private Frame viewingFrame;

    public WorkoutViewerGUI() {
        viewingFrame = new JFrame();
        viewingFrame.pack();
        viewingFrame.setSize(WIDTH, HEIGHT);
        //pack();
        setLayout(new FlowLayout());
        setSize(WIDTH, HEIGHT);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        viewingFrame.add(this);

    }

    public void setTitle(String t) {
        viewingFrame.setTitle(t);
//        Border title = BorderFactory.createTitledBorder(t);
//        setBorder(title);
    }
}
