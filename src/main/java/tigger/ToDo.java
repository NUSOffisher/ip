package tigger;

/**
 * Represents a Tigger.ToDo task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task.
     * @param description description of the to-do task
     */
    public ToDo(String description) {
        super(description.trim());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
