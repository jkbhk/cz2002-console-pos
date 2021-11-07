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
import java.lang.reflect.Array;


public class TableManager {
	private ArrayList<Table> tableList;

    public TableManager() {
        this.fetchTableList();
    }

    public void fetchTableList() {
        tableList = loadTableFromFile();
        System.out.println(tableList);
        if (tableList == null) {
            System.out.println("Empty data set, load default");
            Table newTable1 = new Table(1, 8, Table.STATUS.EMPTY);
            Table newTable2 = new Table(2, 6, Table.STATUS.EMPTY);
            Table newTable3 = new Table(3, 4, Table.STATUS.EMPTY);
            Table newTable4 = new Table(4, 4, Table.STATUS.EMPTY);
            Table newTable5 = new Table(5, 4, Table.STATUS.EMPTY);
            Table newTable6 = new Table(6, 2, Table.STATUS.EMPTY);
            Table newTable7 = new Table(7, 2, Table.STATUS.EMPTY);
            this.tableList = new ArrayList<Table>();
            tableList.add(newTable1);
            tableList.add(newTable2);
            tableList.add(newTable3);
            tableList.add(newTable4);
            tableList.add(newTable5);
            tableList.add(newTable6);
            tableList.add(newTable7);
            writeTableToFile(tableList);
        }
        // Set the table 'RESERVED' status if the current time matches the reservation list.
        ReservationManager reservationManager = new ReservationManager();
        ArrayList<Reservation> reservationList = reservationManager.getReservation();
        long currentTime = Instant.now().getEpochSecond();
        System.out.println(currentTime);
        for (Reservation reservation : reservationList) {
            if (currentTime >= reservation.getStartTime() && currentTime <= reservation.getExpiredTime() ) {
                // Set status to RESERVED.
//                System.out.println("Setting to RESERVED.");
                Table newTableStatus = tableList.stream().filter(table -> table.getTableNo() == reservation.getTableNo()).findAny().orElse(null);
                newTableStatus.setStatus(Table.STATUS.RESERVED);
            }
        }
        writeTableToFile(tableList);

    }

    public boolean addTable() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Table Status: {RESERVED,OCCUPIED,EMPTY}");
        String status = sc.nextLine();
        System.out.println("Table Size");
        int tableSize = sc.nextInt();
        System.out.println(tableSize);
        System.out.println("Table Number:");
        int id = sc.nextInt();
        if ((tableSize >= 2 && tableSize % 2 == 0)) {
            Table newTable = new Table(id, tableSize, Table.STATUS.valueOf(status.toUpperCase()));
            tableList.add(newTable);
            System.out.println(newTable);
            writeTableToFile(tableList);
        }
        return tableSize >= 2 && tableSize % 2 == 0;
    }

    public void updateTable() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Update Table Status To: {RESERVED,OCCUPIED,EMPTY}");
        String status = sc.nextLine();
        System.out.println("Table Number");
        int tableNo = sc.nextInt();
        Table tableObj = tableList.stream().filter(table -> tableNo == table.getTableNo()).findAny().orElse(null);
        System.out.println(tableObj);
        tableObj.setStatus(Table.STATUS.valueOf(status));
        writeTableToFile(tableList);
    }

    public void deleteTable() {
        System.out.println("Remove Table Number : ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        tableList.removeIf(table -> table.getTableNo() == id);
        writeTableToFile(tableList);
    }


    private void writeTableToFile(ArrayList<Table> tableList) {
        try {
            FileOutputStream foStream = new FileOutputStream("table.ser");
            ObjectOutputStream doStream = new ObjectOutputStream(foStream);
            doStream.writeObject(tableList);
            doStream.flush();
            doStream.close();
            foStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("IOError: File not found!" + tableList);
        } catch (IOException e) {
            System.out.println("IO Error!" + e.getMessage());
        }
    }

    public ArrayList<Table> loadTableFromFile() {
        try {
            ArrayList<Table> data;
            FileInputStream file = new FileInputStream("table.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            data = (ArrayList) in.readObject();
            in.close();
            file.close();
            return data;
        } catch (IOException e) {
            System.out.println("EMPTY TEXT FILE FROM " + "table.ser");
            return tableList;
        } catch (ClassNotFoundException e) {
            System.out.println("CLASSNOTFOUNDEXCEPTION: " + e);
            System.exit(0);
        }
        return null;
    }
}
