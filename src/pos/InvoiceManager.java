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
	
	
	public void createInvoice(String invoiceID ,String orderID, String staffID, String customerID, String date, double gst, double membershipDiscount, double totalPrice, boolean isMember){ 
	
		Invoice i = new Invoice(invoiceID, orderID, staffID, customerID, date, gst,membershipDiscount, totalPrice, isMember);
		invoiceList.add(i);
		System.out.println("Invoice successfully created");
	}
		
}
