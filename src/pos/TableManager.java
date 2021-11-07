package pos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;

import pos.Table.STATUS;

import java.lang.reflect.Array;


public class TableManager {
	 
	public static TableManager instance;
	private ArrayList<Table> tableList;
	private Dao<Table> dao;
	

    public TableManager(Dao<Table> d){
 
        instance = this;
        dao = d;
        tableList = d.read();
        fetchTableList();
     }

    public void fetchTableList() {
 
        if (tableList.isEmpty()) {
            System.out.println("Empty data set, load default");
            Table newTable1 = new Table("1",1, 8, Table.STATUS.EMPTY);
            Table newTable2 = new Table("2",2, 6, Table.STATUS.EMPTY);
            Table newTable3 = new Table("3",3, 4, Table.STATUS.EMPTY);
            Table newTable4 = new Table("4",4, 4, Table.STATUS.EMPTY);
            Table newTable5 = new Table("5",5, 4, Table.STATUS.EMPTY);
            Table newTable6 = new Table("6",6, 2, Table.STATUS.EMPTY);
            Table newTable7 = new Table("7",7, 2, Table.STATUS.EMPTY);
            this.tableList = new ArrayList<Table>();
            tableList.add(newTable1);
            tableList.add(newTable2);
            tableList.add(newTable3);
            tableList.add(newTable4);
            tableList.add(newTable5);
            tableList.add(newTable6);
            tableList.add(newTable7);
        }

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
}
