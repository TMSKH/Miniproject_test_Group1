package controllayer;

import modellayer.ParkingReceipt;

/**
 * Inspired by the book: Flexible, Reliable Software
 * Henrik B�rbak Christensen: Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class ControlReceipt {
	
	private ParkingReceipt buyReceipt = null;

	public ControlReceipt(int minutesAmount, double centAmount) {
		buyReceipt = new ParkingReceipt(minutesAmount, centAmount);
	}
  
	public int getParkingTicketValue() {
		return buyReceipt.getMinutesAmount();
	}

	public ParkingReceipt getParkingReceipt() {
		return buyReceipt;
	}

}
