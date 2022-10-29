package persistence;

import exceptions.UnrealisticRepsException;
import model.Move;
import model.Track;
import model.Workout;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noWorkout.json");
        try {
            Workout w = reader.read();
            fail("IOException was not caught");
        } catch (IOException e) {
            // pass
        } catch (UnrealisticRepsException e) {
            fail("No moves can be inside a nonexistent file");
        }
    }

    @Test
    void testReaderEmptyWorkout() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkout.json");
        try {
            Workout w = reader.read();
            assertEquals("Workout without tracks", w.getWorkoutTitle());
            assertEquals(0, w.getTracks().size());
        } catch (IOException e) {
            fail("An error occurred when fetching workout from file");
        } catch (UnrealisticRepsException e) {
            fail("There are no tracks, so there cannot be any moves");
        }
    }

    @Test
    void testReaderNonEmptyWorkout() {
        JsonReader reader = new JsonReader("./data/testReaderNonEmptyWorkout.json");
        try {
            Workout workout = reader.read();
            ArrayList<Track> tracks = workout.getTracks();

            Track firstTrack = tracks.get(0);
            ArrayList<Move> firstTrackMoves = firstTrack.getMoves();

            Track secondTrack = tracks.get(1);
            ArrayList<Move> secondTrackMoves = secondTrack.getMoves();

            assertEquals("Workout with tracks", workout.getWorkoutTitle());
            checkTrack("track1", firstTrackMoves, firstTrack);
            assertEquals("track1", firstTrack.getTrackTitle());
            assertEquals(2, firstTrackMoves.size());
            checkMove("lunges", 10, firstTrackMoves.get(0));
            assertEquals("lunges", firstTrackMoves.get(0).getName());
            assertEquals(10, firstTrackMoves.get(0).getReps());
            checkMove("jumping jacks", 30, firstTrackMoves.get(1));
            assertEquals("jumping jacks", firstTrackMoves.get(1).getName());
            assertEquals(30, firstTrackMoves.get(1).getReps());

            checkTrack("track2", secondTrackMoves, secondTrack);
            assertEquals(0, secondTrackMoves.size());

        } catch (IOException e) {
            fail();
        } catch (UnrealisticRepsException e) {
            fail("All moves had a valid number of reps");
        }
    }

    @Test
    void testNonEmptyWorkoutIllegalMove(){
        JsonReader reader = new JsonReader("./data/testReaderNonEmptyWorkoutIllegalMove.json");
        Workout workout;
        try {
            workout = reader.read();
            fail("An unrealistic reps exception should have been caught");
        } catch (IOException e) {
            fail();
        } catch (UnrealisticRepsException e) {
            System.out.println("Encountered a move with an invalid number of reps!");
            //assertEquals(0, workout.getTracks().size());
            //assertEquals("Workout with illegal move in track", workout.getWorkoutTitle());
        }


    }

}
