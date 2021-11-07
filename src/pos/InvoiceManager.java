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
		double gst, double membershipDiscount, String customerID){ 
		
		Invoice invoice = new Invoice(date, orderID, staffName, employeeID, totalPrice, staffGender, staffJobTitle, tableNo,
										time, oiw, gst, membershipDiscount, customerID);
		
		System.out.println("Invoice successfully created");
	}
		
}
