package ui;

import exceptions.UnrealisticRepsException;
import model.Move;
import model.Track;
import model.Workout;

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
        System.out.println("\t2. View saved workout");
        System.out.println("\t3. Quit application");
    }

    // MODIFIES: this
    // EFFECTS: processes user entry
    private void processEntry(int userEntry) {
        if (userEntry == 1) {
            createWorkout();
        } else if (userEntry == 2) {
            viewSavedWorkout();
        } else {
            System.out.println("Invalid selection" + "\nPlease try again");
        }
    }

    // EFFECTS: prints out the tracks of the previously saved workout
    private void viewSavedWorkout() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: creates a new workout with a name of the user's choice
    private void createWorkout() {
        System.out.println("Please enter the tile of your new workout:");
        String workoutTitle = userInput.next();
        workout = new Workout(workoutTitle);
        System.out.println("Your workout " + "\"" + workoutTitle + "\"" + " has been created");
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
            //showMainMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the newly created track to the current workout
    private void addTrack() {
        System.out.println("Please enter the title of your new track:");
        String newTrackTitle = userInput.next();
        for (Track t : workout) {
            String existingTrackTitle = t.getTrackTitle();
            if (existingTrackTitle.equals(newTrackTitle)) {
                System.out.println("Sorry, the title you entered has already been used.");
                addTrack();
            }
        }
        Track newTrack = new Track(newTrackTitle);
        workout.addTrack(newTrack);
        System.out.println("\"" + newTrackTitle + "\"" + " has been added to the workout "
                + "\"" + workout.getWorkoutTitle() + "\"!");
        nextOptionsAfterWorkout();
    }

    // EFFECTS: prints out the name of all tracks added the workout so far in the order they were added
    private void viewTracks() {
        for (Track t : workout) {
            String trackName = t.getTrackTitle();
            int position = workout.getTracks().indexOf(t);
            position++;
            System.out.println(position + ". " + trackName);
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
            getTrackToViewFromUserInput();
        } else if (userEntry == 2) {
            deleteTrack();
        } else {
            //showMainMenu();
        }
    }

    private void getTrackToViewFromUserInput() {
        System.out.println("Please enter the number corresponding to the track you would like to view:");
        int trackNumber = userInput.nextInt();
        trackNumber--;
        Track trackToView = workout.getTracks().get(trackNumber);
        printMovesInTrack(trackToView);
    }

    private void printMovesInTrack(Track trackToView) {
        if (trackToView.getMoves().isEmpty()) {
            System.out.println(trackToView.getTrackTitle() + "currently has no moves");
        } else {
            for (Move m : trackToView) {
                int moveNumber = trackToView.getMoves().indexOf(m);
                moveNumber++;
                System.out.println(moveNumber + ". Name: " + m.getName() + " Reps: " + m.getReps());
            }
        }
        showOptionsAfterViewTrackAndProcessSelectedOption(trackToView);

    }

    private void showOptionsAfterViewTrackAndProcessSelectedOption(Track trackToView) {
        System.out.println("Please select how you would like to proceed:");
        System.out.println("\t 1. Add a move");
        System.out.println("\t 2. Delete a move");
        System.out.println("\t 3. Return to tracklist");

        int selectedOption = userInput.nextInt();

        if (selectedOption == 1) {
            processOption1(trackToView);

        } else if (selectedOption == 2) {
            processOption2(trackToView);
        }
        viewTracks();
    }

    private void processOption1(Track trackToView) {
        System.out.println("Please enter the name of your new move:");
        String newMoveName = userInput.next();
        System.out.println("Please enter the number of reps of your new move:");
        int newMoveReps = userInput.nextInt();
        try {
            trackToView.addMove(new Move(newMoveName, newMoveReps));
            showOptionsAfterViewTrackAndProcessSelectedOption(trackToView);
        } catch (UnrealisticRepsException e) {
            System.out.println("You have entered an unrealistic number of reps. Please try again:");
            printMovesInTrack(trackToView);
        }
    }

    private void processOption2(Track trackToView) {
        System.out.println("Please enter the number corresponding to the move you would like to delete:");
        int moveToDeleteNum = userInput.nextInt();
        moveToDeleteNum--;
        Move moveToDelete = trackToView.getMoves().get(moveToDeleteNum);
        String moveToDeleteName = moveToDelete.getName();
        trackToView.getMoves().remove(moveToDeleteNum);
        System.out.println("\"" + moveToDeleteName + "\"" + " has been successfully deleted from "
                + trackToView.getTrackTitle());
        showOptionsAfterViewTrackAndProcessSelectedOption(trackToView);
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
        System.out.println("\"" + trackToDeleteTrackTitle + "\""
                + " has been successfully deleted from " + workout.getWorkoutTitle());
        viewTracks();
    }


}
