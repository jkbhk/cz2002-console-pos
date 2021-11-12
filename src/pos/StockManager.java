package pos;

import java.util.ArrayList;

public class StockManager {
   
    public static StockManager instance;

    private ArrayList<Stock> stocks;
    private GenericDao<Stock> dao;

    public StockManager(GenericDao<Stock> d){
       instance = this;
       dao = d;
       stocks = d.read();
    }


    public void createNewStock(String id, String stockName) {
        Stock stock = new Stock(id,stockName);
        stocks.add(stock);
    }

    public void displayAllStocks(boolean showIndexing){
       
        for(int i = 0; i < stocks.size(); i++){

            Stock temp = stocks.get(i);

            if(showIndexing)
                System.out.println((i+1) + ") " + temp.getName());
            else
                System.out.println(temp.getName());
        }
    
    }
    
    public int getStockCount() {
    	return this.stocks.size();
    }

    public Stock getstock(int index){
        if(index >=0 && index < stocks.size())
            return stocks.get(index);

        return null;
    }

    public Stock getstock(String stockID){
        for (Stock stock : stocks) {
            if(stock.getStockID() == stockID)
                return stock;
        }

        return null;
    }


    public void removeStock(int index){
        if(index >=0 && index < stocks.size())
            stocks.remove(index);
    }

    public void removeStock(String stockID){
        for (Stock stock : stocks) {
            if(stock.getStockID() == stockID){
                stocks.remove(stock);
            }
        }

    }
    
    public void save() {
    	dao.write(stocks);
    }
}
