import java.util.ArrayList;
import java.util.List;
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
        System.out.println(BORDER + "\n  " + greeting + "\n" + BORDER);
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
    private void print(String toPrint) {
        System.out.println(BORDER + toPrint + BORDER);
    }

    private String format(String toFormat) {return BORDER + toFormat + BORDER;}

    /**
     * Begin Ui's interaction with user.
     */
    protected void start(){
        // Create new Scanner object to read user input.
        Scanner sc = new Scanner(System.in);

        // Initialise variables needed
        String input = sc.next();
        String taskAdd = "Noted. The following task has been added:\n   ";
        String[] split, dateTime;
        String description;
        // Check if user is done with Duke
        while (!input.equals("bye")) {
            try{
                switch (input) {
                case "list":
                    // Check if list of tasks is empty
                    if (Duke.tasks.isEmpty()) {
                        this.print("\n  There are currently no items in your list. \n");
                        break;
                    }

                    // Initialise String builder to hold list of Task objects.
                    StringBuilder sb = new StringBuilder();

                    // Append each Task object to string builder.
                    sb.append("\n  " + "The items in your list are: \n");
                    for (int i = 1; i <= Duke.tasks.getSize(); i++) {
                        sb.append("  ").append(i).append(". ")
                                .append(Duke.tasks.getTask(i - 1)).append("\n");
                    }

                    // Print out list of tasks to user.
                    this.print(sb.toString());

                    // Update save file.
                    Duke.storage.overwrite((ArrayList<Task>) Duke.tasks.getList());

                    break;
                case "done":
                    // Retrieve Task object at user inputted index.
                    Task task = Duke.tasks.getTask(sc.nextInt() - 1);

                    // Mark retrieved Task object as done.
                    task.done();

                    // Output success message
                    this.print("\n  " + "Well Done! The task has been marked as done."
                            + "\n  " + task + "\n");

                    // Update save file.
                    Duke.storage.overwrite((ArrayList<Task>) Duke.tasks.getList());

                    break;
                case "todo":
                    // Retrieve description from user input.
                    description = sc.nextLine();

                    // Check if description is valid.
                    if (description.isBlank()) {
                        throw new DukeException("My apologies, " +
                                "description of todo is empty. :(");
                    }

                    // Create new Task of type todo.
                    Task todo = new Task(Duke.Tasks.todo, description);

                    // Add new Task object to list.
                    Duke.tasks.addTask(todo);

                    // Output success message.
                    this.print("\n  " + taskAdd + todo + "\n  There are currently "
                            + Duke.tasks.getSize() + " tasks in the list.\n");

                    // Update safe file.
                    Duke.storage.write(todo);

                    break;
                case "deadline":
                    // Retrieve description from user input.
                    description = sc.nextLine();

                    // Split given description
                    split = description.split("/by ");

                    // Check if sufficient details were provided in description.
                    if (split.length < 2) {
                        throw new DukeException("My apologies, " +
                                "deadline description has insufficient details :(");
                    }

                    // Parse date details into LocalDate format.
                    dateTime = split[1].split(" ");
                    String taskBy = Parser.parseDate(dateTime[0]);

                    // Create new Task object of task type deadline.
                    Task deadline = new Task(Duke.Tasks.deadline, split[0],
                            taskBy + dateTime[1]);

                    // Add deadline to list.
                    Duke.tasks.addTask(deadline);

                    // Output success message.
                    this.print("\n  " + taskAdd + deadline + "\n  There are currently "
                            + Duke.tasks.getSize() + " tasks in the list.\n");

                    // Update save file.
                    Duke.storage.write(deadline);

                    break;
                case "event":
                    // Retrieve description from user input.
                    description = sc.nextLine();

                    // Split given description
                    split = description.split("/at ");

                    // Check if given description has sufficient details.
                    if (description.isBlank()) {
                        throw new DukeException("My apologies, " +
                                "event description has insufficient details. :(");
                    }

                    // Parse date details into LocalDate format.
                    dateTime = split[1].split(" ");
                    String taskAt = Parser.parseDate(dateTime[0]);

                    // Create new Task object of event task type.
                    Task event = new Task(Duke.Tasks.event, split[0],
                            taskAt + dateTime[1]);

                    // Add event to list.
                    Duke.tasks.addTask(event);

                    // Output success message.
                    this.print("\n  " + taskAdd + event + "\n  There are currently "
                            + Duke.tasks.getSize() + " tasks in the list.\n");

                    // Update save file.
                    Duke.storage.write(event);

                    break;
                case "delete":
                    // Retrieve description from user input.
                    description = sc.nextLine();

                    // Check if given description has sufficient details.
                    if (description.isBlank()) {
                        throw new DukeException("My apologies," +
                                " task number to be deleted not found :(");
                    }

                    // Parse given int in description to int type.
                    int delete = Integer.parseInt(description.trim());

                    // Check if given int is greater than number of tasks in list.
                    if (delete > Duke.tasks.getSize()) {
                        throw new DukeException("My apologies," +
                                " task number to be deleted not found :(");
                    }

                    // Retrieve details on Task object to be deleted.
                    Task removed = Duke.tasks.getTask(delete - 1);

                    // Remove task from list.
                    Duke.tasks.deleteTask(delete - 1);

                    // Output success message.
                    this.print("\n  Noted. Task " + delete + " has been removed:\n  "
                            + removed + "\n  There are currently " + Duke.tasks.getSize()
                            + " tasks in the list.\n");

                    // Update save file.
                    Duke.storage.overwrite((ArrayList<Task>) Duke.tasks.getList());

                    break;
                    case "find":
                        // Retrieve description from user input.
                        description = sc.nextLine();

                        // Check if description has sufficient details.
                        if (description.isEmpty()) {
                            throw new DukeException("My apologies, "
                                    + "search parameter seems to be empty.\n  "
                                    + "Please check and try again. ");
                        }

                        // Retrieve Task objects with description matching user's description.
                        List<Task> results = Duke.storage.find(description.trim());

                        //Initialise StringBuilder to store results.
                        StringBuilder res = new StringBuilder();
                        res.append("\n The matching Tasks in the list are: \n");

                        // Iterate through results list and append Task to res.
                        for (int i = 1; i <= results.size(); i++) {
                            res.append("  ").append(i).append(". ")
                                    .append(results.get(i - 1)).append("\n");
                        }

                        // Output results to user.
                        this.print(res.toString());

                        break;
                    default:
                        throw new DukeException("My apologies," +
                            " I do not recognise the command. :(");
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

    /**
     * Fetches Duke's response to command inputted by user.
     * @param input User input read by Duke.
     * @return Duke's Response
     */
    protected String getResponse(String input) {
        String taskAdd = "Noted. The following task has been added:\n   ";
        String command = input.split(" ")[0];
        String[] split = input.split(" "), dateTime;

        // Check if user is done with Duke
        try {
            switch (command) {
                case "list":
                    // Check if list of tasks is empty
                    if (Duke.tasks.isEmpty()) {
                        return this.format(
                                "\n  There are currently no items in your list. \n");
                    }

                    // Initialise String builder to hold list of Task objects.
                    StringBuilder sb = new StringBuilder();
                    // Append each Task object to string builder.
                    sb.append("\n  " + "The items in your list are: \n");
                    for (int i = 1; i <= Duke.tasks.getSize(); i++) {
                        sb.append("  ").append(i).append(". ")
                                .append(Duke.tasks.getTask(i - 1)).append("\n");
                    }

                    // Update save file.
                    Duke.storage.overwrite((ArrayList<Task>) Duke.tasks.getList());

                    // Print out list of tasks to user.
                    return this.format(sb.toString());
                case "done":
                    // Retrieve Task object at user inputted index.
                    Task task = Duke.tasks.getTask(Integer.parseInt(input.substring(5)
                            .trim()));

                    // Mark retrieved Task object as done.
                    task.done();

                    // Update save file.
                    Duke.storage.overwrite((ArrayList<Task>) Duke.tasks.getList());

                    // Output success message
                    return this.format("\n  "
                            + "Well Done! The task has been marked as done." + "\n  "
                            + task + "\n");
                case "todo":
                    // Check if description is valid.
                    if (input.length() < 6) {
                        throw new DukeException("My apologies, " +
                                "description of todo is empty. :(");
                    }

                    // Create new Task of type todo.
                    Task todo = new Task(Duke.Tasks.todo, input.substring(5));

                    // Add new Task object to list.
                    Duke.tasks.addTask(todo);

                    // Update safe file.
                    Duke.storage.write(todo);

                    // Output success message.
                    return this.format("\n  " + taskAdd + todo
                            + "\n  There are currently " + Duke.tasks.getSize()
                            + " tasks in the list.\n");
                case "deadline":

                    split = input.split("/by ");

                    // Check if sufficient details were provided in description.
                    if (split.length < 2) {
                        throw new DukeException("My apologies, " +
                                "deadline description has insufficient details :(");
                    }

                    // Parse date details into LocalDate format.
                    dateTime = split[1].split(" ");
                    String taskBy = Parser.parseDate(dateTime[0]);

                    // Create new Task object of task type deadline.
                    Task deadline = new Task(Duke.Tasks.deadline, split[0].substring(9),
                            taskBy + dateTime[1]);

                    // Add deadline to list.
                    Duke.tasks.addTask(deadline);

                    // Update save file.
                    Duke.storage.write(deadline);

                    // Output success message.
                    return this.format("\n  " + taskAdd + deadline
                            + "\n  There are currently " + Duke.tasks.getSize()
                            + " tasks in the list.\n");
                case "event":
                    split = input.split("/at ");

                    // Check if given description has sufficient details.
                    if (split.length < 2) {
                        throw new DukeException("My apologies, " +
                                "event description has insufficient details. :(");
                    }

                    // Parse date details into LocalDate format.
                    dateTime = split[1].split(" ");
                    String taskAt = Parser.parseDate(dateTime[0]);

                    // Create new Task object of event task type.
                    Task event = new Task(Duke.Tasks.event, split[0].substring(6),
                            taskAt + dateTime[1]);

                    // Add event to list.
                    Duke.tasks.addTask(event);

                    // Update save file.
                    Duke.storage.write(event);

                    // Output success message.
                    return this.format("\n  " + taskAdd + event
                            + "\n  There are currently " + Duke.tasks.getSize()
                            + " tasks in the list.\n");

                case "delete":
                    // Check if given description has sufficient details.
                    if (split.length < 2) {
                        throw new DukeException("My apologies," +
                                " task number to be deleted not found :(");
                    }

                    // Parse given int in description to int type.
                    int toDelete = Integer.parseInt(split[1].trim());

                    // Check if given int is greater than number of tasks in list.
                    if (toDelete > Duke.tasks.getSize()) {
                        throw new DukeException("My apologies," +
                                " task number to be deleted not found :(");
                    }

                    // Retrieve details on Task object to be deleted.
                    Task removed = Duke.tasks.getTask(toDelete - 1);

                    // Remove task from list.
                    Duke.tasks.deleteTask(toDelete - 1);

                    // Update save file.
                    Duke.storage.overwrite((ArrayList<Task>) Duke.tasks.getList());

                    // Output success message.
                    return this.format("\n  Noted. Task " + toDelete
                            + " has been removed:\n  " + removed
                            + "\n  There are currently " + Duke.tasks.getSize()
                            + " tasks in the list.\n");
                case "find":
                    // Check if description has sufficient details.
                    if (split.length < 2) {
                        throw new DukeException("My apologies, "
                                + "search parameter seems to be empty.\n  "
                                + "Please check and try again. ");
                    }

                    // Retrieve Task objects with description matching user's description.
                    List<Task> results = Duke.storage.find(input.substring(5));

                    //Initialise StringBuilder to store results.
                    StringBuilder res = new StringBuilder();
                    res.append("\n The matching Tasks in the list are: \n");

                    // Iterate through results list and append Task to res.
                    for (int i = 1; i <= results.size(); i++) {
                        res.append("  ").append(i).append(". ")
                                .append(results.get(i - 1)).append("\n");
                    }

                    // Output results to user.
                    return this.format(res.toString());
                case "bye":
                    return this.format("\n Goodbye. I hope I was useful. "
                            + "See you again.\n");
                default:
                    throw new DukeException("My apologies," +
                            " I do not recognise the command. :(");
            }
        } catch (DukeException e) {
            return this.format("\n  " + e.getMessage() + "\n");
        }
    }
}
