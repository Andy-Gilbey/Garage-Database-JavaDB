package ManageStock;

import java.sql.SQLException;
import Connection.DbConnection;
import Tables.Stock;

/**
 * @author Andrew Gilbey/C00263656
 *
 */
public class InsertToStock {

   static DbConnection conn = new DbConnection();

   /**
    * Uses a stock object with data extracted from user input on the ManageStockGUI to insert  record into the stock table of the db
    * @param stock - stock object with relevant data extracted from a gui to create a record in the db
    * @param carID - the id of the car object contained within the stock object 
    */
   public static void stockInsert(Stock stock, int carID) {
      conn.setConn();
      conn.setPstat(null);
      String sql = "INSERT INTO garage.Stock (Car,Price) VALUES (?,?)";
      try {
         conn.setPstat(conn.getConn().prepareStatement(sql));
         conn.getPstat().setInt(1, carID);
         conn.getPstat().setDouble(2, stock.getPrice());
         conn.getPstat().executeUpdate();

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}