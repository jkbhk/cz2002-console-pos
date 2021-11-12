package pos;
import java.util.*;

public class CustomerInteractable implements IInteractable {
	
	InteractableComponent customerAssistant = new InteractableComponent("Back",true);
	
	public CustomerInteractable()
	{
		customerAssistant.addInteractable(new IInteractable() {

			@Override
			public void handleInput() {
				// TODO Auto-generated method stub
				System.out.println("Enter Customer's Name");
				String name = Application.scanner.nextLine();
				System.out.println("Enter Customer's Contact Number");
				String contactNo = Application.scanner.nextLine();
				String customerID = IDGenerator.GenerateUniqueID();
				String membershipID = IDGenerator.GenerateUniqueAlphaNum(5);
				
				CustomerManager.instance.createCustomer(name, contactNo, membershipID, customerID,true);
				
			}

			@Override
			public String getTitle() {
				// TODO Auto-generated method stub
				return "Create New Customer" ;
			}
			
		});
		
		customerAssistant.addInteractable(new IInteractable() {

			@Override
			public void handleInput() {
				// TODO Auto-generated method stub
				System.out.println("Select Customer you wish to edit.");
				for (int x = 0; x < CustomerManager.instance.getCustomerList().size(); x++)
				{
					System.out.println((x+1) + ". Name: " + CustomerManager.instance.getCustomerList().get(x).getName()+ " Contact Number: " + CustomerManager.instance.getCustomerList().get(x).getContactNo() );
				}
				
				int choice = Integer.parseInt(Application.scanner.nextLine());
				
				while (choice -1 < 0 || choice -1 > CustomerManager.instance.getCustomerList().size()-1)
				{
					System.out.println("Please Select Customer you wish to edit again!");
					choice = Integer.parseInt(Application.scanner.nextLine());
				}
				
				Customer toEdit = CustomerManager.instance.getCustomerList().get(choice-1);
				System.out.println("Select Details to Edit");
				System.out.println("1) Customer's Name");
				System.out.println("2) Customer's Contact Number");
				
				int choiceEdit = Integer.parseInt(Application.scanner.nextLine());
				if (choiceEdit == 1)
				{
					System.out.println("Enter Customer's updated name");
					
					String oldName = toEdit.getName();
					String newName = Application.scanner.nextLine();
					toEdit.setName(newName);
					System.out.println("Customer's Name " + oldName + " is Updated to " + newName);
				}
				
				else if (choiceEdit == 2)
				{
					System.out.println("Enter Customer's updated Contact Number");
					String newNumber = Application.scanner.nextLine();
					String oldNumber = toEdit.getContactNo();
					toEdit.setContactNo(newNumber);
					System.out.println("Customer's Number " + oldNumber + " is Updated to " + newNumber);
				}
				
				System.out.println("Customer's information has been edited");
			}

			@Override
			public String getTitle() {
				// TODO Auto-generated method stub
				return "Edit Customer Details" ;
			}
			
		});
		
		customerAssistant.addInteractable(new IInteractable() {

			@Override
			public void handleInput() {
				// TODO Auto-generated method stub
				if (CustomerManager.instance.getCustomerList().size() == 0)
				{
					System.out.println("No customers to delete.");
				}
				
				else 
				{
					System.out.println("Select Customer you wish to delete.");
					for (int x = 0; x < CustomerManager.instance.getCustomerList().size(); x++)
					{
						System.out.println((x+1) + ". " + CustomerManager.instance.getCustomerList().get(x).getName()+ " " + CustomerManager.instance.getCustomerList().get(x).getContactNo() );
					}
					
					int choice = Integer.parseInt(Application.scanner.nextLine());
					
					while (choice -1 < 0 || choice -1 > CustomerManager.instance.getCustomerList().size()-1)
					{
						System.out.println("Please Select Customer you wish to delete again!");
						choice = Integer.parseInt(Application.scanner.nextLine());
					}
					
					
					CustomerManager.instance.deleteCustomer(choice-1);
				}
			}

			@Override
			public String getTitle() {
				// TODO Auto-generated method stub
				return "Delete Customer" ;
			}
			
		});
		
		customerAssistant.addInteractable(new IInteractable() {

			@Override
			public void handleInput() {
				// TODO Auto-generated method stub
				CustomerManager.instance.displayCustomerList();
			}

			@Override
			public String getTitle() {
				// TODO Auto-generated method stub
				return "View Customer List" ;
			}
			
		});
	}
	
	@Override
	public void handleInput() {
		customerAssistant.start();
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Manage Customers" ;
	}

}
