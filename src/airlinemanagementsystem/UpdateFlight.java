package airlinemanagementsystem;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateFlight extends JFrame implements ActionListener {

    JTextField tfPrice, tfTime, tfCode;
    JButton btnUpdate;
    AirlineManagementSystem.DBConnection db; 

    public UpdateFlight() {
        setTitle("Update Flight");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel l1 = new JLabel("Flight Code");
        l1.setBounds(30, 30, 100, 25);
        add(l1);

        tfCode = new JTextField();
        tfCode.setBounds(150, 30, 180, 25);
        add(tfCode);

        JLabel l2 = new JLabel("New Price");
        l2.setBounds(30, 80, 100, 25);
        add(l2);

        tfPrice = new JTextField();
        tfPrice.setBounds(150, 80, 180, 25);
        add(tfPrice);

        JLabel l3 = new JLabel("New Time (HH:MM:SS)");
        l3.setBounds(30, 130, 180, 25);
        add(l3);

        tfTime = new JTextField();
        tfTime.setBounds(150, 130, 180, 25);
        add(tfTime);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(130, 180, 120, 30);
        btnUpdate.addActionListener(this);
        add(btnUpdate);

        try {
            AirlineManagementSystem ams = new AirlineManagementSystem();
            db = ams.new DBConnection(); 
        } catch (Exception ex) {
            System.out.println("DB Connection Notice: " + ex.getMessage());
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (tfCode.getText().isEmpty() || tfPrice.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields");
            return;
        }

        try {
            if (db == null || db.con == null) {
                AirlineManagementSystem ams = new AirlineManagementSystem();
                db = ams.new DBConnection();
            }

            if (db == null || db.con == null) {
                JOptionPane.showMessageDialog(this, "Database Connection Failed");
                return;
            }

            String sql = "UPDATE flight SET price=?, flight_time=? WHERE flight_code=?";
            PreparedStatement ps = db.con.prepareStatement(sql);

            ps.setDouble(1, Double.parseDouble(tfPrice.getText()));
            
            String timeStr = tfTime.getText().trim();
            if (timeStr.isEmpty()) {
                timeStr = "10:00:00";
            }
            
            try {
                ps.setTime(2, Time.valueOf(timeStr));
            } catch (IllegalArgumentException timeEx) {
                ps.setString(2, timeStr);
            }
            
            ps.setString(3, tfCode.getText());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Flight Updated Successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Flight Code Not Found");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQL Error: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Price format. Use numbers only.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new UpdateFlight();
    }
}