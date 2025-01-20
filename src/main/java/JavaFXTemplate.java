import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;




public class JavaFXTemplate extends Application {

	@Override
	public void start(Stage primaryStage){
		try {
			//read file fxml and draw inetrface
			Parent root = FXMLLoader.load(getClass().getResource("/FXML/homeScene.fxml"));
			primaryStage.setTitle("Three Card Poker");
			Scene homeScene = new Scene(root, 1250, 1500);
			homeScene.getStylesheets().add("/styles/styleHome.css");

			//show
			primaryStage.setScene(homeScene);
			primaryStage.show();

		} catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);


	}

	
	
}
