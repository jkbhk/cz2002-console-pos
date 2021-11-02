package pos;
import java.util.*;

public class CustomerInteractable implements IInteractable {

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		System.out.println("Select your choice that would like to do for customer");
		System.out.println("1) Create New Customer"); //Working
		System.out.println("2) Edit Customer Details"); //Working
		System.out.println("3) Delete Customer"); //Working
		System.out.println("4) View Customer List"); // Working
		System.out.println("5) Exit.");
		int choice = Application.scanner.nextInt();
		
		while (choice != 5)
		{
			switch(choice)
			{
			case 1: 
			{
				System.out.println("Enter Customer's Name");
				String name = Application.scanner.next();
				System.out.println("Enter Customer's Contact Number");
				String contactNo = Application.scanner.next();
				CustomerManager.instance.createCustomer(name, contactNo);
				System.out.println("CustomerID " + CustomerManager.instance.getCustomerID(name)  + " have been created");
			
				break;
			}
			
			case 2: 
			{
				System.out.println("Enter CustomerID");
				String custID = Application.scanner.next();
				System.out.println("Select Details to Edit");
				System.out.println("1) Customer's Name");
				System.out.println("2) Customer's Contact Number");
				System.out.println("3) Exit.");
				int choice1 = Application.scanner.nextInt();
				while(choice1 != 3)
				{
					switch(choice1)
					{
						case 1:
						{
							System.out.println("Enter Customer's updated name");
							String oldName = "Mr..";
							String newName = Application.scanner.next(); //TODO Cannot spaces if not will crash
							Customer currCust = CustomerManager.instance.getCustomer(custID);
							if (currCust != null)
							{
								oldName = currCust.getName();
							}
							else 
							{
								System.out.println("Invalid Customer");
								break;
							}
							
							System.out.println("This is new Name" + newName);
							CustomerManager.instance.getCustomer(custID).setName(newName);
							System.out.println("Customer's Name " + oldName + " is Updated to " + newName);
							break;
						}
						
						case 2: 
						{
							System.out.println("Enter Customer's updated Contact Number");
							String newNumber = Application.scanner.next();
							Customer currCust = CustomerManager.instance.getCustomer(custID);
							String oldNumber = currCust.getContactNo();
							currCust.setContactNo(newNumber);
							System.out.println("Customer's Number " + oldNumber + " is Updated to " + newNumber);
							break;
						}
						
					}
					break;
				}
				break;
				
			}
			
			case 3:
			{
				System.out.println("Enter CustomerID");
				String custID = Application.scanner.next();
				CustomerManager.instance.deleteCustomer(custID);
				System.out.println("Customer have been deleted.");
				break;
			}
			
			case 4:
			{
				CustomerManager.instance.getCustomerList();
			}
			}
			break;
		}
		
		System.out.println("Exiting CustomerManager");
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Manage Customers" ;
	}

}
