package dao;

import java.util.ArrayList;

import entities.MenuItem;
import pos.GenericFileReader;
import pos.GenericFileWriter;

public class MenuItemDao extends GenericDao<MenuItem>{

	private static final String filename = "menu_data.csv";
	private static final String StockIDSeperator = "_";

	@Override
	public ArrayList<MenuItem> read() {
		ArrayList<String[]> result = GenericFileReader.read(filename);
		ArrayList<MenuItem> items = new ArrayList<MenuItem>();

		if(result != null){
			for(String[] props : result){
				
				String[] stockIDs = props[5].split("_");
				ArrayList<String> references = new ArrayList<>();
				for(String r : stockIDs)
					references.add(r);

				MenuItem menuItem = new MenuItem(props[0], props[1], props[2], Double.parseDouble(props[3]),props[4],references);
				items.add(menuItem);
			}
		}

		return items;
	}

	@Override
	public void write(ArrayList<MenuItem> list) {
		String[][] unwrapped = new String[list.size()][6];
		
        for(int i = 0; i < list.size(); i++){
            
            MenuItem temp = list.get(i);
            unwrapped[i][0] = temp.getMenuItemID();
            unwrapped[i][1] = temp.getName();
			unwrapped[i][2] = temp.getDescription(); 
			unwrapped[i][3] = String.format("%.2f", temp.getPrice());
			unwrapped[i][4] = temp.getTag();

			String combinedIDs = "";			
			
			for(int j = 0; j < temp.getStockReferenceIDs().size(); j++){
				combinedIDs += temp.getStockReferenceIDs().get(j);
				combinedIDs += j == temp.getStockReferenceIDs().size()-1 ? "" : StockIDSeperator;
			}

			unwrapped[i][5] = combinedIDs;

        }

        GenericFileWriter.writeFile(unwrapped, filename);
	}


}
