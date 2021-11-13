package managers;

import java.util.ArrayList;

import payment.IPaymentMethod;
import pos.Application;

public class PaymentManager {

	public static PaymentManager instance;
	
	private ArrayList<IPaymentMethod> paymentMethods;
	
	public PaymentManager(IPaymentMethod...methods ) {
		
		instance = this;
		
		paymentMethods = new ArrayList<>();
		
		for(IPaymentMethod p: methods) {
			this.paymentMethods.add(p);
		}
	}
	
	public boolean requestPayment(double total) {
		
		if(paymentMethods.size() == 0) {
			System.out.println("no payment methods are supported at the moment.");
			return false;
		}
		
		boolean done = false;
		
		while(!done) {

			System.out.println("Total payable: "+String.format("$%.2f", total) + "\nChoose payment method:");
			int counter = 1;
			for(IPaymentMethod p : paymentMethods) {
				System.out.println(counter++ + ") " + p.getPaymentMethodName() );
			}
		
			int choice = Integer.parseInt(Application.scanner.nextLine());
		
			if(choice > 0 && choice <= paymentMethods.size()) {
				boolean pass = paymentMethods.get(choice-1).requestPayment(total);
				
				String m = pass ? "Payment successful." : "Payment unsuccessful";
				System.out.println(m);
				done = true;
				return true;
				
			}else{
				System.out.println("invalid payment option!");
				return false;
			}
		}
		
		return false;
	}
	
}
