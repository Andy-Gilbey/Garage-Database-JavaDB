package ManageCars;

import java.sql.SQLException;
import Connection.DbConnection;
import ErrorHandling.IntegrityConstraintValidation;
import Tables.Car;

/**
 * @author Andrew Gilbey/C00263656
 *
 */
public class UpdateCar {

   static DbConnection conn = new DbConnection();

   /**
    * Method which estblishes a connection with the db and submits an sql query in order to update
    * an entry of the db based on the car objects data
    * @param car - car object of which contains the data ready to be passed onto the db
    */
   public static void carUpdate(Car car) {
      conn.setConn();
      conn.setPstat(null);
      String sql = "Update garage.Car Set Reg= ?,Make= ?,Model=?,VIN=?,Transmission=?,Colour=? Where CarId = ? ";
      try {
         conn.setPstat(conn.getConn().prepareStatement(sql));
         conn.getPstat().setString(1, car.getReg());
         conn.getPstat().setString(2, car.getMake());
         conn.getPstat().setString(3, car.getModel());
         conn.getPstat().setString(4, car.getVin());
         conn.getPstat().setString(5, car.getTransmission());
         conn.getPstat().setString(6, car.getColour());
         conn.getPstat().setInt(7, car.getCarId());
         conn.getPstat().executeUpdate();

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   /**
    * Validation method validates the data on a car object to ensure it can be safely passed to the db without creating an error/exception. 
    * @param car - car object which is to be validated before data is passed through to the db
    * @return flag - flag that indicates an error not (1 = error integrity constraint validation is thrown 0 = valid)
    * @throws SQLException - An exception that provides information on a database access error or other errors.
    * @throws IntegrityConstraintValidation - An exception that ensures that the integrity of the database is valid (such as no duplicates)
    */
   public static int validation(Car car) throws SQLException, IntegrityConstraintValidation {
      conn.setConn();
      conn.setPstat(null);
      String red = car.getReg();
      String vin = car.getVin();
      int flag = 0;
      String sql = "Select Reg, Vin From Car where not CarId = ? and Reg= ? or Vin=?";
      conn.setPstat(conn.getConn().prepareStatement(sql));
      conn.getPstat().setInt(1, car.getCarId());
      conn.getPstat().setString(2, car.getReg());
      conn.getPstat().setString(3, car.getVin());
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