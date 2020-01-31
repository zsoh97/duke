import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    TaskList taskList = new TaskList();

    @Test
    void size() {
        taskList.addTask(new Task( Duke.Tasks.todo, "read book" ));
        assertEquals(1, taskList.size());
    }

    @Test
    void isEmpty() {
        assertTrue(taskList.isEmpty());
    }
}