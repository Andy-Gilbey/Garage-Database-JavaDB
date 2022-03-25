package NewCar;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import Connection.DbConnection;
import Tables.Car;
import Tables.User;

public class InsertCar {

	   static DbConnection conn = new DbConnection();
	   
	   public static void carInsert(Car car) {
		      conn.setConn();
		      conn.setPstat(null);
		      String sql = "INSERT INTO garage.Car (Make,Model,Reg,VIN,Transmission,Colour) VALUES (?,?,?,?,?,?)";
		      try {
		         conn.setPstat(conn.getConn().prepareStatement(sql));
		         conn.getPstat().setString(1, car.getMake());
		         conn.getPstat().setString(2, car.getModel());
		         conn.getPstat().setString(3, car.getReg());
		         conn.getPstat().setString(4, car.getVin());
		         conn.getPstat().setString(5, car.getTransmission());
		         conn.getPstat().setString(6, car.getColour());
		         conn.getPstat().executeUpdate();

		      } catch (SQLException e) {
		         e.printStackTrace();
		      }
		   }
	   
	   public static int validation(Car car) throws SQLException {
		      conn.setConn();
		      conn.setPstat(null);
		      int flag = 0;
		      String sql = "Select Reg, Vin From Car where Reg= ?";
		      conn.setPstat(conn.getConn().prepareStatement(sql));
		      conn.getPstat().setString(1, car.getReg());
		      conn.getPstat().setString(2, car.getVin());
		      conn.setRs(conn.getPstat().executeQuery());
		      if (conn.getRs().next()) {
		         String reg = conn.getRs().getString("Reg");
		         String vin = conn.getRs().getString("VIN");
		         if (reg.equals(car.getReg().equals(car.getReg()) || vin.equals(car.getVin()))){
		            flag = 1;
		         }
		      }

		      return flag;

		   }
}
