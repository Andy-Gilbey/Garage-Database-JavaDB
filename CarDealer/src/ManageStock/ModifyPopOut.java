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
import Tables.Stock;

public class ModifyPopOut extends JFrame {
	
	DbConnection conn = new DbConnection();
	
	private static DefaultComboBoxModel carList = new DefaultComboBoxModel(); //List that is full of cars not in the stock table presently	
	private static DefaultComboBoxModel staffList = new DefaultComboBoxModel();
	private static DefaultComboBoxModel customerList = new DefaultComboBoxModel();
	
	private static final long serialVersionUID = 6604858878767111921L;
	private JButton backBtn;
    private JComboBox<String> carCombo;
    private JLabel carLabel;
    private JComboBox<String> customerCombo;
    private JLabel customerLabel;
    private JLabel titleLabel;
    private JPanel headerPanel;
    private JPanel bodyPanel;
    private JTextField priceField;
    private JLabel priceLabel;
    private JButton saveChangesBtn;
    private JComboBox<String> sellerCombo;
    private JLabel sellerLabel;
    private JComboBox<String> statusCombo;
    private JLabel statusLabel;
    private JLabel euroSymbl;
    private JTextField stockNoField;
    private JLabel stockNoLabel;
    private JCheckBox allowRemoveChkbox;
    private JButton removeBtn;

    
  
    
    public ModifyPopOut(Stock stock) throws HeadlessException, SQLException {
		initalise();
		stockNoField.setText(Integer.toString(stock.getStockNo()));
		carCombo.setSelectedItem((stock.getCar().getReg() + " " + stock.getCar().getMake() + " " + stock.getCar().getModel()));
		sellerCombo.setSelectedItem(stock.getStaff().getFirstName() + " " + stock.getStaff().getLastName());
		priceField.setText(Double.toString(stock.getPrice()));
		customerCombo.setSelectedItem(stock.getCustomer().getFname() + " " + stock.getCustomer().getLname());
		statusCombo.setSelectedItem(stock.getStatus());
	}

	public void initalise() throws SQLException {

        headerPanel= new JPanel();
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
        carCombo = new JComboBox<>(carList);
        sellerCombo = new JComboBox<>(staffList);
        priceField = new JTextField();
        priceLabel = new JLabel();
        customerCombo = new JComboBox<>(customerList);
        statusCombo = new JComboBox<>();
        saveChangesBtn = new JButton();
        allowRemoveChkbox = new JCheckBox();
        removeBtn = new JButton();
        

        //Fonts
        Font interM14 = new Font("Inter Medium",0,14);
        Font interM36 = new Font("Inter Medium",0,36);
        
        Dimension panel = new Dimension(378,556);

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
                backBtnActionPerformed(e);
            }
        });
        bodyPanel.add(backBtn);
        backBtn.setBounds(140, 230, 90, 21);

        carCombo.setModel(carList);
        carCombo.setEditable(false);
        populateCar();
        bodyPanel.add(carCombo);
        carCombo.setBounds(120, 60, 200, 21);

        sellerCombo.setModel(staffList);
        populateSeller();
        bodyPanel.add(sellerCombo);
        sellerCombo.setBounds(120, 180, 200, 21);

        priceField.setText("0.0");
        priceField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                priceFieldActionPerformed(e);
            }
        });
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

        statusCombo.setModel(new DefaultComboBoxModel<>(new String[] { "In Stock", "On Hold", "Sold-Not Invoiced", "Sold" }));
        bodyPanel.add(statusCombo);
        statusCombo.setBounds(120, 150, 200, 21);

        saveChangesBtn.setText("Save Changes");
        saveChangesBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveChangesBtnActionPerformed(e);
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
            public void itemStateChanged(ItemEvent evt) {
                allowRemoveChkboxItemStateChanged(evt);
            }
        });
        removeBtn.setBackground(new Color(255, 255, 204));
        removeBtn.setIcon(new ImageIcon(getClass().getResource("/Images/warning.png"))); 
        removeBtn.setText("Remove");
        removeBtn.setEnabled(false);
        removeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });
        bodyPanel.add(removeBtn);
        removeBtn.setBounds(240, 230, 110, 27);
        
        pack();

    }                      

	protected void allowRemoveChkboxItemStateChanged(ItemEvent evt) {
		if (removeBtn.isEnabled() == false)
		{
			removeBtn.setEnabled(true);
		}
		else
		{
			removeBtn.setEnabled(false);
		}
		
		
	}

	protected void removeBtnActionPerformed(ActionEvent evt) {
		
		int selectedOption = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to delete this stock? this cannot be undone.", 
                "Choose", 
                JOptionPane.YES_NO_OPTION); 
				if (selectedOption == JOptionPane.YES_OPTION)
				{
					DeleteStock rmv = new DeleteStock();
					Stock stock = new Stock();
					stock.setStockNo(Integer.parseInt(stockNoField.getText()));
					rmv.stockDelte(stock);	
					dispose();
				}
		
		
	}

	protected void backBtnActionPerformed(ActionEvent e) {
		
		
	}

	protected void priceFieldActionPerformed(ActionEvent e) {
		
		
	}

	protected void saveChangesBtnActionPerformed(ActionEvent e) {
		
		
	}
	
	public void populateSeller() throws SQLException{
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
	
	public  void populateCustomer() throws SQLException{
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
	public void populateCar() throws SQLException{
		carList.removeAllElements();
		conn.setConn();
		String sql = "Select Reg, Make, Model from Car";
		try {
	         // assume that all objects were all properly defined
	         conn.setPstat(conn.getConn().prepareStatement(sql)); //set the prepared statement
	         conn.setRs(conn.getPstat().executeQuery(sql)); //save the results into the result set
	         while (conn.getRs().next()) { //loop through the result set
	            //int id = rs.getInt("id");
	            //list.addElement(id);
	            String cars = conn.getRs().getString("Reg"); //Grab the first name from the table
	            cars = cars + " " + conn.getRs().getString("Make"); 
	            cars = cars + " " + conn.getRs().getString("Model");//Grab the last name from the table
	            carList.addElement(cars); //add the result set data into the combo box
	         }
	      } catch (Exception err) {
	         System.out.println(err);
	      }
	}
	
    
    
}
