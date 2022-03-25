package Tables;

public class Car {

	//Variavle declarations - Matches those found in the car table
	private int carId;
	private String make;
	private String model;
	private String reg;
	private String vin;
	private String transmission;
	private String colour;
	
	
	
	


	//Empty Constructor
	public Car() {
		super();
	}
	
	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
	

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getTransmission() {
		return transmission;
	}
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
	
	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	@Override
	public String toString() {
		return "Car [carId=" + carId + ", make=" + make + ", model=" + model + ", reg=" + reg + ", vin=" + vin
				+ ", transmission=" + transmission + ", colour=" + colour + "]";
	}





	
	
	
}
