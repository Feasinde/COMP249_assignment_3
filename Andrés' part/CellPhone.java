//-----------------------------------------------------------------------
//Assignment 3
//Part 1
//Written by: 
//Sviki Gabbay - 27490968
//Andrés Lou 24712374
//Victoria Avgoustis 27529198
//Laura Elena González 27217323	
//
//COMP 249 - Section D
// CellPhone class with constructors, clone method, accessors, mutators
// and equals method.
//-----------------------------------------------------------------------

import java.util.Scanner;

public class CellPhone implements Cloneable{
	private long serialNum;
	private String brand;
	private int year;
	private double price;


	//default constructor
	public CellPhone(){
		this.serialNum = 0;
		this.brand = null;
		this.year = 0;
		this.price = 0.0;
	}

	//parametrised constructor
	public CellPhone(long serialNum, String brand, double price, int year){
		this.serialNum = serialNum;
		this.brand = brand;
		this.year = year;
		this.price = price;	
	}

	//copy constructor
	public CellPhone(CellPhone phone, long serialNum){
		this.serialNum = serialNum;
		this.brand = phone.brand;
		this.year = phone.year;
		this.price = phone.price;		
	}

	//clone method
	public CellPhone clone(){

		long newSerial;

		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter the serial number of the new phone: ");
		newSerial = kb.nextLong();
		CellPhone copy = new CellPhone(this, newSerial);
		// kb.close();
		return copy;


	}

	//toString method
	public String toString(){
		return this.serialNum+": "+this.brand+" "+this.year+" $"+this.price;
	}

	//equals method
	public boolean equals(CellPhone phone){
		if (this.brand == phone.brand &&
				this.year == phone.year &&
				this.price == phone.price){
			return true;
		}
		return false;
	}

	//accessors and mutators
	public long getSerialNum(){
		return this.serialNum;
	}

	public void setSerialNum(long serialNum){
		this.serialNum = serialNum;
	}

	public String getBrand(){
		return this.brand;
	}

	public void setBrand(String brand){
		this.brand = brand;
	}

	public int getYear(){
		return this.year;
	}

	public void setYear(int year){
		this.year = year;
	}

	public double getPrice(){
		return this.price;
	}

	public void setPrice(double price){
		this.price = price;
	}

}