package pos;

import java.util.ArrayList;

public class OrderManager{
	
	public static OrderManager instance;
	private Order currentOrder;
	private ArrayList<Order> orderList = new ArrayList<Order>();
	private ArrayList<Order> incompleteOrderList = new ArrayList<Order>();
	private GenericDao<Order> dao;
	
	public OrderManager(GenericDao<Order> d) {
		instance = this;
		this.dao = d;
		orderList = d.read();
		
	}
	
	
	public ArrayList<Order> getIncompleteOrderList(){
		return this.incompleteOrderList;
	}
	
	public Order getOrderFromIncompleteOrderList(int tableNo) {
				
		Order order = null;
		if (incompleteOrderList.isEmpty()) {
			
			return order = null;
		}
		else {
			
			for (int i = 0; i < incompleteOrderList.size(); i++) {
				
				if (incompleteOrderList.get(i).getTableNo() == tableNo) {
					
					order = incompleteOrderList.get(i);
					
				}
			}
		}
		
		return order;
	}
	
	public Order getCurrentOrder() {
		return currentOrder;
	}
	
	public Order getIncompleteOrder(int tableNo) {
		for (Order o : incompleteOrderList) {
			if (o.getTableNo() == tableNo) {
				return o;
			}
		}
		
		return null;
	}
	
	public void setCurrentOrder(Order o) {
		currentOrder = o;
	}
	
	public boolean deleteOrderFromIncompleteList(int tableNo) {
		boolean result = false;
		
		if (incompleteOrderList.isEmpty()) {
			System.out.println("Order List is empty, unable to delete order.");
			result = false;
		}
		else {
			
			for (int i = 0; i < incompleteOrderList.size(); i++) {
				if (incompleteOrderList.get(i).getTableNo() == tableNo) {
					
					incompleteOrderList.remove(i);
					System.out.println("Order is deleted.");
					result = true;
				}
			}
		}
		
		return result;
	}
	
	public void startNewOrder() {
		incompleteOrderList.add(currentOrder);
	}
	
	public void addNewOrder(Order o) {
		orderList.add(o);
	}
	
	public void addIncompleteOrder(Order o) {
		incompleteOrderList.add(o);
	}
	
	public Order getOrder(String id) {
		for (Order o : orderList) {
			if (o.getOrderID().equals(id)) {
				return o;
			}
		}
		return null;
	}
	
	public void save() {
		dao.write(orderList);
	}

}
