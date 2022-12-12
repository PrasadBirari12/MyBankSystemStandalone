package com.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dao.DataBaseStorageDao;
import com.dao.FileStorageDao;
import com.model.Account;
import com.model.Customer;
import com.model.FixedDepositAccount;
import com.model.SavingsAccount;

public class MyClass {
	private static int nextmember = 100;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		List<Customer> clist = new ArrayList<Customer>();// customer list
		List<Account> alist = new ArrayList<Account>();// Account list

		Customer customer = new Customer();// customer object

		SavingsAccount sa1 = new SavingsAccount();
		FixedDepositAccount fix = new FixedDepositAccount();
		DataBaseStorageDao daodb = new DataBaseStorageDao();
		FileStorageDao fd = new FileStorageDao();

		while (true) {
			System.out.println("1.Create new customer data");
			System.out.println("2.Assign a bank Account to customer");
			System.out.println("3.Display Balance or interest earned of customer");// remain
			System.out.println("4.Sort customer data");
			System.out.println("5.Persist Customer data");
			System.out.println("6.Show all customers");
			System.out.println("7.Search customers by name");
			System.out.println("8.Exit");

			System.out.println("Enter Option which you want to perform : ");
			int num = sc.nextInt();

			switch (num) {
			case 1:
				System.out.println("Enter Customer name : ");
				String custname = sc.next();
				System.out.println("Enter Adhar number : ");
				String adhar = sc.next();
				System.out.println("Enter Mob number : ");
				int mob = sc.nextInt();
				System.out.println("Enter  age of C : ");
				int age = sc.nextInt();
				System.out.println("Enter date Of Birth dd/mm/yy");
				String dob = sc.next();
				int custId = nextmember++;
				customer = new Customer(custId, custname, adhar, mob, age, dob);
				clist.add(customer);
				break;

			case 2:

				System.out.println("Enter Account No : ");
				long accNo = sc.nextLong();
				System.out.println("Enter IFSC code : ");
				String ifsc = sc.next();
				System.out.println("Enter Bank name : ");
				String bank = sc.next();
				System.out.println("Is salary account say true/false");// true->zero
				boolean isAcc = sc.nextBoolean();
				System.out.println("Enter  Balance  : ");
				double balance = sc.nextDouble();
				System.out.println("Opening date ,,dd/mm/yy,");
				String opendate = sc.next();//

				SavingsAccount sa = new SavingsAccount(accNo, ifsc, bank, balance, opendate, isAcc);
				customer.setSavingsAccount(sa);

				break;
			case 3:
				System.out.println("Enter Your Bank Account No ");
				int acNo = sc.nextInt();
				System.out.println(
						"1.Show Balance \n2.Deposit Amount \n3.Withdraw Money \n4.Fixed Deposit Intrest \n5.Salary Account intrest");
				int n = sc.nextInt();
				switch (n) {
				case 1:
					System.out.println("Your Account Balance is : " + daodb.showBalance(acNo));
					break;
				case 2:
					System.out.println("Enter How much amount u want to deposit");
					int amt = sc.nextInt();
					daodb.depositMoney(acNo, amt);
					break;
				case 3:
					System.out.println("Enter How much amount u want to Withdraw");
					int w = sc.nextInt();
					daodb.withdrawMoney(acNo, w);
					break;
				case 4:
					fix.calculateInterest();
					break;
				case 5:
					sa1.calculateInterest();
					break;
				}

				break;
			case 4: // sort data using collection framework
				System.out.println("On which basis you want to Sort ,,1.CustomerName 2.Bank Balance.");
				int sortNo = sc.nextInt();
				switch (sortNo) {

				case 1:
					System.out.println("Customer list on Basis Customer basis,");
					Collections.sort(clist, (o1, o2) -> o1.getCustName().compareTo(o2.getCustName()));
					for (Customer c : clist) {
						System.out.println(c.getCustId() + "  " + c.getCustName() + "  " + c.getAge());
					}
					break;
				case 2:
					System.out.println("Customer list on Basis of bank balance,");
					clist.sort((e1, e2) -> Double.compare(e1.getSavingsAccount().getBalance(),
							e2.getSavingsAccount().getBalance()));
					for (Customer c : clist) {
						System.out.println(c.getCustId() + "  " + c.getCustName() + "  " + c.getAdharNo() + "  "
								+ c.getSavingsAccount().getAccNo());
					}
					break;
				}
				break;

			case 5:
				System.out.println("Enter Data Store Choice 1.Database Stoarage.2.FileSystem Storage");
				int datachoice = sc.nextInt();
				switch (datachoice) {
				case 1:

					daodb.saveAllCustomers(customer);
					break;
				case 2:

					try { // exception handle
						fd.saveAllCustomers(customer);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

				}

				break;

			case 6:
				System.out.println("Enter choice from where you want to get data. 1.From Database 2.From file sytem");
				int dbchoice = sc.nextInt();
				switch (dbchoice) {
				case 1:
					daodb.retriveAllCustomers();
					break;
				case 2: // exception handle
					try {
						fd.retriveAllCustomers();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}

				break;
			case 7:
				System.out.println("Enter Customer name to Search : ");
				String cname = sc.next();
				daodb.searchCustomers(cname);
				break;
			case 8:
				System.out.println("Thanks");
				System.exit(0);

			}
		}
	}
}
