package pos;

public class AppDependencies {

	public static void initilializeDependencies() {
		
		// create all your managers here and inject dependencies if required
		StockManager stockManager = new StockManager(new StockDao());
		MenuManager menuManager = new MenuManager(new MenuItemDao());
		ExampleManager exampleManager = new ExampleManager();
		ReservationManager reservationManager = new ReservationManager(new ReservationDao());
		CustomerManager customerManager = new CustomerManager(new CustomerDao());
		OrderManager orderManager = new OrderManager();
		InvoiceManager invoiceManager = new InvoiceManager();
		
	}
	
	public static void onExit() {
		StockManager.instance.save();
		MenuManager.instance.save();
		
	}
	
}
