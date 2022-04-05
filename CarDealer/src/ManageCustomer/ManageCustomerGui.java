package ManageCustomer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.NumberFormatter;

import Connection.DbConnection;
import ErrorHandling.DataValidationFail;
import ErrorHandling.IntegrityConstraintValidation;
import Filter.CustomerRowFilter;
import Filter.RegRowFilter;
import Filter.StockNoRowFilter;
import ManageStock.UpdateStock;
import Tables.Address;
import Tables.Customer;
import Tables.Stock;

/**
 * @author Andrew Gilbey/c00263656
 *
 */
public class ManageCustomerGui extends JFrame {

   private JTextField addressLn1Field;
   private JLabel addressLn1Label;
   private JTextField addressLn2Field;
   private JLabel addressLn2Label;
   private JPanel bodyPanel;
   private JComboBox < String > budgetCombo;
   private JLabel budgetLabel;
   private JComboBox < String > countyCombo;
   private JLabel countyLabel;
   private JPanel currentStockPanel;
   private JTable customerListTable;
   private JTextField eircodeField;
   private JLabel eircodeLabel;
   private JTextField emailField;
   private JLabel emailLabel;
   private JCheckBox enableRmvChkBox;
   private JTextField fNameField;
   private JLabel fNameLabel;
   private JPanel headerPanel;
   private JTextField lNameField;
   private JLabel lNameLabel;
   private JScrollPane listScroll;
   private JLabel manageStockLabel;
   private JPanel newCustPanel;
   private JLabel note;
   private JTextField phoneField;
   private JLabel phoneLabel;
   private JButton popOutModBtn;
   private JPanel quickRmPanel;
   private JButton refreshTableBtn;
   private JComboBox < String > rmCustCombo;
   private JLabel rmCustLabel;
   private JButton rmvBtn;
   private JButton saveBtn;
   private JButton searchBtn;
   private JTextField searchField;
   private JLabel searchLabel;
   private JPanel searchPanel;
   private JTextField townField;
   private JLabel townLabel;
   private JButton clearBtn;
   private JLabel managecustomerLogo;

   static DbConnection conn = new DbConnection();

   private static DefaultComboBoxModel customerList = new DefaultComboBoxModel();

   public ManageCustomerGui() throws HeadlessException {
      initialise();
   }

   public void initialise() {

      //Number formatter
      NumberFormat numberFormat = NumberFormat.getIntegerInstance();
      NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
      numberFormatter.setAllowsInvalid(false);
      numberFormatter.setMinimum(0l);

      clearBtn = new JButton();
      headerPanel = new JPanel();
      manageStockLabel = new JLabel();
      bodyPanel = new JPanel();
      currentStockPanel = new JPanel();
      listScroll = new JScrollPane();
      customerListTable = new JTable();
      refreshTableBtn = new JButton();
      note = new JLabel();
      popOutModBtn = new JButton();
      newCustPanel = new JPanel();
      fNameLabel = new JLabel();
      fNameField = new JTextField();
      lNameField = new JTextField();
      addressLn1Label = new JLabel();
      addressLn1Field = new JTextField();
      addressLn2Label = new JLabel();
      addressLn2Field = new JTextField();
      countyLabel = new JLabel();
      townField = new JTextField();
      townLabel = new JLabel();
      countyCombo = new JComboBox < > ();
      eircodeLabel = new JLabel();
      eircodeField = new JTextField();
      phoneLabel = new JLabel();
      phoneField = new JFormattedTextField(numberFormat);
      budgetLabel = new JLabel();
      emailField = new JTextField();
      budgetCombo = new JComboBox < > ();
      emailLabel = new JLabel();
      saveBtn = new JButton();
      lNameLabel = new JLabel();
      quickRmPanel = new JPanel();
      rmCustLabel = new JLabel();
      rmvBtn = new JButton();
      enableRmvChkBox = new JCheckBox();
      rmCustCombo = new JComboBox < > (customerList);
      searchPanel = new JPanel();
      searchLabel = new JLabel();
      searchField = new JTextField();
      searchBtn = new JButton();
      managecustomerLogo = new JLabel();

      //Fonts
      Font interM36B = new Font("Inter Medium", 1, 36);
      Font interM14 = new Font("Inter Medium", 0, 14);
      Font interM10 = new Font("Inter Medium", 0, 10);

      //Dimensions
      Dimension frame = new Dimension(1000, 740);
      Dimension bodyPanelSize = new Dimension(980, 610);

      //Colours
      Color purple = new Color(204, 204, 255);
      Color white = new Color(255, 255, 255);
      Color black = new Color(0, 0, 0);
      Color yellow = new Color(255, 255, 204);

      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      setPreferredSize(frame);
      setResizable(false);
      getContentPane().setLayout(null);
      setTitle("Manage Customers");
      headerPanel.setBackground(purple);
      headerPanel.setBorder(BorderFactory.createLineBorder(black));
      headerPanel.setForeground((white));
      headerPanel.setLayout(null);

      managecustomerLogo.setIcon(new ImageIcon(getClass().getResource("/Images/managecustomers.png"))); // NOI18N
      headerPanel.add(managecustomerLogo);
      managecustomerLogo.setBounds(20, 0, 110, 100);

      manageStockLabel.setFont(interM36B);
      manageStockLabel.setText("Manage Customers");
      headerPanel.add(manageStockLabel);
      manageStockLabel.setBounds(340, 10, 440, 90);

      getContentPane().add(headerPanel);
      headerPanel.setBounds(10, 10, 980, 100);

      bodyPanel.setBorder(BorderFactory.createLineBorder(black));
      bodyPanel.setLayout(null);
      bodyPanel.setPreferredSize(bodyPanelSize);

      currentStockPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(black), "Current Customer List"));
      currentStockPanel.setLayout(null);

      customerListTable.setModel(new DefaultTableModel(
         new Object[][] {
            {
               null,
               null,
               null,
               null,
               null,
               null,
               null
            }, {
               null,
               null,
               null,
               null,
               null,
               null,
               null
            }, {
               null,
               null,
               null,
               null,
               null,
               null,
               null
            }, {
               null,
               null,
               null,
               null,
               null,
               null,
               null
            }
         },
         new String[] {
            "C-ID",
            "First Name",
            "Last Name",
            "Phone",
            "Email",
            "Eircode",
            "Budget"
         }
      ));
      listScroll.setViewportView(customerListTable);
      try {
         updateStockTable(customerListTable);
      } catch (SQLException e2) {
         e2.printStackTrace();
      }

      TableRowSorter tableSorter = new TableRowSorter(customerListTable.getModel());
      customerListTable.setRowSorter(tableSorter);

      currentStockPanel.add(listScroll);
      listScroll.setBounds(9, 23, 480, 530);

      refreshTableBtn.setIcon(new ImageIcon(getClass().getResource("/Images/refresh.png")));;
      refreshTableBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               refreshTableBtnActionPerformed(e);
            } catch (SQLException e1) {
               e1.printStackTrace();
            }
         }
      });
      currentStockPanel.add(refreshTableBtn);
      refreshTableBtn.setBounds(10, 560, 20, 21);

      note.setFont(interM10);
      note.setText("Highlight customer that you wish to view/modify and click");
      currentStockPanel.add(note);
      note.setBounds(40, 560, 290, 13);

      popOutModBtn.setText("Pop-Out Modify");
      popOutModBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               popOutModBtnActionPerformed(e);
            } catch (SQLException e1) {
               e1.printStackTrace();
            }
         }
      });
      currentStockPanel.add(popOutModBtn);
      popOutModBtn.setBounds(330, 560, 160, 21);

      bodyPanel.add(currentStockPanel);
      currentStockPanel.setBounds(470, 10, 500, 590);

      newCustPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(black), "New Customer"));
      newCustPanel.setLayout(null);

      fNameLabel.setFont((interM14));
      fNameLabel.setText("First Name:");
      newCustPanel.add(fNameLabel);
      fNameLabel.setBounds(50, 30, 80, 20);
      newCustPanel.add(fNameField);
      fNameField.setTransferHandler(null); //Disable pasting into field
      fNameField.setBounds(130, 30, 180, 21);
      fNameField.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            if (fNameField.getText().length() >= 45) // limit to 45 characters
               e.consume();
         }
      });
      newCustPanel.add(lNameField);
      lNameField.setTransferHandler(null); //Disable pasting into field
      lNameField.setBounds(130, 60, 180, 21);
      lNameField.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            if (lNameField.getText().length() >= 45) // limit to 45 characters
               e.consume();
         }
      });

      addressLn1Label.setFont((interM14));
      addressLn1Label.setText("Address Line 1:");
      newCustPanel.add(addressLn1Label);
      addressLn1Label.setBounds(20, 90, 120, 20);
      newCustPanel.add(addressLn1Field);
      addressLn1Field.setTransferHandler(null); //Disable pasting into field
      addressLn1Field.setBounds(130, 90, 180, 21);
      addressLn1Field.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            if (addressLn1Field.getText().length() >= 30) // limit to 30 characters
               e.consume();
         }
      });

      addressLn2Label.setFont((interM14));
      addressLn2Label.setText("Address Line 2:");
      newCustPanel.add(addressLn2Label);
      addressLn2Label.setBounds(20, 120, 120, 20);
      newCustPanel.add(addressLn2Field);
      addressLn2Field.setTransferHandler(null); //Disable pasting into field
      addressLn2Field.setBounds(130, 120, 180, 21);
      addressLn2Field.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            if (addressLn2Field.getText().length() >= 30) // limit to 45 characters
               e.consume();
         }
      });

      countyLabel.setFont((interM14));
      countyLabel.setText("County:");
      newCustPanel.add(countyLabel);
      countyLabel.setBounds(70, 180, 70, 20);
      newCustPanel.add(townField);
      townField.setBounds(130, 150, 180, 21);
      townField.setTransferHandler(null); //disable pasting into field
      townField.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            if (townField.getText().length() >= 40) // limit to 45 characters
               e.consume();
         }
      });

      townLabel.setFont((interM14));
      townLabel.setText("Town:");
      newCustPanel.add(townLabel);
      townLabel.setBounds(80, 150, 60, 20);

      countyCombo.setModel(new DefaultComboBoxModel < > (new String[] {
         "Carlow",
         "Cavan",
         "Clare",
         "Cork",
         "Donegal ",
         "Dublin",
         "Galway",
         "Kerry",
         "Kildare",
         "Kilkenny",
         "Laois",
         "Leitrim",
         "Limerick",
         "Longford",
         "Louth",
         "Mayo",
         "Meath",
         "Monaghan",
         "N/A",
         "Offaly",
         "Roscommon",
         "Sligo",
         "Tipperary",
         "Waterford",
         "Westmeath",
         "Wexford",
         "Wicklow"
      }));
      newCustPanel.add(countyCombo);
      countyCombo.setBounds(130, 180, 110, 21);

      eircodeLabel.setFont((interM14));
      eircodeLabel.setText("Eircode:");
      newCustPanel.add(eircodeLabel);
      eircodeLabel.setBounds(70, 210, 70, 20);
      newCustPanel.add(eircodeField);
      eircodeField.setTransferHandler(null); //disable pasting into field
      eircodeField.setBounds(130, 210, 180, 21);
      eircodeField.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            if (eircodeField.getText().length() >= 20) // limit to 45 characters
               e.consume();
         }
      });

      phoneLabel.setFont((interM14));
      phoneLabel.setText("Phone:");
      newCustPanel.add(phoneLabel);
      phoneLabel.setBounds(70, 240, 70, 20);
      newCustPanel.add(phoneField);
      phoneField.setBounds(130, 240, 180, 21);
      phoneField.setTransferHandler(null); //disable pasting into field
      phoneField.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            if (phoneField.getText().length() >= 20) // limit to 20 characters
               e.consume();
         }
      });

      budgetLabel.setFont((interM14));
      budgetLabel.setText("Budget:");
      newCustPanel.add(budgetLabel);
      budgetLabel.setBounds(70, 300, 70, 20);
      newCustPanel.add(emailField);
      emailField.setTransferHandler(null); //disable pasting into field
      emailField.setBounds(130, 270, 180, 21);
      emailField.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            if (emailField.getText().length() >= 45) // limit to 45 characters
               e.consume();
         }
      });

      budgetCombo.setModel(new DefaultComboBoxModel < > (new String[] {
         "<€1000",
         "€1000-€5000",
         "€5000-€10000",
         "€10001-€15000",
         "€15001-€20000",
         "€20001-€30000",
         "€30000-€40000",
         "€40000-€50000",
         "€50000-€75000",
         ">€750001"
      }));
      newCustPanel.add(budgetCombo);
      budgetCombo.setBounds(130, 300, 180, 21);

      emailLabel.setFont((interM14));
      emailLabel.setText("Email:");
      newCustPanel.add(emailLabel);
      emailLabel.setBounds(70, 270, 70, 20);

      saveBtn.setText("Save");
      saveBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               saveBtnActionPerformed(e);
            } catch (DataValidationFail | SQLException e1) {
               e1.printStackTrace();
            }
         }
      });
      newCustPanel.add(saveBtn);
      saveBtn.setBounds(160, 340, 72, 21);

      lNameLabel.setFont((interM14));
      lNameLabel.setText("Last Name:");
      newCustPanel.add(lNameLabel);
      lNameLabel.setBounds(50, 60, 80, 20);

      bodyPanel.add(newCustPanel);
      newCustPanel.setBounds(10, 80, 450, 370);

      quickRmPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(black), "Quick Remove"));
      quickRmPanel.setLayout(null);

      rmCustLabel.setFont((interM14));
      rmCustLabel.setText("Customer:");
      quickRmPanel.add(rmCustLabel);
      rmCustLabel.setBounds(110, 50, 80, 20);

      rmvBtn.setText("Remove");
      rmvBtn.setEnabled(false);
      rmvBtn.setBackground(yellow);
      rmvBtn.setIcon(new ImageIcon(getClass().getResource("/Images/warning.png")));
      rmvBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               rmvBtnActionPerformed(e);
            } catch (DataValidationFail e1) {
               e1.printStackTrace();
            } catch (SQLException e1) {
               e1.printStackTrace();
            } catch (IntegrityConstraintValidation e1) {
               e1.printStackTrace();
            }
         }
      });
      quickRmPanel.add(rmvBtn);
      rmvBtn.setBounds(160, 90, 110, 21);

      enableRmvChkBox.setText("Enable Remove");
      enableRmvChkBox.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {
            enableRmvChkBoxItemStateChanged(e);
         }
      });
      quickRmPanel.add(enableRmvChkBox);
      enableRmvChkBox.setBounds(315, 10, 120, 19);

      rmCustCombo.setModel(customerList);

      quickRmPanel.add(rmCustCombo);
      rmCustCombo.setBounds(190, 50, 140, 21);
      populateCustomer();

      bodyPanel.add(quickRmPanel);
      quickRmPanel.setBounds(10, 450, 450, 130);

      searchPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(black), "Search Customer"));
      searchPanel.setLayout(null);

      searchLabel.setFont((interM14));
      searchLabel.setText("Search:");
      searchPanel.add(searchLabel);
      searchLabel.setBounds(70, 30, 60, 20);
      searchPanel.add(searchField);
      searchField.setBounds(130, 30, 180, 21);

      searchBtn.setText("Search");
      searchBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            String searched = searchField.getText();
            tableSorter.setRowFilter(new CustomerRowFilter(searched));
         }
      });

      searchPanel.add(searchBtn);
      searchBtn.setBounds(320, 30, 90, 21);

      bodyPanel.add(searchPanel);
      searchPanel.setBounds(10, 10, 450, 70);

      getContentPane().add(bodyPanel);
      bodyPanel.setBounds(10, 120, 980, 610);

      clearBtn.setText("Clear");
      clearBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            clearBtnActionPerformed(e);
         }
      });
      newCustPanel.add(clearBtn);
      clearBtn.setBounds(240, 340, 72, 21);

      pack();
      setLocationRelativeTo(null);
   }

   protected void clearBtnActionPerformed(ActionEvent e) {
      fNameField.setText("");
      lNameField.setText("");
      addressLn1Field.setText("");
      addressLn2Field.setText("");
      townField.setText("");
      countyCombo.setSelectedIndex(0);
      eircodeField.setText("");
      phoneField.setText("");
      emailField.setText("");
      budgetCombo.setSelectedIndex(0);

   }

   protected void searchBtnActionPerformed(ActionEvent e) {

   }

   protected void enableRmvChkBoxItemStateChanged(ItemEvent e) {

      if (rmvBtn.isEnabled() == false) {
         rmvBtn.setEnabled(true);
      } else {
         rmvBtn.setEnabled(false);
      }
   }

   protected void rmvBtnActionPerformed(ActionEvent e) throws DataValidationFail, SQLException, IntegrityConstraintValidation {
      Customer customer = new Customer();
      customer = buildCustomerDelete();
      if (customer != null) {
         DeleteCustomer.customerDelete(customer);
         updateStockTable(customerListTable);

      } else {
         JOptionPane.showMessageDialog(bodyPanel, "An Error has occured with Data Validation.\n First Name, Last Name and Phone Number fields are required", "Error", JOptionPane.ERROR_MESSAGE);
      }
      conn.closeAll();

   }

   @SuppressWarnings("static-access")
   protected void saveBtnActionPerformed(ActionEvent e) throws DataValidationFail, SQLException {
      Customer customer = new Customer();

      customer = buildCustomer();
      if (customer != null) {
         InsertCustomer.customerInsert(customer);
         JOptionPane.showMessageDialog(bodyPanel, customer.getFname() + " " + customer.getLname() + " successfully added to database.", "Success", JOptionPane.INFORMATION_MESSAGE);
         populateCustomer();
         updateStockTable(customerListTable);
         clearBtn.doClick();

      } else {
         JOptionPane.showMessageDialog(bodyPanel, "An Error has occured with Data Validation.\n First Name, Last Name and Phone Number fields are required", "Error", JOptionPane.ERROR_MESSAGE);

      }

   }

   protected void popOutModBtnActionPerformed(ActionEvent e) throws SQLException {
      Customer customer = new Customer();

      customer = buildCustomerUpdate();
      if (customer != null) {
         ModifyCustomerPopOut popout = new ModifyCustomerPopOut(customer);
      } else {
         JOptionPane.showMessageDialog(null, "An Error has occured", "Fail", JOptionPane.ERROR_MESSAGE);
      }

   }

   protected void refreshTableBtnActionPerformed(ActionEvent e) throws SQLException {
      updateStockTable(customerListTable);
      populateCustomer();

   }

   //Methods

   public void populateCustomer() {
      customerList.removeAllElements();
      conn.setConn(); //Make a connection
      String sql = "Select CustomerID,Firstname,LastName from garage.Customer";

      try {
         // assume that all objects were all properly defined
         conn.setPstat(conn.getConn().prepareStatement(sql)); //set the prepared statement
         conn.setRs(conn.getPstat().executeQuery(sql)); //save the results into the result set
         while (conn.getRs().next()) { //loop through the result set
            //int id = rs.getInt("id");
            //list.addElement(id);
            int customerID = conn.getRs().getInt("CustomerID");
            String customerName = (String.valueOf(customerID));
            customerName = customerName + " " + conn.getRs().getString("FirstName"); //Grab the first name from the table
            customerName = customerName + " " + conn.getRs().getString("Lastname"); //Grab the last name from the table

            customerList.addElement(customerName); //add the result set data into the combo box

         }
         customerList.removeElementAt(0); //Remove default customer from remove list
      } catch (Exception err) {
         System.out.println(err);
      }
   }

   public static void updateStockTable(JTable table) throws SQLException {
      String sql = "Select CustomerID,Firstname,LastName,PhoneNumber as Phone,Email,Eircode,budget from Customer;";
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

   public Customer buildCustomer() throws DataValidationFail {
      Customer customer = new Customer();
      Address address = new Address();

      customer.setFname(fNameField.getText());
      customer.setLname(lNameField.getText());
      try {
         if (fNameField.getText().isEmpty() || lNameField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name fields cannot be blank.", "Warning", JOptionPane.ERROR_MESSAGE);
            throw new DataValidationFail("Any name field SHOULD NOT be blank for database integrity");
         }

         if (phoneField.getText().isBlank()) {
            customer.setPhone("0000000");
            JOptionPane.showMessageDialog(null, "Phone Number should not be blank.", "Warning", JOptionPane.ERROR_MESSAGE);
            throw new DataValidationFail("Phone number SHOULD NOT be blank for database integrity");
         } else {
            customer.setPhone(phoneField.getText());
         }
         customer.setEmail(emailField.getText());
         address.setAddressLn1(addressLn1Field.getText());
         address.setAddressLn2(addressLn2Field.getText());
         address.setCounty(countyCombo.getSelectedItem().toString());
         address.setTown(townField.getText());
         address.setEircode(eircodeField.getText());
         customer.setAddress(address);

         switch (budgetCombo.getSelectedIndex()) {
         case 0:
            customer.setBudget(1000);
            break;
         case 1:
            customer.setBudget(5000);
            break;
         case 2:
            customer.setBudget(15000);
            break;
         case 3:
            customer.setBudget(20000);
            break;
         case 4:
            customer.setBudget(30000);
            break;
         case 5:
            customer.setBudget(40000);
            break;
         case 6:
            customer.setBudget(50000);
            break;
         case 7:
            customer.setBudget(75000);
            break;
         case 8:
            customer.setBudget(76000);
            break;
         default:
            customer.setBudget(1000);
         }
      } catch (DataValidationFail e) {
         return null;
      }

      return customer;

   }

   public Customer buildCustomerDelete() {
      Customer customer = new Customer();
      String customerName = rmCustCombo.getSelectedItem().toString();
      String customerNameArray[] = customerName.split(" ");
      int customerID = Integer.parseInt(customerNameArray[0]);
      String fName = customerNameArray[1];
      String lName = customerNameArray[2];
      customer.setCustomerID(customerID);
      customer.setFname(fName);
      customer.setLname(lName);

      return customer;
   }

   public Customer buildCustomerUpdate() throws SQLException {
      Customer customer = new Customer();
      Address address = new Address();
      conn.setConn();
      boolean flag = customerListTable.getSelectionModel().isSelectionEmpty();
      if (flag != false) {
         int row = customerListTable.getSelectedRow();
         row = customerListTable.getRowSorter().convertRowIndexToModel(row);
         int col = 0;
         String value = customerListTable.getModel().getValueAt(row, col).toString();
         int quantifiedValue = Integer.parseInt(value);
         String sql = "Select * from Customer where customerID = ?";
         conn.setPstat(conn.getConn().prepareStatement(sql));
         conn.getPstat().setInt(1, quantifiedValue);
         conn.setRs(conn.getPstat().executeQuery());
         if (conn.getRs().next()) {
            int customerID = conn.getRs().getInt("customerID");
            String fName = conn.getRs().getString("FirstName");
            String lName = conn.getRs().getString("LastName");
            String phone = conn.getRs().getString("PhoneNumber");
            String email = conn.getRs().getString("Email");
            String addressLn1 = conn.getRs().getString("AddressLine1");
            String addressLn2 = conn.getRs().getString("AddressLine2");
            String town = conn.getRs().getString("Town");
            String county = conn.getRs().getString("County");
            String eircode = conn.getRs().getString("Eircode");
            double budget = conn.getRs().getDouble("Budget");
            customer.setCustomerID(customerID);
            customer.setFname(fName);
            customer.setLname(lName);
            customer.setPhone(phone);
            customer.setEmail(email);
            address.setAddressLn1(addressLn1);
            address.setAddressLn2(addressLn2);
            address.setCounty(county);
            address.setTown(town);
            address.setEircode(eircode);
            customer.setAddress(address);
            customer.setBudget(budget);
            return customer;

         } else {

            JOptionPane.showMessageDialog(rootPane, "No Customer selected", "Error!", JOptionPane.ERROR_MESSAGE);
         }
      }
      return null;
   }
}