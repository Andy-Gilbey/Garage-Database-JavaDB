package Tables;

public class Staff {
	private int staff_id;
	private String firstName;
	private String lastName;
	
	
	public Staff() {
		super();
	}

	//Mutators and Accessors
	public int getStaff_id() {
		return staff_id;
	}


	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Staff [staff_id=" + staff_id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
	
}
