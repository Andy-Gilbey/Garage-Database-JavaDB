package Tables;

/**
 * @author Andrew Gilbey/C00263656
 * * Class that holds all relevant information for the User Object.
 * This is the Java version of the table held in the SQL database.
 * i.e. The variables in the class match the elements on the DB table
 *
 */
public class User {

   private String username;
   private String password;
   private int level;
   private Staff staff;

   public User() {
      super();
   }

   @Override
   public String toString() {
      return "User [username=" + username + ", password=" + password + ", level=" + level + ", staff=" + staff + "]";
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public int getLevel() {
      return level;
   }

   public void setLevel(int level) {
      this.level = level;
   }

   public Staff getStaff() {
      return staff;
   }

   public void setStaff(Staff staff) {
      this.staff = staff;
   }

}