package test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllayer.*;
import databaselayer.DatabaseLayerException;
import modellayer.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestReset {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@BeforeEach
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Verify that the pay station is cleared and display shows 0 after a buy scenario
	 * @throws DatabaseLayerException 
	 * Case: BTExtra
	 */
	@Test
	public void shouldClearAfterBuy() throws IllegalCoinException, DatabaseLayerException {
		// Arange
		int expectedParkingTime = 0;	// In minutes		
		int coinValue = 5;
		
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
		
		// Act
		ps.addPayment(coinValue, coinCurrency, coinType);
		ps.buy();
		int actualParkingTime = ps.getDisplayTime();

		// Assert
		assertEquals(expectedParkingTime, actualParkingTime);	
	}

	/**
	 * Verify that cancel() clears the pay station
	 * Case: BT3
	 */
	@Test
	public void shouldClearAfterCancel() throws IllegalCoinException {
		// Arange
		int expectedParkingTime = 0;	// In minutes		
		int coinValue = 5;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
				
		// Act
		ps.addPayment(coinValue, coinCurrency, coinType);
		ps.cancel();
		int actualParkingTime = ps.getDisplayTime();

		// Assert
		assertEquals(expectedParkingTime, actualParkingTime);	
	}
}
