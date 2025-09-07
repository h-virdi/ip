package command;

import java.io.IOException;

import misc.TaskBotException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Handles the command to mark/unmark a task
 */

public class MarkCommand extends Command {
    private int index;
    private boolean isMarked;

    /**
     * Initialises the target task index and its marked status
     * @param index
     * @param isMarked
     */
    public MarkCommand(int index, boolean isMarked) {
        this.index = index;
        this.isMarked = isMarked;
    }

    /**
     * Changes marked status of target task
     * @param tasks accumulated list of tasks
     * @param ui UI where notifications are displayed
     * @param storage storage system where tasks are saved
     * @throws TaskBotException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskBotException {
        try {
            Task t = tasks.getTask(index);
            if (isMarked) {
                t.mark();
            } else {
                t.unmark();
            }
            storage.saveTasks(tasks.getTasks());
            ui.printMark(t, isMarked);
        } catch (IOException e) {
            throw new TaskBotException("Error saving tasks" + e.getMessage());
        }
    }
}
