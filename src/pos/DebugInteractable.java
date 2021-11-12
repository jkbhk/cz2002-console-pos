package pos;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

import pos.Table.STATUS;

public class DebugInteractable implements IInteractable{
	
    @Override
    public void handleInput() {
        /*
    	LocalDate localDate = LocalDate.now();
    	LocalTime localTime = LocalTime.now();
    	
    	ArrayList<Reservation> reservationListDate = ReservationManager.instance.getReservationListForDate(localDate);
    	ArrayList<Table> tableList = TableManager.instance.getTableList(); 
    	
    	for (int x = 0; x < reservationListDate.size(); x ++)
    	{
    		LocalTime resTime = reservationListDate.get(x).getTime();
    		Duration duration = Duration.between(localTime, resTime);
    		long difference = duration.toMinutes();
    		if (difference < 20)
    		{
    			int tableNo = reservationListDate.get(x).getTableNo();
    			Table table = TableManager.instance.getTable(tableNo);
    			table.setStatus(STATUS.RESERVED);
    		}
    		
    		else 
    		{
    			int tableNo = reservationListDate.get(x).getTableNo();
    			Table table = TableManager.instance.getTable(tableNo);
    			table.setStatus(STATUS.EMPTY);
    		}
    		
    	}
    	
    	TableManager.instance.printTables();

    	*/
    	
    	
    }

    @Override
    public String getTitle() {
        // TODO Auto-generated method stub
        return "Execute debug code";
    }
    
}
