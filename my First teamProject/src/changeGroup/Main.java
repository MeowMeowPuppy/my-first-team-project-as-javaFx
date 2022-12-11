package changeGroup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("changeGroupForm.fxml"));

		Parent loginForm = loader.load();

		Scene scene = new Scene(loginForm);
		primaryStage.setTitle("프로젝트이름");
		primaryStage.setScene(scene);
		primaryStage.show();
}
 public static void main(String[] args) {
	launch(args);
}



	}


