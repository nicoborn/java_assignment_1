import java.util.ArrayList;
import java.util.Date;

public class AircraftModel {
	
	// Enum role
	enum Role {
	  TRANSPORT,
	  AIRLINER,
	  MILITARY,
	  NONE
	}
	
	// Variables
	private String manufacturer;
	private String model;
	private String description;
	private int introductionYear;
	private Date firstFlight;
	private int numberBuilt;
 	private String nationalOrigin;
	private String fact;
	private String role;
	
	// List
	private ArrayList<AircraftModel> aircrafts;
	
	protected AircraftModel() {
		manufacturer = "";
		model = "";
		description = "";
		introductionYear = 0;
		firstFlight = new Date(Long.MIN_VALUE);
		numberBuilt = 0;
		nationalOrigin = "";
		fact = "";
		role = "";
	}
	
	// Manufacturer
	public String getManufacturer() {
	    return manufacturer;
	}
	
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	// Model
	public String getModel() {
	    return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	// Description
	public String getDescription() {
	    return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	// Introduction year
	public int getIntroductionYear() {
	    return introductionYear;
	}
	
	public void setIntroductionYear(int introductionYear) {
		this.introductionYear = introductionYear;
	}
	
	// First flight
	public Date getFirstFlight() {
		return firstFlight;
	}
	
	public void setFirstFlight(Date firstFlight) {
		this.firstFlight = firstFlight;
	}
	
	// Number built
	public int getNumberBuilt() {
	    return numberBuilt;
	}
	
	public void setNumberBuilt(int numberBuilt) {
		this.numberBuilt = numberBuilt;
	}
	
	// National origin
	public String getNationalOrigin() {
	    return nationalOrigin;
	}
	
	public void setNationalOrigin(String nationalOrigin) {
		this.nationalOrigin = nationalOrigin;
	}
	
	// Fact
	public String getFact() {
	    return fact;
	}
	
	public void setFact(String fact) {
		this.fact = fact;
	}
	
	// Role
	public String getRole() {
	    return role;
	}
	
	public void setRole(String role) {
		this.role = Enum.valueOf(Role.class, role).toString();
	}
	
	// Aircrafts
	public ArrayList<AircraftModel> getAircrafts() {
	    return aircrafts;
	}
	
	public void setAircrafts(ArrayList<AircraftModel> aircrafts) {
		this.aircrafts = aircrafts;
	}
   
}