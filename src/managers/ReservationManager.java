package managers;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import dao.GenericDao;
import pos.IDGenerator;
import pos.Reservation;
/**
 * 
 * Manages Reservation objects.
 *
 */
public class ReservationManager{

	
	private ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
	private GenericDao<Reservation> reservationDao;
	/**
	 * Globally accessible instance.
	 */
	public static ReservationManager instance;
	/**
	 * The default reservation lifetime in minutes.
	 */
	public static final long RESERVATION_EXPIRY = 20;
	
	
	public ReservationManager(GenericDao<Reservation> dao)
	{
		instance = this;
		this.reservationDao = dao;
		reservationList = reservationDao.read();
	}
	/**
	 *  
	 * @param reservationID
	 * @return the Reservation object given the reservation ID from the reservation list.
	 */
	
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
	
	/**
	 * Creates a Reservation object and adds it to the reservation list.
	 * @param name
	 * @param contactNo
	 * @param noPax
	 * @param date
	 * @param time
	 * @param tableNo
	 */
	public void createReservation(String name, String contactNo, int noPax, LocalDate date, LocalTime time, int tableNo)
	{
		// To - Do
		Reservation reservation1 = new Reservation(IDGenerator.GenerateUniqueID(), date, time,noPax,tableNo,contactNo,name);
		reservationList.add(reservation1);
		System.out.println("Reservation has been created for\n" + "Mr/Mrs/Ms " +reservation1.getName() + " on " + reservation1.getDate() + " at " + reservation1.getTime() +" Hrs."  );
		
	}
	
/**
 * 	
 * @param contactNo
 * @return a list of Reservation objects that matches the given contact number from the reservation list.
 */
	public ArrayList<Reservation> getReservationListForContact(String contactNo)
	{
		ArrayList<Reservation> customerReservation = new ArrayList<Reservation>();
		
	
			for (Reservation r : reservationList)
			{
				if (r.getContactNo().equals(contactNo))
				{
					customerReservation.add(r);
				}
			}
		
		return customerReservation;
	}
	
	/**
	 * 
	 * @param date
	 * @return a list of Reservation objects that matches the given date from the reservation list.
	 */
	public ArrayList<Reservation> getReservationListForDate(LocalDate date)
	{
		ArrayList<Reservation> dateReservation = new ArrayList<Reservation>();
		
		
			for (int x = 0; x < reservationList.size(); x++)
			{
				Reservation currres = reservationList.get(x);
				if (currres.getDate().equals(date))
				{
					dateReservation.add(currres);
				}
			}
		
		return dateReservation;
	}
	
	/**
	 * 
	 * @return all the Resservation objects in the reservation list.
	 */
	public ArrayList<Reservation> getReservationList()
	{
		return reservationList;
	}

	/**
	 * 
	 * @param date
	 * @param time
	 * @param contactNo
	 * @return the Reservation object that matches the specified information from the reservation list.
	 */
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
	

	/**
	 * Deletes the specified Reservation object from the reservation list.
	 * @param reservation
	 */
	public void deleteReservation(Reservation reservation)
	{
		reservationList.remove(reservation);
	}
	
	/**
	 * Deletes the Reservation object at the specified index position of the reservation list.
	 * @param index
	 */
	public void deleteReservation(int index)
	{
		reservationList.remove(index);
	}
	
	/**
	 * Displays all Reservation objects in the given reservation list.
	 * @param reservationList
	 */
	public void displayReservationList(ArrayList<Reservation> reservationList)
	{
		if (reservationList == null)
			return;
		if (reservationList.isEmpty())
		{
			System.out.println("There are no reservations");
		}
		
		else 
		{
			for (int x = 0; x < reservationList.size(); x++)
			{
				Reservation currres = reservationList.get(x);
				System.out.println("====== Reservation ("+ (x+1) +") ======");
				System.out.println("Reservation Date: " + currres.getDate());
				System.out.println("Reservation Time: " + currres.getTime());
				System.out.println("Number of Pax: " + currres.getNoPax());
				System.out.println("Table Number: " + currres.getTableNo());
				System.out.println("Customer's Name: " + currres.getName());
				System.out.println("Customer's Contact Number: " + currres.getContactNo());
				System.out.println(" ");
			}
		}
	}
	
	/**
	 * Displays all Reservation objects in the reservation list.
	 */
	public void displayReservationList()
	{
		if (reservationList == null)
			return;
		if (reservationList.isEmpty())
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
				System.out.println("Number of Pax: " + currres.getNoPax());
				System.out.println("Table Number: " + currres.getTableNo());
				System.out.println("Customer's Name: " + currres.getName());
				System.out.println("Customer's Contact Number: " + currres.getContactNo());
				System.out.println(" ");
			}
		}
	}
	
	/**
	 * 
	 * @param date
	 * @param time
	 * @param tableNo
	 * @return true if there is a Reservation object that matches the specified details
	 */
	public boolean reservationChecker (LocalDate date, LocalTime time, int tableNo)
	{
		boolean checker = false; 
		
		for (int x = 0; x < reservationList.size(); x++)
		{
			Reservation currres = reservationList.get(x);
			if (currres.getTime().equals(time) && currres.getDate().equals(date) && currres.getTableNo() == tableNo) //will require to check available tables next time
			{
				return checker = true;
			}
		}
		return checker;
	}
	
	/**
	 * 
	 * @param date
	 * @param time
	 * @return true there is a Reservation object that matches the specified details
	 */
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
	
/**
 * Updates and saves the reservation list.
 */
	public void save() {
		
		reservationDao.write(reservationList);
	}
}
