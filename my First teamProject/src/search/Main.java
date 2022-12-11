package search;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.Open;
import more.MoreController;

public class Main extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchForm.fxml"));
		Parent searchForm =  loader.load();
		
		
		ComboBox<String> categoryBox = (ComboBox<String>) searchForm.lookup("#category");
		categoryBox.getItems().addAll("전체", "운동", "예술", "오락", "공부/어학", "인맥", "봉사", "요리", "캠핑/차", "자유 주제");
		
		
		Open open = new Open();
		open.setPrimaryStage(primaryStage);
		open.setForm(searchForm);
		SearchController searchCon = loader.getController();
		searchCon.setOpen(open);
		
		
		Scene scene = new Scene(searchForm);
		Font.loadFont(getClass().getResource("./a오동통OL.ttf").toString(),16);
		scene.getStylesheets().add("main/application.css");
		primaryStage.setTitle("검색");
		primaryStage.setScene(scene);
		primaryStage.show();
	}	
}
