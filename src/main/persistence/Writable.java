package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: writes the instance of its implementing class as a JSON object
    JSONObject toJson();
}
