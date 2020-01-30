import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class Task {

    private boolean done;
    private String taskName, date;
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
        if(this.byAt.length == 2) {
            this.lD = LocalDate.parse(this.byAt[0]);
        }else{
            this.date = byAt;
        }
    }

    public Task(Duke.Tasks taskType, String taskName){
        this.taskName = taskName;
        this.taskType = taskType;
    }

    public Task(String taskName){
        this.taskName = taskName;
        this.done = false;
    }

    public String write() {
        switch(this.taskType) {
            case todo:
                if (this.done) {
                    return "T / 1 / " + this.taskName;
                }
                return "T / 0 / " + this.taskName;
            case deadline:
                if (this.done) {
                    return "D / 1 / " + taskName + " / " + this.date();
                }
                return "D / 0 / " + taskName + " / " + this.date();
            case event:
                if (this.done) {
                    return "E / 1 / " + taskName + " / " + this.date();
                }
                return "E / 0 / " + taskName + " / " + this.date();
            default:
                return "";
        }
    }

    private String date(){
        if (this.date == null) {
            int day = this.lD.getDayOfMonth();
            Month month = this.lD.getMonth();
            int year = this.lD.getYear();
            return month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " + day + " " + year + " " + this.byAt[1];
        }else{
            return this.date;
        }
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
                    return "[D][✓] " + taskName + " (by: " + this.date() + ")";
                case event:
                    return "[E][✓] " + taskName + " (at: " + this.date() + ")";
                default:
                    return "[✓]" + taskName;
            }
        } else {
            switch (this.taskType) {
                case todo:
                    return "[T][✗] " + taskName;
                case deadline:
                    return "[D][✗] " + taskName + " (by: " + this.date() + ")";
                case event:
                    return "[E][✗] " + taskName + " (at: " + this.date() + ")";
                default:
                    return "[✗]" + taskName;
            }
        }
    }
}
