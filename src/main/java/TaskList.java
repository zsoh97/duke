
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    protected TaskList() {
        this.tasks = new ArrayList<>();
    }

    protected List<Task> getList() {
        return this.tasks;
    }

    protected TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    protected void deleteTask(int index) {
        this.tasks.remove(index);
    }

    protected void addTask(Task task) {
        this.tasks.add(task);
    }

    protected Task getTask(int index) {
        return this.tasks.get(index);
    }

    protected int size() {
        return this.tasks.size();
    }

    protected boolean isEmpty() {
        return this.tasks.isEmpty();
    }
}
