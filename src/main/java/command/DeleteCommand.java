package command;

import java.io.IOException;

import misc.TaskBotException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Handles the command to remove a task from the task list
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Initialises the target task index (to be removed)
     * @param index target task index
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskBotException {
        try {
            Task removedTask = tasks.removeTask(index);
            storage.saveTasks(tasks.getTasks());
            ui.printDeletedTask(tasks.getTasks(), removedTask);
        } catch (IOException e) {
            throw new TaskBotException("Error saving tasks" + e.getMessage());
        }
    }
}
