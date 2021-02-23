package modellayer;

/**
 * Inspired by the book: Flexible, Reliable Software
 * Henrik B�rbak Christensen: Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class PReceipt {
  
	private int minutesAmount; //previously called value
	private double centAmount; //new field so we can now display the price on the receipt
	
	public PReceipt(int minutesAmount, double centAmount) 
	{
		this.minutesAmount = minutesAmount;
		this.centAmount = centAmount;
	}
	
	public int getMinutesAmount() { //previously getValue()
		return minutesAmount;
	}

	public void setMinutesAmount(int value) { //previously setValue()
		this.minutesAmount = value;
	}
	
	public double getCentAmount()
	{
		return centAmount;
	}
	
	public void setCentAmount(double centAmount)
	{
		this.centAmount = centAmount;
	}
}
