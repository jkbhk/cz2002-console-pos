package pos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class ReservationManager{

	private Reservation reservation;
	private ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
	private String membershipID;
	private Dao<Reservation> reservationDao;
	public static ReservationManager instance;
	
	
	public ReservationManager(Dao<Reservation> dao)
	{
		instance = this;
		this.reservationDao = dao;
		//reservationList = reservationDao.read();
	}
	// To retrieve customer's particular reservation.
	
	public Reservation getReservation(String reservationID)
	{
		Reservation reservation = new Reservation();
		
		if (!reservationList.isEmpty())
		{
			for (int x = 0; x < reservationList.size(); x++)
			{
				reservation = reservationList.get(x);
				if (reservation.getReservationID().equals(reservationID))
				{
					return reservation;
				}
			}
		}
		else 
			
			System.out.println("Reservation List is Empty!");
		return reservation;
	}
	
	
	public void createReservation(String name, String contactNo, int noPax, LocalDate date, LocalTime time)
	{
		// To - Do
		Reservation reservation1 = new Reservation();
		reservation1.makeReservation(date, time, noPax, name, contactNo);
		
		reservation1.setReservationID(IDGenerator.GenerateUniqueID());
		reservationList.add(reservation1);
		System.out.println("Reservation " + reservation1.getReservationID() + " has been created for\n" + "Mr/Mrs/Ms " +reservation1.getName() + " on " + reservation1.getDate() + " at " + reservation1.getTime() +" Hrs."  );
		
	}
	
	//Alternative way to retrieve Customer's Reservation List
	public ArrayList<Reservation> getReservationList(String contactNo)
	{
		ArrayList<Reservation> customerReservation = new ArrayList<Reservation>();
		
		if (reservationList.isEmpty())
		{
			System.out.println("The Reservation List is Empty!");
		}
		
		else 
		{
			for (int x = 0; x < reservationList.size(); x++)
			{
				Reservation currres = reservationList.get(x);
				if (currres.getContactNo().equals(contactNo))
				{
					customerReservation.add(currres);
				}
			}
		}
		
		return customerReservation;
	}
	
	public ArrayList<Reservation> getReservationListForDate(LocalDate date)
	{
		ArrayList<Reservation> dateReservation = new ArrayList<Reservation>();
		
		if (reservationList.isEmpty())
		{
			System.out.println("The Reservation List is Empty!");
		}
		
		else 
		{
			for (int x = 0; x < reservationList.size(); x++)
			{
				Reservation currres = reservationList.get(x);
				if (currres.getDate().equals(date))
				{
					dateReservation.add(currres);
				}
			}
		}
		
		return dateReservation;
	}
	
	public ArrayList<Reservation> getReservationList()
	{
		if (reservationList.isEmpty())
		{
			
			return null;
		}
		else 
			return reservationList;
	}

	public Reservation getReservation( LocalDate date, LocalTime time, String contactNo)
	{
		if (!reservationList.isEmpty())
		{
			for (int x = 0; x < reservationList.size(); x++)
			{
				Reservation currres = reservationList.get(x);
				if (currres.getContactNo().equals(contactNo) && currres.getTime().equals(time) && currres.getDate().equals(date))
				{
					return currres;
				}
			}
		}
		else 
			System.out.println("Reservation List is Empty!");
		return null;
	}
	

	
	public void deleteReservation(Reservation reservation1)
	{
		if (reservationList.isEmpty())
		{
			System.out.println("There are no reservations");
		}
		
		else
		{
			for (int x = 0; x < reservationList.size(); x++)
			{
				Reservation currres = reservationList.get(x);
				if (currres.getDate().equals(reservation1.getDate()) && currres.getTime().equals(reservation1.getTime()))
				{
					reservationList.remove(x);
					System.out.println("Reservation is deleted.");
				}
			}
		}	
	}
	
	public void displayReservationList(ArrayList<Reservation> reservationList)
	{
		if (reservationList== null)
		{
			System.out.println("There are no reservations");
		}
		
		else 
		{
			for (int x = 0; x < reservationList.size(); x++)
			{
				Reservation currres = reservationList.get(x);
				System.out.println("Reservation Date: " + currres.getDate());
				System.out.println("Reservation Time: " + currres.getTime());
				System.out.println("ReservationID: " + currres.getReservationID());
				System.out.println("Number of Pax: " + currres.getNoPax());
				System.out.println("Table Number: " + currres.getTableNo());
				System.out.println("Customer's Name: " + currres.getName());
				System.out.println("Customer's Contact Number: " + currres.getContactNo());
				System.out.println(" ");
			}
		}
	}
	
	public void displayReservation(Reservation reservation)
	{
		if (reservation.getDate() != null & reservation.getTime() != null)
		{
			System.out.println("Reservation Date: " + reservation.getDate());
			System.out.println("Reservation Time: " + reservation.getTime());
			System.out.println("Reservation Table No. : " + reservation.getTableNo()); // TODO Need to get Table
			System.out.println("Reservation Number of Pax: " + reservation.getNoPax());
			System.out.println("Customer's Name: " + reservation.getName());
			System.out.println("Customer's Contact Number: " + reservation.getContactNo());
		}
		else 
			System.out.println("No Reservation Found!");
	}
	
	public boolean reservationChecker (LocalDate date, LocalTime time)
	{
		boolean checker = false; 
		
		for (int x = 0; x < reservationList.size(); x++)
		{
			Reservation currres = reservationList.get(x);
			if (currres.getTime().equals(time) && currres.getDate().equals(date)) //will require to check available tables next time
			{
				return checker = true;
			}
		}
		return checker;
	}


	public void save() {
		
		reservationDao.write(reservationList);
	}
}
