package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.exception.MyException;
import com.jdbcConnection.MyConnection;
import com.model.Customer;
import com.model.Idao;
import com.model.SavingsAccount;

public class DataBaseStorageDao implements Idao {

	Connection connection = MyConnection.getConnection();

	@Override
	public void saveAllCustomers(Customer cust) // insert all details
	{
		List<Customer> clist = new ArrayList<Customer>();
		clist.add(cust);
		try {
			for (Customer c : clist) {

				PreparedStatement ps = connection.prepareStatement("insert into customer values (" + c.getCustId()
						+ ",'" + c.getCustName() + "'," + c.getAge() + ",'" + c.getAdharNo() + "'," + c.getMobileNo()
						+ ",'" + c.getDob() + "'," + c.getSavingsAccount().getAccNo() + ",'"
						+ c.getSavingsAccount().getIfscNo() + "','" + c.getSavingsAccount().getBankName() + "',"
						+ c.getSavingsAccount().getBalance() + ",'" + c.getSavingsAccount().getOpeningDate() + "',"
						+ c.getSavingsAccount().isSalaryAcount() + ")");
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("data saved in database successfully");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void retriveAllCustomers() // show all customers
	{
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM customer");

			Set customers = new HashSet();

			while (rs.next()) {
				Customer customer = extractCustomerFromResultSet(rs);
				customers.add(customer);
			}
			System.out.println("All customers in bank ,,,,,,,,,,,");
			Iterator i = customers.iterator();
			while (i.hasNext()) {
				System.out.println(i.next());
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	private Customer extractCustomerFromResultSet(ResultSet rs) throws SQLException { // result set extractor
		Customer customer = new Customer();

		customer.setCustId(rs.getInt("custId"));
		customer.setCustName(rs.getString("customername"));
		customer.setAdharNo(rs.getString("adharNo"));
		customer.setAge(rs.getInt("age"));
		customer.setDob(null);
		customer.setMobileNo(rs.getInt("MobileNo"));

		SavingsAccount account = new SavingsAccount();
		account.setAccNo(rs.getInt("accountNo"));
		account.setBalance(rs.getDouble("balance"));
		account.setBankName(rs.getString("BankName"));
		account.setIfscNo(rs.getString("ifscCode"));
		account.setOpeningDate(rs.getString("OpenDate"));
		account.setSalaryAcount(rs.getBoolean("isSalary"));
		customer.setSavingsAccount(account);
		return customer;
	}

	public void searchCustomers(String name) // search customer by name
	{
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from customer where customername='" + name + "'");
			Set customers = new HashSet();
			while (rs.next()) {
				Customer customer = extractCustomerFromResultSet(rs);
				customers.add(customer);
			}
				try {
						if (customers.isEmpty()) {
							throw new MyException("Customer not found");
						}
						Iterator s = customers.iterator();
		
						while (s.hasNext()) {
							System.out.println(s.next());
						}
						System.out.println();
				} catch (MyException e) {
					e.printStackTrace();
				}

		} 
		catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public double showBalance(int acNo) {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from customer where accountNo=" + acNo + "");

			List<Customer> balance = new ArrayList<Customer>();
			while (rs.next()) {
				Customer customer = extractCustomerFromResultSet(rs);
				balance.add(customer);
			}
			for (Customer bank : balance) {
				return bank.getSavingsAccount().getBalance();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	public void depositMoney(int acNo, int money) {
		double balance = showBalance(acNo);
		double total = balance + money;
		try {
			PreparedStatement ps = connection
					.prepareStatement("update customer set balance=" + total + " where accountNo=" + acNo + "");

			int i = ps.executeUpdate();

			if (i == 1) {
				System.out.println("Deposited in " + acNo + "& Total Balance is  " + total);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

//withdrawa money
	public void withdrawMoney(int acNo, int wamount) {
		boolean isSal = false;
		double accBalance = 0;
		double total = 0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from customer where accountNo=" + acNo + "");

			List<Customer> balance = new ArrayList<Customer>();
			while (rs.next()) {
				Customer customer = extractCustomerFromResultSet(rs);
				balance.add(customer);
			}
			for (Customer bank : balance) {
				isSal = bank.getSavingsAccount().isSalaryAcount();
				accBalance = bank.getSavingsAccount().getBalance();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		if (isSal == true && accBalance >= wamount) {
			total = accBalance - wamount;
			updateWithdraw(total, acNo);
		} else if (isSal == false && accBalance >= (wamount + 5000)) {
			total = accBalance - wamount;
			updateWithdraw(total, acNo);
		} else {
			System.out.println("=========Not sufficent amount to withdraw=======");
		}

	}

	// update withdraw amount
	public void updateWithdraw(double total, int acNo) {
		try {
			PreparedStatement ps = connection
					.prepareStatement("update customer set balance=" + total + " where accountNo=" + acNo + "");

			int i = ps.executeUpdate();

			if (i == 1) {
				System.out.println("withdraw in " + acNo + "& Total Balance is  " + total);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
