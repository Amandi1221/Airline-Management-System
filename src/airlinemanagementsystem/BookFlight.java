package airlinemanagementsystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.*;
import java.util.Vector;
import java.util.Random;

public class BookFlight extends JFrame implements ActionListener, ItemListener {

    JButton bt1, bt2;
    JComboBox<String> cbTicketId, cbSource, cbDestination, cbClass, cbPrice, cbUsername;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11;
    JTextField tfFlightCode, tfJourneyDate, tfName;

    AirlineManagementSystem dbConnection;

    public BookFlight() {
        setTitle("Book Airlines Sri Lanka");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(900, 800);
        setLocation(200, 50);

        l1 = new JLabel();
        l1.setBounds(0, 0, 1000, 700);
        l1.setLayout(null);

        URL imgURL = ClassLoader.getSystemResource("airlinemanagementsystem/icons/11.jpg");
        if (imgURL != null) {
            ImageIcon img = new ImageIcon(imgURL);
            Image i2 = img.getImage().getScaledInstance(1000, 700, Image.SCALE_SMOOTH);
            l1.setIcon(new ImageIcon(i2));
        } else {
            System.out.println("Image not found. Check src/airlinemanagementsystem/icons/11.jpg");
            l1.setOpaque(true);
            l1.setBackground(Color.LIGHT_GRAY);
        }

        l2 = new JLabel("Book Airlines Sri Lanka");
        l2.setBounds(300, 30, 400, 40);
        l2.setForeground(Color.BLACK);
        l2.setFont(new Font("Arial", Font.BOLD, 30));
        l1.add(l2);

        l3 = new JLabel("Ticket Id");
        l3.setBounds(150, 100, 150, 30);
        l3.setFont(new Font("Arial", Font.BOLD, 18));
        l1.add(l3);
        cbTicketId = new JComboBox<>();
        cbTicketId.setBounds(350, 100, 200, 30);
        cbTicketId.setEditable(true);
        l1.add(cbTicketId);

        l4 = new JLabel("Source");
        l4.setBounds(150, 150, 150, 30);
        l4.setFont(new Font("Arial", Font.BOLD, 18));
        l1.add(l4);
        cbSource = new JComboBox<>();
        cbSource.setBounds(350, 150, 200, 30);
        l1.add(cbSource);

        l5 = new JLabel("Destination");
        l5.setBounds(150, 200, 150, 30);
        l5.setFont(new Font("Arial", Font.BOLD, 18));
        l1.add(l5);
        cbDestination = new JComboBox<>();
        cbDestination.setBounds(350, 200, 200, 30);
        l1.add(cbDestination);

        l6 = new JLabel("Class");
        l6.setBounds(150, 250, 150, 30);
        l6.setFont(new Font("Arial", Font.BOLD, 18));
        l1.add(l6);
        cbClass = new JComboBox<>(new String[]{"Economy", "Business"});
        cbClass.setBounds(350, 250, 200, 30);
        l1.add(cbClass);

        l7 = new JLabel("Price");
        l7.setBounds(150, 300, 150, 30);
        l7.setFont(new Font("Arial", Font.BOLD, 18));
        l1.add(l7);
        cbPrice = new JComboBox<>();
        cbPrice.setBounds(350, 300, 200, 30);
        l1.add(cbPrice);

        l8 = new JLabel("Flight Code");
        l8.setBounds(150, 350, 150, 30);
        l8.setFont(new Font("Arial", Font.BOLD, 18));
        l1.add(l8);
        tfFlightCode = new JTextField();
        tfFlightCode.setBounds(350, 350, 200, 30);
        l1.add(tfFlightCode);
        
        l9 = new JLabel("Journey Date");
        l9.setBounds(150, 400, 150, 30);
        l9.setFont(new Font("Arial", Font.BOLD, 18));
        l1.add(l9);
        tfJourneyDate = new JTextField();
        tfJourneyDate.setBounds(350, 400, 200, 30);
        tfJourneyDate.setText("2026-10-01");
        l1.add(tfJourneyDate);

        l10 = new JLabel("Username");
        l10.setBounds(150, 450, 150, 30);
        l10.setFont(new Font("Arial", Font.BOLD, 18));
        l1.add(l10);
        cbUsername = new JComboBox<>();
        cbUsername.setBounds(350, 450, 200, 30);
        l1.add(cbUsername);

        l11 = new JLabel("Name");
        l11.setBounds(150, 500, 150, 30);
        l11.setFont(new Font("Arial", Font.BOLD, 18));
        l1.add(l11);
        tfName = new JTextField();
        tfName.setBounds(350, 500, 200, 30);
        l1.add(tfName);

        bt1 = new JButton("Book Flight");
        bt1.setBounds(250, 640, 150, 40);
        bt1.setBackground(Color.BLACK);
        bt1.setForeground(Color.WHITE);
        l1.add(bt1);

        bt2 = new JButton("Back");
        bt2.setBounds(450, 640, 150, 40);
        bt2.setBackground(Color.RED);
        bt2.setForeground(Color.WHITE);
        l1.add(bt2);

        bt1.addActionListener(this);
        bt2.addActionListener(this);
        cbTicketId.addItemListener(this);
        cbUsername.addItemListener(this);
        cbSource.addItemListener(this);

        generateNewTicketId();
        populateInitialFields();

        add(l1);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void generateNewTicketId() {
        Random rand = new Random();
        String autoTicketId = "TKT-" + (1000 + rand.nextInt(9000));
        cbTicketId.addItem(autoTicketId);
        cbTicketId.setSelectedItem(autoTicketId);
    }

    private void populateInitialFields() {
        try {
            dbConnection = new AirlineManagementSystem();
            if (dbConnection.stm == null) return;
            ResultSet rs;

            String flightQuery = "SELECT DISTINCT source, destination, price, flight_code FROM flight";
            rs = dbConnection.stm.executeQuery(flightQuery);

            Vector<String> sources = new Vector<>();
            Vector<String> destinations = new Vector<>();
            Vector<String> prices = new Vector<>();

            while (rs.next()) {
                String src = rs.getString("source");
                String dest = rs.getString("destination");
                String prc = String.valueOf(rs.getDouble("price"));
                if (!sources.contains(src)) sources.add(src);
                if (!destinations.contains(dest)) destinations.add(dest);
                if (!prices.contains(prc)) prices.add(prc);
            }
            rs.close();

            cbSource.setModel(new DefaultComboBoxModel<>(sources));
            cbDestination.setModel(new DefaultComboBoxModel<>(destinations));
            cbPrice.setModel(new DefaultComboBoxModel<>(prices));

            String passengerQuery = "SELECT username FROM passenger";
            rs = dbConnection.stm.executeQuery(passengerQuery);
            Vector<String> usernames = new Vector<>();
            while (rs.next()) {
                usernames.add(rs.getString("username"));
            }
            rs.close();
            cbUsername.setModel(new DefaultComboBoxModel<>(usernames));

            if (cbUsername.getItemCount() > 0) {
                loadPassengerName((String) cbUsername.getItemAt(0));
            }
            updateFlightCodeFromSelection();

        } catch (SQLException ex) {
            System.out.println("Notice populating initial fields: " + ex.getMessage());
        } finally {
            if (dbConnection != null) dbConnection.closeResources();
        }
    }

    private void updateFlightCodeFromSelection() {
        String src = (String) cbSource.getSelectedItem();
        String dest = (String) cbDestination.getSelectedItem();
        if (src == null || dest == null) return;

        try {
            dbConnection = new AirlineManagementSystem();
            if (dbConnection.stm == null) return;
            String query = "SELECT flight_code, price FROM flight WHERE source = '" + src + "' AND destination = '" + dest + "'";
            try (ResultSet rs = dbConnection.stm.executeQuery(query)) {
                if (rs.next()) {
                    tfFlightCode.setText(rs.getString("flight_code"));
                    cbPrice.setSelectedItem(String.valueOf(rs.getDouble("price")));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error updating flight code: " + ex.getMessage());
        } finally {
            if (dbConnection != null) dbConnection.closeResources();
        }
    }

    private void loadPassengerName(String username) {
        if (username == null || username.isEmpty()) {
            return;
        }

        try {
            dbConnection = new AirlineManagementSystem();
            if (dbConnection.stm == null) return;
            String query = "SELECT name FROM passenger WHERE username = '" + username + "'";
            try (ResultSet rs = dbConnection.stm.executeQuery(query)) {
                if (rs.next()) {
                    tfName.setText(rs.getString("name"));
                } else {
                    tfName.setText("");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error loading passenger name: " + ex.getMessage());
        } finally {
            if (dbConnection != null) dbConnection.closeResources();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        if (ie.getStateChange() == ItemEvent.SELECTED) {
            if (ie.getSource() == cbUsername) {
                String selectedUsername = (String) cbUsername.getSelectedItem();
                loadPassengerName(selectedUsername);
            } else if (ie.getSource() == cbSource || ie.getSource() == cbDestination) {
                updateFlightCodeFromSelection();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            String ticketId = (String) cbTicketId.getSelectedItem();
            String source = (String) cbSource.getSelectedItem();
            String destination = (String) cbDestination.getSelectedItem();
            String classType = (String) cbClass.getSelectedItem();
            String price = (String) cbPrice.getSelectedItem();
            String journeyDate = tfJourneyDate.getText();
            String username = (String) cbUsername.getSelectedItem();
            String name = tfName.getText();
            String flightCode = tfFlightCode.getText();

            if (ticketId == null || ticketId.isEmpty()) {
                ticketId = "TKT-" + (1000 + new Random().nextInt(9000));
            }

            if (source == null || source.isEmpty() || username == null || username.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please ensure a valid Flight and Passenger are selected.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                dbConnection = new AirlineManagementSystem();
                if (dbConnection.stm == null) return;

                String query = "INSERT INTO bookflight(Ticket_Id, Source, Destination, Class, Price, Journey_Date, Username, Name, flight_code, Booking_Status) VALUES ('"
                             + ticketId + "', '" + source + "', '" + destination + "', '" + classType + "', '" + price + "', '" + journeyDate + "', '" + username + "', '" + name + "', '" + flightCode + "', 'Booked')";

                dbConnection.stm.executeUpdate(query);

                // Also insert into payment table for reference
                try {
                    double amt = Double.parseDouble(price != null ? price : "0");
                    String payQuery = "INSERT INTO payment (Ticket_Id, Username, Amount, Status) VALUES ('" + ticketId + "', '" + username + "', " + amt + ", 'Paid')";
                    dbConnection.stm.executeUpdate(payQuery);
                } catch (Exception payEx) {
                    // non-fatal
                }

                JOptionPane.showMessageDialog(null, "Your Flight Successfully Booked! (Ticket ID: " + ticketId + ")", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (HeadlessException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Booking failed: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (dbConnection != null) dbConnection.closeResources();
            }

        } else if (e.getSource() == bt2) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new BookFlight();
    }
}
