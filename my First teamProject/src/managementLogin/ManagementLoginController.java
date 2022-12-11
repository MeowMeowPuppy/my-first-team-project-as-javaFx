package managementLogin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import main.Open;

public class ManagementLoginController implements Initializable {

	private Open open;

	public void setOpen(Open open) {
		this.open = open;
	}

	@FXML
	Button login;
	@FXML
	Button cancel;
	@FXML
	TextField id;
	@FXML
	PasswordField pw;

	private ManagementLoginService service;

	public ManagementLoginController() {
		service = new ManagementLoginService();

	}

	public void login() {
		if (service.login(id, pw)) {
			return;
		}
		open.openManagement();
	}

	public void cancel() {
		open.openLogin();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		id.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				login();
			}
		});
		pw.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				login();
			}
		});
		login.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				login();
			}
		});
		cancel.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				cancel();
			}
		});
		
	}

}
