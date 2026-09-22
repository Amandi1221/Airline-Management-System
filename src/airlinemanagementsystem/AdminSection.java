package airlinemanagementsystem;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminSection extends JFrame implements ActionListener {
    
    JButton btAddFlights, btUpdateFlights, btAddEmployee;
    
    public AdminSection() {
        setTitle("Admin Section");
        getContentPane().setBackground(new Color(255, 240, 245));
        setLayout(null);
        setSize(400, 350);
        setLocation(500, 200);

        JLabel lTitle = new JLabel("Admin Section");
        lTitle.setBounds(120, 30, 200, 30);
        lTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(lTitle);

        btAddFlights = new JButton("Add Flights");
        btAddFlights.setBounds(100, 100, 200, 40);
        add(btAddFlights);

        btUpdateFlights = new JButton("Update Flights");
        btUpdateFlights.setBounds(100, 160, 200, 40);
        add(btUpdateFlights);

        btAddEmployee = new JButton("Add Airline Employee");
        btAddEmployee.setBounds(100, 220, 200, 40);
        add(btAddEmployee);

        btAddFlights.addActionListener(this);
        btUpdateFlights.addActionListener(this);
        btAddEmployee.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btAddFlights) {
            new AddFlights(); 
        } else if (e.getSource() == btUpdateFlights) {
            new UpdateFlight();
        } else if (e.getSource() == btAddEmployee) {
            new AddAirlinesEmployee(); 
        }
    }

    public static void main(String[] args) {
        new AdminSection();
    }
}
