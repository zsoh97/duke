import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    enum Tasks{
        todo, deadline, event
    }

    public static void main(String[] args){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        echo();
    }
    public static void echo(){
        String border = "**********************************************************************";
        String greeting = "Hi! My name is Alfred. I aim to please.";
        System.out.println(border + "\n  " + greeting + "\n" + border);
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        String[] split;
        String description;
        String taskAdd = "Noted. The following task has been added:\n   ";
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data/duke.txt"))) {
            while ((description = br.readLine()) != null) {
                split = description.split(" / ");
                switch (split[0]) {
                    case "T":
                        Task todo = new Task(Tasks.todo, split[2]);
                        if (Integer.parseInt(split[1]) == 1) {
                            todo.done();
                        }
                        tasks.add(todo);
                        break;
                    case "D":
                        Task deadline = new Task(Tasks.deadline, split[2], split[3]);
                        if (Integer.parseInt(split[1]) == 1) {
                            deadline.done();
                        }
                        tasks.add(deadline);
                        break;
                    case "E":
                        Task event = new Task(Tasks.event, split[2], split[3]);
                        if (Integer.parseInt(split[1]) == 1) {
                            event.done();
                        }
                        tasks.add(event);
                        break;
                    default:
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/duke.txt", true)))) {
            while (!input.equals("bye")) {
                try {
                    switch (input) {
                        case "list":
                            if (tasks.isEmpty()) {
                                System.out.println(border + "\n  There are currently no items in your list. \n" + border);
                                break;
                            }
                            System.out.println(border + "\n  " + "The items in your list are:");
                            for (int i = 1; i <= tasks.size(); i++) {
                                System.out.println("  " + (i) + ". " + tasks.get(i - 1));
                            }
                            System.out.println(border);
                            break;
                        case "done":
                            Task task = tasks.get(sc.nextInt() - 1);
                            task.done();
                            System.out.println(border + "\n  " + "Well Done! The task has been marked as done." + "\n  "
                                    + task + "\n" + border);
                            try(PrintWriter done = new PrintWriter(new BufferedWriter(new FileWriter(
                                    "data/duke.txt", false)))) {
                                for(Task t: tasks){
                                    done.println(t.write());
                                }
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                            break;
                        case "todo":
                            description = sc.nextLine();
                            if (description.isBlank()) {
                                throw new DukeException("My apologies, description of todo is empty. :(");
                            }
//                      Todo todo = new Todo(description);
                            Task todo = new Task(Tasks.todo, description);
                            tasks.add(todo);
                            System.out.println(border + "\n  " + taskAdd + todo + "\n  There are currently " + tasks.size()
                                    + " tasks in the list.\n" + border);
                            out.println(todo.write());
                            break;
                        case "deadline":
                            description = sc.nextLine();
                            split = description.split("/by ");
                            if (split.length < 2) {
                                throw new DukeException("My apologies, deadline description has insufficient details :(");
                            }
//                        Deadline deadline = new Deadline(split[0], split[1]);
                            Task deadline = new Task(Tasks.deadline, split[0], split[1]);
                            tasks.add(deadline);
                            System.out.println(border + "\n  " + taskAdd + deadline + "\n  There are currently "
                                    + tasks.size() + " tasks in the list.\n" + border);
                            out.println(deadline.write());
                            break;
                        case "event":
                            description = sc.nextLine();
                            split = description.split("/at ");
                            if (description.isBlank()) {
                                throw new DukeException("My apologies, event description has insufficient details. :(");
                            }
//                        Event event = new event(split[0], split[1]);
                            Task event = new Task(Tasks.event, split[0], split[1]);
                            tasks.add(event);
                            System.out.println(border + "\n  " + taskAdd + event + "\n  There are currently " + tasks.size()
                                    + " tasks in the list.\n" + border);

                            out.println(event.write());
                            break;
                        case "delete":
                            description = sc.nextLine();
                            if (description.isBlank()) {
                                throw new DukeException("My apologies, task number to be deleted not found :(");
                            }
                            int delete = Integer.parseInt(description.trim());
                            if (delete > tasks.size()) {
                                throw new DukeException("My apologies, task number to be deleted not found :(");
                            }
                            Task removed = tasks.get(delete - 1);
                            tasks.remove(delete - 1);
                            System.out.println(border + "\n  Noted. Task " + delete + " has been removed:\n  "
                                    + removed + "\n  There are currently " + tasks.size() + " tasks in the list.\n"
                                    + border);
                            try(PrintWriter toDelete = new PrintWriter(new BufferedWriter(new FileWriter(
                                    "data/duke.txt", false)))) {
                                for(Task t: tasks){
                                    toDelete.println(t.write());
                                }
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                            break;
                        default:
                            throw new DukeException("My apologies, I do not recognise the command. :(");
                    }
                } catch (DukeException e) {
                    System.out.println(border + "\n  " + e.getMessage() + "\n" + border);
                }
                input = sc.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Goodbye. I hope I was useful. See you again.");
        sc.close();
    }
}
