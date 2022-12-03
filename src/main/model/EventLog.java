package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class EventLog implements Iterable<Event> {
    private static EventLog theLog;
    private Collection<Event> events;

    // EFFECTS: constructs a single instance of an EventLog
    private EventLog() {
        events = new ArrayList<>();
    }

    // EFFECTS: returns the single EventLog instance
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }

    // MODIFIES: this
    // EFFECTS: logs the given event
    public void logEvent(Event e) {
        events.add(e);
    }

    // EFFECTS: prints description of every logged event to the console
    public void printLoggedEvents() {
        for (Event e : events) {
            System.out.println(e.getDescription());
        }
    }

    // EFFECTS: returns the collection of logged events
    public Collection<Event> getLoggedEvents() {
        return events;
    }

    // MODIFIES: this
    // EFFECTS: clear all logged events
    public void clearLog() {
        events.clear();
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
