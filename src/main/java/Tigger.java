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
            if (command.equals("list")){
                System.out.println("    ____________________________________________________________\n");
                System.out.println("    Here are the tasks in your list:\n");
                for (int i = 0; i < list.size(); i++){
                    Task current = list.get(i);
                    System.out.println("    " + (i + 1) + ". [" + current.getStatusIcon() + "] "
                            + current.getDescription());
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
                System.out.println("      [" + list.get(index - 1).getStatusIcon() + "] "
                        + list.get(index - 1).getDescription());
                System.out.println("    ____________________________________________________________\n");
            } else {
            Task t = new Task(command);
            list.add(t);
            System.out.println("    ____________________________________________________________\n");
            System.out.println("    added:" + command);
            System.out.println("    ____________________________________________________________\n");
            }
            command = scanner.nextLine();
        }
        System.out.println("    ____________________________________________________________\n");
        System.out.println("    Bye. Hope to see you again soon!\n");
        System.out.println("    ____________________________________________________________");
    }
}
