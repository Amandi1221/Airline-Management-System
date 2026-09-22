package airlinemanagementsystem;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.*;
import javax.swing.*;

public class CancelTicket extends JFrame implements ActionListener, ItemListener {

    JButton btCancel, btBack;
    JComboBox<String> cbTicketId;
   
    JLabel lBackground, lTitle, lTicketId, lSource, lDestination, lClass, lPrice, 
           lFlightCode, lFlightDate, lUsername, lName, lReason;
    
    JTextField tfSource, tfDestination, tfClass, tfPrice, tfFlightCode, 
               tfFlightDate, tfUsername, tfName, tfReason;

    public CancelTicket() {
        setTitle("Cancel Your Flight Ticket");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(900, 650); 
        setLocation(300, 100);

        lBackground = new JLabel();
        lBackground.setBounds(0, 0, 900, 650);
        lBackground.setLayout(null);

        URL imgURL = ClassLoader.getSystemResource("airlinemanagementsystem/icons/12.jpg");
        if (imgURL != null) {
            ImageIcon img = new ImageIcon(imgURL);
            Image i2 = img.getImage().getScaledInstance(900, 650, Image.SCALE_SMOOTH);
            lBackground.setIcon(new ImageIcon(i2));
        } else {
            System.out.println("Image not found. Check src/airlinemanagementsystem/icons/12.jpg");
            lBackground.setOpaque(true);
            lBackground.setBackground(Color.LIGHT_GRAY);
        }
        add(lBackground); 

        lTitle = new JLabel("Cancel Your Flight Ticket");
        lTitle.setBounds(250, 20, 450, 40);
        lTitle.setForeground(Color.BLACK);
        lTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
        lBackground.add(lTitle);
        
        int y_start = 100;
        int y_spacing = 50;
        int x_L_label = 50;
        int x_L_field = 200;
        int x_R_label = 450;
        int x_R_field = 600;

        lTicketId = new JLabel("Ticket Id"); 
        lTicketId.setBounds(x_L_label, y_start, 150, 30); 
        lTicketId.setFont(new Font("Arial", Font.BOLD, 18)); 
        lBackground.add(lTicketId);
        cbTicketId = new JComboBox<>(); 
        cbTicketId.setBounds(x_L_field, y_start, 150, 30); 
        lBackground.add(cbTicketId);
        
        lSource = new JLabel("Source");
        lSource.setBounds(x_R_label, y_start, 150, 30); 
        lSource.setFont(new Font("Arial", Font.BOLD, 18)); 
        lBackground.add(lSource);
        tfSource = new JTextField(); 
        tfSource.setBounds(x_R_field, y_start, 150, 30); 
        tfSource.setEditable(false); 
        lBackground.add(tfSource);

        lDestination = new JLabel("Destination"); 
        lDestination.setBounds(x_L_label, y_start + y_spacing, 150, 30); 
        lDestination.setFont(new Font("Arial", Font.BOLD, 18)); 
        lBackground.add(lDestination);
        tfDestination = new JTextField(); 
        tfDestination.setBounds(x_L_field, y_start + y_spacing, 150, 30); 
        tfDestination.setEditable(false); 
        lBackground.add(tfDestination);

        lClass = new JLabel("Class"); 
        lClass.setBounds(x_R_label, y_start + y_spacing, 150, 30); 
        lClass.setFont(new Font("Arial", Font.BOLD, 18)); 
        lBackground.add(lClass);
        tfClass = new JTextField(); 
        tfClass.setBounds(x_R_field, y_start + y_spacing, 150, 30); 
        tfClass.setEditable(false); 
        lBackground.add(tfClass);

        lPrice = new JLabel("Price"); 
        lPrice.setBounds(x_L_label, y_start + 2 * y_spacing, 150, 30); 
        lPrice.setFont(new Font("Arial", Font.BOLD, 18)); 
        lBackground.add(lPrice);
        tfPrice = new JTextField(); 
        tfPrice.setBounds(x_L_field, y_start + 2 * y_spacing, 150, 30); 
        tfPrice.setEditable(false); 
        lBackground.add(tfPrice);

        lFlightCode = new JLabel("Flight Code"); 
        lFlightCode.setBounds(x_R_label, y_start + 2 * y_spacing, 150, 30); 
        lFlightCode.setFont(new Font("Arial", Font.BOLD, 18)); 
        lBackground.add(lFlightCode);
        tfFlightCode = new JTextField(); 
        tfFlightCode.setBounds(x_R_field, y_start + 2 * y_spacing, 150, 30); 
        tfFlightCode.setEditable(false); 
        lBackground.add(tfFlightCode);

        lFlightDate = new JLabel("Flight Date"); 
        lFlightDate.setBounds(x_L_label, y_start + 3 * y_spacing, 150, 30); 
        lFlightDate.setFont(new Font("Arial", Font.BOLD, 18)); 
        lBackground.add(lFlightDate);
        tfFlightDate = new JTextField(); 
        tfFlightDate.setBounds(x_L_field, y_start + 3 * y_spacing, 150, 30); 
        tfFlightDate.setEditable(false); 
        lBackground.add(tfFlightDate);

        lUsername = new JLabel("Username"); 
        lUsername.setBounds(x_R_label, y_start + 3 * y_spacing, 150, 30); 
        lUsername.setFont(new Font("Arial", Font.BOLD, 18)); 
        lBackground.add(lUsername);
        tfUsername = new JTextField(); 
        tfUsername.setBounds(x_R_field, y_start + 3 * y_spacing, 150, 30); 
        tfUsername.setEditable(false); 
        lBackground.add(tfUsername);
        
        lName = new JLabel("Name"); 
        lName.setBounds(x_L_label, y_start + 4 * y_spacing, 150, 30); 
        lName.setFont(new Font("Arial", Font.BOLD, 18)); 
        lBackground.add(lName);
        tfName = new JTextField(); 
        tfName.setBounds(x_L_field, y_start + 4 * y_spacing, 150, 30); 
        tfName.setEditable(false); 
        lBackground.add(tfName);

        lReason = new JLabel("Reason");
        lReason.setBounds(x_R_label, y_start + 4 * y_spacing, 150, 30);
        lReason.setFont(new Font("Arial", Font.BOLD, 18));
        lBackground.add(lReason);
        tfReason = new JTextField();
        tfReason.setBounds(x_R_field, y_start + 4 * y_spacing, 150, 30);
        lBackground.add(tfReason);
        
        btCancel = new JButton("Cancel Flight"); 
        btCancel.setBounds(300, 550, 150, 40); 
        btCancel.setBackground(Color.RED); 
        btCancel.setForeground(Color.WHITE); 
        lBackground.add(btCancel);

        btBack = new JButton("Back"); 
        btBack.setBounds(500, 550, 150, 40); 
        btBack.setBackground(Color.GRAY); 
        btBack.setForeground(Color.WHITE); 
        lBackground.add(btBack);
        
        btCancel.addActionListener(this);
        btBack.addActionListener(this);
        cbTicketId.addItemListener(this); 
        
        populateTicketIds();
        revalidate();
        repaint();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void populateTicketIds() {
        AirlineManagementSystem dbConnection = null;
        try {
            dbConnection = new AirlineManagementSystem();
            if (dbConnection.stm == null) return;
            String query = "SELECT Ticket_Id FROM bookflight WHERE Booking_Status = 'Booked' OR Booking_Status IS NULL";
            try (ResultSet rs = dbConnection.stm.executeQuery(query)) {
                cbTicketId.removeAllItems(); 
                while (rs.next()) {
                    cbTicketId.addItem(rs.getString("Ticket_Id"));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading booked Ticket IDs: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (dbConnection != null) dbConnection.closeResources();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        if (ie.getStateChange() == ItemEvent.SELECTED && ie.getSource() == cbTicketId) {
            String selectedTicketId = (String) cbTicketId.getSelectedItem();
            if (selectedTicketId == null) return;
            
            AirlineManagementSystem dbConnection = null;
            try {
                dbConnection = new AirlineManagementSystem();
                if (dbConnection.stm == null) return;

                String query = "SELECT bf.Source, bf.Destination, bf.Class, bf.Price, bf.Username, " +
                               "bf.Journey_Date, bf.Name, bf.flight_code " +
                               "FROM bookflight bf " +
                               "WHERE bf.Ticket_Id = '" + selectedTicketId + "'";

                try (ResultSet rs = dbConnection.stm.executeQuery(query)) {
                    if (rs.next()) {
                        tfSource.setText(rs.getString("Source"));
                        tfDestination.setText(rs.getString("Destination"));
                        tfClass.setText(rs.getString("Class"));
                        tfPrice.setText(rs.getString("Price"));
                        tfFlightCode.setText(rs.getString("flight_code"));
                        tfFlightDate.setText(rs.getString("Journey_Date"));
                        tfUsername.setText(rs.getString("Username"));
                        tfName.setText(rs.getString("Name"));
                        tfReason.setText(""); 
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error loading ticket details: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (dbConnection != null) dbConnection.closeResources();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btCancel) { 
            String ticketId = (String) cbTicketId.getSelectedItem();
            String reason = tfReason.getText();
            
            if (ticketId == null || reason.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a ticket and provide a reason for cancellation.", "Input Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            AirlineManagementSystem dbConnection = null;
            try {
                dbConnection = new AirlineManagementSystem();
                if (dbConnection.stm == null) return;

                String updateQuery = "UPDATE bookflight SET Booking_Status = 'Cancelled', Cancel_Reason = '" + reason + "' WHERE Ticket_Id = '" + ticketId + "'";
                int rows = dbConnection.stm.executeUpdate(updateQuery);

                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Ticket ID " + ticketId + " successfully cancelled.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    populateTicketIds();
                } else {
                    JOptionPane.showMessageDialog(this, "Cancellation failed. Ticket may already be cancelled or not found.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Cancellation failed: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (dbConnection != null) dbConnection.closeResources();
            }
        } else if (e.getSource() == btBack) { 
            dispose();
        }
    }
    
    public static void main(String[] args) {
        new CancelTicket();
    }
}
