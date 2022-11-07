package model;

import exceptions.UnrealisticRepsException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class TrackTest {
    private Track testTrack;
    private Move firstMove;
    private Move secondMove;

    @BeforeEach
    public void runBefore() {
        testTrack = new Track("Standing Warmup");
        try {
            firstMove = new Move("Prep and Lift - SLOW (R)", 4);
            secondMove = new Move("Prep and Lift - FAST (R)", 8);
        } catch (UnrealisticRepsException e) {
            fail();
        }
    }

    @Test
    public void testConstructor() {
        assertEquals("Standing Warmup", testTrack.getTrackTitle());
        assertEquals(0, testTrack.getMoves().size());
        assertTrue(testTrack.getMoves().isEmpty());
    }

    @Test
    public void testAddOneMove() {
        testTrack.addMove(firstMove);
        assertEquals(1, testTrack.getMoves().size());
        assertEquals(firstMove, testTrack.getMoves().get(0));
    }

    @Test
    public void testAddTwoMoves() {
        testTrack.addMove(firstMove);
        assertEquals(1, testTrack.getMoves().size());
        testTrack.addMove(secondMove);
        assertEquals(2, testTrack.getMoves().size());
        assertEquals(firstMove, testTrack.getMoves().get(0));
        assertEquals(secondMove, testTrack.getMoves().get(1));
    }

    @Test
    public void testAddSameMoveTwice() {
        testTrack.addMove(firstMove);
        assertEquals(1, testTrack.getMoves().size());
        assertEquals(firstMove, testTrack.getMoves().get(0));

        testTrack.addMove(firstMove);
        assertEquals(2, testTrack.getMoves().size());
        assertEquals(firstMove, testTrack.getMoves().get(0));
    }

    @Test
    public void testDeleteMove() {
        testTrack.addMove(firstMove);

        assertTrue(testTrack.deleteMove(firstMove));
        assertEquals(0, testTrack.getMoves().size());
    }

    @Test
    public void testDeleteTwoMoves() {
        testTrack.addMove(firstMove);
        testTrack.addMove(secondMove);

        assertTrue(testTrack.deleteMove(firstMove));
        assertEquals(1, testTrack.getMoves().size());
        assertTrue(testTrack.deleteMove(secondMove));
        assertEquals(0, testTrack.getMoves().size());
    }

    @Test
    public void removeNonExistentMove() {
        assertFalse(testTrack.deleteMove(firstMove));
        assertEquals(0, testTrack.getMoves().size());
    }

    @Test
    public void testMovesIterator() {
        testTrack.addMove(firstMove);
        testTrack.addMove(secondMove);

        ArrayList<Move> copyOfMoves = new ArrayList<>();
        copyOfMoves.add(firstMove);
        copyOfMoves.add(secondMove);

        int i = 0;
        for (Move m : testTrack) {
            assertEquals(m, copyOfMoves.get(i));
            i++;
        }
    }

    @Test
    public void testToJson() {
        testTrack.addMove(firstMove);
        testTrack.addMove(secondMove);

        JSONObject jsonTrack = testTrack.toJson();
        JSONArray jsonMoves = (JSONArray) jsonTrack.get("moves");

        assertEquals("Standing Warmup", jsonTrack.get("trackTitle"));
        assertEquals(2, jsonMoves.length());

        JSONObject firstJsonMove = firstMove.toJson();
        String firstMoveName = firstJsonMove.getString("name");
        int firstMoveReps = firstJsonMove.getInt("reps");
        JSONObject secondJsonMove = secondMove.toJson();
        String secondMoveName = secondJsonMove.getString("name");
        int secondMoveReps = secondJsonMove.getInt("reps");

        assertEquals("Prep and Lift - SLOW (R)", firstMoveName);
        assertEquals(4, firstMoveReps);

        assertEquals("Prep and Lift - FAST (R)", secondMoveName);
        assertEquals(8, secondMoveReps);

    }

    @Test
    public void testToJsonTrackWithNoMoves() {
        JSONArray jsonMoves = testTrack.movesToJson();
        assertTrue(jsonMoves.isEmpty());
        assertEquals(0, jsonMoves.length());
    }

    @Test
    public void testMovesToJson() {
        testTrack.addMove(firstMove);
        testTrack.addMove(secondMove);

        JSONArray jsonMoves = testTrack.movesToJson();
        assertEquals(2, jsonMoves.length());

        JSONObject firstJsonMove = (JSONObject) jsonMoves.get(0);
        JSONObject secondJsonMove = (JSONObject) jsonMoves.get(1);

        assertEquals("Prep and Lift - SLOW (R)", firstJsonMove.get("name"));
        assertEquals(4, firstJsonMove.get("reps"));

        assertEquals("Prep and Lift - FAST (R)", secondJsonMove.get("name"));
        assertEquals(8, secondJsonMove.get("reps"));

    }

}
