package pos;

import entities.Invoice;
import entities.Order;
import entities.OrderItemWrapper;
import managers.MenuManager;
import managers.OrderManager;
import managers.StaffManager;

/**
 * 
 * Template adapter that implements ITemplateA to provide a invoice a printing template.
 *
 */
public class TemplateAAdapter implements ITemplateA{
	
	private Invoice invoice;
	private String[] menuItems;
	private String[] menuQuantity;
	private String[] menuUnitPrice;
	private String[] menuAmount;
	
	private Order o;
	
	/**
	 * Given an invoice retrieve the relative orders and splits them.
	 * @param i Invoice Object
	 */
	public TemplateAAdapter(Invoice i) {
		invoice = i;
		o = OrderManager.instance.getOrder(i.getOrderID());
		splittingArrayList(o);
	}
	/**
	 * Splits value of OrderItemWrapper and store it into different ArrayList<String>.
	 * @param o Order Object.
	 */

	public void splittingArrayList(Order o) {
		
		if (o == null) {
			System.out.println("Can't find order in invoice.");
			return;
		}
		
		int menuItemSize = o.getMenuItemIDList().size();
		
		menuItems = new String[menuItemSize];
		menuQuantity = new String[menuItemSize];
		menuUnitPrice = new String[menuItemSize];
		menuAmount = new String[menuItemSize];
		
		int i = 0;
		
		for (OrderItemWrapper oiw : o.getMenuItemIDList()) {
			menuItems[i] = MenuManager.instance.getMenuItem(oiw.getMenuItemID()).getName();
			menuQuantity[i] = (""+ oiw.getQuantity());
			menuUnitPrice[i] = (""+ String.format("%.2f",oiw.getItemPrices()));
			menuAmount[i] = ("" + String.format("%.2f",(oiw.getQuantity() * oiw.getItemPrices())));
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
		String result = String.format("%.2f", invoice.getGstPrice());
		
		return result;
	}
	
	@Override
	public String getMembershipRate() {
		
		return (""+invoice.getMembershipRate());
	}
	
	@Override 
	public String getMembershipDiscountPrice() {
		String result = String.format("%.2f", invoice.getMembershipDiscountPrice());
		return result;
	}
	
	@Override
	public String getServiceRate() {
		
		return (""+invoice.getServiceRate());
	}

	@Override
	public String getServicePrice() {
		String result = String.format("%.2f", invoice.getServicePrice());
		
		return result;
	}

	@Override
	public String getTotalPrice() {
		
		return String.format("%.2f", invoice.getTotalPrice());
	}
	
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
		
		return StaffManager.instance.getCurrentStaff().getStaffName();
	}

	@Override
	public String getStaffGender() {
		
		return StaffManager.instance.getCurrentStaff().getGender();
	}

	@Override
	public String getStaffJobTitle() {
		
		return StaffManager.instance.getCurrentStaff().getJobTitle();
	}

	
}
