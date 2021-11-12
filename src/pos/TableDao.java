
package pos;

import java.util.ArrayList;

public class TableDao extends GenericDao<Table> {

    private static final String filename = "table_data.csv";

    @Override
    public ArrayList<Table> read() {
        ArrayList<String[]> result = GenericFileReader.read(filename);
        ArrayList<Table> tableList = new ArrayList<>();

        if(result != null){
            for (String[] props : result) {
                Table t = new Table(props[0],Integer.parseInt(props[1]),Integer.parseInt(props[2]), Table.STATUS.EMPTY);
                tableList.add(t);
            }        
        }

        return tableList;
    }

    @Override
    public void write(ArrayList<Table> list) {
        String[][] unwrapped = new String[list.size()][3];
        
        for(int i = 0; i < list.size(); i++){
            
            Table temp = list.get(i);
            unwrapped[i][0] = temp.getTableId();
            unwrapped[i][1] = ""+temp.getTableNo();
            unwrapped[i][2] = ""+temp.getTableSize();
        }

        GenericFileWriter.writeFile(unwrapped, filename);
    }

    

    
    
}
