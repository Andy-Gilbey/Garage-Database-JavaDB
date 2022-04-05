package Administrator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import Connection.DbConnection;
import ErrorHandling.Blanks;
import Tables.Staff;
import Tables.User;

/**
 * @author Andrew Gilbey/C00263656
 */
public class EditExistingUser extends JFrame {

   static DbConnection conn = new DbConnection();

   private static DefaultComboBoxModel staffList = new DefaultComboBoxModel();

   private static String initalUsername;

   //Variable Declarations
   private JLabel passwordLabel;
   private JButton clearBtn;
   private JButton closeBtn;
   private JButton resetBtn;
   private JLabel titleLabel;
   private static JPanel editUserPanel;
   private JSeparator jSeparator1;
   private static JComboBox < String > levelCombo;
   private JLabel levelLabel;
   private JButton saveBtn;
   private static JComboBox < String > staffCombo;
   private JLabel staffLabel;
   private static JTextField usernameField;
   private JLabel usernameLabel;

   /**
    * Constructor which calls the initialise method to open the gui
    * @param user - User is tested for its level, if 0 the user is an admin otherwise a standard user 
    * @throws HeadlessException - Thrown when code that is dependent on a keyboard, display, or mouse is called in an environment that does not support a keyboard, display, or mouse.
    */
   public EditExistingUser(User user) throws HeadlessException {
      initialise();
      initalUsername = user.getUsername();
      getusernameField().setText(user.getUsername());
      if (user.getLevel() == 0) {
         getLevelCombo().setSelectedItem("Administrator");
      } else {
         getLevelCombo().setSelectedItem("Standard User");
      }
      //"Update Authors SET LastName=? Where FirstName=?"

   }

   private void initialise() {
      //Font Setup
      Font inter18 = new Font("Inter", 0, 18);
      Font inter14 = new Font("Inter", 0, 14);
      Font inter24 = new Font("Inter", 0, 24);

      //Dimension for Size
      Dimension frame = new Dimension(550, 460);
      Dimension panel = new Dimension(549, 414);

      //Variable to be used in Update method

      editUserPanel = new JPanel();
      usernameLabel = new JLabel();
      usernameField = new JTextField();
      levelCombo = new JComboBox < > ();
      levelLabel = new JLabel();
      passwordLabel = new JLabel();
      resetBtn = new JButton();
      staffCombo = new JComboBox < String > (staffList);
      staffLabel = new JLabel();
      closeBtn = new JButton();
      saveBtn = new JButton();
      clearBtn = new JButton();
      titleLabel = new JLabel();
      jSeparator1 = new JSeparator();

      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); //Dispose on close to make sure the entire program does not close when gui is closed

      getContentPane().setLayout(null);
      setPreferredSize(frame); //Sets size of the frame to the dimensions set in the frame dimension object
      setResizable(false);

      editUserPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
      editUserPanel.setLayout(null);

      usernameLabel.setFont(inter18);
      usernameLabel.setText("Username:");
      editUserPanel.add(usernameLabel);
      usernameLabel.setBounds(30, 70, 140, 23);
      editUserPanel.add(usernameField);
      usernameField.setBounds(140, 70, 160, 21);

      levelCombo.setModel(new DefaultComboBoxModel < > (new String[] {
         "Standard User",
         "Administrator"
      }));
      editUserPanel.add(levelCombo);
      levelCombo.setBounds(140, 130, 160, 21);

      levelLabel.setFont(inter18);
      levelLabel.setText("Level:");
      editUserPanel.add(levelLabel);
      levelLabel.setBounds(70, 130, 140, 20);

      passwordLabel.setFont(inter18);
      passwordLabel.setText("Password:");
      editUserPanel.add(passwordLabel);
      passwordLabel.setBounds(30, 100, 140, 20);

      resetBtn.setText("Reset");
      resetBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               resetBtnactionPerformed(e);
            } catch (SQLException e1) {
               e1.printStackTrace();
            }
         }
      });
      editUserPanel.add(resetBtn);
      resetBtn.setBounds(140, 100, 160, 20);

      staffCombo.setModel(staffList);
      populateStaff();
      editUserPanel.add(staffCombo);
      staffCombo.setBounds(140, 160, 160, 21);

      staffLabel.setFont(inter18);
      staffLabel.setText("Staff Name:");
      editUserPanel.add(staffLabel);
      staffLabel.setBounds(20, 160, 140, 15);

      closeBtn.setText("Close");
      closeBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            closeBtnactionPerformed(e);
         }
      });
      editUserPanel.add(closeBtn);
      closeBtn.setBounds(230, 220, 77, 21);

      saveBtn.setText("Save");
      saveBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               saveBtnActionPerformed(e);
            } catch (SQLException e1) {
               e1.printStackTrace();
            }
         }
      });
      editUserPanel.add(saveBtn);
      saveBtn.setBounds(50, 220, 72, 21);

      clearBtn.setText("Clear");
      editUserPanel.add(clearBtn);
      clearBtn.setBounds(140, 220, 77, 21);

      getContentPane().add(editUserPanel);
      editUserPanel.setBounds(0, 40, 550, 414);

      titleLabel.setFont(inter24);
      titleLabel.setText("Edit User");
      getContentPane().add(titleLabel);
      titleLabel.setBounds(200, 0, 270, 30);
      getContentPane().add(jSeparator1);
      jSeparator1.setBounds(160, 30, 200, 10);

      pack();
      setLocationRelativeTo(null);
   }

   /**
    * ActionListener for the reset button. resets a users password to a default value and then calls the dispose method to exit the frame
    * @param ActionEvent e
    * @throws SQLException
    */
   protected void resetBtnactionPerformed(ActionEvent e) throws SQLException {
      UpdateUser update = new UpdateUser();
      update.resetPassword(initalUsername);
      JOptionPane.showMessageDialog(rootPane, "Password reset to the default password.", "Sucess", JOptionPane.INFORMATION_MESSAGE);
      this.dispose();
   }

   /**
    * ActionListener of the close button. Calls the dispose method from the JFrame class to dispose of the frame.
    * @param ActionEvent e
    */
   protected void closeBtnactionPerformed(ActionEvent e) {
      this.dispose();

   }

   //Action Listener
   /**
    * ActionListener for the save button. Calls the pullUser method and valiadtion method to create a user object built by 
    * input data on the gui and then passes it through the updateUser method in order to change data of the Db
    * @param ActionEvent e
    * @throws SQLException
    */
   protected void saveBtnActionPerformed(ActionEvent e) throws SQLException {
      User user = new User();
      user = pullUser();
      System.out.println(user.toString());
      UpdateUser update = new UpdateUser();
      int flag = update.validation(user);
      if (flag == 0) {
         update.userUpdate(user, initalUsername);
         JOptionPane.showMessageDialog(rootPane, "Success. User has been updated", "Sucess", JOptionPane.INFORMATION_MESSAGE);
         this.dispose();
      } else {
         JOptionPane.showMessageDialog(rootPane, "Validation Fail, Duplicate Username", "Error!", JOptionPane.WARNING_MESSAGE);
         this.dispose();
      }

   }

   //Mutators and Accessors

   public JComboBox < String > getLevelCombo() {
      return levelCombo;
   }

   public void setLevelCombo(JComboBox < String > levelCombo) {
      this.levelCombo = levelCombo;
   }

   public JComboBox < String > getStaffCombo() {
      return staffCombo;
   }

   public void setStaffCombo(JComboBox < String > staffCombo) {
      this.staffCombo = staffCombo;
   }

   public JTextField getusernameField() {
      return usernameField;
   }

   public void setusernameField(JTextField usernameField) {
      this.usernameField = usernameField;
   }

   //Populate Satff Method to ensure the combo box populates from the DB
   /**
    * Populates the staff combo box with entries that are taken from the DB using a prepared statement
    */
   public static void populateStaff() {
      staffList.removeAllElements();
      conn.setConn(); //Make a connection
      String sql = "Select Staff_Firstname,Staff_LastName from garage.Staff";

      try {
         // assume that all objects were all properly defined
         conn.setPstat(conn.getConn().prepareStatement(sql)); //set the prepared statement
         conn.setRs(conn.getPstat().executeQuery(sql)); //save the results into the result set
         while (conn.getRs().next()) { //loop through the result set
            //int id = rs.getInt("id");
            //list.addElement(id);
            String staffName = conn.getRs().getString("Staff_Firstname"); //Grab the first name from the table
            staffName = staffName + " " + conn.getRs().getString("Staff_Lastname"); //Grab the last name from the table
            staffList.addElement(staffName); //add the result set data into the combo box
         }
      } catch (Exception err) {
         System.out.println(err);
      }
   }

   /**
    * Returns a user based on information on the gui s
    * @return user - a user object built using data extracted from the gui  (user input)
    * @throws SQLException
    */
   public static User pullUser() throws SQLException {

      if (usernameField.getText().equals("")) {
         JOptionPane.showMessageDialog(editUserPanel, "Username is blank", "Error!", JOptionPane.WARNING_MESSAGE);
      } else {
         User user = new User();
         Staff member = new Staff();

         user.setUsername(usernameField.getText());
         //Level is an int on the GUI it is displayed in text, this converts combo selection to the required INT
         String level = levelCombo.getSelectedItem().toString();
         if (level.contentEquals("Standard User")) {
            user.setLevel(1);
         } else {
            user.setLevel(0);
         }

         String staff = staffCombo.getSelectedItem().toString();
         String[] staffName = staff.split(" ");
         member.setFirstName(staffName[0]);
         member.setLastName(staffName[1]); {
            try {
               String sql = "Select StaffID FROM garage.Staff where Staff_Firstname = ? and Staff_LastName = ?";
               conn.setPstat(conn.getConn().prepareStatement(sql));
               conn.getPstat().setString(1, staffName[0]);
               conn.getPstat().setString(2, staffName[1]);
               conn.setRs(conn.getPstat().executeQuery());

               if (conn.getRs().next()) {
                  member.setStaff_id(conn.getRs().getInt("StaffID"));

               } else {
                  member.setStaff_id(1);

               }
            } catch (SQLException e) {
               e.printStackTrace();
            }

            user.setStaff(member);

            return user;
         }

      }
      return null;

   } //END PULL USER METHOD

}