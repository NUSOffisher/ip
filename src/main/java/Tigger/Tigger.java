package Tigger;

import java.util.ArrayList;

/**
 * Chatbot named Tigger.Tigger that acts as a checklist.
 */
public class Tigger {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Tigger.Tigger class.
     * @param path path to storage file
     */
    public Tigger(String path) {
        storage = new Storage(path);
        tasks = new TaskList(storage.getTaskList());
        ui = new Ui();
    }

    /**
     * Runs the Tigger.Tigger chatbot.
     */
    public void run() {
        ui.showWelcomeMessage();
        ArrayList<Task> list = tasks.getTaskList();
        boolean isExit;

        do {
            isExit = ui.handleCommand(list);
        } while (!isExit);
        storage.saveTasks(list);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Main method to start Tigger.Tigger chatbot.
     * @param args arguments
     */
    public static void main(String[] args) {
        new Tigger("src/main/java/Tigger/tigger.txt").run();
    }

    public String getResponse(String input) {
        return "Tigger heard: " + input;
    }
}
