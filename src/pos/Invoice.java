package pos;

public class Invoice {
	
	private String invoiceID;
	private String orderID;
	private String staffID;
	private String customerID;
	
	private String date;
	private double gst;
	private double membershipDiscount;
	private double totalPrice;
	private boolean isMember;

	public Invoice(String invoiceID ,String orderID, String staffID, String customerID,String date,double gst, double membershipDiscount, double totalPrice, boolean isMember) { 
		this.invoiceID = invoiceID;
		this.orderID = orderID;
		this.staffID = staffID;
		this.customerID = customerID;
		this.date = date;
		this.gst = gst;
		this.membershipDiscount = membershipDiscount;
		this.totalPrice = totalPrice;
		this.isMember = isMember;
	}
	
	public String getCustomerID() {
		return this.customerID;
	}
	
	public String getDate() {
		return this.date;
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

	public String getOrderID() {
		return this.orderID;
	}
	
	public String getStaffID() {
		return this.staffID;
	}
	
	public double getTotalPrice() {
		return this.totalPrice;
	}
	
	public boolean getIsMember() {
		return this.isMember;
	}
	
	
	/*
	public void printItems(String format) {		
		for (OrderItemWrapper oiw : menuItemIDList) {
			String itemName = MenuManager.instance.getMenuItem(oiw.getMenuItemID()).getName();
			double itemPrice = MenuManager.instance.getMenuItem(oiw.getMenuItemID()).getPrice();
			
			System.out.printf(format, itemName, oiw.getQuantity(), itemPrice, oiw.getItemPrices());
		}
		
	}
	*/
	
}
