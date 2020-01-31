import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage object is used as a storage for the list of Task objects by user in Duke.
 * Storage class provides implementation of methods to load, write to and overwrite
 * existing file on hard drive.
 */
public class Storage {
    /**
     * Storage objects have attributes of list of Task objects to store Task objects that
     * is loaded from file in hard drive and updated when user is using Duke and the file
     * path to file in hard drive.
     */
    private List<Task> tasks = new ArrayList<>();
    private String filePath;

    /**
     * Constructs a Storage object.
     * @param filePath path to file with saved list of tasks in hard drive.
     */
    protected Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Overwrites current existing data in saved file.
     * @param newTaskList List of Task objects used to populate safe file.
     */
    protected void overwrite(ArrayList<Task> newTaskList) {
        this.tasks = newTaskList;
        try {
            PrintWriter done = new PrintWriter(new BufferedWriter(new FileWriter(filePath, false)));
            for (Task task : tasks) {
                done.println(task.write());
            }
            done.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Appends newest Task object to save file's list of saved Task.
     * @param task Task object to be added to list.
     */
    protected void write(Task task){
        try{
            PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
            write.println(task.write());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Loads list of tasks in save file into list of Task objects. Throws Duke exception.
     * @return List of Task objects.
     * @throws DukeException Thrown when any line in the safe file does not fit the
     * required Task object requirements.
     */
    protected List<Task> load() throws DukeException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.filePath));
            String identifier;
            while ((identifier = br.readLine()) != null) {
                String[] taskDetails = identifier.split(" / ");
                String taskType = taskDetails[0];
                switch (taskType) {
                    case "T":
                        Task todo = new Task(Duke.Tasks.todo, taskDetails[2]);
                        if (Integer.parseInt(taskDetails[1]) == 1) {
                            todo.done();
                        }
                        tasks.add(todo);
                        break;
                    case "D":
                        if (taskDetails.length < 4) {
                            throw new DukeException("insufficient details in deadline task :(");
                        }
                        Task deadline = new Task(Duke.Tasks.deadline, taskDetails[2], taskDetails[3]);
                        if (Integer.parseInt(taskDetails[1]) == 1) {
                            deadline.done();
                        }
                        tasks.add(deadline);
                        break;
                    case "E":
                        if (taskDetails.length < 4) {
                            throw new DukeException("insufficient details in deadline task :(");
                        }
                        Task event = new Task(Duke.Tasks.event, taskDetails[2], taskDetails[3]);
                        if (Integer.parseInt(taskDetails[1]) == 1) {
                            event.done();
                        }
                        tasks.add(event);
                        break;
                    default:
                }
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return this.tasks;
    }
}
