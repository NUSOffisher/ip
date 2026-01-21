import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Tigger {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n");
        System.out.println("Hello! I'm Tigger\n");
        System.out.println("What can I do for you?\n");
        System.out.println("____________________________________________________________\n");

        ArrayList<String> list = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!Objects.equals(command, "bye")) {
            if (command.equals("list")){
                System.out.println("    ____________________________________________________________\n");
                for (int i = 0; i < list.size(); i++){
                    System.out.println((i + 1) + ". " + list.get(i));
                }
                System.out.println("    ____________________________________________________________\n");
            } else {
            list.add(command);
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
