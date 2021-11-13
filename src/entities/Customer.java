package entities;

/**
 * 
 * 
 * Customer class entity that stores customer's information such as name, contact number, membership ID, customer ID and if Customer is a member. 
 * @param membeshipID Membership ID is a 5 alphanumeric code that is randomly generated for each customer member.
 * @param customerID Customer ID is a randomly generate unique code that is given to each customer.
 * 
 * 
 */

public class Customer {
	
	private String name;
	private String contactNo;
	private String membershipID;
	private String customerID;
	private boolean isMember;
	
	public Customer()
	{
		this.setMember(false);
	}
	
	public Customer(String name, String contactNo, String membershipID, String customerID) {
		this.name = name;
		this.contactNo = contactNo;
		this.membershipID = membershipID;
		this.customerID = customerID;
	}
	
	public String getMembershipID()
	{
		return membershipID;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getContactNo()
	{
		return contactNo;
	}
	
	public String getCustomerID()
	{
		return customerID;
	}
	
	public void setMembershipID(String membershipID)
	{
		this.membershipID = membershipID;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setContactNo(String contactNo)
	{
		this.contactNo = contactNo;
	}
	
	public void setCustomerID(String customerID)
	{
		this.customerID = customerID;
	}

	public boolean isMember() {
		return isMember;
	}

	public void setMember(boolean isMember) {
		this.isMember = isMember;
	}
	
}
