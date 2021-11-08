package pos;

public class TemplateAAdapter implements ITemplateA{
	
	private Invoice invoice;
	private String[] menuItems;
	private String[] menuQuantity;
	private String[] menuUnitPrice;
	private String[] menuAmount;
	
	private Order o;
	
	public TemplateAAdapter(Invoice i) {
		invoice = i;
		o = OrderManager.instance.getCurrentOrder();
		splittingArrayList(o);
	}

	public void splittingArrayList(Order o) {
		int menuItemSize = o.getMenuItemIDList().size();
		
		menuItems = new String[menuItemSize];
		menuQuantity = new String[menuItemSize];
		menuUnitPrice = new String[menuItemSize];
		menuAmount = new String[menuItemSize];
		
		int i = 0;
		
		for (OrderItemWrapper oiw : o.getMenuItemIDList()) {
			menuItems[i] = MenuManager.instance.getMenuItem(oiw.menuItemID).getName();
			menuQuantity[i] = (""+ oiw.getQuantity());
			menuUnitPrice[i] = (""+ oiw.getItemPrices());
			menuAmount[i] = (""+oiw.getQuantity() * oiw.getItemPrices());
			i++;
		}

	}

	@Override
	public String getOrderID() {
		
		return invoice.getOrderID();
	}

	@Override
	public String getDate() {
		
		return invoice.getDate();
	}

	@Override
	public String getGSTRate() {
		
		return (""+invoice.getGstRate());
	}
	
	@Override
	public String getGSTPrice() {
		//this.gstPrice = (invoice.getGstPrice()/100) * o.getTotalPrice();
		String result = String.format("%.2f", invoice.getGstPrice());
		
		return result;
	}
	
	@Override
	public String getMembershipRate() {
		
		return (""+invoice.getMembershipRate());
	}
	
	@Override 
	public String getMembershipDiscountPrice() {
		//this.membershipPrice = (invoice.getMembershipDiscountPrice()/100)*o.getTotalPrice();
		String result = String.format("%.2f", invoice.getMembershipDiscountPrice());
		return result;
	}
	
	@Override
	public String getServiceRate() {
		
		return (""+invoice.getServiceRate());
	}

	@Override
	public String getServicePrice() {
		//serviceChargePrice = (invoice.getServicePrice()/100) * o.getTotalPrice();
		String result = String.format("%.2f", invoice.getServicePrice());
		
		return result;
	}

	@Override
	public String getTotalPrice() {
		
		return String.format("%.2f", invoice.getTotalPrice());
	}
	
	/*
	@Override
	public String getNetTotalPrice() {
		netTotalPrice = invoice.getTotalPrice() - membershipPrice + serviceChargePrice + gstPrice;
		String result = String.format("%.2f", netTotalPrice);
		return result;
	}
*/
	@Override
	public boolean getIsMember() {
		
		return invoice.getIsMember();
	}

	@Override
	public String[] getMenuItems() {
		
		return menuItems;
	}

	@Override
	public String[] getMenuQuantity() {
		
		return menuQuantity;
	}

	@Override
	public String[] getMenuUnitPrice() {
		
		return menuUnitPrice;
	}

	@Override
	public String[] getMenuAmount() {
		
		return menuAmount;
	}
	
	@Override
	public String getNetTotalPrice() {
		
		return String.format("%.2f", invoice.getNetTotal());
	}

	@Override
	public String getStaffName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStaffGender() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStaffJobTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
