import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.YearMonth;
import java.util.*;

public class CustomerPanel extends JPanel {

    /**
     * All the diffrent panels used by the customer
     * are initialized here outside the class
     */
    JPanel profileDisplay = new JPanel();
    JPanel myReservations = new JPanel();
    JPanel searchPanel = new JPanel();
    Date chinDate = null;
    Date choutDate = null;
    JLabel message = new JLabel();
    Customer currentCustomer;

    public CustomerPanel() {

    }

    public CustomerPanel(Customer currentCustomer, HashMap<String, User> usersList, HashSet<Customer> customerList, HashSet<Provider> providerList, HashMap<Integer, HashSet<House>> houseList, HashSet<Reservation> reservations, HashMap<Integer, ArrayList<Message>> allMessages, HashMap<Integer, HashSet<ImageIcon>> allImages) {
        /**
         * setting the panel's attributes
         */
        setBackground(Color.blue.darker().darker().darker().darker());
        setBorder(BorderFactory.createLineBorder(Color.blue.darker().darker().darker(), 5));
        setSize(820, 710);
        setLayout(null);
        setVisible(true);

        /**
         * Filling the currentCustomer's incoming messages hashset
         * so that he will be able to see them in his messagesPanel
         */
        for(int id : allMessages.keySet()){
            if(id == currentCustomer.getUserId()){
                currentCustomer.incomingMessages = allMessages.get(id);
            }
        }

        /**
         * Filling the currentCustomer's reservations hashset
         * so that he will be able to see them in his reservationsPanel
         */
        for(Reservation r : reservations) {
            if (r.getCustomer().getUserId() == currentCustomer.getUserId()) {
                currentCustomer.myReservations.add(r);
            }
        }
        System.out.println(allMessages);
        System.out.println(currentCustomer.getUserId());
        messagePanel messagePanel = new messagePanel(allMessages, currentCustomer.getUserId());

        /**
         * Setting the attributes of the panel that displays the profile of
         * the currentCustomer which contains the following JLabels:
         * @param profileHeader = title in the profileDisplay panel
         * @param fullName
         * @param userName
         * @param email
         * @param id
         * @param type
         */
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

        JLabel fullName = new JLabel("Full Name: " + currentCustomer.getFirstName() + " " + currentCustomer.getLastName());
        fullName.setSize(300, 50);
        fullName.setForeground(Color.CYAN);
        fullName.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.CYAN));
        fullName.setFont(new Font("Calibri", Font.PLAIN, 20));
        fullName.setLocation(80, 150);
        profileDisplay.add(fullName);

        JLabel userName = new JLabel("User Name: " + currentCustomer.getUsername());
        userName.setSize(300, 50);
        userName.setForeground(Color.CYAN);
        userName.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.CYAN));
        userName.setFont(new Font("Calibri", Font.PLAIN, 20));
        userName.setLocation(80, 200);
        profileDisplay.add(userName);

        JLabel email = new JLabel("User's Email: " + currentCustomer.getEmail());
        email.setSize(300, 50);
        email.setForeground(Color.CYAN);
        email.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.CYAN));
        email.setFont(new Font("Calibri", Font.PLAIN, 20));
        email.setLocation(80, 250);
        profileDisplay.add(email);

        JLabel id = new JLabel("ID: " + currentCustomer.getUserId());
        id.setSize(300, 50);
        id.setForeground(Color.CYAN);
        id.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.CYAN));
        id.setFont(new Font("Calibri", Font.PLAIN, 20));
        id.setLocation(80, 300);
        profileDisplay.add(id);

        JLabel type = new JLabel("User Type: Customer");
        type.setSize(300, 50);
        type.setForeground(Color.CYAN);
        type.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.CYAN));
        type.setFont(new Font("Calibri", Font.PLAIN, 20));
        type.setLocation(80, 350);
        profileDisplay.add(type);

        /**
         * @param toProfile is the JButoon that navigates the customer to his
         * profileDisplay panel using an actionListener
         */
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
                myReservations.setVisible(false);
                searchPanel.setVisible(false);
                messagePanel.setVisible(false);
                remove(myReservations);
                remove(searchPanel);
                remove(messagePanel);
                add(profileDisplay);
                profileDisplay.setVisible(true);
            }
        });

        /**
         * Setting the attributes and the elements of the panel myReservations
         * which gives the customer the ability to see his reservations using
         * his reservations ids
         *
         * @param myReservations
         * @param reservationHeader = title of the reservations panel
         * @param toReservations = the button that navigates the user to the
         * reservations panel using an actionListener
         * @param reservationComboBox = a JComboBox containing the ids of the customer's
         * reservations. When the customer chooses an id from the combo box, the characteristics
         * of the chosen reservation is displayed in the
         * @param resDisplay panel by the matching JLabels for evey field of the reservation
         */
        myReservations.setBackground(Color.blue.darker().darker().darker());
        myReservations.setSize(500, 705);
        myReservations.setVisible(false);
        myReservations.setLocation(170, 0);
        myReservations.setLayout(null);
        add(myReservations);

        JLabel reservationsHeader = new JLabel("Reservations");
        reservationsHeader.setForeground(Color.CYAN);
        reservationsHeader.setFont(new Font("Calibri", Font.PLAIN, 40));
        reservationsHeader.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.CYAN));
        reservationsHeader.setSize(210, 50);
        reservationsHeader.setLocation(140, 50);
        myReservations.add(reservationsHeader);

        JButton toReservations = new JButton("Reservations");
        toReservations.setVisible(true);
        toReservations.setBackground(Color.CYAN);
        toReservations.setSize(145, 40);
        toReservations.setFont(new Font("Calibri", Font.PLAIN, 18));
        toReservations.setLocation(15, 160);
        toReservations.setFocusPainted(false);
        add(toReservations);

        JLabel resId = new JLabel("Reservation ID:");
        resId.setForeground(Color.CYAN);
        resId.setSize(150, 30);
        resId.setFont(new Font("Calibri", Font.PLAIN, 20));
        resId.setLocation(140, 130);
        myReservations.add(resId);

        JComboBox<String> reservationComboBox = new JComboBox<>();
        reservationComboBox.setBackground(Color.blue.darker().darker().darker().darker());
        reservationComboBox.setForeground(Color.CYAN);
        reservationComboBox.setFont(new Font("Calibri", Font.PLAIN, 20));
        reservationComboBox.setSize(210, 30);
        reservationComboBox.setLocation(140, 160);

        JPanel resDisplay = new JPanel(null);
        resDisplay.setSize(300, 350);
        resDisplay.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        resDisplay.setLocation(100, 250);
        resDisplay.setBackground(Color.blue.darker().darker().darker().darker());
        resDisplay.setVisible(true);
        myReservations.add(resDisplay);

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

        //Adding the ids of the existing customer's reservations, to the combobox
        for (Reservation r : currentCustomer.myReservations) {
            reservationComboBox.addItem(String.valueOf(r.getReservationId()));
        }
        /**
         * Here the actionListener of the
         * @param reservationComboBox is created
         *
         * The action listener sets the JLabels text equal to the text that every
         * field of the chosen reservation has
         */
        reservationComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                String selectedNow = (String) reservationComboBox.getSelectedItem();
                for (Reservation r : currentCustomer.myReservations) {

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

        myReservations.add(reservationComboBox);

        //ActionListener of the toReservations button which navigates the user to the reservations panel
        toReservations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profileDisplay.setVisible(false);
                searchPanel.setVisible(false);
                messagePanel.setVisible(false);
                remove(profileDisplay);
                remove(searchPanel);
                remove(messagePanel);
                add(myReservations);
                myReservations.setVisible(true);
            }
        });


        /**
         * Setting the attributes, the Jlabels and the JTextfields of the searchPanel
         *
         * @param toSearchPanel = JButton that navigates the customer to the search panel
         *
         * The customer fills the JTextfields with the characteristics
         * he wants the house to have.
         *
         * @param houseIds = JList containing the ids of the houses that have the
         * characteristics the customer wants
         */

        searchPanel.setBackground(Color.blue.darker().darker().darker());
        searchPanel.setSize(500, 705);
        searchPanel.setVisible(false);
        searchPanel.setLocation(170, 0);
        searchPanel.setLayout(null);
        add(searchPanel);

        JButton toSearchPanel = new JButton("Search House");
        toSearchPanel.setVisible(true);
        toSearchPanel.setBackground(Color.CYAN);
        toSearchPanel.setSize(145, 40);
        toSearchPanel.setFont(new Font("Calibri", Font.PLAIN, 18));
        toSearchPanel.setLocation(15, 220);
        toSearchPanel.setFocusPainted(false);
        add(toSearchPanel);

        JLabel searchHeader = new JLabel("House Search");
        searchHeader.setForeground(Color.CYAN);
        searchHeader.setFont(new Font("Calibri", Font.PLAIN, 40));
        searchHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.CYAN));
        searchHeader.setSize(225, 50);
        searchHeader.setLocation(135, 50);
        searchPanel.add(searchHeader);

        JLabel cityLabel = new JLabel("City:");
        cityLabel.setBackground(Color.blue.darker().darker().darker().darker());
        cityLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        cityLabel.setForeground(Color.white);
        cityLabel.setSize(50, 40);
        cityLabel.setLocation(95, 150);
        searchPanel.add(cityLabel);

        JTextField cityField = new JTextField();
        cityField.setBackground(Color.blue.darker().darker().darker());
        cityField.setFont(new Font("Calibri", Font.PLAIN, 20));
        cityField.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        cityField.setForeground(Color.white);
        cityField.setSize(265, 30);
        cityField.setLocation(135, 155);
        cityField.setHorizontalAlignment(SwingConstants.LEFT);
        searchPanel.add(cityField);

        JLabel roomsLabel = new JLabel("Rooms:");
        roomsLabel.setBackground(Color.blue.darker().darker().darker().darker());
        roomsLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        roomsLabel.setForeground(Color.white);
        roomsLabel.setSize(65, 40);
        roomsLabel.setLocation(70, 200);
        searchPanel.add(roomsLabel);

        JTextField roomsField = new JTextField();
        roomsField.setBackground(Color.blue.darker().darker().darker());
        roomsField.setFont(new Font("Calibri", Font.PLAIN, 20));
        roomsField.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        roomsField.setForeground(Color.white);
        roomsField.setSize(50, 30);
        roomsField.setLocation(135, 205);
        roomsField.setHorizontalAlignment(SwingConstants.RIGHT);
        searchPanel.add(roomsField);

        JLabel bedsLabel = new JLabel("Beds:");
        bedsLabel.setBackground(Color.blue.darker().darker().darker().darker());
        bedsLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        bedsLabel.setForeground(Color.white);
        bedsLabel.setSize(50, 40);
        bedsLabel.setLocation(300, 200);
        searchPanel.add(bedsLabel);

        JTextField bedsField = new JTextField();
        bedsField.setBackground(Color.blue.darker().darker().darker());
        bedsField.setFont(new Font("Calibri", Font.PLAIN, 20));
        bedsField.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        bedsField.setForeground(Color.white);
        bedsField.setSize(50, 30);
        bedsField.setLocation(348, 205);
        bedsField.setHorizontalAlignment(SwingConstants.RIGHT);
        searchPanel.add(bedsField);

        JLabel priceLabel = new JLabel("Max price P/N:");
        priceLabel.setBackground(Color.blue.darker().darker().darker().darker());
        priceLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        priceLabel.setForeground(Color.white);
        priceLabel.setSize(150, 40);
        priceLabel.setLocation(10, 250);
        searchPanel.add(priceLabel);

        JTextField priceField = new JTextField();
        priceField.setBackground(Color.blue.darker().darker().darker());
        priceField.setFont(new Font("Calibri", Font.PLAIN, 20));
        priceField.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        priceField.setForeground(Color.white);
        priceField.setSize(50, 30);
        priceField.setLocation(135, 255);
        priceField.setHorizontalAlignment(SwingConstants.RIGHT);
        searchPanel.add(priceField);

        JLabel ratingLabel = new JLabel("Stars:");
        ratingLabel.setBackground(Color.blue.darker().darker().darker().darker());
        ratingLabel.setSize(50, 30);
        ratingLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        ratingLabel.setForeground(Color.white);
        ratingLabel.setSize(150, 40);
        ratingLabel.setLocation(300, 250);
        searchPanel.add(ratingLabel);

        JTextField ratingField = new JTextField();
        ratingField.setBackground(Color.blue.darker().darker().darker());
        ratingField.setFont(new Font("Calibri", Font.PLAIN, 20));
        ratingField.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        ratingField.setForeground(Color.white);
        ratingField.setSize(50, 30);
        ratingField.setLocation(348, 255);
        ratingField.setHorizontalAlignment(SwingConstants.RIGHT);
        searchPanel.add(ratingField);

        JButton searchButton = new JButton("Search");
        searchButton.setBackground(Color.CYAN);
        searchButton.setFont(new Font("Calibri", Font.PLAIN, 20));
        searchButton.setSize(100, 50);
        searchButton.setLocation(300, 305);
        searchButton.setFocusPainted(false);
        searchButton.setVisible(true);
        searchPanel.add(searchButton);

        DefaultListModel<String> ids = new DefaultListModel<>();

        JList<String> houseIds = new JList<>(ids);
        houseIds.setSize(100, 250);
        houseIds.setLocation(70, 370);
        houseIds.setVisible(true);
        houseIds.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        houseIds.setBackground(Color.blue.darker().darker().darker().darker());
        houseIds.setFont(new Font("Calibri", Font.PLAIN, 18));
        houseIds.setForeground(Color.CYAN);
        searchPanel.add(houseIds);

        /**
         * @param housePanel = a panel containing JLabels which represent all
         * the fields of the house that is chosen in the JList houseIds.
         * Also contains a button (viewPhotos) which using an action listener creates
         * @param ImageFrame containing all the photos that the provider of the chosen
         * house added.
         */
        JPanel housePanel = new JPanel(null);
        housePanel.setLocation(170, 370);
        housePanel.setSize(260, 250);
        housePanel.setVisible(true);
        housePanel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        housePanel.setBackground(Color.blue.darker().darker().darker().darker());
        searchPanel.add(housePanel);

        JScrollPane sp = new JScrollPane(houseIds);
        sp.setSize(100, 250);
        sp.setLocation(70, 370);
        sp.setVisible(true);
        sp.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        sp.setBackground(Color.blue.darker().darker().darker().darker());
        sp.setFont(new Font("Calibri", Font.PLAIN, 18));
        sp.setForeground(Color.CYAN);
        searchPanel.add(sp);

        JLabel city2 = new JLabel("City:");
        city2.setLocation(5, 5);
        city2.setSize(250, 20);
        city2.setForeground(Color.CYAN);
        city2.setFont(new Font("Calibri", Font.PLAIN, 18));
        housePanel.add(city2);

        JLabel address2 = new JLabel("Address:");
        address2.setLocation(5, 40);
        address2.setSize(250, 20);
        address2.setForeground(Color.CYAN);
        address2.setFont(new Font("Calibri", Font.PLAIN, 18));
        housePanel.add(address2);

        JLabel rooms2 = new JLabel("Rooms:");
        rooms2.setLocation(5, 75);
        rooms2.setSize(250, 20);
        rooms2.setForeground(Color.CYAN);
        rooms2.setFont(new Font("Calibri", Font.PLAIN, 18));
        housePanel.add(rooms2);

        JLabel beds2 = new JLabel("Beds:");
        beds2.setLocation(5, 110);
        beds2.setSize(250, 20);
        beds2.setForeground(Color.CYAN);
        beds2.setFont(new Font("Calibri", Font.PLAIN, 18));
        housePanel.add(beds2);

        JLabel maxPrice = new JLabel("Max price P/N:");
        maxPrice.setLocation(5, 145);
        maxPrice.setSize(250, 20);
        maxPrice.setForeground(Color.CYAN);
        maxPrice.setFont(new Font("Calibri", Font.PLAIN, 18));
        housePanel.add(maxPrice);

        JLabel rating2 = new JLabel("Stars:");
        rating2.setLocation(5, 180);
        rating2.setSize(250, 20);
        rating2.setForeground(Color.CYAN);
        rating2.setFont(new Font("Calibri", Font.PLAIN, 18));
        housePanel.add(rating2);

        JButton viewPhotos = new JButton("View Photos");
        viewPhotos.setLocation(100, 180);
        viewPhotos.setSize(130, 30);
        viewPhotos.setBackground(Color.CYAN);
        viewPhotos.setFont(new Font("Calibri", Font.PLAIN, 14));
        housePanel.add(viewPhotos);

        //ActionListener of viewPhotos button
        viewPhotos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedHouseId =  Integer.parseInt(houseIds.getSelectedValue());
                for(HashSet<House> houses : houseList.values()){
                    for(House h : houses){
                        if(selectedHouseId == h.getHouseId()){
                            ImagesFrame imagesFrame = new ImagesFrame(allImages, h);
                        }
                    }
                }
            }
        });

        /**
         * JPanel reservation panel is created after the customer clicks reserveButton
         * to reserve the house he choosed from the jlist
         *
         * Used in the panel:
         * @param reservationPanel
         * @param month (JLabel)
         * @param monthBox (ComboBox) containing every month of the year
         * @param checkInBox (ComboBox) containing every free date of the month
         * @param checkOutBox (ComboBox) containing every free date of the month
         *
         * Then the user types how many people will be in the house and if they are more
         * than the house's beds they cant book it.
         *
         * If the user puts every value correctly then when he clicks the
         * @param reserve (JButton) the reservation he wants will be created and added
         * to the Hashset reservations with every reservation that is active in the app
         */
        JPanel reservationPanel = new JPanel(null);
        reservationPanel.setSize(400, 250);
        reservationPanel.setLocation(50, 370);
        reservationPanel.setVisible(true);
        reservationPanel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        reservationPanel.setBackground(Color.blue.darker().darker().darker().darker());

        JLabel month = new JLabel("Month:");
        month.setSize(70, 30);
        month.setLocation(25, 5);
        month.setForeground(Color.CYAN);
        month.setFont(new Font("Calibri", Font.PLAIN, 18));
        reservationPanel.add(month);

        JComboBox<String> monthBox = new JComboBox<>();
        monthBox.setSize(80, 30);
        monthBox.setLocation(95, 5);
        monthBox.setForeground(Color.white);
        monthBox.setBackground(Color.blue.darker().darker().darker().darker());
        monthBox.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        monthBox.setFont(new Font("Calibri", Font.PLAIN, 18));
        reservationPanel.add(monthBox);
        YearMonth ymbox = YearMonth.of(2022, 1);
        for(int i = 0; i < 12; i++){
            monthBox.addItem(String.valueOf(ymbox.plusMonths(i).getMonth()));
        }

        JLabel checkIn2 = new JLabel("Check in:");
        checkIn2.setSize(70, 30);
        checkIn2.setLocation(25, 55);
        checkIn2.setForeground(Color.CYAN);
        checkIn2.setFont(new Font("Calibri", Font.PLAIN, 18));
        reservationPanel.add(checkIn2);

        JComboBox<String> checkInBox = new JComboBox<>();
        checkInBox.setSize(80, 30);
        checkInBox.setLocation(95, 55);
        checkInBox.setForeground(Color.white);
        checkInBox.setBackground(Color.blue.darker().darker().darker().darker());
        checkInBox.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        checkInBox.setFont(new Font("Calibri", Font.PLAIN, 18));
        reservationPanel.add(checkInBox);

        JLabel checkOut2 = new JLabel("Check out:");
        checkOut2.setSize(80, 30);
        checkOut2.setLocation(195, 55);
        checkOut2.setForeground(Color.CYAN);
        checkOut2.setFont(new Font("Calibri", Font.PLAIN, 18));
        reservationPanel.add(checkOut2);

        JComboBox<String> checkOutBox = new JComboBox<>();
        checkOutBox.setSize(80, 30);
        checkOutBox.setLocation(275, 55);
        checkOutBox.setForeground(Color.white);
        checkOutBox.setBackground(Color.blue.darker().darker().darker().darker());
        checkOutBox.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        checkOutBox.setFont(new Font("Calibri", Font.PLAIN, 18));
        reservationPanel.add(checkOutBox);
        for(int day = 1; day <= 31; day++){
            String sday;
            String sdate;

            if(day < 10){
                sday = "0" + day;
            } else {
                sday = String.valueOf(day);
            }
            sdate = sday + "/01/22";
            checkInBox.addItem(sdate);
            checkOutBox.addItem(sdate);
        }

        JLabel people = new JLabel("Number of visitors:");
        people.setSize(140, 30);
        people.setLocation(25, 125);
        people.setForeground(Color.CYAN);
        people.setFont(new Font("Calibri", Font.PLAIN, 18));
        reservationPanel.add(people);

        JTextField peopleField = new JTextField();
        peopleField.setSize(80, 30);
        peopleField.setLocation(175, 125);
        peopleField.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        peopleField.setBackground(Color.blue.darker().darker().darker().darker());
        peopleField.setFont(new Font("Calibri", Font.PLAIN, 18));
        peopleField.setForeground(Color.white);
        reservationPanel.add(peopleField);

        JButton reserve = new JButton("Reserve");
        reserve.setFont(new Font("Calibri", Font.PLAIN, 20));
        reserve.setSize(100, 30);
        reserve.setLocation(150, 175);
        reserve.setBackground(Color.CYAN);
        reserve.setFocusPainted(false);
        reserve.setVisible(true);
        reservationPanel.add(reserve);

        JButton reservationButton = new JButton("Reserve this house");
        reservationButton.setSize(200, 40);
        reservationButton.setLocation(150, 650);
        reservationButton.setBackground(Color.CYAN);
        reservationButton.setFont(new Font("Calibri", Font.PLAIN, 20));
        reservationButton.setFocusPainted(false);
        reservationButton.setVisible(false);
        searchPanel.add(reservationButton);

        JButton clear = new JButton("Clear page");
        clear.setSize(100, 30);
        clear.setLocation(380, 665);
        clear.setBackground(Color.CYAN);
        clear.setFont(new Font("Calibri" , Font.PLAIN, 12));
        searchPanel.add(clear);

        JLabel message = new JLabel("");
        message.setSize(300, 30);
        message.setLocation(50, 215);
        message.setForeground(Color.CYAN);
        message.setFont(new Font("Calibri", Font.PLAIN, 16));
        message.setVisible(false);
        message.setHorizontalAlignment(SwingConstants.CENTER);
        reservationPanel.add(message);

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cityField.setText("");
                roomsField.setText("");
                bedsField.setText("");
                priceField.setText("");
                ratingField.setText("");


                reservationPanel.setVisible(false);
                searchPanel.remove(reservationPanel);

                searchPanel.add(houseIds);
                houseIds.setVisible(true);
                houseIds.setLocation(70, 370);

                searchPanel.add(housePanel);
                housePanel.setVisible(true);

                searchPanel.add(sp);
                sp.setVisible(true);
            }
        });

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

        JButton toMessagePanel = new JButton("Messages");
        toMessagePanel.setVisible(true);
        toMessagePanel.setBackground(Color.CYAN);
        toMessagePanel.setSize(145, 40);
        toMessagePanel.setFont(new Font("Calibri", Font.PLAIN, 20));
        toMessagePanel.setLocation(15, 280);
        toMessagePanel.setFocusPainted(false);
        add(toMessagePanel);

        toMessagePanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profileDisplay.setVisible(false);
                myReservations.setVisible(false);
                searchPanel.setVisible(false);
                remove(profileDisplay);
                remove(myReservations);
                remove(searchPanel);
                add(messagePanel);
                messagePanel.setVisible(true);
            }
        });

        monthBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                checkInBox.removeAllItems();
                checkOutBox.removeAllItems();

                String selected = (String) monthBox.getSelectedItem();
                YearMonth yma = YearMonth.of(2022, Month.valueOf(selected));

                for (int day = 1; day <= yma.lengthOfMonth(); day++) {

                    String sday;
                    String smonth;
                    String sdate;

                    if(yma.getMonthValue() < 10){
                        smonth = "0" + yma.getMonthValue();
                    } else {
                        smonth = String.valueOf(yma.getMonthValue());
                    }

                    if(day < 10){
                        sday = "0" + day;
                    } else {
                        sday = String.valueOf(day);
                    }

                    sdate = sday + "/" + smonth + "/22";

                    System.out.println(day);
                    checkInBox.addItem(sdate);
                    checkOutBox.addItem(sdate);
                }

                House tempHouse = null;
                int selectedID = Integer.parseInt(houseIds.getSelectedValue());
                for (HashSet<House> houses : houseList.values()) {
                    for (House h : houses) {
                        if (selectedID == h.getHouseId()) {
                            tempHouse = h;
                        }
                    }
                }
                currentCustomer.freeDates(tempHouse, chinDate, choutDate, reservations, checkInBox, checkOutBox);

            }
        });

        reserve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chin = (String) checkInBox.getSelectedItem();
                String chout = (String) checkOutBox.getSelectedItem();
                String visitors = peopleField.getText();
                int selected = Integer.parseInt(houseIds.getSelectedValue());
                House tempHouse = null;

                if (Objects.equals(chin, "dd/mm/yy") || Objects.equals(chin, "") || Objects.equals(chout, "dd/mm/yy") || Objects.equals(chout, "") || Objects.equals(visitors, "")) {
                    message.setText("One or more fields are empty");
                    message.setVisible(true);
                } /*else if (!correctFormat(chin) || !correctFormat(chout)){
                    message.setText("Please use the correct format dd/mm/yy");
                    message.setVisible(true);
                }*/ else {
                    message.setText("Correct");
                    message.setVisible(true);

                    try {
                        chinDate = sdf.parse(chin);
                        choutDate = sdf.parse(chout);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }

                    for (HashSet<House> houses : houseList.values()) {
                        for (House h : houses) {
                            if (selected == h.getHouseId()) {
                                tempHouse = h;
                            }
                        }
                    }

                    Reservation tempR = currentCustomer.reserveHouse(currentCustomer, tempHouse, chinDate, choutDate, Integer.parseInt(visitors), reservations, message);
                    reservationComboBox.addItem(String.valueOf(tempR.getReservationId()));

                    assert chinDate != null;
                    assert choutDate != null;
                    currentCustomer.freeDates(tempHouse, chinDate, choutDate, reservations, checkInBox, checkOutBox);


                }
            }
        });

        reservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                houseIds.setVisible(false);
                searchPanel.remove(houseIds);

                sp.setVisible(false);
                searchPanel.remove(sp);

                housePanel.setVisible(false);
                searchPanel.remove(housePanel);

                searchPanel.add(reservationPanel);
                reservationPanel.setVisible(true);
            }
        });

        houseIds.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selected = Integer.parseInt(houseIds.getSelectedValue());
                for (HashSet<House> houses : houseList.values()) {
                    for (House h : houses) {
                        if (selected == h.getHouseId()) {
                            city2.setText("City: " + h.getCity());
                            address2.setText("Address: " + h.getAddress());
                            rooms2.setText("Rooms: " + h.getRooms());
                            beds2.setText("Beds: " + h.getBeds());
                            maxPrice.setText("Price P/N: " + h.getPrice());
                            rating2.setText("Stars: " + h.getRating());

                            reservationButton.setVisible(true);
                        }
                    }
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ct = cityField.getText();
                int rooms = Integer.parseInt(roomsField.getText());
                int beds = Integer.parseInt(bedsField.getText());
                int maxPrice = Integer.parseInt(priceField.getText());
                int rating = Integer.parseInt(ratingField.getText());
                HashSet<House> desiredHouses = currentCustomer.searchHouses(houseList, ct, rooms, beds, maxPrice, rating);
                for (House h : desiredHouses) {
                    if (!ids.contains(String.valueOf(h.getHouseId()))) {
                        ids.addElement(String.valueOf(h.getHouseId()));
                    }
                }
            }
        });

        toSearchPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profileDisplay.setVisible(false);
                myReservations.setVisible(false);
                messagePanel.setVisible(false);
                remove(profileDisplay);
                remove(myReservations);
                remove(messagePanel);
                add(searchPanel);
                searchPanel.setVisible(true);
            }
        });



    }

    public boolean correctFormat(String input){
        boolean isCorrect = false;

        if(Objects.equals(String.valueOf(input.charAt(2)), "/") && Objects.equals(String.valueOf(input.charAt(5)), "/")){
            isCorrect = true;
        }

        return isCorrect;

    }

}