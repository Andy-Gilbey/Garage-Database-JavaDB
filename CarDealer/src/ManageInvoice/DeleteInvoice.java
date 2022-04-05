package ManageInvoice;

import java.sql.SQLException;

import Connection.DbConnection;
import Tables.Invoice;

/**
 * @author Andrew Gilbey/C00262656

 */
public class DeleteInvoice {

   static DbConnection conn = new DbConnection(); //New connection object

   /**
	    method that deletes an invoice from the database and changes the status of same to "Not Invoiced"
 * so the stock item can be processed again at a later date if necessary or delete was in error.
	 * @param invoice
	 * @throws SQLException
	 */
   public static void invoiceDelete(Invoice invoice) throws SQLException {
      conn.setConn();
      conn.setPstat(null);
      String sql = "DELETE FROM Invoice WHERE InvoiceNumber = ?";
      conn.setPstat(conn.getConn().prepareStatement(sql));
      conn.getPstat().setInt(1, invoice.getInvoiceNo());
      conn.getPstat().executeUpdate(); //Invoice Deleted

      //Update the Stock Table
      conn.setConn();
      conn.setPstat(null);

      /*sql = "UPDATE garage.Stock SET Status=\" Sold-Not Invoiced\", Invoice= null WHERE Invoice = ?";
      	  conn.setPstat(conn.getConn().prepareStatement(sql));
         conn.getPstat().setInt(1, invoice.getInvoiceNo());
         conn.getPstat().executeUpdate();//U*/
   }

}