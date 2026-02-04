package Tigger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Tigger.Event task.
 */
public class Event extends Task {

    protected String from;
    protected String to;
    protected LocalDate fromDate;
    protected LocalDate toDate;

    /**
     * Constructs an Event task.
     * @param description description of the event
     * @param from start date of the event
     * @param to end date of the event
     */
    public Event(String description, String from, String to) {
        super(description.trim());
        this.from = from;
        this.to = to;
        try {
            this.fromDate = LocalDate.parse(from);
        } catch (DateTimeParseException e) {
            this.fromDate = null;
        }
        try {
            this.toDate = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            this.toDate = null;
        }
    }

    @Override
    public String toString() {
        if (fromDate != null && toDate != null) {
            return "[E]" + super.toString() + " (from: "
                    + fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: "
                    + toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else if (fromDate != null) {
            return "[E]" + super.toString() + " (from: "
                    + fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + to + ")";
        } else if (toDate != null) {
            return "[E]" + super.toString() + " (from: " + from + " to: "
                    + toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
        }
    }
}
