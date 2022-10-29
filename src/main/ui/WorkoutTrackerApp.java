package ui;

import exceptions.UnrealisticRepsException;
import model.Move;
import model.Track;
import model.Workout;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Workout tracker application
// Code based on the Teller App, GitHub link below:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp

// Pilates Workout Tracker Application
public class WorkoutTrackerApp {
    private static final String JSON_FILE = "./data/workout.json";
    private Scanner userInput;
    private Workout workout;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: runs the workout tracker application
    public WorkoutTrackerApp() {
        userInput = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_FILE);
        jsonReader = new JsonReader(JSON_FILE);
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
                showOptionToSaveWorkout(workout);
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
        System.out.println("\t2. Load saved workout");
        System.out.println("\t3. Quit application");
    }

    // MODIFIES: this
    // EFFECTS: processes user entry
    private void processEntry(int userEntry) {
        if (userEntry == 1) {
            createWorkout();
        } else if (userEntry == 2) {
            loadSavedWorkout();
        } else {
            System.out.println("Invalid selection" + "\nPlease try again");
        }
    }

    // EFFECTS: presents users with the option to save their workout before quitting the application
    private void showOptionToSaveWorkout(Workout workout) {
        System.out.println("Would you like to save your workout " + "\"" + workout.getWorkoutTitle() + "\"" + " ?");
        System.out.println("y -> yes");
        System.out.println("n -> no");

        String decision = userInput.next();
        if (decision.equals("y")) {
            saveWorkout(workout);
        } else if (decision.equals("n")) {
            System.out.println("Your workout " + "\"" + workout.getWorkoutTitle() + "\"" + " will not be saved");
            // TODO: clear json file?
        } else {
            System.out.println("Invalid selection, please try again");
            showOptionToSaveWorkout(workout);
        }
    }

    // EFFECTS: saves the workout to file
    private void saveWorkout(Workout workout) {
        try {
            jsonWriter.open();
            jsonWriter.write(workout);
            jsonWriter.close();
            System.out.println("\"" + workout.getWorkoutTitle() + "\"" + " has been saved to " + JSON_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save " + "\"" + workout.getWorkoutTitle() + "\"" + " to " + JSON_FILE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workout from file and its saved tracks
    private void loadSavedWorkout() {
        try {
            Workout savedWorkout = jsonReader.read();
            workout = savedWorkout;
            System.out.println("Loaded " + "\"" + savedWorkout.getWorkoutTitle() + "\"" + " from " + JSON_FILE);
            nextOptionsAfterWorkout();
        } catch (IOException e) {
            System.out.println("An error occurred when trying to load " + "\"" + workout.getWorkoutTitle() + "\""
                    + " from " + JSON_FILE);
        } catch (UnrealisticRepsException e) {
            System.out.println("\"" + workout.getWorkoutTitle() + "\""
                     + " contains a move with an invalid number of reps!");
        }
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
        } else if (userEntry == 3) {
            //showMainMenu();
        } else {
            System.out.println("Invalid selection, please try again");
            nextOptionsAfterWorkout();
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
        } else if (userEntry == 3) {
            //showMainMenu();
        } else {
            System.out.println("Invalid selection, please try again.");
        }
    }

    // EFFECTS: processes user entry to get the track they would like to view
    private void getTrackToViewFromUserInput() {
        System.out.println("Please enter the number corresponding to the track you would like to view:");
        int trackNumber = userInput.nextInt();
        trackNumber--;
        Track trackToView = workout.getTracks().get(trackNumber);
        printMovesInTrack(trackToView);
    }

    // EFFECTS: prints out all the moves in the selected track to view
    private void printMovesInTrack(Track trackToView) {
        if (trackToView.getMoves().isEmpty()) {
            System.out.println(trackToView.getTrackTitle() + "currently has no moves");
        } else {
            for (Move m : trackToView) {
                int moveNumber = trackToView.getMoves().indexOf(m);
                moveNumber++;
                System.out.println(moveNumber + ". Name: " + m.getName() + ", Reps: " + m.getReps());
            }
        }
        showOptionsAfterViewTrackAndProcessSelectedOption(trackToView);

    }

    // EFFECTS: presents user with options regarding moves and processes their selection
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

    // EFFECTS: creates a new move according to user input and adds it to the selected track
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

    // EFFECTS: deletes selected move from the track
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
