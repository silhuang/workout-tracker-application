package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Iterator;

// Represents a track with a title and a certain number of moves
public class Track implements Iterable<Move>, Writable {
    private String trackTitle;
    private ArrayList<Move> moves;

    // EFFECTS: creates a new track with the given title and an
    //          empty list of moves
    public Track(String title) {
        trackTitle = title;
        moves = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the given move to the track
    public void addMove(Move move) {
        moves.add(move);
    }

    // MODIFIES: this
    // EFFECTS: deletes the given move from the track if contained in the track
    public boolean deleteMove(Move move) {
        if (moves.contains(move)) {
            moves.remove(move);
            return true;
        }
        return false;
    }


    // EFFECTS: returns the title of the track
    public String getTrackTitle() {
        return trackTitle;
    }

    // EFFECTS: returns the list of moves added to the track so far
    public ArrayList<Move> getMoves() {
        return moves;
    }

    // EFFECTS: replaces title of track with new title
    public void setTrackTitle(String newTitle) {
        trackTitle = newTitle;
    }

    @Override
    // EFFECTS: returns an iterator over the collection of moves
    public Iterator<Move> iterator() {
        return moves.iterator();
    }


    @Override
    // EFFECTS: returns a Track as a JSON object
    public JSONObject toJson() {
        JSONObject jsonTrack = new JSONObject();
        jsonTrack.put("title", trackTitle);
        jsonTrack.put("moves", movesToJson());
        return jsonTrack;
    }

    // EFFECTS: returns moves in this track as a JSON array
    public JSONArray movesToJson() {
        JSONArray jsonMoves = new JSONArray();

        for (Move m : moves) {
            jsonMoves.put(m.toJson());
        }

        return jsonMoves;
    }
}
