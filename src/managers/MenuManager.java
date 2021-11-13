package managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import dao.GenericDao;
import entities.MenuItem;

/**
 * 
 * Manages menu objects.
 *
 */
public class MenuManager{
	/**
	 * Globally accessed instance.
	 */

	public static MenuManager instance;	
	private GenericDao<MenuItem> dao;
	private ArrayList<MenuItem> menuItems;

	public MenuManager(GenericDao<MenuItem> d) {
		instance = this;		
		dao = d;
		menuItems = dao.read();
		sortMenuItems();
		
	}

	/**
	 * Creates MenuItem given the following parameters
	 * @param id Uniquely generated ID of MenuItem.
	 * @param name Name of MenuItem.
	 * @param description Descriptions of MenuItem.
	 * @param price Price of MenuItem.
	 * @param tag Value to sort MenuItem.
	 * @param stockReferenceIDs Uniquely generated stock id.
	 */
	public void createNewMenuItem(String id, String name, String description, double price, String tag, ArrayList<String> stockReferenceIDs) {
		MenuItem item = new MenuItem(id, name, description, price, tag, stockReferenceIDs);
		menuItems.add(item);
		System.out.println(name + " added to menu");
		sortMenuItems();
	}
	
	/**
	 * Sort the MenuItem via their tags.
	 */
	private void sortMenuItems() {
		Collections.sort(menuItems, new Comparator<MenuItem>() {
		    @Override
		    public int compare(MenuItem lhs, MenuItem rhs) {
		    	return lhs.getTag().compareTo(rhs.getTag());
		    }
		});
	}
	
	/**
	 * Display the Menu of the restaurant.
	 */
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
	
	
	/**
	 * @return MenuItem given index relative to its list.
	 * @param index
	 * 
	 */
	public MenuItem getMenuItem(int index) {
		if (index >= 0 && index < menuItems.size())
			return menuItems.get(index);

		return null;
	}

	/**
	 * @return MenuItem given a MenuItem id.
	 * @param id
	 * 
	 */
	public MenuItem getMenuItem(String id) {
		for (MenuItem m : menuItems) {
			if (m.getMenuItemID().equals(id))
				return m;
		}

		return null;
	}
	
	/**
	 * Remove MenuItem given index relative to its list.
	 * @param index
	 */

	public void removeMenuItem(int index) {
		if (index >= 0 && index < menuItems.size())
			menuItems.remove(index);
	}

	/**
	 * Remove MenuItem given MenuItem id.
	 * @param id
	 */
	public void removeMenuItem(String id) {
		for (MenuItem m : menuItems) {
			if (m.getMenuItemID() == id) {
				menuItems.remove(m);
			}
		}
	}
	
	/**
	 * Updates and saves the menuItems list.
	 */
	public void save() {
		dao.write(menuItems);
	}
	
}
