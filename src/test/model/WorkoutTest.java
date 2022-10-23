package model;

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

        assertTrue(testWorkout.addTrack(track1));
        assertEquals(1, tracks.size());
        assertEquals(track1, tracks.get(0));
    }

    @Test
    public void testAddDifferentTracks() {
        boolean addOnce = testWorkout.addTrack(track1);
        boolean addTwice = testWorkout.addTrack(track2);
        ArrayList<Track> tracks = testWorkout.getTracks();

        assertTrue(addOnce);
        assertTrue(addTwice);

        assertEquals(2, tracks.size());
        assertEquals(track1, tracks.get(0));
        assertEquals(track2, tracks.get(1));
    }

    @Test
    public void testAddSameTrackTwice() {
        boolean addOnce = testWorkout.addTrack(track1);
        boolean addTwice = testWorkout.addTrack(track1);
        ArrayList<Track> tracks = testWorkout.getTracks();

        assertTrue(addOnce);
        assertFalse(addTwice);

        assertEquals(1, tracks.size());
        assertEquals(track1, tracks.get(0));
    }

    @Test
    public void testDeleteOneTrack() {
        testWorkout.addTrack(track1);

        boolean deleteOnce = testWorkout.deleteTrack(track1);
        ArrayList<Track> tracks = testWorkout.getTracks();

        assertTrue(deleteOnce);
        assertEquals(0, tracks.size());
    }

    @Test
    public void testDeleteTrackNotThere() {
        boolean delete = testWorkout.deleteTrack(track2);
        ArrayList<Track> tracks = testWorkout.getTracks();

        assertFalse(delete);
        assertEquals(0, tracks.size());
    }

    @Test
    public void deleteMultipleTracks() {
        testWorkout.addTrack(track1);
        testWorkout.addTrack(track2);

        boolean deleteOnce = testWorkout.deleteTrack(track1);
        boolean deleteTwice = testWorkout.deleteTrack(track2);
        ArrayList<Track> tracks = testWorkout.getTracks();

        assertTrue(deleteOnce);
        assertTrue(deleteTwice);
        assertEquals(0, tracks.size());
    }

    @Test
    public void testIterator() {
        ArrayList<Track> trackList = testWorkout.getTracks();
        testWorkout.addTrack(track1);
        testWorkout.addTrack(track2);

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


