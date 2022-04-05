package ManageInvoice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import Tables.Invoice;

/**
 * @author Andrew Gilbey/C00263656
 *
 */
public class InvoiceOutput extends JFrame {

   private JLabel addressLn1Tag;
   private JLabel addressLn2Tag;
   private JLabel billToLabel;
   private JLabel companyLogo;
   private JLabel companyadd1;
   private JLabel companyadd2;
   private JLabel companyadd3;
   private JLabel companyadd4;
   private JLabel countyTag;
   private JPanel customerAddPanel;
   private JLabel customerNameTag;
   private JLabel dueLabel;
   private JLabel dueTag;
   private JLabel eircodeTag;
   private JPanel headerPanel;
   private JLabel invoiceNoField;
   private JLabel invoiceNoLabel;
   private JLabel issueDateField;
   private JLabel issueDateLabel;
   private JPanel leftBtmPanel;
   private JLabel paymentMethdLabel;
   private JLabel paymentMethodTag;
   private JLabel priceLabel;
   private JLabel priceTag;
   private JPanel rightBtmPanel;
   private JPanel stockPanel;
   private JTable stockTable;
   private JScrollPane tableScroll;
   private JLabel townTag;
   private JLabel vatLabel;
   private JLabel vatTag;
   private JLabel vrtLabel;

   /**
    * Output Invoice method takes in an Invoice object and then populates fields and labels on the GUI 
    * in order to display an invoice,
    * 
    * @param invoice - An invoice object, created in another class
    * @throws HeadlessException - Thrown when code that is dependent on a keyboard, 
    * display, or mouse is called in an environment that does not support a keyboard, display, or mouse.
    */
   public void outputInvoice(Invoice invoice) throws HeadlessException {
      initalise();
      invoiceNoField.setText(String.valueOf(invoice.getInvoiceNo()));
      issueDateField.setText(invoice.getIssueDate());
      customerNameTag.setText(invoice.getCustomer().getFname() + " " + invoice.getCustomer().getLname());
      addressLn1Tag.setText(invoice.getCustomer().getAddress().getAddressLn1());
      addressLn2Tag.setText(invoice.getCustomer().getAddress().getAddressLn2());
      townTag.setText(invoice.getCustomer().getAddress().getTown());
      countyTag.setText(invoice.getCustomer().getAddress().getCounty());
      eircodeTag.setText(invoice.getCustomer().getAddress().getEircode());

      DefaultTableModel model = (DefaultTableModel) stockTable.getModel();
      Object[] row = {
         invoice.getCar().getReg(),
         invoice.getCar().getMake(),
         invoice.getCar().getModel(),
         invoice.getPrice()
      };
      model.addRow(row);

      paymentMethodTag.setText(invoice.getPaymentMethod());
      priceTag.setText(String.valueOf(invoice.getPrice()));
      vatTag.setText(String.valueOf(invoice.getVAT()));
      dueTag.setText(String.valueOf(invoice.getTotalCashPrice()));

   }

   public void initalise() {

      headerPanel = new JPanel();
      companyLogo = new JLabel();
      companyadd4 = new JLabel();
      companyadd1 = new JLabel();
      companyadd2 = new JLabel();
      companyadd3 = new JLabel();
      issueDateLabel = new JLabel();
      invoiceNoField = new JLabel();
      invoiceNoLabel = new JLabel();
      issueDateField = new JLabel();
      stockPanel = new JPanel();
      tableScroll = new JScrollPane();
      stockTable = new JTable();
      customerAddPanel = new JPanel();
      billToLabel = new JLabel();
      addressLn2Tag = new JLabel();
      addressLn1Tag = new JLabel();
      townTag = new JLabel();
      countyTag = new JLabel();
      customerNameTag = new JLabel();
      eircodeTag = new JLabel();
      leftBtmPanel = new JPanel();
      paymentMethdLabel = new JLabel();
      paymentMethodTag = new JLabel();
      rightBtmPanel = new JPanel();
      vrtLabel = new JLabel();
      priceLabel = new JLabel();
      vatLabel = new JLabel();
      dueLabel = new JLabel();
      dueTag = new JLabel();
      priceTag = new JLabel();
      vatTag = new JLabel();

      //Colours
      Color black = new Color(0, 0, 0);

      //Dimensions
      Dimension frame = new Dimension(600, 600);

      //Fonts
      Font inter12b = new Font("Inter", 1, 12);

      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      getContentPane().setLayout(null);
      setPreferredSize(frame);

      headerPanel.setBorder(BorderFactory.createLineBorder(black));
      headerPanel.setLayout(null);

      companyLogo.setIcon(new ImageIcon(getClass().getResource("/Images/logo.png")));
      headerPanel.add(companyLogo);
      companyLogo.setBounds(10, 0, 90, 90);

      companyadd4.setText("Cork");
      headerPanel.add(companyadd4);
      companyadd4.setBounds(130, 70, 180, 15);

      companyadd1.setText("Larcency Motors");
      headerPanel.add(companyadd1);
      companyadd1.setBounds(130, 10, 110, 15);

      companyadd2.setText("Unit 12 North Link Business Pk.");
      headerPanel.add(companyadd2);
      companyadd2.setBounds(130, 30, 180, 15);

      companyadd3.setText("Marsh Road");
      headerPanel.add(companyadd3);
      companyadd3.setBounds(130, 50, 180, 15);

      issueDateLabel.setText("Issue Date:");
      headerPanel.add(issueDateLabel);
      issueDateLabel.setBounds(390, 30, 70, 15);

      invoiceNoField.setText("0");
      headerPanel.add(invoiceNoField);
      invoiceNoField.setBounds(480, 10, 20, 15);

      invoiceNoLabel.setText("Invoice No:");
      headerPanel.add(invoiceNoLabel);
      invoiceNoLabel.setBounds(390, 10, 70, 15);

      issueDateField.setText("0/0/0");
      headerPanel.add(issueDateField);
      issueDateField.setBounds(470, 30, 70, 15);

      getContentPane().add(headerPanel);
      headerPanel.setBounds(5, 5, 540, 90);

      stockPanel.setBorder(BorderFactory.createLineBorder(black));
      stockPanel.setLayout(null);

      stockTable.setModel(new DefaultTableModel(
         new Object[][] {},
         new String[] {
            "Reg",
            "Make",
            "Model",
            "Price"
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
      tableScroll.setViewportView(stockTable);

      stockPanel.add(tableScroll);
      tableScroll.setBounds(10, 2, 510, 140);

      getContentPane().add(stockPanel);
      stockPanel.setBounds(10, 270, 530, 150);

      customerAddPanel.setBorder(BorderFactory.createLineBorder(black));
      customerAddPanel.setLayout(null);

      billToLabel.setFont(inter12b); // NOI18N
      billToLabel.setText("Bill to:");
      customerAddPanel.add(billToLabel);
      billToLabel.setBounds(10, 10, 60, 15);

      addressLn2Tag.setText("addressLn2");
      customerAddPanel.add(addressLn2Tag);
      addressLn2Tag.setBounds(10, 70, 240, 15);

      addressLn1Tag.setText("addressLn1");
      customerAddPanel.add(addressLn1Tag);
      addressLn1Tag.setBounds(10, 50, 230, 15);

      townTag.setText("town");
      customerAddPanel.add(townTag);
      townTag.setBounds(10, 80, 210, 30);

      countyTag.setText("county");
      customerAddPanel.add(countyTag);
      countyTag.setBounds(10, 110, 170, 15);

      customerNameTag.setText("customerName");
      customerAddPanel.add(customerNameTag);
      customerNameTag.setBounds(10, 30, 240, 15);

      eircodeTag.setText("eircode");
      customerAddPanel.add(eircodeTag);
      eircodeTag.setBounds(10, 130, 210, 20);

      getContentPane().add(customerAddPanel);
      customerAddPanel.setBounds(10, 100, 260, 160);

      leftBtmPanel.setBorder(BorderFactory.createLineBorder(black));
      leftBtmPanel.setLayout(null);

      paymentMethdLabel.setText("Payment Method:");
      leftBtmPanel.add(paymentMethdLabel);
      paymentMethdLabel.setBounds(30, 50, 110, 15);

      paymentMethodTag.setText("\"\"");
      leftBtmPanel.add(paymentMethodTag);
      paymentMethodTag.setBounds(140, 50, 90, 15);

      getContentPane().add(leftBtmPanel);
      leftBtmPanel.setBounds(10, 430, 240, 120);

      rightBtmPanel.setBorder(BorderFactory.createLineBorder(black));
      rightBtmPanel.setLayout(null);

      priceLabel.setText("Price:");
      rightBtmPanel.add(priceLabel);
      priceLabel.setBounds(40, 20, 42, 15);

      vatLabel.setText("VAT@23%:");
      rightBtmPanel.add(vatLabel);
      vatLabel.setBounds(10, 40, 70, 15);

      dueLabel.setText("Due:");
      rightBtmPanel.add(dueLabel);
      dueLabel.setBounds(40, 80, 30, 15);

      dueTag.setText("0.0");
      rightBtmPanel.add(dueTag);
      dueTag.setBounds(80, 80, 120, 15);

      priceTag.setText("0.0");
      rightBtmPanel.add(priceTag);
      priceTag.setBounds(80, 20, 100, 15);

      vatTag.setText("0.0");
      rightBtmPanel.add(vatTag);
      vatTag.setBounds(80, 40, 100, 15);

      getContentPane().add(rightBtmPanel);
      rightBtmPanel.setBounds(300, 430, 240, 120);

      pack();
      setVisible(true);
   }

}