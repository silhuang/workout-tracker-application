package model;

import exceptions.InvalidNumberOfRepsException;
import exceptions.NegativeRepsException;
import exceptions.UnrealisticRepsException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {

    public Move testMove;

    @BeforeEach
    @Test
    public void testConstructorWithRealisticRepsAndRunBefore() {
        try {
            testMove = new Move("Plank Jacks", 30);
            assertEquals("Plank Jacks", testMove.getName());
            assertEquals(30, testMove.getReps());
        } catch (InvalidNumberOfRepsException e) {
            fail("An exception was incorrectly caught");
        }
    }

    @Test
    public void testConstructorWithUnrealisticReps() {
        Move testMove;
        try {
            testMove = new Move("Jumping Jacks", 1001);
            fail("An exception should have been caught");
        } catch (InvalidNumberOfRepsException e) {
            System.out.println("An invalid number of reps has been entered! Please try again:");
        }
    }

    @Test
    public void testConstructorWithNegativeReps() {
        Move testMove;
        try {
            testMove = new Move("Squats", -1);
            fail("An exception should have been caught");
        } catch (InvalidNumberOfRepsException e) {
            System.out.println("An invalid number of reps has been entered! Please try again:");
        }
    }

    @Test
    public void testSetToUnrealisticNumberOfReps() {
        try {
            testMove.changeReps(1001);
            fail("An InvalidNumberOfRepsException should have been caught");
        } catch (InvalidNumberOfRepsException e) {
            System.out.println("An invalid number of reps has been entered! Please try again:");
        }
    }

    @Test
    public void testSetToNegativeNumberOfReps() {
        try {
            testMove.changeReps(0);
            fail("An InvalidNumberOfRepsException should have been caught");
        } catch (InvalidNumberOfRepsException e) {
            System.out.println("An invalid number of reps has been entered! Please try again:");
        }
    }

    @Test
    public void testReduceReps() {
        try {
            assertTrue(testMove.changeReps(15));
            assertEquals(15, testMove.getReps());
        } catch (InvalidNumberOfRepsException e) {
            fail("An exception was incorrectly caught");
        }
    }

    @Test
    public void testIncreaseReps() {
        try {
            assertTrue(testMove.changeReps(50));
            assertEquals(50, testMove.getReps());
        } catch (InvalidNumberOfRepsException e) {
            fail("An exception was incorrectly caught");
        }

    }

    @Test
    public void testReduceToMinNumberOfReps() {
        try {
            assertTrue(testMove.changeReps(1));
            assertEquals(1, testMove.getReps());
        } catch (InvalidNumberOfRepsException e) {
            fail("An exception was incorrectly caught");
        }
    }

    @Test
    public void testRepsNotChanged() {
        try {
            assertFalse(testMove.changeReps(30));
            assertEquals(30, testMove.getReps());
        } catch (InvalidNumberOfRepsException e) {
            fail("An exception was incorrectly caught");
        }
    }

    @Test
    public void testToJson() {
        try {
            testMove = new Move("Plank Jacks", 30);
        } catch (UnrealisticRepsException e) {
            fail();
        }

        JSONObject testJsonMove = testMove.toJson();
        assertEquals("Plank Jacks", testJsonMove.get("name"));
        assertEquals(30,testJsonMove.get("reps"));
    }
}
