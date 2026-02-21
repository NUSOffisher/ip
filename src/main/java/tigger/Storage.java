package tigger;

import java.io.File;
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

    private boolean failedToLoad = false;
    private String loadErrorMessage = null;

    /**
     * Constructor for Tigger.Storage class.
     * Used ChatGPT to help with file handling and error handling.
     * @param path path to storage file
     */
    public Storage(String path) {
        this.path = path;
        this.savedTasks = new File(path);

        try {
            File parent = savedTasks.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            if (!savedTasks.exists()) {
                savedTasks.createNewFile();
            }

            // Now load tasks
            try (Scanner reader = new Scanner(savedTasks)) {
                while (reader.hasNextLine()) {
                    String task = reader.nextLine();

                    if (task.startsWith("T")) {
                        String[] subCommand = task.split("[|]");
                        ToDo todo = new ToDo(subCommand[2].trim());
                        if (subCommand[1].trim().equals("1")) {
                            todo.setDone();
                        }
                        taskList.add(todo);

                    } else if (task.startsWith("D")) {
                        String[] subCommand = task.split("[|]");
                        Deadline deadline = new Deadline(
                                subCommand[2].trim(),
                                subCommand[3].trim()
                        );
                        if (subCommand[1].trim().equals("1")) {
                            deadline.setDone();
                        }
                        taskList.add(deadline);

                    } else if (task.startsWith("E")) {
                        String[] subCommand = task.split("[|]");
                        Event event = new Event(
                                subCommand[2].trim(),
                                subCommand[3].trim(),
                                subCommand[4].trim()
                        );
                        if (subCommand[1].trim().equals("1")) {
                            event.setDone();
                        }
                        taskList.add(event);
                    }
                }
            }

        } catch (IOException e) {
            this.failedToLoad = true;
            this.loadErrorMessage = "Error initializing storage file.";
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
     * Returns true if loading the saved file failed
     */
    public boolean hasLoadError() {
        return this.failedToLoad;
    }

    /**
     * Returns the load error message if present
     */
    public String getLoadErrorMessage() {
        return this.loadErrorMessage;
    }

    /**
     * Saves the tasks to the storage file.
     * @param tasks list of tasks to be saved
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try (FileWriter myWriter = new FileWriter(path)) {
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);

                if (i > 0) {
                    myWriter.write("\n");
                }
                if (t instanceof ToDo todo) {
                    myWriter.write("T | " + (todo.isDone ? "1" : "0")
                            + " | " + todo.getDescription().trim());

                } else if (t instanceof Deadline deadline) {
                    myWriter.write("D | " + (deadline.isDone ? "1" : "0")
                            + " | " + deadline.getDescription().trim()
                            + " | " + deadline.by.trim());

                } else if (t instanceof Event event) {
                    myWriter.write("E | " + (event.isDone ? "1" : "0")
                            + " | " + event.getDescription().trim()
                            + " | " + event.from.trim()
                            + " | " + event.to.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred");
        }
    }
}
