package managers;

import java.util.ArrayList;

import dao.GenericDao;
import entities.Table;


public class TableManager {
	 
	public static TableManager instance;
	private ArrayList<Table> tableList;
	private GenericDao<Table> dao;
	

    public TableManager(GenericDao<Table> d){
 
        instance = this;
        dao = d;
        tableList = dao.read();
     }

    public void addTable(String id, int tableNo, int tableSize, String status) {
      
            Table newTable = new Table(id, tableNo, tableSize, Table.STATUS.valueOf(status.toUpperCase()));
            tableList.add(newTable);

    }


    public void deleteTable(int tableNo) {
    	
        tableList.removeIf(table -> table.getTableNo() == tableNo);
 
    }

    
    public ArrayList<Table> getTableList(){
    	
    	return tableList;
    }
    
    public Table getTable(int tableNo) {
    	
    		for (Table table : tableList) {
            if(table.getTableNo() == tableNo)
                return table;
        }

        return null;
    }
    
    public void printTables() {
        for (Table table : tableList) {
            System.out.println("Table Number : " + table.getTableNo() + " Table Size : " + table.getTableSize() + " STATUS:  " + table.getStatus());
        }
    }
    
    public void printTablesList() {
        for (Table table : tableList) {
            System.out.println("Table Number : " + table.getTableNo() + " Table Size : " + table.getTableSize());
        }
    }
}
