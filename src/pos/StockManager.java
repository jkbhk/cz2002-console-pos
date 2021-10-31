package pos;

import java.util.ArrayList;

public class StockManager implements ISerializable{
   private ArrayList<Stock> stocks;
   private Dao<Stock> dao;
   
   public StockManager(Dao<Stock> d){
       //stocks = new ArrayList<>();
       dao = d;
   }

    @Override
    public void serialize() {
        
        dao.write(stocks);
    }
}
