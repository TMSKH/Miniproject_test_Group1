package test;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllayer.ControlPayStation;
import controllayer.IllegalCoinException;
import databaselayer.DatabaseLayerException;
import modellayer.Currency;
import modellayer.Currency.ValidCoinType;
import modellayer.Currency.ValidCurrency;

public class TestAddPaymentMethod {

	private ControlPayStation ps;

	/** Fixture for pay station testing. */
	@BeforeEach
	public void setUp() {
		ps = new ControlPayStation();
	}
	
	/**
	 * Entering no coins should make the display report 0 minutes parking time.
	 * Case: APExtra
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
	 * APExtra2
	 * Illegal Euro coin
	 */
	@Test
	public void shouldRejectIllegalEuroCoinAndTimeAndPriceDontChange(){
		assertThrows(IllegalCoinException.class, () -> ps.addPayment(3, ValidCurrency.EURO, ValidCoinType.INTEGER));
		assertEquals(ps.getDisplayAmountInCents(), 0);
		assertEquals(ps.getDisplayTime(), 0);
		
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
	
	// Norwegian coin
		/**
		 * Case: AP4
		 */
		@Test
		public void shouldRejectIllegalCurrencyNokCoinAndTimeAndPriceDontChange() {
			//Arrange
			int expectedParkingTime = 0;
			double expectedParkingPrice = 0d;
			
			//Act
			int actualParkingTime = ps.getDisplayTime();
			double actualParkingPrice = ps.getDisplayAmountInCents();			
			
			//Assert
			assertThrows(IllegalCoinException.class, () -> ps.addPayment(2, ValidCurrency.NOK, ValidCoinType.INTEGER));
			assertEquals(expectedParkingTime, actualParkingTime, "Parking time should be 0 minutes.");
			assertEquals(expectedParkingPrice, actualParkingPrice, "Parking price should be 0 cents.");
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
		
		
		
		
		
	
	
	
	/** Fixture for pay station testing. */
	@AfterEach
	public void cleanUp() {
		ps.setReady();
	}	
	

}
