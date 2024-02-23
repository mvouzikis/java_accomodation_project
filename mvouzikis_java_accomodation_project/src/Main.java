import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Class Main: Here all the needed data structures and they load
 * their values from the binary files of the project
 */


public class Main {
    /**
     * @param usersList = a hashmap which has emails as keyset and the matching user at values
     * @param customerList = a list with the customers
     * @param providerList = a list with providers
     * @param houseList = hashmap with provider's id as keyset and a hashset of their houses as valyes
     * @param reservations = hashset with all the active reservations
     * @param allMessages = hashmap with user's id's as keyset and their messages as values
     */
    static HashMap<String, User> usersList = new HashMap<>();
    static HashSet<Customer> customerList = new HashSet<>();
    static HashSet<Provider> providerList = new HashSet<>();
    static HashMap<Integer, HashSet<House>> houseList = new HashMap<>();
    static HashSet<Reservation> reservations = new HashSet<>();
    static HashMap<Integer, ArrayList<Message>> allMessages = new HashMap<>();
    static HashMap<Integer, HashSet<ImageIcon>> allImages = new HashMap<>();

    public static void main(String[] args) throws ParseException, IOException, ClassNotFoundException {

        File tempfileHouse = new File("houseList.ser");
        if(tempfileHouse.exists()) {
            ObjectInputStream houseListInput = new ObjectInputStream(new FileInputStream("houseList.ser"));
            houseList = (HashMap<Integer, HashSet<House>>) houseListInput.readObject();
        }
        System.out.println("HouseList" + houseList + "\n\n");

        File tempfileUsers = new File("usersList.ser");
        if(tempfileUsers.exists()) {
            ObjectInputStream usersListInput = new ObjectInputStream(new FileInputStream("usersList.ser"));
            usersList = (HashMap<String, User>) usersListInput.readObject();
            System.out.println("1");
            System.out.println(usersList);
        }

        File tempfileReservations = new File("reservations.ser");
        if(tempfileReservations.exists()){
            ObjectInputStream reservationsInput = new ObjectInputStream(new FileInputStream("reservations.ser"));
            reservations = (HashSet<Reservation>) reservationsInput.readObject();
            System.out.println(reservations);
        } else {
            reservations = new HashSet<>();
            System.out.println(reservations);
        }

        File tempfileMessages = new File("allMessages.ser");
        if(tempfileMessages.exists()){
            ObjectInputStream messagesInput = new ObjectInputStream(new FileInputStream("allMessages.ser"));
            allMessages = (HashMap<Integer, ArrayList<Message>>) messagesInput.readObject();
            System.out.println(allMessages);
        }

        File tempfileImages = new File("allImages.ser");
        if(tempfileImages.exists()){
            ObjectInputStream imagesInput = new ObjectInputStream(new FileInputStream("allImages.ser"));
            allMessages = (HashMap<Integer, ArrayList<Message>>) imagesInput.readObject();
            System.out.println("Images: " + allImages);
        }

        /**
         * Filling the customerList and the providerList using the usersList
         */

        for(User u : usersList.values()){
            if(u.getUserId() >= 1000 && u.getUserId() <= 1999){
                Provider tempP = (Provider) u;
                providerList.add(tempP);
                for(int id : allMessages.keySet()){
                    if(id == tempP.getUserId()){
                        tempP.incomingMessages = allMessages.get(tempP.getUserId());
                    }
                }
            } else if(u.getUserId() >= 2000 && u.getUserId() <= 2999){
                Customer tempC = (Customer) u;
                customerList.add(tempC);
                for(int id : allMessages.keySet()){
                    if(id == tempC.getUserId()){
                        tempC.incomingMessages = allMessages.get(tempC.getUserId());
                    }
                }
            }
        }

        /**
        * A user of every type is being initialized here
         * and their are put on the data structure they must be in
        */


        Admin admin1 = new Admin("Giorgos", "Papadopoulos", "GiorgosP", "giorgos123", "giorgospapa@gmail.com", 0);
        Provider provider1 = new Provider("Giannis", "Ioannou", "GiannisIo", "giannis12", "giannisioan@gmail.com", 1000, 0, 0);
        Customer customer1 = new Customer("Kostas", "Dimitriou", "KostasD", "kostas33", "kostasdim@gmail.com", 2000);

        usersList.put(admin1.getEmail(), admin1);
        usersList.put(provider1.getEmail(), provider1);
        usersList.put(customer1.getEmail(), customer1);
        providerList.add(provider1);
        customerList.add(customer1);

        //Starting main frame
        MainFrame mf = new MainFrame(usersList, customerList, providerList, houseList, reservations, allMessages, allImages);


        }
    }
