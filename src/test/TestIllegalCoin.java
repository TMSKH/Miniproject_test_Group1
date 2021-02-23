package test;

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
	
	// Norwegian coin
	@Test(expected = IllegalCoinException.class)
	public void shouldRejectIllegalCurrencyNokCoin() throws IllegalCoinException {
		
		//Arrange
		Coin coin = new Coin(1, ValidCurrency.NOK, ValidCoinType.INTEGER);
		Validation validation = new Validation();
		
		//ACT
		//Nothing
		
		//Assert
		validation.validateCoin(coin);
		
		
	}
	// unknown Euro coin value
	@Test(expected = IllegalCoinException.class)
	public void shouldRejectIllegalEuroCoin() throws IllegalCoinException {
		
		Coin coin = new Coin(3, ValidCurrency.EURO, ValidCoinType.INTEGER);
		Validation validation = new Validation();
		
		validation.validateCoin(coin);
	}
}
