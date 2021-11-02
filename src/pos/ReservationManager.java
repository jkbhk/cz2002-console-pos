package pos;

import java.time.LocalDate;
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
	
	public void createReservation(String name, String contactNo, int noPax, LocalDate date, String time)
	{
		// To - Do
		Reservation reservation1 = new Reservation();
		reservation1.makeReservation(date, time, noPax, name, contactNo);
		if (reservationList.isEmpty())
		{
			reservation1.setReservationID("1");
			reservationList.add(reservation1);
			System.out.println("Reservation 1 has been created");
		}
		else 
		{
			int currIndex = Integer.parseInt(reservationList.get(reservationList.size()-1).getReservationID()+1);
			reservation1.setReservationID(Integer.toString(currIndex));
		}
		
		
	}
	
	//Alternative way to retrieve Customer's Reservation List
	public ArrayList<Reservation> getReservationList(String contactNo)
	{
		return reservationList;
	}
	
	public ArrayList<Reservation> getReservationListForDate(LocalDate date)
	{
		return reservationList;
	}

	public Reservation getReservation(String name, String contactNo, LocalDate date, String time)
	{
		if (!reservationList.isEmpty())
		{
			for (int x = 0; x < reservationList.size(); x++)
			{
				Reservation currres = reservationList.get(x);
				if (currres.getName().equals(name) && currres.getContactNo().equals(contactNo) && currres.getTime().equals(time) && currres.getDate().equals(date))
				{
					return currres;
				}
			}
		}
		else 
			System.out.println("Reservation List is Empty!");
		return null;
	}
}
