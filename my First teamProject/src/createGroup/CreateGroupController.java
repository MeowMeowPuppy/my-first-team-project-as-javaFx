package createGroup;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import login.LoginDTO;
import main.Open;

public class CreateGroupController implements Initializable {
	@FXML private TextField groupName;
	@FXML private TextField groupMember;
	@FXML private ComboBox<String> category;
	@FXML private TextArea content;
	@FXML private Button create;
	@FXML private Button cancel;
	private Open open;
	private CreateGroupService createGroupService ;
	private LoginDTO loginDto;
	
	public void setLoginDto(LoginDTO loginDto) {
		this.loginDto = loginDto;
	}
	
	
	public void setOpen(Open open) {
		this.open = open;
	}
		

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		createGroupService = new CreateGroupService();
		
		
		create.setOnMouseEntered(event -> {
			create.setStyle("-fx-background-color:linear-gradient(to top,skyblue,#AFE0FF); -fx-background-radius:30");
		});
		create.setOnMouseExited(event -> {
			create.setStyle("-fx-background-color:skyblue; -fx-background-radius:30");
		});
		cancel.setOnMouseEntered(event -> {
			cancel.setStyle("-fx-background-color:linear-gradient(to top,skyblue,#AFE0FF); -fx-background-radius:30");
		});
		cancel.setOnMouseExited(event -> {
			cancel.setStyle("-fx-background-color:skyblue; -fx-background-radius:30");
		});
		
		groupName.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				groupMember.requestFocus();
			}
		});
		groupMember.setOnKeyPressed(event -> {
			if(event.getCode()==KeyCode.ENTER) {
				content.requestFocus();
			}
		});
		
		
	}
	
	//생성 버튼 클릭시 동작하는 메서드
	public void createProc() {
		String id = loginDto.getId();
		boolean check = createGroupService.createProc(id, groupName, groupMember, category, content);
		if(check) {
			return;
		}
		open.openBackStage();
	}
	
	//취소 버튼 클릭시 동작하는 메서드
	public void cancellProc() {
		open.openBackStage();
	}
}
