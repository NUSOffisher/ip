import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Tigger {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n");
        System.out.println("Hello! I'm Tigger\n");
        System.out.println("What can I do for you?\n");
        System.out.println("____________________________________________________________\n");

        ArrayList<Task> list = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!Objects.equals(command, "bye")) {
            try {
                if (command.equals("list")) {
                    System.out.println("    ____________________________________________________________\n");
                    System.out.println("    Here are the tasks in your list:\n");
                    for (int i = 0; i < list.size(); i++) {
                        Task current = list.get(i);
                        System.out.println("    " + (i + 1) + ". " + current.toString());
                    }
                    System.out.println("    ____________________________________________________________\n");
                } else if (command.startsWith("mark") || command.startsWith("unmark")) {
                    String[] splitString = command.split("\\s");
                    int index = Integer.parseInt(splitString[1]);
                    System.out.println("    ____________________________________________________________\n");
                    if (command.startsWith("mark")) {
                        list.get(index - 1).setDone();
                        System.out.println("    Nice! I've marked this task as done: \n");
                    } else {
                        list.get(index - 1).setNotDone();
                        System.out.println("    OK, I've marked this task as not done yet: \n");
                    }
                    System.out.println("        " + list.get(index - 1).toString());
                    System.out.println("    ____________________________________________________________\n");
                } else if (command.startsWith("todo")) {
                    if (command.length() <= 5) {
                        throw new TiggerException("What do you want me todo??");
                    }
                    System.out.println("    ____________________________________________________________\n");
                    System.out.println("    Got it. I've added this task:\n");
                    ToDo t = new ToDo(command.substring(5));
                    list.add(t);
                    System.out.println("    " + t + "\n");
                    System.out.println("    Now you have " + list.size() + " tasks in the list\n");
                    System.out.println("    ____________________________________________________________\n");
                } else if (command.startsWith("deadline")) {
                    if (command.length() <= 9) {
                        throw new TiggerException("What's the deadline for??");
                    }
                    System.out.println("    ____________________________________________________________\n");
                    System.out.println("    Got it. I've added this task:\n");
                    String fullCommand = command.substring(9);
                    String[] splitCommand = fullCommand.split("[/]");
                    splitCommand[1] = splitCommand[1].substring(3);
                    Deadline d = new Deadline(splitCommand[0], splitCommand[1]);
                    list.add(d);
                    System.out.println("    " + d + "\n");
                    System.out.println("    Now you have " + list.size() + " tasks in the list\n");
                    System.out.println("    ____________________________________________________________\n");
                } else if (command.startsWith("event")) {
                    if (command.length() <= 6) {
                        throw new TiggerException("Whats the new event??");
                    }
                    System.out.println("    ____________________________________________________________\n");
                    System.out.println("    Got it. I've added this task:\n");
                    String fullCommand = command.substring(6);
                    String[] splitCommand = fullCommand.split("[/]");
                    splitCommand[1] = splitCommand[1].substring(5);
                    splitCommand[2] = splitCommand[2].substring(3);
                    Event d = new Event(splitCommand[0], splitCommand[1], splitCommand[2]);
                    list.add(d);
                    System.out.println("    " + d + "\n");
                    System.out.println("    Now you have " + list.size() + " tasks in the list\n");
                    System.out.println("    ____________________________________________________________\n");
                } else {
                    throw new TiggerException("Give me something I can understand!!");
                }
            } catch (TiggerException e){
                System.out.println("    ____________________________________________________________\n");
                System.out.println("    " + e);
                System.out.println("    ____________________________________________________________\n");
            }
            command = scanner.nextLine();
        }
        System.out.println("    ____________________________________________________________\n");
        System.out.println("    Bye. Hope to see you again soon!\n");
        System.out.println("    ____________________________________________________________");
    }
}
