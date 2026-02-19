package tigger;

import java.util.Scanner;

/**
 * Handles user interaction for Tigger chatbot.
 */
public class Ui {

    private String currentCommand;
    private Scanner scanner;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcomeMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Tigger");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");

    }

    /**
     * Reads the next command from the user.
     */
    public void readCommand() {
        currentCommand = scanner.nextLine();

    }

}
