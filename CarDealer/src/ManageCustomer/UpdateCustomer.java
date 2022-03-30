package ManageCustomer;

import java.sql.SQLException;

import Connection.DbConnection;
import Tables.Customer;

public class UpdateCustomer {
	
	 static DbConnection conn = new DbConnection();
	 
	  public static void customerUpdate(Customer customer) {
	      conn.setConn();
	      conn.setPstat(null);
	      
	      String sql = "UPDATE Customer SET FirstName= ? ,LastName= ?,PhoneNumber= ?,Email= ?,AddressLine1=?,AddressLine2=?,Town=?,County= ?," 
	      		+"Eircode=?,Budget=? WHERE customerID =?";
	      try {
	         conn.setPstat(conn.getConn().prepareStatement(sql));
	         conn.getPstat().setString(1, customer.getFname());
	         conn.getPstat().setString(2, customer.getLname());
	         conn.getPstat().setString(3, customer.getPhone());
	         conn.getPstat().setString(4, customer.getEmail());
	         conn.getPstat().setString(5, customer.getAddress().getAddressLn1());
	         conn.getPstat().setString(6, customer.getAddress().getAddressLn2());
	         conn.getPstat().setString(7, customer.getAddress().getTown());
	         conn.getPstat().setString(8, customer.getAddress().getCounty());
	         conn.getPstat().setString(9, customer.getAddress().getEircode());
	         conn.getPstat().setDouble(10, customer.getBudget());
	         conn.getPstat().setDouble(11, customer.getCustomerID());
	         conn.getPstat().executeUpdate();

	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	   }
}
