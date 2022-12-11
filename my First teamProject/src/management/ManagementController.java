package management;

import javafx.fxml.FXML;
import main.Open;

public class ManagementController {

	private Open open;

	public void setOpen(Open open) {
		this.open = open;
	}

	public void showMember() {
		open.openShowMember();
	}

	public void showExitReason() {
		open.openExitReason();
	}

	public void cancel() {
		open.openManagementLogin();
	}
}
