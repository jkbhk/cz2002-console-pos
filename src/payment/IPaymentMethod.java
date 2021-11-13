package payment;
/*
 * An interface for payment methods.
 */
public interface IPaymentMethod {
	/**
	 * 
	 * @param amount
	 * @return true when payment is successful.
	 */
	public boolean requestPayment(double amount);
	/**
	 * 
	 * @return the name of the payment method.
	 */
	public String getPaymentMethodName();

}
