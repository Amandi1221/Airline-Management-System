package airlinemanagementsystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.net.URL;

public class AddAirlinesEmployee extends JFrame implements ActionListener {

    JLabel l1; 
    JTextField tfUsername, tfEmployeeName, tfPhone;
    JPasswordField pfPassword;
    JButton btAddEmployee, btBack;

    public AddAirlinesEmployee() {
        setTitle("Add Airlines Employee");
        setLayout(null);
        setSize(450, 450);
        setLocation(500, 150);
        getContentPane().setBackground(Color.black);

        URL imageURL = ClassLoader.getSystemResource("airlinemanagementsystem/icons/2.jpg");
        
        if (imageURL != null) {
            ImageIcon i1 = new ImageIcon(imageURL);
            Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_SMOOTH);
            ImageIcon i3 = new ImageIcon(i2);
            l1 = new JLabel(i3);
        } else {
            System.out.println("Image not found. Check src/airlinemanagementsystem/icons/2.jpg");
            l1 = new JLabel();
            l1.setOpaque(true);
            l1.setBackground(Color.LIGHT_GRAY);
        }
        
        l1.setBounds(0, 0, 450, 450);
        add(l1); 

        JLabel lTitle = new JLabel("Add Airlines Employee"); 
        lTitle.setBounds(80, 20, 300, 30); 
        lTitle.setFont(new Font("Tahoma", Font.BOLD, 22)); 
        lTitle.setForeground(Color.white);
        l1.add(lTitle);
        
        int y_start = 80;
        int y_spacing = 50;

        JLabel lUsername = new JLabel("Username:"); 
        lUsername.setBounds(50, y_start, 150, 30); 
        lUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
        lUsername.setForeground(Color.WHITE);
        l1.add(lUsername);
        
        tfUsername = new JTextField(); 
        tfUsername.setBounds(200, y_start, 180, 30); 
        l1.add(tfUsername);
        
        JLabel lEmployeeName = new JLabel("Employee Name:"); 
        lEmployeeName.setBounds(50, y_start + y_spacing, 150, 30); 
        lEmployeeName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lEmployeeName.setForeground(Color.WHITE);
        l1.add(lEmployeeName);
        
        tfEmployeeName = new JTextField(); 
        tfEmployeeName.setBounds(200, y_start + y_spacing, 180, 30); 
        l1.add(tfEmployeeName);

        JLabel lPassword = new JLabel("Password:"); 
        lPassword.setBounds(50, y_start + 2 * y_spacing, 150, 30); 
        lPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        lPassword.setForeground(Color.WHITE);
        l1.add(lPassword);
        
        pfPassword = new JPasswordField(); 
        pfPassword.setBounds(200, y_start + 2 * y_spacing, 180, 30); 
        l1.add(pfPassword);

        JLabel lPhone = new JLabel("Phone:"); 
        lPhone.setBounds(50, y_start + 3 * y_spacing, 150, 30); 
        lPhone.setFont(new Font("Tahoma", Font.BOLD, 14));
        lPhone.setForeground(Color.WHITE);
        l1.add(lPhone);
        
        tfPhone = new JTextField(); 
        tfPhone.setBounds(200, y_start + 3 * y_spacing, 180, 30); 
        l1.add(tfPhone);

        btAddEmployee = new JButton("Add Employee"); 
        btAddEmployee.setBounds(80, 330, 140, 40); 
        btAddEmployee.setBackground(Color.WHITE); 
        btAddEmployee.setForeground(Color.BLACK); 
        l1.add(btAddEmployee);

        btBack = new JButton("Back"); 
        btBack.setBounds(240, 330, 100, 40); 
        btBack.setBackground(Color.WHITE); 
        btBack.setForeground(Color.BLACK); 
        l1.add(btBack);
        
        btAddEmployee.addActionListener(this);
        btBack.addActionListener(this);
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btAddEmployee) {
            String username = tfUsername.getText();
            String name = tfEmployeeName.getText();
            String password = new String(pfPassword.getPassword());
            String phone = tfPhone.getText();
            
            if (username.isEmpty() || name.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username, Name, and Password are mandatory.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String query = "INSERT INTO employee (username, employee_name, password, phone) VALUES ('" + 
                           username + "','" + name + "','" + password + "','" + phone + "')";

            AirlineManagementSystem dbConnection = null;
            try {
                dbConnection = new AirlineManagementSystem();
                if (dbConnection.stm == null) return;
                dbConnection.stm.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "Employee Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (dbConnection != null) dbConnection.closeResources();
            }
        } else if (e.getSource() == btBack) {
            dispose();
        }
    }
    
    public static void main(String[] args) {
        new AddAirlinesEmployee();
    }
}
