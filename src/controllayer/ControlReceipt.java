package controllayer;

import modellayer.PReceipt;

/**
 * Inspired by the book: Flexible, Reliable Software
 * Henrik B�rbak Christensen: Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class ControlReceipt {
	
	private PReceipt buyReceipt = null;

	public ControlReceipt(int minutesAmount, double centAmount) {
		buyReceipt = new PReceipt(minutesAmount, centAmount);
	}
  
	public int getParkingTicketValue() {
		return buyReceipt.getMinutesAmount();
	}

	public PReceipt getParkingReceipt() {
		return buyReceipt;
	}

}
