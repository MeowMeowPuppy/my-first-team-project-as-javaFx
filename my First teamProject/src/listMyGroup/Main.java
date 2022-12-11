package listMyGroup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MyGroupListForm.fxml"));

		Parent Form = loader.load();

		Scene scene = new Scene(Form);
		primaryStage.setTitle("프로젝트이름");
		primaryStage.setScene(scene);
		primaryStage.show();
}
 public static void main(String[] args) {
	launch(args);
}

	}


