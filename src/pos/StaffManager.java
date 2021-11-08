package pos;

import java.util.ArrayList;

public class StaffManager {
	
	public static final String[] JOB_TITLES = {"Cashier", "Manager"};
	
	public static StaffManager instance;
	private ArrayList<Staff> staffList;
	private Dao<Staff> dao;
	
	public StaffManager(Dao<Staff> d) {
		instance = this;
		staffList = new ArrayList<Staff>();
		dao = d;
		staffList = dao.read();
		
	}
	
	public void createNewStaff(String staffID, String name, String gender, String jobTitle) {
		Staff s = new Staff(staffID,name,gender,jobTitle);
		staffList.add(s);
	}
	
	public void displayJobTitles() {
		for(int i = 0; i < JOB_TITLES.length; i++) {
			System.out.println((i+1) + ") " + JOB_TITLES);
		}
	}
	
	public void displayAllStaff() {
		for(int i = 0; i < staffList.size(); i++) {
			Staff s = staffList.get(i);
			System.out.println((i+1) + ") " + s.getStaffName() + "  "+s.getGender()+"  "+"("+s.getJobTitle()+")");
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
	
	public int getStaffCount() {
		return this.staffList.size();
	}

	
	public boolean deleteStaff(int index) {
		if(index >= 0 && index < staffList.size()) {
			staffList.remove(index);
			
			return true;
		}	
		return false;
	}
	
	public void save() {
		dao.write(staffList);
	}
	
	
}
