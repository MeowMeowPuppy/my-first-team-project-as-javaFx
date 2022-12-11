package more;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.Open;

public class Main extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MoreForm.fxml"));
		Parent form = loader.load();
		
		Open open = new Open();
		open.setPrimaryStage(primaryStage);
		open.setForm(form);
		MoreController Con = loader.getController();
		Con.setOpen(open);
		
		Scene scene = new Scene(form);
		Font.loadFont(getClass().getResource("./a오동통OL.ttf").toString(),16);
		scene.getStylesheets().add("main/application.css");
		primaryStage.setScene(scene);	
		primaryStage.show();
		
	}

}
