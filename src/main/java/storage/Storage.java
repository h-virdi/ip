package storage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import parser.Parser;
import task.Task;
import misc.TaskBotException;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private final File file;
    private Parser parser;

    /**
     * Creates a Storage object and initialises a file with the specified file path
     *
     * @param filePath path of the file where the tasks are stored
     */

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Saves current list of tasks in local file
     *
     * @param tasks current list of tasks (to be saved)
     * @throws IOException
     */

    public void saveTasks(List<Task> tasks) throws IOException {
        try {
            BufferedWriter bw = new BufferedWriter((new FileWriter(file)));
            for (Task t : tasks) {
                bw.write(t.toFileString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("OOPS!! Tasks were not saved: " + e.getMessage());
        }
    }

    /**
     * Loads tasks stored in the file if it exists
     * Otherwise, a new file is created and an empty task list is returned
     *
     * @return List<Task> list of tasks stored in file
     */

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return tasks;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String command;
            while ((command = br.readLine()) != null) {
                try {
                    Task t = Parser.parseTask(command);
                    tasks.add(t);
                } catch (TaskBotException e) {
                    System.out.println("OOPS!! Line cannot be parsed: " + e.getMessage());
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("OOPS!! File could not be loaded: " + e.getMessage());
        }
        return tasks;
    }
}
