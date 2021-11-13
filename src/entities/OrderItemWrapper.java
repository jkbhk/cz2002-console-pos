package entities;

public class OrderItemWrapper {
	
	private String menuItemID;
	private int quantity;
	private double itemPrices;
	
	public OrderItemWrapper(String menuItemID, int quantity, double itemPrices) {
		this.menuItemID = menuItemID;
		this.quantity = quantity;
		this.itemPrices = itemPrices;
	}
	
	public String getMenuItemID() {
		return menuItemID;
	}
	
	public void setMenuItemID(String menuItemID) {
		this.menuItemID = menuItemID;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getItemPrices() {
		return this.itemPrices;
	}
	
	public void setItemPrices(float itemPrices) {
		this.itemPrices = itemPrices;
	}
	
	public void incrementQuantity(int quantity) {
		this.quantity += quantity;
	}
	
	public void decrementQuantity(int quantity) {
		this.quantity -= quantity;
	}
	/*
	public void calculateItemPrices(double price) {
		this.itemPrices = quantity * price;
	}*/
}
