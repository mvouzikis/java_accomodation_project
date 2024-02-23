import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


//Αρχικό παράθυρο της εφαρμογής
public class MainFrame extends JFrame {

    public MainFrame(HashMap<String, User> usersList, HashSet<Customer> customerList, HashSet<Provider> providerList, HashMap<Integer, HashSet<House>> houseList, HashSet<Reservation> reservations, HashMap<Integer, ArrayList<Message>> allMessages, HashMap<Integer, HashSet<ImageIcon>> allImages){
        JFrame mainframe = new JFrame("Main Frame");
        mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainframe.setSize(1000, 750);
        mainframe.getContentPane().setBackground(Color.blue.darker().darker().darker().darker());
        mainframe.setBackground(Color.blue.darker().darker().darker().darker());
        mainframe.setLocationRelativeTo(null);
        mainframe.setVisible(true);
        mainframe.setResizable(false);

        JPanel mainpanel = new JPanel();
        mainpanel.setBackground(Color.blue.darker().darker().darker().darker());
        mainpanel.setSize(500, 600);
        mainpanel.setLayout(null);
        mainpanel.setLocation(350, 100);
        mainframe.getLayeredPane().add(mainpanel);

        JLabel headerTitle = new JLabel("House Offers");
        headerTitle.setSize(333, 90);
        headerTitle.setForeground(Color.CYAN);
        headerTitle.setLocation(0, 0);
        headerTitle.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5, true));
        headerTitle.setFont(new Font("Calibri", Font.PLAIN, 60));
        mainpanel.add(headerTitle);

        JLabel label1 = new JLabel("Sign up or Log in if you have an account");
        label1.setSize(400,30);
        label1.setForeground(Color.CYAN);
        label1.setLocation(0, 100);
        label1.setFont(new Font("Calibri", Font.PLAIN, 20));
        mainpanel.add(label1);

        JButton signup = new JButton("Sign up");
        signup.setSize(200, 50);
        signup.setLocation(60, 200);
        signup.setFont(new Font("Calibri", Font.PLAIN, 20));
        signup.setBackground(Color.CYAN);
        signup.setFocusPainted(false);
        mainpanel.add(signup);

        JButton login = new JButton("Log in");
        login.setSize(200, 50);
        login.setLocation(60, 300);
        login.setFont(new Font("Calibri", Font.PLAIN, 20));
        login.setBackground(Color.CYAN);
        login.setFocusPainted(false);
        mainpanel.add(login);

        JButton back = new JButton("Back");
        back.setBackground(Color.CYAN);
        back.setFont(new Font("Calibri", Font.PLAIN, 20));
        back.setSize(100, 40);
        back.setLocation(850, 70);
        back.setFocusPainted(false);
        back.setVisible(false);
        mainframe.getLayeredPane().add(back);

        JButton enter = new JButton("Enter");
        enter.setBackground(Color.CYAN);
        enter.setFont(new Font("Calibri", Font.PLAIN, 20));
        enter.setSize(100, 40);
        enter.setLocation(850, 170);
        enter.setFocusPainted(false);
        enter.setVisible(false);
        mainframe.getLayeredPane().add(enter);

        JButton logout = new JButton("Log out");
        logout.setBackground(Color.CYAN);
        logout.setFont(new Font("Calibri", Font.PLAIN, 20));
        logout.setSize(100, 40);
        logout.setLocation(850, 70);
        logout.setFocusPainted(false);
        logout.setVisible(false);
        mainframe.getLayeredPane().add(logout);

        SignUpPanel signUpPanel = new SignUpPanel(usersList, customerList, providerList);
        LogInPanel logInPanel = new LogInPanel(usersList, customerList, providerList);

        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainframe.getLayeredPane().add(signUpPanel);
                mainframe.getLayeredPane().remove(mainpanel);
                back.setVisible(true);
                back.setText("Back");
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainframe.getLayeredPane().add(logInPanel);
                mainframe.getLayeredPane().remove(mainpanel);
                back.setVisible(true);
                enter.setVisible(true);
                back.setText("Back");
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainframe.getLayeredPane().add(mainpanel);
                mainframe.getLayeredPane().removeAll();
                mainframe.getLayeredPane().add(mainpanel);
                mainframe.getLayeredPane().add(back);
                mainframe.getLayeredPane().add(enter);
                mainframe.getLayeredPane().add(logout);
                signUpPanel.clear();
                logInPanel.clear();
                back.setVisible(false);
                enter.setVisible(false);
            }
        });


        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(logInPanel.logged){
                    int tempId = usersList.get(logInPanel.email.getText()).getUserId();
                    if (tempId >= 2000 && tempId <= 2999){
                        Customer currentCustomer = (Customer) usersList.get(logInPanel.email.getText());
                        CustomerPanel customerPanel = new CustomerPanel(currentCustomer, usersList, customerList, providerList, houseList, reservations, allMessages, allImages);
                        mainframe.getLayeredPane().add(customerPanel);
                    } else if (tempId >= 1000 && tempId <= 1999){
                        Provider currentProvider = (Provider) usersList.get(logInPanel.email.getText());
                        ProviderPanel providerPanel = new ProviderPanel(currentProvider, usersList, customerList, providerList, houseList, reservations, allMessages, allImages);
                        mainframe.getLayeredPane().add(providerPanel);
                    } else if (tempId >= 0 && tempId <= 999){
                        Admin currentAdmin = (Admin) usersList.get(logInPanel.email.getText());
                        AdminPanel adminPanel = new AdminPanel(currentAdmin, usersList, customerList, providerList, houseList, reservations, allMessages, allImages);
                        mainframe.getLayeredPane().add(adminPanel);
                    }
                    mainframe.getLayeredPane().remove(logInPanel);
                    enter.setVisible(false);
                    logInPanel.clear();
                    logout.setVisible(true);
                    back.setVisible(false);
                } else {
                    logInPanel.clear();
                    logInPanel.message.setText("Not logged in");
                }
            }
        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainframe.setVisible(false);
                MainFrame mainframe = new MainFrame(usersList, customerList, providerList, houseList, reservations, allMessages, allImages);
            }
        });

        mainframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ObjectOutputStream houseOOS = new ObjectOutputStream(new FileOutputStream("houseList.ser"));
                    houseOOS.writeObject(houseList);

                    System.out.println(usersList);
                    ObjectOutputStream usersOOS = new ObjectOutputStream(new FileOutputStream("usersList.ser"));
                    usersOOS.writeObject(usersList);

                    ObjectOutputStream customersOOS = new ObjectOutputStream(new FileOutputStream("customerList.ser"));
                    customersOOS.writeObject(customerList);

                    ObjectOutputStream providersOOS = new ObjectOutputStream(new FileOutputStream("providerList.ser"));
                    providersOOS.writeObject(providerList);

                    ObjectOutputStream reservationsOOS = new ObjectOutputStream(new FileOutputStream("reservations.ser"));
                    reservationsOOS.writeObject(reservations);

                    ObjectOutputStream messagesOOs = new ObjectOutputStream(new FileOutputStream("allMessages.ser"));
                    messagesOOs.writeObject(allMessages);

                    ObjectOutputStream imagesOOs = new ObjectOutputStream(new FileOutputStream("allImages.ser"));
                    imagesOOs.writeObject(allImages);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

    }

}
