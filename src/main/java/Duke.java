/**
 * Main class for Duke.
 * Class contains constructor for Duke object.
 */
public class Duke {

    /**
     * Duke object contains parameters that
     */
    protected static Storage storage;
    protected static TaskList tasks;
    private Ui ui = new Ui();

    public Duke(String filePath) {
        this.ui.showWelcome();
        storage = new Storage(filePath);

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
        (new Duke("data/duke.txt")).run();
    }

    enum Tasks {
        todo,
        deadline,
        event
    }
}
