package test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import controllayer.ControlPayStation;
//import controllayer.Currency;
//import controllayer.IPayStation;
//import controllayer.IReceipt;
//import controllayer.IllegalCoinException;

import databaselayer.*;
import modellayer.*;
import controllayer.*;

//import static org.junit.Assert.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestDatabaseAccess {
	
	DBConnection con = null;
	static PBuy tempPBuy;

	/** Fixture for pay station testing. */
	@BeforeEach
	public void setUp() {
		con = DBConnection.getInstance();
	}
	
	/**
	 * Case: DC1
	 */
	@Test
	public void wasConnected() {
		assertNotNull(con, "Connected - connection cannot be null");
		
		DBConnection.closeConnection();
		boolean wasNullified = DBConnection.instanceIsNull();
		assertTrue(wasNullified, "Disconnected - instance set to null");
		
		con = DBConnection.getInstance();
		assertNotNull(con, "Connected - connection cannot be null");		
	}
	
	
	/**
	 * Case: DC2
	 */
	@Test
	public void wasInsertedBuy() {
		
		// Arrange
		LocalDate timeNow = java.time.LocalDate.now();
		double payedCentAmount = 100;
		
		tempPBuy = new PBuy();
		
		PPayStation pStat = new PPayStation(1, "P-423E");
		pStat.setAmount(payedCentAmount);
		tempPBuy.setAssociatedPaystation(pStat);
		tempPBuy.setBuyTime(timeNow);
		
		DatabasePBuy dbPbuy = new DatabasePBuy();
		
		// Act
		
		int key = 0; 
		try
		{
			key = dbPbuy.insertParkingBuy(tempPBuy);
			tempPBuy.setId(key); //Setting the tempPBuy ID to KEY so we can delete it
		}
		catch (DatabaseLayerException e)
		{
			fail("Database Layer Exception");
		}
		
		// Assert
		assertEquals(true, key > 0);

		
	}	
	
	/**
	 * 
	 * @throws DatabaseLayerException
	 * Case: DC3
	 */
	@Test
	public void wasRetrievedPriceDatabaselayer() throws DatabaseLayerException {
		// Arrange
		PPrice foundPrice = null;
		int parkingZoneId = 2;
		DatabasePPrice dbPrice = new DatabasePPrice();
		
		
		// Act
		foundPrice = dbPrice.getPriceByZoneId(parkingZoneId);
		// Assert
		assertEquals(foundPrice.getParkingPrice(), 25);
		
	}
	
	/**
	 * Case: DC4
	 */
	@Test
	public void wasRetrievedPriceControllayer() {

		// Arrange
		ControlPrice ctrPrice = new ControlPrice();
		
		
		// Act

		// Assert
		assertEquals(ctrPrice.getCurrentPrice().getParkingPrice(), 24);
		
	}	
	
	
	/** Fixture for pay station testing. */
	@AfterEach
	public void cleanUp() {
		DBConnection.closeConnection();
	}	
	
	@AfterAll
	public static void cleanUpWhenFinish() {
		// Arrange
		DatabasePBuy dbPbuy = new DatabasePBuy();
		int numDeleted = 0;
		
		// Act
		try {
			numDeleted = dbPbuy.deleteParkingBuy(tempPBuy);
		} catch(Exception ex) { 
			System.out.println("Error: " + ex.getMessage());
		} finally {
			DBConnection.closeConnection();
		}
		// Assert
		assertEquals(1, numDeleted, "One row deleted");
	}	

}
