package Tables;

public class Address {

	private String addressLn1;
	private String addressLn2;
	private String town;
	private String county;
	
	public Address() {
		super();
	}

	//Mutators and Accessorss
	public String getAddressLn1() {
		return addressLn1;
	}

	public void setAddressLn1(String addressLn1) {
		this.addressLn1 = addressLn1;
	}

	public String getAddressLn2() {
		return addressLn2;
	}

	public void setAddressLn2(String addressLn2) {
		this.addressLn2 = addressLn2;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
	
	
	
}
