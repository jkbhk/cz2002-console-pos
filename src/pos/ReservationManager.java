package pos;

import java.util.*;

public class ReservationManager {

	private Reservation reservation;
	private ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
	private String membershipID;
	public static ReservationManager instance;
	
	// To retrieve customer's particular reservation.
	
	public Reservation getReservation(String reservationID)
	{
		if (!reservationList.isEmpty())
		{
			for (int x = 0; x < reservationList.size(); x++)
			{
				Reservation currres = reservationList.get(x);
				if (currres.getReservationID().equals(reservationID))
				{
					return currres;
				}
			}
		}
		else 
			System.out.println("Reservation List is Empty!");
		return null;
	}
	
	public ReservationManager()
	{
		instance = this;
	}
	
	public void createReservation(String name, String contactNo, int noPax, Date date, String time)
	{
		// To - Do
		Reservation reservation1 = new Reservation();
		reservation1.makeReservation(date, time, noPax, name, contactNo);
		
		reservationList.add(reservation1);
	}
	
	//Alternative way to retrieve Customer's Reservation List
	public ArrayList<Reservation> getReservationList(String contactNo)
	{
		return reservationList;
	}
	
	public ArrayList<Reservation> getReservationListForDate(Date date)
	{
		return reservationList;
	}
	
}
