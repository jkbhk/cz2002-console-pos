package pos;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Reservation {

	private LocalDate date;
	private LocalTime time;
	private int noPax;
	private int tableNo;
	private String reservationID;
	private String customerID;
	private String contactNo;
	private String name;
	
	public Reservation()
	{
		
	}
	
	public Reservation(LocalDate date, LocalTime time, int noPax, int tableNo, String reservationID, String customerID, String contactNo , String name)
	{
		this.date = date;
		this.time = time;
		this.noPax = noPax;
		this.tableNo = tableNo;
		this.reservationID = reservationID;
		this.customerID = customerID;
		this.contactNo = contactNo;
		this.name = name;
	}
	
	public LocalDate getDate()
	{
		return date;
	}
	
	public LocalTime getTime()
	{
		return time;
	}
	
	public int getNoPax()
	{
		return noPax;
	}
	
	public String getContactNo()
	{
		return contactNo;
	}
	
	public int getTableNo()
	{
		return tableNo;
	}
	
	public String getReservationID()
	{
		return reservationID;
	}
	
	public String getCustomerID()
	{
		return customerID;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void makeReservation(LocalDate date, LocalTime time, int noPax, String name, String contactNo)
	{
		this.date = date; 
		this.time = time;
		this.noPax = noPax;
		this.name = name;
		this.contactNo = contactNo;
	}
	
	public void setDate(LocalDate date)
	{
		this.date = date;
	}
	
	public void setTime(LocalTime time)
	{
		this.time = time;
	}
	
	public void setCustomerID(String customerID)
	{
		this.customerID = customerID;
	}
	
	public void setReservationID(String reservationID)
	{
		this.reservationID = reservationID;
	}
	
	public void setTableNo(int tableNo)
	{
		this.tableNo = tableNo;
	}
	
}
