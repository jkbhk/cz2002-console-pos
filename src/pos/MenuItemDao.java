package pos;

import java.util.ArrayList;

public class MenuItemDao implements Dao<MenuItem>{

	@Override
	public ArrayList<MenuItem> read() {
		// TODO Auto-generated method stub
		ArrayList<MenuItem> items = new ArrayList<MenuItem>();
		
		//file operations here
		
		return items;
	}

	@Override
	public void write(ArrayList<MenuItem> list) {
		// TODO Auto-generated method stub
		
	}


}
