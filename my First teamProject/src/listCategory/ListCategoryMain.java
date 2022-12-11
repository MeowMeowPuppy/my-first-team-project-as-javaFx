package listCategory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.Open;

public class ListCategoryMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCategoryForm.fxml"));
		
		Open open = new Open();
		open.setPrimaryStage(primaryStage);
		
		Parent listCategory = loader.load();
		ListCategoryController listCon = loader.getController();
		listCon.setOpen(open);
		
		Scene scene = new Scene(listCategory);
		primaryStage.getIcons().add(new Image("/listCategoryForm_0829/img/DAMO - remove32 32.png"));
		primaryStage.setTitle("카테고리게시물");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
