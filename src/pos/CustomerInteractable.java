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
				Application.scanner.nextLine();
				String name = Application.scanner.nextLine();
				System.out.println("Enter Customer's Contact Number");
				String contactNo = Application.scanner.next();
				CustomerManager.instance.createCustomer(name, contactNo);
				System.out.println("CustomerID " + CustomerManager.instance.getCustomerID(name)  + " have been created");
			
				break;
			}
			
			case 2: 
			{
				CustomerManager.instance.editCustomer();
				break;
				
			}
			
			case 3:
			{
				CustomerManager.instance.deleteCustomer();
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
