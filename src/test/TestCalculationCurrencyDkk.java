package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllayer.*;
import modellayer.Currency;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestCalculationCurrencyDkk {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@BeforeEach
	public void setUp() {
		ps = new ControlPayStation();
	}

	//new code
	
	/**
	 * 
	 * @throws IllegalCoinException
	 * Case: AP2
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
		assertEquals(expectedParkingTime, actualParkingTime, "Should display 3 min for 50 �re");
		assertEquals(expectedParkingPrice, actualParkingPrice, 0.01d); //make space for errors
	}



	/** Fixture for pay station testing. */
	@AfterEach
	public void cleanUp() {
		ps.setReady();
	}	
	
}
