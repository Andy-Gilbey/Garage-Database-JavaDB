package LandingGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import ManageCars.ManageCarGui;
import ManageCustomer.ManageCustomerGui;
import ManageInvoice.ManageInvoiceGui;
import ManageStock.ManageStockGui;
import NewCar.AddNewCarGui;

/**
 * @author Andrew Gilbey/C00263656
 */
public class LandingPage extends JFrame {

   private static final long serialVersionUID = -3114996622877779979L;
   private JButton addNewStockBtn;
   private JPanel bodyPanel;
   private JMenu fileMenu;
   private JLabel getStartedLabel;
   private JPanel getStartedPanel;
   private JPanel headerPanel;
   private JMenu helpMenu;
   private JMenuItem menuManageInvoices;
   private JMenuItem menuSettings;
   private JMenuItem menuLogOut;
   private JLabel liabilityLabel;
   private JLabel loggedInAsLabel;
   private JMenuItem menuManageCars;
   private JLabel logoLabel;
   private JButton manageCustomersBtn;
   private JButton manageInvoicesBtn;
   private JButton manageStockBtn;
   private JMenuItem menuAddNewCar;
   private JMenuBar menuBar;
   private JMenuItem menuManageCustomers;
   private JMenuItem menuManageStock;
   private JLabel quickBtnsShortCutsLabel;
   private JMenu quitMenu;
   private JLabel shortcut1;
   private JLabel shortcut2;
   private JLabel shortcut3;
   private JLabel shortcut4;
   private JLabel sloganLabel;
   private JLabel usernameTag;

   /**
    * Constructor, takes in String value (the username of the user) to display on the gui screen.
    * Builds the gui componenets calling the initalise method
    * @param username - The username of the user that was passed in through the login panel.
    * @throws HeadlessException
    */
   public LandingPage(String username) throws HeadlessException {
      initialise();
      usernameTag.setText(username);
   }

   /**
    * The initalise method which is called in the constructor. builds the gui
    */
   public void initialise() {

      headerPanel = new JPanel();
      sloganLabel = new JLabel();
      logoLabel = new JLabel();
      liabilityLabel = new JLabel();
      usernameTag = new JLabel();
      loggedInAsLabel = new JLabel();
      bodyPanel = new JPanel();
      getStartedPanel = new JPanel();
      getStartedLabel = new JLabel();
      manageStockBtn = new JButton();
      manageInvoicesBtn = new JButton();
      shortcut2 = new JLabel();
      shortcut3 = new JLabel();
      menuManageCars = new JMenuItem();
      addNewStockBtn = new JButton();
      manageCustomersBtn = new JButton();
      shortcut4 = new JLabel();
      shortcut1 = new JLabel();
      quickBtnsShortCutsLabel = new JLabel();
      menuBar = new JMenuBar();
      fileMenu = new JMenu();
      menuAddNewCar = new JMenuItem();
      menuManageStock = new JMenuItem();
      menuManageCustomers = new JMenuItem();
      menuManageInvoices = new JMenuItem();
      helpMenu = new JMenu();
      menuSettings = new JMenuItem();
      quitMenu = new JMenu();
      menuLogOut = new JMenuItem();

      //Font Setup
      Font interM18 = new Font("Inter Medium", 0, 18);
      Font interM14 = new Font("Inter Medium", 0, 14);
      Font inter14 = new Font("Inter", 0, 14);
      Font daniel36 = new Font("Daniel", 1, 36);
      //Colour Setup
      Color purple = new Color(204, 204, 255);
      Color black = new Color(0, 0, 0);
      //Dimensions
      Dimension frame = new Dimension(1010, 791);

      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      setTitle("Larceny Motors");
      getContentPane().setLayout(null);
      setPreferredSize(frame);
      setResizable(false);

      headerPanel.setBackground(purple);
      headerPanel.setBorder(BorderFactory.createLineBorder(black));
      headerPanel.setLayout(null);

      sloganLabel.setFont(interM18);
      sloganLabel.setText("Where every car is a steal....");
      headerPanel.add(sloganLabel);
      sloganLabel.setBounds(290, 80, 360, 30);

      logoLabel.setFont(daniel36);
      logoLabel.setIcon(new ImageIcon(getClass().getResource("/Images/logo.png")));
      logoLabel.setText("Larceny Motors");
      headerPanel.add(logoLabel);
      logoLabel.setBounds(0, 0, 440, 130);

      liabilityLabel.setText("Price...not actually stolen");
      headerPanel.add(liabilityLabel);
      liabilityLabel.setBounds(300, 110, 270, 15);

      usernameTag.setFont(interM14);
      usernameTag.setText("Username");
      headerPanel.add(usernameTag);
      usernameTag.setBounds(800, 60, 170, 20);

      loggedInAsLabel.setFont(interM14);
      loggedInAsLabel.setText("Logged in as:");
      headerPanel.add(loggedInAsLabel);
      loggedInAsLabel.setBounds(710, 60, 170, 18);

      getContentPane().add(headerPanel);
      headerPanel.setBounds(0, 0, 1000, 130);

      bodyPanel.setBorder(BorderFactory.createLineBorder(black));
      bodyPanel.setLayout(null);

      getStartedPanel.setBorder(BorderFactory.createLineBorder(black));
      getStartedPanel.setLayout(null);

      getStartedLabel.setFont(inter14);
      getStartedLabel.setText("Get started...");
      getStartedPanel.add(getStartedLabel);
      getStartedLabel.setBounds(10, 10, 140, 18);

      manageStockBtn.setText("Manage Stock");
      manageStockBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               menuManageStockActionPerformed(e);
            } catch (HeadlessException | SQLException e1) {
               e1.printStackTrace();
            }
         }
      });
      getStartedPanel.add(manageStockBtn);
      manageStockBtn.setBounds(20, 120, 150, 21);

      manageInvoicesBtn.setText("Manage Invoices");
      manageInvoicesBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               menuManageInvoicesActionPerformed(e);
            } catch (SQLException e1) {
               e1.printStackTrace();
            }
         }
      });
      getStartedPanel.add(manageInvoicesBtn);
      manageInvoicesBtn.setBounds(300, 120, 150, 21);

      shortcut2.setFont(inter14);
      shortcut2.setText("ALT + C");
      getStartedPanel.add(shortcut2);
      shortcut2.setBounds(180, 120, 110, 20);

      shortcut3.setFont(inter14);
      shortcut3.setText("SHIFT + C");
      getStartedPanel.add(shortcut3);
      shortcut3.setBounds(460, 80, 110, 20);

      addNewStockBtn.setText("Add New Car");
      addNewStockBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               menuAddNewStockActionPerformed(e);
            } catch (HeadlessException | SQLException e1) {
               e1.printStackTrace();
            }
         }
      });

      menuAddNewCar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               menuAddNewStockActionPerformed(e);
            } catch (HeadlessException e1) {
               e1.printStackTrace();
            } catch (SQLException e1) {
               e1.printStackTrace();
            }
         }
      });
      getStartedPanel.add(addNewStockBtn);
      addNewStockBtn.setBounds(20, 80, 150, 21);

      manageCustomersBtn.setText("Manage Customers");
      manageCustomersBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            menuManageCustomersActionPerformed(e);
         }
      });
      menuManageCustomers.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            menuManageCustomersActionPerformed(e);
         }
      });

      getStartedPanel.add(manageCustomersBtn);
      manageCustomersBtn.setBounds(300, 80, 150, 21);

      shortcut4.setFont(inter14);
      shortcut4.setText("ALT + I");
      getStartedPanel.add(shortcut4);
      shortcut4.setBounds(460, 120, 110, 20);

      shortcut1.setFont(inter14);
      shortcut1.setText("ALT + N");
      getStartedPanel.add(shortcut1);
      shortcut1.setBounds(180, 80, 110, 20);

      quickBtnsShortCutsLabel.setFont(inter14);
      quickBtnsShortCutsLabel.setText("Quick Buttons & Short Cuts");
      getStartedPanel.add(quickBtnsShortCutsLabel);
      quickBtnsShortCutsLabel.setBounds(10, 40, 250, 15);

      bodyPanel.add(getStartedPanel);
      getStartedPanel.setBounds(10, 140, 980, 200);

      getContentPane().add(bodyPanel);
      bodyPanel.setBounds(0, 0, 1000, 740);

      fileMenu.setText("File");

      menuAddNewCar.setText("Add New Car");
      menuAddNewCar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_DOWN_MASK));
      fileMenu.add(menuAddNewCar);

      menuManageStock.setText("Manage Stock");
      menuManageStock.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
      menuManageStock.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               menuManageStockActionPerformed(e);
            } catch (HeadlessException e1) {
               e1.printStackTrace();
            } catch (SQLException e1) {
               e1.printStackTrace();
            }
         }
      });

      menuManageCars.setText("Manage Cars");
      menuManageCars.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.ALT_DOWN_MASK));
      menuManageCars.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               menuManageCarsActionPerformed(e);
            } catch (HeadlessException e1) {
               e1.printStackTrace();
            } catch (SQLException e1) {
               e1.printStackTrace();
            }
         }
      });
      fileMenu.add(menuManageCars);

      fileMenu.add(menuManageStock);

      menuManageCustomers.setText("Manage Customers");
      menuManageCustomers.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.SHIFT_DOWN_MASK));
      fileMenu.add(menuManageCustomers);

      menuManageInvoices.setText("Manage Invoices");
      menuManageInvoices.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_DOWN_MASK));
      menuManageInvoices.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               menuManageInvoicesActionPerformed(e);
            } catch (HeadlessException e1) {
               e1.printStackTrace();
            } catch (SQLException e1) {
               e1.printStackTrace();
            }
         }
      });
      fileMenu.add(menuManageInvoices);

      menuBar.add(fileMenu);

      helpMenu.setText("Help");

      menuSettings.setText("Settings");
      helpMenu.add(menuSettings);

      //menuBar.add(helpMenu);

      quitMenu.setText("Quit");

      menuLogOut.setText("Log Out");
      quitMenu.add(menuLogOut);

      menuBar.add(quitMenu);

      setJMenuBar(menuBar);

      pack();
      setLocationRelativeTo(null);
   }

   //Action Listeners for Buttons

   protected void menuManageInvoicesActionPerformed(ActionEvent e) throws SQLException {
      ManageInvoiceGui mgeInv = new ManageInvoiceGui();

   }

   protected void menuManageCarsActionPerformed(ActionEvent e) throws HeadlessException, SQLException {
      ManageCarGui mgecar = new ManageCarGui();
   }

   protected void menuManageCustomersActionPerformed(ActionEvent e) {
      ManageCustomerGui manageCust = new ManageCustomerGui();
      manageCust.setVisible(true);

   }

   protected void menuManageStockActionPerformed(ActionEvent e) throws HeadlessException, SQLException {
      ManageStockGui manageStock = new ManageStockGui();
      manageStock.setVisible(true);
   }

   private void menuAddNewStockActionPerformed(ActionEvent e) throws HeadlessException, SQLException {
      AddNewCarGui addNewCar = new AddNewCarGui();
      addNewCar.setVisible(true);
   }

}