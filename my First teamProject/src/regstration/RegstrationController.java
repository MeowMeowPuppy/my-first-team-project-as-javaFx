package regstration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import main.Open;

public class RegstrationController implements Initializable {
	@FXML
	private TextField id;
	@FXML
	private PasswordField pw;
	@FXML
	private PasswordField confirm;
	@FXML
	private TextField name;
	@FXML
	private TextField phoneNumber;
	@FXML
	private RadioButton male;
	@FXML
	private RadioButton female;
	@FXML
	private ToggleGroup gender;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private Button cancelBtn;
	@FXML
	private Button regBtn;
	
	private Open open;

	private RegstrationService regstrationService;

	public RegstrationController() {
		regstrationService = new RegstrationService();
	}

	public void setOpen(Open open) {
		this.open = open;
	}

	public void cancel() {
		open.openLogin();
	}

	public void reg() {
		// 데이터 검수
		if (regstrationService.check(id, pw, confirm, name, phoneNumber, male, female)) {
			return;
		}
		
		// 입력받은 전화번호를 정리하여 다시 저장
		String organizedNum = regstrationService.organizedNum(phoneNumber);
		phoneNumber.setText(organizedNum);

		// 위의 check를 통해 데이터 검증 후 DB에 저장
		regstrationService.insert(id, pw, confirm, name, phoneNumber, male, female);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		id.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				reg();
			}
		});
		pw.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				reg();
			}
		});
		confirm.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				reg();
			}
		});
		name.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				reg();
			}
		});
		phoneNumber.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				reg();
			}
		});
		male.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				male.setSelected(true);
			}
		});
		female.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				female.setSelected(true);
			}
		});
		regBtn.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				reg();
			}
		});
		cancelBtn.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				open.openLogin();
			}
		});
		
		regBtn.setOnMouseEntered(event -> {
			regBtn.setStyle("-fx-background-color:linear-gradient(to top,skyblue,#AFE0FF); -fx-background-radius:30");
		});
		regBtn.setOnMouseExited(event -> {
			regBtn.setStyle("-fx-background-color:skyblue; -fx-background-radius:30");
		});
		cancelBtn.setOnMouseEntered(event -> {
			cancelBtn.setStyle("-fx-background-color:linear-gradient(to top,skyblue,#AFE0FF); -fx-background-radius:30");
		});
		cancelBtn.setOnMouseExited(event -> {
			cancelBtn.setStyle("-fx-background-color:skyblue; -fx-background-radius:30");
		});
		
	}
}
