package pos;

import java.util.ArrayList;

public class StaffManager {

	public static StaffManager instance;
	private ArrayList<Staff> staffList;
	
	public StaffManager() {
		instance = this;
		staffList = new ArrayList<Staff>();
		//dao
		
		
	}
	
	public void createNewStaff(String staffID, String name, String gender, String jobTitle) {
		Staff s = new Staff(staffID,name,gender,jobTitle);
		staffList.add(s);
	}
	
	public void displayAllStaff() {
		for(int i = 0; i < staffList.size(); i++) {
			System.out.println((i+1) + ") " + staffList.get(i));
		}
	}
	
	public Staff getStaff(String id) {
		for(Staff s : staffList) {
			if(s.getID() == id) {
				return s;
			}
		}
		
		return null;
	}

	
	public boolean deleteStaff(int index) {
		if(index >= 0 && index < staffList.size()) {
			staffList.remove(index);
			
			return true;
		}	
		return false;
	}
	
	
	
}
