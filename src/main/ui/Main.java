package ui;

import javax.swing.*;

public class Main {
    // Method based on main method in CustomIconDemo class:
    // https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/
    // uiswing/examples/components/CustomIconDemoProject/src/components/CustomIconDemo.java
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });


    }

    private static void createAndShowGUI() {
        JFrame frame = new ButtonPane("Workout Tracker App");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        WorkoutViewerGUI workoutViewer = new WorkoutViewerGUI();
//        frame.setJMenuBar(workoutViewer.createImage);
//        frame.getContentPane().add(workoutViewer.getSplitPane());

        frame.pack();
        frame.setSize(500, 400);
        frame.setVisible(true);
    }
}
