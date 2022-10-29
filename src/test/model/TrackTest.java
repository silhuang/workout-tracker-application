package model;

import exceptions.UnrealisticRepsException;
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

}
