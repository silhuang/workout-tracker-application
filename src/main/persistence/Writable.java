package persistence;

import org.json.JSONObject;

// Code referenced from JsonSerializationDemo, GitHub link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public interface Writable {
    // EFFECTS: writes the instance of its implementing class as a JSON object
    JSONObject toJson();
}
