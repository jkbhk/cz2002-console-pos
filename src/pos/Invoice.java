package pos;

public class Invoice {
	
	private String invoiceID;
	private String orderID;
	private String staffID;
	//private String customerID;
	
	private String date;
	private String time;
	private double gstRate;
	private double gstPrice;
	private double membershipRate;
	private double membershipDiscountPrice;
	private double serviceRate;
	private double servicePrice;
	private double totalPrice;
	private double netTotal;
	private boolean isMember;

	public Invoice(String invoiceID ,String orderID, String staffID,
			String date, String time, double gstRate, double gstPrice, double membershipRate,
			double membershipDiscountPrice, double serviceRate, double servicePrice, double totalPrice, double netTotal, boolean isMember) { 
		this.invoiceID = invoiceID;
		this.orderID = orderID;
		this.staffID = staffID;
		//this.customerID = customerID;
		this.date = date;
		this.time = time;
		this.gstRate = gstRate;
		this.gstPrice = gstPrice;
		this.membershipRate = membershipRate;
		this.membershipDiscountPrice = membershipDiscountPrice;
		this.serviceRate = serviceRate;
		this.servicePrice = servicePrice;
		this.totalPrice = totalPrice;
		this.netTotal = netTotal;
		this.isMember = isMember;
	}
	
	public String getDate() {
		return this.date;
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

	public String getInvoiceID() {
		return invoiceID;
	}

	public String getTime() {
		return time;
	}

	public double getGstRate() {
		return gstRate;
	}

	public double getGstPrice() {
		return gstPrice;
	}

	public double getMembershipRate() {
		return membershipRate;
	}

	public double getMembershipDiscountPrice() {
		return membershipDiscountPrice;
	}

	public double getServiceRate() {
		return serviceRate;
	}

	public double getServicePrice() {
		return servicePrice;
	}

	public double getNetTotal() {
		return netTotal;
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
