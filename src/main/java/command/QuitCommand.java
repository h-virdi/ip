package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Handles the command to quit the software
 */

public class QuitCommand extends Command {
    /**
     * Prints goodbye message as part of the quit command
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Changes shouldExit boolean to true to exit software
     * @return true
     */
    @Override
    public boolean shouldExit() {
        return true;
    }
}
