package persistence;

import exceptions.UnrealisticRepsException;
import model.Move;
import model.Track;
import model.Workout;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterEmptyWorkout() {
        Workout workout = new Workout("Workout without tracks");
        JsonWriter wr = new JsonWriter("./data/testWriterEmptyWorkout");
        try {
            wr.open();
            wr.write(workout);
            wr.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkout");
            workout = reader.read();
            assertEquals("Workout without tracks", workout.getWorkoutTitle());
            assertEquals(0, workout.getTracks().size());
        } catch (IOException e) {
            fail();
        } catch (UnrealisticRepsException e) {
            fail();
        }
    }


    @Test
    void testWriterWorkoutUnrealisticRepsException() {
        Workout workout = new Workout("Workout with tracks");
        workout.addTrack(new Track("Empty Track"));
        Track track2 = new Track("Track with a move");
        try {
            track2.addMove(new Move("plank jacks", -5));
        } catch (UnrealisticRepsException e) {
            System.out.println("A move with an unrealistic number of reps was encountered!");
        }

        JsonWriter wr = new JsonWriter("./data/testWriterNonEmptyWorkoutIllegalMove.json");
        try {
            wr.open();
            wr.write(workout);
            wr.close();
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void testWriterWorkoutNoExceptionsExpected() {
        Workout workout = new Workout("Workout with tracks");
        workout.addTrack(new Track("Empty Track"));
        Track track2 = new Track("Track with a move");
        try {
            track2.addMove(new Move("plank jacks", 10));
        } catch (UnrealisticRepsException e) {
            fail("Each move has a valid number of reps");
        }

        JsonWriter wr = new JsonWriter("./data/testWriterNonEmptyWorkout.json");
        try {
            wr.open();
            wr.write(workout);
            wr.close();
        } catch (IOException e) {
            fail();
        }
    }

}


