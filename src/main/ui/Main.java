package ui;

import model.Track;

public class Main {
    // Method based on main method in CustomIconDemo class:
    // https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/
    // uiswing/examples/components/CustomIconDemoProject/src/components/CustomIconDemo.java
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WorkoutTrackerGUI();
            }
        });


    }
}
