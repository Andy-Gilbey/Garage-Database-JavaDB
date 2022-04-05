package Administrator;

import java.sql.SQLException;

import Connection.DbConnection;
import Tables.User;

/**
 * @author Andrew Gilbey/C00263656
 *
 */
public class UpdateUser {

   static DbConnection conn = new DbConnection();

   /**
    * @param user - user object which have information ready to passed on to the db
    * @param initalUsername - if the username was edited this will be the inital username. This parameter is passed to the validation method below.
    */
   public static void userUpdate(User user, String initalUsername) {
      conn.setConn();
      conn.setPstat(null);
      String sql = "UPDATE Users SET Username = ? , Level= ? , Staff_Member = ? WHERE Username = ?;";
      try {
         conn.setPstat(conn.getConn().prepareStatement(sql));
         conn.getPstat().setString(1, user.getUsername());
         conn.getPstat().setInt(2, user.getLevel());
         conn.getPstat().setInt(3, user.getStaff().getStaff_id());
         conn.getPstat().setString(4, initalUsername);
         conn.getPstat().executeUpdate();

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   /**
    * Method which valiadates a user object to make sure it can be safely passed to the db without error (duplicates)
    * @param user - user object which is checked for validation
    * @return flag - 1 indicates valid 0 indicates an error
    * @throws SQLException
    */
   public static int validation(User user) throws SQLException {
      conn.setConn();
      conn.setPstat(null);
      int flag = 0;
      String sql = "Select username From Users where Username= ?";
      conn.setPstat(conn.getConn().prepareStatement(sql));
      conn.getPstat().setString(1, user.getUsername());
      conn.setRs(conn.getPstat().executeQuery());
      if (conn.getRs().next()) {
         String Username = conn.getRs().getString("Username");
         if (Username.equals(user.getUsername())) {
            flag = 1;
         }
      }

      return flag; //Error
   }

   /**
    * Method which resets a users password to the default value (password).
    * @param initalUsername
    * @throws SQLException
    */
   public static void resetPassword(String initalUsername) throws SQLException {
      conn.setConn();
      conn.setPstat(null);
      String sql = "UPDATE Users SET Password = ? WHERE Username = ?;";
      conn.setPstat(conn.getConn().prepareStatement(sql));
      conn.getPstat().setString(1, "password");
      conn.getPstat().setString(2, initalUsername);
      conn.getPstat().executeUpdate();
   }

}