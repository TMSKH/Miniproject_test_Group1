package test;

import org.junit.*;
import static org.junit.Assert.*;

import controllayer.*;
import modellayer.Currency;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestCalculationCurrencyDkk {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Entering 50 �re should make the display report 3 minutes parking time.
	 */
	@Test
	public void entering1DKKShouldDisplay6MinutesAnd13Cents() throws IllegalCoinException {
		// Arrange
		int expectedParkingTime = 6;	// In minutes
		double expectedParkingPrice = 13.33d;
		
		int coinValue = 1;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		
		// Act
		ps.addPayment(coinValue, coinCurrency, coinType);
		int actualParkingTime = ps.getDisplayTime();
		double actualParkingPrice = ps.getDisplayAmountInCents();
			
		// Assert
		assertEquals("Should display 3 min for 50 �re", expectedParkingTime, actualParkingTime);
		assertEquals(expectedParkingPrice, actualParkingPrice, 0.01d); //make space for errors
	}



	/** Fixture for pay station testing. */
	@After
	public void cleanUp() {
		ps.setReady();
	}	
	
}
