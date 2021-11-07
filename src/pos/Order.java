package pos;

import java.util.ArrayList;
import java.util.Date;

public class Order {
	
	protected String date;
	protected int orderID;
	protected String staffName;
	protected String employeeID;
	protected double totalPrice;
	protected String staffGender;
	protected String staffJobTitle;
	protected int tableNo;
	protected String time;
	protected ArrayList<OrderItemWrapper> menuItemIDList = new ArrayList<OrderItemWrapper>();
	
	public Order(String date, int orderID, String staffName, String employeeID, 
			double price, String staffGender, String staffJobTitle, int tableNo, String time, ArrayList<OrderItemWrapper> menuItemIDList) {
		
		this.setDate(date);
		this.setOrderID(orderID);
		this.setStaffName(staffName);
		this.setEmployeeID(employeeID);
		this.setTotalPrice(price);
		this.setStaffGender(staffGender);
		this.setStaffJobTitle(staffJobTitle);
		this.setTableNo(tableNo);
		this.setTime(time);
		this.setMenuItemIDList(menuItemIDList);
		
	}
	
	public Order() {
		
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public double getTotalPrice() {
		double temp = 0;
		for (OrderItemWrapper oiw : menuItemIDList) {
			temp += oiw.getItemPrices();
		}
		return temp;
	}

	public void setTotalPrice(double price) {
		this.totalPrice = price;
	}

	public String getStaffGender() {
		return staffGender;
	}

	public void setStaffGender(String staffGender) {
		this.staffGender = staffGender;
	}

	public String getStaffJobTitle() {
		return staffJobTitle;
	}

	public void setStaffJobTitle(String staffJobTitle) {
		this.staffJobTitle = staffJobTitle;
	}

	public int getTableNo() {
		return tableNo;
	}

	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public ArrayList<OrderItemWrapper> getMenuItemIDList() {
		return menuItemIDList;
	}

	public void setMenuItemIDList(ArrayList<OrderItemWrapper> menuItemIDList) {
		this.menuItemIDList = menuItemIDList;
	}
	
}
