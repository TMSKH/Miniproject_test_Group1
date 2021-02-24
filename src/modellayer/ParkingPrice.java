package modellayer;

import java.util.Date;

/**
 * Inspired by the book: Flexible, Reliable Software
 * Henrik B�rbak Christensen: Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class ParkingPrice {
	
	// Parkingtime in seconds / 1 cent (Currency: Euro)
	private int price;	
	// Price depends on the pay stations parkingzone
	private ParkingZone parkingZone;	
	// Exchange rate 1 Euro to DKK
	private double exchangeEuroDkk;
	private Date startTime;

	// Hard coded values
	public ParkingPrice() {
		this.price = 24;
		this.parkingZone = new ParkingZone();
		this.exchangeEuroDkk = 7.5;
	}

	public ParkingPrice(int parkingPrice, ParkingZone parkingZone) {
		this.price = parkingPrice;
		this.parkingZone = parkingZone;
	}
	
	public ParkingPrice(int parkingPrice, ParkingZone parkingZone, double exchangeEuroDkk) {
		this.price = parkingPrice;
		this.parkingZone = parkingZone;
		this.exchangeEuroDkk = exchangeEuroDkk;
	}

	public int getParkingPrice() {
		return price;
	}

	public void setParkingPrice(int parkingPrice) {
		this.price = parkingPrice;
	}

	public ParkingZone getParkingZone() {
		return parkingZone;
	}

	public void setParkingZone(ParkingZone parkingZone) {
		this.parkingZone = parkingZone;
	}

	public double getExchangeEuroDkk() {
		return exchangeEuroDkk;
	}

	public void setExchangeEuroDkk(double exchangeEuroDkk) {
		this.exchangeEuroDkk = exchangeEuroDkk;
	}
	
}
