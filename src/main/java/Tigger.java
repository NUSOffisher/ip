import java.util.Objects;
import java.util.Scanner;

public class Tigger {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n");
        System.out.println("Hello! I'm Tigger\n");
        System.out.println("What can I do for you?\n");
        System.out.println("____________________________________________________________\n");

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!Objects.equals(command, "bye")) {
            System.out.println("    ____________________________________________________________\n");
            System.out.println("    " + command);
            System.out.println("    ____________________________________________________________\n");
            command = scanner.nextLine();
        }
        System.out.println("    ____________________________________________________________\n");
        System.out.println("    Bye. Hope to see you again soon!\n");
        System.out.println("    ____________________________________________________________");
    }
}
