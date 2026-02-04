package Tigger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to handle storage of tasks to a file.
 */
public class Storage {

    private String path;
    private File savedTasks;
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Constructor for Tigger.Storage class.
     * @param path path to storage file
     */
    public Storage(String path) {
        this.path = path;
        this.savedTasks = new File(path);

        try (Scanner reader = new Scanner(savedTasks)) {
            while (reader.hasNextLine()) {
                String task = reader.nextLine();
                if (task.startsWith("T")) { // New Tigger.ToDo
                    String[] subCommand = task.split("[|]");
                    ToDo todo = new ToDo(subCommand[2].trim());
                    if (subCommand[1].trim().equals("1")) {
                        todo.setDone();
                    }
                    taskList.add(todo);
                } else if (task.startsWith("D")) { // New Tigger.Deadline
                    String[] subCommand = task.split("[|]");
                    Deadline deadline = new Deadline(subCommand[2].trim(), subCommand[3].trim());
                    if (subCommand[1].trim().equals("1")) {
                        deadline.setDone();
                    }
                    taskList.add(deadline);
                } else if (task.startsWith("E")) { // New Tigger.Event
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
     * Returns the list of tasks
     * @return list of tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Saves the tasks to the storage file.
     * @param tasks list of tasks to be saved
     */
    public void saveTasks(ArrayList<Task> tasks) {
        int len = tasks.size();
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                try (FileWriter myWriter = new FileWriter(path)) {
                    Task t = tasks.get(i);
                    if (t instanceof ToDo todo) {
                        if (todo.isDone) {
                            myWriter.write("T | 1 | " + todo.getDescription().trim());
                        } else {
                            myWriter.write("T | 0 | " + todo.getDescription().trim());
                        }
                    } else if (t instanceof Deadline deadline) {
                        if (deadline.isDone) {
                            myWriter.write("D | 1 | " + deadline.getDescription().trim()
                                    + "| " + deadline.by.trim());
                        } else {
                            myWriter.write("D | 0 | " + deadline.getDescription().trim()
                                    + "| " + deadline.by.trim());
                        }
                    } else if (t instanceof Event event) {
                        if (event.isDone) {
                            myWriter.write("E | 1 | " + event.getDescription().trim()
                                    + "| " + event.from.trim() + " | " + event.to.trim());
                        } else {
                            myWriter.write("E | 0 | " + event.getDescription().trim()
                                    + "| " + event.from.trim() + " | " + event.to.trim());
                        }
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred");
                }
            } else {
                try (FileWriter myWriter = new FileWriter(path, true)) {
                    Task t = tasks.get(i);
                    if (t instanceof ToDo todo) {
                        if (todo.isDone) {
                            myWriter.write("\nT | 1 | " + todo.getDescription().trim());
                        } else {
                            myWriter.write("\nT | 0 | " + todo.getDescription().trim());
                        }
                    } else if (t instanceof Deadline deadline) {
                        if (deadline.isDone) {
                            myWriter.write("\nD | 1 | " + deadline.getDescription().trim()
                                    + " | " + deadline.by.trim());
                        } else {
                            myWriter.write("\nD | 0 | " + deadline.getDescription().trim()
                                    + " | " + deadline.by.trim());
                        }
                    } else if (t instanceof Event event) {
                        if (event.isDone) {
                            myWriter.write("\nE | 1 | " + event.getDescription().trim()
                                    + " | " + event.from.trim() + " | " + event.to.trim());
                        } else {
                            myWriter.write("\nE | 0 | " + event.getDescription().trim()
                                    + " | " + event.from.trim() + " | " + event.to.trim());
                        }
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred");
                }
            }
        }
    }
}
