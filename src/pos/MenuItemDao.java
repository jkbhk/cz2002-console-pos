package pos;

import java.util.ArrayList;

public class MenuItemDao implements Dao<MenuItem>{

	private static final String filename = "menu_data.csv";

	@Override
	public ArrayList<MenuItem> read() {
		ArrayList<String[]> result = GenericFileReader.read(filename);
		ArrayList<MenuItem> items = new ArrayList<MenuItem>();

		for(String[] props : result){
			MenuItem menuItem = new MenuItem(props[0], props[1], props[2], Double.parseDouble(props[3]));
			items.add(menuItem);
		}

		return items;
	}

	@Override
	public void write(ArrayList<MenuItem> list) {
		String[][] unwrapped = new String[list.size()][4];
		
        for(int i = 0; i < list.size(); i++){
            
            MenuItem temp = list.get(i);
            unwrapped[i][0] = temp.getMenuItemID();
            unwrapped[i][1] = temp.getName();
            unwrapped[i][2] = String.format("%.2f", temp.getPrice());
			unwrapped[i][3] = temp.getDescription();
        }

        GenericFileWriter.writeFile(unwrapped, filename);
	}


}
