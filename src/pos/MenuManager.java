package pos;

import java.util.ArrayList;

public class MenuManager implements ISerializable {

	private Dao<MenuItem> dao;
	private ArrayList<MenuItem> menuItems;
	
	public MenuManager(Dao<MenuItem> d) {
	
		dao = d;
		menuItems = dao.read();
	}

	@Override
	public void serialize() {
		dao.write(menuItems);
	}

	public ArrayList<MenuItem> getMenuItems(){
		return this.menuItems;
	}

	
	
	
}
