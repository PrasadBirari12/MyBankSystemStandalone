package com.model;

import java.io.Serializable;

public class Customer implements Serializable {
	int custId; // auto generate
	String custName;
	String adharNo;
	int mobileNo;
	int age;

	String dob; // modify

//	Account account; // instance of account
	SavingsAccount savingsAccount;

	public Customer() {
	}

	public Customer(int custId, String custName, String adharNo, int mobileNo, int age, String dob) // 1 //2 account
	{
		super();
		this.custId = custId;
		this.custName = custName;
		this.adharNo = adharNo;
		this.mobileNo = mobileNo;
		this.age = age;
		if (Utility.validDate(dob) == false) {
			System.out.println(",,,,,Please Re enter Date of Birth,,,,,");
		} else {
			this.dob = dob;
		}
	}

	public SavingsAccount getSavingsAccount() {
		return savingsAccount;
	}

	public void setSavingsAccount(SavingsAccount savingsAccount) {
		this.savingsAccount = savingsAccount;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob)// setter dob
	{
		this.dob = dob;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getAdharNo() {
		return adharNo;
	}

	public void setAdharNo(String adharNo) {
		this.adharNo = adharNo;
	}

	public int getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(int mobileNo) {
		this.mobileNo = mobileNo;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", adharNo=" + adharNo + ", mobileNo="
				+ mobileNo + ", age=" + age + ", dob=" + dob + ", savingsAccount=" + savingsAccount + "]";
	}

}
