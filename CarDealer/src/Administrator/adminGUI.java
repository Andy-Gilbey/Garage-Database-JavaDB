package Administrator;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import Connection.DbConnection;
import ErrorHandling.Blanks;
import Tables.Staff;
import Tables.User;

/**
 * @author Andrew Gilbey/C0263656
 *
 */
public class adminGUI extends JFrame {

   private JMenuItem addNewUser;

   //New Connection & Insert User Objects
   static DbConnection conn = new DbConnection();
   //New list model in order to populate the combo box directly from the DB
   private static DefaultComboBoxModel staffList = new DefaultComboBoxModel();

   //Flag to check whether a panel is open or noAt
   private int aupPanelOpen = 0;
   private int euuPanelOpen = 0;

   //Error Handling Username
   private static int errorUser = 0;

   ///////// Frame Components
   private JMenuItem editExistingUser;
   private JMenu fileMenu;
   private JMenu helpMenu;
   private JLabel loggedInLabel;
   private JMenuBar menuBar;
   private JMenu quitMenu;
   private JSeparator seperator;
   private JLabel titleLabel;
   private JLabel usernameTag;
   private JLabel warningMessage;

   //////////// Add Panel Components
   private JPanel addUserPanel;
   private JButton aupBackBtn;
   private JButton aupClearBtn;
   private static JComboBox < String > aupLevelCombo;
   private JLabel aupLevelLabel;
   private static JPasswordField aupPasswordField;
   private JLabel aupPasswordLabel;
   private JButton aupSaveBtn;
   private static JComboBox < String > aupStaffCombo;
   private JLabel aupStaffLabel;
   private static JTextField aupUsernameField;
   private JLabel aupUsernameLabel;

   //////////// Edit Existing Users Panel Components
   private JPanel editExistingUserPanel;
   private JScrollPane eeuScrollPane;
   private JTable eeuTable;
   private JLabel eeuTitleLabel;
   private JButton euuBackBtn;
   private JButton euuEditUserBtn;

   /**
    * Constructor of the adminGui, calls the initalise method which builds the gui 
    * @param username - The username of the user that logs in, is reflected on the GUI changing a label on the GUI to reflect who is logged in.
    */
   public adminGUI(String username) {
      initialise();
      usernameTag.setText(username);

   }

   /**
    * initialise method initialises and sets the postion of all elements of the GUI
    */
   @SuppressWarnings("unchecked")
   public void initialise() {

      aupPanelOpen = 0;
      euuPanelOpen = 0;
      usernameTag = new JLabel();
      loggedInLabel = new JLabel();
      titleLabel = new JLabel();
      seperator = new JSeparator();
      warningMessage = new JLabel();
      addUserPanel = new JPanel();
      menuBar = new JMenuBar();
      fileMenu = new JMenu();
      addNewUser = new JMenuItem();
      editExistingUser = new JMenuItem();
      helpMenu = new JMenu();
      quitMenu = new JMenu();

      //Add user panel components init
      aupStaffLabel = new JLabel();
      aupUsernameLabel = new JLabel();
      aupPasswordLabel = new JLabel();
      aupLevelLabel = new JLabel();
      aupPasswordField = new JPasswordField();
      aupUsernameField = new JTextField();
      aupLevelCombo = new JComboBox < > ();
      aupStaffCombo = new JComboBox < String > (staffList); //Sets the model to the 'staff list' (a list array)
      aupSaveBtn = new JButton();
      aupClearBtn = new JButton();
      aupBackBtn = new JButton();

      //Edit Existing Users panel components init
      editExistingUserPanel = new JPanel();
      eeuScrollPane = new JScrollPane();
      eeuTable = new JTable();
      eeuTitleLabel = new JLabel();
      euuBackBtn = new JButton();
      euuEditUserBtn = new JButton();

      //Fonts
      Font inter18 = new Font("Inter", 0, 18);
      Font inter14 = new Font("Inter", 0, 14);
      Font inter24bold = new Font("Inter", 1, 24);

      //Dimension for Size
      Dimension frame = new Dimension(775, 645);
      Dimension panel = new Dimension(605, 300);

      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      setTitle("Larceny Motors [Admin Panel]");
      getContentPane().setLayout(null);
      getContentPane().setVisible(true);
      setPreferredSize(frame); //Sets size of the frame to the dimensions set in the frame dimension object
      setResizable(false); //Disable resize window

      usernameTag.setText("Username");
      getContentPane().add(usernameTag);
      usernameTag.setBounds(90, 20, 80, 20);

      loggedInLabel.setText("Logged in as:");
      getContentPane().add(loggedInLabel);
      loggedInLabel.setBounds(10, 20, 80, 20);

      titleLabel.setFont(inter24bold);
      titleLabel.setText("Administrator Panel");
      getContentPane().add(titleLabel);
      titleLabel.setBounds(180, 30, 280, 20);
      getContentPane().add(seperator);
      seperator.setBounds(0, 60, 790, 10);

      warningMessage.setText("Unauthorized access or use is a violation of law and may lead to prosecution. ");
      getContentPane().add(warningMessage);
      warningMessage.setBounds(10, 0, 510, 15);

      addUserPanel.setBorder(BorderFactory.createTitledBorder("Add User"));
      addUserPanel.setLayout(null);

      getContentPane().add(addUserPanel);
      addUserPanel.setBounds(10, 70, 760, 540);

      fileMenu.setText("File");

      addNewUser.setText("Add New User");
      addNewUser.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            addNewUserActionPerformed(e);
         }
      });
      fileMenu.add(addNewUser);

      editExistingUser.setText("Edit Existing User");
      editExistingUser.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            editExistingUserActionPerformed(e);
         }
      });
      fileMenu.add(editExistingUser);

      menuBar.add(fileMenu);

      helpMenu.setText("Help");
      menuBar.add(helpMenu);

      quitMenu.setText("Quit");
      quitMenu.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            quitMenuActionPerformed(e);
         }
      });
      menuBar.add(quitMenu);

      setJMenuBar(menuBar);

      //////////// Add Panel Configuration and Component INIT
      addUserPanel.setVisible(false);
      addUserPanel.setSize(panel);
      aupStaffLabel.setFont(inter18);
      aupStaffLabel.setText("Staff Name:");
      addUserPanel.add(aupStaffLabel);
      aupStaffLabel.setBounds(10, 140, 140, 15);

      aupUsernameLabel.setFont(inter18);
      aupUsernameLabel.setText("Username:");
      addUserPanel.add(aupUsernameLabel);
      aupUsernameLabel.setBounds(10, 50, 140, 23);

      aupPasswordLabel.setFont(inter18);
      aupPasswordLabel.setText("Password:");
      addUserPanel.add(aupPasswordLabel);
      aupPasswordLabel.setBounds(10, 80, 140, 20);

      aupLevelLabel.setFont(inter18);
      aupLevelLabel.setText("Level:");
      addUserPanel.add(aupLevelLabel);
      aupLevelLabel.setBounds(50, 110, 140, 20);
      addUserPanel.add(aupPasswordField);
      aupPasswordField.setBounds(120, 80, 160, 21);
      addUserPanel.add(aupUsernameField);
      aupUsernameField.setBounds(120, 50, 160, 21);

      aupLevelCombo.setModel(new DefaultComboBoxModel < > (new String[] {
         "Standard User",
         "Administrator"
      }));
      addUserPanel.add(aupLevelCombo);
      aupLevelCombo.setBounds(120, 110, 160, 21);

      aupStaffCombo.setModel(staffList);
      populateStaff();
      addUserPanel.add(aupStaffCombo);
      aupStaffCombo.setBounds(120, 140, 160, 21);

      aupSaveBtn.setText("Save");
      aupSaveBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               aupSaveBtnActionPerformed(e);
            } catch (Blanks e1) {
               e1.printStackTrace();
            } catch (SQLException e1) {
               e1.printStackTrace();
            }
         }
      });
      addUserPanel.add(aupSaveBtn);
      aupSaveBtn.setBounds(11, 203, 72, 21);

      aupClearBtn.setText("Clear");
      aupClearBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            aupClearBtnActionPerformed(e);
         }
      });
      addUserPanel.add(aupClearBtn);
      aupClearBtn.setBounds(89, 203, 72, 21);

      aupBackBtn.setText("Back");
      aupBackBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            aupBackBtnActionPerformed(e);
         }
      });
      addUserPanel.add(aupBackBtn);
      aupBackBtn.setBounds(167, 203, 72, 21);

      //////////// Edit User Panel Configuration and Component INIT
      editExistingUserPanel.setBorder(BorderFactory.createTitledBorder("Edit Exisiting User"));
      editExistingUserPanel.setLayout(null);
      editExistingUserPanel.setVisible(false);

      eeuTable.setModel(new DefaultTableModel(
         new Object[][] {
            {
               null,
               null,
               null
            }, {
               null,
               null,
               null
            }, {
               null,
               null,
               null
            }, {
               null,
               null,
               null
            }
         },

         new String[] {
            "Username",
            "Level",
            "Employee"
         }
      ) {
         boolean[] canEdit = new boolean[] {
            false,
            false,
            false
         };

         public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
         }
      });
      eeuScrollPane.setViewportView(eeuTable);
      if (eeuTable.getColumnModel().getColumnCount() > 0) {
         eeuTable.getColumnModel().getColumn(0).setResizable(false);
         eeuTable.getColumnModel().getColumn(1).setResizable(false);
         eeuTable.getColumnModel().getColumn(2).setResizable(false);
      }
      try {
         updateTable(eeuTable);
      } catch (SQLException e) {
         e.printStackTrace();
      }
      eeuScrollPane.setViewportView(eeuTable);

      editExistingUserPanel.add(eeuScrollPane);
      eeuScrollPane.setBounds(180, 50, 560, 480);

      eeuTitleLabel.setFont(inter14); // NOI18N
      eeuTitleLabel.setText("User List");
      editExistingUserPanel.add(eeuTitleLabel);
      eeuTitleLabel.setBounds(430, 20, 80, 18);

      euuBackBtn.setText("Back");
      editExistingUserPanel.add(euuBackBtn);
      euuBackBtn.setBounds(20, 160, 130, 40);

      euuEditUserBtn.setText("Reset Password");
      editExistingUserPanel.add(euuEditUserBtn);
      euuEditUserBtn.setBounds(20, 110, 130, 40);

      getContentPane().add(editExistingUserPanel);
      editExistingUserPanel.setBounds(10, 70, 760, 540);
      eeuScrollPane.setViewportView(eeuTable);
      eeuScrollPane.setViewportView(eeuTable);
      eeuScrollPane.setBounds(180, 50, 560, 480);
      eeuTitleLabel.setFont(inter14);
      eeuTitleLabel.setText("User List");
      eeuTitleLabel.setBounds(430, 20, 80, 18);
      euuBackBtn.setText("Back");
      euuBackBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            euuBackBtnActionPerformed(e);
         }
      });
      euuBackBtn.setBounds(20, 160, 130, 40);
      euuEditUserBtn.setText("Edit User");
      euuEditUserBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            euuEditUserBtnActionPerformed(e);
         }
      });
      euuEditUserBtn.setBounds(20, 110, 130, 40);

      //Configure all components to default set sizes
      pack();
      setLocationRelativeTo(null);
   }

   //Getters and Setters

   //Add user panel Getters and Setters

   public JComboBox < String > getAupLevelCombo() {
      return aupLevelCombo;
   }

   public void setAupLevelCombo(JComboBox < String > aupLevelCombo) {
      this.aupLevelCombo = aupLevelCombo;
   }

   public JPasswordField getAupPasswordField() {
      return aupPasswordField;
   }

   public void setAupPasswordField(JPasswordField aupPasswordField) {
      this.aupPasswordField = aupPasswordField;
   }

   public JComboBox < String > getAupStaffCombo() {
      return aupStaffCombo;
   }

   public void setAupStaffCombo(JComboBox < String > aupStaffCombo) {
      this.aupStaffCombo = aupStaffCombo;
   }

   public JTextField getAupUsernameField() {
      return aupUsernameField;
   }

   public void setAupUsernameField(JTextField aupUsernameField) {
      this.aupUsernameField = aupUsernameField;
   }

   //Edit Existing User Panel Getters & Setters
   public JTable getEeuTable() {
      return eeuTable;
   }

   //Action Listeners

   //Frame Listeners

   //Quit button
   protected void quitMenuActionPerformed(ActionEvent e) {
      System.exit(0);
   }

   //Edit Existing Users <<Opens up the Panel to search users >>
   protected void editExistingUserActionPerformed(ActionEvent e) {

      if (euuPanelOpen == 0 && aupPanelOpen != 1) {
         editExistingUserPanel.setVisible(true);
         euuPanelOpen = 1;
      } else {
         editExistingUserPanel.setVisible(false);
         euuPanelOpen = 0;
      }

   }

   //Add New User <<Opens up the Panel to Add new user >>
   protected void addNewUserActionPerformed(ActionEvent e) {

      if (aupPanelOpen == 0 && euuPanelOpen != 1) {
         addUserPanel.setVisible(true);
         aupPanelOpen = 1;
      } else {
         addUserPanel.setVisible(false);
         aupPanelOpen = 0;
      }
   }

   protected void euuBackBtnActionPerformed(ActionEvent e) {
      if (euuPanelOpen == 0 && aupPanelOpen != 1) {
         editExistingUserPanel.setVisible(true);
         euuPanelOpen = 1;
      } else {
         editExistingUserPanel.setVisible(false);
         euuPanelOpen = 0;
      }
   }

   protected void euuEditUserBtnActionPerformed(ActionEvent e) {
      EditExistingUser popout = new EditExistingUser(exportUser());
      popout.setVisible(true);
   }

   ///////// Frame Methods

   //Populate Staff ComboBox with Staff names
   /**
    * Populates the staff combo box with the first name and last name of the staff members from the Database
    */
   @SuppressWarnings("unchecked")
   public static void populateStaff() {
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

   ///////// Add User PanelMethods 

   //Extract the form data from the Add User Panel and create a new User object to be inserted into the database
   /**
    * @return User - the user object that was built/pulled from entered information on the GUI
    * @throws Blanks - When required fields are blank this error message is thrown
    * @throws SQLException
    */
   @SuppressWarnings("deprecation")
   public static User pullUser() throws Blanks, SQLException {
      try {

         if (aupUsernameField.getText().equals("") || aupPasswordField.getText().equals("")) {
            throw new Blanks("username AND/OR Password is blank");
         } else {
            User user = new User();
            Staff member = new Staff();

            user.setUsername(aupUsernameField.getText());
            user.setPassword(aupPasswordField.getText());
            //Level is an int on the GUI it is displayed in text, this converts combo selection to the required INT
            String level = aupLevelCombo.getSelectedItem().toString();
            if (level.contentEquals("Standard User")) {
               user.setLevel(1);
            } else {
               user.setLevel(0);
            }

            String staff = aupStaffCombo.getSelectedItem().toString();
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
      } catch (Blanks e) {
         aupUsernameField.setText("ERROR" + errorUser);
         aupPasswordField.setText("PASSWORD");
         errorUser++;

         return null;
      }

   } //END PULL USER METHOD

   /**
    * Listener for the Save Button (on the Add User Panel) 
    * @param ActionEvent e
    * @throws SQLException
    * @throws Blanks - When required fields are blank this error message is thrown
    */
   @SuppressWarnings("static-access")
   protected void aupSaveBtnActionPerformed(ActionEvent e) throws SQLException, Blanks {
      User user = new User();
      user = pullUser();
      //System.out.println(user.toString()); //Debug
      InsertUser insert = new InsertUser();
      int flag = insert.validation(user);
      if (flag == 0) {
         insert.userInsert(user);
         JOptionPane.showMessageDialog(rootPane, "Succes. New User created", "Saved!", JOptionPane.INFORMATION_MESSAGE);
         aupClearBtn.doClick();
      } else {
         JOptionPane.showMessageDialog(rootPane, "Validation Fail, Duplicate Username", "Error!", JOptionPane.WARNING_MESSAGE);
         aupClearBtn.doClick();
      }
      updateTable(eeuTable);

   }

   /**
    * Action Listener for the clear button resets all fields to blank, default values
    * @param ActionEvent e
    */
   protected void aupClearBtnActionPerformed(ActionEvent e) {
      aupUsernameField.setText("");
      aupPasswordField.setText("");
      aupLevelCombo.setSelectedIndex(0);
      aupStaffCombo.setSelectedIndex(0);

   }

   /**
    * Action Listener for the Back Button, closes one panel if another is open and vice versa
    * @param ActionEvent e
    */
   protected void aupBackBtnActionPerformed(ActionEvent e) {

      if (aupPanelOpen == 0 && euuPanelOpen != 1) {
         addUserPanel.setVisible(true);
         aupPanelOpen = 1;
      } else {
         addUserPanel.setVisible(false);
         aupPanelOpen = 0;
      }
   }

   /////// Edit Existing Users Methods

   //Update Table with all users 

   /**
    * Method that retrieves data from the database and then applies it to the/a table on the GUI.
    * @param table - table that is displayed on the gui screen
    * @throws SQLException
    */
   public void updateTable(JTable table) throws SQLException {
      String sql = "Select username, level , staff_member from Users";
      conn.setConn();
      conn.setPstat(conn.getConn().prepareStatement(sql));
      conn.setRs(conn.getPstat().executeQuery(sql));

      //To remove previously added rows
      while (table.getRowCount() > 0) {
         ((DefaultTableModel) table.getModel()).removeRow(0);
      }
      int columns = conn.getRs().getMetaData().getColumnCount();
      while (conn.getRs().next()) {
         Object[] row = new Object[columns];
         for (int i = 1; i <= columns; i++) {
            row[i - 1] = conn.getRs().getObject(i);
         }
         ((DefaultTableModel) table.getModel()).insertRow(conn.getRs().getRow() - 1, row);
      }

   }

   /**
    * Exportuser method, retrieves information from the selected row on the table on the GUI. 
    * Then returns the user objec.
    * @return User - User object built inside the method
    */
   public User exportUser() {
      User user = new User();
      Staff member = new Staff();
      boolean flag = getEeuTable().getSelectionModel().isSelectionEmpty();
      if (flag == false) {
         int row = getEeuTable().getSelectedRow();
         int col = 0;
         String value = getEeuTable().getModel().getValueAt(row, col).toString();
         user.setUsername(value);
         col = 1;
         value = getEeuTable().getModel().getValueAt(row, col).toString();
         user.setLevel(Integer.valueOf(value));
         col = 2;
         value = getEeuTable().getModel().getValueAt(row, col).toString();
         member.setStaff_id(Integer.valueOf(value));
         user.setStaff(member);

      } else {
         JOptionPane.showMessageDialog(rootPane, "No user selected", "Error!", JOptionPane.WARNING_MESSAGE);

      }
      return user;
   }

   //Debug main method
   /* public static void main(String[] args) {
        adminGUI gui = new adminGUI();
        gui.setVisible(true);
     }*/

   //
}