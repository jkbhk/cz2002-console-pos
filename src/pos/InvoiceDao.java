package pos;

import java.util.ArrayList;

public class InvoiceDao  implements Dao<Invoice>{
	private static final String filename = "invoice_data.csv";

	@Override
	public ArrayList<Invoice> read() {
		
		ArrayList<String[]> result = GenericFileReader.read(filename);
		ArrayList<Invoice> invoices = new ArrayList<>();
		
		if(result != null) {
			for (String[] props : result) {
				Invoice i = new Invoice(props[0], props[1], props[2], props[3], props[4], Double.parseDouble(props[5]),
						Double.parseDouble(props[6]), Double.parseDouble(props[7]), Double.parseDouble(props[8]), 
						Double.parseDouble(props[9]), Double.parseDouble(props[10]), Double.parseDouble(props[11]), 
						Double.parseDouble(props[12]), Boolean.parseBoolean(props[13]));
				invoices.add(i);
			}
		}
		
		return invoices;
	}

	@Override
	public void write(ArrayList<Invoice> list) {
		String[][] unwrapped = new String[list.size()][14];
		
		for(int i = 0; i < list.size(); i++) {
			
			Invoice temp = list.get(i);
			unwrapped[i][0] = temp.getInvoiceID();
			unwrapped[i][1] = temp.getOrderID();
			unwrapped[i][2] = temp.getStaffID();
			unwrapped[i][3] = temp.getDate();
			unwrapped[i][4] = temp.getTime();
			unwrapped[i][5] = ""+temp.getGstRate();
			unwrapped[i][6] = ""+temp.getGstPrice();
			unwrapped[i][7] = ""+temp.getMembershipRate();
			unwrapped[i][8] = ""+temp.getMembershipDiscountPrice();
			unwrapped[i][9] = ""+temp.getServiceRate();
			unwrapped[i][10] = ""+temp.getServicePrice();
			unwrapped[i][11] = ""+temp.getTotalPrice();
			unwrapped[i][12] = ""+temp.getNetTotal();
			unwrapped[i][13] = ""+temp.getIsMember();
					
		}
		
		GenericFileWriter.writeFile(unwrapped, filename);
		
	}
	
}
