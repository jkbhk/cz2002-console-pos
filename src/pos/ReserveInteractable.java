package pos;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import pos.Table.STATUS;

import java.time.Duration;

public class ReserveInteractable implements IInteractable {
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
	public LocalTime currentTime;
	public LocalDate currentDate;
	InteractableComponent reservationAssistant = new InteractableComponent("Back",true);
	
	
	
	private void refreshReservationList()
	{
		currentTime = LocalTime.now();
		currentDate = LocalDate.now();
		
		ArrayList<Reservation> rList = ReservationManager.instance.getReservationList();
		if (rList != null)
		{
			for (int x = 0; x < rList.size(); x++)
			{
				LocalTime resTime = rList.get(x).getTime();
				LocalDate resDate = rList.get(x).getDate();
				Duration duration = Duration.between(resTime, currentTime);
				long difference = duration.toMinutes();
				
				if (currentDate.equals(resDate) && difference > 20)  //Orders Cancel in 20 Minutes 
				{
					rList.remove(x);
				}
			}
		}
		else;
	}
	
	
	private LocalTime requestTime() {
		
		System.out.println("Enter time in HH");
		String timeString = Application.scanner.nextLine();
				
		if(timeString.matches("\\d{2}")) {  
				//convert String to LocalDate
			LocalTime localTime = LocalTime.parse(timeString, dtf);
			return localTime;
		}
		System.out.println("Time is in the wrong format!");
		
		return null;
	}
	
	private LocalDate requestDate() {
		
		System.out.println("Enter Date of Reservation in DD-MM-YYYY");
		String dateString = Application.scanner.nextLine();
				
		if(dateString.matches("\\d{2}-\\d{2}-\\d{4}")) {  
				//convert String to LocalDate
			LocalDate localDate = LocalDate.parse(dateString, formatter);
			return localDate;
		}
		
		System.out.println("Date is in the wrong format!");
		return null;
	}
	
	public ReserveInteractable()
	{
		reservationAssistant.addInteractable(new IInteractable() {

			@Override
			public void handleInput() {
				// TODO Auto-generated method stub
				
				LocalDate localDate = requestDate();
				if(localDate == null)
					return;
				
				LocalTime time = requestTime();
				if(time == null)
					return;
				
				
				System.out.println("Enter Customer Name");
				String customerName = Application.scanner.nextLine();
				System.out.println("Enter Customer Contact Number");
				String contactNo = Application.scanner.nextLine();
				
				System.out.println("Enter Number of Pax");
				int noPax = Integer.parseInt(Application.scanner.nextLine()) ;
				
				
				//TODO Need to call TableManager
				//To set table as book and set tableNo in reservation.
				ArrayList<Table> tableList = TableManager.instance.getTableList();
				Table currTable = null;
				int tableNumber = 0;
				
				
				if (tableList.isEmpty())
				{
					System.out.println("There is no table");
					return;
				}
				//Check if table is available & number of pax == tableSize
				for (int x = tableNumber; x < tableList.size(); x++)
				{
					if (tableList.get(x).getTableSize() == noPax && !ReservationManager.instance.reservationChecker(localDate, time,tableNumber))
					{
						currTable = tableList.get(x);
						tableNumber = x+1;
						break;
					}
					else;
				}
				
				if (currTable == null) //Exits and Let User Know No reservation available at that time.
					{
						System.out.println("There is no available reservation.");
						return;
					}
				else
				{
					// TODO Need to call CustomerManager
					Customer currCus = new Customer();
					boolean checker = CustomerManager.instance.checkCustomerInList(customerName, contactNo);
					if (!checker)
					{
						System.out.println("Customer does not exist, creating new customer");
						CustomerManager.instance.createCustomer(customerName, contactNo);
						
						currCus = CustomerManager.instance.getCustomer(customerName,contactNo);
					}
					
					CustomerManager.instance.displayCustomerList();
					
					//TODO change reservationManager
					ReservationManager.instance.createReservation(customerName, contactNo, noPax, localDate, time);
					ReservationManager.instance.getReservation(localDate,time,contactNo).setTablNo(tableNumber);
					refreshReservationList();
				}
				
				//To Check if the date and time is available.
				
					//ReservationManager.instance.getReservation(localDate, time, contactNo).setTablNo(availTable);
					//ReservationManager.instance.displayReservationList(ReservationManager.instance.getReservationList(contactNo));
				
			
				
			}

			@Override
			public String getTitle() {
				// TODO Auto-generated method stub
				return "Create New Reservation" ;
			}
			
		});
		
		reservationAssistant.addInteractable(new IInteractable() {

			@Override
			public void handleInput() {
				// TODO Auto-generated method stub
				
				LocalDate date = requestDate();
				if(date == null)
					return;
				
				LocalTime time = requestTime();
				if(time == null)
					return;
				
				
				System.out.println("Enter Customer's Contact Number");
                String contactNo = Application.scanner.nextLine();
                
				if (ReservationManager.instance.reservationChecker(date,time) && ReservationManager.instance.getReservation(date, time, contactNo).getContactNo().equals(contactNo))
				{
					Reservation reservation1 = ReservationManager.instance.getReservation(date,time, contactNo);
	                
	               
	                //TableManager maybe can display available Tables to reserve
	                
	                System.out.println("Select new date");
	                LocalDate dateNew = requestDate();
					if(date == null)
						return;
					
					System.out.println("Select new time");
					LocalTime timeNew = requestTime();
					if(time == null)
						return;
	                
	                if(ReservationManager.instance.reservationChecker(dateNew, timeNew))
	                {
	                	System.out.println("This reservation is not available to book.");
	                }
	                
	                else 
	                {
	                	reservation1.setDate(dateNew);
	                	reservation1.setTime(timeNew);
	                	System.out.println("Reservation Date have been successfully changed to " + dateNew + " at " + timeNew + " Hrs");
	                   
	                }
				}
				
				else
				{
					System.out.println("This Reservation Does Not Exist.");
				}
                
				refreshReservationList();
                     
				
			}

			@Override
			public String getTitle() {
				// TODO Auto-generated method stub
				return "Edit Reservation" ;
			}
			
		});
		
		reservationAssistant.addInteractable(new IInteractable() {

			@Override
			public void handleInput() {
				// TODO Auto-generated method stub
				System.out.println("Enter Contact Number");
				String contactNo = Application.scanner.nextLine();
				if (ReservationManager.instance.getReservationList(contactNo).size() == 0)
				{
					System.out.println("There is no such reservation.");
				}
				else
				{
					ReservationManager.instance.displayReservationList(ReservationManager.instance.getReservationList(contactNo));
				}
				
				refreshReservationList();
			}

			@Override
			public String getTitle() {
				// TODO Auto-generated method stub
				return "View Customer's Reservations Details" ;
			}
			
		});
		
		reservationAssistant.addInteractable(new IInteractable() {

			@Override
			public void handleInput() {
				// TODO Auto-generated method stub
				System.out.println("Enter Reservation Date to delete");
				
				LocalDate date = LocalDate.parse(Application.scanner.nextLine(),formatter);
				ArrayList<Reservation> resList = ReservationManager.instance.getReservationListForDate(date);
				for (int x = 0; x <resList.size(); x++ )
				{
					System.out.println((x+1)+".  Date: " + resList.get(x).getDate().toString() + " Time: " + resList.get(x).getTime());
				}
				
				if (resList.size() == 0)
				{
					System.out.println("There is no reservations to delete");
				}
				else 
				{
					System.out.println("Enter your choice to delete");
					int choice = Integer.parseInt(Application.scanner.nextLine());
					ReservationManager.instance.deleteReservation(resList.get(choice-1));
				}
				refreshReservationList();
			}

			@Override
			public String getTitle() {
				// TODO Auto-generated method stub
				return "Delete Reservation" ;
			}
			
		});
		
		reservationAssistant.addInteractable(new IInteractable() {

			@Override
			public void handleInput() {
				ArrayList<Reservation> resList = ReservationManager.instance.getReservationList();
				ReservationManager.instance.displayReservationList(resList);
				refreshReservationList();
			}

			@Override
			public String getTitle() {
				// TODO Auto-generated method stub
				return "Display All Reservation List" ;
			}
			
		});
		
	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		reservationAssistant.start();
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Manage Reservations"; 
	}

}
