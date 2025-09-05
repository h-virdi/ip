package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;
import misc.TaskBotException;
import java.io.IOException;

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

    /**
     * Carries out the relevant tasks to execute the add command
     * @param tasks accumulated list of tasks
     * @param ui UI where notifications are displayed
     * @param storage storage system where tasks are saved
     * @throws TaskBotException
     */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskBotException {
        try {
            tasks.addTask(addedTask);
            storage.saveTasks(tasks.getTasks());
            ui.printAddedTask(addedTask, tasks.size());
        } catch (IOException e) {
            throw new TaskBotException("Error saving tasks" + e.getMessage());
        }
    }
}
