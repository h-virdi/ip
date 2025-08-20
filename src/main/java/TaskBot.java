import java.util.Scanner;

public class TaskBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm TaskBot!");
        System.out.println("What can I do for you?");

        while(true) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
            else {
                System.out.println("____________________________________________________________");
                System.out.println(" " + command);
                System.out.println("____________________________________________________________");
            }
        }
    }
}
