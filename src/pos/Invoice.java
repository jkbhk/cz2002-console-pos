package pos;

import java.util.ArrayList;

public class Invoice extends Order {
	
	private double gst;
	private double membershipDiscount;


	public Invoice(String date, int orderID, String staffName, String employeeID, double totalPrice, 
			String staffGender, String staffJobTitle, int tableNo, String time, ArrayList<OrderItemWrapper> oiw, double gst, double membershipDiscount) {
		super(date, orderID, staffName, employeeID, totalPrice, 
				staffGender,staffJobTitle, tableNo, time, oiw);

		this.gst = gst;
		this.membershipDiscount = membershipDiscount;
	}

	public double getGst() {
		return gst;
	}

	public void setGst(double gst) {
		this.gst = gst;
	}

	public double getMemberShipDiscount() {
		return membershipDiscount;
	}

	public void setMemberShipDiscount(double membershipDiscount) {
		this.membershipDiscount = membershipDiscount;
	}

	public void printItems(String format) {		
		for (OrderItemWrapper oiw : menuItemIDList) {
			String itemName = MenuManager.instance.getMenuItem(oiw.getMenuItemID()).getName();
			double itemPrice = MenuManager.instance.getMenuItem(oiw.getMenuItemID()).getPrice();
			
			System.out.printf(format, itemName, oiw.getQuantity(), itemPrice, oiw.getItemPrices());
		}
		
	}
	
}
