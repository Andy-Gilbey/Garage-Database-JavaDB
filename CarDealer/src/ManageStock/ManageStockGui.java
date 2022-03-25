package ManageStock;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
import Connection.DbConnection;
import ErrorHandling.DataValidationFail;
import Tables.Car;
import Tables.Customer;
import Tables.Staff;
import Tables.Stock;


public class ManageStockGui extends JFrame{
	
	//Database Connection 
	static DbConnection conn = new DbConnection();

	//Table Models
	private static DefaultComboBoxModel nonAssignedCars = new DefaultComboBoxModel(); //List that is full of cars not in the stock table presently
	private static DefaultComboBoxModel currentStock = new DefaultComboBoxModel();//List that stores the current Stock items stored in DB
	private static DefaultComboBoxModel staffList = new DefaultComboBoxModel();
	private static DefaultComboBoxModel customerList = new DefaultComboBoxModel();
	//Variable Declaration
	private JPanel addCarToStockPanel;
    private JButton addToStockListBtn;
    private static JPanel bodyPanel;
    private JLabel carToAddLabel;
    private JPanel currentStockPanel;
    private static JComboBox<String> customerCombo;
    private JLabel customerLabel;
    private JLabel euroSym;
    private JButton generateInvoiceBtn;
    private JPanel generateInvoicePanel;
    private JPanel headerPanel;
    private JComboBox<String> invGenStockItemCombo;
    private static JComboBox<String> carToAddCombo;
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
    private static JComboBox<String> sellerCombo;
    private JLabel sellerLabel;
    private static JComboBox<String> statusCombo;
    private JLabel statusLabel;
    private static JComboBox<String> stockItemCombo;
    private JLabel modEuroSymb;
    private static JTextField modPriceField;
    private JLabel modPriceLabel;
    private JButton popOutModify;
    private JLabel stockItemLabel;
    private JLabel stockItemLabel2;
    private JTable stockListTable;
    private JLabel titleLabel;
    private JLabel instructionLabel;
    private JButton refreshTableBtn;
    

	
    public ManageStockGui() throws HeadlessException, SQLException {
		initalise();
		updateStockTable(stockListTable);
	}


	public void initalise() throws SQLException {

    	  headerPanel = new JPanel();
          titleLabel = new JLabel();
          bodyPanel = new JPanel();
          currentStockPanel = new JPanel();
          listScrollPane = new JScrollPane();
          stockListTable = new JTable();
          addCarToStockPanel = new JPanel();
          carToAddCombo = new JComboBox<String> (nonAssignedCars);
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
          customerCombo = new JComboBox<>(customerList);
          statusLabel = new JLabel();
          popOutModify = new JButton();
          statusCombo = new JComboBox<>();
          sellerLabel = new JLabel();
          sellerCombo = new JComboBox<>(staffList);
          stockItemCombo = new JComboBox<String> (currentStock);
          generateInvoicePanel = new JPanel();
          stockItemLabel2 = new JLabel();
          invGenStockItemCombo = new JComboBox<>();
          generateInvoiceBtn = new JButton();
          modPriceLabel = new JLabel();
          modEuroSymb = new JLabel();
          modPriceField = new JTextField();
          instructionLabel = new JLabel();
          refreshTableBtn = new JButton();

          setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
          setTitle("Manage Stock");
          getContentPane().setLayout(null);
          
          //Fonts
          Font interM36 = new Font("Inter Medium",1,36);
          Font interM14 = new Font("Inter Medium",1,14);
          //Colours
          Color purple = new Color(204,204,255);
          Color black = new Color(0,0,0);
          
          //Dimensions
          Dimension frame = new Dimension(1000, 740);
          Dimension bodyPanelSize = new Dimension(980, 610);
          
          setPreferredSize(frame); //Sets size of the frame to the dimensions set in the frame dimension object
          setResizable(false); //Disable resize window
          
          
          headerPanel.setBackground(purple);
          headerPanel.setBorder(BorderFactory.createLineBorder(black));
          headerPanel.setForeground(new Color(255, 255, 255));
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
              new Object [][] {
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null},
                  {null, null, null, null}
              },
              new String [] {
                  "Stock Number", "Car-Reg", "Price", "Status"
              }
          ) {
              boolean[] canEdit = new boolean [] {
                  false, false, false, false
              };

              public boolean isCellEditable(int rowIndex, int columnIndex) {
                  return canEdit [columnIndex];
              }
          });
          listScrollPane.setViewportView(stockListTable);

          currentStockPanel.add(listScrollPane);
          listScrollPane.setBounds(19, 23, 470, 510);

          bodyPanel.add(currentStockPanel);
          currentStockPanel.setBounds(460, 20, 500, 570);

          addCarToStockPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(black), "Add Car to Stock List"));
          addCarToStockPanel.setLayout(null);

          addCarToStockPanel.add(carToAddCombo);
          carToAddCombo.setModel(nonAssignedCars);
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
          statusCombo.setModel(new DefaultComboBoxModel<>(new String[] { "In Stock", "On Hold", "Sold-Not Invoiced","Sold" }));
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

          invGenStockItemCombo.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
          generateInvoicePanel.add(invGenStockItemCombo);
          invGenStockItemCombo.setBounds(120, 30, 140, 21);

          generateInvoiceBtn.setText("Generate Invoice");
          generateInvoiceBtn.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  generateInvoiceBtnActionPerformed(e);
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
    }



	protected void refreshTableBtnActionPerformed(ActionEvent e) throws SQLException {
		updateStockTable(stockListTable);
		
	}


	//Action Listeners
	protected void addToStockListBtnActionPerformed(ActionEvent e) throws SQLException {
		
		Stock stock = new Stock();
		InsertToStock insert = new InsertToStock();
		stock = buildStockForAdd();
		insert.stockInsert(stock, stock.getCar().getCarId());
		JOptionPane.showMessageDialog(null, "Success!" + stock.getCar().getReg() + " added to stock", "Success", JOptionPane.INFORMATION_MESSAGE);
		updateStockTable(stockListTable);
		
	}


	protected void modStockBtnActionPerformed(ActionEvent e) throws SQLException {
			Stock stock = new Stock();
			UpdateStock update = new UpdateStock();
			stock = buildStockForUpdate();
			update.stockUpdate(stock);
			JOptionPane.showMessageDialog(null, "Success!" + stock.getCar().getReg() + " stock modified", "Success", JOptionPane.INFORMATION_MESSAGE);
			updateStockTable(stockListTable);
	}


	protected void generateInvoiceBtnActionPerformed(ActionEvent e) {

		
	}
	
	protected void popOutModifyActionPerformed(ActionEvent e) throws SQLException {
		Stock stock = new Stock();
		stock = buildStock();
		if(stock != null)
		{
			ModifyPopOut popout = new ModifyPopOut(stock);
		}
	}

	
	//Methods
	public void updateStockTable(JTable table) throws SQLException {
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
			nonAssignedCars.removeAllElements();
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
	            nonAssignedCars.addElement(resultSet); //add the result set data into the combo box
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
	            resultSet = resultSet + " " + conn.getRs().getString("Model") +","; 
	            resultSet = resultSet + " " + conn.getRs().getString("Price") + ","; 
	    	    resultSet = resultSet + " " + conn.getRs().getString("Seller") + ","; 
	    	    resultSet = resultSet + " " + conn.getRs().getString("Customer") + ","; 
	    	    resultSet = resultSet + " " + conn.getRs().getString("Status"); //Grab the last name from the table
	            currentStock.addElement(resultSet); //add the result set data into the combo box
	         }
		 
	}
	
	public static void populateSeller() throws SQLException{
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
	
	public static void populateCustomer() throws SQLException{
		customerList.removeAllElements();
		conn.setConn();
		String sql = "Select FirstName,LastName from garage.Customer";
		try {
	         // assume that all objects were all properly defined
	         conn.setPstat(conn.getConn().prepareStatement(sql)); //set the prepared statement
	         conn.setRs(conn.getPstat().executeQuery(sql)); //save the results into the result set
	         while (conn.getRs().next()) { //loop through the result set
	            //int id = rs.getInt("id");
	            //list.addElement(id);
	            String customerName = conn.getRs().getString("FirstName"); //Grab the first name from the table
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
			}
			catch(NumberFormatException e) {
		        stock.setPrice(0.0);
				JOptionPane.showMessageDialog(null, "The Price Field must only accept numbers. Price reverted to 0.0", "Error", JOptionPane.WARNING_MESSAGE);

		         return null;
			}
			
			return stock;
		}
		catch(NullPointerException e)
		{
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
        	 if (carID ==0)
	             {
	            	 throw new DataValidationFail("BAD CAR ID");//Because values are pre-set in the combo box this error should not occur.
	             }
         	}
         catch(DataValidationFail e)
         {
        	 JOptionPane.showMessageDialog(null, "Bad Car ID", "Fatal Error", JOptionPane.WARNING_MESSAGE);
        	 return (Integer) null;
         }
         
		return carID;
}
	
	public static Stock buildStockForUpdate() throws SQLException{
		//Objects required to build
		Stock stock = new Stock();
		Car car = new Car();
		Staff staff = new Staff();
		Customer customer = new Customer();
		String stockDetails = stockItemCombo.getSelectedItem().toString();
		String stockDetailsArray[] = stockDetails.split(",");
		stock.setStockNo(Integer.parseInt(stockDetailsArray[0]));
		car.setReg(stockDetailsArray[1]);
		car.setMake(stockDetailsArray[2]);
		stock.setCar(car);
		stock.setPrice(Double.parseDouble(modPriceField.getText()));
		String customerName = customerCombo.getSelectedItem().toString();
		String staffName = sellerCombo.getSelectedItem().toString();
		String customerNameArray[] = customerName.split(" ");
		String staffNameArray[] = staffName.split(" ");
		customer.setFname(customerNameArray[0]);
		customer.setLname(customerNameArray[1]);
		staff.setFirstName(staffNameArray[0]);
		staff.setLastName(staffNameArray[1]);

		customer.setCustomerID(findCustomerID(customer));
		staff.setStaff_id(findStaffID(staff));
		
		stock.setCustomer(customer);
		stock.setStaff(staff);
		
		stock.setStatus(statusCombo.getSelectedItem().toString());
		
		
		return stock;
	}
	
	//Find staff ID for use within the UPDATE stock method
	public static int findStaffID(Staff staff) throws SQLException{
		int staffID;
		String sql = "Select StaffId from Staff where Staff_firstname = ? AND Staff_Lastname = ?";
	    conn.setPstat(conn.getConn().prepareStatement(sql));
	    conn.getPstat().setString(1, staff.getFirstName());
	    conn.getPstat().setString(2, staff.getLastName());
	    conn.setRs(conn.getPstat().executeQuery());
	    conn.getRs().next();
	    staffID = conn.getRs().getInt("StaffID");
         try {
        	 if (staffID ==0)
	             {
	            	 throw new DataValidationFail("BAD STAFF ID");//Because values are pre-set in the combo box this error should not occur.
	             }
         	}
         catch(DataValidationFail e)
         {
        	 JOptionPane.showMessageDialog(null, "Bad STAFF ID", "Fatal Error", JOptionPane.WARNING_MESSAGE);
        	 return (Integer) null;
         }
         
		return staffID;
	}
	
	public static int findCustomerID(Customer customer) throws SQLException{
		int customerID;
		String sql = "Select CustomerID from Customer where firstName= ? AND lastName = ? ";
	    conn.setPstat(conn.getConn().prepareStatement(sql));
	    conn.getPstat().setString(1, customer.getFname());
	    conn.getPstat().setString(2, customer.getLname());
	    conn.setRs(conn.getPstat().executeQuery());
	    conn.getRs().next();
	    customerID = conn.getRs().getInt("customerID");
         try {
        	 if (customerID ==0)
	             {
	            	 throw new DataValidationFail("BAD CUSTOMER ID");//Because values are pre-set in the combo box this error should not occur.
	             }
         	}
         catch(DataValidationFail e)
         {
        	 JOptionPane.showMessageDialog(null, "Bad CUSTOMER ID", "Fatal Error", JOptionPane.WARNING_MESSAGE);
        	 return (Integer) null;
         }
         
		return customerID;
	}
	
	public Stock buildStock() throws SQLException {
		
		Stock stock = new Stock();
		Car car = new Car();
		Customer customer = new Customer();
		Staff seller = new Staff();
		
		conn.setConn();
		try {
			  boolean flag = getStockListTable().getSelectionModel().isSelectionEmpty();
			    if (flag == false) {
			         int row = getStockListTable().getSelectedRow();
			         int col = 0;
			         String value = getStockListTable().getModel().getValueAt(row, col).toString();
			         String sql = "Select StockNumber,Car,Customer,Price,Status,WhoSold as Seller from Stock Where StockNumber = ?";
			         	conn.setPstat(conn.getConn().prepareStatement(sql));
			 	   		conn.getPstat().setString(1, value);
			 	   		conn.setRs(conn.getPstat().executeQuery());
			 	   		conn.getRs().next();
			 	   		int stockNumber =  conn.getRs().getInt("StockNumber");
			 	   		int carID =  conn.getRs().getInt("Car");
			 	   		int customerID =  conn.getRs().getInt("customer");
			 	   		double price =  conn.getRs().getDouble("Price");
			 	   		String status =  conn.getRs().getString("Status");
			 	   		int staff_ID =  conn.getRs().getInt("Seller");
			 	   		
			 	   	  sql = "Select Model,Make from Car WHERE carId = ?";
			         	conn.setPstat(conn.getConn().prepareStatement(sql));
			 	   		conn.getPstat().setInt(1, carID);
			 	   		conn.setRs(conn.getPstat().executeQuery());
			 	   		conn.getRs().next();
			 	   		car.setMake(conn.getRs().getString("Model"));
			 	   		car.setModel(conn.getRs().getString("Make"));
			 	   		car.setCarId(carID);
			 	   		customer.setCustomerID(customerID);
			 	   		seller.setStaff_id(staff_ID);
			 	   		
			 	   		stock.setCar(car);
			 	   		stock.setCustomer(customer);
			 	   		stock.setStaff(seller);
			 	   		stock.setPrice(price);
			 	   		stock.setStockNo(Integer.parseInt(value));
			 	   		
			 	   		return stock;

			      } else {
			         JOptionPane.showMessageDialog(rootPane, "No Stock selected", "Error!", JOptionPane.WARNING_MESSAGE);
			         

			      }
		}
		catch(NullPointerException e) {
			return null;
		}
		return null;
	  
	   }

	public JTable getStockListTable() {
		return stockListTable;
	}


	public void setStockListTable(JTable stockListTable) {
		this.stockListTable = stockListTable;
	}

	
}
