package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;
import misc.TaskBotException;

/**
 * Abstract class for handling different types of commands
 * Inherited by Add, Delete, Mark etc. classes
 */
public abstract class Command {

    /**
     * Carries out the relevant steps to fulfil the given command
     *
     * @param tasks accumulated list of tasks
     * @param ui UI where notifications are displayed
     * @param storage storage system where tasks are saved
     * @throws TaskBotException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws TaskBotException;

    /**
     * Represents whether the command requires the application to be terminated
     * @return true if command requires application to be quit; otherwise false
     */
    public boolean shouldExit() {
        return false;
    }
}
