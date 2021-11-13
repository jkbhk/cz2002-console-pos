package managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import dao.GenericDao;
import entities.MenuItem;

public class MenuManager{

	public static MenuManager instance;	
	private GenericDao<MenuItem> dao;
	private ArrayList<MenuItem> menuItems;

	public MenuManager(GenericDao<MenuItem> d) {
		instance = this;		
		dao = d;
		menuItems = dao.read();
		sortMenuItems();
		
	}


	public void createNewMenuItem(String id, String name, String description, double price, String tag, ArrayList<String> stockReferenceIDs) {
		MenuItem item = new MenuItem(id, name, description, price, tag, stockReferenceIDs);
		menuItems.add(item);
		System.out.println(name + " added to menu");
		sortMenuItems();
	}
	
	
	private void sortMenuItems() {
		Collections.sort(menuItems, new Comparator<MenuItem>() {
		    @Override
		    public int compare(MenuItem lhs, MenuItem rhs) {
		    	return lhs.getTag().compareTo(rhs.getTag());
		    }
		});
	}
	
	
	public void displayMenu() {
		
		
		String leftAlignFormat = "| %-25s              %-5s |\n";
		String descAlignFormat = "| %-44s |\n";

		System.out.println("===================== Menu =====================");
		System.out.println("|                                              |");
		System.out.println("| Menu item                            Cost($) |");
		System.out.println("|----------------------------------------------|");
		System.out.println("|                                              |");
		
		String currentTag = "";
		
		for (int i = 0; i < menuItems.size(); i++) {
			MenuItem temp = menuItems.get(i);
			
			if(!currentTag.equals(temp.getTag())) {
				currentTag = temp.getTag();
				System.out.println("|                                              |");
				System.out.printf(descAlignFormat,currentTag);
				System.out.println("|----------------------------------------------|");
			}
			
		    System.out.printf(leftAlignFormat, (i+1) + ") " + temp.getName(), String.format("%.2f", temp.getPrice()));
		    System.out.printf(descAlignFormat, temp.getDescription());
		    System.out.println("|                                              |");
		}
		
		System.out.println("|==============================================|");
	}
	
	
	public MenuItem getMenuItem(int index) {
		if (index >= 0 && index < menuItems.size())
			return menuItems.get(index);

		return null;
	}

	public MenuItem getMenuItem(String id) {
		for (MenuItem m : menuItems) {
			if (m.getMenuItemID().equals(id))
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
