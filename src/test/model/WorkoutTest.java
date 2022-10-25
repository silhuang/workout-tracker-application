package model;

import exceptions.TitleAlreadyUsedException;
import exceptions.TrackNotFoundException;
import exceptions.UnrealisticRepsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class WorkoutTest {
    private Workout testWorkout;
    private Track track1;
    private Track track2;

    @BeforeEach
    public void runBefore() {
        testWorkout = new Workout("POP 23");
        track1 = new Track("Standing Warmup");
        track2 = new Track("Total Body Opener");
    }

    @Test
    public void testConstructor() {
        assertEquals("POP 23", testWorkout.getWorkoutTitle());
        assertTrue(testWorkout.getTracks().isEmpty());
    }

    @Test
    public void testAddTrackOnce() {
        ArrayList<Track> tracks = testWorkout.getTracks();

        try {
            testWorkout.addTrack(track1);
            assertEquals(1, tracks.size());
            assertEquals(track1, tracks.get(0));
        } catch (TitleAlreadyUsedException e) {
            fail();
        }
    }

    @Test
    public void testAddDifferentTracks() {
        try {
            testWorkout.addTrack(track1);
            testWorkout.addTrack(track2);
        } catch (TitleAlreadyUsedException e) {
            fail();
        }
        ArrayList<Track> tracks = testWorkout.getTracks();

        assertEquals(2, tracks.size());
        assertEquals(track1, tracks.get(0));
        assertEquals(track2, tracks.get(1));
    }

    @Test
    public void testAddSameTrackTwice() {
        try {
            testWorkout.addTrack(track1);
            testWorkout.addTrack(track1);
            fail("A TitleAlreadyUsedException was not thrown");
        } catch (TitleAlreadyUsedException e) {
            System.out.println("Sorry, the title you have entered is already taken. Please enter " +
                                "a different title:");
        }

        ArrayList<Track> tracks = testWorkout.getTracks();

        assertEquals(1, tracks.size());
        assertEquals(track1, tracks.get(0));
    }

    @Test
    public void testAddTrackWithSameName() {
        try {
            track2.addMove(new Move("high knees", 25));
        } catch (UnrealisticRepsException e) {
            fail();
        }

        try {
            testWorkout.addTrack(track2);
            testWorkout.addTrack(new Track(track2.getTrackTitle()));
            fail("A TitleAlreadyUsedException was not thrown");
        } catch (TitleAlreadyUsedException e) {
            System.out.println("Sorry, the title you have entered is already taken. Please enter " +
                                "a different title:");
        }

        ArrayList<Track> tracks = testWorkout.getTracks();

        assertEquals(1, tracks.size());
        assertEquals(track2, tracks.get(0));
    }

    @Test
    public void testDeleteOneTrack() {
        try {
            testWorkout.addTrack(track1);
        } catch (TitleAlreadyUsedException e) {
            fail();
        }

        try {
            testWorkout.deleteTrack(track1);
        } catch (TrackNotFoundException e) {
            fail();
        }
        ArrayList<Track> tracks = testWorkout.getTracks();
        assertEquals(0, tracks.size());
    }

    @Test
    public void testDeleteTrackNotThere() {
        try {
            testWorkout.deleteTrack(track2);
        } catch (TrackNotFoundException e) {
            System.out.println("Could not find a track with title " + track2.getTrackTitle());
        }
        ArrayList<Track> tracks = testWorkout.getTracks();
        assertEquals(0, tracks.size());
    }

    @Test
    public void deleteMultipleTracks() {
        ArrayList<Track> tracks = testWorkout.getTracks();
        try {
            testWorkout.addTrack(track1);
            testWorkout.addTrack(track2);
            assertEquals(2, tracks.size());
        } catch (TitleAlreadyUsedException e) {
            fail();
        }

        try {
            testWorkout.deleteTrack(track1);
            testWorkout.deleteTrack(track2);
            assertEquals(0, tracks.size());
        } catch (TrackNotFoundException e) {
            fail();
        }
    }

    @Test
    public void testIterator() {
        ArrayList<Track> trackList = testWorkout.getTracks();
        try {
            testWorkout.addTrack(track1);
            testWorkout.addTrack(track2);
        } catch (TitleAlreadyUsedException e) {
            fail();
        }

        ArrayList<Track> tracksToIterateOver = new ArrayList<>();
        tracksToIterateOver.add(track1);
        tracksToIterateOver.add(track2);

        Iterator<Track> tracksIterator = trackList.iterator();
        for (int i = 0; i < trackList.size(); i++) {
            Track track = tracksIterator.next();
            Track sameTrack = tracksToIterateOver.get(i);
            assertTrue(track.equals(sameTrack));
        }
    }
}


