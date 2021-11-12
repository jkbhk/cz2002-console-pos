package pos;

public class Stock {
    private String stockID;
    private String name;

    public Stock(String stockID, String name){
        this.stockID = stockID;
        this.name = name;
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
    

}
