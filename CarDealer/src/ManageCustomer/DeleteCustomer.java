package ManageCustomer;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import Connection.DbConnection;
import ErrorHandling.DataValidationFail;
import ErrorHandling.IntegrityConstraintValidation;
import Tables.Customer;
import Tables.Stock;

public class DeleteCustomer {
  static DbConnection conn = new DbConnection();
	  
	  public static void customerDelete(Customer customer) throws SQLException, IntegrityConstraintValidation {
		  
		  customer = integrityValidation(customer);
		  if(customer !=null) {
		
		  conn.setConn();
	      conn.setPstat(null);
	      String sql = "DELETE FROM garage.Customer where customerID = ?";
	      

		   	 conn.setPstat(conn.getConn().prepareStatement(sql));
	         conn.getPstat().setInt(1, customer.getCustomerID());
	         conn.getPstat().executeUpdate();
	         sql = "Select CustomerID from Customer where CustomerID = ?";
		   	 conn.setPstat(conn.getConn().prepareStatement(sql));
	         conn.getPstat().setInt(1, customer.getCustomerID());
	         conn.setRs(conn.getPstat().executeQuery());
	         
	         if(conn.getRs().next()) {
	        	 JOptionPane.showMessageDialog(null, "Delete Failed", "Error", JOptionPane.ERROR_MESSAGE);
	         }
	         else {
	        	 JOptionPane.showMessageDialog(null, "Customer of ID:  " + customer.getCustomerID()+ " Deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
	         }

	  }
		  else
		  {
			  JOptionPane.showMessageDialog(null, "Delete Failed", "Error", JOptionPane.ERROR_MESSAGE);
		  }
	  }
	   
	
	       
	      public static Customer integrityValidation(Customer customer) throws SQLException, IntegrityConstraintValidation{
	    	  
	    	  try {
	    		  conn.setConn();
			      conn.setPstat(null);
			      String sql = "Select customer from Stock Inner Join Customer where customer = ?";
			      conn.setPstat(conn.getConn().prepareStatement(sql));
			      conn.getPstat().setInt(1, customer.getCustomerID());
			      conn.setRs(conn.getPstat().executeQuery());
			      if(conn.getRs().next()) {
			        	 JOptionPane.showMessageDialog(null, "Customer found in stock table. Cannot delete while customer is involed in a sale.", " Fatal Error", JOptionPane.ERROR_MESSAGE);
			        	 throw new IntegrityConstraintValidation("Cannot delete or update a parent row: a foreign key constraint fails");
			         }
			         else {
			        	 
			        	return customer;
			         }
			      
	    	  }
	    	  catch(IntegrityConstraintValidation e){
	    		  return null;
	    	  }
	    	  
	      }
	        
	         
	         
	      
	      
	      
	      
	      
		
		
	}
		



