import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm TaskBot!");
        System.out.println("What can I do for you?");
        List<Task> tasks = new ArrayList<>();

        while(true) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
            else if (command.startsWith("mark")) {
                int taskNum = Integer.parseInt(command.substring(5));
                Task t = tasks.get(taskNum - 1);
                t.mark();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [" + t.getStatusIcon() + "] " + t.getDesc());
                System.out.println("____________________________________________________________");
            }
            else if (command.startsWith("unmark")) {
                int taskNum = Integer.parseInt(command.substring(7));
                Task t = tasks.get(taskNum - 1);
                t.unmark();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  [" + t.getStatusIcon() + "] " + t.getDesc());
                System.out.println("____________________________________________________________");
            }
            else if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    Task t = tasks.get(i);
                    System.out.println((i + 1) + ".[" + t.getStatusIcon() + "] " + t.getDesc());
                }
                System.out.println("____________________________________________________________");
            }
            else {
                tasks.add(new Task(command));
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + command);
                System.out.println("____________________________________________________________");
            }
        }
    }
}
