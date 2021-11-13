package pos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ReservationDao implements Dao<Reservation>  {

	private static final String filename = "reservation_data.csv";
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
	
	@Override
	public ArrayList<Reservation> read() {
		ArrayList<String[]> result = GenericFileReader.read(filename);
        ArrayList<Reservation> reservationList = new ArrayList<>();
        
		  
		  //convert String to LocalDate
		

        if(result != null){
            for (String[] props : result) {
                Reservation reservation = new Reservation(LocalDate.parse(props[0]),LocalTime.parse(props[1]),Integer.parseInt(props[2]),Integer.parseInt(props[3]),props[4],props[5],props[6],props[7]);
                reservationList.add(reservation);
            }        
        }

        return reservationList;
		
	}

	@Override
	public void write(ArrayList<Reservation> list) {
		// TODO Auto-generated method stub
		String[][] unwrapped = new String[list.size()][8];
        
        for(int i = 0; i < list.size(); i++){
            
            Reservation temp = list.get(i);
            unwrapped[i][0] = temp.getDate().toString();
            unwrapped[i][1] = temp.getTime().toString();
            unwrapped[i][2] = ""+temp.getNoPax();
            unwrapped[i][3] = ""+temp.getTableNo();
            unwrapped[i][4] = temp.getReservationID();
            unwrapped[i][5] = temp.getCustomerID();
            unwrapped[i][6] = temp.getName();
            unwrapped[i][7] = temp.getContactNo();
        }

        GenericFileWriter.writeFile(unwrapped, filename);
	}

}
