package pos;

public class MenuItem {
    private String menuItemID;
    private String name;
    private String description;
    private double price;
    
    public MenuItem(String id, String name, String description, double price) {
        this.menuItemID = id;
        this.name = name;
        this.description = description;
        this.price = price;
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
}