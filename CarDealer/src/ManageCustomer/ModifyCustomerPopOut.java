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
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import ErrorHandling.DataValidationFail;
import ErrorHandling.IntegrityConstraintValidation;
import Tables.Address;
import Tables.Customer;

public class ModifyCustomerPopOut extends JFrame {

   private JTextField addressLn1Field;
   private JLabel addressLn1Label;
   private JTextField addressLn2Field;
   private JLabel addressLn2Label;
   private JCheckBox allowRemoveChkbox;
   private JButton backBtn;
   private JComboBox < String > budgetCombo;
   private JLabel budgetLabel;
   private JComboBox < String > countyCombo;
   private JLabel countyLabel;
   private JTextField customerIDField;
   private JLabel customerIDLabel;
   private JTextField eircodeField;
   private JLabel eircodeLabel;
   private JTextField emailField;
   private JLabel emailLabel;
   private JTextField fNameField;
   private JLabel fnameLabel;
   private JPanel headerPanel;
   private JPanel bodyPanel;
   private JTextField lNameField;
   private JLabel lNameLabel;
   private JTextField phoneField;
   private JLabel phoneLabel;
   private JButton removeBtn;
   private JButton saveChangesBtn;
   private JLabel titleLabel;
   private JTextField townField;
   private JLabel townLabel;

   public ModifyCustomerPopOut(Customer customer) throws HeadlessException, SQLException {
      initalise();
      if(customer.getCustomerID()!=1)
      {
    	  customerIDField.setText(String.valueOf(customer.getCustomerID()));
          fNameField.setText(customer.getFname());
          lNameField.setText(customer.getLname());
          addressLn1Field.setText(customer.getAddress().getAddressLn1());
          addressLn2Field.setText(customer.getAddress().getAddressLn2());
          townField.setText(customer.getAddress().getTown());
          countyCombo.setSelectedItem(customer.getAddress().getCounty());
          eircodeField.setText(customer.getAddress().getEircode());
          phoneField.setText(customer.getPhone());
          emailField.setText(customer.getEmail());
          int budget = (int) customer.getBudget();
          switch (budget) {
          case 1000:
             budgetCombo.setSelectedIndex(0);
             break;

          case 5000:
             budgetCombo.setSelectedIndex(1);
             break;

          case 15000:
             budgetCombo.setSelectedIndex(2);
             break;

          case 20000:
             budgetCombo.setSelectedIndex(3);
             break;

          case 30000:
             budgetCombo.setSelectedIndex(4);
             break;

          case 40000:
             budgetCombo.setSelectedIndex(5);
             break;

          case 50000:
             budgetCombo.setSelectedIndex(6);
             break;

          case 75000:
             budgetCombo.setSelectedIndex(7);
             break;

          case 76000:
             budgetCombo.setSelectedIndex(8);
             break;

          default:
             budgetCombo.setSelectedIndex(0);
             break;
          }
      }
      else
      {
	       JOptionPane.showMessageDialog(rootPane, "Default customer is non-modifiable data", "Warning", JOptionPane.WARNING_MESSAGE);
    	  customerIDField.setText(String.valueOf(customer.getCustomerID()));
          fNameField.setText(customer.getFname());
          lNameField.setText(customer.getLname());
    	  fNameField.setEnabled(false);
          lNameField.setEnabled(false);
          addressLn1Field.setEnabled(false);
          addressLn2Field.setEnabled(false);
          townField.setEnabled(false);
          countyCombo.setEnabled(false);
          eircodeField.setEnabled(false);
          phoneField.setEnabled(false);
          emailField.setEnabled(false);
          budgetCombo.setEnabled(false);
          saveChangesBtn.setEnabled(false);
      }
     
   }

   public void initalise() throws SQLException {

      headerPanel = new JPanel();
      titleLabel = new JLabel();
      bodyPanel = new JPanel();
      customerIDField = new JTextField();
      removeBtn = new JButton();
      saveChangesBtn = new JButton();
      backBtn = new JButton();
      budgetLabel = new JLabel();
      allowRemoveChkbox = new JCheckBox();
      customerIDLabel = new JLabel();
      fnameLabel = new JLabel();
      lNameLabel = new JLabel();
      addressLn1Label = new JLabel();
      addressLn2Label = new JLabel();
      townLabel = new JLabel();
      countyLabel = new JLabel();
      eircodeLabel = new JLabel();
      phoneLabel = new JLabel();
      emailLabel = new JLabel();
      addressLn2Field = new JTextField();
      fNameField = new JTextField();
      phoneField = new JTextField();
      emailField = new JTextField();
      countyCombo = new JComboBox < > ();
      townField = new JTextField();
      eircodeField = new JTextField();
      lNameField = new JTextField();
      addressLn1Field = new JTextField();
      budgetCombo = new JComboBox < > ();

      //Dimensions
      Dimension frame = new Dimension(400, 580);

      //Fonts
      Font interM36 = new Font("Inter Medium", 1, 36);
      Font interM14 = new Font("Inter Medium", 0, 14);

      //Colours
      Color black = new Color(0, 0, 0);
      Color purple = new Color(204, 204, 255);
      Color yellow = new Color(255, 255, 0);

      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      getContentPane().setLayout(null);
      setTitle("Modify Customer Pop-Out");
      setPreferredSize(frame);
      setResizable(false);

      headerPanel.setBackground(purple);
      headerPanel.setBorder(BorderFactory.createLineBorder(black));
      headerPanel.setLayout(null);

      titleLabel.setFont(interM36);
      titleLabel.setText("Modify Customer");
      headerPanel.add(titleLabel);
      titleLabel.setBounds(30, 30, 320, 40);

      getContentPane().add(headerPanel);
      headerPanel.setBounds(10, 10, 380, 100);

      bodyPanel.setBorder(BorderFactory.createLineBorder(black));
      bodyPanel.setLayout(null);

      customerIDField.setEditable(false);
      customerIDField.setToolTipText("non-modifiable-cannot be changed.");
      bodyPanel.add(customerIDField);
      customerIDField.setBounds(120, 20, 110, 21);

      removeBtn.setBackground(yellow);
      removeBtn.setIcon(new ImageIcon(getClass().getResource("/Images/warning.png")));
      removeBtn.setText("Remove");
      removeBtn.setEnabled(false);
      removeBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
				removeBtnActionPerformed(e);
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (IntegrityConstraintValidation e1) {
				e1.printStackTrace();
			}
         }
      });
      bodyPanel.add(removeBtn);
      removeBtn.setBounds(240, 370, 130, 20);

      saveChangesBtn.setText("Save Changes");
      saveChangesBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
				saveChangesBtnActionPerformed(e);
			} catch (DataValidationFail e1) {
				e1.printStackTrace();
			}
         }
      });
      bodyPanel.add(saveChangesBtn);
      saveChangesBtn.setBounds(10, 370, 120, 21);

      backBtn.setText("Back");
      backBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            backBtnActionPerformed(e);
         }
      });
      bodyPanel.add(backBtn);
      backBtn.setBounds(140, 370, 90, 21);

      budgetLabel.setFont(interM14);
      budgetLabel.setText("Budget:");
      bodyPanel.add(budgetLabel);
      budgetLabel.setBounds(60, 320, 70, 20);

      allowRemoveChkbox.setText("Allow Remove");
      allowRemoveChkbox.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {
            allowRemoveChkboxItemStateChanged(e);
         }
      });
      bodyPanel.add(allowRemoveChkbox);
      allowRemoveChkbox.setBounds(240, 20, 110, 19);

      customerIDLabel.setFont(interM14);
      customerIDLabel.setText("Customer ID:");
      bodyPanel.add(customerIDLabel);
      customerIDLabel.setBounds(30, 20, 120, 20);

      fnameLabel.setFont(interM14);
      fnameLabel.setText("First Name:");
      fNameField.setTransferHandler(null); //Disable pasting into field
      bodyPanel.add(fnameLabel);
      fnameLabel.setBounds(40, 50, 90, 20);
      fNameField.addKeyListener(new KeyAdapter() {
          @Override
          public void keyTyped(KeyEvent e) {
             if (fNameField.getText().length() >= 45) // limit to 45 characters
                e.consume();
          }
       });

      lNameLabel.setFont(interM14);
      lNameLabel.setText("Last Name:");
      bodyPanel.add(lNameLabel);
      lNameField.setTransferHandler(null); //Disable pasting into field
      lNameLabel.setBounds(40, 80, 110, 20);
      lNameField.addKeyListener(new KeyAdapter() {
          @Override
          public void keyTyped(KeyEvent e) {
             if (lNameField.getText().length() >= 45) // limit to 45 characters
                e.consume();
          }
       });

      addressLn1Label.setFont(interM14);
      addressLn1Label.setText("Address Line 1:");
      bodyPanel.add(addressLn1Label);
      addressLn1Label.setBounds(10, 110, 120, 20);
      addressLn1Field.setTransferHandler(null); //Disable pasting into field
      addressLn1Field.addKeyListener(new KeyAdapter() {
          @Override
          public void keyTyped(KeyEvent e) {
             if (addressLn1Field.getText().length() >= 30) // limit to 30 characters
                e.consume();
          }
       });

      addressLn2Label.setFont(interM14);
      addressLn2Label.setText("Address Line 2:");
      bodyPanel.add(addressLn2Label);
      addressLn2Field.setTransferHandler(null); //Disable pasting into field
      addressLn2Label.setBounds(10, 140, 120, 20);
      addressLn2Field.addKeyListener(new KeyAdapter() {
          @Override
          public void keyTyped(KeyEvent e) {
             if (addressLn2Field.getText().length() >= 30) // limit to 45 characters
                e.consume();
          }
       });

      townLabel.setFont(interM14);
      townLabel.setText("Town:");
      bodyPanel.add(townLabel);
      townLabel.setBounds(75, 170, 60, 20);

      countyLabel.setFont(interM14);
      countyLabel.setText("County:");
      bodyPanel.add(countyLabel);
      countyLabel.setBounds(67, 200, 60, 20);

      eircodeLabel.setFont(interM14);
      eircodeLabel.setText("Eircode:");
      bodyPanel.add(eircodeLabel);
      eircodeLabel.setBounds(65, 230, 120, 20);

      phoneLabel.setFont(interM14);
      phoneLabel.setText("Phone:");
      bodyPanel.add(phoneLabel);
      phoneLabel.setBounds(70, 260, 60, 20);

      emailLabel.setFont(interM14);
      emailLabel.setText("Email:");
      bodyPanel.add(emailLabel);
      emailLabel.setBounds(70, 290, 60, 20);
      bodyPanel.add(addressLn2Field);
      addressLn2Field.setBounds(120, 140, 180, 21);
      bodyPanel.add(fNameField);
      fNameField.setBounds(120, 50, 130, 21);
      bodyPanel.add(phoneField);
      phoneField.setTransferHandler(null); //disable pasting into field
      phoneField.setBounds(120, 260, 130, 21);
      phoneField.addKeyListener(new KeyAdapter() {
          @Override
          public void keyTyped(KeyEvent e) {
             if (phoneField.getText().length() >= 20) // limit to 20 characters
                e.consume();
          }
       });
      bodyPanel.add(emailField);
      emailField.setTransferHandler(null); //disable pasting into field
      emailField.setBounds(120, 290, 180, 21);
      emailField.addKeyListener(new KeyAdapter() {
          @Override
          public void keyTyped(KeyEvent e) {
             if (emailField.getText().length() >= 45) // limit to 45 characters
                e.consume();
          }
       });

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
      bodyPanel.add(countyCombo);
      countyCombo.setBounds(120, 200, 110, 21);
      bodyPanel.add(townField);
      townField.setTransferHandler(null); //disable pasting into field
      townField.setBounds(120, 170, 180, 21);
      townField.addKeyListener(new KeyAdapter() {
          @Override
          public void keyTyped(KeyEvent e) {
             if (townField.getText().length() >= 40) // limit to 45 characters
                e.consume();
          }
       });

      bodyPanel.add(eircodeField);
      eircodeField.setTransferHandler(null); 
      eircodeField.setBounds(120, 230, 180, 21);
      eircodeField.addKeyListener(new KeyAdapter() {
          @Override
          public void keyTyped(KeyEvent e) {
             if (eircodeField.getText().length() >= 20) // limit to 45 characters
                e.consume();
          }
       });
      bodyPanel.add(lNameField);
      lNameField.setBounds(120, 80, 130, 21);
      bodyPanel.add(addressLn1Field);
      addressLn1Field.setBounds(120, 110, 180, 21);

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
      bodyPanel.add(budgetCombo);
      budgetCombo.setBounds(120, 320, 180, 21);

      getContentPane().add(bodyPanel);
      bodyPanel.setBounds(10, 120, 380, 410);
      setVisible(true);
      pack();
   }

   protected void removeBtnActionPerformed(ActionEvent e) throws SQLException, IntegrityConstraintValidation {
	   	  Customer customer = new Customer();
	      customer = buildCustomerDelete();
	      if (customer != null) {
	         DeleteCustomer.customerDelete(customer);
	         dispose();
	      } else {
	         JOptionPane.showMessageDialog(bodyPanel, "An Error has occured with Data Validation.", "Error", JOptionPane.ERROR_MESSAGE);
	      }

	   }
   

   protected void saveChangesBtnActionPerformed(ActionEvent e) throws DataValidationFail {
	   Customer customer = new Customer();
	   customer = buildCustomer();
	   if(customer != null) {
		   UpdateCustomer.customerUpdate(customer);
		   JOptionPane.showMessageDialog(rootPane, "Customer ID:" + customer.getCustomerID() + " modified", "Success", JOptionPane.INFORMATION_MESSAGE);
		   dispose();
	   }
	   else
	   {
	       JOptionPane.showMessageDialog(rootPane, "Data Valiadation Error occured", "Error!", JOptionPane.ERROR_MESSAGE);
	       dispose();
	   }
	   
   }

   protected void backBtnActionPerformed(ActionEvent e) {
      dispose();

   }

   protected void allowRemoveChkboxItemStateChanged(ItemEvent e) {
      if (customerIDField.getText().equals("1")) {
         JOptionPane.showMessageDialog(rootPane, "Default Customer Cannot be deleted", "Error!", JOptionPane.ERROR_MESSAGE);
      } else {
         if (removeBtn.isEnabled() == false) {
            removeBtn.setEnabled(true);

         } else {
            removeBtn.setEnabled(false);
         }

      }

   }

   public Customer buildCustomer() throws DataValidationFail {
      Customer customer = new Customer();
      Address address = new Address();
      customer.setCustomerID(Integer.parseInt(customerIDField.getText()));
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
         return customer;

      } catch (DataValidationFail e) {
         return null;
      }
   }
   
   public Customer buildCustomerDelete() {
	      Customer customer = new Customer();
	      customer.setCustomerID(Integer.parseInt(customerIDField.getText()));
	      return customer;
	   }

}