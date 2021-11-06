package pos;

import java.util.ArrayList;

public class OrderManager{
	
	public static OrderManager instance;
	private Order currentOrder;
	private ArrayList<Order> orderList = new ArrayList<Order>();
	
	public OrderManager() {
		instance = this;
	}
	
	public ArrayList<Order> getOrderList(){
		
		return this.orderList;
	}
	
	public Order getOrder(int tableNo) {
				
		Order order = null;
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
	
	public Order getCurrentOrder() {
		return currentOrder;
	}
	
	public boolean deleteOrder(int tableNo) {
		boolean result = false;
		
		if (orderList.isEmpty()) {
			System.out.println("Order List is empty, unable to delete order.");
			result = false;
		}
		else {
			
			for (int i = 0; i < orderList.size(); i++) {
				if (orderList.get(i).getTableNo() == tableNo) {
					
					orderList.remove(i);
					System.out.println("Order is deleted.");
					result = true;
				}
			}
		}
		
		return result;
	}
	
	public void startNewOrder() {
		currentOrder = new Order();
		
	}
	
	public void addNewOrder(Order o) {
		orderList.add(o);
	}

}
