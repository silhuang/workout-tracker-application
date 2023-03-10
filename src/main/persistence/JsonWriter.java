package persistence;

import model.Event;
import model.EventLog;
import model.Workout;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Code referenced from JsonSerializationDemo, GitHub link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a writer that writes a JSON representation of a workout to file
public class JsonWriter {
    private String destination;
    private PrintWriter writer;

    // EFFECTS: constructs a writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if error occurred when trying to
    //          open destination file for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workout to file
    public void write(Workout w) {
        JSONObject jsonWorkout = w.toJson();
        saveToFile(jsonWorkout.toString());
        EventLog.getInstance().logEvent(new Event(w.getWorkoutTitle() + " was saved to file"));
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }
}
