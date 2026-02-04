package Tigger;

import java.util.ArrayList;

/**
 * Public class for loading and saving tasks to tigger.txt.
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Constructor for Tigger.TaskList class.
     * @param taskList list of tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }


    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

}
