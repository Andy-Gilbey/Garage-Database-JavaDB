package ManageStock;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
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
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Connection.DbConnection;
import Tables.Customer;
import Tables.Staff;
import Tables.Stock;

public class ModifyStockPopOut extends JFrame {

   DbConnection conn = new DbConnection();

   private static DefaultComboBoxModel staffList = new DefaultComboBoxModel();
   private static DefaultComboBoxModel customerList = new DefaultComboBoxModel();

   private static ManageStockGui gui;

   private static final long serialVersionUID = 6604858878767111921L;
   private JButton backBtn;
   private JTextField carField;
   private JLabel carLabel;
   private JComboBox < String > customerCombo;
   private JLabel customerLabel;
   private JLabel titleLabel;
   private JPanel headerPanel;
   private JPanel bodyPanel;
   private JTextField priceField;
   private JLabel priceLabel;
   private JButton saveChangesBtn;
   private JComboBox < String > sellerCombo;
   private JLabel sellerLabel;
   private JComboBox < String > statusCombo;
   private JLabel statusLabel;
   private JLabel euroSymbl;
   private JTextField stockNoField;
   private JLabel stockNoLabel;
   private JCheckBox allowRemoveChkbox;
   private JButton removeBtn;

   public ModifyStockPopOut(Stock stock, ManageStockGui parent) throws HeadlessException, SQLException {
      initalise();

      stockNoField.setText(Integer.toString(stock.getStockNo()));

      String carDetails;
      carDetails = stock.getCar().getReg() + " ";
      carDetails = carDetails + stock.getCar().getMake() + " ";
      carDetails = carDetails + stock.getCar().getModel();
      carField.setText(carDetails);

      String sellerDetails;
      sellerDetails = stock.getStaff().getFirstName() + " ";
      sellerDetails = sellerDetails + stock.getStaff().getLastName();
      sellerCombo.setSelectedItem(sellerDetails);
      System.out.println(sellerDetails);

      priceField.setText(Double.toString(stock.getPrice()));
      customerCombo.setSelectedItem(stock.getCustomer().getFname() + " " + stock.getCustomer().getLname());
      statusCombo.setSelectedItem(stock.getStatus());
      gui = parent;
   }

   public void initalise() throws SQLException {

      headerPanel = new JPanel();
      titleLabel = new JLabel();
      bodyPanel = new JPanel();
      sellerLabel = new JLabel();
      stockNoField = new JTextField();
      stockNoLabel = new JLabel();
      carLabel = new JLabel();
      customerLabel = new JLabel();
      euroSymbl = new JLabel();
      statusLabel = new JLabel();
      backBtn = new JButton();
      carField = new JTextField();
      sellerCombo = new JComboBox < > (staffList);
      priceField = new JTextField();
      priceLabel = new JLabel();
      customerCombo = new JComboBox < > (customerList);
      statusCombo = new JComboBox < > ();
      saveChangesBtn = new JButton();
      allowRemoveChkbox = new JCheckBox();
      removeBtn = new JButton();

      //Fonts
      Font interM14 = new Font("Inter Medium", 0, 14);
      Font interM36 = new Font("Inter Medium", 0, 36);

      Dimension panel = new Dimension(378, 556);

      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      getContentPane().setLayout(null);
      setTitle("Modify Stock Pop-out Card");
      setVisible(true);
      setPreferredSize(panel);

      headerPanel.setBackground(new Color(204, 204, 255));
      headerPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
      headerPanel.setLayout(null);

      titleLabel.setFont(interM36);
      titleLabel.setText("Modify Stock");
      headerPanel.add(titleLabel);
      titleLabel.setBounds(60, 30, 320, 40);

      getContentPane().add(headerPanel);
      headerPanel.setBounds(10, 10, 360, 100);

      bodyPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
      bodyPanel.setLayout(null);

      sellerLabel.setFont(interM14);
      sellerLabel.setText("Seller:");
      bodyPanel.add(sellerLabel);
      sellerLabel.setBounds(70, 180, 50, 20);

      stockNoField.setEditable(false);
      stockNoField.setToolTipText("non-modifiable-cannot be changed.");
      bodyPanel.add(stockNoField);
      stockNoField.setBounds(120, 20, 110, 21);

      stockNoLabel.setFont(interM14);
      stockNoLabel.setText("Stock Number:");
      bodyPanel.add(stockNoLabel);
      stockNoLabel.setBounds(10, 20, 120, 20);

      carLabel.setFont(interM14);
      carLabel.setText("Car:");
      bodyPanel.add(carLabel);
      carLabel.setBounds(80, 60, 40, 20);

      customerLabel.setFont(interM14);
      customerLabel.setText("Customer:");
      bodyPanel.add(customerLabel);
      customerLabel.setBounds(40, 90, 80, 20);

      euroSymbl.setFont(interM14);
      euroSymbl.setText("€");
      bodyPanel.add(euroSymbl);
      euroSymbl.setBounds(110, 120, 10, 20);

      statusLabel.setFont(interM14);
      statusLabel.setText("Status:");
      bodyPanel.add(statusLabel);
      statusLabel.setBounds(60, 150, 60, 20);

      backBtn.setText("Back");
      backBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               backBtnActionPerformed(e);
            } catch (SQLException e1) {
               e1.printStackTrace();
            }
         }
      });
      bodyPanel.add(backBtn);
      backBtn.setBounds(140, 230, 90, 21);

      bodyPanel.add(carField);
      carField.setBounds(120, 60, 200, 21);
      carField.setEditable(false);

      sellerCombo.setModel(staffList);
      populateSeller();
      bodyPanel.add(sellerCombo);
      sellerCombo.setBounds(120, 180, 200, 21);

      priceField.setText("0.0");

      bodyPanel.add(priceField);
      priceField.setBounds(120, 120, 200, 21);

      priceLabel.setFont(interM14);
      priceLabel.setText("Price:");
      bodyPanel.add(priceLabel);
      priceLabel.setBounds(70, 120, 50, 20);

      customerCombo.setModel(customerList);
      populateCustomer();
      bodyPanel.add(customerCombo);
      customerCombo.setBounds(120, 90, 200, 21);

      statusCombo.setModel(new DefaultComboBoxModel < > (new String[] {
         "In Stock",
         "On Hold",
         "Sold-Not Invoiced",
         "Sold"
      }));
      bodyPanel.add(statusCombo);
      statusCombo.setBounds(120, 150, 200, 21);

      saveChangesBtn.setText("Save Changes");
      saveChangesBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               saveChangesBtnActionPerformed(e);
            } catch (SQLException e1) {
               e1.printStackTrace();
            }
         }
      });
      bodyPanel.add(saveChangesBtn);
      saveChangesBtn.setBounds(10, 230, 120, 21);

      getContentPane().add(bodyPanel);
      bodyPanel.setBounds(10, 120, 360, 280);

      allowRemoveChkbox.setText("Allow Remove");
      bodyPanel.add(allowRemoveChkbox);
      allowRemoveChkbox.setBounds(240, 20, 110, 19);
      allowRemoveChkbox.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {
            allowRemoveChkboxItemStateChanged(e);
         }
      });
      removeBtn.setBackground(new Color(255, 255, 204));
      removeBtn.setIcon(new ImageIcon(getClass().getResource("/Images/warning.png")));
      removeBtn.setText("Remove");
      removeBtn.setEnabled(false);
      removeBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               removeBtnActionPerformed(e);
            } catch (SQLException e1) {
               e1.printStackTrace();
            }
         }
      });
      bodyPanel.add(removeBtn);
      removeBtn.setBounds(240, 230, 110, 27);

      pack();
      setLocationRelativeTo(null);
   }

   protected void allowRemoveChkboxItemStateChanged(ItemEvent e) {
      if (removeBtn.isEnabled() == false) {
         removeBtn.setEnabled(true);
      } else {
         removeBtn.setEnabled(false);
      }

   }

   protected void removeBtnActionPerformed(ActionEvent e) throws SQLException {

      int selectedOption = JOptionPane.showConfirmDialog(bodyPanel,
         "Are you sure you want to delete this stock? this cannot be undone.",
         "Choose",
         JOptionPane.YES_NO_OPTION);
      if (selectedOption == JOptionPane.YES_OPTION) {
         DeleteStock rmv = new DeleteStock();
         Stock stock = new Stock();
         if (stock != null) {
            stock.setStockNo(Integer.parseInt(stockNoField.getText()));
            rmv.stockDelte(stock);
            dispose();
            ManageStockGui.populateNonStock();
            ManageStockGui.updateStockTable(gui.getStockListTable());
            ManageStockGui.populateCurrentStock();
         } else {
            JOptionPane.showMessageDialog(null, "An Error has occured. No Data has been deleted", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
         }

      }
      conn.closeAll();

   }

   protected void backBtnActionPerformed(ActionEvent e) throws SQLException {
      dispose();
      ManageStockGui.populateNonStock();
      ManageStockGui.updateStockTable(gui.getStockListTable());
      ManageStockGui.populateCurrentStock();
      conn.closeAll();

   }

   protected void saveChangesBtnActionPerformed(ActionEvent e) throws SQLException {
      Stock stock = new Stock();
      UpdateStock update = new UpdateStock();
      stock = buildStock();
      if (stock != null) {
         update.stockUpdate(stock);
         JOptionPane.showMessageDialog(null, "Stock " + stock.getStockNo() + " saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
         ManageStockGui.populateNonStock();
         ManageStockGui.updateStockTable(gui.getStockListTable());
         ManageStockGui.populateCurrentStock();
         dispose();
      } else {
         JOptionPane.showMessageDialog(null, "An Error has occured. No Data changed", "Error", JOptionPane.ERROR_MESSAGE);
         dispose();
      }

      conn.closeAll();

   }

   public void populateSeller() throws SQLException {
      staffList.removeAllElements();
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
            staffList.addElement(staffName); //add the result set data into the combo box
         }
      } catch (Exception err) {
         System.out.println(err);
      }
   }

   public void populateCustomer() throws SQLException {
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

   public Stock buildStock() throws SQLException {
      Stock stock = new Stock();
      Customer customer = new Customer();
      Staff seller = new Staff();

      stock.setStockNo(Integer.parseInt(stockNoField.getText()));
      if (statusCombo.getSelectedItem().toString().isEmpty()) {
         stock.setStatus("In Stock");
         JOptionPane.showMessageDialog(bodyPanel, "Status Blank. Default value assigned", "Information", JOptionPane.INFORMATION_MESSAGE);
      } else {
         stock.setStatus(statusCombo.getSelectedItem().toString());
      }

      //Set price field - only numerical values are accepted
      try {
         stock.setPrice(Double.parseDouble(priceField.getText()));
      } catch (NumberFormatException e) {
         stock.setPrice(0.0);
         JOptionPane.showMessageDialog(bodyPanel, "The Price Field must only accept numbers. Price reverted to 0.0", "Error", JOptionPane.ERROR_MESSAGE);

         return null;
      }

      //Setout Customer
      String customerName = customerCombo.getSelectedItem().toString();
      String customerNameArray[] = customerName.split(" ");
      customer.setCustomerID(Integer.parseInt(customerNameArray[0]));
      customer.setFname(customerNameArray[1]);
      customer.setLname(customerNameArray[2]);
      //System.out.println(customer.toString()); 
      stock.setCustomer(customer);

      //Setout Seller/Staff
      String staffName = sellerCombo.getSelectedItem().toString();
      String staffNameArray[] = staffName.split(" ");
      seller.setStaff_id(Integer.parseInt(staffNameArray[0]));
      seller.setFirstName(staffNameArray[1]);
      seller.setLastName(staffNameArray[2]);
      stock.setStaff(seller);
      //System.out.println(stock.toString());

      return stock;
   }

}