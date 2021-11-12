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

	InteractableComponent reservationAssistant = new InteractableComponent("Back",true);
	private LocalTime timeSlots[] = {LocalTime.parse("10",dtf),LocalTime.parse("11",dtf),LocalTime.parse("12",dtf),LocalTime.parse("13",dtf),LocalTime.parse("14",dtf),LocalTime.parse("15",dtf),LocalTime.parse("16",dtf),LocalTime.parse("17",dtf),LocalTime.parse("18",dtf),LocalTime.parse("19",dtf),LocalTime.parse("20",dtf),LocalTime.parse("21",dtf),LocalTime.parse("22",dtf),LocalTime.parse("23",dtf)}; 
	
	
		
	public ReserveInteractable()
	{
		reservationAssistant.addInteractable(new IInteractable() {

			@Override
			public void handleInput() {
				// TODO Auto-generated method stub
				TableReservationSyncController.sync();
				
				LocalDate localDate = requestValidDate();
				LocalTime time = requestValidTime(getAvailableTimeSlots(localDate));
				
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
				
				TableReservationSyncController.sync();	
	
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
				
				TableReservationSyncController.sync();
				System.out.println("Enter Customer's Contact Number");
                String contactNo = Application.scanner.nextLine();
			
                ArrayList<Reservation> rlist = ReservationManager.instance.getReservationListForContact(contactNo);
                if(rlist.size() <= 0) {
                	System.out.println("No reservations for this number");
                	return;
                }
				
                ReservationManager.instance.displayReservationList(rlist);
                
                System.out.println("Select reservation number to edit:");
                
                int choice = -1;
                
                while(choice < 1 || choice >= rlist.size()+1)
                	choice = Integer.parseInt(Application.scanner.nextLine());
                
                
               Reservation current = rlist.get(choice-1); 
               System.out.println("Enter new date ? "); 
               
               LocalDate dateNew = requestValidDate();
          
               current.setDate(dateNew);
               
               System.out.println("Enter new time ?");
              
               LocalTime timeNew = requestValidTime(getAvailableTimeSlots(dateNew));
               
               if(timeNew == null)
            	   return;
               else
            	   current.setTime(timeNew);
               
               System.out.println("Reservation Date have been successfully changed to " + dateNew + " at " + timeNew + " Hrs");
			
               TableReservationSyncController.sync();
                     
				
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
				TableReservationSyncController.sync();
				System.out.println("Enter Contact Number");
				String contactNo = Application.scanner.nextLine();
				ReservationManager.instance.displayReservationList(ReservationManager.instance.getReservationListForContact(contactNo));
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
				TableReservationSyncController.sync();	
				
				System.out.println("Enter Customer's Contact Number");
                String contactNo = Application.scanner.nextLine();
			
                ArrayList<Reservation> rlist = ReservationManager.instance.getReservationListForContact(contactNo);
       
                if(rlist.size() <= 0) {
                	System.out.println("No reservations for this number");
                	return;
                }
				
                ReservationManager.instance.displayReservationList(rlist); 
                
                System.out.println("Select reservation number to delete:");
                
                int choice = Integer.parseInt(Application.scanner.nextLine());
                if(choice <=0 || choice >rlist.size())
                	System.out.println("Invalid choice");
                else {
                	Reservation toDelete = rlist.get(choice-1);
                	ReservationManager.instance.deleteReservation(toDelete);
                	System.out.println("Reservation deleted.");
                }  
               
				
				TableReservationSyncController.sync();	
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
				TableReservationSyncController.sync();
				ReservationManager.instance.displayReservationList();
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
	
	
	private LocalTime requestValidTime(ArrayList<LocalTime> options) {
		
		if(options == null) {
			System.out.println("no available time slots left!");
			return null;
		}
		
		System.out.println("Select your preferred timeslot.");
		
		for(int x = 0; x < options.size(); x++){
			System.out.println((x+1)+ ".  Timeslot: " + options.get(x));
		}
		System.out.println(" ");
		int choice = Integer.parseInt(Application.scanner.nextLine());
		
		if(choice <= 0 || choice > options.size())
			return requestValidTime(options);
		else
			return options.get(choice-1);
				
		
	}
	
	
	private LocalDate requestValidDate() {
		
			System.out.println("Enter Date of Reservation in DD-MM-YYYY");
			String dateString = Application.scanner.nextLine();
				
			if(dateString.matches("\\d{2}-\\d{2}-\\d{4}")) {  
				//convert String to LocalDate
				LocalDate localDate = LocalDate.parse(dateString, formatter);
				if(localDate.isAfter(LocalDate.now()) || localDate.equals(LocalDate.now()))
					return localDate;
				else
					System.out.println("cannot travel back in time!");
				
			}else {
				System.out.println("date is in wrong format!");
			}
		
		return requestValidDate();
	}
	
	private ArrayList<LocalTime> getAvailableTimeSlots(LocalDate date){
		
		ArrayList<LocalTime> options = new ArrayList<LocalTime>();
        LocalTime time = LocalTime.now();
        int index = 0;
        
        if(date.equals(LocalDate.now())) {
        	for (int x = 0; x < timeSlots.length; x++)
        	{
        		if (time.isBefore(timeSlots[x]))
        		{
        			index = x;
        			break;
        		}
            
        	}
        }
        
        
        for (int y = index; y < timeSlots.length; y++)
        {
            options.add(timeSlots[y]);
        }
        
        if (options.size() == 0)
            return null;
        else 
            return options;
		
		
	}
	

}
