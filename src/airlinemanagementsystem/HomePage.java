package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class HomePage extends JFrame implements ActionListener {
    
    JMenuBar mb;
    JMenuItem miBookFlight, miFlightZone, miCancelTicket, miCheckPayment, miAddPassengerProfile, miUpdatePassenger, miExit; 

    public HomePage() {
        setTitle("Airlines SriLanka Home Page");
        setSize(1000, 600);
        setLocation(200, 100);
        
        JLabel background = new JLabel();
        background.setBounds(0, 0, 1000, 600);
        
        URL imgPath = ClassLoader.getSystemResource("airlinemanagementsystem/icons/9.jpg"); 
        
        if (imgPath != null) {
            ImageIcon img = new ImageIcon(imgPath);
            Image i2 = img.getImage().getScaledInstance(1000, 600, Image.SCALE_SMOOTH);
            background.setIcon(new ImageIcon(i2));
        } else {
            background.setBackground(Color.BLUE);
            background.setOpaque(true);
        }
        add(background);

        mb = new JMenuBar();
        
        JMenu menuPassengerProfile = new JMenu("Passenger Profile"); 
        JMenu menuManagePassenger = new JMenu("Manage Passenger"); 
        JMenu menuFlight = new JMenu("Your Flight"); 
        JMenu menuFlightDetails = new JMenu("Flight Details"); 
        JMenu menuCancellation = new JMenu("Cancellation"); 
        JMenu menuBill = new JMenu("Bill"); 
        JMenu menuLogout = new JMenu("Logout"); 
        
        mb.add(menuPassengerProfile);
        mb.add(menuManagePassenger);
        mb.add(menuFlight);
        mb.add(menuFlightDetails);
        mb.add(menuCancellation);
        mb.add(menuBill);
        mb.add(menuLogout); 
        
        miAddPassengerProfile = new JMenuItem("Add Passenger Profile");
        menuPassengerProfile.add(miAddPassengerProfile);
        
        miUpdatePassenger = new JMenuItem("Update Passenger Details");
        menuManagePassenger.add(miUpdatePassenger);
        
        miBookFlight = new JMenuItem("Book Flight");
        menuFlight.add(miBookFlight);
        
        miFlightZone = new JMenuItem("Flight Zone");
        menuFlightDetails.add(miFlightZone);

        miCancelTicket = new JMenuItem("Cancel Ticket");
        menuCancellation.add(miCancelTicket);

        miCheckPayment = new JMenuItem("Check Payment");
        menuBill.add(miCheckPayment);
        
        miExit = new JMenuItem("Logout");
        menuLogout.add(miExit);
       
        miAddPassengerProfile.addActionListener(this);
        miUpdatePassenger.addActionListener(this);
        miBookFlight.addActionListener(this);
        miFlightZone.addActionListener(this);
        miCancelTicket.addActionListener(this);
        miCheckPayment.addActionListener(this);
        miExit.addActionListener(this); 

        setJMenuBar(mb);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();
        
        switch (msg) {
            case "Add Passenger Profile" -> new AddPassengerProfile();
            case "Update Passenger Details" -> new UpdatePassengerDetails();
            case "Book Flight" -> new BookFlight();
            case "Flight Zone" -> new FlightZone();
            case "Cancel Ticket", "Cancellation" -> new CancelTicket();
            case "Check Payment" -> new CheckPayment();
            case "Logout", "Exit" -> new Exit(this);
            default -> {
            }
        }
    }

    public static void main(String[] args) {
        new AirlineManagementSystem(); 
        new HomePage();
    }

    private static class Exit {
        public Exit(JFrame parent) {
            int choice = JOptionPane.showConfirmDialog(parent, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                if (parent != null) {
                    parent.dispose();
                }
                new Login();
            }
        }
    }
}
