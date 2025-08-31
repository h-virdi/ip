import java.util.Scanner;

import command.Command;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;
import misc.TaskBotException;

public class TaskBot {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public TaskBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
    }

    public static void main(String[] args) {
        new TaskBot("./data/TaskBot.txt").run();
    }

    public void run() {
        Ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        boolean shouldExit = false;

        while (!shouldExit) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;

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