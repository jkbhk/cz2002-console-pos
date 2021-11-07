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
		PaymentManager paymentManager = new PaymentManager(7,10,new CardPaymentMethod());
		TableManager tableManager = new TableManager(new TableDao());
		
	}
	
	public static void onExit() {
		StockManager.instance.save();
		MenuManager.instance.save();
		//ReservationManager.instance.save();
		
	}
	
}
