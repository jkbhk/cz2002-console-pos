package managers;

import java.util.ArrayList;

import dao.GenericDao;
import entities.Table;
/**
 * 
 * Manages Table objects.
 *
 */

public class TableManager {
	 /**
	  * Globally accessible instance.
	  */
	public static TableManager instance;
	private ArrayList<Table> tableList;
	private GenericDao<Table> dao;
	

    public TableManager(GenericDao<Table> d){
 
        instance = this;
        dao = d;
        tableList = dao.read();
     }

   /**
    * Returns all tables in the table list. 
    */
    public ArrayList<Table> getTableList(){
    	
    	return tableList;
    }
    
    /**
     * 
     * @return the table that matches the given table number.
     */
    public Table getTable(int tableNo) {
    	
    		for (Table table : tableList) {
            if(table.getTableNo() == tableNo)
                return table;
        }

        return null;
    }
    
    /**
     * Displays all the tables and their respective information from the table list.
     */
    public void printTables() {
        for (Table table : tableList) {
            System.out.println("Table Number : " + table.getTableNo() + " Table Size : " + table.getTableSize() + " STATUS:  " + table.getStatus());
        }
    }
    
    /**
     * Displays all the tables without their status from the table list.
     */
    public void printTablesList() {
        for (Table table : tableList) {
            System.out.println("Table Number : " + table.getTableNo() + " Table Size : " + table.getTableSize());
        }
    }
}
