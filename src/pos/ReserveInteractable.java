package pos;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ReserveInteractable implements IInteractable {

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		System.out.println("Select your choice that would like to do for reservations");
		System.out.println("1) Create New Reservation"); // Ask for Customer's ID, if its not existing -> create new customer
		System.out.println("2) Edit Reservation"); // Ask for Customer's ID
		System.out.println("3) View Reservation Details");
		System.out.println("4) Delete Reservation"); // Ask for Customer's ID
		int choice = Integer.parseInt(Application.scanner.nextLine());
		
		while (choice!=5)
		{
			switch(choice)
			{
				case 1: // Ask for Customer Details and Reservation Requirements
				{
					System.out.println("Enter Customer Name");
					String customerName = Application.scanner.nextLine();
					System.out.println("Enter Number of Pax");
					int noPax = Integer.parseInt(Application.scanner.nextLine()) ;
					System.out.println("Enter Date of Reservation in DD-MM-YYYY");
					String dateString = Application.scanner.nextLine();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					  
					  //convert String to LocalDate
					LocalDate localDate = LocalDate.parse(dateString, formatter);
					
					
					System.out.println("Enter Time of Reservation");
					String time = Application.scanner.next();
					
					
					// TODO Need to call CustomerManager
					String customerID = CustomerManager.instance.getCustomerID(customerName);
					Customer currCus = CustomerManager.instance.getCustomer(customerID);
					String custName = currCus.getName();
					String custContactNo = currCus.getContactNo();
					
					//TODO Need to call TableManager
					
					//TODO change reservationManager
					ReservationManager.instance.createReservation(custName, custContactNo, noPax, localDate, time);
					Reservation currRes = ReservationManager.instance.getReservation(custName, custContactNo, localDate, time);
					currRes.setCustomerID(customerID);
					
					
					break;
				}
					
				case 2:
				{
					System.out.println("Enter ReservationID");
					String reservation = Application.scanner.next();
					Reservation reservation1 = ReservationManager.instance.getReservation(reservation);
					System.out.println("What would you like to edit for your reservation?");
					
					System.out.println("1) Change Reservation Date.");
					System.out.println("2) Change Reservation Time.");
					System.out.println("3) Exit.");
					
					
					int x = Integer.parseInt(Application.scanner.nextLine());
					
					while ( x != 3)
					{
						switch(x)
						{
							case 1:
							{
								System.out.println("Choose Selected Date.");
								String dateString1 = Application.scanner.next();
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
								  
								  //convert String to LocalDate
								LocalDate date1 = LocalDate.parse(dateString1, formatter);
								reservation1.setDate(date1);
								System.out.println("Reservation Date have been successfully changed!");
								break;
							}
							
							case 2: 
							{
								System.out.println("Choose Selected Time.");
								String time2 = Application.scanner.next();
								reservation1.setTime(time2);
								System.out.println("Reservation Time have been successfully changed!");
								break;
							}
						}
						break;
					}
					break;
				}
				
				case 3: 
				{
					System.out.println("Enter ReservationID");
					String ReservationID = Application.scanner.next();
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
					
					break;
				}
				
				case 4: //TODO
					System.out.println("Enter ReservationID to delete");
					String currResID = Application.scanner.next();
					
			}
			break;
		}
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Manage Reservations"; 
	}

}
