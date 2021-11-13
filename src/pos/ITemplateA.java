package pos;

/**
 * 
 * Interface that is utilised by TemplateAAdapter which provides a template for printing.
 *
 */
public interface ITemplateA {
	
	//Template for Invoice Printing
	
	/**
	 * @return Order ID.
	 */
	public String getOrderID();
	/**
	 * @return Staff Name.
	 */
	public String getStaffName();
	/**
	 * @return Staff Gender.
	 */
	public String getStaffGender();
	/**
	 * @return Staff Job Title.
	 */
	public String getStaffJobTitle();
	/**
	 * @return Date.
	 */
	public String getDate();
	/**
	 * @return GSTRate.
	 */
	public String getGSTRate();
	/**
	 * @return GSTPrice.
	 */
	public String getGSTPrice();
	/**
	 * @return Membership rate.
	 */
	public String getMembershipRate();
	/**
	 * @return Membership discount price.
	 */
	public String getMembershipDiscountPrice();
	/**
	 * @return Service rate.
	 */
	public String getServiceRate();
	/**
	 * @return Service price.
	 */
	public String getServicePrice();
	/**
	 * @return Total price.
	 */
	public String getTotalPrice();
	/**
	 * @return Net total price.
	 */
	public String getNetTotalPrice();
	/**
	 * @return Customer's membership.
	 */
	public boolean getIsMember();
	/**
	 * @return MenuItems
	 */
	public String[] getMenuItems();
	/**
	 * @return MenuItems quantity.
	 */
	public String[] getMenuQuantity();
	/**
	 * @return MenuItems unit price.
	 */
	public String[] getMenuUnitPrice();
	/**
	 * @return MenuItems amount.
	 */
	public String[] getMenuAmount();
}
