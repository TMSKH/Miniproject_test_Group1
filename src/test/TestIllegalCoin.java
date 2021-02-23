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

	/**
	 * Verify that illegal coins are rejected.
	 */
	
	// Norvegian coin
	@Test
	public void shouldRejectIllegalCurrencyNokCoinAndTimeDoesntChange() {
		assertThrows(IllegalCoinException.class, () -> ps.addPayment(2, ValidCurrency.NOK, ValidCoinType.INTEGER));
		assertEquals(ps.getDisplayAmountInCents(), 0);
		assertEquals(ps.getDisplayTime(), 0);

		
	}
	
	// unknown Euro coin value
	@Test
	public void shouldRejectIllegalEuroCoin(){
		assertThrows(IllegalCoinException.class, () -> ps.addPayment(3, ValidCurrency.EURO, ValidCoinType.INTEGER));
		assertEquals(ps.getDisplayAmountInCents(), 0);
		assertEquals(ps.getDisplayTime(), 0);
		
	}
	
	@Test
	public void shouldAcceptBothLegalAndIllegalCoinsAndPrintATicketWithTime1() throws DatabaseLayerException, IllegalCoinException {
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
		assertEquals(receipt.getValue(), 1);
		
	}
	
	@Test
	public void shouldThrowExceptionForIllegalCoinAndAcceptValidCoins() {
		
		//Valid coin
		assertDoesNotThrow(() -> ps.addPayment(1, ValidCurrency.EURO, ValidCoinType.FRACTION));
		//InvalidCoin
		assertThrows(IllegalCoinException.class, () -> ps.addPayment(3, ValidCurrency.EURO, ValidCoinType.INTEGER));
		//Valid coin
		assertDoesNotThrow(() -> ps.addPayment(1, ValidCurrency.EURO, ValidCoinType.FRACTION));
		
		assertEquals(1, ps.getDisplayTime());
	}
	
	@Test
	public void differentValidCoinsShouldPrintTicketWithTime6() throws IllegalCoinException, DatabaseLayerException {
		 ps.addPayment(1, ValidCurrency.EURO, ValidCoinType.FRACTION);
		 ps.addPayment(1, ValidCurrency.DKK, ValidCoinType.INTEGER);
		 
		 PReceipt receipt = ps.buy();
		 assertEquals(receipt.getValue(), 6);
		 
	}
	
	
}
