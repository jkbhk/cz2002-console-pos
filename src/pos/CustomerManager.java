package pos;

import java.util.*;

public class CustomerManager implements ISerializable {
	
	private Dao<Customer> customerDao;
	private Customer customer;
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	public static CustomerManager instance;
	
	public CustomerManager(Dao<Customer> customerDao)
	{
		instance = this;
		this.customerDao = customerDao;
		customerList = customerDao.read();
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
		customer.setMembershipID(IDGenerator.GenerateUniqueID());
		customer.setCustomerID(IDGenerator.GenerateUniqueID());
		customerList.add(customer);
			
		
	}
	
	public void deleteCustomer(int index) //Delete Customer but custID and membershipID is not reusable
	{
		
		if (customerList.isEmpty())
		{
			System.out.println("customerList is Empty. Unable to delete Customer.");
			
		}
		
		else if (index >= 0 && index <customerList.size())
		{
			customerList.remove(index);
		}
	}
	
	public void displayCustomerList()
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
				System.out.println("MembershipID: " + customerList.get(x).getMembershipID());
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
	
	
	public ArrayList<Customer> getCustomerList()
	{
		return customerList;
	}

	@Override
	public void serialize() {
		// TODO Auto-generated method stub
		customerDao.write(customerList);
	}

}
