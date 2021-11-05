package pos;

import java.util.ArrayList;

public class StockManager implements ISerializable{
   private ArrayList<Stock> stocks;
   private Dao<Stock> dao;
   
   public StockManager(Dao<Stock> d){
       //stocks = new ArrayList<>();
       dao = d;
       stocks = d.read();
      
       for (Stock stock : stocks) {
          System.out.println(stock.getName() + " " + stock.getQuantity() + " "+ stock.getStockID()); 
       }
   }

    @Override
    public void serialize() {
        
        dao.write(stocks);
    }
}
