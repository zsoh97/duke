public class Task {

    private boolean done;
    private String taskName, byAt;
    private Duke.Tasks taskType;

//    public Task(){
//
//    }

    public Task(Duke.Tasks taskType, String taskName, String byAt){
        this.taskName = taskName;
        this.taskType = taskType;
        this.byAt = byAt;
    }

    public Task(Duke.Tasks taskType, String taskName){
        this.taskName = taskName;
        this.taskType = taskType;
    }

    public Task(String taskName){
        this.taskName = taskName;
        this.done = false;
    }

    public void done(){
        this.done = true;
    }

//    public String toString(){
//        if(this.done){
//            return "[✓] " + taskName;
//        }else{
//            return "[✗] \" + taskName;";
//        }
//    }
//

    @Override
    public String toString() {
        if (this.done) {
            switch (this.taskType) {
                case todo:
                    return "[T][✓] " + taskName;
                case deadline:
                    return "[D][✓] " + taskName + " (by: " + byAt + ")";
                case event:
                    return "[E][✓] " + taskName + " (at: " + byAt + ")";
                default:
                    return "[✓]" + taskName;
            }
        } else {
            switch (this.taskType) {
                case todo:
                    return "[T][✗] " + taskName;
                case deadline:
                    return "[D][✗] " + taskName + " (by: " + byAt + ")";
                case event:
                    return "[E][✗] " + taskName + " (at: " + byAt + ")";
                default:
                    return "[✗]" + taskName;
            }
        }
    }
}
