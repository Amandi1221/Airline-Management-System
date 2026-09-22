package airlinemanagementsystem;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.*;
import javax.swing.*;

public class AddFlights extends JFrame implements ActionListener {
    JLabel l1;
    JTextField tfFlightCode, tfSource, tfDestination, tfCapacity, tfClassName, tfPrice;
    JButton btAddFlight, btBack;

    public AddFlights() {
        setTitle("Add Airlines Flight");
        setLayout(null);
        setSize(450, 550);
        setLocation(500, 100);

        l1 = new JLabel();
        l1.setBounds(0, 0, 450, 550);
        l1.setLayout(null);
        add(l1);

        URL imgURL = ClassLoader.getSystemResource("airlinemanagementsystem/icons/7.jpg");
        if (imgURL != null) {
            ImageIcon img = new ImageIcon(imgURL);
            Image i2 = img.getImage().getScaledInstance(450, 550, Image.SCALE_SMOOTH);
            l1.setIcon(new ImageIcon(i2));
        } else {
            System.out.println("Image not found. Check src/airlinemanagementsystem/icons/7.jpg");
            l1.setOpaque(true);
            l1.setBackground(Color.LIGHT_GRAY);
        }

        JLabel lTitle = new JLabel("Add Airlines Flight");
        lTitle.setBounds(120, 20, 250, 30);
        lTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
        lTitle.setForeground(Color.WHITE);
        l1.add(lTitle);

        int y_start = 80;
        int y_spacing = 45;

        JLabel lFlightCode = new JLabel("Flight Code:");
        lFlightCode.setBounds(50, y_start, 150, 30);
        lFlightCode.setForeground(Color.WHITE);
        lFlightCode.setFont(new Font("Tahoma", Font.BOLD, 18));
        l1.add(lFlightCode);
        tfFlightCode = new JTextField();
        tfFlightCode.setBounds(200, y_start, 180, 30);
        l1.add(tfFlightCode);

        JLabel lSource = new JLabel("Source:");
        lSource.setBounds(50, y_start + 1 * y_spacing, 150, 30);
        lSource.setForeground(Color.WHITE);
        lSource.setFont(new Font("Tahoma", Font.BOLD, 18));
        l1.add(lSource);
        tfSource = new JTextField();
        tfSource.setBounds(200, y_start + 1 * y_spacing, 180, 30);
        l1.add(tfSource);

        JLabel lDestination = new JLabel("Destination:");
        lDestination.setBounds(50, y_start + 2 * y_spacing, 150, 30);
        lDestination.setForeground(Color.WHITE);
        lDestination.setFont(new Font("Tahoma", Font.BOLD, 18));
        l1.add(lDestination);
        tfDestination = new JTextField();
        tfDestination.setBounds(200, y_start + 2 * y_spacing, 180, 30);
        l1.add(tfDestination);

        JLabel lCapacity = new JLabel("Capacity:");
        lCapacity.setBounds(50, y_start + 3 * y_spacing, 150, 30);
        lCapacity.setForeground(Color.WHITE);
        lCapacity.setFont(new Font("Tahoma", Font.BOLD, 18));
        l1.add(lCapacity);
        tfCapacity = new JTextField();
        tfCapacity.setBounds(200, y_start + 3 * y_spacing, 180, 30);
        l1.add(tfCapacity);

        JLabel lClassName = new JLabel("Class Name:");
        lClassName.setBounds(50, y_start + 4 * y_spacing, 150, 30);
        lClassName.setForeground(Color.WHITE);
        lClassName.setFont(new Font("Tahoma", Font.BOLD, 18));
        l1.add(lClassName);
        tfClassName = new JTextField();
        tfClassName.setBounds(200, y_start + 4 * y_spacing, 180, 30);
        l1.add(tfClassName);

        JLabel lPrice = new JLabel("Price:");
        lPrice.setBounds(50, y_start + 5 * y_spacing, 150, 30);
        lPrice.setForeground(Color.WHITE);
        lPrice.setFont(new Font("Tahoma", Font.BOLD, 18));
        l1.add(lPrice);
        tfPrice = new JTextField();
        tfPrice.setBounds(200, y_start + 5 * y_spacing, 180, 30);
        l1.add(tfPrice);

        btAddFlight = new JButton("Add Flight");
        btAddFlight.setBounds(100, 430, 100, 40);
        btAddFlight.setBackground(Color.GREEN.darker());
        btAddFlight.setForeground(Color.WHITE);
        l1.add(btAddFlight);

        btBack = new JButton("Back");
        btBack.setBounds(250, 430, 100, 40);
        btBack.setBackground(Color.GRAY);
        btBack.setForeground(Color.WHITE);
        l1.add(btBack);

        btAddFlight.addActionListener(this);
        btBack.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btAddFlight) {
            String flightCode = tfFlightCode.getText();
            String source = tfSource.getText();
            String destination = tfDestination.getText();
            String capacity = tfCapacity.getText();
            String className = tfClassName.getText();
            String price = tfPrice.getText();

            if (flightCode.isEmpty() || price.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Flight Code and Price are mandatory.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String query = "INSERT INTO flight(flight_code, source, destination, capacity, price, class_Name) VALUES('" 
                         + flightCode + "','" + source + "','" + destination + "','" 
                         + (capacity.isEmpty() ? "100" : capacity) + "','" + price + "','" + className + "')";

            AirlineManagementSystem dbConnection = null;
            try {
                dbConnection = new AirlineManagementSystem();
                if (dbConnection.stm == null) return;
                dbConnection.stm.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "Flight Data Successfully Inserted.", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error adding flight. Check if Flight Code is duplicated: " + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (dbConnection != null) dbConnection.closeResources();
            }
        } else if (e.getSource() == btBack) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new AddFlights();
    }
}
