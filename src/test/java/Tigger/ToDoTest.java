package Tigger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {

    @Test
    public void setDoneAndDescriptionBehaviors() {
        ToDo t = new ToDo("  my task  ");

        assertFalse(t.isDone, "New ToDo should be not done by default");
        assertEquals("my task", t.getDescription(), "Description should be trimmed");
        t.setDone();
        assertTrue(t.isDone, "ToDo should be marked done after setDone");
        t.setDone();
        assertTrue(t.isDone, "setDone should remain true when called again");
        assertEquals("my task", t.getDescription());
    }
}
