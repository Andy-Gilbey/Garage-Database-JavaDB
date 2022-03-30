package Tables;

public class Customer {
	
	private int customerID;
	private String fname;
	private String lname;
	private String phone;
	private String email;
	private Address address;
	private double budget;
	
	
	//Constructor
	public Customer() {
		super();
	}
	
	//Mutators and Accessors
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", fname=" + fname + ", lname=" + lname + ", phone=" + phone
				+ ", email=" + email + ", address=" + address + "]";
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	
	
	
	
	
}
