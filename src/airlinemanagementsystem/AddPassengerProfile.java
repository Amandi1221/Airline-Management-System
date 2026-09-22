package airlinemanagementsystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.SQLException;

public class AddPassengerProfile extends JFrame implements ActionListener {

    JButton bt1, bt2;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10;

    public AddPassengerProfile() {
        setTitle("Add Passenger Details"); 
        getContentPane().setBackground(Color.WHITE); 
        setLayout(null);
        setSize(900, 600);
        setLocation(300, 100);
        
        l1 = new JLabel();
        l1.setBounds(0, 0, 900, 600);
        l1.setLayout(null);

        URL imgURL = ClassLoader.getSystemResource("airlinemanagementsystem/icons/10.jpg");
        if (imgURL != null) {
            ImageIcon img = new ImageIcon(imgURL);
            Image i2 = img.getImage().getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
            l1.setIcon(new ImageIcon(i2));
        } else {
            System.out.println("Image not found. Check src/airlinemanagementsystem/icons/10.jpg");
            l1.setOpaque(true);
            l1.setBackground(Color.LIGHT_GRAY);
        }
        
        l3 = new JLabel("Welcome to Airlines Sri Lanka");
        l3.setBounds(200, 20, 600, 40);
        l3.setForeground(Color.BLACK);
        l3.setFont(new Font("Arial", Font.BOLD, 28));
        l1.add(l3);

        l2 = new JLabel("Username");
        l2.setBounds(50, 150, 150, 30);
        l2.setFont(new Font("Arial", Font.BOLD, 18));
        l1.add(l2);

        tf1 = new JTextField();
        tf1.setBounds(200, 150, 150, 30);
        l1.add(tf1);

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

        bt1 = new JButton("Save");
        bt1.setBounds(250, 450, 150, 40);
        bt1.setBackground(Color.WHITE);
        l1.add(bt1);

        bt2 = new JButton("Close");
        bt2.setBounds(450, 450, 150, 40);
        bt2.setBackground(Color.WHITE);
        bt2.setForeground(Color.RED);
        l1.add(bt2);

        bt1.addActionListener(this);
        bt2.addActionListener(this);

        add(l1);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            String username = tf1.getText();
            String name = tf2.getText();
            String age = tf3.getText();
            String dob = tf4.getText();
            String address = tf5.getText();
            String phone = tf6.getText();
            String email = tf7.getText();
            String nationality = tf8.getText();
            String gender = tf9.getText();
            String passport = tf10.getText();
            
            if (username.isEmpty() || name.isEmpty() || passport.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username, Name, and Passport are required fields.");
                return;
            }

            AirlineManagementSystem obj = null;
            try {
                obj = new AirlineManagementSystem();
                if (obj.stm == null) return;

                String query = "INSERT INTO passenger (username, name, age, dob, address, phone, email, nationality, gender, passport) VALUES ('"
                        + username + "','" + name + "','" + age + "','" + dob + "','" + address + "','" 
                        + phone + "','" + email + "','" + nationality + "','" + gender + "','" + passport + "')";

                obj.stm.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Details Successfully Inserted");
                dispose(); 
            } catch (HeadlessException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Failed to insert data: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (obj != null) obj.closeResources();
            }
        }

        if (e.getSource() == bt2) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new AddPassengerProfile();
    }
}
