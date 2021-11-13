package managers;

import java.time.LocalDate;
import java.util.ArrayList;

import dao.GenericDao;
import entities.Invoice;

/**
 * 
 * Manages Invoice object.
 *
 */

public class InvoiceManager {
	/**
	 * Globally accessible instance.
	 */
	public static InvoiceManager instance;
	private ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
	
	private GenericDao<Invoice> dao;
	
    public InvoiceManager(GenericDao<Invoice> d){
       instance = this;
       dao = d;
       invoiceList = d.read();
    }
   
	
    /**
     * @return invoice list of invoices.
     * 
     */
	public ArrayList<Invoice> getInvoiceList(){
		return invoiceList;
	}
	
	/**
	 * Add invoice to invoice list given index relative to its invoice list.
	 * @param i Index of invoice list.
	 */
	public void addInvoiceToList(Invoice i) {
		invoiceList.add(i);
	}
	
	/**
	 * Creates a Invoice object given the parameters.
	 * @param invoiceID Unique randomly generated invoice id.
	 * @param orderID Unique randomly generated order id.
	 * @param staffID Unique randomly generated staff id.
	 * @param date Date of invoice creation.
	 * @param time Time of invoice creation.
	 * @param gstRate GstRate of totalPrice.
	 * @param gstPrice GstPrice is the calculated value.
	 * @param membershipRate MembershipRate discount of totalPrice.
	 * @param membershipDiscountPrice membershipDiscountPrice is the calculated value.
	 * @param serviceRate ServiceRate of totalPrice
	 * @param servicePrice ServicePrice is the calculated value.
	 * @param totalPrice TotalPrice is the subtotal amount of the orders.
	 * @param netTotal NetTotal is the total price inclusive of discounts and additional charges.
	 * @param isMember isMember is the status of Customer.
	 */
	
	public void createInvoice(String invoiceID ,String orderID, String staffID, String date, String time, double gstRate, double gstPrice, double membershipRate, double membershipDiscountPrice, double serviceRate, double servicePrice, double totalPrice, double netTotal, boolean isMember){ 
	
		Invoice i = new Invoice(invoiceID, orderID, staffID, date, time, gstRate, gstPrice, membershipRate, membershipDiscountPrice, serviceRate, servicePrice, totalPrice, netTotal, isMember);
		invoiceList.add(i);
		System.out.println("Invoice successfully created");
	}
	
	/**
	 * @return ArrayList of invoices given a month.
	 * @param month 
	 * 
	 */
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
	
	
	/**
	 * @return ArrayList of invoices for the day.
	 * 
	 */
	public ArrayList<Invoice> getInvoiceListForCurrentDay() {
		
		ArrayList<Invoice> dailyInvoices = new ArrayList<>();
		
		for(Invoice i : invoiceList) {
			LocalDate date = LocalDate.parse(i.getDate());
			if(date.equals(LocalDate.now()))
					dailyInvoices.add(i);
			
		}
		
		return dailyInvoices;
		
	}
	
	/**
	 * Updates and saves the invoice list.
	 */
	public void save() {
		dao.write(invoiceList);
	}
}
