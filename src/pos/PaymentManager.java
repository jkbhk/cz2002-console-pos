package pos;

import java.util.ArrayList;

public class PaymentManager {

	public static PaymentManager instance;
	
	private ArrayList<IPaymentMethod> paymentMethods;
	private final double GST_RATE;
	private final double SERVICE_RATE;
	
	
	public PaymentManager(double gstRate,double serviceChargeRate, IPaymentMethod...methods ) {
		
		instance = this;
		
		this.GST_RATE = gstRate;
		this.SERVICE_RATE = serviceChargeRate;
		
		paymentMethods = new ArrayList<>();
		
		for(IPaymentMethod p: methods) {
			this.paymentMethods.add(p);
		}
	}
	
	public void requestPayment(double total) {
		
		if(paymentMethods.size() == 0) {
			System.out.println("no payment methods are supported at the moment.");
			return;
		}
		
		boolean done = false;
		
		while(!done) {
		
			double gstAmount = (total/100)  * GST_RATE;
			double serviceAmount = (total/100) * SERVICE_RATE;
			
		
			total = total + gstAmount + serviceAmount;
			
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
				
			}else{
				System.out.println("invalid payment option!");
			}
		}
	}
	
}
