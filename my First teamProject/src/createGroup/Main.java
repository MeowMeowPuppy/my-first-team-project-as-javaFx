package createGroup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import main.Open;

public class Main extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateGroupForm.fxml"));
		Parent createGroupForm =  loader.load();
		
		Open open = new Open();
		CreateGroupController con = loader.getController();
		con.setOpen(open);
		@SuppressWarnings("unchecked")
		ComboBox<String> categoryBox = (ComboBox<String>) createGroupForm.lookup("#category");
		categoryBox.getItems().addAll("운동", "예술", "오락", "공부/어학", "인맥", "봉사", "요리", "캠핑/차", "자유 주제");
		
		Scene scene = new Scene(createGroupForm);
		primaryStage.setTitle("모입 생성");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
