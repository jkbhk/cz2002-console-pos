package pos;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		int choice = Application.scanner.nextInt();
		while (choice!=4)
		{
			switch(choice)
			{
				case 1: // Ask for Customer Details and Reservation Requirements
				{
					System.out.println("Enter CustomerID");
					String customerID = Application.scanner.next();
					System.out.println("Enter Number of Pax");
					int noPax = Application.scanner.nextInt();
					System.out.println("Enter Date of Reservation in DD-MM-YYYY");
					String dateString = Application.scanner.next();
					DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); // This maybe can implement method or smth
					Date date = null;
					try {
						date = formatter.parse(dateString);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Enter Time of Reservation");
					String time = Application.scanner.next();
					
					
					// TODO Need to call CustomerManager
					Customer currcus = CustomerManager.instance.getCustomer(customerID);
					String custName = currcus.getName();
					String custContactNo = currcus.getContactNo();
					
					//TODO change reservationManager
					ReservationManager.instance.createReservation(custName, custContactNo, noPax, date, time);
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
					
					int x = Application.scanner.nextInt();
					
					while ( x != 3)
					{
						switch(x)
						{
							case 1:
							{
								System.out.println("Choose Selected Date.");
								String dateString1 = Application.scanner.next();
								DateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
								Date date1 = null;
								try {
									date1 = formatter1.parse(dateString1);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
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
					}
					break;
				}
				
				case 3: 
				{
					System.out.println("Enter ReservationID");
					String ReservationID = Application.scanner.next();
					Reservation curr = ReservationManager.instance.getReservation(ReservationID);
					System.out.println("Reservation Date: " + curr.getDate());
					System.out.println("Reservation Time: " + curr.getTime());
					System.out.println("Reservation Table No. : " + curr.getTableNo());
					System.out.println("Reservation Number of Pax: " + curr.getNoPax());
					break;
				}
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
