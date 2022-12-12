package com.model;

import java.io.Serializable;

public abstract class Account implements Serializable {
	long accNo;
	String ifscNo;
	String bankName;
	transient double balance;/// change transient
	String openingDate;

	public Account() {
		super();

	}

	public Account(long accNo, String ifscNo, String bankName, double balance, String openingDate) {
		super();
		this.accNo = accNo;
		this.ifscNo = ifscNo;
		this.bankName = bankName;
		this.balance = balance;
		this.openingDate = openingDate;

	}

	public long getAccNo() {
		return accNo;
	}

	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}

	public String getIfscNo() {
		return ifscNo;
	}

	public void setIfscNo(String ifscNo) {
		this.ifscNo = ifscNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(String openingDate) {
		this.openingDate = openingDate;
	}

	@Override
	public String toString() {
		return "Account [accNo=" + accNo + ", ifscNo=" + ifscNo + ", bankName=" + bankName + ", balance=" + balance
				+ ", openingDate=" + openingDate + "]";
	}

	abstract void calculateInterest();// abstract balance
}
