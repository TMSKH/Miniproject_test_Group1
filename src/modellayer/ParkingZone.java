package modellayer;

public class ParkingZone {
	
	// Id of actual parkingzone
	private int id;
	private String name;

	// Hard coded value
	public ParkingZone() {
		this.id = 2;
		this.name = "Zone B";
	}
	
	// Dynamicallt set parkingzone
	public ParkingZone(int pZoneIdent, String pZoneName) {
		this.id = pZoneIdent;
		this.name = pZoneName;
	}	
	
	public int getpZoneId() {
		return id;
	}

	public void setpZoneId(int pZoneId) {
		this.id = pZoneId;
	}
	
	
	
	//TODO: Add getter/setter for name
	
}
