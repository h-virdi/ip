package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Handles the command to print the full task list
 */

public class ListCommand extends Command {
    /**
     * Prints all the tasks in task list
     * @param tasks accumulated list of tasks
     * @param ui UI where notifications are displayed
     * @param storage storage system where tasks are saved
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks.getTasks());
    }
}
