package pos;

import java.util.ArrayList;

public class StockDao extends GenericDao<Stock> {

    private static final String filename = "stock_data.csv";

    @Override
    public ArrayList<Stock> read() {
        ArrayList<String[]> result = GenericFileReader.read(filename);
        ArrayList<Stock> stocks = new ArrayList<>();

        if(result != null){
            for (String[] props : result) {
                Stock s = new Stock(props[0],props[1]);
                stocks.add(s);
            }        
        }

        return stocks;
    }

    @Override
    public void write(ArrayList<Stock> list) {
        String[][] unwrapped = new String[list.size()][2];
        
        for(int i = 0; i < list.size(); i++){
            
            Stock temp = list.get(i);
            unwrapped[i][0] = temp.getStockID();
            unwrapped[i][1] = temp.getName();
        }

        GenericFileWriter.writeFile(unwrapped, filename);
    }

    

    
    
}
