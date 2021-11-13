package managers;

import java.util.ArrayList;

import dao.GenericDao;
import entities.Order;

/**
 * 
 * Manages order object.
 *
 */
public class OrderManager{
	/**
	 * Globally accessed instance.
	 */
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
	
	/**
	 * @return ArrayList of incomplete order list.
	 * 
	 */
	
	public ArrayList<Order> getIncompleteOrderList(){
		return this.incompleteOrderList;
	}
	
	/**
	 * 
	 * @param tableNo Table number of Table object.
	 * @return ArrayList of completed order list.
	 */
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
	
	/**
	 * 
	 * @return Current Order.
	 */
	public Order getCurrentOrder() {
		return currentOrder;
	}
	
	/**
	 * 
	 * @param tableNo Table object's table number.
	 * @return Incomplete Order given parameter.
	 */
	public Order getIncompleteOrder(int tableNo) {
		for (Order o : incompleteOrderList) {
			if (o.getTableNo() == tableNo) {
				return o;
			}
		}
		
		return null;
	}
	/**
	 * Set current order given an order object.
	 * @param o Order Object.
	 */
	public void setCurrentOrder(Order o) {
		currentOrder = o;
	}
	
	/**
	 * 
	 * @param tableNo Table object's table number.
	 * @return boolean when successfully delete order from incomplete list given table number.
	 */
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
	
	/**
	 * Add order into incomplete order list when an order started.
	 */
	public void startNewOrder() {
		incompleteOrderList.add(currentOrder);
	}
	
	/**
	 * Add a given order object into order list.
	 * @param o Order Object.
	 */
	public void addNewOrder(Order o) {
		orderList.add(o);
	}
	
	/**
	 * Add a given order object into incomplete order list.
	 * @param o Order Object.
	 */
	public void addIncompleteOrder(Order o) {
		incompleteOrderList.add(o);
	}
	
	/**
	 * 
	 * @param id Uniquely generated order ID
	 * @return Order object given order id.
	 */
	public Order getOrder(String id) {
		
		Order temp = null;
		temp = getCompletedOrder(id);	
		if (temp == null)
		{
			temp = getIncompletedOrder(id);
		}
		
		return temp;
		
	}
	
	/**
	 * 
	 * @param id Uniquely generated order ID.
	 * @return Order object from order list.
	 */
	public Order getCompletedOrder(String id) {
		for (Order o : orderList) {
			if (o.getOrderID().equals(id)) {
				return o;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param id Uniquely generated order ID.
	 * @return Order object from incomplete order list.
	 */
	public Order getIncompletedOrder(String id) {
		for (Order o : incompleteOrderList) {
			if (o.getOrderID().equals(id)) {
				return o;
			}
		}
		return null;
	}
	
	/**
	 * Updates and saves order list.
	 */
	public void save() {
		dao.write(orderList);
	}

}
