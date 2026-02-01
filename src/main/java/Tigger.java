import java.util.ArrayList;

/**
 * Chatbot named Tigger that acts as a checklist.
 */
public class Tigger {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Tigger class.
     * @param path path to storage file
     */
    public Tigger(String path) {
        storage = new Storage(path);
        tasks = new TaskList(storage.getTaskList());
        ui = new Ui();
    }

    /**
     * Runs the Tigger chatbot.
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
     * Main method to start Tigger chatbot.
     * @param args arguments
     */
    public static void main(String[] args) {
       new Tigger("src/main/tigger.txt").run();
    }
}
