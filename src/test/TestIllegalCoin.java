package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.*;

import controllayer.*;
import modellayer.Coin;
import modellayer.Currency.ValidCoinType;
import modellayer.Currency.ValidCurrency;
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
	public void shouldRejectIllegalCurrencyNokCoinAndTimeDoesntChange() {
		assertThrows(IllegalCoinException.class, () -> ps.addPayment(2, ValidCurrency.NOK, ValidCoinType.INTEGER));
		assertEquals(ps.readDisplay(), 0);
		
		
	}
	// unknown Euro coin value
	@Ignore
	public void shouldRejectIllegalEuroCoin(){
		assertThrows(IllegalCoinException.class, () -> ps.addPayment(3, ValidCurrency.EURO, ValidCoinType.INTEGER));
		assertEquals(ps.readDisplay(), 0);
		
	}
}
