package pos;

import java.util.ArrayList;

public class OrderInteractable implements IInteractable{
	
	InteractableComponent orderAssistant = new InteractableComponent("Back", true);
	
	private final double GST_RATE = 7;
	private final double SERVICE_RATE = 10;
	private final double MEMBERSHIP_RATE = 5;
	
	public OrderInteractable() {
		
		//Add Menu Item
		orderAssistant.addInteractable(new IInteractable() {

			@Override
			public void handleInput() {
				//System.out.println("Enter the staff name for this order: ");
				//String name = Application.scanner.nextLine();
				//System.out.println("Enter the staff's gender (M/F): ");
				//check if input is M or F only
				//String gender = Application.scanner.nextLine();
				//System.out.println("Enter the staff's job title: ");
				//String staffJobTitle = Application.scanner.nextLine();
				
				Order o = OrderManager.instance.getCurrentOrder();
				MenuManager.instance.displayMenu();
				System.out.println("Enter the menu items that are included in this menu: ");
				int choice = Integer.parseInt(Application.scanner.nextLine());
				MenuItem m = MenuManager.instance.getMenuItem(choice-1);
				
				if (m != null) {
					boolean isDuplicate = false;
					
					for (OrderItemWrapper wrapper : o.getMenuItemIDList()) {
						if (wrapper.getMenuItemID().equals(m.getMenuItemID())) {
							wrapper.incrementQuantity(1);
							wrapper.calculateItemPrices(m.getPrice());
							System.out.println("Quantity successfully updated into the order.");
							isDuplicate = true;
							break;
						}
					}
					
					if (!isDuplicate) {
						
						OrderItemWrapper oiw = new OrderItemWrapper(m.getMenuItemID(), 1, m.getPrice());
						o.getMenuItemIDList().add(oiw);
						System.out.println("Item successfully added into the order.");
					}
					
				}
				else {
					System.out.println("Invalid choice.");
				}
				
			}

			@Override
			public String getTitle() {
				
				return "Add Menu Item";
			}

		});
		
		//Remove Menu Item
		orderAssistant.addInteractable(new IInteractable() {

			@Override
			public void handleInput() {
				
				Order o = OrderManager.instance.getCurrentOrder();
				if (o.getMenuItemIDList().size() == 0) {
					System.out.println("There's no item to remove.");
				}
				else {
					for (OrderItemWrapper oiw : o.getMenuItemIDList()) {
						int count = 1;
						System.out.println(count + ") " + MenuManager.instance.getMenuItem(oiw.getMenuItemID()).getName() 
								+ "  $" + String.format("%.2f", MenuManager.instance.getMenuItem(oiw.getMenuItemID()).getPrice()) + 
										"  Qty: " + oiw.getQuantity() + " Item Prices: $" + oiw.getItemPrices());
						
					}
					System.out.println("Enter the menu items that you want to delete in this order: ");
					int choice = Integer.parseInt(Application.scanner.nextLine());
					
					if (choice > 0 && choice <= o.getMenuItemIDList().size()) {
						if (o.getMenuItemIDList().get(choice-1).getQuantity() >= 1) {
							System.out.println("Do you wish to delete 1 or all of the quantity?");
							System.out.println("(1) to delete 1");
							System.out.println("(2) to delete all");
							int choiceDelete = Integer.parseInt(Application.scanner.nextLine());
							OrderItemWrapper temp = o.getMenuItemIDList().get(choice-1);
							
							if (temp != null) {
								if (choiceDelete == 1) {
									temp.decrementQuantity(1);
									
									if (temp.getQuantity() <= 0) {
										o.getMenuItemIDList().remove(choice-1);
										System.out.println("Quantity is zero, removing item.");
									}
									else {
										temp.calculateItemPrices(MenuManager.instance.getMenuItem(temp.getMenuItemID()).getPrice());
										System.out.println("Successfully decrease the item quantity by 1.");
									}
								}
								else if (choiceDelete == 2) {
									o.getMenuItemIDList().remove(choice-1);
									System.out.println("Successfully remove the item of choice.");
								}
							}
							
						}
					}
					else {
						System.out.println("Invalid choice.");
					}
				}
				
				
			}

			@Override
			public String getTitle() {
				
				return "Remove Menu Item";
			}

			
		});
		
		//Display All Menu Items
		orderAssistant.addInteractable(new IInteractable() {

			@Override
			public void handleInput() {
				
				
				Order o = OrderManager.instance.getCurrentOrder();
				
				if (o.getMenuItemIDList().size() == 0) {
					System.out.println("There's no items in this order.");
				}
				else {
					int count = 1;
					for (OrderItemWrapper oiw : o.getMenuItemIDList()) {
						System.out.println(count++ + ") " + MenuManager.instance.getMenuItem(oiw.getMenuItemID()).getName() 
								+ "  $" + String.format("%.2f", MenuManager.instance.getMenuItem(oiw.getMenuItemID()).getPrice()) + 
										"  Qty: " + oiw.getQuantity() + " Item Price: $" + oiw.getItemPrices());
					}
					System.out.println("------------------------------");
					System.out.println("Sub Total Price: $" + o.getTotalPrice());
					System.out.println("------------------------------");
				}
			
			}

			@Override
			public String getTitle() {
				
				return "Display All Menu Items";
			}

			
		});
		
		//Checkout Order
		orderAssistant.addInteractable(new IInteractable() {

			@Override
			public void handleInput() {
				
				System.out.println("Do you wish to confirm your check out?");
				System.out.println("(y) for yes");
				System.out.println("(n) for no");
				String again = Application.scanner.nextLine();
				
				if(again.equals("y")) {
					Order o = OrderManager.instance.getCurrentOrder();
					if (o.getMenuItemIDList().isEmpty()){
						System.out.println("You have no item to checkout.");
					}
					else {
						OrderManager.instance.addNewOrder(o);
						System.out.println("You've successfully checked out.");
						
						Invoice i = new Invoice(o.getDate(), o.getOrderID(), o.getStaffName(), o.getEmployeeID(), 
								o.getTotalPrice(), o.getStaffGender(), o.getStaffJobTitle(), o.getTableNo(), o.getTime(), 
								o.getMenuItemIDList(), GST_RATE, MEMBERSHIP_RATE, o.customerID);
						
						double netTotal = calculateTotalAmountPayable(i);
						printInvoice(i);
						
						boolean okay = false;
						
						while(okay == false)
							if (PaymentManager.instance.requestPayment(netTotal)) {
								InvoiceManager.instance.addInvoiceToList(i);
								okay = true;
							}
							else {
								System.out.println("Failed to pay.");
								System.out.println("Please enter the appropriate amount again.");
							}
						
						orderAssistant.terminate();
					}
				}
			}

			@Override
			public String getTitle() {
				
				return "Checkout Order";
			}

		});
		
	}
	
	
	@Override
	public void handleInput() {
		OrderManager.instance.startNewOrder();
		
		orderAssistant.start();
	}

	@Override
	public String getTitle() {
		
		return "Start Ordering";
	}
	
	private void occupyRandomTable() {
		//will go to the reservation manager to see if there's any reservation for the day.
		//for all the reservation, check if the reservation is within this timing.
		//if its in this timing, i'll filter the 
	}
	
	private double calculateTotalAmountPayable(Invoice i) {
		double result = 0;
		
		double totalPrice = i.getTotalPrice();
		double gst = (i.getGst()/100) * totalPrice;
		double membershipDiscount = (i.getMemberShipDiscount()/100) * totalPrice;
		
		result = totalPrice + membershipDiscount - gst;
		return result;
	}
	
	private double getApplicableRate(String customerID) {
		
		if (CustomerManager.instance.checkMembership(customerID)) {
			return MEMBERSHIP_RATE;
		}
		else
			return 0;
		
	}
	
	private double applyDiscount(double amount, Customer id) {
		
		return 0;
	}
	
	private void printInvoice(Invoice i) {
		
		String x = "=";
		String m = "";
		int number = 15;
		for (int j = 0; j < number; j++) {
			m += x;
		}
		
		String c = "-";
		String l = "";
		for (int w = 0; w < number; w++) {
			l += c;
		}
		
		String itemFormat = "| %-13s%-12s%-14s%-6s |\n";
		String titleFormat = "-19s%-19s";
		String calculationFormat = "%-40s%-2s\n";
		
		System.out.printf("==================== INVOICE ====================");
		System.out.println("");
		System.out.println(Application.storeName);
		System.out.println(Application.storeAddress);
		System.out.println(Application.storeNumber);
		System.out.println(m + m + m + "====");
		System.out.println("Order Number: " + i.getOrderID());
		System.out.println("Order Date: " + i.getDate());
		System.out.println("Order Time: " + i.getTime());
		System.out.println("Staff: " + i.getStaffName() + "  Gender: " + i.getStaffGender() + "  Job Title: " + i.getStaffJobTitle());
		System.out.println(m + m + m + "====");
		System.out.println("|"+ l + "- Item Details --" + l +"|");
		System.out.println("| Item      Quantity      UnitPrice      Amount |");
		
		i.printItems(itemFormat);
		
		System.out.println("|-" + l + l + l +"-|");
		System.out.println(l + l + l + "----");
		System.out.printf(calculationFormat, "Subtotal: ", "$" + String.format("%.2f", i.getTotalPrice()));
		
		if (CustomerManager.instance.checkMembership(i.customerID))
			System.out.printf(calculationFormat, "Membership Discount (" + i.getMemberShipDiscount() + "%):", "$" + String.format("%.2f", (i.getMemberShipDiscount()/100) * i.getTotalPrice()));
		
		System.out.printf(calculationFormat, "GST (" + i.getGst() + "%):", "$" + String.format("%.2f", (i.getGst()/100) * i.getTotalPrice()));
		System.out.printf(calculationFormat, "Total Amount Payable:", "$" + String.format("%.2f", calculateTotalAmountPayable(i)));
		System.out.println(m + m + m + "====");
	}
	
}
