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

    public String write(){
        switch (this.taskType){
            case todo:
                if(this.done){
                    return "T / 1 / " + this.taskName;
                }else{
                    return "T / 0 / " + this.taskName;
                }
            case deadline:
                if(this.done){
                    return "D / 1 / " + this.taskName + " / " + this.byAt;
                }else{
                    return "D /  / " + this.taskName + " / " + this.byAt;
                }
            case event:
                if(this.done){
                    return "E / 1 / " + this.taskName + " / " + this.byAt;
                }else{
                    return "E / 0 / " + this.taskName + " / " + this.byAt;
                }
            default:
                return"";
        }
    }

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
