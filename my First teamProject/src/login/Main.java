package login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.Open;

public class Main extends Application{
	public static void main(String[] args) {
	 launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginForm.fxml"));
		Parent loginForm = loader.load();
		
		Open open = new Open();
		LoginController con = loader.getController();
		con.setOpen(open);
		// 현재 화면의 폼, 스테이지 저장
		open.setForm(loginForm);
		open.setPrimaryStage(primaryStage);
		
		Scene scene = new Scene(loginForm);
		scene.getStylesheets().add("main/application.css");
		primaryStage.getIcons().add(new Image("/more/img/DAMO__3_-removebg-preview (1).png"));
		primaryStage.setTitle("로그인 화면");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
