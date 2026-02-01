package Tigger;

import java.util.Scanner;
import java.util.ArrayList;
public class Ui {

    private String currentCommand;
    Scanner scanner;

    public Ui(){
        this.scanner = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Tigger.Tigger");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

    }

    public void readCommand() {
        currentCommand = scanner.nextLine();

    }

    public boolean handleCommand(ArrayList<Task> list) {
        readCommand();
        if (currentCommand.equals("bye")) {
            return true;
        }

        try {
            Parser.execute(currentCommand, list);
        } catch (TiggerException e) {
            System.out.println("    ____________________________________________________________");
            System.out.println("    " + e);
            System.out.println("    ____________________________________________________________\n");
        }

        return false;
    }
}
