package model;

import java.util.ArrayList;
import java.util.Iterator;

// Represents a track with a title and a certain number of moves
public class Track implements Iterable<Move> {
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


    @Override
    // EFFECTS: returns an iterator over the collection of moves
    public Iterator<Move> iterator() {
        return moves.iterator();
    }
}
