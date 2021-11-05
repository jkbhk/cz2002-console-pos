package pos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StockDao implements Dao<Stock> {

    @Override
    public ArrayList<Stock> read() {
        // TODO Auto-generated method stub
        ArrayList<String[]> result = GenericFileReader.read("stock_data.csv");

        ArrayList<Stock> stocks = new ArrayList<>();

        if(result == null){
            System.out.println("failed to read stock_data.csv");
            return stocks;
        }

        for (String[] props : result) {
            Stock s = new Stock(props[0],props[1],Integer.parseInt(props[2]));
            stocks.add(s);
        }        

        return stocks;
    }

    @Override
    public void write(ArrayList<Stock> list) {
        // TODO Auto-generated method stub
        
    }

    

    
    
}
