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
	 * Entering 1 cent and 50 �re should make the display report 4 minutes parking time.
	 * @throws DatabaseLayerException 
	 */
	@Test
	public void shouldDisplay4MinFor1CentAnd1DKK() throws IllegalCoinException, DatabaseLayerException {
		
		// Arange
		int expectedParkingTime = 6;	// In minutes		
		
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

		// Assert
		assertEquals(expectedParkingTime, actualParkingTime);		
	}

	
	/** Fixture for pay station testing. */
	@After
	public void cleanUp() {
		ps.setReady();
	}
	
}
