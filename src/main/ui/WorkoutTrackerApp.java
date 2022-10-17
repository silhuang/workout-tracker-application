package ui;

import model.Move;
import model.Track;
import model.Workout;

import java.util.ArrayList;
import java.util.Scanner;

// Workout tracker application
// Code based on the Teller App, GitHub link below:

public class WorkoutTrackerApp {
    private Scanner userInput;
    private Workout workout;
    private ArrayList<Track> tracks;


    // EFFECTS: runs the workout tracker application
    public WorkoutTrackerApp() {
        trackWorkout();
    }

    //
    private void trackWorkout() {
        boolean isRunning = true;
        int userEntry = 0;

        while (isRunning) {
            showMainMenu();
            userEntry = userInput.nextInt();

            if (userEntry == 3) {
                isRunning = false;
            } else {
                processEntry(userEntry);
            }
        }

        System.out.println("\nThank you for using the Pilates Workout Tracker Application!");
    }

    private void showMainMenu() {
        System.out.println("\nPlease select one of the following options:");
        System.out.println("\t1. Create a new workout");
        System.out.println("\t2. View workouts");
        System.out.println("\t3. Quit application");
    }

    private void processEntry(int userEntry) {
        if (userEntry == 1) {
            createWorkout();
        } else if (userEntry == 2) {
            viewWorkouts();
        } else {
            System.out.println("Invalid selection" + "\nPlease try again");
        }
    }

    private void viewWorkouts() {
        // stub
    }

    private void createWorkout() {
        //String workoutName = null;
        System.out.println("Please enter the tile of your new workout:");
        String workoutTitle = userInput.next();
        workout = new Workout(workoutTitle);
        System.out.println("Your workout " + workoutTitle + " has been created");
        nextOptionsAfterWorkout();
    }

    private void nextOptionsAfterWorkout() {
        System.out.println("Please select your next option:");
        System.out.println("\t 1. Add new track");
        System.out.println("\t 2. View tracks");
        System.out.println("\t 3. Return to main menu");

        int userEntry = userInput.nextInt();
        processNextOptionsAfterWorkout(userEntry);
    }

    private void processNextOptionsAfterWorkout(int userEntry) {
        if (userEntry == 1) {
            addTrack();
        } else if (userEntry == 2) {
            viewTracks();
        } else {
            showMainMenu();
        }
    }

    private void addTrack() {
        System.out.println("Please enter the title of your new track:");
        String trackTitle = userInput.next();
        Track newTrack = new Track(trackTitle);
        workout.addTrack(newTrack);
        System.out.println(trackTitle + " has been added to the workout " + workout.getWorkoutTitle());
        nextOptionsAfterWorkout();
    }

//    private void nextOptionsAfterTrack() {
//        System.out.println("How would you like to proceed?");
//        System.out.println("\t 1. Add a new move");
//        System.out.println("\t 2. View moves");
//        System.out.println("\t 3. Back to tracklist");
//
//        int userEntry = userInput.nextInt();
//        processOptionsAfterTrack(userEntry);
//    }

//    private void processOptionsAfterTrack(int userEntry) {
//        if (userEntry == 1) {
//            addMove();
//        } else if (userEntry == 2) {
//            viewMoves();
//        } else {
//            viewTracks();
//        }
//    }

//    // can't access track that was added to workout b/c object ref only within scope of addTrack method
//    private void addMove() {
//        System.out.println("Please enter the name of the move you would like to add:");
//        String moveName = userInput.next();
//        Move newMove = new Move(moveName);
//        //workout.getTrack(newTrack);
//        System.out.println(moveName + " has been added to the workout " + workout.getWorkoutTitle());
//        nextOptionsAfterTrack();
//
//    }

    private void viewTracks() {
        for (Track t : workout) {
            String trackName = t.getTrackTitle();
            int position = workout.getTracks().indexOf(t);
            System.out.println(String.valueOf(position++) + trackName);
        }
        afterViewTracks();
    }

    public void afterViewTracks() {
        System.out.println("Please select one of the following options:");
        System.out.println("\t 1. Select a track to view");
        System.out.println("\t 2. Delete a track");
        System.out.println("\t 2. Return to main menu");

        int userEntry = userInput.nextInt();

        if (userEntry == 1) {
            viewTrack();
        } else if (userEntry == 2) {
            deleteTrack();
        } else {
            showMainMenu();
        }
    }

    private void viewTrack() {
        System.out.println("Please enter the number corresponding to the track you would like to view:");
        int trackNumber = userInput.nextInt();
        Track trackToView = workout.getTracks().get(trackNumber--);
        for (Move m : trackToView) {
            int moveNumber = trackToView.getMoves().indexOf(m);
            System.out.println(String.valueOf(moveNumber) + m.getName() + m.getReps());
        }
        int selectedOption = userInput.nextInt();
        showOptionsAfterViewTrack();
        if (selectedOption == 1) {
            System.out.println("Please enter the name of your new move:");
            String newMoveName = userInput.next();
            System.out.println("Please enter the number of reps of your new move:");
            int newMoveReps = userInput.nextInt();
            trackToView.addMove(new Move(newMoveName, newMoveReps));
        } else if (selectedOption == 2) {
            System.out.println("Please enter the number corresponding to the move you would like to delete:");
            int moveToDeleteNum = userInput.nextInt();
            Move moveToDelete = trackToView.getMoves().get(moveToDeleteNum--);
            String moveToDeleteName = moveToDelete.getName();
            trackToView.getMoves().remove(moveToDeleteNum--);
            System.out.println(moveToDeleteName + " has been successfully deleted from " + trackToView.getTrackTitle());
        } else {
            viewTracks();
        }
    }

    private void showOptionsAfterViewTrack() {
        System.out.println("Please select how you would like to proceed:");
        System.out.println("\t 1. Add a move");
        System.out.println("\t 2. Delete a move");
        System.out.println("\t 3. Return to tracklist");
    }

    private void deleteTrack() {
        System.out.println("Please enter the number corresponding to the track you would like to delete:");
        int trackNumber = userInput.nextInt();
        Track trackToDelete = workout.getTracks().get(trackNumber--);
        String trackToDeleteTrackTitle = trackToDelete.getTrackTitle();
        workout.deleteTrack(trackToDelete);
        System.out.println(trackToDeleteTrackTitle + "has been successfully deleted from " + workout.getWorkoutTitle());
    }



}
