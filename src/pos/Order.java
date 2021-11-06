package pos;

import java.util.ArrayList;
import java.util.Date;

public class Order {
	
	private Date date;
	private int orderID;
	private String staffName;
	private String employeeID;
	private float price;
	private String staffGender;
	private String staffJobTitle;
	private int tableNo;
	private String time;
	private ArrayList<String> menuItemIDList;
	
	public Order(Date date, int orderID, String staffName, String employeeID, 
			float price, String staffGender, String staffJobTitle, int tableNo, String time, ArrayList<String> menuItemIDList) {
		
		this.setDate(date);
		this.setOrderID(orderID);
		this.setStaffName(staffName);
		this.setEmployeeID(employeeID);
		this.setPrice(price);
		this.setStaffGender(staffGender);
		this.setStaffJobTitle(staffJobTitle);
		this.setTableNo(tableNo);
		this.setTime(time);
		this.setMenuItemIDList(menuItemIDList);
		
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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

	public ArrayList<String> getMenuItemIDList() {
		return menuItemIDList;
	}

	public void setMenuItemIDList(ArrayList<String> menuItemIDList) {
		this.menuItemIDList = menuItemIDList;
	}

	
}
