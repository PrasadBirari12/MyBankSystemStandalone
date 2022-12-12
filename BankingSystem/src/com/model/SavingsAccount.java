package com.model;

import java.util.Scanner;

import com.exception.MyException;

public class SavingsAccount extends Account {

	boolean isSalaryAcount; // done
	int minimumBalance = 5000;
	Scanner sc = new Scanner(System.in);

	public SavingsAccount(long accNo, String ifscNo, String bankName, double balance, String openingDate,
			boolean isSalaryAcount) {
		super(accNo, ifscNo, bankName, balance, openingDate);
		this.isSalaryAcount = isSalaryAcount;

		try {
			if (isSalaryAcount == false && balance < 5000) {
				throw new MyException("Insufficent Balance for account createtion , insufficent account balance");
			} else {
				System.out.println("account created ok");
			}
		} catch (MyException e) {
			e.printStackTrace();
		}

	}

	public SavingsAccount() {
		super();
	}

	public boolean isSalaryAcount() {
		return isSalaryAcount;
	}

	public void setSalaryAcount(boolean isSalaryAcount) {
		this.isSalaryAcount = isSalaryAcount;
	}

	public int getMinimumBalance() {
		return minimumBalance;
	}

	public void setMinimumBalance(int minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

	@Override
	public void calculateInterest() {
		System.out.println("Enter deposit Amount");
		double bal = sc.nextInt();
		System.out.println("Enter Years");
		int y = sc.nextInt();
		double intrest = bal * y * 0.08;
		System.out.println("Intrest earned is : " + intrest);
	}

}
