import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui class interacts with Duke user, requesting input and printing prompts for user.
 *
 * This class provides constructor for Ui object.
 */
public class Ui {
    private final String BORDER = "**********************************************************************";

    /**
     * Constructor for Ui object.
     */
    public Ui() {
    }

    /**
     * Ui object displays welcome message when Duke is launched.
     */
    protected void showWelcome() {
        String greeting = "Hi! My name is Alfred. I aim to please.";
        System.out.println(this.BORDER + "\n  " + greeting + "\n" + this.BORDER);
    }

    /**
     * Ui object displays error message if any error arises when loading from save file
     * from hard disk.
     */
    protected void showLoadingError() {
        System.out.println();
    }

    /**
     * Ui object displays input String.
     * @param toPrint input String for Ui object to display.
     */
    protected void print(String toPrint) {
        System.out.println(this.BORDER + toPrint + this.BORDER);
    }

    /**
     * Begin Ui's interaction with user.
     */
    protected void start(){
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        String taskAdd = "Noted. The following task has been added:\n   ";
        String[] split;
        String description;
         while (!input.equals("bye")) {
             try{
             switch (input) {
                 case "list":
                     if (Duke.tasks.isEmpty()) {
                         this.print("\n  There are currently no items in your list. \n");
                         break;
                     }
                     StringBuilder sb = new StringBuilder();
                     sb.append("\n  " + "The items in your list are: \n");
                     for (int i = 1; i <= Duke.tasks.size(); i++) {
                         sb.append("  ").append(i).append(". ").append(Duke.tasks.getTask(i - 1)).append("\n");
                     }
                     this.print(sb.toString());
                     Duke.storage.overwrite((ArrayList<Task>) Duke.tasks.getList());
                     break;
                     case "done":
                         Task task = Duke.tasks.getTask(sc.nextInt() - 1);
                         task.done();
                         this.print("\n  " + "Well Done! The task has been marked as done." + "\n  "
                                 + task + "\n");
                         Duke.storage.overwrite((ArrayList<Task>) Duke.tasks.getList());
                         break;
                     case "todo":
                         description = sc.nextLine();
                         if (description.isBlank()) {
                             throw new DukeException("My apologies, description of todo is empty. :(");
                         }
                         Task todo = new Task(Duke.Tasks.todo, description);
                         Duke.tasks.addTask(todo);
                         this.print("\n  " + taskAdd + todo + "\n  There are currently " + Duke.tasks.size()
                                 + " Duke.tasks in the list.\n");
                         Duke.storage.write(todo);
                         break;
                     case "deadline":
                         description = sc.nextLine();
                         split = description.split("/by ");
                         if (split.length < 2) {
                             throw new DukeException("My apologies, deadline description has insufficient details :(");
                         }
                         Task deadline = new Task(Duke.Tasks.deadline, split[0], split[1]);
                         Duke.tasks.addTask(deadline);
                         this.print("\n  " + taskAdd + deadline + "\n  There are currently "
                                 + Duke.tasks.size() + " Duke.tasks in the list.\n");
                         Duke.storage.write(deadline);
                         break;
                     case "event":
                         description = sc.nextLine();
                         split = description.split("/at ");
                         if (description.isBlank()) {
                             throw new DukeException("My apologies, event description has insufficient details. :(");
                         }
                         Task event = new Task(Duke.Tasks.event, split[0], split[1]);
                         Duke.tasks.addTask(event);
                         this.print("\n  " + taskAdd + event + "\n  There are currently " + Duke.tasks.size()
                                 + " Duke.tasks in the list.\n");
                         Duke.storage.write(event);
                         break;
                     case "delete":
                         description = sc.nextLine();
                         if (description.isBlank()) {
                             throw new DukeException("My apologies, task number to be deleted not found :(");
                         }
                         int delete = Integer.parseInt(description.trim());
                         if (delete > Duke.tasks.size()) {
                             throw new DukeException("My apologies, task number to be deleted not found :(");
                         }
                         Task removed = Duke.tasks.getTask(delete - 1);
                         Duke.tasks.deleteTask(delete - 1);
                         this.print("\n  Noted. Task " + delete + " has been removed:\n  "
                                 + removed + "\n  There are currently " + Duke.tasks.size() + " Duke.tasks in the list.\n");
                         Duke.storage.overwrite((ArrayList<Task>) Duke.tasks.getList());
                         break;
                     default:
                         throw new DukeException("My apologies, I do not recognise the command. :(");
                    }
                } catch (DukeException e) {
                    this.print("\n  " + e.getMessage() + "\n");
                }
                input = sc.next();
            }
        this.print("\n Goodbye. I hope I was useful. See you again.\n");
        sc.close();
        Duke.storage.overwrite((ArrayList<Task>) Duke.tasks.getList());
    }
}
