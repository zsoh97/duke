import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class Task {

    private boolean isDone;
    private String taskName;
    private LocalDate lD;
    private String[] byAt;
    private Duke.Tasks taskType;


    public Task(Duke.Tasks taskType, String taskName, String byAt){
        this.taskName = taskName;
        this.taskType = taskType;
        this.byAt = byAt.split(" ");
        this.lD = LocalDate.parse(this.byAt[0]);
    }

    public Task(Duke.Tasks taskType, String taskName){
        this.taskName = taskName;
        this.taskType = taskType;
    }

    public Task(String taskName){
        this.taskName = taskName;
        this.isDone = false;
    }

    public String write() {
        switch(this.taskType) {
            case todo:
                // Check if Task has been marked as done.
                if (this.isDone) {
                    return "T / 1 / " + this.taskName;
                }

                return "T / 0 / " + this.taskName;
            case deadline:
                //Check if Task has been marked as done.
                if (this.isDone) {
                    return "D / 1 / " + taskName + " / " + this.date();
                }

                return "D / 0 / " + taskName + " / " + this.date();
            case event:
                // Check if TAsk has been marked as done.
                if (this.isDone) {
                    return "E / 1 / " + taskName + " / " + this.date();
                }

                return "E / 0 / " + taskName + " / " + this.date();
            default:
                return "";
        }
    }

    private String date(){
        int day = this.lD.getDayOfMonth();
        Month month = this.lD.getMonth();
        int year = this.lD.getYear();
        return month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " + day + " " + year;
    }

    public void done(){
        this.isDone = true;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            switch (this.taskType) {
                case todo:
                    return "[T][✓] " + taskName;
                case deadline:
                    return "[D][✓] " + taskName + " (by: " + this.date()+ " " + this.byAt[1]
                            + ")";
                case event:
                    return "[E][✓] " + taskName + " (at: " + this.date() + " " + this.byAt[1]
                            + ")";
                default:
                    return "[✓]" + taskName;
            }
        } else {
            switch (this.taskType) {
                case todo:
                    return "[T][✗] " + taskName;
                case deadline:
                    return "[D][✗] " + taskName + " (by: " + this.date() + " " + this.byAt[1]
                            + ")";
                case event:
                    return "[E][✗] " + taskName + " (at: " + this.date() + " " + this.byAt[1]
                            + ")";
                default:
                    return "[✗]" + taskName;
            }
        }
    }
}
