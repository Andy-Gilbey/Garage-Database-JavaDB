package ManageInvoice;

import java.sql.SQLException;

import Connection.DbConnection;
import Tables.Invoice;

/**
 * @author Andrew Gilbey/C00263656

 *
 */

public class ModStatus {

   static DbConnection conn = new DbConnection(); //Database Connection

   /**
    * holds the method (statusMod) in order to change the status of an invoice to paid or unpaid (1 or 0) 
    * @param invoice
    * @throws SQLException
    */
   public static void statusMod(Invoice invoice) throws SQLException {
      conn.setConn();
      conn.setPstat(null);
      int invoiceID = invoice.getInvoiceNo();
      int invoiceStatus = invoice.getInvoiceStatus();

      if (invoiceStatus != 1) {
         invoiceStatus = 1;
      } else {
         invoiceStatus = 0;
      }

      String sql = "UPDATE garage.Invoice SET Invoice_Status = ? WHERE InvoiceNumber = ? ";
      conn.setPstat(conn.getConn().prepareStatement(sql));
      conn.getPstat().setInt(1, invoiceStatus);
      conn.getPstat().setInt(2, invoiceID);
      conn.getPstat().executeUpdate();

   }
}