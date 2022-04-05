package ManageInvoice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
import Connection.DbConnection;
import Filter.InvoiceRowFilter;
import Tables.Address;
import Tables.Car;
import Tables.Customer;
import Tables.Invoice;
import Tables.Stock;

/**
 * @author Andrew Gilbey/C00263656
 */
public class ManageInvoiceGui extends JFrame {

   //Database Connection 
   static DbConnection conn = new DbConnection();

   private static DefaultComboBoxModel invoiceList = new DefaultComboBoxModel(); //List that is full of cars not in the stock table presently

   //Variable Declaration
   private JPanel bodyPanel;
   private JButton chngstatusBtn;
   private JCheckBox enableRmvChkBox;
   private JTable fullInvoiceTable;
   private JPanel headerPanel;
   private JComboBox < String > invoiCombo;
   private JLabel invoiceLabel;
   private JPanel invoiceTable;
   private JLabel logo;
   private JScrollPane listScroll;
   private JLabel manageStockLabel;
   private JLabel note;
   private JButton printBtn;
   private JPanel quickRmPanel;
   private JButton refreshTableBtn;
   private JButton rmvBtn;
   private JButton searchBtn;
   private JTextField searchField;
   private JLabel searchLabel;
   private JPanel searchPanel;
   private JButton viewBtn;

   /**
    * Constructor calls the initalise method to build the GUI and output it to the screen
    * @throws SQLException
    */
   public ManageInvoiceGui() throws SQLException {
      initialise();
   }

   /**
    * initialise method called in the constructor. Contains all code relating to the Manage Invoice GUI.
    * @throws SQLException
    */

   public void initialise() throws SQLException {
      headerPanel = new JPanel();
      manageStockLabel = new JLabel();
      logo = new JLabel();
      bodyPanel = new JPanel();
      invoiceTable = new JPanel();
      listScroll = new JScrollPane();
      fullInvoiceTable = new JTable();
      refreshTableBtn = new JButton();
      note = new JLabel();
      viewBtn = new JButton();
      printBtn = new JButton();
      chngstatusBtn = new JButton();
      quickRmPanel = new JPanel();
      invoiceLabel = new JLabel();
      rmvBtn = new JButton();
      enableRmvChkBox = new JCheckBox();
      invoiCombo = new JComboBox < > ();
      searchPanel = new JPanel();
      searchLabel = new JLabel();
      searchField = new JTextField();
      searchBtn = new JButton();

      //Fonts
      Font interM36 = new Font("Inter Medium", 1, 36);
      Font interM14 = new Font("Inter Medium", 0, 14);

      //Dimensions
      Dimension frame = new Dimension(1000, 770);
      //Colours
      Color black = new Color(0, 0, 0);
      Color white = new Color(255, 255, 255);

      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      getContentPane().setLayout(null);
      setPreferredSize(frame);
      setResizable(false);
      setTitle("Manage Invoices");

      headerPanel.setBackground(new Color(204, 204, 255));
      headerPanel.setBorder(BorderFactory.createLineBorder(black));
      headerPanel.setForeground(white);
      headerPanel.setLayout(null);

      manageStockLabel.setFont(interM36);
      manageStockLabel.setText("Manage Invoices");
      headerPanel.add(manageStockLabel);
      manageStockLabel.setBounds(340, 10, 440, 90);

      logo.setIcon(new ImageIcon(getClass().getResource("/Images/manageInvoices.png")));
      headerPanel.add(logo);
      logo.setBounds(10, 0, 110, 100);

      getContentPane().add(headerPanel);
      headerPanel.setBounds(10, 10, 980, 100);

      bodyPanel.setBorder(BorderFactory.createLineBorder(black));
      bodyPanel.setLayout(null);

      invoiceTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(black), "Invoice Table"));
      invoiceTable.setLayout(null);

      fullInvoiceTable.setModel(new DefaultTableModel(
         new Object[][] {
            {
               null,
               null,
               null,
               null,
               null,
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
               null,
               null,
               null,
               null,
               null,
               null
            }
         },
         new String[] {
            "Invoice-No",
            "First Name",
            "Last Name",
            "Car-Reg",
            "Car-Make",
            "Car-Model",
            "Price",
            "VAT",
            "Total Price",
            "Payment Method",
            "Issue Date",
            "Status"
         }
      ) {
         boolean[] canEdit = new boolean[] {
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false
         };

         public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
         }
      });
      updateInvoiceTable(fullInvoiceTable);
      listScroll.setViewportView(fullInvoiceTable);

      TableRowSorter tableSorter = new TableRowSorter(fullInvoiceTable.getModel());
      fullInvoiceTable.setRowSorter(tableSorter);

      invoiceTable.add(listScroll);
      listScroll.setBounds(10, 20, 940, 380);

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
      invoiceTable.add(refreshTableBtn);
      refreshTableBtn.setBounds(10, 410, 20, 20);

      note.setText("Click to select an invoice in the table and click on option button");
      invoiceTable.add(note);
      note.setBounds(40, 410, 370, 15);

      viewBtn.setText("View ");
      viewBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               viewBtnActionPerformed(e);
            } catch (SQLException e1) {

               e1.printStackTrace();
            }
         }
      });
      invoiceTable.add(viewBtn);
      viewBtn.setBounds(430, 410, 72, 21);

      invoiceTable.add(printBtn);
      //printBtn.setBounds(520, 410, 72, 21);

      chngstatusBtn.setText("Change Status");
      chngstatusBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               chngstatusBtnActionPerformed(e);
            } catch (SQLException e1) {
               e1.printStackTrace();
            }
         }
      });
      invoiceTable.add(chngstatusBtn);
      chngstatusBtn.setBounds(610, 410, 130, 21);

      bodyPanel.add(invoiceTable);
      invoiceTable.setBounds(10, 10, 960, 440);

      quickRmPanel.setBorder(BorderFactory.createTitledBorder("Remove Invoice"));
      quickRmPanel.setLayout(null);

      invoiceLabel.setFont(interM14);
      invoiceLabel.setText("Invoice:");
      quickRmPanel.add(invoiceLabel);
      invoiceLabel.setBounds(110, 50, 80, 20);

      rmvBtn.setText("Remove");
      rmvBtn.setEnabled(false);
      rmvBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               rmvBtnActionPerformed(e);
            } catch (SQLException e1) {
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
      enableRmvChkBox.setBounds(330, 10, 120, 19);

      invoiCombo.setModel(invoiceList);
      invoiCombo.setEnabled(false);
      quickRmPanel.add(invoiCombo);
      invoiCombo.setBounds(190, 50, 180, 21);
      populateInvoices(); //populate invoicombo

      bodyPanel.add(quickRmPanel);
      quickRmPanel.setBounds(10, 460, 460, 130);

      searchPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)), "Search"));
      searchPanel.setLayout(null);

      searchLabel.setFont(interM14);
      searchLabel.setText("Search:");
      searchPanel.add(searchLabel);
      searchLabel.setBounds(60, 60, 60, 20);
      searchPanel.add(searchField);
      searchField.setBounds(120, 60, 180, 21);

      searchBtn.setText("Search");

      searchBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String searched = searchField.getText();
            tableSorter.setRowFilter(new InvoiceRowFilter(searched));
         }
      });

      searchPanel.add(searchBtn);
      searchBtn.setBounds(320, 60, 90, 21);

      bodyPanel.add(searchPanel);
      searchPanel.setBounds(480, 460, 490, 130);

      getContentPane().add(bodyPanel);
      bodyPanel.setBounds(10, 120, 980, 610);

      pack();
      setVisible(true);
      setLocationRelativeTo(null);
   }

   /**
    * Check-box State Listener which when changes state (either checked or not) will enable or disable the Remove Button accordingly. 
    * 
    * @param ItemEvent e
    */
   protected void enableRmvChkBoxItemStateChanged(ItemEvent e) {

      if (rmvBtn.isEnabled() == false) {
         rmvBtn.setEnabled(true);
         invoiCombo.setEnabled(true);
      } else {
         rmvBtn.setEnabled(false);
         invoiCombo.setEnabled(false);
      }

   }

   /**
    * Actionlistener for the Remove button.
    * Creates a new invoice object and calls the buildInvoicefrmcombo to create an invoice based on details
    * from the invoice combo box (that was selected for delete). Runs the Invoice delete method from the DeleteInvoice class
    * to remove the invoice entry from the database
    * @param ActionEvent e
    * @throws SQLException
    */
   protected void rmvBtnActionPerformed(ActionEvent e) throws SQLException {
      Invoice invoice = new Invoice();
      invoice = buildInvoicefrmcombo();
      if (invoice != null) {
         DeleteInvoice.invoiceDelete(invoice);
         JOptionPane.showMessageDialog(null, "Invoice deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
         updateInvoiceTable(fullInvoiceTable); //Refresh table to reflect edit
      } else {
         JOptionPane.showMessageDialog(null, "An error ouccred.", "Error", JOptionPane.ERROR_MESSAGE);
      }
   }

   /**
    * Method called when the chngStatus button is clicked.
    * Calls the build invoice method to create an invoice object if it returns not null the invoice object is passed through
    * the statusMod method of the ModStatus class to Update the SQL status in the database
    * @param ActionEvent e
    * @throws SQLException - An exception that provides information on a database access error or other errors.
    */
   protected void chngstatusBtnActionPerformed(ActionEvent e) throws SQLException {
      Invoice invoice = new Invoice();
      invoice = buildInvoice(); //call the build invoice method
      if (invoice != null) { //If the object is returned not null
         ModStatus.statusMod(invoice); //calls the status mod method from the ModStatus class
         updateInvoiceTable(fullInvoiceTable); //Refresh table to reflect edit

      } else {
         JOptionPane.showMessageDialog(null, "Nothing selected to change.", "Error", JOptionPane.ERROR_MESSAGE);
      }
   }

   protected void viewBtnActionPerformed(ActionEvent e) throws SQLException {
      Invoice invoice = new Invoice();
      invoice = buildInvoice();
      invoice.toString();
      if (invoice != null) {
         InvoiceOutput output = new InvoiceOutput();
         output.outputInvoice(invoice);
      } else {
         JOptionPane.showMessageDialog(null, "Error encountered.", "Error", JOptionPane.ERROR_MESSAGE);
      }

   }

   /**
    * Refresh button method, calls the updateInvoiceTable method in order to refresh any changes made to the table.
    * essentially recalls the SQL queries in order to re-populate the table with data.
    * 
    * @param Action Event e
    * @throws SQLException - An exception that provides information on a database access error or other errors.
    */
   protected void refreshTableBtnActionPerformed(ActionEvent e) throws SQLException {
      updateInvoiceTable(fullInvoiceTable); //Call the method which updates/refreshes the table
      populateInvoices(); //call the populate invoices method
   }

   /**
    * Method that fully populates a given table on the GUI with information on the Database
    * @param table - the table which sits on the GUI
    * @throws SQLException - An exception that provides information on a database access error or other errors.
    */
   public static void updateInvoiceTable(JTable table) throws SQLException {
      String sql = "SELECT Invoice.InvoiceNumber,Customer.FirstName,Customer.LastName,Car.Reg,Car.Make," +
         "Car.Model,Invoice.Price," +
         "Invoice.VAT,Invoice.Total,Invoice.Payment_Method,Invoice.IssueDate,Invoice.Invoice_Status " +
         "FROM garage.Invoice Inner Join Customer On Customer = CustomerID LEFT JOIN Car On Car = CarId ";

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
    * Method that builds an Invoice object that will typically be sent into the generate Invoice Method to produce an invoice for printing.
    * Because the method uses SQL statements the method will throw an SQLException error.
    * @return Invoice - Invoice object that is built in the method is returned
    * @throws SQLException - An exception that provides information on a database access error or other errors.
    */
   public Invoice buildInvoice() throws SQLException {

      Invoice invoice = new Invoice();
      Customer customer = new Customer();
      Address address = new Address();
      Car car = new Car();

      boolean flag = getFullInvoiceTable().getSelectionModel().isSelectionEmpty();
      if (flag == false) {
         int row = getFullInvoiceTable().getSelectedRow();
         row = getFullInvoiceTable().getRowSorter().convertRowIndexToModel(row); //If filtering is used we have to convert this across in order for the correct row to be selected
         int col = 0;
         String value = getFullInvoiceTable().getModel().getValueAt(row, col).toString();
         String sql = "SELECT Invoice.InvoiceNumber,Customer.FirstName,Customer.LastName,Customer.AddressLine1," +
            "Customer.AddressLine2,Customer.Town,Customer.County,Customer.Eircode,Car.Reg," +
            "Car.Make,Car.Model,Invoice.Price,Invoice.VAT,Invoice.Total," +
            "Invoice.Payment_Method,Invoice.IssueDate,Invoice.Invoice_Status " +
            "FROM garage.Invoice Inner Join Customer On Customer = CustomerID LEFT JOIN Car On Car = CarId Where InvoiceNumber = ? ";

         conn.setPstat(conn.getConn().prepareStatement(sql));
         conn.getPstat().setString(1, value);
         conn.setRs(conn.getPstat().executeQuery());
         if (conn.getRs().next()) {
            invoice.setInvoiceNo(conn.getRs().getInt("InvoiceNumber"));
            //Find the inital customer values in the result set
            customer.setFname(conn.getRs().getString("FirstName"));
            customer.setLname(conn.getRs().getString("LastName"));
            //Find the address values in the result set
            address.setAddressLn1(conn.getRs().getString("AddressLine1"));
            address.setAddressLn2(conn.getRs().getString("AddressLine2"));
            address.setTown(conn.getRs().getString("Town"));
            address.setCounty(conn.getRs().getString("County"));
            address.setEircode(conn.getRs().getString("Eircode"));
            customer.setAddress(address); //set the address to the customer 
            invoice.setCustomer(customer);
            //Find the required Car details in the result set
            car.setReg(conn.getRs().getString("Reg"));
            car.setMake(conn.getRs().getString("Make"));
            car.setModel(conn.getRs().getString("Model"));
            invoice.setCar(car); //Set the car object into invoice

            //Get required final invoice variables
            invoice.setPrice(conn.getRs().getDouble("Price"));
            invoice.setVAT(conn.getRs().getDouble("VAT"));
            invoice.setTotalCashPrice(conn.getRs().getDouble("Total"));
            invoice.setPaymentMethod(conn.getRs().getString("Payment_Method"));
            invoice.setIssueDate(conn.getRs().getString("IssueDate"));
            invoice.setInvoiceStatus(conn.getRs().getInt("Invoice_Status"));

            //System.out.println(invoice.toString());//ToString for debug purposes
            return invoice;

         } else {
            return null;
         }
      }
      return null;

   }

   /**Method that populates the Invoice Combo-box  with entries
    * 
    */
   public void populateInvoices() {
      invoiceList.removeAllElements();
      conn.setConn(); //Make a connection
      String sql = "Select InvoiceNumber,StockID,Total from Invoice";

      try {
         // assume that all objects were all properly defined
         conn.setPstat(conn.getConn().prepareStatement(sql)); //set the prepared statement
         conn.setRs(conn.getPstat().executeQuery(sql)); //save the results into the result set
         while (conn.getRs().next()) { //loop through the result set
            //int id = rs.getInt("id");
            //list.addElement(id);
            int invoiceNumber = (conn.getRs().getInt("InvoiceNumber"));
            String invoiceDisplay = "Inv:" + " " + (String.valueOf(invoiceNumber));
            invoiceDisplay = invoiceDisplay + " Stock No: " + conn.getRs().getString("StockID"); //Grab the first name from the table
            invoiceList.addElement(invoiceDisplay); //a dd the result set data into the combo box

         }
      } catch (Exception err) {
         System.out.println(err);
      }
   }

   /**
    * Method that builds an Invoice object which is then to passed onto the delete invoice method to remove it from the database.
    * @return Invoice - The built invoice object make up in the method (relates to the invoice combo box of the gui)
    */
   public Invoice buildInvoicefrmcombo() {
      Invoice invoice = new Invoice();
      Stock stock = new Stock();
      String fullComboStr = invoiCombo.getSelectedItem().toString();
      String invoiceDetArray[] = fullComboStr.split(" ");

      invoice.setInvoiceNo(Integer.valueOf(invoiceDetArray[1]));
      stock.setStockNo(Integer.valueOf(invoiceDetArray[4]));
      invoice.setStock(stock);

      return invoice;

   }

   //Getters and Setters

   //Table needs Getter methods for functionality in conjunction with other methods that populate the table.
   public JTable getFullInvoiceTable() {
      return fullInvoiceTable;
   }

   public void setFullInvoiceTable(JTable fullInvoiceTable) {
      this.fullInvoiceTable = fullInvoiceTable;
   }
}