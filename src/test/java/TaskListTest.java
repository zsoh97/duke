import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    TaskList tasklist = new TaskList();

    @Test
    void getList() {
    }

    @Test
    void deleteTask() {
    }

    @Test
    void addTask() {
    }

    @Test
    void getTask() {
    }

    @Test
    void size() {
        tasklist.addTask(new Task(Duke.Tasks.todo, "read book"));
        assertEquals(1, tasklist.size());
    }

    @Test
    void isEmpty() {
        assertTrue(tasklist.isEmpty());
    }
}