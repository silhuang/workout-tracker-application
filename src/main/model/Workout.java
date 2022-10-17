package model;

import ui.WorkoutTrackerApp;

import java.util.ArrayList;
import java.util.Iterator;


// Represents a workout with a title and a certain number of tracks
public class Workout implements Iterable<Track> {
    private String workoutTitle;
    private ArrayList<Track> tracks;

    // EFFECTS: constructs a new workout with the given title
    //          and an empty set of tracks
    public Workout(String title) {
        workoutTitle = title;
        tracks = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the given track to the workout's set of tracks
    public boolean addTrack(Track track) {
        if (!tracks.contains(track)) {
            tracks.add(track);
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: if track exists in the workout, delete it from the set of tracks
    public boolean deleteTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
            return true;
        }
        return false;
    }

    // EFFECTS: returns the title of the workout
    public String getWorkoutTitle() {
        return workoutTitle;
    }

    // MODIFIES: this
    // EFFECTS: sets the title of the workout to the given title
    public void setWorkoutTitle(String title) {
        workoutTitle = title;
    }

    // EFFECTS: returns the set of tracks added to the workout so far
    public ArrayList<Track> getTracks() {
        return tracks;
    }


    @Override
    public Iterator<Track> iterator() {
        return tracks.iterator();
    }
}
