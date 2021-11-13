package managers;

import java.util.ArrayList;

import dao.GenericDao;
import entities.Stock;
/**
 * 
 * Manages Stock objects.
 *
 */
public class StockManager {
   /**
    * Globally accessible instance.
    */
    public static StockManager instance;

    private ArrayList<Stock> stocks;
    private GenericDao<Stock> dao;

    public StockManager(GenericDao<Stock> d){
       instance = this;
       dao = d;
       stocks = d.read();
    }

/**
 * Creates a new Stock object and adds it to the stock list.
 */
    public void createNewStock(String id, String stockName) {
        Stock stock = new Stock(id,stockName);
        stocks.add(stock);
    }
/**
 * Displays all stocks from the stock list.
 */
    public void displayAllStocks(boolean showIndexing){
       
        for(int i = 0; i < stocks.size(); i++){

            Stock temp = stocks.get(i);

            if(showIndexing)
                System.out.println((i+1) + ") " + temp.getName());
            else
                System.out.println(temp.getName());
        }
    
    }
    /**
     * @return the current number of different Stock objects in the stock list.
     * 
     */
    public int getStockCount() {
    	return this.stocks.size();
    }

    /**
     * @return a Stock object given the index relative to its position in the stock list.
     */
    public Stock getstock(int index){
        if(index >=0 && index < stocks.size())
            return stocks.get(index);

        return null;
    }
/**
 *
 * @return a Stock object given a stock ID.
 */
    public Stock getstock(String stockID){
        for (Stock stock : stocks) {
            if(stock.getStockID() == stockID)
                return stock;
        }

        return null;
    }

/**
 * Removes the Stock object at the specified index position of the stock list.
 */
    public void removeStock(int index){
        if(index >=0 && index < stocks.size())
            stocks.remove(index);
    }

    /**
     * Remove the Stock object that matches the given stock ID from the stock list.
     */
    public void removeStock(String stockID){
        for (Stock stock : stocks) {
            if(stock.getStockID() == stockID){
                stocks.remove(stock);
            }
        }

    }
    
    /**
     * Updates and saves the stock list.
     */
    public void save() {
    	dao.write(stocks);
    }
}
