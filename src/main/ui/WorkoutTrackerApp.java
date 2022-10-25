package ui;

import model.Move;
import model.Track;
import model.Workout;

import java.util.ArrayList;
import java.util.Scanner;

// Workout tracker application
// Code based on the Teller App, GitHub link below:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp

// Pilates Workout Tracker Application
public class WorkoutTrackerApp {
    private Scanner userInput = new Scanner(System.in);
    private Workout workout;


    // EFFECTS: runs the workout tracker application
    public WorkoutTrackerApp() {
        trackWorkout();
    }

    // MODIFIES this;
    // EFFECTS: tracks a workout based on user input
    private void trackWorkout() {
        boolean isRunning = true;
        int userEntry;

        while (isRunning) {
            showMainMenu();
            userInput.useDelimiter("\n");
            userEntry = userInput.nextInt();

            if (userEntry == 3) {
                isRunning = false;
            } else {
                processEntry(userEntry);
            }
        }

        System.out.println("\nThank you for using the Pilates Workout Tracker Application!");
    }

    // EFFECTS: displays the main menu options
    private void showMainMenu() {
        System.out.println("\nPlease select one of the following options:");
        System.out.println("\t1. Create a new workout");
        System.out.println("\t2. View saved workouts");
        System.out.println("\t3. Quit application");
    }

    // MODIFIES: this
    // EFFECTS: processes user entry
    private void processEntry(int userEntry) {
        if (userEntry == 1) {
            //createWorkout();
        } else if (userEntry == 2) {
            //viewWorkouts();
        } else {
            System.out.println("Invalid selection" + "\nPlease try again");
        }
    }




}
