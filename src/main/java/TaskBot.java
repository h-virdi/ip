import java.util.Scanner;

import command.Command;
import misc.TaskBotException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Class handling the running of TaskBot
 */
public class TaskBot {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Initialises the internal attributes required for TaskBot to run
     * @param filePath path of file where task list is locally stored
     */
    public TaskBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
    }

    public static void main(String[] args) {
        new TaskBot("./data/TaskBot.txt").run();
    }

    /**
     * Runs TaskBot
     */
    public void run() {
        Ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        boolean shouldExit = false;

        while (!shouldExit) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                continue;
            }

            try {
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                shouldExit = c.shouldExit();
            } catch (TaskBotException e) {
                Ui.showError(e.getMessage());
            }
        }
        scanner.close();
    }
}
