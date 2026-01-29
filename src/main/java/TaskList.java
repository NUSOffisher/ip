import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Public class for loading and saving tasks to tigger.txt.
 */
public class TaskList {
    String path;
    File savedTasks;
    ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(String path) {
        this.path = path;
        this.savedTasks = new File(path);

        try (Scanner reader = new Scanner(savedTasks)) {
            while (reader.hasNextLine()) {
                String task = reader.nextLine();
                if (task.startsWith("T")) { // New ToDo
                    String[] subCommand = task.split("[|]");
                    ToDo todo = new ToDo(subCommand[2].trim());
                    if (subCommand[1].trim().equals("1")) {
                        todo.setDone();
                    }
                    taskList.add(todo);
                } else if (task.startsWith("D")) { // New Deadline
                    String[] subCommand = task.split("[|]");
                    Deadline deadline = new Deadline(subCommand[2].trim(), subCommand[3].trim());
                    if (subCommand[1].trim().equals("1")) {
                        deadline.setDone();
                    }
                    taskList.add(deadline);
                } else if (task.startsWith("E")) { // New Event
                    String[] subCommand = task.split("[|]");
                    Event event = new Event(subCommand[2].trim(), subCommand[3].trim(), subCommand[4].trim());
                    if (subCommand[1].trim().equals("1")) {
                        event.setDone();
                    }
                    taskList.add(event);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Saved file not found");
        }
    }

    /**
     * Adds a new ToDo to tigger.txt.
     * @param command description of ToDo
     */
    public void addTask(String command) {
        try (FileWriter myWriter = new FileWriter(path, true)) {
            myWriter.write("\nT | 0 | " + command);
        } catch (IOException e) {
            System.out.println("An error occurred");
        }
    }

    /**
     * Adds a new Deadline to tigger.txt.
     * @param command Description of Deadline
     * @param by Do by date/time
     */
    public void addTask(String command, String by) {
        try (FileWriter myWriter = new FileWriter(path, true)) {
            myWriter.write("\nD | 0 | " + command + "| " + by);
        } catch (IOException e) {
            System.out.println("An error occurred");
        }
    }

    /**
     * Adds a new Event to tigger.txt.
     * @param command Description of Event
     * @param from Event start date/time
     * @param to Event end date/time
     */
    public void addTask(String command, String from, String to) {
        try (FileWriter myWriter = new FileWriter(path, true)) {
            myWriter.write("\nE | 0 | " + command + "| " + from + " | " + to);
        } catch (IOException e) {
            System.out.println("An error occurred");
        }
    }

    /**
     * Replaces the current list of tasks in tigger.txt with an updated list.
     * @param list list of tasks
     */
    public void replaceList(ArrayList<Task> list) {
        int len = list.size();
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                try (FileWriter myWriter = new FileWriter(path)) {
                    Task t = list.get(i);
                    if (t instanceof ToDo) {
                        ToDo todo = (ToDo) t;
                        if (todo.isDone) {
                            myWriter.write("T | 1 | " + todo.getDescription().trim());
                        } else {
                            myWriter.write("T | 0 | " + todo.getDescription().trim());
                        }
                    } else if (t instanceof Deadline) {
                        Deadline deadline = (Deadline) t;
                        if (deadline.isDone) {
                            myWriter.write("D | 1 | " + deadline.getDescription().trim() + "| " + deadline.by.trim());
                        } else {
                            myWriter.write("D | 0 | " + deadline.getDescription().trim() + "| " + deadline.by.trim());
                        }
                    } else if (t instanceof Event) {
                        Event event = (Event) t;
                        if (event.isDone) {
                            myWriter.write("E | 1 | " + event.getDescription().trim() + "| " + event.from.trim() + " | " + event.to.trim());
                        } else {
                            myWriter.write("E | 0 | " + event.getDescription().trim() + "| " + event.from.trim() + " | " + event.to.trim());
                        }
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred");
                }
            } else {
                try (FileWriter myWriter = new FileWriter(path, true)) {
                    Task t = list.get(i);
                    if (t instanceof ToDo) {
                        ToDo todo = (ToDo) t;
                        if (todo.isDone) {
                            myWriter.write("\nT | 1 | " + todo.getDescription().trim());
                        } else {
                            myWriter.write("\nT | 0 | " + todo.getDescription().trim());
                        }
                    } else if (t instanceof Deadline) {
                        Deadline deadline = (Deadline) t;
                        if (deadline.isDone) {
                            myWriter.write("\nD | 1 | " + deadline.getDescription().trim() + "| " + deadline.by.trim());
                        } else {
                            myWriter.write("\nD | 0 | " + deadline.getDescription().trim() + "| " + deadline.by.trim());
                        }
                    } else if (t instanceof Event) {
                        Event event = (Event) t;
                        if (event.isDone) {
                            myWriter.write("\nE | 1 | " + event.getDescription().trim() + "| " + event.from.trim() + " | " + event.to.trim());
                        } else {
                            myWriter.write("\nE | 0 | " + event.getDescription().trim() + "| " + event.from.trim() + " | " + event.to.trim());
                        }
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred");
                }
            }
        }
    }

    public ArrayList<Task> getTaskList () {
            return this.taskList;
    }
}
