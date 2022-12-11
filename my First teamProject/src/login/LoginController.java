package login;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import main.Open;

public class LoginController implements Initializable {
	@FXML
	private TextField id;
	@FXML
	private PasswordField pw;
	@FXML
	private VBox vbox;
	@FXML
	private Button loginBtn;
	@FXML
	private Button regBtn;
	@FXML
	private ImageView damo;

	private LoginService loginService;
	private Open open;
	private LoginDTO loginDto;
	private int count;
	
	public void setOpen(Open open) {
		this.open = open;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginDto = new LoginDTO();
		loginService = new LoginService();
		id.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				loginProc();
			}
		});
		pw.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				loginProc();
			}
		});
		loginBtn.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				loginProc();
			}
		});
		regBtn.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				open.openRegstration();
			}
		});

		loginBtn.setOnMouseEntered(event -> {
			loginBtn.setStyle("-fx-background-color:linear-gradient(to top,skyblue,#AFE0FF); -fx-background-radius:30");
		});
		loginBtn.setOnMouseExited(event -> {
			loginBtn.setStyle("-fx-background-color:skyblue; -fx-background-radius:30");
		});
		regBtn.setOnMouseEntered(event -> {
			regBtn.setStyle("-fx-background-color:linear-gradient(to top,skyblue,#AFE0FF); -fx-background-radius:30");
		});
		regBtn.setOnMouseExited(event -> {
			regBtn.setStyle("-fx-background-color:skyblue; -fx-background-radius:30");
		});
		
		damo.setOnMouseClicked(event -> {
			++count;
			if(count>=10) {
				open.openManagementLogin();
				count=0;
				return;
			}
		});
	}

	// 로그인 버튼 클릭 시 동작하는 메서드
	public void loginProc() {
		// 로그인 성공 시
		loginDto = loginService.loginProc(id, pw);

		try {
			if ((loginDto != null)) {
				open.setLoginDto(loginDto);
				open.openMain();
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원 가입 버튼 클릭 시 동작하는 메서드
	public void regProc() {
		open.openRegstration();
	}

	// 아이디 찾기 버튼 클릭시 작동
	public void findId() {
		open.openFindId();
	}
	// 비밀번호 찾기 버튼 클릭시 작동
	public void findPw() {
		open.openFindPw();
	}
	
	//
	public void openManagementLogin() {
		if(count>=10) {
			open.openManagementLogin();
			count=0;
			return;
		}
	}

}
