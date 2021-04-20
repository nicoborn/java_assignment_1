

import java.util.Calendar;
import java.util.Locale;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AircraftView {
    private final AircraftModel model;
    private Stage stage;

	protected ListView listViewLeft;
	protected TextField textManufacturer;
	protected TextField textModel;
	protected TextField textDescription;
	protected TextField textIntroYear;
	protected DatePicker datepickerFirstFlight;
	protected TextField textNumberBuilt;
	protected ComboBox comboBoxNationalOrigin;
	protected TextField textRandomFact;
	protected ComboBox comboBoxRole;
	protected Button btnSave;
	protected Button btnBottomAdd;
	protected Button btnBottomDelete;
	protected Button btnBottomLoadFile;
	protected Button btnBottomEditEntry;

	protected AircraftView(Stage stage, AircraftModel model) {
		this.stage = stage;
		this.model = model;
		
		stage.setTitle("Aircraft Manager");
		
		// Root & padding
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10, 10, 10, 10));
 
		// TOP
		Label labelTitle = new Label("Aircraft Manager");
		root.setTop(labelTitle);
		BorderPane.setMargin(labelTitle, new Insets(10, 10, 10, 10));
      
		// LEFT
		listViewLeft = new ListView();
      
		root.setLeft(listViewLeft);
		BorderPane.setMargin(listViewLeft, new Insets(10, 10, 10, 10));
		BorderPane.setAlignment(listViewLeft, Pos.TOP_CENTER);
 
		// CENTER
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.TOP_LEFT);
		gridPane.setVgap(5);
		gridPane.setHgap(5);
  
		// Manufacturer Label & Textbox
		Label labelManufacturer = new Label("Manufacturer ");
		gridPane.add(labelManufacturer, 0, 1);
		textManufacturer = new TextField();
		gridPane.add(textManufacturer, 1, 1);
  
		// Model Label & Textbox
		Label labelModel = new Label("Model ");
		gridPane.add(labelModel, 0, 2);
		textModel = new TextField();
		gridPane.add(textModel, 1, 2);
  
		// Description Label & Textbox
		Label labelDescription = new Label("Description ");
		gridPane.add(labelDescription, 0, 3);
		textDescription = new TextField();
		gridPane.add(textDescription, 1, 3);
  
		// Introduction Year Label & Textbox
		Label labelIntroYear = new Label("Introduction Year ");
		gridPane.add(labelIntroYear, 0, 4);
		textIntroYear = new TextField();
		gridPane.add(textIntroYear, 1, 4);
  
		// First Flight Label & Date
		Label labelFirstFlight = new Label("First Flight ");
		gridPane.add(labelFirstFlight, 0, 5);
		datepickerFirstFlight = new DatePicker();
		gridPane.add(datepickerFirstFlight, 1, 5);
  
		// Number Built Label & Textbox
		Label labelNumberBuilt = new Label("Number Built ");
		gridPane.add(labelNumberBuilt, 0, 6);
		textNumberBuilt = new TextField();
		gridPane.add(textNumberBuilt, 1, 6);
  
		// National Origin Label & Textbox
		Label labelNationalOrigin = new Label("National Origin ");
		gridPane.add(labelNationalOrigin, 0, 7);
		comboBoxNationalOrigin = new ComboBox();
		gridPane.add(comboBoxNationalOrigin, 1, 7);
  
		// Random Fact Label & Date
		Label labelRandomFact = new Label("Random Fact ");
  		gridPane.add(labelRandomFact, 0, 8);
  		textRandomFact = new TextField();
  		gridPane.add(textRandomFact, 1, 8);
  
  		// Role Label & Textbox
  		Label labelRole = new Label("Role");
  		gridPane.add(labelRole, 0, 9);
  		comboBoxRole = new ComboBox();
  		gridPane.add(comboBoxRole, 1, 9);
  
  		// Save Button
  		btnSave = new Button("Save");
  		gridPane.add(btnSave, 1, 10);
  
  		// Alignment
  		BorderPane.setMargin(gridPane, new Insets(10, 10, 10, 10));
  		BorderPane.setAlignment(gridPane, Pos.TOP_CENTER);
  
  		// BOTTOM
  		GridPane gridPaneButtonsBottom = new GridPane();
  		gridPaneButtonsBottom.setAlignment(Pos.TOP_LEFT);
  		gridPaneButtonsBottom.setHgap(5);
  
  		// Button +
  		btnBottomAdd = new Button(" + ");
  		btnBottomAdd.setPadding(new Insets(5, 5, 5, 5));
  		gridPaneButtonsBottom.add(btnBottomAdd, 3, 0);
  		
  		// Button Load
  		btnBottomEditEntry = new Button("Edit selected");
  		btnBottomEditEntry.setPadding(new Insets(5, 5, 5, 5));
  		gridPaneButtonsBottom.add(btnBottomEditEntry, 1, 0);
  
  		// Button -
  		btnBottomDelete = new Button(" - ");
  		btnBottomDelete.setPadding(new Insets(5, 5, 5, 5));
  		gridPaneButtonsBottom.add(btnBottomDelete, 4, 0);
  
  		// Button Load
  		btnBottomLoadFile = new Button("Load data");
  		btnBottomLoadFile.setPadding(new Insets(5, 5, 5, 5));
  		gridPaneButtonsBottom.add(btnBottomLoadFile, 0, 0);
      
  		// Align center
  		root.setBottom(gridPaneButtonsBottom);
  		BorderPane.setAlignment(gridPaneButtonsBottom, Pos.TOP_LEFT);
  		root.setCenter(gridPane);
      
  		// Set margin for bottom area.
  		BorderPane.setMargin(gridPaneButtonsBottom, new Insets(10, 10, 10, 10));
 
  		// Scene
  		Scene scene = new Scene(root, 725, 500);
		stage.setScene(scene);
	}
	
	public void start() {
		stage.show();
	}
	
	/**
	 * Getter for the stage, so that the controller can access window events
	 */
	public Stage getStage() {
		return stage;
	}
}
