package entities;

import java.util.ArrayList;

/**
 * 
 * MenuItem is an entity class that contains menuItemID, name, description, price, tag, stockReferenceIDs.
 * MenuItem is composed of {@link Stock} and references them using stockReferenceIDs.
 * 
 * 
 *
 */
public class MenuItem {
	
    private String menuItemID;
    private String name;
    private String description;
    private double price;
    private String tag;
    private ArrayList<String> stockReferenceIDs;
    public void setMenuItemID(String menuItemID) {
        this.menuItemID = menuItemID;
    }

    public MenuItem(String id, String name, String description, double price, String tag, ArrayList<String> stockReferenceIDs) {
        this.menuItemID = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.tag = tag;
        this.stockReferenceIDs = stockReferenceIDs;
    }

    public String getMenuItemID(){
        return this.menuItemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public ArrayList<String> getStockReferenceIDs() {
        return this.stockReferenceIDs;
    }
    
}