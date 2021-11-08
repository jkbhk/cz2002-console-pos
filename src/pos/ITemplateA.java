package pos;

public interface ITemplateA {
	
	//Template for Invoice Printing
	public String getOrderID();
	public String getStaffName();
	public String getStaffGender();
	public String getStaffJobTitle();
	public String getDate();
	public String getGSTRate();
	public String getGSTPrice();
	public String getMembershipRate();
	public String getMembershipDiscountPrice();
	public String getServiceRate();
	public String getServicePrice();
	public String getTotalPrice();
	public String getNetTotalPrice();
	public boolean getIsMember();
	public String[] getMenuItems();
	public String[] getMenuQuantity();
	public String[] getMenuUnitPrice();
	public String[] getMenuAmount();
}
