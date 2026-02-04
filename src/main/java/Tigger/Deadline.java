package Tigger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Tigger.Deadline task.
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDate byDate;

    /**
     * Constructs a Deadline task.
     *
     * @param description Description of the deadline task.
     * @param by          Deadline date as a string.
     */
    public Deadline(String description, String by) {
        super(description.trim());
        this.by = by;
        try {
            this.byDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            this.byDate = null;
        }
    }

    @Override
    public String toString() {
        if (byDate == null) {
            return "[D]" + super.toString() + " (by: " + by + ")";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }
}