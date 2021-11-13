package pos;
import java.time.LocalDate;
import java.time.LocalTime;


public class Reservation {

	private LocalDate date;
	private LocalTime time;
	private int noPax;
	private int tableNo;
	private String reservationID;
	private String contactNo;
	private String name;
	
	public Reservation()
	{
		
	}
	
	public Reservation(String reservationID, LocalDate date, LocalTime time, int noPax, int tableNo, String contactNo , String name)
	{
		this.date = date;
		this.time = time;
		this.noPax = noPax;
		this.tableNo = tableNo;
		this.reservationID = reservationID;
		this.contactNo = contactNo;
		this.name = name;
	}
	
	/**
	 * @return LocalDate that is stored.
	 */
	public LocalDate getDate()
	{
		return date;
	}
	
	/**
	 * @return LocalTime that is stored.
	 */
	public LocalTime getTime()
	{
		return time;
	}
	
	/**
	 * @return Number of pax customer has requested.
	 */
	public int getNoPax()
	{
		return noPax;
	}
	
	/**
	 * @return Contact number of customer.
	 */
	public String getContactNo()
	{
		return contactNo;
	}
	
	/**
	 * @return Table number that is assigned to customer's reservation.
	 */
	public int getTableNo()
	{
		return tableNo;
	}
	
	/**
	 * @return Reservation ID of the current reservation.
	 */
	public String getReservationID()
	{
		return reservationID;
	}
	
	
	/**
	 * @return Name of the customer.
	 */
	public String getName()
	{
		return name;
	}
	
	
	
	/**
	 * Set the date of the reservation.
	 * @param date Date that the reservation is booked.
	 */
	public void setDate(LocalDate date)
	{
		this.date = date;
	}
	
	/**
	 * Set the time of the reservation.
	 * @param time Time that the reservation is booked.
	 */
	public void setTime(LocalTime time)
	{
		this.time = time;
	}
	
	/**
	 * Set the randomly generated reservation ID.
	 * @param reservationID Uniquely randomly generated reservation ID.
	 */
	public void setReservationID(String reservationID)
	{
		this.reservationID = reservationID;
	}
	
	/**
	 * Set Table object table number.
	 * @param tableNo Table object's table number.
	 */
	public void setTableNo(int tableNo)
	{
		this.tableNo = tableNo;
	}
	
}
