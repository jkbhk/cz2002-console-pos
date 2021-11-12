package pos;

public class AppDependencies {

	public static void initilializeDependencies() {
		
		// create all your managers here and inject dependencies if required
		StockManager stockManager = new StockManager(new StockDao());
		MenuManager menuManager = new MenuManager(new MenuItemDao());
		StaffManager staffManager = new StaffManager(new StaffDao());
		TableManager tableManager = new TableManager(new TableDao());
		ReservationManager reservationManager = new ReservationManager(new ReservationDao());
		CustomerManager customerManager = new CustomerManager(new CustomerDao());
		OrderManager orderManager = new OrderManager(new OrderDao());
		InvoiceManager invoiceManager = new InvoiceManager(new InvoiceDao());
		PaymentManager paymentManager = new PaymentManager(new CardPaymentMethod());
		InvoicePrinter invoicePrinter = new InvoicePrinter();
		
	}
	
	public static void onExit() {
		StockManager.instance.save();
		MenuManager.instance.save();
		StaffManager.instance.save();
		ReservationManager.instance.save();
		OrderManager.instance.save();
		InvoiceManager.instance.save();
	}
	
}
