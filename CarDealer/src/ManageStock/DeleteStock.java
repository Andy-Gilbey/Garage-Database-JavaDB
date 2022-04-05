package ManageStock;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import Connection.DbConnection;
import Tables.Stock;

/**
 * @author Andrew Gilbey/C00263656
 */

public class DeleteStock {

   static DbConnection conn = new DbConnection();

   /**
    * Method which communicates with the SQL database to execute a query and delete the stock.
    * @param stock- A stock object built in another class and passed through. 
    * 
    */
   public static void stockDelte(Stock stock) {
      conn.setConn();
      conn.setPstat(null);

      String sql = "DELETE FROM `garage`.`Stock` WHERE StockNumber = ? ";
      try {
         conn.setPstat(conn.getConn().prepareStatement(sql));
         conn.getPstat().setInt(1, stock.getStockNo());
         conn.getPstat().executeUpdate();
         sql = "Select StockNumber from Stock where StockNumber = ?";
         conn.setPstat(conn.getConn().prepareStatement(sql));
         conn.getPstat().setInt(1, stock.getStockNo());
         conn.setRs(conn.getPstat().executeQuery());
         if (conn.getRs().next()) {
            JOptionPane.showMessageDialog(null, "Delete Failed", "Error", JOptionPane.ERROR_MESSAGE);
         } else {
            JOptionPane.showMessageDialog(null, "Stock " + stock.getStockNo() + " Deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}