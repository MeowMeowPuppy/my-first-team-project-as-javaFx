package changePassword;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import login.LoginDAO;
import login.LoginDTO;
import main.Open;

public class ChangePasswordController implements Initializable{
	@FXML
	private PasswordField currentPw;
	@FXML
	private PasswordField changePw;
	@FXML
	private Button changeBtn;
	@FXML
	private Button cancelBtn;
	public void setLoginDto(LoginDTO loginDto) {
	}
	
	private Open open;
	private ChangePasswordService changePasswordService;

	public ChangePasswordController() {
		changePasswordService = new ChangePasswordService();
	}

	public void setOpen(Open open) {
		this.open = open;
	}

	public void changePw() {

		int check = 0;
		LoginDTO loginDto = open.getLoginDto();
		check = changePasswordService.check(loginDto, currentPw, changePw);
		if (check == 1) {
			return;
		}

		changePasswordService.change(loginDto, changePw);
		LoginDAO loginDao = new LoginDAO();
		loginDao.setLogout(loginDto.getId());
		open.openLogin();
	}

	public void cancel() {
		open.openMore();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		currentPw.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				changePw();
			}
		});
		
		changePw.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				changePw();
			}
		});
		changeBtn.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				changePw();
			}
		});
		cancelBtn.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				cancel();
			}
		});
		
		
		
		// 아래는 버튼에 마우스 올리고 떼고 할때의 엑션
		changeBtn.setOnMouseEntered(event -> {
			changeBtn.setStyle("-fx-background-color:linear-gradient(to top,skyblue,#AFE0FF); -fx-background-radius:30");
		});
		changeBtn.setOnMouseExited(event -> {
			changeBtn.setStyle("-fx-background-color:skyblue; -fx-background-radius:30");
		});
		cancelBtn.setOnMouseEntered(event -> {
			cancelBtn.setStyle("-fx-background-color:linear-gradient(to top,skyblue,#AFE0FF); -fx-background-radius:30");
		});
		cancelBtn.setOnMouseExited(event -> {
			cancelBtn.setStyle("-fx-background-color:skyblue; -fx-background-radius:30");
		});
	}

}
