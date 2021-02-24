package test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.*;

import controllayer.*;
import modellayer.Currency.ValidCoinType;
import modellayer.Currency.ValidCurrency;

import databaselayer.DatabaseLayerException;
import modellayer.Coin;
import modellayer.Currency.ValidCoinType;
import modellayer.Currency.ValidCurrency;
import modellayer.PReceipt;
import utility.Validation;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestIllegalCoin {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}

	
	// Norwegian coin
	/**
	 * Case: AP4
	 */
	@Test
	public void shouldRejectIllegalCurrencyNokCoinAndTimeAndPriceDontChange() {
		assertThrows(IllegalCoinException.class, () -> ps.addPayment(2, ValidCurrency.NOK, ValidCoinType.INTEGER));
		assertEquals(ps.getDisplayAmountInCents(), 0);
		assertEquals(ps.getDisplayTime(), 0);

		
	}
	
	// unknown Euro coin value
	//New code
	@Test
	public void shouldRejectIllegalEuroCoinAndTimeAndPriceDontChange(){
		assertThrows(IllegalCoinException.class, () -> ps.addPayment(3, ValidCurrency.EURO, ValidCoinType.INTEGER));
		assertEquals(ps.getDisplayAmountInCents(), 0);
		assertEquals(ps.getDisplayTime(), 0);
		
	}
	
	@Test
	public void shouldAcceptBothLegalAndIllegalCoinsAndPrintATicketWithTime1AndPrice1Cents() throws DatabaseLayerException, IllegalCoinException {
		//Checks if it's invalid
		try {
			ps.addPayment(3, ValidCurrency.EURO, ValidCoinType.INTEGER);
			fail("Didn't throw the exception");
		}
		catch(IllegalCoinException e) {
			//If it's invalid inserts the coin
			ps.addPayment(1, ValidCurrency.EURO, ValidCoinType.FRACTION);
		}
		
		PReceipt receipt = ps.buy();
		assertEquals(receipt.getMinutesAmount(), 1);
		assertEquals(receipt.getCentAmount(), 1, 0d);
		
	}
	
	/**
	 * Case: AP5
	 * new code
	 */
	@Test
	public void shouldThrowExceptionForIllegalCoinAndAcceptValidCoinsPrice1MinuteCost2Cents() {
		
		//Arrange
		int expectedParkingTime = 1;
		double expectedParkingPrice = 2d;
		
		//Act
		//Valid coin
		assertDoesNotThrow(() -> ps.addPayment(1, ValidCurrency.EURO, ValidCoinType.FRACTION));
		//InvalidCoin
		assertThrows(IllegalCoinException.class, () -> ps.addPayment(3, ValidCurrency.EURO, ValidCoinType.INTEGER));
		//Valid coin
		assertDoesNotThrow(() -> ps.addPayment(1, ValidCurrency.EURO, ValidCoinType.FRACTION));
		int actualParkingTime = ps.getDisplayTime();
		double actualParkingPrice = ps.getDisplayAmountInCents();
		
		//Assert
		assertEquals(expectedParkingTime, actualParkingTime, "Parking time should be 1 minute.");
		assertEquals(expectedParkingPrice, actualParkingPrice, 0d, "Parking should cost 2 cents.");
	}
	
	@Test
	public void differentValidCoinsShouldPrintTicketWithTime6() throws IllegalCoinException, DatabaseLayerException {
		 ps.addPayment(1, ValidCurrency.EURO, ValidCoinType.FRACTION);
		 ps.addPayment(1, ValidCurrency.DKK, ValidCoinType.INTEGER);
		 
		 PReceipt receipt = ps.buy();
		 assertEquals(receipt.getMinutesAmount(), 6);
		 
	}
	
	
}
