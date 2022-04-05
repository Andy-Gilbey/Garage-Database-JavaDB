package ManageInvoice;

import java.sql.SQLException;
import Connection.DbConnection;
import Tables.Car;
import Tables.Invoice;

/**
 * @author Andrew Gilbey/C00263656
 */
public class GenerateInvoice {

   static DbConnection conn = new DbConnection();
   final static int VAT_RATE = 23; //Constant static variable of the VatRate used within the calcuvat method 

   /**
    * Method which takes in an Invoice object, assembles an SQL query and inserts the invoice object data into the Database.
    * @param invoice - an invoice object that has been created in another class (from information entered on a GUI)
    * @throws SQLException - An exception that provides information on a database access error or other errors.
    */
   public static void genInvoice(Invoice invoice) {

      invoice.setVAT(calcVat(invoice.getPrice()));
      invoice.setTotalCashPrice(invoice.getPrice() + invoice.getVAT());

      conn.setConn();
      conn.setPstat(null);
      String sql = "INSERT INTO garage.Invoice (Customer,Car,Invoice_Status,Price,Payment_Method,StockID,VAT,Total,IssueDate)" +
         " VALUES (?,?,?,?,?,?,?,?,?)";

      try {
         conn.setPstat(conn.getConn().prepareStatement(sql));
         conn.getPstat().setInt(1, invoice.getCustomer().getCustomerID());
         conn.getPstat().setInt(2, invoice.getCar().getCarId());
         conn.getPstat().setInt(3, invoice.getInvoiceStatus());
         conn.getPstat().setDouble(4, invoice.getPrice());
         conn.getPstat().setString(5, invoice.getPaymentMethod());
         conn.getPstat().setInt(6, invoice.getStock().getStockNo());
         conn.getPstat().setDouble(7, invoice.getVAT());
         conn.getPstat().setDouble(8, invoice.getTotalCashPrice());
         conn.getPstat().setString(9, invoice.getIssueDate());
         conn.getPstat().executeUpdate();

         //Update specfic car status to Sold.
         conn.setConn();
         conn.setPstat(null);
         sql = "UPDATE garage.Stock SET Status= 'Sold' WHERE StockNumber = ?";
         conn.setPstat(conn.getConn().prepareStatement(sql));
         conn.getPstat().setInt(1, invoice.getStock().getStockNo());
         conn.getPstat().executeUpdate();

         //Get Invoice Number of new Entry
         conn.setConn();
         conn.setPstat(null);
         sql = "Select InvoiceNumber from Invoice where StockID = ?";
         conn.setPstat(conn.getConn().prepareStatement(sql));
         conn.getPstat().setInt(1, invoice.getStock().getStockNo());
         conn.setRs(conn.getPstat().executeQuery());
         if (conn.getRs().next()) {
            invoice.setInvoiceNo(conn.getRs().getInt("InvoiceNumber"));
         } else {
            System.out.println("Error");
         }

         //Change Invoice Entry in Stock Table
         conn.setConn();
         conn.setPstat(null);
         sql = "UPDATE garage.Stock SET Invoice = ? WHERE StockNumber = ?";
         conn.setPstat(conn.getConn().prepareStatement(sql));
         conn.getPstat().setInt(1, invoice.getInvoiceNo());
         conn.getPstat().setInt(2, invoice.getStock().getStockNo());
         conn.getPstat().executeUpdate();

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   /**
    * Method to calculate the VAT on the sale. VAT_RATE is a constant of the class used within the method. 
    * The invoice objects Price is taken multiplied by the VAT_RATE and divided by 100. Returns the calculated VAT.
    * @param price - the price value of an invoice object
    * @return vat - the result variable after the arithmetic has been carried out on the price variable
    * 
    */
   public static double calcVat(double price) {
      double vat = (price * VAT_RATE) / 100;

      return vat;
   }

}