package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllayer.ControlPayStation;
import controllayer.IllegalCoinException;
import databaselayer.DatabaseLayerException;
import modellayer.Currency;
import modellayer.ParkingReceipt;
import modellayer.Currency.ValidCoinType;
import modellayer.Currency.ValidCurrency;

public class TestBuyTicket {

	ControlPayStation ps;
	
	@BeforeEach
	public void setup() {
		ps = new ControlPayStation();
		
	}
	
	/**
	 * Verify that the pay station is cleared and display shows 0 after a buy scenario
	 * @throws DatabaseLayerException 
	 * Case: BTExtra
	 */
	@Test
	public void shouldClearAfterBuy() throws IllegalCoinException, DatabaseLayerException {
		// Arrange
		int expectedParkingTime = 0;	// In minutes		
		int coinValue = 5;
		
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
		
		// Act
		ps.addPayment(coinValue, coinCurrency, coinType);
		ps.buy();
		int actualParkingTime = ps.getDisplayTime();

		// Assert
		assertEquals(expectedParkingTime, actualParkingTime);	
	}

	
	
	
	/**
	 * 
	 * @throws IllegalCoinException
	 * @throws DatabaseLayerException
	 * Case: BT1
	 */
	@Test
	public void ticketTimeShouldBe6AndThePrice14CentsAfterInsertingValidEuroAndDKK() throws IllegalCoinException, DatabaseLayerException {
		// Arange
		int expectedParkingTime = 6;	// In minutes		
		int expectedParkingPrice = 14;	// In cents		
		int coinValue = 1;
		int dkkCoinValue = 1;
		Currency.ValidCurrency euroCoinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType euroCoinType = Currency.ValidCoinType.FRACTION;
		

		Currency.ValidCurrency dkkCoinCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType dkkCoinType = Currency.ValidCoinType.INTEGER;
		
		// Act
		ps.addPayment(coinValue, dkkCoinCurrency, dkkCoinType);
		ps.addPayment(dkkCoinValue, euroCoinCurrency, euroCoinType);
		ParkingReceipt receipt = ps.buy();

		// Assert
		assertEquals(expectedParkingPrice, receipt.getCentAmount(),0.5d);	
		assertEquals(expectedParkingTime, receipt.getMinutesAmount());	
	}
	
	/**
	 * 
	 * @throws DatabaseLayerException
	 * @throws IllegalCoinException
	 * Case: BT2
	 */
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
		
		ParkingReceipt receipt = ps.buy();
		assertEquals(receipt.getMinutesAmount(), 1);
		assertEquals(receipt.getCentAmount(), 1, 0d);
		
	}
	
	
	/**
	 * Verify that cancel() clears the pay station
	 * Case: BT3
	 */
	@Test
	public void shouldClearAfterCancel() throws IllegalCoinException {
		// Arange
		int expectedParkingTime = 0;	// In minutes		
		int coinValue = 5;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
				
		// Act
		ps.addPayment(coinValue, coinCurrency, coinType);
		ps.cancel();
		int actualParkingTime = ps.getDisplayTime();

		// Assert
		assertEquals(expectedParkingTime, actualParkingTime);	
	}
	
	
	
	
	@AfterEach
	public void exit() {
		
	}
}
