package model;

import java.util.Objects;

public class Event {
    private String description;

    // MODIFIES: this
    // EFFECTS: creates a new event with the following description
    public Event(String description) {
        this.description = description;
    }

    // EFFECTS: returns the description of the event
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        return description.equals(event.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
