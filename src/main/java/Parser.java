import java.util.ArrayList;

public class Parser {

    private Parser() {
    }

    public static void execute(String command, ArrayList<Task> list) throws TiggerException {
        if (command == null) {
            return;
        }

        if (command.equals("list")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                Task current = list.get(i);
                System.out.println("    " + (i + 1) + ". " + current.toString());
            }
            System.out.println("    ____________________________________________________________");
        } else if (command.startsWith("mark") || command.startsWith("unmark")) {
            String[] splitString = command.split("\\s");
            int index = Integer.parseInt(splitString[1]);
            System.out.println("    ____________________________________________________________");
            if (command.startsWith("mark")) {
                list.get(index - 1).setDone();
                System.out.println("    Nice! I've marked this task as done: ");
            } else {
                list.get(index - 1).setNotDone();
                System.out.println("    OK, I've marked this task as not done yet: ");
            }
            System.out.println("        " + list.get(index - 1).toString());
            System.out.println("    ____________________________________________________________");
        } else if (command.startsWith("todo")) {
            if (command.length() <= 5) {
                throw new TiggerException("What do you want me todo??");
            }
            System.out.println("    ____________________________________________________________");
            System.out.println("    Got it. I've added this task:");
            ToDo t = new ToDo(command.substring(5));
            list.add(t);
            System.out.println("    " + t);
            System.out.println("    Now you have " + list.size() + " tasks in the list");
            System.out.println("    ____________________________________________________________\n");
        } else if (command.startsWith("deadline")) {
            if (command.length() <= 9) {
                throw new TiggerException("What's the deadline for??");
            }
            System.out.println("    ____________________________________________________________");
            System.out.println("    Got it. I've added this task:");
            String fullCommand = command.substring(9);
            String[] splitCommand = fullCommand.split("[/]");
            splitCommand[1] = splitCommand[1].substring(3);
            Deadline d = new Deadline(splitCommand[0], splitCommand[1]);
            list.add(d);
            System.out.println("    " + d + "\n");
            System.out.println("    Now you have " + list.size() + " tasks in the list");
            System.out.println("    ____________________________________________________________\n");
        } else if (command.startsWith("event")) {
            if (command.length() <= 6) {
                throw new TiggerException("Whats the new event??");
            }
            System.out.println("    ____________________________________________________________");
            System.out.println("    Got it. I've added this task:");
            String fullCommand = command.substring(6);
            String[] splitCommand = fullCommand.split("[/]");
            splitCommand[1] = splitCommand[1].substring(5);
            splitCommand[2] = splitCommand[2].substring(3);
            Event d = new Event(splitCommand[0], splitCommand[1].trim(), splitCommand[2]);
            list.add(d);
            System.out.println("    " + d + "\n");
            System.out.println("    Now you have " + list.size() + " tasks in the list");
            System.out.println("    ____________________________________________________________\n");
        } else if (command.startsWith("delete")) {
            if (command.length() <= 7) {
                throw new TiggerException("Whats do you want me to delete??");
            }
            System.out.println("    ____________________________________________________________");
            System.out.println("    Noted. I've removed this task:");
            int index = Integer.parseInt(command.substring(7)) - 1;
            Task t = list.get(index);
            System.out.println("    " + t + "\n");
            list.remove(index);
            System.out.println("    Now you have " + list.size() + " tasks in the list");
            System.out.println("    ____________________________________________________________\n");
        } else {
            throw new TiggerException("Give me something I can understand!!");
        }
    }
}
