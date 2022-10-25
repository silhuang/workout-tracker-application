package model;

import exceptions.TitleAlreadyUsedException;
import exceptions.TrackNotFoundException;
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

//    // MODIFIES: this
//    // EFFECTS: adds the given track to the workout's set of tracks
//    public boolean addTrack(Track track) {
//        if (!tracks.contains(track)) {
//            tracks.add(track);
//            return true;
//        }
//        return false;
//    }

    // MODIFIES: this
    // EFFECTS: adds the given track to the workout's set of tracks,
    //          throws TitleAlreadyUsedException if the name of the
    //          track is the same as any track already in the workout
    public void addTrack(Track track) throws TitleAlreadyUsedException {
        String trackToAdd = track.getTrackTitle();
        for (Track t : tracks) {
            String trackTitle = t.getTrackTitle();
            if (trackToAdd == trackTitle) {
                throw new TitleAlreadyUsedException();
            }
        }
        tracks.add(track);
    }

//    // MODIFIES: this
//    // EFFECTS: if track exists in the workout, delete it from the set of tracks
//    public boolean deleteTrack(Track track) {
//        if (tracks.contains(track)) {
//            tracks.remove(track);
//            return true;
//        }
//        return false;
//    }

    // MODIFIES: this
    // EFFECTS: if track exists in the workout, delete it from the set of tracks,
    //          throws TrackNotFoundException if the workout doesn't contain
    //          this track
    public void deleteTrack(Track track) throws TrackNotFoundException {
        String trackToDelete = track.getTrackTitle();
        for (Track t : tracks) {
            String trackTitle = t.getTrackTitle();
            if (trackToDelete == trackTitle) {
                tracks.remove(track);
            }
        }
        throw new TrackNotFoundException();
    }

    // EFFECTS: returns the title of the workout
    public String getWorkoutTitle() {
        return workoutTitle;
    }

    // EFFECTS: returns the set of tracks added to the workout so far
    public ArrayList<Track> getTracks() {
        return tracks;
    }


    @Override
    // EFFECTS: returns an iterator over the collection of tracks
    public Iterator<Track> iterator() {
        return tracks.iterator();
    }
}
