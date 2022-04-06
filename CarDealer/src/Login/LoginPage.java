package Login;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import Administrator.adminGUI;
import Connection.DbConnection;
import LandingGUI.LandingPage;

/**
 * @author Andrew Gilbey/C00263656
 *
 */
public class LoginPage {

   //Variable Declaration
   private JButton helpBtn;
   private JPanel panel;
   private JLabel passLabel;
   private JPasswordField passwordField;
   private JButton submitBtn;
   private JTextField userField;
   private JLabel userLabel;
   private JLabel statusLabel;
   private Canvas connectionLight;
   private DbConnection conn = new DbConnection();

   //Constructor
   /**
    *  Constructor for the login panel, this is the only gui class in the program that does not extend JFrame and instead initialises the program through 
    *  the constructor instead of a separate initialise method. 
    */
   public LoginPage() {

      Font inter18 = new Font("Inter", 0, 18);
      panel = new JPanel();
      passLabel = new JLabel();
      userField = new JTextField();
      userLabel = new JLabel();
      passwordField = new JPasswordField();
      helpBtn = new JButton();
      statusLabel = new JLabel();
      submitBtn = new JButton();
      connectionLight = new Canvas();

      JFrame frame = new JFrame("Larceny Motors [Login]");
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame.setSize(545, 200);
      frame.setLocationRelativeTo(null); //Center on screen
      frame.setResizable(false); //Disable resize window

      panel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
      panel.setLayout(null);

      panel.add(connectionLight);
      connectionLight.setBounds(10, 145, 30, 20);
      connectionLight.setBackground(new Color(255, 0, 0));

      passLabel.setFont(inter18);
      passLabel.setText("Password:");
      panel.add(passLabel);
      passLabel.setBounds(130, 70, 120, 18);
      panel.add(userField);
      userField.setBounds(230, 30, 150, 23);

      userLabel.setFont(inter18);
      userLabel.setText("Username:");
      panel.add(userLabel);
      userLabel.setBounds(130, 30, 120, 18);
      panel.add(passwordField);
      passwordField.setBounds(230, 70, 150, 23);

      helpBtn.setText("?");
      helpBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(panel, "Login with your Username and Password. If you have forgotten either, " +
               "please contact the system administrator.");
         }
      });

      panel.add(helpBtn);
      helpBtn.setBounds(300, 110, 80, 30);

      statusLabel.setText("Status");
      panel.add(statusLabel);
      statusLabel.setBounds(10, 125, 150, 15);

      submitBtn.setText("Submit");
      submitBtn.addActionListener(new ActionListener() { //Listener add to the submit button
         public void actionPerformed(ActionEvent e) {
            System.out.println("Working");
            try {
               conn.setConn();
               String username = userField.getText();
               @SuppressWarnings("deprecation")
               String password = passwordField.getText();
               int level;

               String sql = "select * from Users where Username='" + username + "' and Password='" + password + "'";
               conn.setPstat(conn.getConn().prepareStatement(sql));
               conn.setRs(conn.getPstat().executeQuery(sql));
               if (conn.getRs().next()) {
                  statusLabel.setText("Successful");
                  level = conn.getRs().getInt("Level");
                  if (level == 0) {
                     JOptionPane.showMessageDialog(panel, "You are about to log in with Administrator Access. Any changes made will be permanent." +
                        "\nDO NOT leave your work-station while logged into this account", "WARNING", JOptionPane.WARNING_MESSAGE);
                     adminGUI gui = new adminGUI(username);
                     gui.setVisible(true);
                  }
                  if (level == 1) {
                      JOptionPane.showMessageDialog(panel, "Log in successful", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                      LandingPage gui = new LandingPage(username);
                      gui.setVisible(true);
                   }
                 

                  frame.dispose(); //close login

               } else {
                  userField.setText("");
                  passwordField.setText("");
                  JOptionPane.showMessageDialog(panel, "Wrong password or username entered", "WARNING", JOptionPane.WARNING_MESSAGE);
               }
               conn.getConn().close();
            } catch (SQLException e1) {
               System.out.println(e1.getMessage());
            } //end error handling
         }

      });
      panel.add(submitBtn);
      submitBtn.setBounds(220, 110, 80, 30);

      frame.add(panel);
      frame.setVisible(true);
   }
   //Checks the connection to the database - if one is established the canvas will turn from red to green to indicate there is a connection
   /**
    *  This method tests the connection to the database and alters the colour of the tiny canvas from red to green 
    *  (unless there is no connection to the DB in which case the canvas stays red)
    */
   public void checkConnection() {
      conn.setConn();
      try {
         if (conn.getConn() != null || !conn.getConn().isClosed() == true) {
            connectionLight.setBackground(new Color(0, 255, 0));
            System.out.println("Connection Established");
            conn.getConn().close();
         }
      } catch (SQLException ignored) {}

   }

}