import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class LogInPanel extends JPanel{
    JTextField email = new JTextField();
    JPasswordField password = new JPasswordField();
    JLabel message = new JLabel();
    boolean logged = false;

    public LogInPanel(HashMap<String, User> usersList, HashSet<Customer> customerList, HashSet<Provider> providerList){
        System.out.println("---LOG IN---");
        setBackground(Color.blue.darker().darker().darker());
        setSize(350, 500);
        setLocation(350, 100);
        setLayout(null);
        setVisible(true);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setSize(100, 30);
        emailLabel.setLocation(50, 70);
        emailLabel.setBackground(Color.blue.darker().darker().darker().darker());
        emailLabel.setForeground(Color.CYAN);
        emailLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        add(emailLabel);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setSize(100, 30);
        passwordLabel.setLocation(50, 170);
        passwordLabel.setBackground(Color.blue.darker().darker().darker().darker());
        passwordLabel.setForeground(Color.CYAN);
        passwordLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        add(passwordLabel);

        email.setBackground(Color.blue.darker().darker().darker().darker());
        email.setFont(new Font("Calibri", Font.PLAIN, 20));
        email.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        email.setForeground(Color.white);
        email.setSize(250, 40);
        email.setLocation(50, 100);
        add(email);

        password.setBackground(Color.blue.darker().darker().darker().darker());
        password.setFont(new Font("Calibri", Font.PLAIN, 20));
        password.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        password.setForeground(Color.white);
        password.setSize(250, 40);
        password.setLocation(50, 200);
        add(password);

        JButton loginButton = new JButton("Log in");
        loginButton.setLocation(200, 300);
        loginButton.setSize(100, 40);
        loginButton.setBackground(Color.CYAN);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Calibri", Font.PLAIN, 20));
        add(loginButton);

        message.setFont(new Font("Calibri", Font.PLAIN, 20));
        message.setLocation(25, 350);
        message.setForeground(Color.CYAN);
        message.setSize(350, 30);
        add(message);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usersList.containsKey(email.getText())){
                    if (Objects.equals(usersList.get(email.getText()).getPassword(), String.valueOf(password.getPassword()))){
                        message.setText("Logged in successfully");
                        int userId = usersList.get(email.getText()).getUserId();
                        logged = true;
                    } else {
                        message.setText("Password is incorrect");
                    }
                } else {
                    message.setText("A user with this email does not exist");
                }
            }
        });
    }

    public void clear(){
        email.setText("");
        password.setText("");
        message.setText("");
    }

}
