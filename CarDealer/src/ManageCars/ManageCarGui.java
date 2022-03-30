package ManageCars;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;

import Connection.DbConnection;
import ErrorHandling.IntegrityConstraintValidation;
import NewCar.InsertCar;
import Tables.Car;



public class ManageCarGui extends JFrame{

	   //Database Connection
	   static DbConnection conn = new DbConnection();
	   
	private JPanel bodyPanel;
	private JPanel carDetailsPanel;
	private JPanel carListPanel;
	private JScrollPane carListScrollPane;
	private JTable carListTable;
	private JButton deleteBtn;
	private JButton closeBtn;
	private JComboBox<String> colourCombo;
	private JTextField vinField;
	private JLabel colourLabel;
	private JPanel headerPanel;
	private JButton editBtn;
	private JLabel note;
    private JLabel idLabel;
    private JTextField idField;
	private JComboBox<String> makeCombo;
	private JLabel makeLabel;
	private JTextField modelField;
	private JLabel modelLabel;
	private JButton refreshTableBtn;
	private JTextField regField;
	private JLabel regLabel;
	private JButton saveBtn;
	private JLabel titleLabel;
	private JComboBox<String> transmissionCombo;
	private JLabel transmissionLabel;
    private JCheckBox enableDeleteBox;
	private JLabel vinLabel;
	
	
	
	
	
	
	public ManageCarGui() throws HeadlessException, SQLException {
		initalise();
	}

	public void initalise() throws SQLException {
		
		  headerPanel = new JPanel();
	        titleLabel = new JLabel();
	        bodyPanel = new JPanel();
	        carListScrollPane = new JScrollPane();
	        carListTable = new JTable();
	        carDetailsPanel = new JPanel();
	        transmissionLabel = new JLabel();
	        makeCombo = new JComboBox<>();
	        makeLabel = new JLabel();
	        regField = new JTextField();
	        modelLabel = new JLabel();
	        colourLabel = new JLabel();
	        regLabel = new JLabel();
	        vinLabel = new JLabel();
	        idField = new JTextField();
	        idLabel = new JLabel();
	        transmissionCombo = new JComboBox<>();
	        vinField = new JTextField();
	        modelField = new JTextField();
	        colourCombo = new JComboBox<>();
	        closeBtn = new JButton();
	        saveBtn = new JButton();
	        enableDeleteBox = new JCheckBox();
	        deleteBtn = new JButton();
	        carListPanel = new JPanel();
	        note = new JLabel();
	        editBtn = new JButton();
	        refreshTableBtn = new JButton();
	        
	        //Colours
	        Color black = new Color(0, 0, 0);
	        Color purple = new Color(204, 204, 255);
	        
	        //Fonts
	        Font interM36B = new Font("Inter Medium", 1, 36);
	        Font interM18B= new Font("Inter Medium", 1, 18);
	        Font interM10 = new Font("Inter", 0, 10);
	        
	
	        
	        //Dimensions
	        Dimension frame = new Dimension(602, 750);
	        
	        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	        getContentPane().setLayout(null);
	        setPreferredSize(frame);
	        setResizable(false);
	        

	        headerPanel.setBackground(new Color(204, 204, 255));
	        headerPanel.setBorder(BorderFactory.createLineBorder(black));
	        headerPanel.setForeground(new Color(255, 255, 255));
	        headerPanel.setLayout(null);
	        

	        titleLabel.setFont(interM36B);
	        titleLabel.setText("Manage Cars");
	        headerPanel.add(titleLabel);
	        titleLabel.setBounds(150, 30, 340, 30);

	        getContentPane().add(headerPanel);
	        headerPanel.setBounds(10, 0, 575, 90);

	        bodyPanel.setBackground(purple);
	        bodyPanel.setBorder(BorderFactory.createLineBorder(black));
	        bodyPanel.setLayout(null);

	        carListScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	        carListScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

	        carListTable.setBorder(BorderFactory.createEtchedBorder());
	        carListTable.setModel(new DefaultTableModel(
	                new Object [][] {
	                    {null, null, null, null, null, null},
	                },
	                new String [] {
	                    "Reg", "Make", "Model", "VIN", "Transmission", "Colour"
	                }
	        ) {
	            boolean[] canEdit = new boolean [] {
	                false, false, false,false,false,false,
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
            updateTable(carListTable);
	        carListScrollPane.setViewportView(carListTable);

	        bodyPanel.add(carListScrollPane);
	        carListScrollPane.setBounds(10, 10, 560, 270);

	        carDetailsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(black), "Car Details", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Inter Medium", 0, 12))); 
	        carDetailsPanel.setLayout(null);

	        transmissionLabel.setFont(interM18B); 
	        transmissionLabel.setText("Transmission:");
	        carDetailsPanel.add(transmissionLabel);
	        transmissionLabel.setBounds(260, 130, 210, 20);

	        makeCombo.setModel(new DefaultComboBoxModel<>(new String[] { "Alfa Romeo", "Aston Martin", "Audi", "Apollo", "Bentley", "BMW", "Buick", "Cadillac", "Chevrolet", "Chrysler", "Citroen", "Daewoo", "Fiat", "Ford", "Honda", "Jaguar", "Jeep", "KIA", "Lamborghini", "Lotus", "Land Rover", "Locus", "Lotec", "Maserati", "Mazda", "Mercedes", "MG", "Mitsubishi", "Nissan", "Opel/Vauxhall", "Peugeot", "Pontiac", "Porsche", "Puma", "Rolls-Royce", "Saab", "Scion", "Suzuki", "Smart", "Tesla", "Toyota", "Volkswagen", "Volvo", "Yamaha" }));
	        carDetailsPanel.add(makeCombo);
	        makeCombo.setBounds(80, 50, 140, 21);

	        makeLabel.setFont(interM18B); 
	        makeLabel.setText("Make:");
	        carDetailsPanel.add(makeLabel);
	        makeLabel.setBounds(20, 50, 60, 15);
	        carDetailsPanel.add(regField);
	        regField.setBounds(390, 90, 140, 21);

	        modelLabel.setFont(interM18B); 
	        modelLabel.setText("Model:");
	        carDetailsPanel.add(modelLabel);
	        modelLabel.setBounds(320, 50, 60, 15);

	        colourLabel.setFont(interM18B); 
	        colourLabel.setText("Colour:");
	        carDetailsPanel.add(colourLabel);
	        colourLabel.setBounds(10, 130, 80, 15);

	        regLabel.setFont(interM18B); 
	        regLabel.setText("Registration:");
	        carDetailsPanel.add(regLabel);
	        regLabel.setBounds(270, 90, 120, 20);

	        vinLabel.setFont(interM18B); 
	        vinLabel.setText("VIN:");
	        carDetailsPanel.add(vinLabel);
	        vinLabel.setBounds(30, 90, 120, 20);

	        transmissionCombo.setModel(new DefaultComboBoxModel<>(new String[] { "Manual", "Automatic", "CVT", "Tiptronic" }));
	        carDetailsPanel.add(transmissionCombo);
	        transmissionCombo.setBounds(390, 130, 140, 21);
	        carDetailsPanel.add(vinField);
	        vinField.setBounds(80, 90, 140, 21);
	        carDetailsPanel.add(modelField);
	        modelField.setBounds(390, 50, 140, 21);

	        colourCombo.setModel(new DefaultComboBoxModel<>(new String[] { "Beige", "Black", "Blue", "Brown", "Custom", "Gold", "Gray", "Green", "Orange", "Purple", "Red", "Silver", "White", "Yellow" }));
	        carDetailsPanel.add(colourCombo);
	        colourCombo.setBounds(80, 130, 140, 21);
	        
	        enableDeleteBox.setText("Enable Delete");
	        enableDeleteBox.addItemListener(new ItemListener() {
	            public void itemStateChanged(ItemEvent e) {
	            	enableDeleteBoxItemStateChanged(e);
	            }
	         });
	        
	        carListPanel.add(enableDeleteBox);
	        enableDeleteBox.setBounds(430, 0, 110, 19);
	        
	        carListPanel.add(idField);
	        idField.setBounds(40, 150, 100, 21);

	        idLabel.setFont(interM18B); // NOI18N
	        idLabel.setText("ID:");
	        carListPanel.add(idLabel);
	        idLabel.setBounds(10, 150, 30, 20);
	        idField.setEnabled(false);
	        
	        closeBtn.setText("Close");
	        closeBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                closeBtnActionPerformed(e);
	            }
	        });
	        carDetailsPanel.add(closeBtn);
	        closeBtn.setBounds(330, 230, 80, 30);

	        saveBtn.setText("Save");
	        saveBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                try {
						saveBtnActionPerformed(e);
					} catch (SQLException | IntegrityConstraintValidation e1) {
						e1.printStackTrace();
					}
	            }
	        });
	        carDetailsPanel.add(saveBtn);
	        saveBtn.setBounds(130, 230, 80, 30);

	        deleteBtn.setText("Delete");
	        deleteBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	deleteBtnActionPerformed(e);
	            }
	        });
	        carDetailsPanel.add(deleteBtn);
	        deleteBtn.setEnabled(false);
	        deleteBtn.setBounds(230, 230, 80, 30);

	        carListPanel.setLayout(null);
	        carDetailsPanel.add(carListPanel);
	        carListPanel.setBounds(10, 20, 540, 240);

	        bodyPanel.add(carDetailsPanel);
	        carDetailsPanel.setBounds(10, 310, 560, 280);

	        note.setFont(interM10); 
	        note.setText("Select the Car you wish to modify and click the edit button to populate the menu below");
	        bodyPanel.add(note);
	        note.setBounds(60, 280, 430, 20);

	        editBtn.setFont(interM10); 
	        editBtn.setText("Edit");
	        bodyPanel.add(editBtn);
	        editBtn.setBounds(490, 280, 60, 20);
	        editBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	try {
						editBtnActionPerformed(e);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
	            }
	        });

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
	        bodyPanel.add(refreshTableBtn);
	        refreshTableBtn.setBounds(20, 280, 20, 20);

	        getContentPane().add(bodyPanel);
	        bodyPanel.setBounds(10, 100, 575, 600);

	        pack();
	        setVisible(true);
	}

	protected void enableDeleteBoxItemStateChanged(ItemEvent e) {
		   if (deleteBtn.isEnabled() == false) {
			   deleteBtn.setEnabled(true);
		      } else {
		    	  deleteBtn.setEnabled(false);
		      }
		
	}

	protected void editBtnActionPerformed(ActionEvent e) throws SQLException {
		 Car car = new Car();
		 car = buildCarEdit();
		 if(car != null)
		 {
			 setEditDetails(car);
		 }
		 else
		 {
	        JOptionPane.showMessageDialog(rootPane, "An Error has occured", "Error!", JOptionPane.ERROR_MESSAGE);
		 }
		 
		
	}

	protected void closeBtnActionPerformed(ActionEvent e) {
		dispose();
		
	}

	protected void saveBtnActionPerformed(ActionEvent e) throws SQLException, IntegrityConstraintValidation {
		Car car = new Car();
		car = buildCarSave();
		if(car != null) {
			int flag = UpdateCar.validation(car);
			if(flag !=1)
			{
				UpdateCar.carUpdate(car);
				 JOptionPane.showMessageDialog(rootPane, "Car" + car.getCarId() + " modified", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				 JOptionPane.showMessageDialog(rootPane, "Valdiation Error, VIN/Reg must be unique", "Error!", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

	protected void deleteBtnActionPerformed(ActionEvent e) {

		
	}

	protected void refreshTableBtnActionPerformed(ActionEvent e) throws SQLException {
        updateTable(carListTable);
		
	}
	
	 public void updateTable(JTable table) throws SQLException {
	      String sql = "Select Reg,Make,Model,VIN,Transmission,Colour from Car";
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
	 
	 public Car buildCarEdit() throws SQLException {
		 Car car = new Car();
		 conn.setConn();
		 boolean flag = carListTable.getSelectionModel().isSelectionEmpty();
		 if(flag == false) {
			 int row = carListTable.getSelectedRow();
	         int col = 0;
	         String value = carListTable.getModel().getValueAt(row, col).toString();
	         System.out.println(value);
	         String sql = "Select CarId,Reg,Make,Model,VIN,Transmission,Colour from Car where Reg = ?";
	         conn.setPstat(conn.getConn().prepareStatement(sql));
	         conn.getPstat().setString(1, value);
	         conn.setRs(conn.getPstat().executeQuery());
	         if (conn.getRs().next()) {
	        	 car.setCarId(conn.getRs().getInt("CarId")); 
	        	 car.setMake(conn.getRs().getString("Make")); 
	        	 car.setModel(conn.getRs().getString("Model")); 
	        	 car.setReg(conn.getRs().getString("Reg")); 
	        	 car.setVin(conn.getRs().getString("VIN"));
	        	 car.setTransmission(conn.getRs().getString("Transmission"));
	        	 car.setColour(conn.getRs().getString("Colour"));
	        	 
	        	 return car;
	         }
	         else {
	        	 
	        	 return null;
	         }
	         
		 }
		 return null;
	 }
	 
	 public void setEditDetails(Car car) {
		 idField.setText(Integer.toString(car.getCarId()));
		 makeCombo.setSelectedItem(car.getMake());
		 modelField.setText(car.getModel());
		 vinField.setText(car.getVin());
		 regField.setText(car.getReg());
		 transmissionCombo.setSelectedItem(car.getTransmission());
		 colourCombo.setSelectedItem(car.getColour()); 
	 }
	 
	 public Car buildCarSave() {
		 Car car = new Car();
		 car.setColour(colourCombo.getSelectedItem().toString());
		 car.setMake(makeCombo.getSelectedItem().toString());
		 car.setModel(modelField.getText());
		 car.setVin(vinField.getText());
		 car.setReg(regField.getText());
		 car.setTransmission(transmissionCombo.getSelectedItem().toString());
		 car.setCarId(Integer.parseInt(idField.getText()));
		 
		 return car;
	 }
}
