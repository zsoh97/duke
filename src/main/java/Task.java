import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class Task {

    private boolean done;
    private String taskName;
    private LocalDate lD;
    private String[] byAt;
    private Duke.Tasks taskType;

//    public Task(){
//
//    }

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
        this.done = false;
    }

    private String date(){
        int day = this.lD.getDayOfMonth();
        Month month = this.lD.getMonth();
        int year = this.lD.getYear();
        return month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " + day + " " + year;
    }

    public void done(){
        this.done = true;
    }

    @Override
    public String toString() {
        if (this.done) {
            switch (this.taskType) {
                case todo:
                    return "[T][✓] " + taskName;
                case deadline:
                    return "[D][✓] " + taskName + " (by: " + this.date()+ " " + this.byAt[1] + ")";
                case event:
                    return "[E][✓] " + taskName + " (at: " + this.date() + " " + this.byAt[1] + ")";
                default:
                    return "[✓]" + taskName;
            }
        } else {
            switch (this.taskType) {
                case todo:
                    return "[T][✗] " + taskName;
                case deadline:
                    return "[D][✗] " + taskName + " (by: " + this.date() + " " + this.byAt[1] + ")";
                case event:
                    return "[E][✗] " + taskName + " (at: " + this.date() + " " + this.byAt[1] + ")";
                default:
                    return "[✗]" + taskName;
            }
        }
    }
}
