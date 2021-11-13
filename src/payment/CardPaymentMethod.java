package payment;

import pos.Application;
/**
 * 
 * Implements payment method that uses card.
 *
 */
public class CardPaymentMethod implements IPaymentMethod{

	@Override
	public boolean requestPayment(double amount) {
		
		System.out.println("Waiting for payment...(Enter any number to continue)");
		
		Application.scanner.nextLine();

		return true;
	}

	@Override
	public String getPaymentMethodName() {
		
		return "Card";
	}
	

}
