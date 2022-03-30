package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnection{

	private final String DATABASE_URL;
	private String username = "";
	private String password = "";
	private Connection conn;
	private static PreparedStatement pstat;
	private ResultSet rs;


	public DbConnection() {
		
		DATABASE_URL = "jdbc:mysql://localhost:49155/garage";
		username = "root";
		password = "password";
		conn = null;
		pstat = null;
		setRs(null);
	}
	
	
	public String getDATABASE_URL() {
		return DATABASE_URL;
	}


	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}
	
	public ResultSet getRs() {
		return rs;
	}


	public void setRs(ResultSet rs) {
		this.rs = rs;
	}


	public Connection getConn() {
		return conn;
	}
	
	public void setPstat(PreparedStatement pstat) {
		this.pstat = pstat;
	}


	


	public void setConn() {
		try {
			 
		     conn = DriverManager.getConnection(this.getDATABASE_URL(), this.getUsername(), this.getPassword());
		 
		    if (conn != null) {
		        //System.out.println("Connected");
		    }
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
	}
	

	
	public PreparedStatement getPstat() {
		return pstat;
	}
	
	
	public void closeAll() throws SQLException {
		conn.close();
		pstat.close();
		rs.close();
	}




	

}
