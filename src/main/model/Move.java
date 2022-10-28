package model;

import exceptions.NegativeRepsException;
import exceptions.UnrealisticRepsException;
import org.json.JSONObject;
import persistence.Writable;

// Represents a move with a name and number of reps
public class Move implements Writable {
    private String name;
    private int reps;

    // EFFECTS: constructs a new move with the given name and number of reps
    //          number of seconds that the move is held
    public Move(String name, int reps) throws UnrealisticRepsException {
        if (reps > 1000) {
            throw new UnrealisticRepsException();
        }
        if (reps <= 0) {
            throw new UnrealisticRepsException();
        }
        this.name = name;
        this.reps = reps;
    }

    // REQUIRES: newReps > 0
    // MODIFIES: this
    // EFFECTS: changes the number of reps of the current move to newReps,
    //          throws NegativeRepsException if newReps <= 0.
    //          throws UnrealisticRepsException if newReps > 1000
    public boolean changeReps(int newReps) throws NegativeRepsException, UnrealisticRepsException {
        if (newReps <= 0) {
            throw new NegativeRepsException();
        }
        if (newReps > 1000) {
            throw new UnrealisticRepsException();

        } else if (reps != newReps) {
            reps = newReps;
            return true;
        }
        return false;
    }

    // EFFECTS: returns the name of the move
    public String getName() {
        return name;
    }

    // EFFECTS: returns the number of reps
    public int getReps() {
        return reps;
    }


    @Override
    // EFFECTS: returns this move as a JSON object
    public JSONObject toJson() {
        JSONObject jsonMove = new JSONObject();
        jsonMove.put("name", name);
        jsonMove.put("reps", reps);
        return jsonMove;
    }
}
