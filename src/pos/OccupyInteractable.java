package pos;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import pos.Table.STATUS;

public class OccupyInteractable implements IInteractable {

	@Override
	public void handleInput() {
		
		System.out.println("Enter contact number:");
		String contact = Application.scanner.nextLine();
		
		ArrayList<Reservation> rlist = ReservationManager.instance.getReservationListForContact(contact);
		
		if(rlist.size() <= 0) {
			System.out.println("no reservations for this number.");
		}else {
			useValidReservation(rlist);
		}
		
	}

	@Override
	public String getTitle() {
		return "Occupy Reservation";
	}
	
	private void useValidReservation(ArrayList<Reservation> rlist) {
		
		Reservation toDelete = null;
		
		for(Reservation r : rlist) {
			
			if(LocalDate.now().equals(r.getDate())) {
				
				Duration duration = Duration.between(r.getTime(),LocalTime.now());
	    		long difference = duration.toMinutes();
	    		
	    		if(difference <= ReservationManager.RESERVATION_EXPIRY && difference >= 0) {
	    			
	    			toDelete = r;
	    			break;
	    		}
			}
			
		}
		
		if(toDelete != null) {
			int table = toDelete.getTableNo();
			ReservationManager.instance.deleteReservation(toDelete);
			TableManager.instance.getTable(table).setStatus(STATUS.OCCUPIED);
			TableReservationSyncController.sync();
			System.out.println("Reservation verified. Occupying table " + table);
		}else {
			System.out.println("it's not time for your reservation yet!");
		}
		
		
	}

	
	
}
