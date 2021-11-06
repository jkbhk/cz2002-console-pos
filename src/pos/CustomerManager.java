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
	
	public void deleteCustomer() //Delete Customer but custID and membershipID is not reusable
	{
		
		if (customerList.isEmpty())
		{
			System.out.println("customerList is Empty. Unable to delete Customer.");
			
		}
		else 
		{
			System.out.println("Select Customer you wish to delete.");
			for (int x = 0; x < customerList.size(); x++)
			{
				System.out.println((x+1) + ". " + customerList.get(x).getName()+ " " + customerList.get(x).getContactNo() );
			}
			
			int choice = Integer.parseInt(Application.scanner.nextLine());
			while(choice > 0)
			{
				if (choice <= customerList.size())
				{
					customerList.remove(choice-1);
					System.out.println("Customer has been deleted");
					break;
				}
				
				else 
				{
					System.out.println("Select Customer Correctly or -1 (Back) to Exit.");
					choice = Integer.parseInt(Application.scanner.nextLine());
				}
			}
		}
		
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
	
	public void editCustomer()
	{
		if (customerList.isEmpty())
		{
			System.out.println("customerList is Empty. Unable to edit Customer details.");
			
		}
		else 
		{
			System.out.println("Select Customer you wish to edit.");
			for (int x = 0; x < customerList.size(); x++)
			{
				System.out.println((x+1) + ". Name: " + customerList.get(x).getName()+ " Contact Number: " + customerList.get(x).getContactNo() );
			}
			
			int choice = Application.scanner.nextInt();
			Customer toEdit = customerList.get(choice-1);
			System.out.println("Select Details to Edit");
			System.out.println("1) Customer's Name");
			System.out.println("2) Customer's Contact Number");
			
			int choiceEdit = Integer.parseInt(Application.scanner.nextLine());
			if (choiceEdit == 1)
			{
				System.out.println("Enter Customer's updated name");
				String oldName = toEdit.getName();
				Application.scanner.nextLine();
				String newName = Application.scanner.nextLine();
				toEdit.setName(newName);
				System.out.println("Customer's Name " + oldName + " is Updated to " + newName);
			}
			
			else if (choiceEdit == 2)
			{
				System.out.println("Enter Customer's updated Contact Number");
				String newNumber = Application.scanner.next();
				String oldNumber = toEdit.getContactNo();
				toEdit.setContactNo(newNumber);
				System.out.println("Customer's Number " + oldNumber + " is Updated to " + newNumber);
			}
			
			System.out.println("Customer's information has been edited");
		}
	}

}
