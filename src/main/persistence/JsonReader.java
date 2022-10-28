package persistence;

import exceptions.UnrealisticRepsException;
import model.Move;
import model.Track;
import model.Workout;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class JsonReader {
    private String source;


    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workout from file and returns it;
    //          throws IOException if an error is encountered while reading data from file
    public Workout read() throws IOException, UnrealisticRepsException {
        String jsonData = readFile(source);
        JSONObject jsonWorkout = new JSONObject(jsonData);
        return parseWorkout(jsonWorkout);
    }

    // EFFECTS: reads source file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workout from JSON object and returns it
    private Workout parseWorkout(JSONObject jsonWorkout) throws UnrealisticRepsException {
        String title = jsonWorkout.getString("workout title");
        Workout workout = new Workout(title);
        addTracks(workout, jsonWorkout);
        return workout;
    }


    // MODIFIES: workout
    // EFFECTS: parses tracks from JSON object and adds it to workout
    private void addTracks(Workout workout, JSONObject jsonWorkout) throws UnrealisticRepsException {
        JSONArray tracks = jsonWorkout.getJSONArray("tracks");
        for (Object json : tracks) {
            JSONObject nextTrack = (JSONObject) json;
            addTrack(workout, nextTrack);
        }
    }

    // MODIFIES: workout
    // EFFECTS: parses track from JSON object and adds it to workout
    private void addTrack(Workout workout, JSONObject jsonTrack) throws UnrealisticRepsException {
        String title = jsonTrack.getString("track title");
        Track track = new Track(title);
        addMoves(track, jsonTrack);
        workout.addTrack(track);
    }


    // MODIFIES: tr
    // EFFECTS: parses moves from JSON object and adds them to track, then adds track to workout
    private void addMoves(Track tr, JSONObject jsonTrack) throws UnrealisticRepsException {
        JSONArray moves = jsonTrack.getJSONArray("moves");
        for (Object json : moves) {
            JSONObject nextMove = (JSONObject) json;
            addMove(tr, nextMove);
        }
    }

    // MODIFIES: tr
    // EFFECTS: parses move from JSON object and adds it to the track
    private void addMove(Track tr, JSONObject jsonMove) throws UnrealisticRepsException {
        String name = jsonMove.getString("name");
        int reps = jsonMove.getInt("reps");
        Move move = new Move(name, reps);
        tr.addMove(move);

    }
}
