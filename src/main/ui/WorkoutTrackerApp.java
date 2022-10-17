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
        System.out.println("\t2. View workouts");
        System.out.println("\t3. Quit application");
    }

    // MODIFIES: this
    // EFFECTS: processes user entry
    private void processEntry(int userEntry) {
        if (userEntry == 1) {
            createWorkout();
        } else if (userEntry == 2) {
            viewWorkouts();
        } else {
            System.out.println("Invalid selection" + "\nPlease try again");
        }
    }

    // EFFECTS: prints out all workouts user has created so far (tentative method, may remove later)
    private void viewWorkouts() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: creates a new workout with a name of the user's choice
    private void createWorkout() {
        System.out.println("Please enter the tile of your new workout:");
        String workoutTitle = userInput.next();
        workout = new Workout(workoutTitle);
        System.out.println("Your workout " + workoutTitle + " has been created");
        nextOptionsAfterWorkout();
    }

    // MODIFIES: this
    // EFFECTS: shows options after workout is created and processes subsequent entry
    private void nextOptionsAfterWorkout() {
        System.out.println("Please select your next option:");
        System.out.println("\t 1. Add new track");
        System.out.println("\t 2. View tracks");
        System.out.println("\t 3. Return to main menu");

        int userEntry = userInput.nextInt();

        if (userEntry == 1) {
            addTrack();
        } else if (userEntry == 2) {
            viewTracks();
        } else {
            showMainMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the newly created track to the current workout
    private void addTrack() {
        System.out.println("Please enter the title of your new track:");
        String trackTitle = userInput.next();
        Track newTrack = new Track(trackTitle);
        workout.addTrack(newTrack);
        System.out.println(trackTitle + " has been added to the workout " + workout.getWorkoutTitle() + "!");
        nextOptionsAfterWorkout();
    }

    // EFFECTS: prints out the name of all tracks added the workout so far in the order they were added
    private void viewTracks() {
        for (Track t : workout) {
            String trackName = t.getTrackTitle();
            int position = workout.getTracks().indexOf(t);
            position++;
            System.out.println(String.valueOf(position) + ". " + trackName);
        }
        afterViewTracks();
    }

    // MODIFIES: this
    // EFFECTS: displays available options after tracks are viewed, processes subsequent user entry
    public void afterViewTracks() {
        System.out.println("Please select one of the following options:");
        System.out.println("\t 1. Select a track to view");
        System.out.println("\t 2. Delete a track");
        System.out.println("\t 3. Return to main menu");

        int userEntry = userInput.nextInt();

        if (userEntry == 1) {
            viewTrack();
        } else if (userEntry == 2) {
            deleteTrack();
        } else {
            showMainMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: prints out all moves added to track so far, shows and processes options related to moves
    @SuppressWarnings("methodlength")
    private void viewTrack() {
        System.out.println("Please enter the number corresponding to the track you would like to view:");
        int trackNumber = userInput.nextInt();
        trackNumber--;
        Track trackToView = workout.getTracks().get(trackNumber);

        for (Move m : trackToView) {
            int moveNumber = trackToView.getMoves().indexOf(m);
            moveNumber++;
            System.out.println(String.valueOf(moveNumber) + ". Name: " + m.getName() + " Reps: " + m.getReps());
        }

        System.out.println("Please select how you would like to proceed:");
        System.out.println("\t 1. Add a move");
        System.out.println("\t 2. Delete a move");
        System.out.println("\t 3. Return to tracklist");

        int selectedOption = userInput.nextInt();

        if (selectedOption == 1) {
            System.out.println("Please enter the name of your new move:");
            String newMoveName = userInput.next();
            System.out.println("Please enter the number of reps of your new move:");
            int newMoveReps = userInput.nextInt();
            trackToView.addMove(new Move(newMoveName, newMoveReps));

        } else if (selectedOption == 2) {
            System.out.println("Please enter the number corresponding to the move you would like to delete:");
            int moveToDeleteNum = userInput.nextInt();
            moveToDeleteNum--;
            Move moveToDelete = trackToView.getMoves().get(moveToDeleteNum);
            String moveToDeleteName = moveToDelete.getName();
            trackToView.getMoves().remove(moveToDeleteNum--);
            System.out.println(moveToDeleteName + " has been successfully deleted from " + trackToView.getTrackTitle());
        }
        viewTracks();
    }

    // MODIFIES: this
    // EFFECTS: deletes the track based on user entry
    private void deleteTrack() {
        System.out.println("Please enter the number corresponding to the track you would like to delete:");
        int trackNumber = userInput.nextInt();
        trackNumber--;
        Track trackToDelete = workout.getTracks().get(trackNumber);
        String trackToDeleteTrackTitle = trackToDelete.getTrackTitle();
        workout.deleteTrack(trackToDelete);
        System.out.println(trackToDeleteTrackTitle
                + " has been successfully deleted from " + workout.getWorkoutTitle());
        viewTracks();
    }



}
