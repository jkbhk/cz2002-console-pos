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
	
	
	public void createInvoice(String invoiceID ,String orderID, String staffID, String customerID, String date, String time, double gstRate, double gstPrice, double membershipRate, double membershipDiscountPrice, double serviceRate, double servicePrice, double totalPrice, double netTotal, boolean isMember){ 
	
		Invoice i = new Invoice(invoiceID, orderID, staffID, customerID, date, time, gstRate, gstPrice, membershipRate, membershipDiscountPrice, serviceRate, servicePrice, totalPrice, netTotal, isMember);
		invoiceList.add(i);
		System.out.println("Invoice successfully created");
	}
		
}
