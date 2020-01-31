
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui = new Ui();

    public Duke(String filePath) {
        this.ui.showWelcome();
        this.storage = new Storage(filePath);

        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }

    }

    public void run() {
        ui.start(tasks, storage);
        }

    public static void main(String[] args) {
        (new Duke("data/duke.txt")).run();
    }

    enum Tasks {
        todo,
        deadline,
        event
    }
}
