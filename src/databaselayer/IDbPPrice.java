package databaselayer;

import modellayer.ParkingPrice;

public interface IDbPPrice {

	public ParkingPrice getCurrentPrice();
    // Get Price by parking zone id
	public ParkingPrice getPriceByZoneId(int zoneId) throws DatabaseLayerException;
    
}
