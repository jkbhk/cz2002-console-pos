package pos;
import java.util.*;

public class Customer {
	
	private String name;
	private String contactNo;
	private String membershipID;
	private String customerID;
	
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
	
}
