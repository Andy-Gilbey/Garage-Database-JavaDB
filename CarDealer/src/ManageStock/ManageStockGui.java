package ManageStock;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import ErrorHandling.DataValidationFail;
import Filter.RegRowFilter;
import Filter.StockNoRowFilter;
import ManageInvoice.GenerateInvoice;
import ManageInvoice.GenerateInvoicePane;
import Tables.Address;
import Tables.Car;
import Tables.Customer;
import Tables.Invoice;
import Tables.Staff;
import Tables.Stock;

/**
 * @author Andrew Gilbey/C00263656
 */
public class ManageStockGui extends JFrame {

   private static final long serialVersionUID = 17922813122782036L;

   //Database Connection 
   static DbConnection conn = new DbConnection();

   //Table Models
   @SuppressWarnings("rawtypes")
   private static DefaultComboBoxModel nonAssignedCars = new DefaultComboBoxModel(); //List that is full of cars not in the stock table presently
   @SuppressWarnings("rawtypes")
   private static DefaultComboBoxModel currentStock = new DefaultComboBoxModel(); //List that stores the current Stock items stored in DB
   @SuppressWarnings("rawtypes")
   private static DefaultComboBoxModel staffList = new DefaultComboBoxModel();
   @SuppressWarnings("rawtypes")
   private static DefaultComboBoxModel customerList = new DefaultComboBoxModel();
   @SuppressWarnings("rawtypes")
   private static DefaultComboBoxModel invoiceableStock = new DefaultComboBoxModel();

   //Variable Declaration
   private JPanel addCarToStockPanel;
   private JLabel manageStockImage;
   private JButton addToStockListBtn;
   private static JPanel bodyPanel;
   private JLabel carToAddLabel;
   private JPanel currentStockPanel;
   private static JComboBox < String > customerCombo;
   private JLabel customerLabel;
   private JLabel euroSym;
   private JButton generateInvoiceBtn;
   private JPanel generateInvoicePanel;
   private JPanel headerPanel;
   private JComboBox < String > invGenStockItemCombo;
   private static JComboBox < String > carToAddCombo;
   private JScrollPane listScrollPane;
   private JButton modStockBtn;
   private JPanel modStockPanel;
   private static JTextField priceField;
   private JLabel priceLabel;
   private JTextField searchByRegField;
   private JLabel searchByRegLabel;
   private JTextField searchByStockNoField;
   private JPanel searchPanel;
   private JLabel searchStockNoLabel;
   private static JComboBox < String > sellerCombo;
   private JLabel sellerLabel;
   private static JComboBox < String > statusCombo;
   private JLabel statusLabel;
   private static JComboBox < String > stockItemCombo;
   private JLabel modEuroSymb;
   private static JTextField modPriceField;
   private JLabel modPriceLabel;
   private JButton popOutModify;
   private JLabel stockItemLabel;
   private JLabel stockItemLabel2;
   private static JTable stockListTable;
   private JLabel titleLabel;

   private JLabel instructionLabel;
   private JButton refreshTableBtn;
   private JButton searchBtn;

   /**
    * Constructor that calls the initialise method to build the GUI to the screen
    * @throws HeadlessException
    * @throws SQLException
    */
   public ManageStockGui() throws HeadlessException, SQLException {
      initialise();
      updateStockTable(stockListTable);
   }

   /**
    * The initialise method contains the code in order to build the ManageStock GUI and output it to the screen 
    * @throws SQLException
    */
   public void initialise() throws SQLException {

      headerPanel = new JPanel();
      manageStockImage = new JLabel();
      titleLabel = new JLabel();
      bodyPanel = new JPanel();
      currentStockPanel = new JPanel();
      listScrollPane = new JScrollPane();
      stockListTable = new JTable();
      addCarToStockPanel = new JPanel();
      carToAddCombo = new JComboBox < String > (getNonAssignedCars());
      carToAddLabel = new JLabel();
      euroSym = new JLabel();
      priceLabel = new JLabel();
      priceField = new JTextField();
      addToStockListBtn = new JButton();
      searchPanel = new JPanel();
      searchByRegLabel = new JLabel();
      searchByRegField = new JTextField();
      searchStockNoLabel = new JLabel();
      searchByStockNoField = new JTextField();
      modStockPanel = new JPanel();
      stockItemLabel = new JLabel();
      customerLabel = new JLabel();
      modStockBtn = new JButton();
      customerCombo = new JComboBox < > (customerList);
      statusLabel = new JLabel();
      popOutModify = new JButton();
      statusCombo = new JComboBox < > ();
      sellerLabel = new JLabel();
      sellerCombo = new JComboBox < > (getStaffList());
      stockItemCombo = new JComboBox < String > (currentStock);
      generateInvoicePanel = new JPanel();
      stockItemLabel2 = new JLabel();
      invGenStockItemCombo = new JComboBox < > (invoiceableStock);
      generateInvoiceBtn = new JButton();
      modPriceLabel = new JLabel();
      modEuroSymb = new JLabel();
      modPriceField = new JTextField();
      instructionLabel = new JLabel();
      refreshTableBtn = new JButton();
      searchBtn = new JButton();

      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      setTitle("Manage Stock");
      getContentPane().setLayout(null);

      //Fonts
      Font interM36 = new Font("Inter Medium", 1, 36);
      Font interM14 = new Font("Inter Medium", 1, 14);
      //Colours
      Color purple = new Color(204, 204, 255);
      Color black = new Color(0, 0, 0);
      Color white = new Color(255, 255, 255);

      //Dimensions
      Dimension frame = new Dimension(1000, 740);
      Dimension bodyPanelSize = new Dimension(980, 610);

      setPreferredSize(frame); //Sets size of the frame to the dimensions set in the frame dimension object
      setResizable(false); //Disable resize window

      headerPanel.setBackground(purple);
      headerPanel.setBorder(BorderFactory.createLineBorder(black));
      headerPanel.setForeground(white);
      headerPanel.setLayout(null);

      titleLabel.setFont(interM36);
      titleLabel.setText("Manage Stock");
      headerPanel.add(titleLabel);
      titleLabel.setBounds(340, 10, 440, 90);

      getContentPane().add(headerPanel);
      headerPanel.setBounds(10, 10, 980, 100);

      bodyPanel.setBorder(BorderFactory.createLineBorder(black));
      bodyPanel.setLayout(null);

      currentStockPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(black), "Stock List"));
      currentStockPanel.setLayout(null);

      stockListTable.setModel(new DefaultTableModel(
         new Object[][] {
            {
               null,
               null,
               null,
               null
            }, {
               null,
               null,
               null,
               null
            }, {
               null,
               null,
               null,
               null
            }, {
               null,
               null,
               null,
               null
            }
         },
         new String[] {
            "Stock Number",
            "Car-Reg",
            "Price",
            "Status"
         }
      ) {
         boolean[] canEdit = new boolean[] {
            false,
            false,
            false,
            false
         };

         public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
         }
      });

      TableRowSorter tableSorter = new TableRowSorter(stockListTable.getModel());
      stockListTable.setRowSorter(tableSorter);

      manageStockImage.setIcon(new ImageIcon(getClass().getResource("/Images/manageStock.png")));
      headerPanel.add(manageStockImage);
      manageStockImage.setBounds(10, 0, 240, 100);

      listScrollPane.setViewportView(stockListTable);

      currentStockPanel.add(listScrollPane);
      listScrollPane.setBounds(19, 23, 470, 510);

      bodyPanel.add(currentStockPanel);
      currentStockPanel.setBounds(460, 20, 500, 570);

      addCarToStockPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(black), "Add Car to Stock List"));
      addCarToStockPanel.setLayout(null);

      addCarToStockPanel.add(carToAddCombo);
      carToAddCombo.setModel(getNonAssignedCars());
      carToAddCombo.setBounds(120, 40, 260, 21);

      carToAddLabel.setFont(interM14);
      carToAddLabel.setText("Car to Add:");
      addCarToStockPanel.add(carToAddLabel);
      carToAddLabel.setBounds(30, 40, 80, 20);

      euroSym.setFont(interM14);
      euroSym.setText("€");
      addCarToStockPanel.add(euroSym);
      euroSym.setBounds(110, 80, 10, 20);

      instructionLabel.setText("Highlight the stock item you wish to modify and click");
      currentStockPanel.add(instructionLabel);
      instructionLabel.setBounds(40, 540, 300, 20);

      priceLabel.setFont(interM14);
      priceLabel.setText("Price:");
      addCarToStockPanel.add(priceLabel);
      priceLabel.setBounds(60, 80, 80, 20);

      priceField.setText("0.0");
      addCarToStockPanel.add(priceField);
      priceField.setBounds(120, 80, 110, 21);

      addToStockListBtn.setText("Add To Stock List");
      populateNonStock();
      addToStockListBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               addToStockListBtnActionPerformed(e);
            } catch (SQLException e1) {
               e1.printStackTrace();
            }

         }
      });
      addCarToStockPanel.add(addToStockListBtn);
      addToStockListBtn.setBounds(130, 120, 140, 21);

      bodyPanel.add(addCarToStockPanel);
      addCarToStockPanel.setBounds(20, 120, 440, 170);

      searchPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(black), "Search Stock"));
      searchPanel.setLayout(null);

      searchByRegLabel.setFont(interM14);
      searchByRegLabel.setText("Search By Car Reg:");
      searchPanel.add(searchByRegLabel);
      searchByRegLabel.setBounds(60, 60, 170, 20);
      searchPanel.add(searchByRegField);
      searchByRegField.setBounds(200, 60, 110, 21);

      searchStockNoLabel.setFont(interM14);
      searchStockNoLabel.setText("Search By Stock Number:");
      searchPanel.add(searchStockNoLabel);
      searchStockNoLabel.setBounds(20, 30, 170, 20);
      searchPanel.add(searchByStockNoField);
      searchByStockNoField.setBounds(200, 30, 110, 21);

      searchBtn.setText("Search");
      searchBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (searchByRegField.getText().isEmpty() == false && searchByStockNoField.getText().isEmpty() == false) {
               JOptionPane.showMessageDialog(bodyPanel, "You can only search by one category at a time.", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
               if (searchByRegField.getText().isEmpty()) {

                  String searched = searchByStockNoField.getText();
                  tableSorter.setRowFilter(new StockNoRowFilter(searched));
               } else {
                  String searched = searchByRegField.getText();
                  tableSorter.setRowFilter(new RegRowFilter(searched));
               }

            }
         }
      });

      searchPanel.add(searchBtn);
      searchBtn.setBounds(330, 40, 90, 21);

      bodyPanel.add(searchPanel);
      searchPanel.setBounds(20, 20, 440, 90);

      modStockPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(black), "Quick Stock Modify"));
      modStockPanel.setLayout(null);

      stockItemLabel.setFont(interM14);
      stockItemLabel.setText("Stock Item:");
      modStockPanel.add(stockItemLabel);
      stockItemLabel.setBounds(40, 30, 80, 20);

      customerLabel.setFont(interM14);
      customerLabel.setText("Customer:");
      modStockPanel.add(customerLabel);
      customerLabel.setBounds(50, 120, 80, 20);

      modStockBtn.setText("Modify Stock");
      modStockBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               modStockBtnActionPerformed(e);
            } catch (SQLException e1) {
               e1.printStackTrace();
            }
         }
      });
      modStockPanel.add(modStockBtn);
      modStockBtn.setBounds(130, 180, 140, 21);

      modStockPanel.add(customerCombo);
      customerCombo.setBounds(130, 120, 140, 21);
      populateCustomer();

      statusLabel.setFont(interM14);
      statusLabel.setText("Status:");
      modStockPanel.add(statusLabel);
      statusLabel.setBounds(60, 150, 80, 20);

      modStockPanel.add(statusCombo);
      statusCombo.setModel(new DefaultComboBoxModel < > (new String[] {
         "In Stock",
         "On Hold",
         "Sold-Not Invoiced"
      }));
      statusCombo.setBounds(130, 150, 140, 21);

      sellerLabel.setFont(interM14);
      sellerLabel.setText("Seller:");
      modStockPanel.add(sellerLabel);
      sellerLabel.setBounds(70, 90, 80, 20);

      modStockPanel.add(sellerCombo);
      sellerCombo.setBounds(130, 90, 140, 21);
      populateSeller();
      modStockPanel.add(stockItemCombo);
      stockItemCombo.setBounds(130, 30, 300, 21);
      populateCurrentStock();

      bodyPanel.add(modStockPanel);
      modStockPanel.setBounds(20, 300, 440, 220);

      generateInvoicePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(black), "Generate Invoice"));
      generateInvoicePanel.setLayout(null);

      stockItemLabel2.setFont(interM14);
      stockItemLabel2.setText("Stock Item:");
      generateInvoicePanel.add(stockItemLabel2);
      stockItemLabel2.setBounds(40, 30, 80, 20);

      invGenStockItemCombo.setModel(invoiceableStock);
      populateInvoiceable();
      generateInvoicePanel.add(invGenStockItemCombo);
      invGenStockItemCombo.setBounds(120, 30, 140, 21);

      generateInvoiceBtn.setText("Generate Invoice");
      generateInvoiceBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               generateInvoiceBtnActionPerformed(e);
            } catch (SQLException e1) {
               e1.printStackTrace();
            }
         }
      });
      generateInvoicePanel.add(generateInvoiceBtn);
      generateInvoiceBtn.setBounds(280, 30, 130, 21);

      bodyPanel.add(generateInvoicePanel);
      generateInvoicePanel.setBounds(20, 520, 440, 70);

      modEuroSymb.setFont(interM14);
      modEuroSymb.setText("€");
      modStockPanel.add(modEuroSymb);
      modEuroSymb.setBounds(120, 60, 10, 20);

      modPriceField.setText("0.0");
      modStockPanel.add(modPriceField);
      modPriceField.setBounds(130, 60, 100, 21);

      modPriceLabel.setFont(interM14);
      modPriceLabel.setText("Price:");
      modStockPanel.add(modPriceLabel);
      modPriceLabel.setBounds(70, 60, 40, 20);

      popOutModify.setText("Pop-Out Modify");
      currentStockPanel.add(popOutModify);
      popOutModify.setBounds(340, 540, 150, 21);
      popOutModify.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               popOutModifyActionPerformed(e);
            } catch (SQLException e1) {
               e1.printStackTrace();
            }
         }
      });

      getContentPane().add(bodyPanel);
      bodyPanel.setBounds(10, 120, 980, 610);

      refreshTableBtn.setIcon(new ImageIcon(getClass().getResource("/Images/refresh.png")));
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
      refreshTableBtn.setBounds(10, 540, 20, 21);

      pack();
      setLocationRelativeTo(null); //Spawn the GUI centre of the screen
   }

   //Action Listeners

   /**
    * Refresh button action listener. Calls the updateStockTable method to refresh any changes made to the table.
    * Also calls the populateNonStock and populateInvoiceable methods to reflect any changes that may have occurred in modifying the database
    * and reflect changes in the combo-boxes
    * @param ActionEvent e
    * @throws SQLException
    */

   protected void refreshTableBtnActionPerformed(ActionEvent e) throws SQLException {
      updateStockTable(stockListTable);
      populateNonStock();
      populateInvoiceable();

   }

   /**
    * Action listener for the addToStockList button. 
    * Adds a stock item to the stock table of the database when called based on a selection from a combo box
    * @param ActionEvent e
    * @throws SQLException
    */
   protected void addToStockListBtnActionPerformed(ActionEvent e) throws SQLException {

      Stock stock = new Stock(); //new stock object
      InsertToStock insert = new InsertToStock(); //new InsertTOStock object
      stock = buildStockForAdd(); //Build stock to add based on fields in the GUI
      if (stock != null) { //If the object is not returned as null
         insert.stockInsert(stock, stock.getCar().getCarId()); //Insert the car object to the database 
         JOptionPane.showMessageDialog(null, "Success! " + stock.getCar().getReg() + " added to stock", "Success", JOptionPane.INFORMATION_MESSAGE);
         updateStockTable(stockListTable); //refresh the table
         populateNonStock(); //refresh the combo box
         populateCurrentStock(); //refresh the combo box
         priceField.setText("0.0"); //reset the price field to zero
      } else {
         JOptionPane.showMessageDialog(null, "Data Validation Error occured.", "Fail", JOptionPane.ERROR_MESSAGE); //Error message if the object is returned null to avoid exception and a crash
      }

   }

   protected void modStockBtnActionPerformed(ActionEvent e) throws SQLException {
      Stock stock = new Stock();
      UpdateStock update = new UpdateStock();
      stock = buildStockForUpdate();
      if (stock != null) {
         update.stockUpdate(stock);
         JOptionPane.showMessageDialog(null, "Success!" + stock.getCar().getReg() + " stock modified", "Success", JOptionPane.INFORMATION_MESSAGE);
         updateStockTable(stockListTable);
         populateCurrentStock();
      } else {
         JOptionPane.showMessageDialog(null, "Data Validation Error occured.", "Fail", JOptionPane.ERROR_MESSAGE);
      }

   }

   protected void generateInvoiceBtnActionPerformed(ActionEvent e) throws SQLException {

      GenerateInvoicePane customPanes = new GenerateInvoicePane();
      String method = customPanes.paymentMethodPane();

      if (method == null) {
         JOptionPane.showMessageDialog(null, "Cancelled", "Cancelled", JOptionPane.NO_OPTION);
      } else {
         int status = customPanes.statusPane();
         Invoice invoice = new Invoice();
         invoice = buildInvoice(status, method);
         if (invoice != null) {

            GenerateInvoice.genInvoice(invoice);
            dispose();
            JOptionPane.showMessageDialog(this, "Invoice Generated", "Success", JOptionPane.INFORMATION_MESSAGE);
         } else {
            JOptionPane.showMessageDialog(null, "An Error has occured", "Fail", JOptionPane.ERROR_MESSAGE);
         }

      }

   }

   protected void popOutModifyActionPerformed(ActionEvent e) throws SQLException {
      Stock stock = new Stock();
      stock = buildStock();
      if (stock != null) {
         ModifyStockPopOut popout = new ModifyStockPopOut(stock, this);
      }
   }

   //Methods
   public static void updateStockTable(JTable table) throws SQLException {
      String sql = "Select Stock.StockNumber,Car.Reg,Stock.Price,Stock.Status from Stock Inner Join Car on Stock.Car = Car.CarId;";
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

   public static void populateNonStock() { //Populate The insert to stock combo box with Cars not found in the stock table already.
      getNonAssignedCars().removeAllElements();
      conn.setConn(); //Make a connection
      String sql = "Select Car.Reg,Car.Model,Car.Make from Car LEFT JOIN Stock ON Car.CarId = Stock.Car where Stock.Car IS NULL";

      try {
         // assume that all objects were all properly defined
         conn.setPstat(conn.getConn().prepareStatement(sql)); //set the prepared statement
         conn.setRs(conn.getPstat().executeQuery(sql)); //save the results into the result set
         while (conn.getRs().next()) { //loop through the result set

            String resultSet = conn.getRs().getString("Reg") + ", "; //Grab the first name from the table
            resultSet = resultSet + " " + conn.getRs().getString("Model") + ", "; //Grab the last name from the table
            resultSet = resultSet + " " + conn.getRs().getString("Make"); //Grab the last name from the table
            getNonAssignedCars().addElement(resultSet); //add the result set data into the combo box
         }
      } catch (Exception err) {
         System.out.println(err);
      }
   }

   public static void populateCurrentStock() throws SQLException {
      currentStock.removeAllElements();
      conn.setConn();
      String sql = "Select Stock.StockNumber,Car.Reg,Car.Model,Stock.Price,Stock.WhoSold as Seller,Stock.Customer,Stock.Status from Stock inner join Car on Stock.Car = Car.carId";
      conn.setPstat(conn.getConn().prepareStatement(sql));
      conn.setRs(conn.getPstat().executeQuery(sql)); //save the results into the result set
      while (conn.getRs().next()) { //loop through the result set

         String resultSet = conn.getRs().getString("StockNumber") + ", "; //Grab the first name from the table
         resultSet = resultSet + " " + conn.getRs().getString("Reg") + ", ";
         resultSet = resultSet + " " + conn.getRs().getString("Model") + ",";
         resultSet = resultSet + " " + conn.getRs().getString("Price") + ",";
         resultSet = resultSet + " " + conn.getRs().getString("Seller") + ",";
         resultSet = resultSet + " " + conn.getRs().getString("Customer") + ",";
         resultSet = resultSet + " " + conn.getRs().getString("Status"); //Grab the last name from the table
         currentStock.addElement(resultSet); //add the result set data into the combo box
      }

   }

   public static void populateSeller() throws SQLException {
      getStaffList().removeAllElements();
      conn.setConn(); //Make a connection
      String sql = "Select StaffID,Staff_Firstname,Staff_LastName from garage.Staff";

      try {
         // assume that all objects were all properly defined
         conn.setPstat(conn.getConn().prepareStatement(sql)); //set the prepared statement
         conn.setRs(conn.getPstat().executeQuery(sql)); //save the results into the result set
         while (conn.getRs().next()) { //loop through the result set
            //int id = rs.getInt("id");
            //list.addElement(id);
            String staffName = conn.getRs().getInt("StaffID") + " " + conn.getRs().getString("Staff_Firstname"); //Grab the first name from the table
            staffName = staffName + " " + conn.getRs().getString("Staff_Lastname"); //Grab the last name from the table
            getStaffList().addElement(staffName); //add the result set data into the combo box
         }
      } catch (Exception err) {
         System.out.println(err);
      }
   }

   /**
    * Populates the customer combo box with data that it retrieves from the database.
    * 
    * @throws SQLException
    */
   public static void populateCustomer() throws SQLException {
      customerList.removeAllElements();
      conn.setConn();
      String sql = "Select CustomerID, FirstName,LastName from garage.Customer";
      try {
         // assume that all objects were all properly defined
         conn.setPstat(conn.getConn().prepareStatement(sql)); //set the prepared statement
         conn.setRs(conn.getPstat().executeQuery(sql)); //save the results into the result set
         while (conn.getRs().next()) { //loop through the result set
            //int id = rs.getInt("id");
            //list.addElement(id);
            String customerName = conn.getRs().getInt("customerID") + " " + conn.getRs().getString("FirstName"); //Grab the first name from the table
            customerName = customerName + " " + conn.getRs().getString("LastName"); //Grab the last name from the table
            customerList.addElement(customerName); //add the result set data into the combo box

         }
      } catch (Exception err) {
         System.out.println(err);
      }
   }

   public static Stock buildStockForAdd() throws SQLException {
      Stock stock = new Stock();
      Car car = new Car();
      try {
         String carDetails = carToAddCombo.getSelectedItem().toString();
         String carDetailsArray[] = carDetails.split(", ");
         car.setReg(carDetailsArray[0]);
         car.setCarId(getCarId(car));
         stock.setCar(car);
         try {
            stock.setPrice(Double.parseDouble(priceField.getText()));
         } catch (NumberFormatException e) {
            stock.setPrice(0.0);
            JOptionPane.showMessageDialog(null, "The Price Field must only accept numbers. Price reverted to 0.0", "Error", JOptionPane.WARNING_MESSAGE);

            return null;
         }

         return stock;
      } catch (NullPointerException e) {
         JOptionPane.showMessageDialog(null, "There are no cars in inventory to stock", "Error", JOptionPane.WARNING_MESSAGE);

         return null;
      }

   }

   public static int getCarId(Car car) throws SQLException {

      int carID = 0;
      String sql = "Select CarID FROM Car Where Reg= ?";
      conn.setPstat(conn.getConn().prepareStatement(sql));
      conn.getPstat().setString(1, car.getReg());
      conn.setRs(conn.getPstat().executeQuery());
      conn.getRs().next();
      carID = conn.getRs().getInt("CarID");
      try {
         if (carID == 0) {
            throw new DataValidationFail("BAD CAR ID"); //Because values are pre-set in the combo box this error should not occur.
         }
      } catch (DataValidationFail e) {
         JOptionPane.showMessageDialog(null, "Bad Car ID", "Fatal Error", JOptionPane.WARNING_MESSAGE);
         return (Integer) null;
      }

      return carID;
   }

   /**
    * Method used to build a Stock object for use with the UpdateStock class and pop-out Stock modifier GUI.
    * Pulls initial information from the Stock Item combo and makes use of the String.Split() method in order to build SQL queries later.
    * This method does not make use of any SQL queries itself.
    * @return Stock Object
    */
   public static Stock buildStockForUpdate() {
      //Objects required to build
      Stock stock = new Stock();
      Car car = new Car();
      Staff staff = new Staff();
      Customer customer = new Customer();

      String stockDetails = stockItemCombo.getSelectedItem().toString(); //Get the selected item of the combo box and assign contents to a variable
      String stockDetailsArray[] = stockDetails.split(","); //Split the string into seperate parts and put them into an array.
      stock.setStockNo(Integer.parseInt(stockDetailsArray[0])); //First element will be the ID
      car.setReg(stockDetailsArray[1]); //Second element will be the Cars Reg
      car.setMake(stockDetailsArray[2]); //Thrid element will be the Cars make
      stock.setCar(car);
      //Assign the stock price from the mod field. Requires Double.parseDouble to convert.
      stock.setPrice(Double.parseDouble(modPriceField.getText()));

      String customerName = customerCombo.getSelectedItem().toString(); //Repeat same thing for Customer combo to split the information and store into an array
      String customerNameArray[] = customerName.split(" ");
      customer.setCustomerID(Integer.parseInt(customerNameArray[0]));
      customer.setFname(customerNameArray[1]);
      customer.setLname(customerNameArray[2]);

      String staffName = sellerCombo.getSelectedItem().toString();
      String staffNameArray[] = staffName.split(" ");
      staff.setStaff_id(Integer.parseInt(staffNameArray[0]));
      staff.setFirstName(staffNameArray[1]);
      staff.setLastName(staffNameArray[2]);

      stock.setCustomer(customer); //Assign the stock objects customer object
      stock.setStaff(staff); //Assign the stock objects Staff object

      stock.setStatus(statusCombo.getSelectedItem().toString()); //set the stocks status by retrriving the data from the combo box
      //stock.toString(); //Debug toString
      return stock; //Return the Stock Object
   }

   /**
    * Method that builds a Stock object from a selected item in the Stock List Table object of the GUI. 
    * Executes SQL query based on the information of the selected item in the table. Throws an SQLException error 
    * @return Stock Object 
    * @throws SQLException
    */
   public Stock buildStock() throws SQLException {

      //Create new Objects that will make up the Stock object (and the stock object that will be returned)
      Stock stock = new Stock();
      Car car = new Car();
      Customer customer = new Customer();
      Staff seller = new Staff();

      conn.setConn();
      try {
         boolean flag = getStockListTable().getSelectionModel().isSelectionEmpty(); //Make sure the table has an item selected
         if (flag == false) {
            int row = getStockListTable().getSelectedRow(); //get the selected rows index
            row = getStockListTable().getRowSorter().convertRowIndexToModel(row); //If filtering is used we have to convert this across in order for the correct row to be selected
            int col = 0; //first column of the selected row 
            String value = getStockListTable().getModel().getValueAt(row, col).toString(); //adds the first Row+Col entry into a String variable
            String sql = "Select StockNumber,Car,Customer,Price,Status,WhoSold as Seller from Stock Where StockNumber = ?"; //Setup the SQL p.statement 
            conn.setPstat(conn.getConn().prepareStatement(sql)); //Set the prepared statement to the sql variable
            conn.getPstat().setString(1, value); //Set the prepared statement to find the entry found earlier (value variable)
            conn.setRs(conn.getPstat().executeQuery()); //execute the query

            if (conn.getRs().next()) { //if anything is found through the query
               //Declare and initalise new variables to the values found in the DB through  the result set
               int stockNumber = conn.getRs().getInt("StockNumber");
               int carID = conn.getRs().getInt("Car");
               int customerID = conn.getRs().getInt("customer");
               double price = conn.getRs().getDouble("Price");
               String status = conn.getRs().getString("Status");
               int staff_ID = conn.getRs().getInt("Seller");

               //Another SQL qeury is required to find information for the car object
               sql = "Select Reg,Model,Make from Car WHERE carId = ?";
               conn.setPstat(conn.getConn().prepareStatement(sql));
               conn.getPstat().setInt(1, carID);
               conn.setRs(conn.getPstat().executeQuery());
               if (conn.getRs().next()) {
                  car.setReg(conn.getRs().getString("Reg"));
                  car.setMake(conn.getRs().getString("Model"));
                  car.setModel(conn.getRs().getString("Make"));
                  car.setCarId(carID);
               };

               //Another SQL query is required to find information for the staff object
               sql = "SELECT staff_firstname, staff_lastname from Staff where staffId = ?";
               conn.setPstat(conn.getConn().prepareStatement(sql));
               conn.getPstat().setInt(1, staff_ID);
               conn.setRs(conn.getPstat().executeQuery());
               if (conn.getRs().next()) {
                  seller.setFirstName(conn.getRs().getString("Staff_FirstName"));
                  seller.setLastName(conn.getRs().getString("Staff_LastName"));
               };
               //Another SQL query is required to find information for the customer object
               sql = "Select Customer.FirstName,Customer.Lastname from Customer where CustomerID = ?";
               conn.setPstat(conn.getConn().prepareStatement(sql));
               conn.getPstat().setInt(1, customerID);
               conn.setRs(conn.getPstat().executeQuery());
               if (conn.getRs().next()) {
                  customer.setFname(conn.getRs().getString("FirstName"));
                  customer.setLname(conn.getRs().getString("LastName"));
               };

               //System.out.println(seller.toString()); //Debug toString outputs
               //Set and assign all the objects correctly before completing the stock object for  return
               customer.setCustomerID(customerID);
               seller.setStaff_id(staff_ID);
               stock.setStatus(status);
               stock.setCar(car);
               stock.setCustomer(customer);
               stock.setStaff(seller);
               stock.setPrice(price);
               stock.setStockNo(Integer.parseInt(value));

               //System.out.println(stock.toString()); //Debug toString outputs
            };

            return stock;

         } else {
            JOptionPane.showMessageDialog(rootPane, "No Stock selected", "Error!", JOptionPane.WARNING_MESSAGE); //error message if nothing on the table was selected

         }
      } catch (NullPointerException e) {
         return null; //Return a null object if a nullpointerexception is thrown, this will be used in the button AL to end the method before the program crashes
      }
      return null; //return null which will be used in the AL of the button that calls this method to prevent the program from crashing through SQL issue.

   }

   /**
    * Method that finds any Stock item that is eligible to be invoiced (i.e.- A Stock in the DB that has the status (Sold-Not invoiced) 
    * After it has ran an SQL Prepared statement it cycles through a while loop to populate the invoice combo with the values
    * found in the SQL query.
    */
   public void populateInvoiceable() {
      invoiceableStock.removeAllElements(); //Removes all the elements to avoid duplication of elements if the method is called multiple times
      conn.setConn(); //start the connection
      String sql = "Select Stock.StockNumber,Stock.Status, Car.Reg from Stock inner join Car where Car = Car.CarId AND Status = \"Sold-Not Invoiced\"";
      try {
         // assume that all objects were all properly defined
         conn.setPstat(conn.getConn().prepareStatement(sql)); //set the prepared statement
         conn.setRs(conn.getPstat().executeQuery(sql)); //save the results into the result set
         while (conn.getRs().next()) { //loop through the result set
            //int id = rs.getInt("id");
            //list.addElement(id);
            String invoiceable = conn.getRs().getInt("StockNumber") + " " + conn.getRs().getString("Reg"); //Grab the first name from the table
            invoiceableStock.addElement(invoiceable); //add the result set data into the combo box

         }
      } catch (Exception err) {
         System.out.println(err);
      }
   }

   /**
    * Method used to Build an Invoice object for use in conjunction with the ManageInvoice class in order to generate an invoice for a customer. 
    * Called when the Generate Invoice button is clicked on the Manage Stock GUI.
    * Because it runs SQL code it throws an SQLException on query fail.
    * 
    * @param status- The status of the invoice object to be returned at the end of the method. 
    * @param method - The payment method that will be assigned to the invoice to be returned at the end of the end of the method.
    * @return Invoice Object
    * @throws SQLException
    */
   public Invoice buildInvoice(int status, String method) throws SQLException {

      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //Simple date format to format the date into the specified format
      long currentTime = System.currentTimeMillis(); //New variable to catch the current system time/date
      Date today = new Date(currentTime); //Slap the date into a variable to be later assigned into the open slot for the invoice object 
      Invoice invoice = new Invoice(); //New invoice object, this will be created and all objects within it before being returned at the end of the method
      invoice.setIssueDate(sdf.format(today)); //Set the invoice date String to the specified date object with formatting applied
      invoice.setPaymentMethod(method);
      invoice.setInvoiceStatus(status);

      //Create the objects required by the invoice object
      Customer customer = new Customer();
      Address address = new Address();
      Stock stock = new Stock();
      Car car = new Car();

      //Open Connection
      conn.setConn();
      conn.setPstat(null);

      //Get the required information to populate the customer object in order to satisfy invoice requirements
      String stockItem = invGenStockItemCombo.getSelectedItem().toString();
      String stockItemArray[] = stockItem.split(" ");
      int stockNumber = Integer.parseInt(stockItemArray[0]);
      //SQL queries to build customer
      String sql = "Select Stock.StockNumber,Stock.Customer,Stock.Price,Customer.FirstName,Customer.LastName,Customer.AddressLine1" +
         ",Customer.AddressLine2,Customer.Town,Customer.County,Customer.Eircode from Stock Inner Join Customer " +
         "Where Stock.StockNumber = ? and Customer = CustomerID";

      conn.setPstat(conn.getConn().prepareStatement(sql));
      conn.getPstat().setInt(1, stockNumber);
      conn.setRs(conn.getPstat().executeQuery());

      if (conn.getRs().next()) { //If the result set produces results the code in the if statement is ran which assigns the customer object details
         stock.setStockNo(conn.getRs().getInt("StockNumber"));
         stock.setPrice(conn.getRs().getDouble("Price"));
         customer.setCustomerID(conn.getRs().getInt("Customer"));
         customer.setFname(conn.getRs().getString("FirstName"));
         customer.setLname(conn.getRs().getString("LastName"));
         address.setAddressLn1(conn.getRs().getString("AddressLine1"));
         address.setAddressLn2(conn.getRs().getString("AddressLine2"));
         address.setTown(conn.getRs().getString("Town"));
         address.setCounty(conn.getRs().getString("County"));
         address.setEircode(conn.getRs().getString("Eircode"));
         customer.setAddress(address);
      } else {
         return null;
      }
      //SQL queries to build car
      sql = "Select CarId,Make,Model,Reg from Car where Reg= ?";
      conn.setPstat(conn.getConn().prepareStatement(sql));
      conn.getPstat().setString(1, stockItemArray[1]);
      System.out.println(stockItemArray[1]);
      conn.setRs(conn.getPstat().executeQuery());
      if (conn.getRs().next()) { //If the result set produces results the code in the if statement is ran which assigns the customer object details
         car.setCarId(conn.getRs().getInt("CarId"));
         car.setMake(conn.getRs().getString("Make"));
         car.setModel(conn.getRs().getString("Model"));
         car.setReg(conn.getRs().getString("Reg"));
         invoice.setPrice(stock.getPrice());
      } else {

         return null;
      }

      //Assign the Invoice object with the objects created in this method 
      invoice.setStock(stock);
      invoice.setCustomer(customer);
      invoice.setCar(car);
      System.out.println(invoice.toString());

      return invoice;
   }

   //Getters and Setters
   public JTable getStockListTable() {
      return stockListTable;
   }

   public void setStockListTable(JTable stockListTable) {
      this.stockListTable = stockListTable;
   }

   public static DefaultComboBoxModel getNonAssignedCars() {
      return nonAssignedCars;
   }

   public static void setNonAssignedCars(DefaultComboBoxModel nonAssignedCars) {
      ManageStockGui.nonAssignedCars = nonAssignedCars;
   }

   public static DefaultComboBoxModel getStaffList() {
      return staffList;
   }

   public static void setStaffList(DefaultComboBoxModel staffList) {
      ManageStockGui.staffList = staffList;
   }

}