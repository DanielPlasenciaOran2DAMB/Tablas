package dad.tablas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	FicherosController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new FicherosController();

		Scene scene = new Scene(controller.getView());

		primaryStage.setTitle("Tablas");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
