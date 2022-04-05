package Tables;

/**
 @author Andrew Gilbey/C00263656
 * * Class that holds all relevant information for the Staff Object.
 * This is the Java version of the table held in the SQL database.
 * i.e. The variables in the class match the elements on the DB table
 *
 */
public class Staff {
   private int staff_id;
   private String firstName;
   private String lastName;

   public Staff() {
      super();
   }

   //Mutators and Accessors
   public int getStaff_id() {
      return staff_id;
   }

   public void setStaff_id(int staff_id) {
      this.staff_id = staff_id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   @Override
   public String toString() {
      return "Staff [staff_id=" + staff_id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
   }

}