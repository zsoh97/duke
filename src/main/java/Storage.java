
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    protected List<Task> tasks = new ArrayList<>();
    protected String filePath;

    protected Storage(String filePath) {
        this.filePath = filePath;
    }

    protected void overwrite(ArrayList<Task> newTaskList) {
        // Assigns new input list to object's list.
        this.tasks = newTaskList;

        // Overwrite current save file with new list.
        try {
            PrintWriter done = new PrintWriter(new BufferedWriter(new FileWriter(
                    filePath, false)));
            for (Task task : tasks) {
                done.println(task.write());
            }
            done.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void write(Task task){
        // Append new Task to save file.
        try{
            PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter(
                    filePath, true)));
            write.println(task.write());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    protected List<Task> load() throws DukeException {
        // Iterates through list in safe file and populates list with Task objects.
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.filePath));
            String identifier;

            //Check if save file has reached EOF
            while ((identifier = br.readLine()) != null) {
                String[] taskDetails = identifier.split(" / ");
                String taskType = taskDetails[0];
                switch (taskType) {
                    case "T":
                        // Create new Task of todo type
                        Task todo = new Task(Duke.Tasks.todo, taskDetails[2]);

                        // Check if Task has been marked as done.
                        if (Integer.parseInt(taskDetails[1]) == 1) {
                            todo.done();
                        }

                        // Add todo to list
                        tasks.add(todo);

                        break;
                    case "D":
                        // Check if details provided in safe file is sufficient to form deadline Task
                        if (taskDetails.length < 4) {
                            throw new DukeException("insufficient details in deadline task :(");
                        }

                        // Create new Task of deadline type
                        Task deadline = new Task(Duke.Tasks.deadline, taskDetails[2],
                                taskDetails[3]);

                        // Check if Task has been marked as done.
                        if (Integer.parseInt(taskDetails[1]) == 1) {
                            deadline.done();
                        }

                        // Add deadline to list.
                        tasks.add(deadline);

                        break;
                    case "E":
                        // Check if sufficient details are present to form Task of event type.
                        if (taskDetails.length < 4) {
                            throw new DukeException("insufficient details in event task :(");
                        }

                        // Create new Task object of event task type.
                        Task event = new Task(Duke.Tasks.event, taskDetails[2], taskDetails[3]);

                        // Check if Task has been marked as done.
                        if (Integer.parseInt(taskDetails[1]) == 1) {
                            event.done();
                        }

                        // Add event to list.
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
