package pos;

import java.util.ArrayList;

public class StockManager implements ISerializable{
   
    public static StockManager instance;

    private ArrayList<Stock> stocks;
    private Dao<Stock> dao;

    public StockManager(Dao<Stock> d){
       //stocks = new ArrayList<>();
       instance = this;
       dao = d;
       stocks = d.read();
    }

    @Override
    public void serialize() {
        
        dao.write(stocks);
    }

    public ArrayList<Stock> getStocks(){
        return this.stocks;
    }
}
