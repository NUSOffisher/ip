package tigger;

import java.util.ArrayList;
import java.util.Random;

/**
 * Parser class to handle user commands and manipulate the task list.
 * Used GitHub Copilot to change the responses to be more Tigger-like.
 */
public class Parser {
    private static boolean waitingForTriviaAnswer = false;
    private static String currentTriviaAnswer = "";
    /**
     * Executes the command
     *
     * @param command The user command to execute.
     * @param list    The list of tasks to manipulate.
     * @throws TiggerException If the command is invalid or cannot be processed.
     */
    public static String getResponse(String command, ArrayList<Task> list) throws TiggerException {
        if (command == null) {
            return "";
        }
        command = command.trim().toLowerCase();

        if (waitingForTriviaAnswer) {
            waitingForTriviaAnswer = false;
            if (command.equalsIgnoreCase(currentTriviaAnswer)) {
                return "Tigerrific! That's right! Tigger's proud of you!";
            } else {
                return "Oh, fiddle-umps! That's not it! The answer was: " + currentTriviaAnswer;
            }
        }

        StringBuilder out = new StringBuilder();

        if (command.equals("list")) {
            listCommand(list, out);
        } else if (command.startsWith("mark") || command.startsWith("unmark")) {
            markCommand(command, list, out);
        } else if (command.startsWith("todo")) {
            toDoCommand(command, list, out);
        } else if (command.startsWith("deadline")) {
            deadlineCommand(command, list, out);
        } else if (command.startsWith("event")) {
            eventCommand(command, list, out);
        } else if (command.startsWith("delete")) {
            deleteCommand(command, list, out);
        } else if (command.startsWith("find")) {
            findCommand(command, list, out);
        } else if (command.startsWith("trivia")) {
            triviaCommand(out);
        } else {
            throw new TiggerException("Tigger can't understand that! Try something else, tee-hee!");
        }

        return out.toString();
    }
    /**
     * Helper method to handle the list command.
     *
     * @param list The list of tasks to display.
     * @param out  The StringBuilder to append the output message to.
     */
    public static void listCommand(ArrayList<Task> list, StringBuilder out) {
        out.append("Tigger's bouncy list o' tasks:\n");
        for (int i = 0; i < list.size(); i++) {
            Task current = list.get(i);
            out.append(i + 1).append(". ").append(current.toString()).append("\n");
        }
    }
    /**
     * Helper method to handle mark and unmark commands.
     *
     * @param command The user command to execute.
     * @param list    The list of tasks to manipulate.
     * @param out     The StringBuilder to append the output message to.
     * @throws TiggerException If the command is invalid or cannot be processed.
     */
    public static void markCommand(String command, ArrayList<Task> list, StringBuilder out) throws TiggerException {
        String[] splitString = command.split("\\s");
        int index = Integer.parseInt(splitString[1]);
        if (command.startsWith("mark")) {
            list.get(index - 1).setDone();
            out.append("Tigerrific! I bounced this one as done! Hip hooray!\n");
        } else {
            list.get(index - 1).setNotDone();
            out.append("Oh, tiddly! It's unbounced now (not done):\n");
        }
        out.append(list.get(index - 1).toString()).append("\n");
    }
    /**
     * Helper method to handle the todo command.
     *
     * @param command The user command to execute.
     * @param list    The list of tasks to manipulate.
     * @param out     The StringBuilder to append the output message to.
     * @throws TiggerException If the command is invalid or cannot be processed.
     */
    public static void toDoCommand(String command, ArrayList<Task> list, StringBuilder out) throws TiggerException {
        if (command.length() <= 5) {
            throw new TiggerException("What d'ya want Tigger to do??");
        }
        out.append("Tigerrific! Tigger's added a bouncy task:\n");
        ToDo t = new ToDo(command.substring(5));
        list.add(t);
        out.append(t).append("\n");
        out.append("Now you have ").append(list.size()).append(" bouncy tasks in the list! Bounce on!\n");
    }
    /**
     * Helper method to handle the deadline command.
     *
     * @param command The user command to execute.
     * @param list    The list of tasks to manipulate.
     * @param out     The StringBuilder to append the output message to.
     * @throws TiggerException If the command is invalid or cannot be processed.
     */
    public static void deadlineCommand(String command,
                                       ArrayList<Task> list,
                                       StringBuilder out) throws TiggerException {
        if (command.length() <= 9) {
            throw new TiggerException("What's the deadline for, Tigger can't guess??");
        }
        out.append("Tigerrific! Tigger's added a deadline task! Tick-tock:\n");
        String fullCommand = command.substring(9);
        String[] splitCommand = fullCommand.split("[/]");
        splitCommand[1] = splitCommand[1].substring(3);
        Deadline d = new Deadline(splitCommand[0], splitCommand[1]);
        list.add(d);
        out.append(d).append("\n\n");
        out.append("Now you have ").append(list.size()).append(" bouncy tasks in the list! Bounce along!\n");
    }
    /**
     * Helper method to handle the event command.
     *
     * @param command The user command to execute.
     * @param list    The list of tasks to manipulate.
     * @param out     The StringBuilder to append the output message to.
     * @throws TiggerException If the command is invalid or cannot be processed.
     */
    public static void eventCommand(String command, ArrayList<Task> list, StringBuilder out) throws TiggerException {
        if (command.length() <= 6) {
            throw new TiggerException("What's the new event, dear Tigger??");
        }
        out.append("Tigerrific! Tigger's added an event! How exciting!:\n");
        String fullCommand = command.substring(6);
        String[] splitCommand = fullCommand.split("[/]");
        splitCommand[1] = splitCommand[1].substring(5);
        splitCommand[2] = splitCommand[2].substring(3);
        Event e = new Event(splitCommand[0], splitCommand[1].trim(), splitCommand[2]);
        list.add(e);
        out.append(e).append("\n\n");
        out.append("Now you have ").append(list.size()).append(" bouncy tasks in the list! whoopee!\n");
    }
    /**
     * Helper method to handle the delete command.
     *
     * @param command The user command to execute.
     * @param list    The list of tasks to manipulate.
     * @param out     The StringBuilder to append the output message to.
     * @throws TiggerException If the command is invalid or cannot be processed.
     */
    public static void deleteCommand(String command, ArrayList<Task> list, StringBuilder out) throws TiggerException {
        if (command.length() <= 7) {
            throw new TiggerException("What d'ya want me to delete??");
        }
        out.append("Oh my! Tigger removed this bouncy task:\n");
        int index = Integer.parseInt(command.substring(7)) - 1;
        Task t = list.get(index);
        out.append(t).append("\n");
        list.remove(index);
        out.append("Now you have ").append(list.size()).append(" bouncy tasks in the list! All set!\n");
    }
    /**
     * Helper method to handle the find command.
     *
     * @param command The user command to execute.
     * @param list    The list of tasks to manipulate.
     * @param out     The StringBuilder to append the output message to.
     * @throws TiggerException If the command is invalid or cannot be processed.
     */
    public static void findCommand(String command, ArrayList<Task> list, StringBuilder out) throws TiggerException {
        if (command.length() <= 5) {
            throw new TiggerException("What d'ya want me to find??");
        }
        String itemToFind = command.substring(5);
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : list) {
            if (task.getDescription().contains(itemToFind)) {
                foundTasks.add(task);
            }
        }
        if (!foundTasks.isEmpty()) {
            out.append("Here are the matching bouncy things! Ta-da!:\n");
            for (int i = 0; i < foundTasks.size(); i++) {
                out.append(i + 1).append(".").append(foundTasks.get(i).toString()).append("\n");
            }
        } else {
            out.append("No matches, oh bother! Tigger couldn't find any.\n");
        }
    }
    /**
     * Helper method to handle the trivia command.
     *
     * @param out The StringBuilder to append the output message to.
     */
    public static void triviaCommand(StringBuilder out) {
        Random random = new Random();
        int num = random.nextInt(5);

        Trivia trivia = new Trivia();
        String question = trivia.getQuestion(num);

        currentTriviaAnswer = trivia.getAnswer(num);
        waitingForTriviaAnswer = true;
        out.append("Ooh! Trivia time! Bounce and guess, tee-hee!:\n");
        out.append(question).append("\n");
    }
}
