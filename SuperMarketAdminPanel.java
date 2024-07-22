import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SuperMarketAdminPanel {
    private JPanel Main;
    private JTextField txtBarcode;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JTextField txtName;
    private JTextField txtPrice;
    private JButton searchButton;
    private JTextField txtpid;

    public static void main(String[] args) {
        JFrame frame = new JFrame("SuperMarket Admin Panel");
        frame.setContentPane(new SuperMarketAdminPanel().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public SuperMarketAdminPanel() {
        Connect();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String barcode,name,price;

                barcode = txtBarcode.getText();
                name = txtName.getText();
                price = txtPrice.getText();

                try {
                    pst = con.prepareStatement("insert into product(barcode,product,price)values(?,?,?)");
                    pst.setString(1, barcode);
                    pst.setString(2, name);
                    pst.setString(3, price);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record Addedd!!!!");

                    txtBarcode.setText("");
                    txtName.setText("");
                    txtPrice.setText("");
                    txtBarcode.requestFocus();
                }
                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }

            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String pid = txtpid.getText();
                    pst = con.prepareStatement("select barcode,product,price from product where id = ?");
                    pst.setString(1, pid);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        String barcode = rs.getString(1);
                        String name = rs.getString(2);
                        String price = rs.getString(3);

                        txtBarcode.setText(barcode);
                        txtName.setText(name);
                        txtPrice.setText(price);
                    }
                    else
                    {
                        txtBarcode.setText("");
                        txtName.setText("");
                        txtPrice.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid Product ID");

                    }
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pid,barcode,name,price;

                barcode = txtBarcode.getText();
                name = txtName.getText();
                price = txtPrice.getText();
                pid = txtpid.getText();

                try {
                    pst = con.prepareStatement("update product set barcode = ?,product = ?,price = ? where id = ?");
                    pst.setString(1, barcode);
                    pst.setString(2, name);
                    pst.setString(3, price);
                    pst.setString(4, pid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updatedd!!!!!");

                    txtBarcode.setText("");
                    txtName.setText("");
                    txtPrice.setText("");
                    txtBarcode.requestFocus();
                    txtpid.setText("");
                }
                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bid;
                bid = txtpid.getText();

                try {
                    pst = con.prepareStatement("delete from product  where id = ?");
                    pst.setString(1, bid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deletedd!!!!!");

                    txtBarcode.setText("");
                    txtName.setText("");
                    txtPrice.setText("");
                    txtBarcode.requestFocus();
                    txtpid.setText("");
                }
                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }
            }
        });
    }

    Connection con;
    PreparedStatement pst;

    public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/rbc", "root","");
            System.out.println("Success");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

}

