package myGroup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Open;

public class Main extends Application {


	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MyGroupForm.fxml"));

			Parent form = loader.load();
			
			Open open = new Open();
			open.setPrimaryStage(primaryStage);
			open.setForm(form);
			MyGroupController myGroupCon = loader.getController();
  		    myGroupCon.setOpen(open);
  		    
			Scene scene = new Scene(form);
//			primaryStage.setTitle("프로젝트이름");
			primaryStage.setScene(scene);
			primaryStage.show();
	}
	 public static void main(String[] args) {
		launch(args);
	}

}
