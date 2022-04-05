package NewCar;

import java.sql.SQLException;

import Connection.DbConnection;
import ErrorHandling.IntegrityConstraintValidation;
import Tables.Car;

/**
 * @author Andrew Gilbey/C00263656
 *
 */
public class InsertCar {

   static DbConnection conn = new DbConnection();

   /**
    * Method that extracts data from the car object and uses an sql query (in prepared statement) to insert a record into the database
    * @param car - the car object which data will be used to create a record in the car table
    */
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

   /**
    * Method which checks a car object to ensure it can be sent to the database without error.
    * @param car - The car object which is being checked for validation
    * @return flag - 1 = error 0 - valid
    * @throws SQLException
    * @throws IntegrityConstraintValidation
    */
   public static int validation(Car car) throws SQLException, IntegrityConstraintValidation {
      conn.setConn();
      conn.setPstat(null);
      int flag = 0;
      String sql = "Select Reg, Vin From Car where Reg= ? or Vin=?";
      conn.setPstat(conn.getConn().prepareStatement(sql));
      conn.getPstat().setString(1, car.getReg());
      conn.getPstat().setString(2, car.getVin());
      conn.setRs(conn.getPstat().executeQuery());
      try {
         if (conn.getRs().next()) {
            throw new IntegrityConstraintValidation("Integrity of Database compromised");
         }
      } catch (IntegrityConstraintValidation e) {
         flag = 1;
         return flag;
      }

      return flag;

   }
}