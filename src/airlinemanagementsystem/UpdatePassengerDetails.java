package airlinemanagementsystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.*;

public class UpdatePassengerDetails extends JFrame implements ActionListener, ItemListener {

    JButton bt1, bt2;
    JComboBox<String> cb1;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12;
    JTextField tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10; 
    AirlineManagementSystem dbConnection;

    public UpdatePassengerDetails() {
        setTitle("Update Passenger Details"); 
        setLayout(null);
        setSize(900, 600);
        setLocation(300, 100);
        
        l1 = new JLabel();
        l1.setBounds(0, 0, 900, 600);
        l1.setLayout(null); 

        URL imgPath = ClassLoader.getSystemResource("airlinemanagementsystem/icons/13.jpg");
        if (imgPath != null) {
            ImageIcon img = new ImageIcon(imgPath);
            Image i2 = img.getImage().getScaledInstance(900, 600, Image.SCALE_SMOOTH);
            l1.setIcon(new ImageIcon(i2));
        } else {
            System.out.println("Image not found. Check src/airlinemanagementsystem/icons/13.jpg");
            l1.setOpaque(true);
            l1.setBackground(Color.LIGHT_GRAY);
        }
        
        l3 = new JLabel("Update Passenger Details");
        l3.setBounds(250, 20, 600, 40);
        l3.setForeground(Color.BLUE);
        l3.setFont(new Font("Arial", Font.BOLD, 28));
        l1.add(l3);
        
        l2 = new JLabel("Username");
        l2.setBounds(50, 150, 150, 30);
        l2.setFont(new Font("Arial", Font.BOLD, 18));
        l1.add(l2);

        cb1 = new JComboBox<>(); 
        cb1.setBounds(200, 150, 150, 30);
        l1.add(cb1);

        l4 = new JLabel("Name");
        l4.setBounds(450, 150, 200, 30);
        l4.setFont(new Font("Arial", Font.BOLD, 18));
        l1.add(l4);

        tf2 = new JTextField();
        tf2.setBounds(600, 150, 150, 30);
        l1.add(tf2);

        l5 = new JLabel("Age");
        l5.setBounds(50, 200, 100, 30);
        l5.setFont(new Font("Arial", Font.BOLD, 18));
        l1.add(l5);

        tf3 = new JTextField();
        tf3.setBounds(200, 200, 150, 30);
        l1.add(tf3);

        l6 = new JLabel("Date of Birth");
        l6.setBounds(450, 200, 200, 30);
        l6.setFont(new Font("Arial", Font.BOLD, 18));
        l1.add(l6);

        tf4 = new JTextField();
        tf4.setBounds(600, 200, 150, 30);
        l1.add(tf4);

        l7 = new JLabel("Address");
        l7.setBounds(50, 250, 150, 30);
        l7.setFont(new Font("Arial", Font.BOLD, 18));
        l1.add(l7);

        tf5 = new JTextField();
        tf5.setBounds(200, 250, 150, 30);
        l1.add(tf5);

        l8 = new JLabel("Phone");
        l8.setBounds(450, 250, 150, 30);
        l8.setFont(new Font("Arial", Font.BOLD, 18));
        l1.add(l8);

        tf6 = new JTextField();
        tf6.setBounds(600, 250, 150, 30);
        l1.add(tf6);

        l9 = new JLabel("Email");
        l9.setBounds(50, 300, 150, 30);
        l9.setFont(new Font("Arial", Font.BOLD, 18));
        l1.add(l9);

        tf7 = new JTextField();
        tf7.setBounds(200, 300, 150, 30);
        l1.add(tf7);

        l10 = new JLabel("Nationality");
        l10.setBounds(450, 300, 150, 30);
        l10.setFont(new Font("Arial", Font.BOLD, 18));
        l1.add(l10);

        tf8 = new JTextField();
        tf8.setBounds(600, 300, 150, 30);
        l1.add(tf8);

        l11 = new JLabel("Gender");
        l11.setBounds(50, 350, 150, 30);
        l11.setFont(new Font("Arial", Font.BOLD, 18));
        l1.add(l11);

        tf9 = new JTextField();
        tf9.setBounds(200, 350, 150, 30);
        l1.add(tf9);

        l12 = new JLabel("Passport No");
        l12.setBounds(450, 350, 150, 30);
        l12.setFont(new Font("Arial", Font.BOLD, 18));
        l1.add(l12);

        tf10 = new JTextField();
        tf10.setBounds(600, 350, 150, 30);
        l1.add(tf10);

        bt1 = new JButton("Update Passenger");
        bt1.setBounds(250, 450, 160, 40);
        bt1.setBackground(Color.BLACK);
        bt1.setForeground(Color.WHITE);
        l1.add(bt1);

        bt2 = new JButton("Back");
        bt2.setBounds(450, 450, 150, 40);
        bt2.setBackground(Color.RED);
        bt2.setForeground(Color.WHITE);
        l1.add(bt2);

        bt1.addActionListener(this);
        bt2.addActionListener(this);
        cb1.addItemListener(this); 

        populateComboBox();
        
        add(l1); 
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void populateComboBox() {
        try {
            dbConnection = new AirlineManagementSystem();
            if (dbConnection.stm == null) return;
            String query = "SELECT username FROM passenger";
            ResultSet rs = dbConnection.stm.executeQuery(query);
            cb1.removeAllItems();
            while (rs.next()) {
                cb1.addItem(rs.getString("username"));
            }
            if (cb1.getItemCount() > 0) {
                 loadPassengerDetails((String)cb1.getItemAt(0));
            }
        } catch (SQLException ex) {
            System.out.println("Notice loading passenger usernames: " + ex.getMessage());
        } finally {
            if (dbConnection != null) dbConnection.closeResources();
        }
    }

    private void loadPassengerDetails(String username) {
        if (username == null || username.isEmpty()) return;
        try {
            dbConnection = new AirlineManagementSystem();
            if (dbConnection.stm == null) return;
            String query = "SELECT * FROM passenger WHERE username = '" + username + "'";
            ResultSet rs = dbConnection.stm.executeQuery(query);
            if (rs.next()) {
                tf2.setText(rs.getString("name"));
                tf3.setText(rs.getString("age"));
                tf4.setText(rs.getString("dob"));
                tf5.setText(rs.getString("address"));
                tf6.setText(rs.getString("phone"));
                tf7.setText(rs.getString("email"));
                tf8.setText(rs.getString("nationality"));
                tf9.setText(rs.getString("gender"));
                tf10.setText(rs.getString("passport"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (dbConnection != null) dbConnection.closeResources();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        if (ie.getSource() == cb1 && ie.getStateChange() == ItemEvent.SELECTED) {
            loadPassengerDetails((String) cb1.getSelectedItem());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            String username = (String) cb1.getSelectedItem(); 
            String query = "UPDATE passenger SET name='"+tf2.getText()+"', age='"+tf3.getText()+"', dob='"+tf4.getText()+"', address='"+tf5.getText()+"', phone='"+tf6.getText()+"', email='"+tf7.getText()+"', nationality='"+tf8.getText()+"', gender='"+tf9.getText()+"', passport='"+tf10.getText()+"' WHERE username='"+username+"'";
            try {
                dbConnection = new AirlineManagementSystem();
                if (dbConnection.stm == null) return;
                dbConnection.stm.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Successfully Updated");
                dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Update Error: " + ex.getMessage());
            } finally {
                if (dbConnection != null) dbConnection.closeResources();
            }
        }
        if (e.getSource() == bt2) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new UpdatePassengerDetails();
    }
}