package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public abstract class Command {
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
