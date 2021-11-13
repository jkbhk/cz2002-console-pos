package pos;

import java.time.LocalDate;
import java.util.ArrayList;

public class InvoiceManager {
	
	public static InvoiceManager instance;
	private ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
	
	private Dao<Invoice> dao;

    public InvoiceManager(Dao<Invoice> d){
       instance = this;
       dao = d;
       invoiceList = d.read();
    }
   
	
	public ArrayList<Invoice> getInvoiceList(){
		return invoiceList;
	}
	
	public void addInvoiceToList(Invoice i) {
		invoiceList.add(i);
	}
	
	
	public void createInvoice(String invoiceID ,String orderID, String staffID, String date, String time, double gstRate, double gstPrice, double membershipRate, double membershipDiscountPrice, double serviceRate, double servicePrice, double totalPrice, double netTotal, boolean isMember){ 
	
		Invoice i = new Invoice(invoiceID, orderID, staffID, date, time, gstRate, gstPrice, membershipRate, membershipDiscountPrice, serviceRate, servicePrice, totalPrice, netTotal, isMember);
		invoiceList.add(i);
		System.out.println("Invoice successfully created");
	}
	
	public ArrayList<Invoice> getInvoiceListByMonth(int month) {
		
		ArrayList<Invoice> monthlyInvoices = new ArrayList<>();
		
		for(Invoice i : invoiceList) {
			LocalDate date = LocalDate.parse(i.getDate());
			if (month == date.getMonthValue()){
				monthlyInvoices.add(i);
			}
		}
		
		return monthlyInvoices;
	}
	
	
	public ArrayList<Invoice> getInvoiceListForCurrentDay() {
		
		ArrayList<Invoice> dailyInvoices = new ArrayList<>();
		
		for(Invoice i : invoiceList) {
			LocalDate date = LocalDate.parse(i.getDate());
			if(date.equals(LocalDate.now()))
					dailyInvoices.add(i);
			
		}
		
		return dailyInvoices;
		
	}
	
	public void save() {
		dao.write(invoiceList);
	}
}
