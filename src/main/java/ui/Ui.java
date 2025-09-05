package ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import task.Task;
import task.Deadline;
import task.Event;
import misc.TaskBotException;

/**
 * Deals with interactions with user
 */

public class Ui {

    /**
     * Parses the input command to obtain the required task index i
     *
     * @param command input command provided by the user
     * @param size current size of the task list
     * @return task index
     * @throws TaskBotException
     */

    public static int read(String command, int size) throws TaskBotException {
        try {
            int i = Integer.parseInt(command.trim()) - 1;
            if (i < 0 || i >= size) {
                throw new TaskBotException("OOPS!! Task at index i not found.");
            }
            return i;
        } catch (NumberFormatException e) {
            throw new TaskBotException("Task index i needs to be an integer.");
        }
    }

    /**
     * Notifies user of task added to list of tasks
     * @param t task added
     * @param size size of the task list after adding new task
     */

    public static void printAddedTask(Task t, int size) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + t.toString());
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints an enumerated list of all current tasks
     * @param tasks accumulated list of tasks
     */

    public static void printList(List<Task> tasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println(" " + (i + 1) + "." + t.toString());
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Notifies user of marking/unmarking task
     * @param t task whose status is being modified
     * @param isMarked boolean representing task status
     */

    public static void printMark(Task t, boolean isMarked) {
        System.out.println("____________________________________________________________");
        if (isMarked) {
            System.out.println(" Nice! I've marked this task as done:");
        } else {
            System.out.println(" OK, I've marked this task as not done yet:");
        }
        System.out.println("   [" + t.getStatusIcon() + "] " + t.getDesc());
        System.out.println("____________________________________________________________");
    }

    /**
     * Notifies user of task removed from task list
     * @param tasks task list
     * @param t task being deleted
     */
    public static void printDeletedTask(List<Task> tasks, Task t) {
        System.out.println("____________________________________________________________");
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + t.toString());
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints all Deadline and Event objects relevant to specified date
     * @param tasks accumulated list of tasks
     * @param date date of interest
     */

    public static void printDateTasks(List<Task> tasks, LocalDate date) {
        System.out.println("____________________________________________________________");
        System.out.println("      Tasks on " + date + ":");
        boolean tasksFound = false;

        for (Task t : tasks) {
            if (t instanceof Deadline d && d.getBy().equals(date)) {
                System.out.println("       " + t);
                tasksFound = true;
            } else if (t instanceof Event e) {
                LocalDate start = e.getStart();
                LocalDate end = e.getEnd();

                if ((date.isEqual(start) || date.isAfter(start)) &&
                        (date.isEqual(end) || date.isBefore(end))) {
                    System.out.println("       " + t);
                    tasksFound = true;
                }
            }
        }
        if (!tasksFound) {
            System.out.println("      OOPS!! No tasks relevant to this date.");
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints all tasks with names containing the specified keyword
     * @param tasks accumulated list of tasks
     * @param taskName keyword used to search in tasks
     * @return String representing the list of matching tasks
     */
    public String printFind(ArrayList<Task> tasks, String taskName) {
        StringBuilder sb = new StringBuilder();
        sb.append("Looking for task including ")
                .append(taskName)
                .append("...")
                .append("\n");
        boolean taskFound = false;

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getDesc().toLowerCase().contains(taskName.toLowerCase())) {
                sb.append("  ")
                        .append(i + 1)
                        .append(". ")
                        .append(t)
                        .append("\n");
                taskFound = true;
            }
        }
        if (!taskFound) {
            return "Task including " + taskName + "not found.";
        }
        return sb.toString();
    }

    public static void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm TaskBot.");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void showGoodbye() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void showError(String msg) {
        System.out.println("OOPS!!" + msg);
    }
}
