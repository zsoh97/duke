/**
 * Task is the main object used to maintain the list of tasks from the user by Duke.
 * This class provides multiple implementations of Task object.
 */
public class Task {

    /**
     * All Task objects will have the boolean attribute determining if the task has been
     * done, the task details, and the type of Task. Task objects with deadline and event
     * types will have additional local date attributes and additional details when
     * applicable.
     */
    private boolean isDone;
    private String taskDetails;
    private String additionalDetails;
    private Duke.Tasks taskType;

    /**
     * Constructor to construct deadline and event task type Task objects.
     * @param taskType Type of Task
     * @param taskDetails Details of task
     * @param additionalDetails Additional details of the task, if applicable.
     */
    public Task(Duke.Tasks taskType, String taskDetails, String additionalDetails) {
        this.taskDetails = taskDetails;
        this.taskType = taskType;
        this.additionalDetails = additionalDetails;
        this.isDone = false;
    }

    /**
     * Constructor to construct todo Task object.
     * @param taskType Type of task
     * @param taskDetails Details of task
     */
    public Task(Duke.Tasks taskType, String taskDetails) {
        this.isDone = false;
        this.taskDetails = taskDetails;
        this.taskType = taskType;
    }

    /**
     * Returns details of Task object formatted for storing in hard drive.
     * @return formatted string of task object.
     */
    public String writeToFile() {
        switch (this.taskType) {
        case todo:
            assert !this.taskDetails.isEmpty() : "Task details should not be empty.";
            // Check if Task has been marked as done.
            if (this.isDone) {
                return "T / 1 / " + this.taskDetails;
            }
            return "T / 0 / " + this.taskDetails;

        case deadline:
            assert !this.taskDetails.isEmpty() : "Task details should not be empty.";
            assert !additionalDetails.isEmpty() : "Additional details are required for deadline Tasks.";
            //Check if Task has been marked as done.
            if (this.isDone) {
                return "D / 1 / " + taskDetails + " / " + additionalDetails;
            }
            return "D / 0 / " + taskDetails + " / "  + additionalDetails;

        case event:
            assert !this.taskDetails.isEmpty() : "Task details should not be empty.";
            assert !additionalDetails.isEmpty() : "Additional details are required for event Tasks.";
            // Check if Task has been marked as done.
            if (this.isDone) {
                return "E / 1 / " + taskDetails + " / " + additionalDetails;
            }
            return "E / 0 / " + taskDetails + " / "  + additionalDetails;
        default:
            return "";
        }
    }

    /**
     * Changes status of Task object to done.
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * Gets description of Task object.
     * @return String containing task description.
     */
    public String getDescription() {
        return this.taskDetails;
    }

    protected boolean getIsDone(){
        return this.isDone;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            switch (this.taskType) {
            case todo:
                return "[T][\u2713] " + taskDetails;
            case deadline:
                return "[D][\u2713] " + taskDetails + " (by: " + this.additionalDetails + ")";
            case event:
                return "[E][\u2713] " + taskDetails + " (at: "  + this.additionalDetails + ")";
            default:
                return "[\u2713]" + taskDetails;
            }
        } else {
            switch (this.taskType) {
            case todo:
                return "[T][\u274C] " + taskDetails;
            case deadline:
                return "[D][\u274C] " + taskDetails + " (by: " + this.additionalDetails + ")";
            case event:
                return "[E][\u274C] " + taskDetails + " (at: " + this.additionalDetails + ")";
            default:
                return "[\u274C]" + taskDetails;
            }
        }
    }
}
