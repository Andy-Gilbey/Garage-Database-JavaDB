package ManageStock;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import Connection.DbConnection;
import Tables.Stock;

public class DeleteStock {

	  static DbConnection conn = new DbConnection();
	  
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
	         if(conn.getRs().next()) {
	        	 JOptionPane.showMessageDialog(null, "Delete Failed", "Error", JOptionPane.ERROR_MESSAGE);
	         }
	         else {
	        	 JOptionPane.showMessageDialog(null, "Stock " + stock.getStockNo() + " Deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
	         }
	         
	  

	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	   }
}
