package pos;

import java.util.ArrayList;

public class InvoiceManager {
	
	public static InvoiceManager instance;
	private ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
	
	public InvoiceManager() {
		instance = this;
	}
	
	public ArrayList<Invoice> getInvoiceList(){
		return invoiceList;
	}
	
	public void addInvoiceToList(Invoice i) {
		invoiceList.add(i);
	}
	
	public void createInvoice(String date, int orderID, String staffName, String employeeID, double totalPrice, 
		String staffGender, String staffJobTitle, int tableNo, String time, ArrayList<OrderItemWrapper> oiw, 
		double gst, double membershipDiscount){ 
		
		Invoice invoice = new Invoice(date, orderID, staffName, employeeID, totalPrice, staffGender, staffJobTitle, tableNo,
										time, oiw, gst, membershipDiscount);
		
		System.out.println("Invoice successfully created");
	}
	
	public void printInvoice(Invoice i) {
		
		String x = "=";
		String m = "";
		int number = 15;
		for (int j = 0; j < number; j++) {
			m += x;
		}
		
		String c = "-";
		String l = "";
		for (int w = 0; w < number; w++) {
			l += c;
		}
		
		String itemFormat = "| %-13s%-12s%-14s%-6s |\n";
		String titleFormat = "-19s%-19s";
		String calculationFormat = "%-40s%-2s\n";
		
		System.out.printf("==================== INVOICE ====================");
		System.out.println("");
		System.out.println(Application.storeName);
		System.out.println(Application.storeAddress);
		System.out.println(Application.storeNumber);
		System.out.println(m + m + m + "====");
		System.out.println("Order Number: " + i.getOrderID());
		System.out.println("Order Date: " + i.getDate());
		System.out.println("Order Time: " + i.getTime());
		System.out.println("Staff: " + i.getStaffName() + "  Gender: " + i.getStaffGender() + "  Job Title: " + i.getStaffJobTitle());
		System.out.println(m + m + m + "====");
		System.out.println("|"+ l + "- Item Details --" + l +"|");
		System.out.println("| Item      Quantity      UnitPrice      Amount |");
		
		i.printItems(itemFormat);
		
		System.out.println("|-" + l + l + l +"-|");
		System.out.println(l + l + l + "----");
		System.out.printf(calculationFormat, "Subtotal: ", "$" + String.format("%.2f", i.getTotalPrice()));
		
		if (i.getMemberShipDiscount() > 0)
			System.out.printf(calculationFormat, "Membership Discount (" + i.getMemberShipDiscount() + "%):", "$" + String.format("%.2f", (i.getMemberShipDiscount()/100) * i.getTotalPrice()));
		
		System.out.printf(calculationFormat, "GST (" + i.getGst() + "%):", "$" + String.format("%.2f", (i.getGst()/100) * i.getTotalPrice()));
		System.out.printf(calculationFormat, "Total Amount Payable:", "$" + String.format("%.2f", calculateTotalAmountPayable(i)));
		System.out.println(m + m + m + "====");
	}
	
	public double calculateTotalAmountPayable(Invoice i) {
		double result = 0;
		
		double totalPrice = i.getTotalPrice();
		double gst = (i.getGst()/100) * totalPrice;
		double membershipDiscount = (i.getMemberShipDiscount()/100) * totalPrice;
		
		result = totalPrice + membershipDiscount - gst;
		return result;
	}
	
}
