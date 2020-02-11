
import java.util.ArrayList;
import java.util.List;

/**
 * TaskList object stores the List of tasks by the user in Duke. Tasklist object handles
 * operations of adding Task object, deleting Task object and retrieving Task object.
 */
public class TaskList {
    /**
     * Class TaskList contains List of Task objects to be maintained.
     */
    private List<Task> tasks;

    /**
     * Constructor for Tasklist object.
     */
    protected TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns List of tasks in duke.
     * @return List of Task objects.
     */
    protected List<Task> getList() {
        return this.tasks;
    }

    /**
     * Constructs new TaskList object with input List of Task objects.
     * @param tasks List of Task objects used to populate TaskList's list parameter.
     */
    protected TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Deletes Task object at index from list.
     * @param index Index of Task object to be deleted.
     */
    protected void deleteTask(int index) {
        assert index <= tasks.size() && index > 0 : "Index of "
                + "task should be greater than 0";
        this.tasks.remove(index);
    }

    /**
     * Adds Task object to list.
     * @param task Task object to be added.
     */
    protected void addTask(Task task) {
        assert task != null: "Task should not be null.";
        this.tasks.add(task);
    }

    /**
     * Returns Task object from list at index.
     * @param index Index of desired Task object.
     * @return Task object at index.
     */
    protected Task getTask(int index) {
        assert index <= tasks.size() && index > 0 : "Index should be greater"
                + " than 0 and smaller than list size.";
        return this.tasks.get(index);
    }

    /**
     * Checks size of list.
     * @return Returns number of Task objects in the list.
     */
    protected int getSize() {
        return this.tasks.size();
    }

    /**
     * Checks if the list contains no elements.
     * @return True if the list contains no elements.
     */
    protected boolean isEmpty() {
        return this.tasks.isEmpty();
    }
}
