public class Task {

    private boolean done;
    private String taskName;

    public Task(String taskName){
        this.taskName = taskName;
        this.done = false;
    }

    public void done(){
        this.done = true;
    }

    @Override
    public String toString() {
        if(this.done){
            return "[✓] " + taskName;
        }else{
            return "[✗] " + taskName;
        }
    }
}
