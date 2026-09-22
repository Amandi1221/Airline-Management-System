package airlinemanagementsystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.net.URL;

public class EmployeeLogin extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4;
    JButton bt1, bt2;
    JPasswordField pf;
    JTextField tf;
    JFrame f;

    public EmployeeLogin() {
        f = new JFrame("Employee Login");
        f.getContentPane().setBackground(Color.WHITE);
        f.setLayout(null);
        f.setSize(580, 400); 
        f.setLocation(450, 200);

        l1 = new JLabel();
        l1.setBounds(0, 0, 580, 400);
        l1.setLayout(null);
        
        URL imgURL = ClassLoader.getSystemResource("airlinemanagementsystem/icons/8.jpg");
        
        if (imgURL != null) {
            ImageIcon img = new ImageIcon(imgURL);
            Image i1 = img.getImage().getScaledInstance(580, 400, Image.SCALE_SMOOTH);
            l1.setIcon(new ImageIcon(i1));
        } else {
            System.out.println("Image not found in airlinemanagementsystem/icons/8.jpg");
            l1.setOpaque(true);
            l1.setBackground(Color.LIGHT_GRAY);
        }

        l3 = new JLabel("Employee Login");
        l3.setBounds(160, 30, 300, 50);
        l3.setForeground(Color.WHITE);
        l3.setFont(new Font("Arial", Font.BOLD, 30));
        l1.add(l3);

        l2 = new JLabel("Username");
        l2.setBounds(100, 120, 150, 30);
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("Arial", Font.BOLD, 20));
        l1.add(l2);

        tf = new JTextField();
        tf.setBounds(280, 120, 180, 30);
        l1.add(tf);

        l4 = new JLabel("Password");
        l4.setBounds(100, 170, 150, 30);
        l4.setForeground(Color.WHITE);
        l4.setFont(new Font("Arial", Font.BOLD, 20));
        l1.add(l4);

        pf = new JPasswordField();
        pf.setBounds(280, 170, 180, 30);
        l1.add(pf);

        bt1 = new JButton("Login");
        bt1.setBackground(Color.BLACK);
        bt1.setForeground(Color.WHITE);
        bt1.setBounds(120, 250, 120, 40);
        l1.add(bt1);

        bt2 = new JButton("Close");
        bt2.setBackground(Color.RED);
        bt2.setForeground(Color.WHITE);
        bt2.setBounds(280, 250, 120, 40);
        l1.add(bt2);

        bt1.addActionListener(this);
        bt2.addActionListener(this);

        f.add(l1);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            String username = tf.getText();
            String pass = new String(pf.getPassword());

            if (username.equalsIgnoreCase("emp") && pass.equals("1234")) {
                JOptionPane.showMessageDialog(f, "Login Successful!");
                new HomePage();
                f.dispose();
                return;
            }
            
            AirlineManagementSystem dbConnection = null;
            try {
                dbConnection = new AirlineManagementSystem();
                if (dbConnection.connection != null) {
                    String sql = "SELECT * FROM employee WHERE username=? AND password=?";
                    PreparedStatement pst = dbConnection.connection.prepareStatement(sql);
                    pst.setString(1, username);
                    pst.setString(2, pass);
                    
                    ResultSet rs = pst.executeQuery();
                    
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(f, "Login Successful!");
                        new HomePage();
                        f.dispose();
                    } else {
                        JOptionPane.showMessageDialog(f, "Invalid Username or Password!");
                    }
                    pst.close();
                } else {
                    JOptionPane.showMessageDialog(f, "Default login: emp / 1234", "Notice", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(f, "Database Error: " + ex.getMessage());
            } finally {
                if (dbConnection != null) dbConnection.closeResources();
            }
        }
        
        if (e.getSource() == bt2) {
            f.dispose();
        }
    }

    public static void main(String[] args) {
        new EmployeeLogin();
    }
}
