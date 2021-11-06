package pos;

import java.util.ArrayList;

public class OrderManager{
	
	public static OrderManager instance;
	private Order order;
	private ArrayList<Order> orderList;
	
	public OrderManager() {
		instance = this;
	}
	
	public ArrayList<Order> getOrderList(){
		
		return this.orderList;
	}
	
	public Order getOrder(int tableNo) {
		
		order = null;
		if (orderList.isEmpty()) {
			
			return order = null;
		}
		else {
			
			for (int i = 0; i < orderList.size(); i++) {
				
				if (orderList.get(i).getTableNo() == tableNo) {
					
					order = orderList.get(i);
					
				}
			}
		}
		
		return order;
	}
	
	public boolean deleteOrder(int tableNo) {
		
		if (orderList.isEmpty()) {
			System.out.println("Order List is empty, unable to delete order.");
		}
		else {
			
			for (int i = 0; i < orderList.size(); i++) {
				if (orderList.get(i).getTableNo() == tableNo) {
					
					orderList.remove(i);
					System.out.println("Order is deleted.");
				}
			}
		}
		
		return false;
	}
	
	public void addOrder(Order o) {
		orderList.add(o);
		System.out.println("Order successfully added.");
	}
	
	/*
	public Order getCurrentOrder() {
		
		
	}
	*/
	
	public void displayOrder() {
		
		if (orderList.isEmpty()) {
			System.out.println("Order is empty, there's nothing to display.");
		}
		else {
			
			for (int i = 0; i < orderList.size(); i++) {
				Order temp = orderList.get(i);
				System.out.println((i+1) + ") " + "Created by: " + temp.getStaffName() + " | " + temp.getStaffGender() + " | " + temp.getStaffJobTitle());
				
				if (temp.getMenuItemIDList().isEmpty()) {
					System.out.println("There's no items in orders.");
				}
				else {
					
					for (int j = 0; j < temp.getMenuItemIDList().size(); j++) {
						
						
					}
					
				}
				
				
			}
		}
		
	}
}
