package pos;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
							
							//wrapper.calculateItemPrices(m.getPrice());
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
										"  Qty: " + oiw.getQuantity() + " Item Prices: $" + String.format("%.2f", oiw.getItemPrices()));
						
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
										//temp.calculateItemPrices(MenuManager.instance.getMenuItem(temp.getMenuItemID()).getPrice());
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
										"  Qty: " + oiw.getQuantity() + " Item Price: $" + String.format("%.2f", oiw.getItemPrices() * oiw.getQuantity()));
					}
					System.out.println("------------------------------");
					calculateCurrentTotal();
					System.out.println("Sub Total Price: $" + String.format("%.2f", OrderManager.instance.getCurrentOrder().getTotalPrice()));
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
				
				Order o = OrderManager.instance.getCurrentOrder();
				if (o.getMenuItemIDList().isEmpty()){
					System.out.println("You have no item to checkout.");
				}
				else {
					//OrderManager.instance.addNewOrder(o);
					System.out.println("You've successfully checked out.");
					calculateCurrentTotal();
					String generated = IDGenerator.GenerateUniqueID();
					
					String orderID = OrderManager.instance.getCurrentOrder().getOrderID();
					String staffID = StaffManager.instance.getCurrentStaff().getStaffName();
					//String customerID = "random";
					String date = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
					String time = LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
					
					boolean isMember = enquireMembership();
					
					double gstPrice = (GST_RATE/100) * o.getTotalPrice();
					double membershipDiscountPrice = isMember ? (MEMBERSHIP_RATE/100) * o.getTotalPrice() : 0;
					double serviceChargePrice = (SERVICE_RATE/100) * o.getTotalPrice();
					double totalPrice = o.getTotalPrice();
					double netTotal = Math.round((totalPrice * 100.0))/100.0 + Math.round((gstPrice * 100.0))/100.0 + Math.round((serviceChargePrice * 100.0))/100.0 - Math.round((membershipDiscountPrice * 100.0))/100.0;
					
					Invoice i = new Invoice(generated,orderID,staffID,date,time,GST_RATE, gstPrice, MEMBERSHIP_RATE, membershipDiscountPrice, SERVICE_RATE, serviceChargePrice, totalPrice, netTotal, isMember); 
					
					GenericDataPrinter.printTemplate(new TemplateAAdapter(i));
					
					boolean okay = false;
					
					while(okay == false)
						if (PaymentManager.instance.requestPayment(netTotal)) {
							InvoiceManager.instance.addInvoiceToList(i);
							OrderManager.instance.addNewOrder(o);
							OrderManager.instance.deleteOrderFromIncompleteList(o.tableNo);
							TableManager.instance.getTable(o.tableNo).setStatus(Table.STATUS.EMPTY);
							okay = true;
						}
						else {
							System.out.println("Failed to pay.");
							System.out.println("Please enter the appropriate amount again.");
						}
					
					orderAssistant.terminate();
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
		
		setCurrentOrder();
		//OrderManager.instance.startNewOrder();
		//OrderManager.instance.getCurrentOrder().setOrderID(IDGenerator.GenerateUniqueID());
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
	
	private boolean enquireMembership() {
		
		boolean isMember = false;
		
		System.out.println("Are you a member?\n1) Yes\n2) No");
		int c = Integer.parseInt(Application.scanner.nextLine());
		if(c == 1) {
			System.out.println("Enter member code");
			String code = Application.scanner.nextLine();
			
			if(true) {
				System.out.println("Membership status identified.");
				isMember = true;
			}
		}
		
		return isMember;
		
	}
	
	
	private void calculateCurrentTotal() {
		Order o = OrderManager.instance.getCurrentOrder();
		
		double total = 0;
		
		for (OrderItemWrapper oiw : o.getMenuItemIDList()) {
			total += oiw.getItemPrices() * oiw.getQuantity();
		}
		o.setTotalPrice(total);
	}
	
	private void setCurrentOrder() {
		boolean isNew = true;
		
		TableManager.instance.printTables();
		System.out.println("Select a table number: ");
		int choice = Integer.parseInt(Application.scanner.nextLine());
		
		if (choice > 0 && choice <= TableManager.instance.getTableList().size()) {
			boolean exist = checkTableOrder(choice);
			
			if (exist) {
				OrderManager.instance.setCurrentOrder(OrderManager.instance.getIncompleteOrder(choice));
				System.out.println("Table number: " + OrderManager.instance.getCurrentOrder().tableNo);
				System.out.println("Editing previous order from table number.");
			}
			else {
				Order newOrder = new Order(IDGenerator.GenerateUniqueID(), choice);
				OrderManager.instance.addIncompleteOrder(newOrder);
				OrderManager.instance.setCurrentOrder(newOrder);				
				System.out.println("Table number: " + OrderManager.instance.getCurrentOrder().tableNo);
				System.out.println("Creating new order from table number.");
				TableManager.instance.getTable(choice).setStatus(Table.STATUS.OCCUPIED);
			}
		}
		else {
			System.out.println("Please select the correct table.");
			setCurrentOrder();
		}
	}
	
	private boolean checkTableOrder(int tableNo) {
		
		for (Order o : OrderManager.instance.getIncompleteOrderList()) {
			
			if (o.tableNo == tableNo)
				return true;
		}
		
		return false;
	}
	
}
