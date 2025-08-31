package misc;

import task.Task;

public enum Commands {
    TODO,
    DEADLINE,
    EVENT,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    BYE,
    ONDATE;

    public static Commands fromString(String input) throws TaskBotException {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TaskBotException("OOPS!! I don't know what you want me to do :-(" + input);
        }
    }
}