package pos;

import java.util.ArrayList;
import java.util.HashMap;

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
		
		
		String leftAlignFormat = "| %-17s             %-5s |\n";
		String descAlignFormat = "| %-36s|\n";

		System.out.println("=========== Promotional Set ===========");
		System.out.println("|                                     |");
		System.out.println("| Menu item                     Cost  |");
		System.out.println("|-------------------------------------|");
		
		for (int i = 0; i < menuItems.size(); i++) {
			MenuItem temp = menuItems.get(i);
		    System.out.printf(leftAlignFormat, (i+1) + ") " + temp.getName(), String.format("%.2f", temp.getPrice()));
		    System.out.printf(descAlignFormat, temp.getDescription());
		    System.out.println("|                                     |");
		}
		
		System.out.println("|=====================================|");
		
		//for (int i = 0; i < menuItems.size(); i++) {
		//	MenuItem temp = menuItems.get(i);
		//	System.out.println((i + 1) + ") " + temp.getName() + "  $" + String.format("%.2f", temp.getPrice()) + "\n" + temp.getDescription() + "\n");
		//}
	}
	public void displayMenuSorted() {
		
		
		int counter = 1;
		
		HashMap<String, ArrayList<MenuItem>> menu = getSortedMenu();
		
		for(String header : menu.keySet()) {
			System.out.println(header+"\n---------------------------");
			
			for(MenuItem i : menu.get(header)) {
				System.out.println(counter++ + ") "+i.getName() + "   " + String.format("$%.2f", i.getPrice()));
			}
		}
	}
	
	public HashMap<String,ArrayList<MenuItem>> getSortedMenu(){
		
		HashMap<String,ArrayList<MenuItem>> menu = new HashMap<String,ArrayList<MenuItem>>();
		
		for(MenuItem i : menuItems) {
			if(menu.containsKey(i.getTag())){
				menu.get(i.getTag()).add(i);
			}else {
				ArrayList<MenuItem> created = new ArrayList<>();
				created.add(i);
				menu.put(i.getTag(), created);
				
			}
		}
		
		return menu;
		
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
