package pos;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class ReservationManager{

	
	private ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
	private Dao<Reservation> reservationDao;
	public static ReservationManager instance;
	public static final long RESERVATION_EXPIRY = 20;
	
	
	public ReservationManager(Dao<Reservation> dao)
	{
		instance = this;
		this.reservationDao = dao;
		reservationList = reservationDao.read();
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
	
	
	public void createReservation(String name, String contactNo, int noPax, LocalDate date, LocalTime time, int tableNo)
	{
		// To - Do
		Reservation reservation1 = new Reservation(IDGenerator.GenerateUniqueID(), date, time,noPax,tableNo,contactNo,name);
		reservationList.add(reservation1);
		System.out.println("Reservation has been created for\n" + "Mr/Mrs/Ms " +reservation1.getName() + " on " + reservation1.getDate() + " at " + reservation1.getTime() +" Hrs."  );
		
	}
	
	//Alternative way to retrieve Customer's Reservation List
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
	
	public ArrayList<Reservation> getReservationList()
	{
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
	

	
	public void deleteReservation(Reservation reservation)
	{
		reservationList.remove(reservation);
	}
	
	public void deleteReservation(int index)
	{
		reservationList.remove(index);
	}
	
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
