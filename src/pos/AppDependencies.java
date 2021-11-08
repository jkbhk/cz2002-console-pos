package pos;

public class AppDependencies {

	public static void initilializeDependencies() {
		
		// create all your managers here and inject dependencies if required
		StockManager stockManager = new StockManager(new StockDao());
		MenuManager menuManager = new MenuManager(new MenuItemDao());
		StaffManager staffManager = new StaffManager(new StaffDao());
		ExampleManager exampleManager = new ExampleManager();
		TableManager tableManager = new TableManager(new TableDao());
		ReservationManager reservationManager = new ReservationManager(new ReservationDao());
		CustomerManager customerManager = new CustomerManager(new CustomerDao());
		OrderManager orderManager = new OrderManager();
		InvoiceManager invoiceManager = new InvoiceManager();
		PaymentManager paymentManager = new PaymentManager(new CardPaymentMethod());
		
		
	}
	
	public static void onExit() {
		StockManager.instance.save();
		MenuManager.instance.save();
		StaffManager.instance.save();
		ReservationManager.instance.save();
		
	}
	
}
