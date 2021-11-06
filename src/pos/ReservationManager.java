package pos;

import java.time.LocalDate;
import java.util.*;

public class ReservationManager implements ISerializable {

	private Reservation reservation;
	private ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
	private String membershipID;
	private Dao<Reservation> reservationDao;
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
	
	public ReservationManager(Dao<Reservation> dao)
	{
		instance = this;
		this.reservationDao = dao;
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
	
	public Reservation getReservation(LocalDate date, String time)
	{
		if (!reservationList.isEmpty())
		{
			for (int x = 0; x < reservationList.size(); x++)
			{
				Reservation currres = reservationList.get(x);
				if (currres.getTime().equals(time) && currres.getDate().equals(date))
				{
					return currres;
				}
			}
		}
		else 
			System.out.println("Reservation List is Empty!");
		return null;
	}
	
	public void deleteReservation(Date date, String time)
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
				if (currres.getDate().equals(date) && currres.getTime().equals(time))
				{
					reservationList.remove(x);
				}
			}
		}	
	}
	
	public void displayReservationList(ArrayList<Reservation> reservationList)
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

	@Override
	public void serialize() {
		// TODO Auto-generated method stub
		reservationDao.write(reservationList);
	}
}
