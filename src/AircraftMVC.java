import javafx.application.Application;
import javafx.stage.Stage;

public class AircraftMVC extends Application {
	private AircraftView view;
	private AircraftController controller;
	private AircraftModel model;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void start(Stage primaryStage) {
		// Initialize the GUI
		model = new AircraftModel();
		view = new AircraftView(primaryStage, model);
		controller = new AircraftController(model, view);

		// Display the GUI after all initialization is complete
		view.start();
	}
}
