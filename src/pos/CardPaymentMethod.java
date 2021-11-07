package pos;

public class CardPaymentMethod implements IPaymentMethod{

	@Override
	public boolean requestPayment(double amount) {
		
		System.out.println("Waiting for payment...(Press enter to continue)");
		
		try {
			System.in.read();
		}catch(Exception e) {
			System.out.println("accepted.");
		};
		
		return true;
	}

	@Override
	public String getPaymentMethodName() {
		
		return "Card";
	}
	

}
