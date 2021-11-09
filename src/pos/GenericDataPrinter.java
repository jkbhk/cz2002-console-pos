package pos;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class GenericDataPrinter {
	
	public static void printTemplate(ITemplateA a) {
		String x = "=";
		String m = "";
		int number = 15;
		for (int j = 0; j < number; j++) {
			m += x;
		}
		
		String c = "-";
		String l = "";
		for (int w = 0; w < number; w++) {
			l += c;
		}
		
		String itemFormat = "| %-13s%-12s%-14s%-6s |\n";
		String calculationFormat = "%-40s%-2s\n";
		
		System.out.printf("==================== INVOICE ====================");
		System.out.println("");
		System.out.println(Application.STORE_NAME);
		System.out.println(Application.STORE_ADDRESS);
		System.out.println(Application.STORE_NUMBER);
		System.out.println(m + m + m + "====");
		System.out.println("Order Number: " + a.getOrderID());
		System.out.println("Order Date: " + a.getDate());
		System.out.println("Order Time: " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
		System.out.println("Staff: " + a.getStaffName() + "  Gender: " + a.getStaffGender() + "  Job Title: " + a.getStaffJobTitle());
		System.out.println(m + m + m + "====");
		System.out.println("|"+ l + "- Item Details --" + l +"|");
		System.out.println("| Item      Quantity      UnitPrice      Amount |");
		
		for (int i = 0; i < a.getMenuItems().length; i++) {
			System.out.printf(itemFormat, a.getMenuItems()[i], a.getMenuQuantity()[i], a.getMenuUnitPrice()[i], a.getMenuAmount()[i]);
		}
		
		System.out.println("|-" + l + l + l +"-|");
		System.out.println(l + l + l + "----");
		System.out.printf(calculationFormat, "Subtotal: ", "$" + a.getTotalPrice());
		
		if (a.getIsMember())
			System.out.printf(calculationFormat, "Membership Discount (" + a.getMembershipRate() + "%):", "$" + a.getMembershipDiscountPrice());
		
		System.out.printf(calculationFormat, "GST (" + a.getGSTRate() + "%):", "$" + a.getGSTPrice());
		System.out.printf(calculationFormat, "Service Charge (" + a.getServiceRate() + "%):", "$" + a.getServicePrice());
		System.out.printf(calculationFormat, "Total Amount Payable:", "$" + a.getNetTotalPrice());
		System.out.println(m + m + m + "====");
	}
	
}
