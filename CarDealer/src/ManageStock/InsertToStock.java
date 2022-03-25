package ManageStock;

import java.sql.SQLException;

import Connection.DbConnection;
import Tables.Stock;

public class InsertToStock {
	
	  static DbConnection conn = new DbConnection();
	   
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
