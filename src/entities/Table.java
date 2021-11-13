package entities;

/**
 * 
 * 
 *	A data structure to store the table size and table status.
 *
 */
public class Table {
    private int tableNo;
    private String id;
    private int tableSize;
    private Table.STATUS status;
    public enum STATUS {OCCUPIED, EMPTY, RESERVED}

    public Table(String id, int tableNo, int tableSize, STATUS status) {
        this.tableNo = tableNo;
        this.tableSize= tableSize;
        this.status = status;
        this.id = id;
    }

    public int getTableNo() {
        return tableNo;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public int getTableSize() {
        return tableSize;
    }

    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }
    
    public String getTableId() {
    	return id;
    }
}