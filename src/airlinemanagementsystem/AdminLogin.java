package airlinemanagementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.net.URL;

public class AdminLogin extends JFrame implements ActionListener {
    
    JLabel l1; 
    JTextField tfUsername;
    JPasswordField pfPassword;
    JButton btLogin, btCancel;

    public AdminLogin() {
        setTitle("Admin Login");
        setLayout(null); 
        setSize(400, 300);
        setLocation(500, 200);
        setForeground(Color.WHITE);

        URL imgURL = ClassLoader.getSystemResource("airlinemanagementsystem/icons/4.jpg");
        if (imgURL != null) {
            ImageIcon i1 = new ImageIcon(imgURL);
            Image i2 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
            l1 = new JLabel(new ImageIcon(i2));
        } else {
            l1 = new JLabel();
            l1.setOpaque(true);
            l1.setBackground(Color.DARK_GRAY);
        }
        
        l1.setBounds(0, 0, 400, 300); 
        add(l1);
        
        JLabel lTitle = new JLabel("Admin Login");
        lTitle.setBounds(150, 20, 150, 30);
        lTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        lTitle.setForeground(Color.WHITE); 
        l1.add(lTitle); 

        JLabel lUsername = new JLabel("Username:");
        lUsername.setBounds(50, 80, 100, 30);
        lUsername.setForeground(Color.WHITE);
        l1.add(lUsername);

        tfUsername = new JTextField();
        tfUsername.setBounds(160, 80, 150, 30);
        l1.add(tfUsername);

        JLabel lPassword = new JLabel("Password:");
        lPassword.setBounds(50, 130, 100, 30);
        lPassword.setForeground(Color.WHITE);
        l1.add(lPassword);

        pfPassword = new JPasswordField();
        pfPassword.setBounds(160, 130, 150, 30);
        l1.add(pfPassword);

        btLogin = new JButton("Login");
        btLogin.setBounds(80, 200, 100, 30);
        btLogin.setBackground(Color.BLACK);
        btLogin.setForeground(Color.WHITE);
        l1.add(btLogin);

        btCancel = new JButton("Cancel");
        btCancel.setBounds(200, 200, 100, 30);
        btCancel.setBackground(Color.RED);
        btCancel.setForeground(Color.WHITE);
        l1.add(btCancel);

        btLogin.addActionListener(this);
        btCancel.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btLogin) {
            String username = tfUsername.getText();
            String password = new String(pfPassword.getPassword());

            // Bypass check for default admin login if DB not configured yet
            if (username.equals("admin") && password.equals("admin123")) {
                JOptionPane.showMessageDialog(this, "Admin Login Successful. Opening Admin Section.", "Success", JOptionPane.INFORMATION_MESSAGE);
                new AdminSection();
                dispose();
                return;
            }

            String query = "SELECT * FROM admin WHERE username = '" + username + "' AND password = '" + password + "'";
            AirlineManagementSystem dbConnection = null;

            try {
                dbConnection = new AirlineManagementSystem();
                if (dbConnection.stm != null) {
                    ResultSet rs = dbConnection.stm.executeQuery(query);
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(this, "Admin Login Successful. Opening Admin Section.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        new AdminSection();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid Admin Credentials.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    rs.close();
                } else {
                    JOptionPane.showMessageDialog(this, "Default fallback credentials: admin / admin123", "Notice", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error during login: " + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (dbConnection != null) dbConnection.closeResources();
            }
        } else if (e.getSource() == btCancel) {
            dispose();
        }
    }
    
    public static void main(String[] args) {
        new AdminLogin();
    }
}
