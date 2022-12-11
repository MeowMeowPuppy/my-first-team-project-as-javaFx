package breakAway;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import main.Open;

public class BreakAwayController implements Initializable {
	@FXML
	private PasswordField pw;
	@FXML
	private Button breakAwayBtn;
	@FXML
	private Button cancelBtn;
	@FXML
	private TextField name;
	@FXML
	private TextArea reason;

	private Open open;
	private BreakAwayService breakAwayService;

	public BreakAwayController() {
		breakAwayService = new BreakAwayService();
	}

	public void setOpen(Open open) {
		this.open = open;
	}

	public void breakAway() {
		String id = open.getLoginDto().getId();
		int check = breakAwayService.breakAway(id, pw);

		if (check == 1) {
			return;
		}
		
		if(reason.getText().isEmpty()==false) {
			breakAwayService.saveReason(name, reason);
		}
		open.openLogin();
	}

	public void cancel() {
		open.openMore();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pw.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				breakAway();
			}
		});
		breakAwayBtn.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				breakAway();
			}
		});
		cancelBtn.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				cancel();
			}
		});

		breakAwayBtn.setOnMouseEntered(event -> {
			breakAwayBtn
					.setStyle("-fx-background-color:linear-gradient(to top,skyblue,#AFE0FF); -fx-background-radius:30");
		});
		breakAwayBtn.setOnMouseExited(event -> {
			breakAwayBtn.setStyle("-fx-background-color:skyblue; -fx-background-radius:30");
		});
		cancelBtn.setOnMouseEntered(event -> {
			cancelBtn
					.setStyle("-fx-background-color:linear-gradient(to top,skyblue,#AFE0FF); -fx-background-radius:30");
		});
		cancelBtn.setOnMouseExited(event -> {
			cancelBtn.setStyle("-fx-background-color:skyblue; -fx-background-radius:30");
		});

	}
}
