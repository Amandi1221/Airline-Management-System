package airlinemanagementsystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class CheckPayment extends JFrame implements ActionListener {

    JButton btShow;
    JTextField tfUsername;
    JTable table;
    JLabel lTitle, lUsername;
    JScrollPane sp;
    
    public CheckPayment() {
        setTitle("Check Payment Details");
        getContentPane().setBackground(new Color(240, 248, 255)); 
        setLayout(null);
        setSize(900, 500);
        setLocation(300, 150);

        lTitle = new JLabel("Check Payment Details");
        lTitle.setBounds(300, 20, 300, 30);
        lTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(lTitle); 
        
        lUsername = new JLabel("Username:");
        lUsername.setBounds(150, 80, 150, 30);
        lUsername.setFont(new Font("Arial", Font.PLAIN, 18));
        add(lUsername);
        
        tfUsername = new JTextField();
        tfUsername.setBounds(280, 80, 150, 30);
        add(tfUsername);

        btShow = new JButton("Show");
        btShow.setBounds(450, 80, 100, 30);
        btShow.setBackground(Color.BLUE);
        btShow.setForeground(Color.WHITE);
        btShow.addActionListener(this);
        add(btShow);
        
        table = new JTable();
        sp = new JScrollPane(table);
        sp.setBounds(20, 140, 860, 300);
        add(sp); 
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void showPaymentDetails() {
        String username = tfUsername.getText().trim();
        
        if (username.isEmpty()) {
             JOptionPane.showMessageDialog(null, "Please enter a valid Username.", "Input Error", JOptionPane.WARNING_MESSAGE);
             return;
        }

        String query = "SELECT Ticket_Id, Price, Username, Booking_Status AS Status FROM bookflight WHERE Username = '" + username + "'";

        AirlineManagementSystem dbConnection = null;
        try {
            dbConnection = new AirlineManagementSystem();
            if (dbConnection.stm == null) return;
            
            try (ResultSet rs = dbConnection.stm.executeQuery(query)) {
                table.setModel(DbUtils.resultSetToTableModel(rs));

                if (table.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "No payment/booking details found for username: " + username, "Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving payment data: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        } finally {
             if (dbConnection != null) dbConnection.closeResources();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btShow) {
            showPaymentDetails();
        }
    }
    
    public static void main(String[] args) {
        new CheckPayment();
    }
}
