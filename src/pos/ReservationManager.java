package pos;

import java.util.*;

public class ReservationManager {

	private Reservation reservation;
	private ArrayList<Reservation> reservationList;
	private String membershipID;
	
	// To retrieve customer's particular reservation.
	public Reservation getReservation(int reservationID)
	{
		return reservation;
	}
	
	public void createReservation(String customerID, String contactNo, int noPax, Date date, String time)
	{
		// To - Do
	}
	
	//Alternative way to retrieve Customer's Reservation List
	public ArrayList<Reservation> getReservation(String contactNo)
	{
		return reservationList;
	}
	
	public ArrayList<Reservation> getReservationForDate(Date date)
	{
		return reservationList;
	}
	
}
