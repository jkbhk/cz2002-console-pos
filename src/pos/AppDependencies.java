package pos;

import dao.CustomerDao;
import dao.InvoiceDao;
import dao.MenuItemDao;
import dao.OrderDao;
import dao.ReservationDao;
import dao.StaffDao;
import dao.StockDao;
import dao.TableDao;
import managers.CustomerManager;
import managers.InvoiceManager;
import managers.MenuManager;
import managers.OrderManager;
import managers.PaymentManager;
import managers.ReservationManager;
import managers.StaffManager;
import managers.StockManager;
import managers.TableManager;
import payment.CardPaymentMethod;
/**
 * 
 * Top level module for injecting dependencies.
 * 
 *
 */
public class AppDependencies {
/**
 * Instantiates all concrete classes and inject them into the respective constructors.
 */
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
	
	/**
	 * Called when the application is closing.
	 */
	public static void onExit() {
		StockManager.instance.save();
		MenuManager.instance.save();
		StaffManager.instance.save();
		ReservationManager.instance.save();
		OrderManager.instance.save();
		InvoiceManager.instance.save();
	}
	
}
