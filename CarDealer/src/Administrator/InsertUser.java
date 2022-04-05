package Administrator;

import Tables.User;
import java.sql.SQLException;
import Connection.DbConnection;

/**
 * @author Andrew Gilbey/C00263656
 *
 */
public class InsertUser {

   static DbConnection conn = new DbConnection();

   public static void userInsert(User user) {
      conn.setConn();
      conn.setPstat(null);
      String sql = "INSERT INTO garage.Users (Username,Password,Level,Staff_Member) VALUES (?,?,?,?)";
      try {
         conn.setPstat(conn.getConn().prepareStatement(sql));
         conn.getPstat().setString(1, user.getUsername());
         conn.getPstat().setString(2, user.getPassword());
         conn.getPstat().setInt(3, user.getLevel());
         conn.getPstat().setInt(4, user.getStaff().getStaff_id());
         conn.getPstat().executeUpdate();

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
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

      return flag;

   }

}