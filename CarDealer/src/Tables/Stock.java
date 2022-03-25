package Tables;

public class Stock {
	private int stockNo;


	private Car car;
	private double price;
	private String status;
	private Customer customer;
	private Staff staff;
	

	public Stock() {
		super();
	}
	
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public int getStockNo() {
		return stockNo;
	}

	public void setStockNo(int stockNo) {
		this.stockNo = stockNo;
	}

	@Override
	public String toString() {
		return "Stock [stockNo=" + stockNo + ", car=" + car + ", price=" + price + ", status=" + status + ", customer="
				+ customer + ", staff=" + staff + "]";
	}


	
}
