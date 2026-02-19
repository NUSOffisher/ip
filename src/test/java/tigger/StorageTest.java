package tigger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class StorageTest {

    @Test
    public void saveAndLoadTasks() throws Exception {
        Path temp = Files.createTempFile("tigger_test", ".txt");
        temp.toFile().deleteOnExit();
        String path = temp.toString();

        ArrayList<Task> tasks = new ArrayList<>();
        ToDo todo = new ToDo("test");
        todo.setDone();
        Deadline deadline = new Deadline("deadline", "2025-01-01");
        deadline.setDone();
        Event event = new Event("event", "2025-02-01", "2025-02-02");
        tasks.add(todo);
        tasks.add(deadline);
        tasks.add(event);

        Storage writer = new Storage(path);
        writer.saveTasks(tasks);

        List<String> lines = Files.readAllLines(temp);
        allTest(lines);

        Storage loader = new Storage(path);
        ArrayList<Task> loaded = loader.getTaskList();
        assertEquals(3, loaded.size());

        todoTest(loaded);
        deadlineTest(loaded);
        eventTest(loaded);
    }

    public void allTest(List<String> lines) {
        assertEquals("T | 1 | test", lines.get(0));
        assertEquals("D | 1 | deadline | 2025-01-01", lines.get(1));
        assertEquals("E | 0 | event | 2025-02-01 | 2025-02-02", lines.get(2));
    }

    public void todoTest(ArrayList<Task> loaded) {
        Task t0 = loaded.get(0);
        assertInstanceOf(ToDo.class, t0);
        ToDo lt = (ToDo) t0;
        assertEquals("test", lt.getDescription());
        assertTrue(lt.isDone);
    }

    public void deadlineTest(ArrayList<Task> loaded) {
        Task t1 = loaded.get(1);
        assertInstanceOf(Deadline.class, t1);
        Deadline ld = (Deadline) t1;
        assertEquals("deadline", ld.getDescription());
        assertTrue(ld.isDone);
        assertEquals("2025-01-01", ld.by);
    }

    public void eventTest(ArrayList<Task> loaded) {
        Task t2 = loaded.get(2);
        assertInstanceOf(Event.class, t2);
        Event le = (Event) t2;
        assertEquals("event", le.getDescription());
        assertFalse(le.isDone);
        assertEquals("2025-02-01", le.from);
        assertEquals("2025-02-02", le.to);
    }
}
