package test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllayer.*;
import modellayer.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestCalculationCurrencyEuro {

	private ControlPayStation ps;

	/** Fixture for pay station testing. */
	@BeforeEach
	public void setUp() {
		ps = new ControlPayStation();
	}
	
	/**
	 * Entering no coins should make the display report 0 minutes parking time.
	 */
	@Test
	public void enteringNoCoinsShouldDisplay0MinutesAnd0Price() throws IllegalCoinException {
		
		// Arrange
		int expectedParkingTime = 0;
		double expectedCoinAmount = 0d;
		
		// Act
		// No action
		
		// Assert
		assertEquals(expectedParkingTime, ps.getDisplayTime(), "Should display 0 min for no coins");
		assertEquals(expectedCoinAmount, ps.getDisplayAmountInCents(), 0d, "Should display 0 cents for no coins");
	}	

	/**
	 * Entering 5 cents should make the display report 2 minutes parking time
	 * Case: AP1
	 */
	@Test
	public void enteringOneCentShouldDisplay1MinuteAndPriceIs1Cent() throws IllegalCoinException {
		
		// Arrange
		int expectedParkingTime = 1;	// In minutes	
		double expectedParkingPrice = 1d;
		
		int coinValue = 1;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
		
		// Act
		ps.addPayment(coinValue, coinCurrency, coinType);
		int actualParkingTime = ps.getDisplayTime();
		double actualParkingPrice = ps.getDisplayAmountInCents();

		// Assert
		assertEquals(expectedParkingTime, actualParkingTime);
		assertEquals(expectedParkingPrice, actualParkingPrice, 0d);
	}

	
}
