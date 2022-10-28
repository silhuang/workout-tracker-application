package persistence;

import model.Move;
import model.Track;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonTest {
    protected void checkMove(String name, int reps, Move move) {
        assertEquals(name, move.getName());
        assertEquals(reps, move.getReps());
    }

    protected void checkTrack(String title, ArrayList<Move> moves, Track track) {
        assertEquals(title, track.getTrackTitle());
        Iterator<Move> iterator = track.iterator();
        for (Move m : moves) {
            Move actualMove = iterator.next();
            assertTrue(m.equals(actualMove));
        }
    }
}
