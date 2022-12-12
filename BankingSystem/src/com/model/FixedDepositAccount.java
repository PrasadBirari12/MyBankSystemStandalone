package com.model;

import java.util.Scanner;

public class FixedDepositAccount extends Account {
	int depositAmount; // minimum is 1000
	int tenure; // min 1 and max is 7
	double intrest;

	Scanner sc = new Scanner(System.in);

	public FixedDepositAccount() {
		super();
	}

	public FixedDepositAccount(int depositAmount, int tenure) {
		super();
		this.depositAmount = depositAmount;
		this.tenure = tenure;
	}

	public int getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(int depositAmount) {
		if (depositAmount >= 1000) {
			this.depositAmount = depositAmount;
		} else {
			System.out.println("Enter Deposit amount minimum 1000");
		}
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		if (tenure >= 1 && tenure <= 7) {
			this.tenure = tenure;
		} else {
			System.out.println("Tenure Should be between 1 to 7");
		}
	}

	public double getIntrest() {
		return intrest;
	}

	public void setIntrest(double intrest) {
		this.intrest = intrest;
	}

	@Override
	public void calculateInterest() // abstract method
	{
		System.out.println("Enter deposit Amount");
		setDepositAmount(sc.nextInt());
		System.out.println("Enter Years");
		setTenure(sc.nextInt());
		intrest = depositAmount * tenure * 0.08;
		System.out.println("Intrest earned is : " + intrest);
	}

}
