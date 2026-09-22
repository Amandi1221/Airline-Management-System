package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class Login extends JFrame implements ActionListener {
    
    JButton btnAdminLogin, btnEmployeeLogin;
    JLabel lblTitle, lblAdmin, lblEmployee, background;
    
    public Login() {
        setTitle("Airlines Management System - Login");
        setSize(800, 500);
        setLayout(null);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        URL imgPath = ClassLoader.getSystemResource("airlinemanagementsystem/icons/1.jpg"); 
        
        if (imgPath != null) {
            ImageIcon i1 = new ImageIcon(imgPath);
            Image i2 = i1.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH);
            background = new JLabel(new ImageIcon(i2));
        } else {
            background = new JLabel("Background Image Not Found", SwingConstants.CENTER);
            background.setOpaque(true);
            background.setBackground(Color.LIGHT_GRAY);
        }
        
        background.setBounds(0, 0, 800, 500);
        add(background); 

        lblTitle = new JLabel("Airlines Management");
        lblTitle.setBounds(250, 30, 400, 45);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 35));
        lblTitle.setForeground(Color.WHITE); 
        background.add(lblTitle);
        
        lblAdmin = new JLabel("Admin Login");
        lblAdmin.setBounds(150, 150, 200, 30);
        lblAdmin.setFont(new Font("Arial", Font.BOLD, 22));
        lblAdmin.setForeground(Color.WHITE);
        background.add(lblAdmin);
        
        btnAdminLogin = new JButton("Login");
        btnAdminLogin.setBounds(500, 150, 150, 40);
        btnAdminLogin.setBackground(Color.WHITE);
        btnAdminLogin.setForeground(Color.BLACK);
        btnAdminLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnAdminLogin.addActionListener(this);
        background.add(btnAdminLogin);
        
        lblEmployee = new JLabel("Employee Login");
        lblEmployee.setBounds(150, 250, 200, 30);
        lblEmployee.setFont(new Font("Arial", Font.BOLD, 22));
        lblEmployee.setForeground(Color.WHITE);
        background.add(lblEmployee);
        
        btnEmployeeLogin = new JButton("Login");
        btnEmployeeLogin.setBounds(500, 250, 150, 40);
        btnEmployeeLogin.setBackground(Color.WHITE);
        btnEmployeeLogin.setForeground(Color.BLACK);
        btnEmployeeLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnEmployeeLogin.addActionListener(this);
        background.add(btnEmployeeLogin);
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnAdminLogin) {
            new AdminLogin().setVisible(true);
            this.dispose(); 
        }
        else if (ae.getSource() == btnEmployeeLogin) {
            new EmployeeLogin().setVisible(true);
            this.dispose();
        }
    }
    
    public static void main(String[] args) {
        new Login();
    }
}
