import javafx.application.Application;
import javafx.stage.Stage;

public class AircraftMVC extends Application {
	private AircraftView view;
	private AircraftController controller;
	private AircraftModel model;

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Optional. Program initialization can go here, for example, connection to
	 * a database, or a network server. The primaryStage is not yet available.
	 */
	@Override
	public void init() {
		// Nothing to do in this example
	}

	/**
	 * Note the dependencies between model, view and controller. Additionally,
	 * the view needs access to the primaryStage
	 */
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
