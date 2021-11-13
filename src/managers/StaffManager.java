package managers;

import java.util.ArrayList;

import dao.GenericDao;
import entities.Staff;
/**
 * 
 * Manages Staff objects.
 *
 */
public class StaffManager {
/**
 * Available job titles a staff can hold.
 * A staff can only have one job title.	
 */
	public final String[] JOB_TITLES = {"Cashier", "Manager"};
	/**
	 * Globally accessible instance.
	 */
	public static StaffManager instance;
	private Staff currentStaff; 
	
	private ArrayList<Staff> staffList;
	private GenericDao<Staff> dao;
	
	
	public StaffManager(GenericDao<Staff> d) {
		instance = this;
		staffList = new ArrayList<Staff>();
		dao = d;
		staffList = dao.read();
		
	}
	
	/**
	 * Sets the current staff of the system.
	 */
	public void setCurrentStaff(Staff s) {
		currentStaff = s;
	}
	
	/**
	 * 
	 * @return the current staff of the system.
	 */
	public Staff getCurrentStaff() {
		return this.currentStaff;
	}
	
	/**
	 * Creates a a Staff object and adds it to the staff list.
	 */
	public void createNewStaff(String staffID, String name, String gender, String jobTitle) {
		Staff s = new Staff(staffID,name,gender,jobTitle);
		staffList.add(s);
	}
	
	/**
	 * Displays all the available job titles.
	 */
	public void displayJobTitles() {
		for(int i = 0; i < JOB_TITLES.length; i++) {
			System.out.println((i+1) + ") " + JOB_TITLES[i]);
		}
	}
	
	/**
	 * Displays all staff that are registered in the system.
	 */
	public void displayAllStaff() {
		for(int i = 0; i < staffList.size(); i++) {
			Staff s = staffList.get(i);
			System.out.println((i+1) + ") " + s.getStaffName() + "  "+s.getGender()+"  "+"("+s.getJobTitle()+")");
		}
	}
	
	/**
	 * 
	 * @return a Staff object given a staff ID.
	 */
	public Staff getStaff(String id) {
		for(Staff s : staffList) {
			if(s.getID() == id) {
				return s;
			}
		}
		
		return null;
	}
	
	/**
	 * 
	 * @return the Staff object at the given index location of the staff list.n
	 */
	public Staff getStaff(int index) {
		return staffList.get(index);
	}
	
	/**
	 * 
	 * @return the number of staff registered in the system.
	 */
	public int getStaffCount() {
		return this.staffList.size();
	}

	/**
	 * 
	 * @return true if deletion is successful.
	 */
	public boolean deleteStaff(int index) {
		if(index >= 0 && index < staffList.size()) {
			staffList.remove(index);
			
			return true;
		}	
		return false;
	}
	
	/**
	 * Updates and saves the staff list.
	 */
	public void save() {
		dao.write(staffList);
	}
	
	
}
