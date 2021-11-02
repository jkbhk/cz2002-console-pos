package pos;

import java.util.*;

public class CustomerManager {
	
	private Customer customer;
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	public static CustomerManager instance;
	
	public CustomerManager()
	{
		instance = this;
	}
	
	public Customer getCustomer(String customerID)
	{
		int x = 0;
		customer = null;
		if (customerList.isEmpty())
		{
			//System.out.println("customerList is Empty");
			return customer = null;
		}
		else 
		{
			//System.out.println("customerList is not Empty");
			for (x = 0; x < customerList.size(); x++)
			{
				System.out.println("Checking loop"+x);
				if (customerList.get(x).getCustomerID().equals(customerID))
				{
					//System.out.println("There is a customerID that is the same");
					customer = customerList.get(x);
				}
			}
		}
		
		return customer;
		
			
		
		
	}
	
	public void createCustomer(String name, String contactNo) // TO-DO to review again of creating customer
	{
		
		Customer customer = new Customer();
		customer.setName(name);
		customer.setContactNo(contactNo);
		if (!customerList.isEmpty())
		{
			
			customer.setMembershipID(Integer.toString(Integer.parseInt(customerList.get(customerList.size()-1).getMembershipID()) +1 ));
			String custNewID = Integer.toString( Integer.parseInt(customerList.get(customerList.size()-1).getCustomerID()) + 1);
			customer.setCustomerID(custNewID);
			customerList.add(customer);
	
		}
		else 
		{
			customer.setMembershipID("1");
			customer.setCustomerID("1");
			customerList.add(customer);
		}
	}
	
	public void deleteCustomer(String customerID) //Delete Customer but custID and membershipID is not reusable
	{
		int indexNo = Integer.parseInt(customerID)-1;
		Customer deletedCust = customerList.get(indexNo);
		customerList.remove(indexNo);
		
	}
	
	public void getCustomerList()
	{
		if (customerList.size() < 1)
		{
			System.out.println("The List is Empty!");
		}
		
		else 
		{
			for (int x = 0; x < customerList.size(); x++)
			{
				System.out.println(x+1 + ". Customer:");
				System.out.println("Name: " + customerList.get(x).getName());
				System.out.println("Contact Number: " + customerList.get(x).getContactNo());
				System.out.println("CustomerID: " + customerList.get(x).getCustomerID());
				System.out.println("MemebershipID: " + customerList.get(x).getMembershipID());
			}
		}
	}
	
	public String getCustomerID(String name)
	{
		if (!customerList.isEmpty())
		{
			for (int x = 0; x < customerList.size(); x++)
			{
				if (customerList.get(x).getName() == name)
				{
					return customerList.get(x).getCustomerID();
				}
			}
		}
		else 
		{
			System.out.println("Customer List is Empty");
			return null;
		}
		
		return null;
	}

}
