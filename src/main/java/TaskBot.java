import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm TaskBot");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
        List<Task> tasks = new ArrayList<>();

        while(true) {
            String command = scanner.nextLine();
            try {
                if (command.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (command.startsWith("mark")) {
                    int taskNum = Integer.parseInt(command.substring(5));
                    Task t = tasks.get(taskNum - 1);
                    t.mark();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   [" + t.getStatusIcon() + "] " + t.getDesc());
                    System.out.println("____________________________________________________________");
                } else if (command.startsWith("unmark")) {
                    int taskNum = Integer.parseInt(command.substring(7));
                    Task t = tasks.get(taskNum - 1);
                    t.unmark();
                    System.out.println("____________________________________________________________");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   [" + t.getStatusIcon() + "] " + t.getDesc());
                    System.out.println("____________________________________________________________");
                } else if (command.startsWith("delete")) {
                    int taskNum = Integer.parseInt(command.substring(7));
                    Task t = tasks.get(taskNum - 1);
                    tasks.remove(taskNum - 1);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + t.toString());
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (command.equals("list")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task t = tasks.get(i);
                        System.out.println(" " + (i + 1) + "." + t.toString());
                    }
                    System.out.println("____________________________________________________________");
                } else if (command.startsWith("todo")) {
                    if (command.length() <= 5) {
                        throw new TaskBotException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    String todo = command.substring(5);
                    Task t = new Todo(todo);
                    tasks.add(t);
                    printAddedTask(t, tasks.size());
                } else if (command.startsWith("deadline")) {
                    String[] details = command.split("/by", 2);
                    String todo = details[0].substring(9).trim();
                    String deadline = details[1].trim();
                    Task t = new Deadline(todo, deadline);
                    tasks.add(t);
                    printAddedTask(t, tasks.size());
                } else if (command.startsWith("event")) {
                    String[] parts = command.split("/from", 2);
                    String todo = parts[0].substring(6).trim();
                    String[] times = parts[1].split("/to", 2);
                    String start = times[0].trim();
                    String end = times[1].trim();
                    Task t = new Event(todo, start, end);
                    tasks.add(t);
                    printAddedTask(t, tasks.size());
                } else {
                    throw new TaskBotException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (TaskBotException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }
    }
    private static void printAddedTask(Task t, int size) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + t.toString());
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

}
