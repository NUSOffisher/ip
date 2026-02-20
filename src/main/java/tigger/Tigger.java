package tigger;

/**
 * Chatbot named Tigger.Tigger that acts as a checklist.
 */
public class Tigger {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for Tigger.Tigger class.
     * @param path path to storage file
     */
    public Tigger(String path) {
        storage = new Storage(path);
        tasks = new TaskList(storage.getTaskList());
    }

    public String getWelcomeMessage() {
        if (storage != null && storage.hasLoadError()) {
            String msg = storage.getLoadErrorMessage();
            return msg + "\n";
        }
        return """
                    _____________________________________
                    Hello! I'm Tigger
                    What can I do for you?
                    _____________________________________\
                """;
    }
    public TaskList getTaskList() {
        return tasks;
    }

    /** Returns the Storage instance used by Tigger */
    public Storage getStorage() {
        return storage;
    }
}
