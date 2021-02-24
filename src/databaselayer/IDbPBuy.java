package databaselayer;

import modellayer.ParkingBuy;

public interface IDbPBuy {

    // insert new PBuy
 	public int insertParkingBuy(ParkingBuy parkingBuy) throws DatabaseLayerException;
 		
 	// Delete PBuy
 	public int deleteParkingBuy(ParkingBuy parkingBuy) throws DatabaseLayerException;
    
}
