package NewCar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
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
import javax.swing.table.DefaultTableModel;
import Connection.DbConnection;
import ErrorHandling.DataValidationFail;
import ErrorHandling.IntegrityConstraintValidation;
import Tables.Car;

public class AddNewCarGui extends JFrame {



   //Database Connection
   static DbConnection conn = new DbConnection();

   //Variable Declarations
   private JPanel bodyPanel;
   private JPanel carDetailsPanel;
   private JPanel carListPanel;
   private JScrollPane carListScrollPane;
   private JTable carListtable;
   private JButton clearBtn;
   private JButton closeBtn;
   private JComboBox < String > colourCombo;
   private JTextField vinField;
   private JLabel colourLabel;
   private JPanel headerPanel;
   private JComboBox < String > makeCombo;
   private JLabel makeLabel;
   private JTextField modelField;
   private JLabel modelLabel;
   private JTextField regField;
   private JLabel regLabel;
   private JButton saveBtn;
   private JLabel titleLabel;
   private JComboBox < String > transmissionCombo;
   private JLabel transmissionLabel;
   private JLabel vinLabel;

   public AddNewCarGui() throws HeadlessException, SQLException {
      initalise();
      updateTable(carListtable);
   }

   @SuppressWarnings({
      "serial",
      "serial"
   })
   public void initalise() {
      headerPanel = new JPanel();
      titleLabel = new JLabel();
      bodyPanel = new JPanel();
      carDetailsPanel = new JPanel();
      transmissionLabel = new JLabel();
      makeCombo = new JComboBox < > ();
      makeLabel = new JLabel();
      regField = new JTextField();
      modelLabel = new JLabel();
      colourLabel = new JLabel();
      regLabel = new JLabel();
      vinLabel = new JLabel();
      transmissionCombo = new JComboBox < > ();
      vinField = new JTextField();
      modelField = new JTextField();
      colourCombo = new JComboBox < > ();
      closeBtn = new JButton();
      saveBtn = new JButton();
      clearBtn = new JButton();
      carListPanel = new JPanel();
      carListScrollPane = new JScrollPane();
      carListtable = new JTable();

      //Fonts
      Font interM18 = new Font("Inter Medium", 0, 18);
      Font interM36 = new Font("Inter Medium", 0, 36);
      //Colours
      Color black = new Color(0, 0, 0);
      Color purple = new Color(204, 204, 255);

      //Dimensions
      Dimension frame = new Dimension(602, 750);

      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      getContentPane().setLayout(null);
      setPreferredSize(frame);

      headerPanel.setBackground(purple);
      headerPanel.setBorder(BorderFactory.createLineBorder(black));
      headerPanel.setLayout(null);

      titleLabel.setFont(interM36);
      titleLabel.setText("Add New Car");
      headerPanel.add(titleLabel);
      titleLabel.setBounds(150, 30, 340, 30);

      getContentPane().add(headerPanel);
      headerPanel.setBounds(10, 0, 575, 90);

      bodyPanel.setBackground(purple);
      bodyPanel.setBorder(BorderFactory.createLineBorder(black));
      bodyPanel.setLayout(null);

      carDetailsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(black), "Car Details", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Inter Medium", 0, 12)));
      carDetailsPanel.setLayout(null);

      transmissionLabel.setFont((interM18));
      transmissionLabel.setText("Transmission:");
      carDetailsPanel.add(transmissionLabel);
      transmissionLabel.setBounds(260, 130, 210, 20);

      makeCombo.setModel(new DefaultComboBoxModel < > (new String[] {
         "Alfa Romeo",
         "Aston Martin",
         "Audi",
         "Apollo",
         "Bentley",
         "BMW",
         "Buick",
         "Cadillac",
         "Chevrolet",
         "Chrysler",
         "Citroen",
         "Dacia",
         "Daewoo",
         "Fiat",
         "Ford",
         "Honda",
         "Hummer",
         "Hyundai",
         "Jaguar",
         "Jeep",
         "KIA",
         "Lamborghini",
         "Lexus",
         "Lotus",
         "Land Rover",
         "Locus",
         "Lotec",
         "Maserati",
         "Mazda",
         "Mercedes",
         "MG",
         "Mitsubishi",
         "Nissan",
         "Opel/Vauxhall",
         "Peugeot",
         "Pontiac",
         "Porsche",
         "Puma",
         "Renault",
         "Rolls-Royce",
         "Saab",
         "Sabaru",
         "Seat",
         "Skoda",
         "Scion",
         "Suzuki",
         "Smart",
         "Tesla",
         "Toyota",
         "Volkswagen",
         "Volvo",
         "Yamaha"
      }));
      carDetailsPanel.add(makeCombo);
      makeCombo.setBounds(80, 50, 140, 21);

      makeLabel.setFont((interM18));
      makeLabel.setText("Make:");
      carDetailsPanel.add(makeLabel);
      makeLabel.setBounds(20, 50, 60, 15);
      carDetailsPanel.add(regField);
      regField.setTransferHandler(null);
      regField.setBounds(390, 90, 140, 21);
      regField.addKeyListener(new KeyAdapter() {
         public void keyTyped(KeyEvent e) {
            if (regField.getText().length() >= 45) // limit to 45 characters
               e.consume(); //method consumes this event so that it will not be processed 
         }
      });

      modelLabel.setFont((interM18));
      modelLabel.setText("Model:");
      carDetailsPanel.add(modelLabel);
      modelLabel.setBounds(320, 50, 60, 15);

      colourLabel.setFont((interM18));
      colourLabel.setText("Colour:");
      carDetailsPanel.add(colourLabel);
      colourLabel.setBounds(10, 130, 80, 15);

      regLabel.setFont((interM18));
      regLabel.setText("Registration:");
      carDetailsPanel.add(regLabel);
      regLabel.setBounds(270, 90, 120, 20);

      vinLabel.setFont((interM18));
      vinLabel.setText("VIN:");
      carDetailsPanel.add(vinLabel);
      vinLabel.setBounds(30, 90, 120, 20);

      transmissionCombo.setModel(new DefaultComboBoxModel < > (new String[] {
         "Manual",
         "Automatic",
         "CVT",
         "Tiptronic"
      }));
      carDetailsPanel.add(transmissionCombo);
      transmissionCombo.setBounds(390, 130, 140, 21);
      carDetailsPanel.add(vinField);
      vinField.setTransferHandler(null);//Prevent copy and paste into the text field
      vinField.setBounds(80, 90, 140, 21);
      vinField.addKeyListener(new KeyAdapter() {

         public void keyTyped(KeyEvent e) {
            if (vinField.getText().length() >= 17) // limit to 17 characters
               e.consume(); //method consumes this event so that it will not be processed 
         }
      });
      carDetailsPanel.add(modelField);
      modelField.setTransferHandler(null);//Prevent copy and paste into the text field
      modelField.setBounds(390, 50, 140, 21);
      modelField.addKeyListener(new KeyAdapter() {
         public void keyTyped(KeyEvent e) {
            if (modelField.getText().length() >= 45) // limit to 45 characters
               e.consume(); //method consumes this event so that it will not be processed 
         }
      });

      colourCombo.setModel(new DefaultComboBoxModel < > (new String[] {
         "Beige",
         "Black",
         "Blue",
         "Brown",
         "Custom",
         "Gold",
         "Gray",
         "Green",
         "Orange",
         "Purple",
         "Red",
         "Silver",
         "White",
         "Yellow"
      }));
      carDetailsPanel.add(colourCombo);
      colourCombo.setBounds(80, 130, 140, 21);

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
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (IntegrityConstraintValidation e1) {
				e1.printStackTrace();
			}
         }
      });
      carDetailsPanel.add(saveBtn);
      saveBtn.setBounds(130, 230, 80, 30);

      clearBtn.setText("Clear");
      clearBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            clearBtnActionPerformed(e);
         }
      });
      carDetailsPanel.add(clearBtn);
      clearBtn.setBounds(230, 230, 80, 30);

      bodyPanel.add(carDetailsPanel);
      carDetailsPanel.setBounds(10, 10, 550, 280);

      carListPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(black), "Car List"));
      carListPanel.setLayout(null);

      carListScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
      carListScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

      carListtable.setBorder(BorderFactory.createEtchedBorder());
      carListtable.setModel(new DefaultTableModel(
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
            "Reg",
            "Make",
            "Model"
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
      carListScrollPane.setViewportView(carListtable);

      carListPanel.add(carListScrollPane);
      carListScrollPane.setBounds(10, 20, 530, 260);

      bodyPanel.add(carListPanel);
      carListPanel.setBounds(10, 300, 550, 290);

      getContentPane().add(bodyPanel);
      bodyPanel.setBounds(10, 100, 575, 600);

      pack();
      setLocationRelativeTo(null);
      getContentPane().setVisible(true);
   }

   //Getters and Setters

   //Action Listeners
   protected void clearBtnActionPerformed(ActionEvent e) {
      makeCombo.setSelectedItem("Alfa Romeo");
      modelField.setText("");
      regField.setText("");
      vinField.setText("");
      transmissionCombo.setSelectedItem("Manual");
      colourCombo.setSelectedItem("Beige");

   }

   protected void saveBtnActionPerformed(ActionEvent e) throws SQLException, IntegrityConstraintValidation {
      Car car = new Car();
      car = buildCar();
      if(car != null)
      {
    	  int flag = InsertCar.validation(car);
    	  if(flag !=1)
    	  {
    		  	  InsertCar.carInsert(car);
                  updateTable(carListtable);
                  clearBtn.doClick();
    	  }
    	  else
    	  {
    		  JOptionPane.showMessageDialog(rootPane, "Integrity Of database compromised. Duplicate value of Reg or VIN found", "Data Validation Error", JOptionPane.ERROR_MESSAGE);
    	  }
    	  
        
      }
      else
      {
          JOptionPane.showMessageDialog(rootPane, "A data validation error has occured", "Data Validation Error", JOptionPane.ERROR_MESSAGE);
      }



   }

   protected void closeBtnActionPerformed(ActionEvent e) {
      this.dispose();

   }

   //Methods
   public void updateTable(JTable table) throws SQLException {
      String sql = "Select Reg, make , model from Car";
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

   public Car buildCar() {
      Car car = new Car();

      try {
         if (modelField.getText().isEmpty() || regField.getText().isBlank() || vinField.getText().isEmpty()) {
            throw new DataValidationFail("A required data Fields is blank");
         } else {
            car.setMake(makeCombo.getSelectedItem().toString());
            car.setModel(modelField.getText());
            car.setReg(regField.getText());
            car.setVin(vinField.getText());
            car.setTransmission(transmissionCombo.getSelectedItem().toString());
            car.setColour(colourCombo.getSelectedItem().toString());

         }
      } catch (DataValidationFail e) {
         JOptionPane.showMessageDialog(rootPane, "A required data fields is empty (Model,Reg or Vin).", "Data Validation Error", JOptionPane.ERROR_MESSAGE);
      }

      return car;

   }

}