package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainForm.fxml"));
		Parent form = loader.load();
		
		Open open = new Open();
		open.setPrimaryStage(primaryStage);
		open.setForm(form);
		MainController con = loader.getController();
		con.setOpen(open);
		Scene scene = new Scene(form);
		Font.loadFont(getClass().getResource("./a오동통OL.ttf").toString(),16);
		scene.getStylesheets().add("main/application.css");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
