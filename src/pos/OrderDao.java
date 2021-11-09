package pos;

import java.util.ArrayList;

public class OrderDao implements Dao<Order>{

	 private static final String filename = "order_data.csv";
/*
    @Override
    public ArrayList<Order> read() {
        ArrayList<String[]> result = GenericFileReader.read(filename);
        ArrayList<Order> orders = new ArrayList<>();

        if(result != null){
            for (String[] props : result) {
                Order o = new Order(props[0],props[1],Integer.parseInt(props[2]));
                orders.add(o);
            }        
        }

        return stocks;
    }

    @Override
    public void write(ArrayList<Order> list) {
        String[][] unwrapped = new String[list.size()][3];
        
        for(int i = 0; i < list.size(); i++){
            
            Stock temp = list.get(i);
            unwrapped[i][0] = temp.getStockID();
            unwrapped[i][1] = temp.getName();
            unwrapped[i][2] = ""+temp.getQuantity();
        }

        GenericFileWriter.writeFile(unwrapped, filename);
    }
	*/

	@Override
	public ArrayList<Order> read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(ArrayList<Order> list) {
		// TODO Auto-generated method stub
		
	}
}
