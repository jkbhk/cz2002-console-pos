package pos;

import java.util.ArrayList;

public class CustomerDao implements Dao<Customer>{

	private static final String filename = "customer_data.csv";
	
	@Override
	public ArrayList<Customer> read() {
		
		ArrayList<String[]> result = GenericFileReader.read(filename);
		ArrayList<Customer> customerList = new ArrayList<>();
		
		if (result != null)
		{
			for (String[] props : result)
			{
				Customer customer = new Customer(props[0],props[1],props[2],props[3]);
				customerList.add(customer);
			}
		}
		return customerList;
	}

	@Override
	public void write(ArrayList<Customer> list) {
		String[][] unwrapped = new String[list.size()][4];
		
		for(int i = 0; i < list.size(); i++){
            
            Customer temp = list.get(i);
            unwrapped[i][0] = temp.getName();
            unwrapped[i][1] = temp.getContactNo();
            unwrapped[i][2] = temp.getMembershipID();
            unwrapped[i][3] = temp.getCustomerID();
        }

        GenericFileWriter.writeFile(unwrapped, filename);
		
	}
	

}
