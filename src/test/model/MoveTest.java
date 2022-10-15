package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {

    public Move testMove;

    @BeforeEach
    public void runBefore() {
        testMove = new Move("Plank Jacks", 30);
    }

    @Test
    public void testConstructor() {
        assertEquals("Plank Jacks", testMove.getName());
        assertEquals(30, testMove.getReps());
    }

    @Test
    public void testReduceReps() {
        assertTrue(testMove.changeReps(15));
        assertEquals(15, testMove.getReps());
    }

    @Test
    public void testIncreaseReps() {
        assertTrue(testMove.changeReps(50));
        assertEquals(50, testMove.getReps());
    }

    @Test
    public void testReduceToMinNumberOfReps() {
        assertTrue(testMove.changeReps(1));
        assertEquals(1, testMove.getReps());
    }

    @Test
    public void testRepsNotChanged() {
        assertFalse(testMove.changeReps(30));
        assertEquals(30, testMove.getReps());
    }

}
