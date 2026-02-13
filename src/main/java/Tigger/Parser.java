package Tigger;

import java.util.ArrayList;

/**
 * Parser class to handle user commands and manipulate the task list.
 */
public class Parser {

    /**
     * Executes the command
     *
     * @param command The user command to execute.
     * @param list    The list of tasks to manipulate.
     * @throws TiggerException If the command is invalid or cannot be processed.
     */
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
                throw new TiggerException("What do you want me to delete??");
            }
            System.out.println("    ____________________________________________________________");
            System.out.println("    Noted. I've removed this task:");
            int index = Integer.parseInt(command.substring(7)) - 1;
            Task t = list.get(index);
            System.out.println("    " + t + "\n");
            list.remove(index);
            System.out.println("    Now you have " + list.size() + " tasks in the list");
            System.out.println("    ____________________________________________________________\n");
        } else if (command.startsWith("find")) {
            if (command.length() <= 5) {
                throw new TiggerException("What do you want me to find??");
            }
            System.out.println("    ____________________________________________________________");
            String itemToFind = command.substring(5);
            ArrayList<Task> foundTasks = new ArrayList<>();
            for (Task task : list) {
                if (task.getDescription().contains(itemToFind)) {
                    foundTasks.add(task);
                }
            }
            if (!foundTasks.isEmpty()) {
                System.out.println("    Here are the matching tasks in your list:");
                for (int i = 0; i < foundTasks.size(); i++) {
                    System.out.println("    " + (i + 1) + "." + foundTasks.get(i).toString());
                }
            } else {
                System.out.println("    No matching tasks found in your list.");
            }
            System.out.println("    ____________________________________________________________\n");
        } else {
            throw new TiggerException("Give me something I can understand!!");
        }
    }

    public static String getResponse(String command, ArrayList<Task> list) throws TiggerException {
        if (command == null) {
            return "";
        }

        StringBuilder out = new StringBuilder();

        if (command.equals("list")) {
            out.append("    ____________________________________________________________\n");
            out.append("    Here are the tasks in your list:\n");
            for (int i = 0; i < list.size(); i++) {
                Task current = list.get(i);
                out.append("    ").append(i + 1).append(". ").append(current.toString()).append("\n");
            }
            out.append("    ____________________________________________________________\n");
        } else if (command.startsWith("mark") || command.startsWith("unmark")) {
            String[] splitString = command.split("\\s");
            int index = Integer.parseInt(splitString[1]);
            out.append("    ____________________________________________________________\n");
            if (command.startsWith("mark")) {
                list.get(index - 1).setDone();
                assert list.get(index - 1).getStatusIcon().equals("X") : "Task should be marked as done.";
                out.append("    Nice! I've marked this task as done: \n");
            } else {
                list.get(index - 1).setNotDone();
                assert list.get(index - 1).getStatusIcon().equals(" ") : "Task should be marked as undone.";
                out.append("    OK, I've marked this task as not done yet: \n");
            }
            out.append("        ").append(list.get(index - 1).toString()).append("\n");
            out.append("    ____________________________________________________________\n");
        } else if (command.startsWith("todo")) {
            if (command.length() <= 5) {
                throw new TiggerException("What do you want me todo??");
            }
            out.append("    ____________________________________________________________\n");
            out.append("    Got it. I've added this task:\n");
            ToDo t = new ToDo(command.substring(5));
            list.add(t);
            out.append("    ").append(t).append("\n");
            out.append("    Now you have ").append(list.size()).append(" tasks in the list\n");
            out.append("    ____________________________________________________________\n");
        } else if (command.startsWith("deadline")) {
            if (command.length() <= 9) {
                throw new TiggerException("What's the deadline for??");
            }
            out.append("    ____________________________________________________________\n");
            out.append("    Got it. I've added this task:\n");
            String fullCommand = command.substring(9);
            String[] splitCommand = fullCommand.split("[/]");
            splitCommand[1] = splitCommand[1].substring(3);
            Deadline d = new Deadline(splitCommand[0], splitCommand[1]);
            list.add(d);
            out.append("    ").append(d).append("\n\n");
            out.append("    Now you have ").append(list.size()).append(" tasks in the list\n");
            out.append("    ____________________________________________________________\n");
        } else if (command.startsWith("event")) {
            if (command.length() <= 6) {
                throw new TiggerException("Whats the new event??");
            }
            out.append("    ____________________________________________________________\n");
            out.append("    Got it. I've added this task:\n");
            String fullCommand = command.substring(6);
            String[] splitCommand = fullCommand.split("[/]");
            splitCommand[1] = splitCommand[1].substring(5);
            splitCommand[2] = splitCommand[2].substring(3);
            Event d = new Event(splitCommand[0], splitCommand[1].trim(), splitCommand[2]);
            list.add(d);
            out.append("    ").append(d).append("\n\n");
            out.append("    Now you have ").append(list.size()).append(" tasks in the list\n");
            out.append("    ____________________________________________________________\n");
        } else if (command.startsWith("delete")) {
            if (command.length() <= 7) {
                throw new TiggerException("What do you want me to delete??");
            }
            out.append("    ____________________________________________________________\n");
            out.append("    Noted. I've removed this task:\n");
            int index = Integer.parseInt(command.substring(7)) - 1;
            Task t = list.get(index);
            out.append("    ").append(t).append("\n");
            list.remove(index);
            out.append("    Now you have ").append(list.size()).append(" tasks in the list\n");
            out.append("    ____________________________________________________________\n");
        } else if (command.startsWith("find")) {
            if (command.length() <= 5) {
                throw new TiggerException("What do you want me to find??");
            }
            out.append("    ____________________________________________________________\n");
            String itemToFind = command.substring(5);
            ArrayList<Task> foundTasks = new ArrayList<>();
            for (Task task : list) {
                if (task.getDescription().contains(itemToFind)) {
                    foundTasks.add(task);
                }
            }
            if (!foundTasks.isEmpty()) {
                out.append("    Here are the matching tasks in your list:\n");
                for (int i = 0; i < foundTasks.size(); i++) {
                    out.append("    ").append(i + 1).append(".").append(foundTasks.get(i).toString()).append("\n");
                }
            } else {
                out.append("    No matching tasks found in your list.\n");
            }
            out.append("    ____________________________________________________________\n");
        } else {
            throw new TiggerException("Give me something I can understand!!");
        }

        return out.toString();
    }
}
