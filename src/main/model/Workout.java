package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Iterator;


// Represents a workout with a title and a certain number of tracks
public class Workout implements Iterable<Track>, Writable {
    private String workoutTitle;
    private ArrayList<Track> tracks;

    // EFFECTS: constructs a new workout with the given title
    //          and an empty set of tracks
    public Workout(String title) {
        workoutTitle = title;
        tracks = new ArrayList<>();
        EventLog.getInstance().logEvent(new Event("A new workout titled " + title + " was created"));
    }

    // MODIFIES: this
    // EFFECTS: adds the given track to the workout's set of tracks
    public boolean addTrack(Track track) {
        String trackToAdd = track.getTrackTitle();
        for (Track t : tracks) {
            String trackTitle = t.getTrackTitle();
            if (trackToAdd.equals(trackTitle)) {
                return false;
            }
        }
        tracks.add(track);
        EventLog.getInstance().logEvent(new Event("A new track titled "
                + track.getTrackTitle() + " was added to " + workoutTitle));
        return true;
    }


    // MODIFIES: this
    // EFFECTS: if track exists in the workout, delete it from the set of tracks,
    public boolean deleteTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
            EventLog.getInstance().logEvent(new Event(track.getTrackTitle()
                    + " was deleted from" + workoutTitle));
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
        this.workoutTitle = title;
    }

    // EFFECTS: returns the set of tracks added to the workout so far
    public ArrayList<Track> getTracks() {
        return tracks;
    }

    // EFFECTS: logs the event of ending the workout (i.e. closing the application)
    public void endWorkout() {
        EventLog.getInstance().printLoggedEvents();
    }

    @Override
    // EFFECTS: returns an iterator over the collection of tracks
    public Iterator<Track> iterator() {
        return tracks.iterator();
    }

    @Override
    // EFFECTS: returns this workout as a JSON object
    public JSONObject toJson() {
        JSONObject jsonWorkout = new JSONObject();
        jsonWorkout.put("workout title", workoutTitle);
        jsonWorkout.put("tracks", new JSONArray(tracks));
        return jsonWorkout;
    }
}
