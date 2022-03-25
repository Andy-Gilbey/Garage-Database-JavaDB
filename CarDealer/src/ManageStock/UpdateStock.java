package ManageStock;

import java.sql.SQLException;

import Connection.DbConnection;
import Tables.Stock;

public class UpdateStock {
	
	  static DbConnection conn = new DbConnection();
	  
	  public static void stockUpdate(Stock stock) {
	      conn.setConn();
	      conn.setPstat(null);
	      String sql = "UPDATE garage.Stock SET Price = ?, WhoSold= ?, Customer = ?, Status = ? WHERE StockNumber = ?";
	      try {
	         conn.setPstat(conn.getConn().prepareStatement(sql));
	         conn.getPstat().setDouble(1, stock.getPrice());
	         conn.getPstat().setInt(2, stock.getStaff().getStaff_id());
	         conn.getPstat().setInt(3, stock.getCustomer().getCustomerID());
	         conn.getPstat().setString(4, stock.getStatus());
	         conn.getPstat().setInt(5, stock.getStockNo());
	         conn.getPstat().executeUpdate();

	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	   }
}
