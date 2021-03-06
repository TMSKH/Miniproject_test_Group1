package controllayer;

import java.time.LocalDate;

import modellayer.*;
import databaselayer.DatabaseLayerException;
import databaselayer.IDbPBuy;
import databaselayer.DatabasePBuy;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class ControlPayStation {

	private ParkingPayStation payStation;
	private ControlPrice controlPrice;
	
	public ControlPayStation() {
		this.payStation = new ParkingPayStation(1, "P-423E");
		this.controlPrice = new ControlPrice();
	}

	// Receive one coin as input
	public void addPayment(int amount, Currency.ValidCurrency currency, Currency.ValidCoinType coinType) throws IllegalCoinException {
	
		Coin coin = new Coin(amount, currency, coinType);
		
		// Test if coin is valid
		try {	
			payStation.validateCoin(coin);
		}
		catch (IllegalCoinException coinError) {
			throw new IllegalCoinException(
					"Invalid coin: " + currency.toString() + ", " + coinType.toString() + ", " + amount);
		}
		
		ParkingPrice currentPrice = controlPrice.getCurrentPrice();
		// Add amount
		payStation.addAmount(coin, currentPrice);	
	}

	// Process the buy
	public ParkingReceipt buy() throws DatabaseLayerException {
		LocalDate currentTime = java.time.LocalDate.now();
		
		// create buy
		ParkingBuy thisBuy = new ParkingBuy();
		thisBuy.setAssociatedPaystation(payStation);
		thisBuy.setBuyTime(currentTime);
		
		// Save in Parkingsystem db
		IDbPBuy dbBuy = new DatabasePBuy();
		dbBuy.insertParkingBuy(thisBuy);
		//
		ControlReceipt ctrlReceipt = new ControlReceipt(payStation.getTimeBoughtInMinutes(), payStation.getAmount());
		
		reset();	
		ParkingReceipt buyReceipt = ctrlReceipt.getParkingReceipt();		
		return buyReceipt;
	}

	/**
	 * Calculate the corresponding parking time in minutes
	 * (calculated seconds and convert to minutes - rounded up)
	*/
	public int getDisplayTime() 
	{
		return payStation.getTimeBoughtInMinutes();
	}
	
	/**
	 * New method
	 * Used to also be able to test displaying the current price
	 * @return
	 */
	public double getDisplayAmountInCents()
	{
		return payStation.getAmount();
	}
	
	public void setReady() {
		reset();
	}

	public void cancel() {
		reset();
	}

	private void reset() {
		payStation.setAmount(0);
	}
	

	

	

	

}
