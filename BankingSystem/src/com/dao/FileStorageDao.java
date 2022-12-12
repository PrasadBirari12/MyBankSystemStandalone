package com.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.model.Customer;
import com.model.Idao;

public class FileStorageDao implements Idao {

	@Override
	public void saveAllCustomers(Customer cust) throws Exception {

		FileOutputStream f = new FileOutputStream("test.txt");
		ObjectOutputStream out = new ObjectOutputStream(f);
		out.writeObject(cust);
		out.flush();
		out.close();
		f.close();
		System.out.println("Data saved  in File");
	}

	@Override
	public void retriveAllCustomers() throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("test.txt"));
		Customer s = (Customer) in.readObject();
		List<Customer> clist = new ArrayList<Customer>();
		clist.add(s);

		System.out.println("All Customers In Bank System");
		for (Customer c : clist) {
			System.out.println(c.getCustId() + " " + c.getCustName() + " " + c.getAdharNo() + " " + c.getDob() + " "
					+ c.getAge() + "  " + c.getSavingsAccount().getAccNo() + " " + c.getSavingsAccount().getBalance());
		}
		in.close();

	}

}
