package ManageCustomer;

import java.sql.SQLException;

import Connection.DbConnection;
import Tables.Customer;
import Tables.User;

public class InsertCustomer {

	  static DbConnection conn = new DbConnection();

	   public static void customerInsert(Customer customer) {
	      conn.setConn();
	      conn.setPstat(null);
	      String sql = "INSERT INTO garage.Customer (FirstName,LastName,PhoneNumber,Email,AddressLine1,AddressLine2,Town,County,Eircode,Budget) VALUES (?,?,?,?,?,?,?,?,?,?)";
	      
	      	//Set entries, Theres a lot of them, more precise to setup variables first and then add them into prepared statements
	    	 String fname = customer.getFname();
	    	 String lname = customer.getLname();
	    	 String phone = customer.getPhone();
	    	 String email = customer.getEmail();
	    	 String add1 = customer.getAddress().getAddressLn1();
	    	 String add2 = customer.getAddress().getAddressLn2();
	    	 String town = customer.getAddress().getTown();
	    	 String county = customer.getAddress().getCounty();
	    	 String eircode = customer.getAddress().getEircode();
	    	 double budget = customer.getBudget();
	    	 
		      try {
	         conn.setPstat(conn.getConn().prepareStatement(sql));
	         conn.getPstat().setString(1, fname);
	         conn.getPstat().setString(2, lname);
	         conn.getPstat().setString(3, phone);
	         conn.getPstat().setString(4, email);
	         conn.getPstat().setString(5, add1);
	         conn.getPstat().setString(6, add2);
	         conn.getPstat().setString(7, town);
	         conn.getPstat().setString(8, county);
	         conn.getPstat().setString(9, eircode);
	         conn.getPstat().setDouble(10, budget);
	         conn.getPstat().executeUpdate();

	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	   }
}
