package findId;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import main.Open;
import regstration.RegstrationDTO;

public class FindIdController implements Initializable {
	@FXML
	private TextField name;
	@FXML
	private TextField phoneNumber;
	@FXML
	private Button find;
	@FXML
	private Button cancel;

	private Open open;
	private FindIdService findIdService;

	public void setOpen(Open open) {
		this.open = open;
	}

	public void find() {
		RegstrationDTO check =	findIdService.find(name,phoneNumber);
		if(check==null) {
			name.clear();
			phoneNumber.clear();
			return;
		}
		open.openLogin();
	}

	public void cancel() {
		open.openLogin();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		findIdService = new FindIdService();
		
		name.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				find();
			}
		});
		phoneNumber.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				find();
			}
		});
		find.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				find();
			}
		});
		cancel.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				cancel();
			}
		});
		
		
		find.setOnMouseEntered(event -> {
			find.setStyle("-fx-background-color:linear-gradient(to top,skyblue,#AFE0FF); -fx-background-radius:30");
		});
		find.setOnMouseExited(event -> {
			find.setStyle("-fx-background-color:skyblue; -fx-background-radius:30");
		});
		cancel.setOnMouseEntered(event -> {
			cancel.setStyle("-fx-background-color:linear-gradient(to top,skyblue,#AFE0FF); -fx-background-radius:30");
		});
		cancel.setOnMouseExited(event -> {
			cancel.setStyle("-fx-background-color:skyblue; -fx-background-radius:30");
		});
		
	}
}
