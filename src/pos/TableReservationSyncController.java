package pos;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import pos.Table.STATUS;

public class TableReservationSyncController {

	// refresh the status of tables based on the reservation dates and time
	public static void sync() {
		
		refreshReservationList();
		
    	ArrayList<Reservation> reservationListDate = ReservationManager.instance.getReservationList();
    	
    	for (int x = 0; x < reservationListDate.size(); x ++)
    	{
    		LocalTime resTime = reservationListDate.get(x).getTime();
    		LocalDate resDate = reservationListDate.get(x).getDate();
    		
    		Duration duration = Duration.between(resTime, LocalTime.now());
    		
    		long difference = duration.toMinutes();
    		
    		int tableNo = reservationListDate.get(x).getTableNo();
    		Table table = TableManager.instance.getTable(tableNo);
    		
    		if ((resDate.equals(LocalDate.now())) && (difference <= 20 && difference >= 0))
    			table.setStatus(STATUS.RESERVED);
    		else {
    			if(table.getStatus() == STATUS.RESERVED)
    				table.setStatus(STATUS.EMPTY);
    		}
    			
    	}
	
	}
	
	// clear reservations that have expired and release tables
	public static void refreshReservationList(){
		LocalTime currentTime = LocalTime.now();
		LocalDate currentDate = LocalDate.now();
		
		ArrayList<Reservation> reservationList = ReservationManager.instance.getReservationList();
		ArrayList<Reservation> toDelete = new ArrayList<>();
		
		if (reservationList != null)
		{
			
			for (int i = 0; i<reservationList.size(); i++)
			{
				Reservation r = reservationList.get(i);
				
				LocalTime resTime = r.getTime();
				LocalDate resDate = r.getDate();
				Duration duration = Duration.between(resTime, currentTime);
				long difference = duration.toMinutes();
				
				if ((currentDate.equals(resDate) && difference > 20) || currentDate.isAfter(resDate))  //Orders Cancel in 20 Minutes 
				{
					toDelete.add(r);
					int tableNo = r.getTableNo();
					TableManager.instance.getTable(tableNo).setStatus(STATUS.EMPTY);
				}
			}
			
			
			for(Reservation r : toDelete) {
				ReservationManager.instance.deleteReservation(r);
			}
		}
	}

	
}
