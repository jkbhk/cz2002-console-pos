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
	public LocalTime currentTime = LocalTime.now();
	public LocalDate currentDate = LocalDate.now();
	private LocalDate reservationDate;
	InteractableComponent reservationAssistant = new InteractableComponent("Back",true);
	private LocalTime timeSlots[] = new LocalTime[14]; 
	
	
	
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
		
	
		
		LocalTime timeSlots[] = {LocalTime.parse("10",dtf),LocalTime.parse("11",dtf),LocalTime.parse("12",dtf),LocalTime.parse("13",dtf),LocalTime.parse("14",dtf),LocalTime.parse("15",dtf),LocalTime.parse("16",dtf),LocalTime.parse("17",dtf),LocalTime.parse("18",dtf),LocalTime.parse("19",dtf),LocalTime.parse("20",dtf),LocalTime.parse("21",dtf),LocalTime.parse("22",dtf),LocalTime.parse("23",dtf)};
		
		System.out.println("Select your preferred timeslot.");
		
		for(int x = 0; x < 14; x++)
		{
			System.out.println((x+1)+ ".  Time: " + timeSlots[x]);
		}
		System.out.println(" ");
		
		
		
		int choice = -1;
		while (choice <= 0 || choice > 15)
		{
			System.out.println("Select your TimeSlot");
			choice = Integer.parseInt(Application.scanner.nextLine());
			LocalTime localTime = timeSlots[choice-1];
			
			if (reservationDate.isEqual(currentDate) && localTime.isAfter(currentTime))
			{
				return localTime;
			}
			else 
				System.out.println("You cannot book a reservation that has passed.");
				return null;
		}
		
		
		return null;
	}
	
	private LocalDate requestDate() {
		
		System.out.println("Enter Date of Reservation in DD-MM-YYYY");
		String dateString = Application.scanner.nextLine();
				
		if(dateString.matches("\\d{2}-\\d{2}-\\d{4}")) {  
				//convert String to LocalDate
			LocalDate localDate = LocalDate.parse(dateString, formatter);
			if (localDate.isAfter(currentDate) || localDate.isEqual(currentDate))
			{
				reservationDate = localDate;
				return localDate;
			}
			else 
			{
				System.out.println("You cannot book a reservation that has passed.");
				return null;
			}
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
				refreshReservationList(); //Refresh List
				
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
				
				
				
				TableManager.instance.printTablesList();
								
				
				if (tableList.isEmpty())
				{
					System.out.println("There is no table");
					return;
				}
				//Check if table is available & number of pax == tableSize
				System.out.println("Select Table Number for Reservation.");
				int tableNo = Integer.parseInt(Application.scanner.nextLine());
				
				if (ReservationManager.instance.reservationChecker(localDate, time,TableManager.instance.getTable(tableNo).getTableNo()))
				{
					System.out.println("This Reservation has already been booked");
				}
				
				else if (TableManager.instance.getTable(tableNo).getTableSize() < noPax)
				{
					System.out.println("This Table is Not Suitable for Number of Pax.");
				}
				
				else
				{
					// TODO Need to call CustomerManager
					Customer currCus = new Customer();
					boolean checker = CustomerManager.instance.checkCustomerInList(customerName, contactNo);
					if (!checker)
					{
						System.out.println("Customer does not exist, creating new customer");
						String customerID = IDGenerator.GenerateUniqueID();
						String membershipID = IDGenerator.GenerateUniqueAlphaNum(5);
						CustomerManager.instance.createCustomer(customerName, contactNo, membershipID, customerID, true);
						
						currCus = CustomerManager.instance.getCustomer(customerName,contactNo);
					}
					
					//CustomerManager.instance.displayCustomerList();s
					
					//TODO change reservationManager
					ReservationManager.instance.createReservation(customerName, contactNo, noPax, localDate, time);
					ReservationManager.instance.getReservation(localDate,time,contactNo).setTableNo(tableNo);
					
				}
				
				
				
			
				
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
