package entities;

import java.util.ArrayList;
 
public class Order {
	
	private String orderID;
	private double totalPrice;
	private int tableNo;
	private ArrayList<OrderItemWrapper> menuItemIDList = new ArrayList<OrderItemWrapper>();
	
	public Order() {
		
	}
	
	public Order(String orderID, int tableNo) {
		this.orderID = orderID;
		this.tableNo = tableNo;
		totalPrice = 0;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public double getTotalPrice() {
		
		return totalPrice;
	}

	public void setTotalPrice(double price) {
		this.totalPrice = price;
	}


	public int getTableNo() {
		return tableNo;
	}

	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

	public ArrayList<OrderItemWrapper> getMenuItemIDList() {
		return menuItemIDList;
	}

	public void setMenuItemIDList(ArrayList<OrderItemWrapper> menuItemIDList) {
		this.menuItemIDList = menuItemIDList;
	}
	 
}
