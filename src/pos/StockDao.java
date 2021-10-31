package pos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StockDao implements Dao<Stock>, IReceivable{

    @Override
    public void receive(String[] properties) {
        // TODO Auto-generated method stub
        System.out.println(properties[0]);
        
    }

    @Override
    public ArrayList<Stock> read() {
        // TODO Auto-generated method stub
        GenericFileReader.read(this, "stock_data.csv");
        return null;
    }

    @Override
    public void write(ArrayList<Stock> list) {
        // TODO Auto-generated method stub
        
    }

    

    
    
}
