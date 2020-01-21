import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        echo();
    }
    public static void echo() {
        String border = "**********************************************************************";
        String greeting = "Hi! My name is Alfred. I aim to please.";
        System.out.println(border + "\n  " + greeting + "\n" + border);
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        String description;
        String taskAdd = "Noted. The following task has been added:\n   ";
        List<Task> tasks = new ArrayList<>();
        while(!input.equals("bye")) {
            try {
                String[] split;
                switch (input) {
                    case "list":
                        System.out.println(border + "\n  " + "The items in your list are:");
                        for (int i = 1; i <= tasks.size(); i++) {
                            System.out.println("  " + (i) + ". " + tasks.get(i - 1));
                        }
                        System.out.println(border);
                        break;
                    case "done":
                        Task task = tasks.get(sc.nextInt() - 1);
                        task.done();
                        System.out.println(border + "\n  " + "Well Done! The task has been marked as done." + "\n  " + task + "\n" + border);
                        break;
                    case "todo":
                        description = sc.nextLine();
                        if (description.isBlank()) {
                            throw new DukeException("My apologies, description of todo is empty. :(");
                        }
                        Todo todo = new Todo(input);
                        tasks.add(todo);
                        System.out.println(border + "\n  " + taskAdd + todo + "\n  " + "There are currently " + tasks.size() + " tasks in the list.\n" + border);
                        break;
                    case "deadline":
                        description = sc.nextLine();
                        split = description.split("/by ");
                        if (split.length < 2) {
                            throw new DukeException("My apologies, deadline description has insufficient details :(");
                        }
                        Deadline deadline = new Deadline(split[0], split[1]);
                        tasks.add(deadline);
                        System.out.println(border + "\n  " + taskAdd + deadline + "\n  " + "There are currently " + tasks.size() + " tasks in the list.\n" + border);
                        break;
                    case "event":
                        description = sc.nextLine();
                        split = description.split("/at ");
                        if (description.isBlank()) {
                            throw new DukeException("My apologies, event description has insufficient details. :(");
                        }
                        Event event = new Event(split[0], split[1]);
                        tasks.add(event);
                        System.out.println(border + "\n  " + taskAdd + event + "\n  " + "There are currently " + tasks.size() + " tasks in the list.\n" + border);
                        break;
                    default:
                        throw new DukeException("My apologies, I do not recognise the command. :(");
                }
            }catch(DukeException e){
                System.out.println(border + "\n  " + e.getMessage() + "\n" + border);
            }
            input = sc.next();
        }
        System.out.println("Goodbye. I hope I was useful. See you again.");
        sc.close();
    }
}
