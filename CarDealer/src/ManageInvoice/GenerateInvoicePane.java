package ManageInvoice;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * @author Andrew Gilbey/C00263656
 */
public class GenerateInvoicePane {

   /**
    * Single method to be called within a class to make use of the customised JOption pane that is created with the method
    * 
    */

   ImageIcon paymentIcon = new ImageIcon(getClass().getResource("/Images/paymentMethod.png")); //Custom icon for the window
   Object[] paymentMethods = {
      "Cheque",
      "Credit Card",
      "Cash",
      "EFT",
      "Finance"
   }; //ComboBox with selections

   /**
    * Method that is used to display. Will display a combo box with elements containing payment method options 
    * to the screen. Will return the String value of the selection.
    * 
    * @return selection - This is the element of the combo box the user selected. example Output = Credit Card (see JOptionPane.showInputDialog for more)
    */
   public String paymentMethodPane() {
      String selection = (String) JOptionPane.showInputDialog(null, "Select a Payment method:", "Choose Payment Method",
         JOptionPane.PLAIN_MESSAGE, paymentIcon, paymentMethods, "Cheque"); //Icon used,the Object,Objects Initial Value
      System.err.println(selection);
      return selection;
   }

   public int statusPane() {
      int selectedOption = JOptionPane.showConfirmDialog(null,
         "Has this Invoice been paid?",
         "Paid?",
         JOptionPane.YES_NO_OPTION);

      return selectedOption;

   }

   /**
    * Method that used to convert an int into a boolean- used in conjunction with methods building an invoice object for generation.
    *@param selection - Integer that was generated. Parameter SHOULD be 1 or 0
    * @return status - the status returned from converting the int into a boolean
    */

   public boolean statusCheck(int selection) {

      if (selection != 0) {
         return true;
      } else {
         return false;
      }
   }

}