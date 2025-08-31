package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;
import misc.TaskBotException;

/**
 * Handles the command to add a task to the task list
 */
public class AddCommand extends Command {
    private Task addedTask;

    /**
     * Initialises the task to be added as the input task t
     * @param t task to be added
     */
    public AddCommand(Task t) {
        this.addedTask = t;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskBotException {
        tasks.addTask(addedTask);
        storage.saveTasks(tasks.getTasks());
        ui.printAddedTask(addedTask, tasks.size());
    }
}
