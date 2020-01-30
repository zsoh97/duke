//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

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

    protected void write(Task task){
        try{
            PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter(filePath, false)));
            write.println(task.write());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

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
