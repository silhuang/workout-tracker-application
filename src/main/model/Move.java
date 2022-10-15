package model;

// Represents a move with a name and number of reps
public class Move {
    private String name;
    private int reps;

    // EFFECTS: constructs a new move with the given name and number of reps
    //          number of seconds that the move is held
    public Move(String name, int reps) {
        this.name = name;
        this.reps = reps;
    }

    // REQUIRES: newReps > 0
    // MODIFIES: this
    // EFFECTS: changes the number of reps of the current move to newReps
    public boolean changeReps(int newReps) {
        if (reps != newReps) {
            reps = newReps;
            return true;
        }
        return false;
    }

    // EFFECTS: returns the name of the move
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: sets the name of the move to newName
    public void setName(String newName) {
        name = newName;
    }

    // EFFECTS: returns the number of reps
    public int getReps() {
        return reps;
    }



}
