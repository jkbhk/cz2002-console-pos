package pos;

public interface IPaymentMethod {
	
	public boolean requestPayment(double amount);
	public String getPaymentMethodName();

}
