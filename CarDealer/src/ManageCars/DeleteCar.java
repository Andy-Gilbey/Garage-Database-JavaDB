package ManageCars;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import Connection.DbConnection;
import ErrorHandling.IntegrityConstraintValidation;
import Tables.Car;

/**
 * @author Andrew GIlbey/C00263656	
 *
 */
public class DeleteCar {

   static DbConnection conn = new DbConnection(); //DB COnnection

   /**
    * Car delete class, deletes an entry in the car table based on the information in the parameter car object.
    * @param car - car object built in another class. The data of the object is passed into the database
    * @throws SQLException
    * @throws IntegrityConstraintValidation
    */
   public static void carDelete(Car car) throws SQLException, IntegrityConstraintValidation {

      conn.setConn();
      conn.setPstat(null);
      String sql = "DELETE FROM garage.Car where carID = ?";
      conn.setPstat(conn.getConn().prepareStatement(sql));
      conn.getPstat().setInt(1, car.getCarId());
      conn.getPstat().executeUpdate();
      sql = "Select CarId from Car where carID = ?";
      conn.setPstat(conn.getConn().prepareStatement(sql));
      conn.getPstat().setInt(1, car.getCarId());
      conn.setRs(conn.getPstat().executeQuery());

   }

   /**
    * Checks if any data in a car object is active in the stock table of the db. 
    * @param car - a car object which valiadation needs to be checked against existing entries in the database
    * @return car - If the valiadation checks out the object will be bounced back otherwise it will return null
    * @throws SQLException
    * @throws IntegrityConstraintValidation
    */
   public static Car integrityValidation(Car car) throws SQLException, IntegrityConstraintValidation {

      try {
         conn.setConn();
         conn.setPstat(null);
         String sql = "Select Car from Stock inner Join  garage.Car where car = CarId and car = ?";
         conn.setPstat(conn.getConn().prepareStatement(sql));
         conn.getPstat().setInt(1, car.getCarId());
         conn.setRs(conn.getPstat().executeQuery());
         if (conn.getRs().next()) {
            JOptionPane.showMessageDialog(null, "Car found in stock table. Cannot delete while car is involed in a sale.", " Fatal Error", JOptionPane.ERROR_MESSAGE);
            throw new IntegrityConstraintValidation("Cannot delete or update a parent row: a foreign key constraint fails");
         } else {

            return car;
         }

      } catch (IntegrityConstraintValidation e) {
         return null;
      }

   }
}