package pos;

public class Table {
    private int tableNo;
    private int tableSize;
    private Table.STATUS status;
    public enum STATUS {OCCUPIED, EMPTY, RESERVED}

    public Table(int tableNo, int tableSize, STATUS status) {
        this.tableNo = tableNo;
        this.tableSize= tableSize;
        this.status = status;
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
}