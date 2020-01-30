//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private String border = "**********************************************************************";

    public Ui() {
    }

    protected void showWelcome() {
        String greeting = "Hi! My name is Alfred. I aim to please.";
        System.out.println(this.border + "\n  " + greeting + "\n" + this.border);
    }

    protected void showLoadingError() {
        System.out.println();
    }

    protected void print(String toPrint) {
        System.out.println(this.border + toPrint + this.border);
    }

    protected void start(TaskList tasks, Storage storage){

        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        String taskAdd = "Noted. The following task has been added:\n   ";
        String[] split;
        String description;
         while (!input.equals("bye")) {
             try{
             switch (input) {
                 case "list":
                     if (tasks.isEmpty()) {
                         this.print("\n  There are currently no items in your list. \n");
                         break;
                     }
                     StringBuilder sb = new StringBuilder();
                     sb.append("\n  " + "The items in your list are: \n");
                     for (int i = 1; i <= tasks.size(); i++) {
                         sb.append("  ").append(i).append(". ").append(tasks.getTask(i - 1)).append("\n");
                     }
                     this.print(sb.toString());
                     storage.overwrite((ArrayList<Task>) tasks.getList());
                     break;
                     case "done":
                         Task task = tasks.getTask(sc.nextInt() - 1);
                         task.done();
                         this.print("\n  " + "Well Done! The task has been marked as done." + "\n  "
                                 + task + "\n");
                         storage.overwrite((ArrayList<Task>) tasks.getList());
                         break;
                     case "todo":
                         description = sc.nextLine();
                         if (description.isBlank()) {
                             throw new DukeException("My apologies, description of todo is empty. :(");
                         }
                         Task todo = new Task(Duke.Tasks.todo, description);
                         tasks.addTask(todo);
                         this.print("\n  " + taskAdd + todo + "\n  There are currently " + tasks.size()
                                 + " tasks in the list.\n");
                         storage.write(todo);
                         break;
                     case "deadline":
                         description = sc.nextLine();
                         split = description.split("/by ");
                         if (split.length < 2) {
                             throw new DukeException("My apologies, deadline description has insufficient details :(");
                         }
                         Task deadline = new Task(Duke.Tasks.deadline, split[0], split[1]);
                         tasks.addTask(deadline);
                         this.print("\n  " + taskAdd + deadline + "\n  There are currently "
                                 + tasks.size() + " tasks in the list.\n");
                         storage.write(deadline);
                         break;
                     case "event":
                         description = sc.nextLine();
                         split = description.split("/at ");
                         if (description.isBlank()) {
                             throw new DukeException("My apologies, event description has insufficient details. :(");
                         }
                         Task event = new Task(Duke.Tasks.event, split[0], split[1]);
                         tasks.addTask(event);
                         this.print("\n  " + taskAdd + event + "\n  There are currently " + tasks.size()
                                 + " tasks in the list.\n");
                         storage.write(event);
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
                         Task removed = tasks.getTask(delete - 1);
                         tasks.deleteTask(delete - 1);
                         this.print("\n  Noted. Task " + delete + " has been removed:\n  "
                                 + removed + "\n  There are currently " + tasks.size() + " tasks in the list.\n");
                         storage.overwrite((ArrayList<Task>) tasks.getList());
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
    }
}
