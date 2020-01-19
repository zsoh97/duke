import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        echo();
    }
    public static void echo(){
        String border = "******************************************************";
        String greeting = "Hi! My name is Alfred. I aim to please.";
        System.out.println(border + "\n  " + greeting + "\n" + border);
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        String taskAdd = "Noted. The following task has been added:\n   ";
        String[] split;
        List<Task> tasks = new ArrayList<>();
        while(!input.equals("bye")) {
            switch (input){
                case "list":
                    System.out.println(border + "\n  " + "The items in your list are:");
                    for (int i = 1; i <= tasks.size(); i++) {
                        System.out.println("  " + (i) + ". " + tasks.get(i - 1));
                    }
                    System.out.println(border);
                    break;
                case "done":
                    Task task = tasks.get(sc.nextInt()-1);
                    task.done();
                    System.out.println(border + "\n  " + "Well Done! The task has been marked as done." + "\n  " + task + "\n" + border);
                    break;
                case "todo":
                    input = sc.nextLine();
                    Todo todo = new Todo(input);
                    tasks.add(todo);
                    System.out.println(border + "\n  " + taskAdd + todo + "\n  " + "There are currently " + tasks.size() + " tasks in the list.\n" + border);
                    break;
                case "deadline":
                    input = sc.nextLine();
                    split = input.split("/by ");
                    Deadline deadline = new Deadline(split[0], split[1]);
                    tasks.add(deadline);
                    System.out.println(border + "\n  " + taskAdd + deadline + "\n  " + "There are currently " + tasks.size() + " tasks in the list.\n" + border);
                    break;
                case "event":
                    input = sc.nextLine();
                    split = input.split("/at ");
                    Event event = new Event(split[0], split[1]);
                    tasks.add(event);
                    System.out.println(border + "\n  " + taskAdd + event + "\n  " + "There are currently " + tasks.size() + " tasks in the list.\n" + border);
                    break;
                default:
            }
            input = sc.next();
        }
        System.out.println("Goodbye. I hope I was useful. See you again.");
        sc.close();
    }
}
