package airlinemanagementsystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.util.Vector;
import net.proteanit.sql.DbUtils;

public class FlightDetails extends JFrame implements ActionListener {
    JButton bt1, bt2;
    JComboBox<String> cbSource, cbDestination;
    JTable table;
    JLabel l0, l1, l2, l3; 
    JScrollPane sp;
    
    public FlightDetails() {
        setTitle("Search Flight Details");
        getContentPane().setBackground(Color.CYAN);
        setLayout(null);
        setSize(800, 500);
        setLocation(350, 150);

        l1 = new JLabel("Search Available Flights");
        l1.setBounds(250, 20, 300, 30);
        l1.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(l1); 

        l2 = new JLabel("Source");
        l2.setBounds(100, 80, 150, 30);
        l2.setFont(new Font("Arial", Font.BOLD, 18));
        add(l2);
        cbSource = new JComboBox<>();
        cbSource.setBounds(200, 80, 150, 30);
        add(cbSource);

        l3 = new JLabel("Destination");
        l3.setBounds(400, 80, 100, 30);
        l3.setFont(new Font("Arial", Font.BOLD, 18));
        add(l3);
        cbDestination = new JComboBox<>();
        cbDestination.setBounds(500, 80, 150, 30);
        add(cbDestination);

        bt1 = new JButton("Search");
        bt1.setBounds(300, 130, 100, 30);
        bt1.setBackground(Color.BLACK);
        bt1.setForeground(Color.WHITE);
        add(bt1);

        bt2 = new JButton("Close");
        bt2.setBounds(420, 130, 100, 30);
        bt2.setBackground(Color.RED);
        bt2.setForeground(Color.WHITE);
        add(bt2);
        
        table = new JTable();
        sp = new JScrollPane(table);
        sp.setBounds(20, 180, 760, 250);
        add(sp); 
      
        bt1.addActionListener(this);
        bt2.addActionListener(this);
        
        populateComboBoxes();
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void populateComboBoxes() {
        AirlineManagementSystem dbConnection = null;
        try {
            dbConnection = new AirlineManagementSystem();
            if (dbConnection.stm == null) return;
            String query = "SELECT DISTINCT source, destination FROM flight";
       
            try (ResultSet rs = dbConnection.stm.executeQuery(query)) {
                Vector<String> sources = new Vector<>();
                Vector<String> destinations = new Vector<>();
                
                while(rs.next()) {
                    String source = rs.getString("source");
                    String destination = rs.getString("destination");
                    
                    if(!sources.contains(source)) {
                        sources.add(source);
                    }
                    if(!destinations.contains(destination)) {
                        destinations.add(destination);
                    }
                }
                cbSource.setModel(new DefaultComboBoxModel<>(sources));
                cbDestination.setModel(new DefaultComboBoxModel<>(destinations));  
            }
        } catch(SQLException ex) {
            System.out.println("Error loading flight cities: " + ex.getMessage());
        } finally {
            if (dbConnection != null) dbConnection.closeResources();
        }
    }

    private void searchFlights() {
        String source = (String) cbSource.getSelectedItem();
        String destination = (String) cbDestination.getSelectedItem();
        
        if (source == null || source.isEmpty() || destination == null || destination.isEmpty()) {
             JOptionPane.showMessageDialog(null, "Please select both source and destination.");
             return;
        }

        String query = "SELECT flight_code, price, flight_date, flight_time, capacity " + 
                       "FROM flight " + 
                       "WHERE source = '" + source + "' AND destination = '" + destination + "'";

        AirlineManagementSystem dbConnection = null;
        try {
            dbConnection = new AirlineManagementSystem();
            if (dbConnection.stm == null) return;
            
            try (ResultSet rs = dbConnection.stm.executeQuery(query)) {
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error retrieving flight data: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        } finally {
             if (dbConnection != null) dbConnection.closeResources();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            searchFlights(); 
        } else if (e.getSource() == bt2) {
            dispose();
        }
    }
    
    public static void main(String[] args) {
        new FlightDetails();
    }
}
