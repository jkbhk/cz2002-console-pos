package pos;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import entities.Table;
import entities.Table.STATUS;
import managers.ReservationManager;
import managers.TableManager;

/**
 * 
 * Controller class that synchronise the entire application to refresh and updates the reservation list and table list.
 *
 */
public class TableReservationSyncController {

	// refresh the status of tables based on the reservation dates and time
	/**
	 * Refreshes and synchronise to set the table status with reference to the reservation list.
	 */
	public static void sync() {
		
		
    	ArrayList<Reservation> reservationListDate = ReservationManager.instance.getReservationList();
    	
    	refreshReservationList(reservationListDate);
    	
    	
    	for (int x = 0; x < reservationListDate.size(); x ++)
    	{
    		LocalTime resTime = reservationListDate.get(x).getTime();
    		LocalDate resDate = reservationListDate.get(x).getDate();
    		
    		Duration duration = Duration.between(resTime, LocalTime.now());
    		
    		long difference = duration.toMinutes();
    		
    		int tableNo = reservationListDate.get(x).getTableNo();
    		Table table = TableManager.instance.getTable(tableNo);
    		
    		if ((resDate.equals(LocalDate.now())) && (difference <= ReservationManager.RESERVATION_EXPIRY && difference >= 0))
    			table.setStatus(STATUS.RESERVED);
    		else {
    			if(table.getStatus() == STATUS.RESERVED)
    				table.setStatus(STATUS.EMPTY);
    		}
    			
    	}
	
	}
	
	// clear reservations that have expired and release tables
	private static void refreshReservationList(ArrayList<Reservation> reservationList){
		//LocalTime currentTime = LocalTime.now();
		//LocalDate currentDate = LocalDate.now();
		//ArrayList<Reservation> reservationList = ReservationManager.instance.getReservationList();
		ArrayList<Reservation> toDelete = new ArrayList<>();
		
		if (reservationList != null)
		{
			
			for (int i = 0; i<reservationList.size(); i++)
			{
				Reservation r = reservationList.get(i);
				
				LocalTime resTime = r.getTime();
				LocalDate resDate = r.getDate();
				Duration duration = Duration.between(resTime, LocalTime.now());
				long difference = duration.toMinutes();
				
				if ((LocalDate.now().equals(resDate) && difference > ReservationManager.RESERVATION_EXPIRY) || LocalDate.now().isAfter(resDate)) 
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
