import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.scene.control.Alert.AlertType;

public class AircraftController {

	enum Role {
		  TRANSPORT,
		  AIRLINER,
		  MILITARY,
		  NONE
		}
	
	private final AircraftModel model;
	private final AircraftView view;
	private ArrayList<AircraftModel> aircrafts;

	protected AircraftController(AircraftModel model, AircraftView view) {
		this.model = model;
		this.view = view;

		// register ourselves to listen for button clicks
		//view.btnClick.setOnAction((event) -> {
			//model.incrementValue();
			//String newText = Integer.toString(model.getValue());
			//view.lblNumber.setText(newText);
		//});
		
		
		////////////////////////////////////////////////////////
		// Perform live checks while user is typing in values //
		////////////////////////////////////////////////////////
		
		// Check manufacturer
		view.textManufacturer.setOnKeyReleased(e -> {
			if(view.textManufacturer.getText().length() > 25) {
				Alert alert = new Alert(AlertType.ERROR, "The \"Manufacturer\" field can consist of a maximum of 25 characters.", ButtonType.OK);
				alert.showAndWait();
				view.textManufacturer.setText(view.textManufacturer.getText().substring(0, 25));
			}
		});
		
		// Check model
		view.textModel.setOnKeyReleased(e -> {
			if(view.textModel.getText().length() > 25) {
	        	Alert alert = new Alert(AlertType.ERROR, "The \"Model\" field can consist of a maximum of 25 characters.", ButtonType.OK);
	        	alert.showAndWait();
	        	view.textModel.setText(view.textModel.getText().substring(0, 25));
          	}
		});
		
		// Check description
		view.textDescription.setOnKeyReleased(e -> {
			if(view.textDescription.getText().length() > 50) {
				Alert alert = new Alert(AlertType.ERROR, "The \"Description\" field can consist of a maximum of 50 characters.", ButtonType.OK);
				alert.showAndWait();
				view.textDescription.setText(view.textDescription.getText().substring(0, 50));
			}
		});
		
		// Check Introduction Year
		view.textIntroYear.setOnKeyReleased(e -> {
		  
			  // Check if only numbers entered
			  if (!view.textIntroYear.getText().matches("[0-9]+")) {
				  Alert alert = new Alert(AlertType.ERROR, "The \"Introduction Year\" field can only consist of numbers", ButtonType.OK);
				  alert.showAndWait();
				  view.textIntroYear.setText("");
				  return;
			  }
			  
			  // Check if value length is not over 4
			  if(view.textIntroYear.getText().length() > 4) {
				  Alert alert = new Alert(AlertType.ERROR, "The \"Introduction Year\" field can consist of a maximum of 4 characters.", ButtonType.OK);
				  alert.showAndWait();
				  view.textIntroYear.setText(view.textIntroYear.getText().substring(0, 4));
			  }
			  else if(view.textIntroYear.getText().length() == 4) {
				// Check if value is between 1900 and now
				  int year = Integer.parseInt(view.textIntroYear.getText());
				  if(year <= 1900) {
					  // Introduction year can be in the future
					  Alert alert1 = new Alert(AlertType.ERROR, "The \"Introduction Year\" field must at least 1900", ButtonType.OK);
					  alert1.showAndWait();
					  view.textIntroYear.setText("");
				  }
			  }
		});
		
		// Number built check
		view.textNumberBuilt.setOnKeyReleased(e -> {
		  
			  // Check if only numbers entered
			  if (!view.textNumberBuilt.getText().matches("[0-9]+")) {
				  Alert alert = new Alert(AlertType.ERROR, "The \\\"Number Built\\\" field can only consist of numbers", ButtonType.OK);
				  alert.showAndWait();
				  view.textNumberBuilt.setText("");
				  return;
			  }
			  
			// Check if value is not over 10 million
			  int numberbuilt = Integer.parseInt(view.textNumberBuilt.getText());
			  if(numberbuilt > 10000000) {
				  Alert alert = new Alert(AlertType.ERROR, "The \"Number Built\" field can not be over 10 million.", ButtonType.OK);
				  alert.showAndWait();
				  view.textNumberBuilt.setText("");
			  }
		});
		
		// Check random fact
		view.textRandomFact.setOnKeyReleased(e -> {
			  if(view.textRandomFact.getText().length() > 150) {
				  Alert alert = new Alert(AlertType.ERROR, "The \"Description\" field can consist of a maximum of 50 characters.", ButtonType.OK);
				  alert.showAndWait();
				  view.textRandomFact.setText(view.textRandomFact.getText().substring(0, 150));
			  }
		});
		
		// Check random fact
		view.listViewLeft.setOnKeyReleased(e -> {
			  if(view.textRandomFact.getText().length() > 150) {
				  Alert alert = new Alert(AlertType.ERROR, "The \"Description\" field can consist of a maximum of 50 characters.", ButtonType.OK);
				  alert.showAndWait();
				  view.textRandomFact.setText(view.textRandomFact.getText().substring(0, 150));
			  }
		});
		
		
		// Load data from file in listview button
		view.btnBottomLoadFile.setOnAction((event) -> {
			loadData();
		});
		
		// Load values into edit forms
		view.btnBottomEditEntry.setOnAction((event) -> {
			String entry = view.listViewLeft.getSelectionModel().getSelectedItem().toString();
			String[] aircraft = entry.split(" - ");
			
			for (AircraftModel element : aircrafts){
		         if (element.getManufacturer().contains(aircraft[0]) && element.getModel().contains(aircraft[1])){
		        	 view.textManufacturer.setText(element.getManufacturer());
		        	 view.textModel.setText(element.getModel());
		        	 view.textDescription.setText(element.getDescription());
		        	 view.textIntroYear.setText(String.valueOf(element.getIntroductionYear()));
		        	 view.comboBoxNationalOrigin.setValue(element.getNationalOrigin());
		        	 view.datepickerFirstFlight.setValue(convertToLocalDate(element.getFirstFlight()));
		        	 view.textNumberBuilt.setText(String.valueOf(element.getNumberBuilt()));
		        	 view.textRandomFact.setText(element.getFact());
		        	 view.comboBoxRole.setValue(element.getRole());
		         }
		      }
			
		});
		
		
		/////////////////////
		// Misc functions ///
		/////////////////////
		
		// Add countries to combobox
		String[] countries = Locale.getISOCountries();
		for (String countrylist : countries) {
			  Locale obj = new Locale("", countrylist);
			  String[] city = { obj.getDisplayCountry() };
			  for (int x = 0; x < city.length; x++) {
				  view.comboBoxNationalOrigin.getItems().add(obj.getDisplayCountry(Locale.US));
			  }
		}
		
		// Add roles to combobox
		for (Role role : Role.values()) { 
			view.comboBoxRole.getItems().add(role);
		}
	}
	
	// Load data
	protected void loadData()
	{
		readFile();
		for(AircraftModel aircraft: aircrafts)
		{
			view.listViewLeft.getItems().add(aircraft.getManufacturer() + " - " + aircraft.getModel());
		    System.out.println();
		}
	}
	
	// Load aircrafts from file
	protected void readFile(){
		
		// Open file chooser
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.setInitialDirectory(
	            new File(System.getProperty("user.dir"))
	        ); 
		File file = fileChooser.showOpenDialog(view.getStage());
		
		// Read file
        if (file != null) {
        	aircrafts = new ArrayList<AircraftModel>();
    		
    	    StringBuilder stringBuffer = new StringBuilder();
    	    BufferedReader bufferedReader = null;
    	    
    	    try {
    	        bufferedReader = new BufferedReader(new FileReader(file));
    	        
    	        String text;
    	        while ((text = bufferedReader.readLine()) != null) {
    	            stringBuffer.append(text + "\n");
    	            

    	            System.out.println(text);
    	            String[] arrOfStr = text.split("\\|");
    	            
    	            // Create aircraft object
    	            AircraftModel aircraft = new AircraftModel();
    	            aircraft.setManufacturer(arrOfStr[0]);
    	            aircraft.setModel(arrOfStr[1]);
    	            aircraft.setDescription(arrOfStr[2]);
    	            aircraft.setIntroductionYear(Integer.parseInt(arrOfStr[3]));
    	            aircraft.setFirstFlight(new SimpleDateFormat("dd.MM.yyyy").parse(arrOfStr[4]));
    	            aircraft.setNumberBuilt(Integer.parseInt(arrOfStr[5]));
    	            aircraft.setNationalOrigin(arrOfStr[6]);
    	            aircraft.setFact(arrOfStr[7]);
    	            aircraft.setRole(arrOfStr[8]);
    	            
    	            // Add aircraft object to array list
    	            aircrafts.add(aircraft);
    	        }
    	    } 
    	    catch (Exception ex) {
    	    	Alert alert = new Alert(AlertType.ERROR, ex.getMessage() + "File path: " + file.getPath(), ButtonType.OK);
    	    	alert.show();
    	    }
        }
	}
	
	public LocalDate convertToLocalDate(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
}
