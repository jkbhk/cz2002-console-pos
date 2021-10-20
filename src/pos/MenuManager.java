package pos;

import java.util.ArrayList;

public class MenuManager implements ISerializable {

	private MenuItemDao dao;
	private ArrayList<MenuItem> menuItems;
	
	public MenuManager() {
		
		dao = new MenuItemDao();
		
		menuItems = dao.read();
	}

	@Override
	public void serialize() {
		// TODO Auto-generated method stub
		dao.write(menuItems);
	}
	
	
}
