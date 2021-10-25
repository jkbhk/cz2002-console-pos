package pos;
import java.util.*;

public class Reservation {

	private Date date;
	private String time;
	private int noPax;
	private int tableNo;
	private String reservationID;
	private String customerID;
	private String contactNo;
	
	public Date getDate()
	{
		return date;
	}
	
	public String getTime()
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
	
	public void makeReservation(Date date, String time, int noPax, String name, String contactNo)
	{
		// To-Do
	}
	
}
