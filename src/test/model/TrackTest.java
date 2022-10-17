package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrackTest {
    private Track testTrack;
    private Move firstMove;
    private Move secondMove;

    @BeforeEach
    public void runBefore() {
        testTrack = new Track("Standing Warmup");
        firstMove = new Move("Prep and Lift - SLOW (R)", 4);
        secondMove = new Move("Prep and Lift - FAST (R)", 8);
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

}