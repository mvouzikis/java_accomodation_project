import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class AdminPanel extends JPanel {

    JPanel profileDisplay = new JPanel();
    JPanel viewUsersPanel = new JPanel();
    JPanel viewReservationsPanel = new JPanel();
    JLabel message = new JLabel("");

    public AdminPanel(Admin currentAdmin, HashMap<String, User> usersList, HashSet<Customer> customerList, HashSet<Provider> providerList, HashMap<Integer, HashSet<House>> houseList, HashSet<Reservation> reservations, HashMap<Integer, ArrayList<Message>> allMessages, HashMap<Integer, HashSet<ImageIcon>> allImages) {
        setBackground(Color.blue.darker().darker().darker().darker());
        setBorder(BorderFactory.createLineBorder(Color.blue.darker().darker().darker(), 5));
        setSize(820, 710);
        setLayout(null);
        setVisible(true);

        profileDisplay.setLocation(170, 0);
        profileDisplay.setSize(500, 705);
        profileDisplay.setBackground(Color.blue.darker().darker().darker());
        profileDisplay.setLayout(null);
        profileDisplay.setVisible(true);
        add(profileDisplay);

        JLabel profileHeader = new JLabel("Profile");
        profileHeader.setForeground(Color.CYAN);
        profileHeader.setFont(new Font("Calibri", Font.PLAIN, 40));
        profileHeader.setSize(130, 50);
        profileHeader.setLocation(80, 50);
        profileDisplay.add(profileHeader);

        JLabel fullName = new JLabel("Full Name: " + currentAdmin.getFirstName() + " " + currentAdmin.getLastName());
        fullName.setSize(300, 50);
        fullName.setForeground(Color.CYAN);
        fullName.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.CYAN));
        fullName.setFont(new Font("Calibri", Font.PLAIN, 20));
        fullName.setLocation(80, 150);
        profileDisplay.add(fullName);

        JLabel userName = new JLabel("User Name: " + currentAdmin.getUsername());
        userName.setSize(300, 50);
        userName.setForeground(Color.CYAN);
        userName.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.CYAN));
        userName.setFont(new Font("Calibri", Font.PLAIN, 20));
        userName.setLocation(80, 200);
        profileDisplay.add(userName);

        JLabel email = new JLabel("Email: " + currentAdmin.getEmail());
        email.setSize(300, 50);
        email.setForeground(Color.CYAN);
        email.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.CYAN));
        email.setFont(new Font("Calibri", Font.PLAIN, 20));
        email.setLocation(80, 250);
        profileDisplay.add(email);

        JLabel id = new JLabel("ID: " + currentAdmin.getUserId());
        id.setSize(300, 50);
        id.setForeground(Color.CYAN);
        id.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.CYAN));
        id.setFont(new Font("Calibri", Font.PLAIN, 20));
        id.setLocation(80, 300);
        profileDisplay.add(id);

        JLabel type = new JLabel("User Type: Provider");
        type.setSize(300, 50);
        type.setForeground(Color.CYAN);
        type.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.CYAN));
        type.setFont(new Font("Calibri", Font.PLAIN, 20));
        type.setLocation(80, 350);
        profileDisplay.add(type);

        JButton toProfile = new JButton("Profile");
        toProfile.setVisible(true);
        toProfile.setBackground(Color.CYAN);
        toProfile.setSize(145, 40);
        toProfile.setFont(new Font("Calibri", Font.PLAIN, 18));
        toProfile.setLocation(15, 100);
        toProfile.setFocusPainted(false);
        add(toProfile);

        toProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewUsersPanel.setVisible(false);
                viewReservationsPanel.setVisible(false);
                remove(viewReservationsPanel);
                remove(viewUsersPanel);
                add(profileDisplay);
                profileDisplay.setVisible(true);

                message.setText("");
            }
        });

        viewUsersPanel.setLocation(170, 0);
        viewUsersPanel.setSize(500, 705);
        viewUsersPanel.setBackground(Color.blue.darker().darker().darker());
        viewUsersPanel.setLayout(null);
        viewUsersPanel.setVisible(false);
        add(viewUsersPanel);

        DefaultListModel<User> ids = new DefaultListModel<>();

        JList<User> userIds = new JList<>(ids);
        userIds.setSize(500, 300);
        userIds.setLocation(0, 100);
        userIds.setVisible(true);
        userIds.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        userIds.setBackground(Color.blue.darker().darker().darker().darker());
        userIds.setFont(new Font("Calibri", Font.PLAIN, 18));
        userIds.setForeground(Color.CYAN);
        viewUsersPanel.add(userIds);

        for (User u : usersList.values()) {
            if (u.getUserId() >= 1000) {
                ids.addElement(u);
            }
        }

        JScrollPane sp = new JScrollPane(userIds);
        sp.setSize(500, 300);
        sp.setLocation(0, 100);
        sp.setVisible(true);
        sp.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        sp.setBackground(Color.blue.darker().darker().darker().darker());
        sp.setFont(new Font("Calibri", Font.PLAIN, 18));
        sp.setForeground(Color.CYAN);
        viewUsersPanel.add(sp);

        JButton deleteUser = new JButton("Ban User");
        deleteUser.setFocusPainted(false);
        deleteUser.setSize(150, 50);
        deleteUser.setLocation(30, 450);
        deleteUser.setFont(new Font("Calibri", Font.PLAIN, 20));
        deleteUser.setBackground(Color.CYAN);
        viewUsersPanel.add(deleteUser);

        JButton messageUser = new JButton("Message User");
        messageUser.setFocusPainted(false);
        messageUser.setSize(150, 50);
        messageUser.setLocation(250, 450);
        messageUser.setFont(new Font("Calibri", Font.PLAIN, 20));
        messageUser.setBackground(Color.CYAN);
        viewUsersPanel.add(messageUser);

        JTextField messageField = new JTextField("Write your message here");
        messageField.setSize(370, 100);
        messageField.setLocation(30, 550);
        messageField.setBackground(Color.BLUE.darker().darker().darker().darker());
        messageField.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        messageField.setFont(new Font("Calibri", Font.PLAIN, 20));
        messageField.setVisible(false);
        messageField.setForeground(Color.CYAN);
        messageField.setHorizontalAlignment(SwingConstants.LEFT);
        viewUsersPanel.add(messageField);

        JButton messageDone = new JButton("Done");
        messageDone.setFocusPainted(false);
        messageDone.setSize(100, 40);
        messageDone.setLocation(160, 670);
        messageDone.setFont(new Font("Calibri", Font.PLAIN, 20));
        messageDone.setBackground(Color.CYAN);
        messageDone.setVisible(false);
        viewUsersPanel.add(messageDone);

        message.setFont(new Font("Calibri", Font.PLAIN, 20));
        message.setForeground(Color.CYAN);
        message.setSize(300, 30);
        message.setLocation(100, 530);
        message.setVisible(true);
        viewUsersPanel.add(message);

        deleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User selectedUser = userIds.getSelectedValue();
                int selectedUserId = selectedUser.getUserId();
                usersList.remove(selectedUser.getEmail(), selectedUser);

                if (selectedUserId >= 1000 && selectedUserId <= 1999) {
                    providerList.remove(selectedUser);
                } else if (selectedUserId >= 2000 && selectedUserId <= 2999) {
                    customerList.remove(selectedUser);
                }

                ids.removeElement(selectedUser);
                message.setText("User banned successfully");
            }
        });

        messageUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewUsersPanel.add(messageField);
                viewUsersPanel.add(messageDone);

                messageField.setVisible(true);
                messageDone.setVisible(true);
            }
        });

        messageDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User selectedUser = userIds.getSelectedValue();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime tempDate = LocalDateTime.now();

                Message tempMessage = new Message();
                tempMessage.setMessage(messageField.getText());
                tempMessage.setSenderId(currentAdmin.getUserId());
                tempMessage.setReceiverId(selectedUser.getUserId());
                tempMessage.setDateOfMessage(tempDate);

                if(allMessages.containsKey(selectedUser.getUserId())) {
                    for (int id : allMessages.keySet()) {
                        if (id == selectedUser.getUserId()) {
                            allMessages.get(id).add(tempMessage);
                        }
                    }
                } else {
                    ArrayList<Message> tempArrayMessages = new ArrayList<>();
                    tempArrayMessages.add(tempMessage);
                    allMessages.put(selectedUser.getUserId(), tempArrayMessages);
                }

                messageField.setVisible(false);
                messageDone.setVisible(false);
                viewUsersPanel.remove(messageField);
                viewUsersPanel.remove(messageDone);
                messageField.setText("");
            }
        });

        JButton toUsersPanel = new JButton("View Users");
        toUsersPanel.setVisible(true);
        toUsersPanel.setBackground(Color.CYAN);
        toUsersPanel.setSize(145, 40);
        toUsersPanel.setFont(new Font("Calibri", Font.PLAIN, 18));
        toUsersPanel.setLocation(15, 160);
        toUsersPanel.setFocusPainted(false);
        add(toUsersPanel);

        toUsersPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profileDisplay.setVisible(false);
                viewReservationsPanel.setVisible(false);
                remove(viewReservationsPanel);
                remove(profileDisplay);
                add(viewUsersPanel);
                viewUsersPanel.setVisible(true);

                message.setText("");
            }
        });

        JButton toReservationsPanel = new JButton("View Reservations");
        toReservationsPanel.setVisible(true);
        toReservationsPanel.setBackground(Color.CYAN);
        toReservationsPanel.setSize(145, 40);
        toReservationsPanel.setFont(new Font("Calibri", Font.PLAIN, 14));
        toReservationsPanel.setLocation(15, 220);
        toReservationsPanel.setFocusPainted(false);
        add(toReservationsPanel);

        viewReservationsPanel.setLocation(170, 0);
        viewReservationsPanel.setSize(500, 705);
        viewReservationsPanel.setBackground(Color.blue.darker().darker().darker());
        viewReservationsPanel.setLayout(null);
        viewReservationsPanel.setVisible(false);
        add(viewReservationsPanel);

        JComboBox<String> reservationIds = new JComboBox<>();
        reservationIds.setFont(new Font("Calibri", Font.PLAIN, 20));
        reservationIds.setForeground(Color.CYAN);
        reservationIds.setBackground(Color.blue.darker().darker().darker().darker());
        reservationIds.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        reservationIds.setSize(150, 30);
        reservationIds.setLocation(170, 100);
        viewReservationsPanel.add(reservationIds);
        for (Reservation r : reservations) {
            reservationIds.addItem(String.valueOf(r.getReservationId()));
        }

        JPanel resDisplay = new JPanel(null);
        resDisplay.setSize(400, 350);
        resDisplay.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        resDisplay.setLocation(50, 250);
        resDisplay.setBackground(Color.blue.darker().darker().darker().darker());
        resDisplay.setVisible(true);
        viewReservationsPanel.add(resDisplay);

        JLabel houseId = new JLabel("House's ID: ");
        houseId.setSize(200, 30);
        houseId.setLocation(20, 40);
        houseId.setFont(new Font("Calibri", Font.PLAIN, 18));
        houseId.setForeground(Color.CYAN);
        resDisplay.add(houseId);

        JLabel checkIn = new JLabel("Check in: ");
        checkIn.setSize(250, 30);
        checkIn.setLocation(20, 80);
        checkIn.setFont(new Font("Calibri", Font.PLAIN, 18));
        checkIn.setForeground(Color.CYAN);
        resDisplay.add(checkIn);

        JLabel checkOut = new JLabel("Check out: ");
        checkOut.setSize(250, 30);
        checkOut.setLocation(20, 120);
        checkOut.setFont(new Font("Calibri", Font.PLAIN, 18));
        checkOut.setForeground(Color.CYAN);
        resDisplay.add(checkOut);

        JLabel city = new JLabel("City: ");
        city.setSize(200, 30);
        city.setLocation(20, 160);
        city.setFont(new Font("Calibri", Font.PLAIN, 18));
        city.setForeground(Color.CYAN);
        resDisplay.add(city);

        JLabel address = new JLabel("Address: ");
        address.setSize(200, 30);
        address.setLocation(20, 200);
        address.setFont(new Font("Calibri", Font.PLAIN, 18));
        address.setForeground(Color.CYAN);
        resDisplay.add(address);

        JLabel price = new JLabel("Price per Night: ");
        price.setSize(200, 30);
        price.setLocation(20, 240);
        price.setFont(new Font("Calibri", Font.PLAIN, 18));
        price.setForeground(Color.CYAN);
        resDisplay.add(price);

        JLabel reservationId = new JLabel("Reservation ID: ");
        reservationId.setSize(200, 30);
        reservationId.setLocation(20, 280);
        reservationId.setFont(new Font("Calibri", Font.PLAIN, 18));
        reservationId.setForeground(Color.CYAN);
        resDisplay.add(reservationId);

        reservationIds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                String selectedNow = (String) reservationIds.getSelectedItem();
                assert selectedNow != null;
                for (Reservation r : reservations) {

                    if (Integer.parseInt(selectedNow) == r.getReservationId()) {
                        houseId.setText("House's ID: " + r.getHouse().getHouseId());
                        checkIn.setText("Check in: " + sdf.format(r.getCheckInDate()));
                        checkOut.setText("Check out: " + sdf.format(r.getCheckOutDate()));
                        city.setText("City: " + r.getHouse().getCity());
                        address.setText("Address: " + r.getHouse().getAddress());
                        price.setText("Price per Night: " + r.getHouse().getPrice() + "€");
                        reservationId.setText("Reservation ID: " + r.getReservationId());
                    } else if (selectedNow == null) {
                        houseId.setText("House's ID: -/-");
                        checkIn.setText("Check in: -/-");
                        checkOut.setText("Check out: -/-");
                        city.setText("City: -/-");
                        address.setText("Address: -/-");
                        price.setText("Price per Night: -/-");
                        reservationId.setText("Reservation ID: -/-");
                    }
                }
            }
        });

        toReservationsPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profileDisplay.setVisible(false);
                viewUsersPanel.setVisible(false);
                remove(profileDisplay);
                remove(viewUsersPanel);
                add(viewReservationsPanel);
                viewReservationsPanel.setVisible(true);

                message.setText("");
            }
        });

    }
}