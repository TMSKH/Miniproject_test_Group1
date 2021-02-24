package controllayer;

import databaselayer.DatabaseLayerException;
import databaselayer.IDbPPrice;
import databaselayer.DatabasePPrice;
import modellayer.ParkingPrice;

public class ControlPrice {
	
	private IDbPPrice dbPrice;
	
	public ControlPrice() {
		this.dbPrice = new DatabasePPrice();
	}
	
	public ParkingPrice getCurrentPrice() {
		
		ParkingPrice currentPrice = new ParkingPrice(); //hardcoded
		
		return currentPrice;
	}
	
	public ParkingPrice getPriceRemote(int zoneId) throws DatabaseLayerException {
		
		// Get price from Parkingsystem DB
		ParkingPrice readPrice = dbPrice.getPriceByZoneId(zoneId);
		//
		return readPrice;
	}

}
