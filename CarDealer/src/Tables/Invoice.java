package Tables;
/**
 * @author Andrew Gilbey/C00263656
 * Class that holds all relevant information for the Invoice Object.
 * This is the Java version of the table held in the SQL database.
 * i.e. The variables in the class match the elements on the DB table
 *
 */
public class Invoice {

   private int invoiceNo;
   private Customer customer;
   private Car car;
   private Stock stock;
   private int invoiceStatus;
   private String paymentMethod;
   private double price;
   private double VAT;
   private double totalCashPrice;
   private String issueDate;

   public String getIssueDate() {
      return issueDate;
   }

   public void setIssueDate(String issueDate) {
      this.issueDate = issueDate;
   }

   public Invoice() {
      super();
   }

   public int getInvoiceNo() {
      return invoiceNo;
   }

   public void setInvoiceNo(int invoiceNo) {
      this.invoiceNo = invoiceNo;
   }

   public Customer getCustomer() {
      return customer;
   }

   public void setCustomer(Customer customer) {
      this.customer = customer;
   }

   public Car getCar() {
      return car;
   }

   public void setCar(Car car) {
      this.car = car;
   }

   public Stock getStock() {
      return stock;
   }

   public void setStock(Stock stock) {
      this.stock = stock;
   }

   public String getPaymentMethod() {
      return paymentMethod;
   }

   public void setPaymentMethod(String paymentMethod) {
      this.paymentMethod = paymentMethod;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   public int getInvoiceStatus() {
      return invoiceStatus;
   }

   public void setInvoiceStatus(int invoiceStatus) {
      this.invoiceStatus = invoiceStatus;
   }

   public double getVAT() {
      return VAT;
   }

   public void setVAT(double vAT) {
      VAT = vAT;
   }

   public double getTotalCashPrice() {
      return totalCashPrice;
   }

   public void setTotalCashPrice(double totalCashPrice) {
      this.totalCashPrice = totalCashPrice;
   }

   @Override
   public String toString() {
      return "Invoice [invoiceNo=" + invoiceNo + ", customer=" + customer + ", car=" + car + ", stock=" + stock +
         ", invoiceStatus=" + invoiceStatus + ", paymentMethod=" + paymentMethod + ", price=" + price +
         ", VAT=" + VAT + ", totalCashPrice=" + totalCashPrice + ", issueDate=" + issueDate + "]";
   }

}