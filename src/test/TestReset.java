package test;

import org.junit.*;
import static org.junit.Assert.*;

import controllayer.*;
import databaselayer.DatabaseLayerException;
import modellayer.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik Bærbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestReset {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Verify that the pay station is cleared and display shows 0 after a buy scenario
	 * @throws DatabaseLayerException 
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

		// Assert
		assertEquals(expectedParkingTime, 0);	
	}

	/**
	 * Verify that cancel() clears the pay station
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

		// Assert
		assertEquals(expectedParkingTime, 0);	
	}
}
