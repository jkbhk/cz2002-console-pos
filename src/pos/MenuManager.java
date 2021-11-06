package pos;

import java.util.ArrayList;

public class MenuManager{

	public static MenuManager instance;	
	private Dao<MenuItem> dao;
	private ArrayList<MenuItem> menuItems;

	public MenuManager(Dao<MenuItem> d) {
		instance = this;		
		dao = d;
		menuItems = dao.read();
	}


	public void createNewMenuItem(String id, String name, String description, double price, String tag, ArrayList<String> stockReferenceIDs) {
		MenuItem item = new MenuItem(id, name, description, price, tag, stockReferenceIDs);
		menuItems.add(item);
		System.out.println(name + " added to menu");
	}

	public void displayMenu() {
		for (int i = 0; i < menuItems.size(); i++) {
			MenuItem temp = menuItems.get(i);
			System.out.println((i + 1) + ") " + temp.getName() + "  $" + String.format("%.2f", temp.getPrice()) + "\n" + temp.getDescription() + "\n");
		}
	}

	public MenuItem getMenuItem(int index) {
		if (index >= 0 && index < menuItems.size())
			return menuItems.get(index);

		return null;
	}

	public MenuItem getMenuItem(String id) {
		for (MenuItem m : menuItems) {
			if (m.getMenuItemID() == id)
				return m;
		}

		return null;
	}

	public void removeMenuItem(int index) {
		if (index >= 0 && index < menuItems.size())
			menuItems.remove(index);
	}

	public void removeMenuItem(String id) {
		for (MenuItem m : menuItems) {
			if (m.getMenuItemID() == id) {
				menuItems.remove(m);
			}
		}
	}
	
	public void save() {
		dao.write(menuItems);
	}
	
}
