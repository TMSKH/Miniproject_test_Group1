package modellayer;

import java.time.LocalDate;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class ParkingBuy {

	// Buy ident
	private long id;
	// Time of buy
	private LocalDate buyTime;
	// Parkingtime in minutes
	private int duration;
	// Payed amount in cents
	private double payedAmount;
	// PayStation that generated buy 
	private ParkingPayStation associatedPaystation;
	
	// Constructor
	public ParkingBuy() {
		
	}
	public ParkingBuy(int foundId) {
		id = foundId;
	}	
	public ParkingBuy(LocalDate buyTime, int parkingDuration, double payedCentAmount) {
		this.buyTime = buyTime;
		this.duration = parkingDuration;
		this.payedAmount = payedCentAmount;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(LocalDate buyTime) {
		this.buyTime = buyTime;
	}

	public int getParkingDuration() {
		return duration;
	}

	public void setParkingDuration(int parkingDuration) {
		this.duration = parkingDuration;
	}

	public double getPayedCentAmount() {
		return payedAmount;
	}

	public void setPayedCentAmount(double payedCentAmount) {
		this.payedAmount = payedCentAmount;
	}

	public ParkingPayStation getAssociatedPaystation() {
		return associatedPaystation;
	}

	public void setAssociatedPaystation(ParkingPayStation associatedPaystation) {
		this.associatedPaystation = associatedPaystation;
	}
	
}
