package Tables;

/**
 * @author Andrew Gilbey/C00263656
 * Class that holds all relevant information for the Stock Object.
 * This is the Java version of the table held in the SQL database.
 * i.e. The variables in the class match the elements on the DB table
 *
 */
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
      return "Stock [stockNo=" + stockNo + ", car=" + car + ", price=" + price + ", status=" + status + ", customer=" +
         customer + ", staff=" + staff + "]";
   }

}