package pos;

public class Staff {
	private String staffName;
	private String staffID;
	private String gender;
	private String jobTitle;
	
	public Staff(String staffID, String name, String gender, String jobTitle) {
		this.staffName = name;
		this.staffID = staffID;
		this.gender = gender;
		this.jobTitle = jobTitle;
	}
	
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String staffID() {
		return this.staffID;
	}
	public void setStaffID(String id) {
		this.staffID = id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getID() {
		return this.staffID;
	}
	
	
}
