package pos;

import java.util.ArrayList;

public class OrderDao implements Dao<Order>{

	 private static final String filename = "order_data.csv";

    @Override
    public ArrayList<Order> read() {
        ArrayList<String[]> result = GenericFileReader.read(filename);
        ArrayList<Order> orders = new ArrayList<>();

        if(result != null){
            for (String[] props : result) {
            	
            	Order o = new Order();
            	
                o.setOrderID(props[0]);
                o.setTotalPrice(Double.parseDouble(props[1]));
                o.setTableNo(Integer.parseInt(props[2]));
                
                String[] oiws = props[3].split("_");
            	
                for(String inner : oiws) {
            		
            		String[] oiw_props = inner.split("]");
            		OrderItemWrapper temp = new OrderItemWrapper(oiw_props[0],Integer.parseInt(oiw_props[1]),Double.parseDouble(oiw_props[2]));
            		o.getMenuItemIDList().add(temp);
            	}   
                
                orders.add(o);
            }        
        }

        return orders;
    }

    @Override
    public void write(ArrayList<Order> list) {
        String[][] unwrapped = new String[list.size()][4];
        
        for(int i = 0; i < list.size(); i++){
            
            Order temp = list.get(i);
            unwrapped[i][0] = temp.getOrderID();
            unwrapped[i][1] = ""+temp.getTotalPrice();
            unwrapped[i][2] = ""+temp.getTableNo();
            
            String items = "";
            int counter = 1;
            
            for(OrderItemWrapper oiw : temp.getMenuItemIDList()) {
            	items += oiw.getMenuItemID() + "]" + oiw.getQuantity() + "]" + oiw.getItemPrices();
            	
            	if(counter != temp.getMenuItemIDList().size())
            		items += "_";
            	
            	counter++;
            }
                   
            unwrapped[i][3] = items;
        }

        GenericFileWriter.writeFile(unwrapped, filename);
    }

}
