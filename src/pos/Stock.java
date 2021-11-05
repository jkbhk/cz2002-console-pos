package pos;

public class Stock {
    private String stockID;
    private String name;
    private int quantity;

    public Stock(String stockID, String name, int quantity){
        this.stockID = stockID;
        this.name = name;
        this.quantity = quantity;
    }

    public String getStockID() {
        return this.stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    

}
