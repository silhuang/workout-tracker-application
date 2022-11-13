package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class MainMenuGUI extends JPanel {
    private Icon crunches;
    private ImageIcon russianTwists;


    public MainMenuGUI() {
        crunches = createImageIcon("animations/crunches.gif");
        //russianTwists = createImageIcon("animations/russian_twists.gif");
    }

    // Method taken from CustomIconDemo class:
    // https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html
    // EFFECTS: returns an ImageIcon from specified file, or null if the path was invalid
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MainMenuGUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

}
