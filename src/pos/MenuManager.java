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

	public void createNewMenuItem(String id, String name, String description, double price) {
		MenuItem item = new MenuItem(id, name, description, price);
		menuItems.add(item);
	}

	public void displayMenu() {
		for (int i = 0; i < menuItems.size(); i++) {
			MenuItem temp = menuItems.get(i);
			System.out.println((i + 1) + ") " + temp.getName() + "  $" + String.format("%.2f", temp.getPrice()));
		}
	}

	public MenuItem getMenuItem(int index) {
		if (index >= 0 && index < menuItems.size())
			return menuItems.get(index);

		return null;
	}

	public MenuItem getstock(String id) {
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

}
