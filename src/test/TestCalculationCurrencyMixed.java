package test;

import org.junit.*;
import static org.junit.Assert.*;

import controllayer.*;
import databaselayer.DatabaseLayerException;
import modellayer.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestCalculationCurrencyMixed {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * 
	 * @throws IllegalCoinException
	 * @throws DatabaseLayerException
	 * Case: AP3
	 */
	@Test
	public void entering1CentAnd1DKKShouldDisplay6Minutesand14Cents() throws IllegalCoinException, DatabaseLayerException {
		
		// Arrange
		int expectedParkingTime = 6;	// In minutes	
		double expectedParkingPrice = 14.33d;
		
		int coinValueEu = 1;
		Currency.ValidCurrency coinCurrencyEu = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinTypeEu = Currency.ValidCoinType.FRACTION;
		
		int coinValueDKK = 1;
		Currency.ValidCurrency coinCurrencyDKK = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinTypeDKK = Currency.ValidCoinType.INTEGER;
		
		// Act
		ps.addPayment(coinValueEu, coinCurrencyEu, coinTypeEu);
		ps.addPayment(coinValueDKK, coinCurrencyDKK, coinTypeDKK);
		int actualParkingTime = ps.getDisplayTime();
		double actualParkingPrice = ps.getDisplayAmountInCents();

		// Assert
		assertEquals(expectedParkingTime, actualParkingTime);
		assertEquals(expectedParkingPrice, actualParkingPrice, 0.01d);
	}

	
	/** Fixture for pay station testing. */
	@After
	public void cleanUp() {
		ps.setReady();
	}
	
}
