package main;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CommonService {
	
	// Alert으로 메시지 출력하는 메서드
	public static void msg(String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(content);
		alert.show();
	}
	
	// 화면 종료
	public static void windowsClose(Parent form){
		Stage stage = (Stage)form.getScene().getWindow();
		stage.close();
	}
}
