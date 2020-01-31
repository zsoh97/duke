
public class Duke {
    protected static Storage storage;
    protected static TaskList tasks;
    private Ui ui = new Ui();

    public Duke(String filePath) {
        // Prints welcome message
        this.ui.showWelcome();

        // Constructs new storage object
        storage = new Storage(filePath);

        // Load list of tasks from save file
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    public void run() {
        ui.start();
        }

    public static void main(String[] args) {
        // Creates new Duke object and initiates Duke.
        (new Duke("data/duke.txt")).run();
    }

    enum Tasks {
        todo,
        deadline,
        event
    }
}
