import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ProviderPanel extends JPanel {

    JPanel profileDisplay = new JPanel();
    JPanel addHousePanel = new JPanel();
    JLabel message = new JLabel("");
    JPanel yourHousesPanel = new JPanel();

    public ProviderPanel(Provider currentProvider, HashMap<String, User> usersList, HashSet<Customer> customerList, HashSet<Provider> providerList, HashMap<Integer, HashSet<House>> houseList, HashSet<Reservation> reservations, HashMap<Integer, ArrayList<Message>> allMessages, HashMap<Integer, HashSet<ImageIcon>> allImages){
        for(Integer id : houseList.keySet()){
            if(currentProvider.getUserId() == id){
                currentProvider.houses = houseList.get(id);
            }
        }

        for(int id : allMessages.keySet()){
            if(id == currentProvider.getUserId()){
                currentProvider.incomingMessages = allMessages.get(id);
            }
        }

        for(House h : currentProvider.houses){
            System.out.println("STARTING IMAGES: " + h.housePhotos);
        }

        messagePanel messagePanel = new messagePanel(allMessages, currentProvider.getUserId());

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

        JLabel fullName = new JLabel("Full Name: " + currentProvider.getFirstName() + " " + currentProvider.getLastName());
        fullName.setSize(300, 50);
        fullName.setForeground(Color.CYAN);
        fullName.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.CYAN));
        fullName.setFont(new Font("Calibri", Font.PLAIN, 20));
        fullName.setLocation(80, 150);
        profileDisplay.add(fullName);

        JLabel userName = new JLabel("User Name: " + currentProvider.getUsername());
        userName.setSize(300, 50);
        userName.setForeground(Color.CYAN);
        userName.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.CYAN));
        userName.setFont(new Font("Calibri", Font.PLAIN, 20));
        userName.setLocation(80, 200);
        profileDisplay.add(userName);

        JLabel email = new JLabel("Email: " + currentProvider.getEmail());
        email.setSize(300, 50);
        email.setForeground(Color.CYAN);
        email.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.CYAN));
        email.setFont(new Font("Calibri", Font.PLAIN, 20));
        email.setLocation(80, 250);
        profileDisplay.add(email);

        JLabel id = new JLabel("ID: " + currentProvider.getUserId());
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
                addHousePanel.setVisible(false);
                yourHousesPanel.setVisible(false);
                messagePanel.setVisible(false);
                remove(yourHousesPanel);
                remove(addHousePanel);
                remove(messagePanel);
                add(profileDisplay);
                profileDisplay.setVisible(true);
                message.setText("");
            }
        });

        addHousePanel.setLocation(170, 0);
        addHousePanel.setSize(500, 705);
        addHousePanel.setBackground(Color.blue.darker().darker().darker());
        addHousePanel.setLayout(null);
        addHousePanel.setVisible(false);
        add(addHousePanel);

        JButton toAddHousePanel = new JButton("Add House");
        toAddHousePanel.setVisible(true);
        toAddHousePanel.setBackground(Color.CYAN);
        toAddHousePanel.setSize(145, 40);
        toAddHousePanel.setFont(new Font("Calibri", Font.PLAIN, 18));
        toAddHousePanel.setLocation(15, 160);
        toAddHousePanel.setFocusPainted(false);
        add(toAddHousePanel);

        JLabel addHouseHeader = new JLabel("Add a new house");
        addHouseHeader.setForeground(Color.CYAN);
        addHouseHeader.setFont(new Font("Calibri", Font.PLAIN, 40));
        addHouseHeader.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.CYAN));
        addHouseHeader.setSize(280, 50);
        addHouseHeader.setLocation(105, 50);
        addHousePanel.add(addHouseHeader);

        JLabel cityLabel = new JLabel("City:");
        cityLabel.setSize(50, 30);
        cityLabel.setLocation(60, 160);
        cityLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        cityLabel.setForeground(Color.CYAN);
        addHousePanel.add(cityLabel);

        JTextField cityField = new JTextField();
        cityField.setBackground(Color.blue.darker().darker().darker());
        cityField.setFont(new Font("Calibri", Font.PLAIN, 20));
        cityField.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        cityField.setForeground(Color.white);
        cityField.setSize(280, 30);
        cityField.setLocation(100, 160);
        cityField.setHorizontalAlignment(SwingConstants.LEFT);
        addHousePanel.add(cityField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setSize(100, 30);
        addressLabel.setLocation(60, 215);
        addressLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        addressLabel.setForeground(Color.CYAN);
        addHousePanel.add(addressLabel);

        JTextField addressField = new JTextField();
        addressField.setBackground(Color.blue.darker().darker().darker());
        addressField.setFont(new Font("Calibri", Font.PLAIN, 20));
        addressField.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        addressField.setForeground(Color.white);
        addressField.setSize(245, 30);
        addressField.setLocation(135, 215);
        addressField.setHorizontalAlignment(SwingConstants.LEFT);
        addHousePanel.add(addressField);

        toAddHousePanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profileDisplay.setVisible(false);
                yourHousesPanel.setVisible(false);
                messagePanel.setVisible(false);
                remove(yourHousesPanel);
                remove(profileDisplay);
                remove(messagePanel);
                add(addHousePanel);
                addHousePanel.setVisible(true);
                message.setText("");
            }
        });

        JLabel priceLabel = new JLabel("Price per Night(€):");
        priceLabel.setSize(150, 30);
        priceLabel.setLocation(60, 275);
        priceLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        priceLabel.setForeground(Color.CYAN);
        addHousePanel.add(priceLabel);

        JTextField priceField = new JTextField();
        priceField.setBackground(Color.blue.darker().darker().darker());
        priceField.setFont(new Font("Calibri", Font.PLAIN, 20));
        priceField.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        priceField.setForeground(Color.white);
        priceField.setSize(60, 30);
        priceField.setLocation(205, 275);
        priceField.setHorizontalAlignment(SwingConstants.LEFT);
        addHousePanel.add(priceField);

        JLabel roomsLabel = new JLabel("Rooms:");
        roomsLabel.setSize(150, 30);
        roomsLabel.setLocation(60, 335);
        roomsLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        roomsLabel.setForeground(Color.CYAN);
        addHousePanel.add(roomsLabel);

        JButton minusButtonRooms = new JButton("-");
        minusButtonRooms.setFocusPainted(false);
        minusButtonRooms.setSize(30, 30);
        minusButtonRooms.setBackground(Color.CYAN);
        minusButtonRooms.setLocation(125, 335);
        minusButtonRooms.setFont(new Font("Calibri", Font.BOLD, 20));
        minusButtonRooms.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        addHousePanel.add(minusButtonRooms);

        JButton plusButtonRooms = new JButton("+");
        plusButtonRooms.setFocusPainted(false);
        plusButtonRooms.setSize(30, 30);
        plusButtonRooms.setBackground(Color.CYAN);
        plusButtonRooms.setLocation(185, 335);
        plusButtonRooms.setFont(new Font("Calibri", Font.BOLD, 20));
        plusButtonRooms.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        addHousePanel.add(plusButtonRooms);

        JLabel roomsResult = new JLabel("0");
        roomsResult.setSize(30, 30);
        roomsResult.setLocation(155, 335);
        roomsResult.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        roomsResult.setFont(new Font("Calibri", Font.PLAIN, 19));
        roomsResult.setHorizontalAlignment(SwingConstants.CENTER);
        roomsResult.setForeground(Color.CYAN);
        addHousePanel.add(roomsResult);

        minusButtonRooms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rooms = Integer.parseInt(roomsResult.getText());
                if(rooms > 0){
                    rooms--;
                    roomsResult.setText(String.valueOf(rooms));
                }

            }
        });

        plusButtonRooms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rooms = Integer.parseInt(roomsResult.getText());
                rooms = rooms + 1;
                roomsResult.setText(String.valueOf(rooms));
            }
        });

        JLabel bedsLabel = new JLabel("Beds:");
        bedsLabel.setSize(100, 30);
        bedsLabel.setLocation(240, 335);
        bedsLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        bedsLabel.setForeground(Color.CYAN);
        addHousePanel.add(bedsLabel);

        JButton minusButtonBeds = new JButton("-");
        minusButtonBeds.setFocusPainted(false);
        minusButtonBeds.setSize(30, 30);
        minusButtonBeds.setBackground(Color.CYAN);
        minusButtonBeds.setLocation(290, 335);
        minusButtonBeds.setFont(new Font("Calibri", Font.BOLD, 20));
        minusButtonBeds.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        addHousePanel.add(minusButtonBeds);

        JButton plusButtonBeds = new JButton("+");
        plusButtonBeds.setFocusPainted(false);
        plusButtonBeds.setSize(30, 30);
        plusButtonBeds.setBackground(Color.CYAN);
        plusButtonBeds.setLocation(350, 335);
        plusButtonBeds.setFont(new Font("Calibri", Font.BOLD, 20));
        plusButtonBeds.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        addHousePanel.add(plusButtonBeds);

        JLabel bedsResult = new JLabel("0");
        bedsResult.setSize(30, 30);
        bedsResult.setLocation(320, 335);
        bedsResult.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        bedsResult.setFont(new Font("Calibri", Font.PLAIN, 19));
        bedsResult.setHorizontalAlignment(SwingConstants.CENTER);
        bedsResult.setForeground(Color.CYAN);
        addHousePanel.add(bedsResult);

        minusButtonBeds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int beds = Integer.parseInt(bedsResult.getText());
                if(beds > 0){
                    beds--;
                    bedsResult.setText(String.valueOf(beds));
                }

            }
        });

        plusButtonBeds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int beds = Integer.parseInt(bedsResult.getText());
                beds = beds + 1;
                bedsResult.setText(String.valueOf(beds));
            }
        });

        JButton addHouse = new JButton("Add new house");
        addHouse.setSize(185,50);
        addHouse.setLocation(60, 420);
        addHouse.setFocusPainted(false);
        addHouse.setFont(new Font("Calibri", Font.PLAIN, 20));
        addHouse.setHorizontalAlignment(SwingConstants.CENTER);
        addHouse.setBackground(Color.CYAN);
        addHousePanel.add(addHouse);

        message.setSize(450, 30);
        message.setFont(new Font("Calibri", Font.PLAIN, 20));
        message.setForeground(Color.CYAN);
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setLocation(20, 500);
        message.setVisible(false);
        addHousePanel.add(message);

        JButton toYourHouses = new JButton("Your Houses");
        toYourHouses.setVisible(true);
        toYourHouses.setBackground(Color.CYAN);
        toYourHouses.setSize(145, 40);
        toYourHouses.setFont(new Font("Calibri", Font.PLAIN, 18));
        toYourHouses.setLocation(15, 220);
        toYourHouses.setFocusPainted(false);
        add(toYourHouses);

        yourHousesPanel.setLocation(170, 0);
        yourHousesPanel.setSize(500, 705);
        yourHousesPanel.setBackground(Color.blue.darker().darker().darker());
        yourHousesPanel.setLayout(null);
        yourHousesPanel.setVisible(false);

        JLabel yourHousesHeader = new JLabel("Your Houses");
        yourHousesHeader.setForeground(Color.CYAN);
        yourHousesHeader.setFont(new Font("Calibri", Font.PLAIN, 40));
        yourHousesHeader.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.CYAN));
        yourHousesHeader.setSize(205, 50);
        yourHousesHeader.setLocation(145, 50);
        yourHousesPanel.add(yourHousesHeader);

        JComboBox<String> houseIds = new JComboBox<>();
        for(House h : currentProvider.houses){
            houseIds.addItem(String.valueOf(h.getHouseId()));
        }
        houseIds.setSize(150,30);
        houseIds.setLocation(170 ,150);
        houseIds.setBackground(Color.blue.darker().darker().darker());
        houseIds.setForeground(Color.CYAN);
        houseIds.setFont(new Font("Calibri", Font.PLAIN, 20));
        houseIds.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        yourHousesPanel.add(houseIds);

        toYourHouses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profileDisplay.setVisible(false);
                addHousePanel.setVisible(false);
                messagePanel.setVisible(false);
                remove(profileDisplay);
                remove(addHousePanel);
                remove(messagePanel);
                add(yourHousesPanel);
                yourHousesPanel.setVisible(true);
                message.setText("");
            }
        });

        addHouse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = cityField.getText();
                String address = addressField.getText();
                int price = Integer.parseInt(priceField.getText());
                int rooms = Integer.parseInt(roomsResult.getText());
                int beds = Integer.parseInt(bedsResult.getText());
                House tempHouse = currentProvider.addHouse(houseList, city, address, rooms, beds, price, 5, message);
                if(!(tempHouse == null)){
                    houseIds.addItem(String.valueOf(tempHouse.getHouseId()));
                }

            }
        });

        JPanel houseDisplay = new JPanel(null);
        houseDisplay.setSize(350, 400);
        houseDisplay.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        houseDisplay.setLocation(70, 250);
        houseDisplay.setBackground(Color.blue.darker().darker().darker().darker());
        houseDisplay.setVisible(true);
        yourHousesPanel.add(houseDisplay);

        JPanel editHousePanel = new JPanel(null);
        editHousePanel.setSize(350, 400);
        editHousePanel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        editHousePanel.setLocation(70, 250);
        editHousePanel.setBackground(Color.blue.darker().darker().darker().darker());
        editHousePanel.setVisible(false);
        yourHousesPanel.add(editHousePanel);

        JLabel city = new JLabel("City:");
        city.setSize(250, 30);
        city.setLocation(20, 20);
        city.setForeground(Color.CYAN);
        city.setFont(new Font("Calibri", Font.PLAIN, 20));
        houseDisplay.add(city);

        JLabel address = new JLabel("Address:");
        address.setSize(250, 30);
        address.setLocation(20, 60);
        address.setForeground(Color.CYAN);
        address.setFont(new Font("Calibri", Font.PLAIN, 20));
        houseDisplay.add(address);

        JLabel rooms = new JLabel("Rooms:");
        rooms.setSize(200, 30);
        rooms.setLocation(20, 100);
        rooms.setForeground(Color.CYAN);
        rooms.setFont(new Font("Calibri", Font.PLAIN, 20));
        houseDisplay.add(rooms);

        JLabel beds = new JLabel("Beds:");
        beds.setSize(200, 30);
        beds.setLocation(20, 140);
        beds.setForeground(Color.CYAN);
        beds.setFont(new Font("Calibri", Font.PLAIN, 20));
        houseDisplay.add(beds);

        JLabel price = new JLabel("Price per Night:");
        price.setSize(200, 30);
        price.setLocation(20, 180);
        price.setForeground(Color.CYAN);
        price.setFont(new Font("Calibri", Font.PLAIN, 20));
        houseDisplay.add(price);

        JLabel rating = new JLabel("Rating: ");
        rating.setSize(200, 30);
        rating.setLocation(20, 220);
        rating.setForeground(Color.CYAN);
        rating.setFont(new Font("Calibri", Font.PLAIN, 20));
        houseDisplay.add(rating);

        JLabel houseId = new JLabel("House ID: ");
        houseId.setSize(200, 30);
        houseId.setLocation(20, 260);
        houseId.setForeground(Color.CYAN);
        houseId.setFont(new Font("Calibri", Font.PLAIN, 20));
        houseDisplay.add(houseId);

        houseIds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) houseIds.getSelectedItem();
                for(House h : currentProvider.houses){
                    if(h.getHouseId() == Integer.parseInt(selected)){
                        city.setText("City: " + h.getCity());
                        address.setText("Address: " + h.getAddress());
                        rooms.setText("Rooms: " + h.getRooms());
                        beds.setText("Beds: " + h.getBeds());
                        price.setText("Price per Night: " + h.getPrice() + "€");
                        rating.setText("Rating: " + h.getRating());
                        houseId.setText("House ID: " + h.getHouseId());
                    }
                }
            }
        });

        JButton delHouse = new JButton("Delete House");
        JButton editHouse = new JButton("Edit House");

        delHouse.setSize(150, 40);
        delHouse.setLocation(20, 300);
        delHouse.setFocusPainted(false);
        delHouse.setBackground(Color.CYAN);
        delHouse.setFont(new Font("Calibri", Font.PLAIN, 20));
        houseDisplay.add(delHouse);

        JButton addPhotos = new JButton("Add Photos");
        addPhotos.setLocation(190, 240);
        addPhotos.setSize(150, 40);
        addPhotos.setFocusPainted(false);
        addPhotos.setBackground(Color.cyan);
        addPhotos.setFont(new Font("Calibri", Font.PLAIN, 20));
        houseDisplay.add(addPhotos);

        addPhotos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) houseIds.getSelectedItem();
                JFileChooser jfc = new JFileChooser();
                ImageIcon tempImg = null;
                File selectedFile = null;
                jfc.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", ImageIO.getReaderFileSuffixes()));
                int result = jfc.showOpenDialog(addHousePanel);
                if (result == jfc.APPROVE_OPTION){
                    selectedFile = jfc.getSelectedFile();
                    System.out.println(selectedFile);
                }

                assert selectedFile != null;
                tempImg = new ImageIcon(selectedFile.getPath());
                for(House h : currentProvider.houses){
                    if(h.getHouseId() == Integer.parseInt(selected)) {
                        h.housePhotos.add(tempImg);
                        if(allImages.containsKey(h.getHouseId())){
                            allImages.get(h.getHouseId()).add(tempImg);
                        } else {
                            HashSet<ImageIcon> tempHashsetImages = new HashSet<>();
                            tempHashsetImages.add(tempImg);
                            allImages.put(h.getHouseId(), tempHashsetImages);
                        }
                    }
                }
                System.out.println("IMAGE: " + tempImg);

            }
        });

        JButton viewPhotos = new JButton("View Photos");
        viewPhotos.setLocation(190, 180);
        viewPhotos.setSize(150, 40);
        viewPhotos.setFocusPainted(false);
        viewPhotos.setBackground(Color.cyan);
        viewPhotos.setFont(new Font("Calibri", Font.PLAIN, 20));
        houseDisplay.add(viewPhotos);

        //ActionListener of viewPhotos button
        viewPhotos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assert houseIds.getSelectedItem() != null;
                String sselectedHouseId = (String) houseIds.getSelectedItem();
                int selectedHouseId = Integer.parseInt(sselectedHouseId);
                for(HashSet<House> houses : houseList.values()){
                    for(House h : houses){
                        if(selectedHouseId == h.getHouseId()){
                            ImagesFrame imagesFrame = new ImagesFrame(allImages, h);
                        }
                    }
                }
            }
        });

        editHouse.setSize(150, 40);
        editHouse.setLocation(190, 300);
        editHouse.setFocusPainted(false);
        editHouse.setBackground(Color.CYAN);
        editHouse.setFont(new Font("Calibri", Font.PLAIN, 20));
        houseDisplay.add(editHouse);

        JButton back = new JButton("Back");
        back.setFocusPainted(false);
        back.setBackground(Color.CYAN);
        back.setSize(60, 30);
        back.setLocation(280,20);
        back.setFont(new Font("Calibri", Font.BOLD, 13));
        editHousePanel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editHousePanel.setVisible(false);
                yourHousesPanel.remove(editHousePanel);
                yourHousesPanel.add(houseDisplay);
                houseDisplay.setVisible(true);
                message.setText("");
            }
        });

        JTextField newCity = new JTextField();
        newCity.setSize(250, 30);
        newCity.setLocation(20, 20);
        newCity.setForeground(Color.CYAN);
        newCity.setBackground(Color.blue.darker().darker().darker().darker());
        newCity.setFont(new Font("Calibri", Font.PLAIN, 20));
        newCity.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        editHousePanel.add(newCity);

        JTextField newAddress = new JTextField();
        newAddress.setSize(250, 30);
        newAddress.setLocation(20, 60);
        newAddress.setForeground(Color.CYAN);
        newAddress.setBackground(Color.blue.darker().darker().darker().darker());
        newAddress.setFont(new Font("Calibri", Font.PLAIN, 20));
        newAddress.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        editHousePanel.add(newAddress);

        JTextField newRooms = new JTextField();
        newRooms.setSize(200, 30);
        newRooms.setLocation(20, 100);
        newRooms.setForeground(Color.CYAN);
        newRooms.setBackground(Color.blue.darker().darker().darker().darker());
        newRooms.setFont(new Font("Calibri", Font.PLAIN, 20));
        newRooms.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        editHousePanel.add(newRooms);

        JTextField newBeds = new JTextField();
        newBeds.setSize(200, 30);
        newBeds.setLocation(20, 140);
        newBeds.setForeground(Color.CYAN);
        newBeds.setBackground(Color.blue.darker().darker().darker().darker());
        newBeds.setFont(new Font("Calibri", Font.PLAIN, 20));
        newBeds.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        editHousePanel.add(newBeds);

        JTextField newPrice = new JTextField();
        newPrice.setSize(200, 30);
        newPrice.setLocation(20, 180);
        newPrice.setForeground(Color.CYAN);
        newPrice.setBackground(Color.blue.darker().darker().darker().darker());
        newPrice.setFont(new Font("Calibri", Font.PLAIN, 20));
        newPrice.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        editHousePanel.add(newPrice);

        JTextField newRating = new JTextField();
        newRating.setSize(200, 30);
        newRating.setLocation(20, 220);
        newRating.setForeground(Color.CYAN);
        newRating.setBackground(Color.blue.darker().darker().darker().darker());
        newRating.setFont(new Font("Calibri", Font.PLAIN, 20));
        newRating.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        editHousePanel.add(newRating);

        JButton update = new JButton("Update House");
        update.setSize(200, 40);
        update.setLocation(20, 270);
        update.setBackground(Color.CYAN);
        update.setFocusPainted(false);
        update.setFont(new Font("Calibri", Font.PLAIN, 20));
        editHousePanel.add(update);

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
                yourHousesPanel.setVisible(false);
                addHousePanel.setVisible(false);
                remove(profileDisplay);
                remove(yourHousesPanel);
                remove(addHousePanel);
                add(messagePanel);
                messagePanel.setVisible(true);
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) houseIds.getSelectedItem();
                assert selected != null;
                int id = Integer.parseInt(selected);
                int beds = Integer.parseInt(newBeds.getText());
                int rooms = Integer.parseInt(newRooms.getText());
                int rating = Integer.parseInt(newRating.getText());
                int price = Integer.parseInt(newPrice.getText());
                String address = newAddress.getText();
                String city = newCity.getText();

                currentProvider.editHouse(id, beds, rooms, rating, price, address, city);
            }
        });

        delHouse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) houseIds.getSelectedItem();
                if(selected != null) {
                    currentProvider.deleteHouse(Integer.parseInt(selected), houseList, message);
                    houseIds.removeItem(selected);
                    city.setText("City:");
                    address.setText("Address:");
                    rooms.setText("Rooms:");
                    beds.setText("Beds:");
                    price.setText("Price:");
                    rating.setText("Rating:");
                    houseId.setText("House ID: ");
                }
            }
        });

        editHouse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) houseIds.getSelectedItem();

                if(selected != null) {
                    houseDisplay.setVisible(false);
                    yourHousesPanel.remove(houseDisplay);
                    yourHousesPanel.add(editHousePanel);
                    editHousePanel.setVisible(true);

                    for(House h : currentProvider.houses){
                        if(h.getHouseId() == Integer.parseInt(selected)){
                            newCity.setText(h.getCity());
                            newAddress.setText(h.getAddress());
                            newRooms.setText(String.valueOf(h.getRooms()));
                            newBeds.setText(String.valueOf(h.getBeds()));
                            newPrice.setText(String.valueOf(h.getPrice()));
                            newRating.setText(String.valueOf(h.getRating()));
                        }
                    }
                }
            }
        });

    }

}
