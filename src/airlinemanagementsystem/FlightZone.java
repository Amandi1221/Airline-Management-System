package airlinemanagementsystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import net.proteanit.sql.DbUtils;
import java.sql.*;

public class FlightZone extends JFrame implements ActionListener {
    JComboBox<String> cbFlightCode;
    JButton btShowDetails;
    JTable table;
    JScrollPane sp;
    
    public FlightZone() {
        setTitle("Air Sri Lanka Flight Information");
        getContentPane().setBackground(new Color(230, 255, 230));
        setLayout(null);
        setSize(850,500);
        setLocation(300,150);
        
        JLabel title = new JLabel("Air Sri Lanka Flight Information");
        title.setBounds(250, 20, 400, 30);
        title.setFont(new Font("Tahoma", Font.BOLD, 22));
        add(title);
        
        JLabel lCode = new JLabel("Flight Code");
        lCode.setBounds(50, 80, 150, 30);
        lCode.setFont(new Font("Arial", Font.PLAIN, 16));
        add(lCode);
        
        cbFlightCode = new JComboBox<>();
        cbFlightCode.setBounds(180, 80, 150, 30);
        add(cbFlightCode);
        
        btShowDetails = new JButton("Show Details");
        btShowDetails.setBounds(380, 80, 150, 30);
        btShowDetails.setBackground(Color.BLUE);
        btShowDetails.setForeground(Color.WHITE);
        btShowDetails.addActionListener(this);
        add(btShowDetails);
        
        table = new JTable();
        sp = new JScrollPane(table);
        sp.setBounds(20, 140, 800, 300);
        add(sp);
        
        populateFlightCodes();
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void showFlightDetails() {
        String flightCode = (String)cbFlightCode.getSelectedItem();
        if(flightCode == null || flightCode.isEmpty()) return;
        
        String query = "SELECT source, destination, flight_date, flight_time, price, capacity, class_Name FROM flight WHERE flight_code = '" + flightCode +"'";
        
        AirlineManagementSystem dbConnection = null;
        try {
            dbConnection = new AirlineManagementSystem();
            if (dbConnection.stm == null) return;
            try (ResultSet rs = dbConnection.stm.executeQuery(query)) {
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error loading flight details: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if(dbConnection != null) dbConnection.closeResources();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btShowDetails) {
            showFlightDetails();
        }
    }
    
    public static void main(String[] args) {
        new FlightZone();
    }

    private void populateFlightCodes() {
        AirlineManagementSystem dbConnection = null;
        try {
            dbConnection = new AirlineManagementSystem();
            if (dbConnection.stm == null) return;
            String query = "SELECT flight_code FROM flight";
            
            try(ResultSet rs = dbConnection.stm.executeQuery(query)) {
                cbFlightCode.removeAllItems();
                while(rs.next()) {
                    cbFlightCode.addItem(rs.getString("flight_code"));
                }
            }
        } catch(SQLException ex) {
            System.out.println("Notice loading flight codes: " + ex.getMessage());
        } finally {
            if(dbConnection != null) dbConnection.closeResources();
        }
    }
}
