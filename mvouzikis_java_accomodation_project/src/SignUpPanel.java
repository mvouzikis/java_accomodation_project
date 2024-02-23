import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class SignUpPanel extends JPanel {
    Admin admin1 = new Admin("Giorgos", "Papadopoulos", "GiorgosP", "giorgos123", "giorgospapa@gmail.com", 0000);
    public JTextField firstName = new JTextField();
    JTextField lastName = new JTextField();
    JTextField userName = new JTextField();
    JTextField email = new JTextField();
    JPasswordField password = new JPasswordField();
    int userType;

    JLabel message = new JLabel();

    public SignUpPanel(HashMap<String, User> usersList, HashSet<Customer> customerList, HashSet<Provider> providerList){
        System.out.println("SIGN UP!!!");
        setBackground(Color.blue.darker().darker().darker());
        setSize(350, 580);
        setLocation(350, 100);
        setLayout(null);
        setVisible(true);

        JLabel firstNameLabel = new JLabel("First Name");
        JLabel lastNameLabel = new JLabel("Last Name");
        JLabel userNameLabel = new JLabel("User Name");
        JLabel emailLabel = new JLabel("Email");
        JLabel passwordLabel = new JLabel("Password");

        firstNameLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        firstNameLabel.setLocation(50, 50);
        firstNameLabel.setForeground(Color.CYAN);
        firstNameLabel.setSize(200, 30);
        add(firstNameLabel);

        lastNameLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        lastNameLabel.setLocation(50, 130);
        lastNameLabel.setForeground(Color.CYAN);
        lastNameLabel.setSize(200, 30);
        add(lastNameLabel);

        userNameLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        userNameLabel.setLocation(50, 210);
        userNameLabel.setForeground(Color.CYAN);
        userNameLabel.setSize(200, 30);
        add(userNameLabel);

        emailLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        emailLabel.setLocation(50, 290);
        emailLabel.setForeground(Color.CYAN);
        emailLabel.setSize(200, 30);
        add(emailLabel);

        passwordLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        passwordLabel.setLocation(50, 370);
        passwordLabel.setForeground(Color.CYAN);
        passwordLabel.setSize(200, 30);
        add(passwordLabel);

        firstName.setBackground(Color.blue.darker().darker().darker().darker());
        firstName.setFont(new Font("Calibri", Font.PLAIN, 20));
        firstName.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        firstName.setForeground(Color.white);
        firstName.setSize(250, 40);
        firstName.setLocation(50, 80);
        add(firstName);

        lastName.setBackground(Color.blue.darker().darker().darker().darker());
        lastName.setFont(new Font("Calibri", Font.PLAIN, 20));
        lastName.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        lastName.setForeground(Color.white);
        lastName.setSize(250, 40);
        lastName.setLocation(50, 160);
        add(lastName);

        userName.setBackground(Color.blue.darker().darker().darker().darker());
        userName.setFont(new Font("Calibri", Font.PLAIN, 20));
        userName.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        userName.setForeground(Color.white);
        userName.setSize(250, 40);
        userName.setLocation(50, 240);
        add(userName);

        email.setBackground(Color.blue.darker().darker().darker().darker());
        email.setFont(new Font("Calibri", Font.PLAIN, 20));
        email.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        email.setForeground(Color.white);
        email.setSize(250, 40);
        email.setLocation(50, 320);
        add(email);

        password.setBackground(Color.blue.darker().darker().darker().darker());
        password.setFont(new Font("Calibri", Font.PLAIN, 20));
        password.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        password.setForeground(Color.white);
        password.setSize(250, 40);
        password.setLocation(50, 400);
        StringBuilder code = new StringBuilder();
        add(password);

        JButton customerButton = new JButton("Customer");
        JButton providerButton = new JButton("Provider");

        customerButton.setBackground(Color.CYAN);
        customerButton.setFont(new Font("Calibri", Font.PLAIN, 15));
        customerButton.setSize(100, 40);
        customerButton.setLocation(50, 460);
        customerButton.setFocusPainted(false);
        add(customerButton);

        providerButton.setBackground(Color.CYAN);
        providerButton.setFont(new Font("Calibri", Font.PLAIN, 15));
        providerButton.setSize(100, 40);
        providerButton.setLocation(200, 460);
        providerButton.setFocusPainted(false);
        add(providerButton);

        message.setFont(new Font("Calibri", Font.PLAIN, 20));
        message.setLocation(15, 530);
        message.setForeground(Color.CYAN);
        message.setSize(350, 30);
        add(message);

        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userType = 1;
                if(Objects.equals(firstName.getText(), "") || Objects.equals(lastName.getText(), "") || Objects.equals(userName.getText(), "") || Objects.equals(email.getText(), "")){
                    message.setText("There is an empty field");
                } else {
                    if(!customerList.isEmpty()) {
                        for (Customer c : customerList) {
                            if (Objects.equals(email.getText(), c.getEmail()) || usersList.containsKey(email.getText())) {
                                message.setText("User with this email already exists");
                            } else if (Objects.equals(userName.getText(), c.getUsername())) {
                                message.setText("User with this username already exists");
                            } else if (String.valueOf(password.getPassword()).length() < 8) {
                                message.setText("Password must be at least 8 characters");
                            } else {
                                admin1.createUser(usersList, customerList, providerList, userType, firstName.getText(), lastName.getText(), userName.getText(), email.getText(), String.valueOf(password.getPassword()));
                                message.setText("Signed up successfully");
                            }
                        }
                    } else {
                          if (String.valueOf(password.getPassword()).length() < 8) {
                            message.setText("Password must be at least 8 characters");
                        } else {
                            admin1.createUser(usersList, customerList, providerList, userType, firstName.getText(), lastName.getText(), userName.getText(), email.getText(), String.valueOf(password.getPassword()));
                            message.setText("Signed up successfully");
                        }
                    }
                }
            }
        });

        providerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userType = 2;

                if(Objects.equals(firstName.getText(), "") || Objects.equals(lastName.getText(), "") || Objects.equals(userName.getText(), "") || Objects.equals(email.getText(), "")){
                    message.setText("There is an empty field");
                } else {
                    if(!providerList.isEmpty()) {
                        for (Provider p : providerList) {
                            if (Objects.equals(email.getText(), p.getEmail()) || usersList.containsKey(email.getText())) {
                                message.setText("User with this email already exists");
                            } else if (Objects.equals(userName.getText(), p.getUsername())) {
                                message.setText("User with this username already exists");
                            } else if (String.valueOf(password.getPassword()).length() < 8) {
                                message.setText("Password must be at least 8 characters");
                            } else {
                                admin1.createUser(usersList, customerList, providerList, userType, firstName.getText(), lastName.getText(), userName.getText(), email.getText(), String.valueOf(password.getPassword()));
                                message.setText("Signed up successfully");

                            }
                        }
                    } else {
                        if (String.valueOf(password.getPassword()).length() < 8) {
                            message.setText("Password must be at least 8 characters");
                        } else {
                            admin1.createUser(usersList, customerList, providerList, userType, firstName.getText(), lastName.getText(), userName.getText(), email.getText(), String.valueOf(password.getPassword()));
                            message.setText("Signed up successfully");
                        }
                    }
                }
            }
        });
    }

    public void clear(){
        firstName.setText("");
        lastName.setText("");
        userName.setText("");
        email.setText("");
        password.setText("");
        message.setText("");
    }

}

