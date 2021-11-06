package pos;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ReserveInteractable implements IInteractable {
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	InteractableComponent reservationAssistant = new InteractableComponent("Back",true);
	
	public ReserveInteractable()
	{
		reservationAssistant.addInteractable(new IInteractable() {

			@Override
			public void handleInput() {
				// TODO Auto-generated method stub
				System.out.println("Enter Customer Name");
				String customerName = Application.scanner.nextLine();
				System.out.println("Enter Customer Contact Number");
				String contactNo = Application.scanner.nextLine();
				System.out.println("Enter Number of Pax");
				int noPax = Integer.parseInt(Application.scanner.nextLine()) ;
				System.out.println("Enter Date of Reservation in DD-MM-YYYY");
				String dateString = Application.scanner.nextLine();
				
				  
				  //convert String to LocalDate
				LocalDate localDate = LocalDate.parse(dateString, formatter);
				
				
				System.out.println("Enter Time of Reservation");
				String time = Application.scanner.nextLine();
				
				
				// TODO Need to call CustomerManager
				Customer currCus = new Customer();
				currCus = CustomerManager.instance.getCustomer(customerName,contactNo);
				if (currCus == null)
				{
					System.out.println("Customer does not exist, creating new customer");
					CustomerManager.instance.createCustomer(customerName, contactNo);
					
					currCus = CustomerManager.instance.getCustomer(customerName,contactNo);
				}
				
				
				//TODO Need to call TableManager
				
				//TODO change reservationManager
				ReservationManager.instance.createReservation(customerName, contactNo, noPax, localDate, time);
				Reservation currRes = ReservationManager.instance.getReservation(customerName, contactNo, localDate, time);
				currRes.setCustomerID(CustomerManager.instance.getCustomerID(customerName));
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
				System.out.println("Enter ReservationID");
                String reservation = Application.scanner.nextLine();
                Reservation reservation1 = ReservationManager.instance.getReservation(reservation);
               
                
                
    
                System.out.println("Choose Selected Date.");
                String dateString1 = Application.scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                  
                  //convert String to LocalDate
                LocalDate date1 = LocalDate.parse(dateString1, formatter);
                System.out.println("Choose Selected Time.");
                String time2 = Application.scanner.nextLine();
                if(ReservationManager.instance.getReservation(date1, time2)== null)
                {
                	reservation1.setTime(time2);
                    reservation1.setDate(date1);
                }
                
                System.out.println("Reservation Date have been successfully changed!");
                System.out.println("Reservation Time have been successfully changed!");
                     
				
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
				System.out.println("Enter ReservationID");
				String ReservationID = Application.scanner.nextLine();
				Reservation curr = ReservationManager.instance.getReservation(ReservationID);
				if (curr != null)
				{
					System.out.println("Reservation Date: " + curr.getDate());
					System.out.println("Reservation Time: " + curr.getTime());
					System.out.println("Reservation Table No. : " + curr.getTableNo()); // TODO Need to get Table
					System.out.println("Reservation Number of Pax: " + curr.getNoPax());
				}
				else 
					System.out.println("No Reservation Found");
			}

			@Override
			public String getTitle() {
				// TODO Auto-generated method stub
				return "View Reservation Details" ;
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
