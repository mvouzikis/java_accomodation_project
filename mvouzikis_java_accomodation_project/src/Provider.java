import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Provider extends User
{
    public HashSet<House> houses;
    public HashSet<Reservation> myReservations;
    public int reservationCounter = 0;
    public int reservationCancellations = 0;
    public ArrayList<Message> incomingMessages;

    public Provider()
    {
        houses = new HashSet<>();
    }

    public Provider(String firstName, String lastName, String username, String password, String email, int userId, int reservationCounter, int reservationCancellations)
    {
        super(firstName, lastName, username, password, email, userId);
        reservationCounter = 0;
        reservationCancellations = 0;
        houses = new HashSet<>();
        incomingMessages = new ArrayList<>();
    }

    //προσθήκη σπιτιών

    public House addHouse(HashMap<Integer, HashSet<House>> houseList, String city, String address, int rooms, int beds, int price, int rating, JLabel message)
    {
        House tempHouse = null;
        boolean alreadyExists = false;
        message.setVisible(true);

        for(House h : houses){
            if(beds == h.getBeds() && rooms == h.getRooms() && price == h.getPrice() && Objects.equals(city, h.getCity()) && Objects.equals(address, h.getAddress())){
                message.setText("You have already added a house with the same characteristics");
                message.setFont(new Font("Calibri", Font.PLAIN, 18));
                alreadyExists = true;
            }
        }

        if(!alreadyExists){
            tempHouse = new House(beds, rooms, rating, city, address, price);
            houses.add(tempHouse);
            if(houseList.containsKey(getUserId())){
                houseList.get(getUserId()).add(tempHouse);
                message.setText("The house was successfully added and its id is: " + tempHouse.getHouseId());
            } else {
                houseList.put(getUserId(), houses);
                message.setText("The house was successfully added and its id is: " + tempHouse.getHouseId());
            }
        }
        return tempHouse;
    }

    //διαγραφή σπιτιών
    public void deleteHouse(int id, HashMap<Integer, HashSet<House>> houseList, JLabel message)
    {
      for (House h : houses){
          if (id == h.getHouseId()){
              houses.remove(h);
              houseList.get(getUserId()).remove(h);
              System.out.println("Το κατάλυμα αφαιρέθηκε με επιτυχία");
              message.setText("House deleted successfully");
          } else {
              System.out.println("Δεν υπάρχει κατάλυμα με το id που δώσατε");
          }
      }
    }

    //επεξεργασία σπιτιών

    public void editHouse(int id, int beds, int rooms, int rating, int price, String address, String city) {
        boolean found = false;
        for (House h : houses){
            if (id == h.getHouseId()){
                System.out.println("Βρέθηκε κατάλυμα με αυτό το id");
                h.setBeds(beds);
                h.setRooms(rooms);
                h.setPrice(price);
                h.setCity(city);
                h.setAddress(address);
                h.setRating(rating);

                found = true;

                System.out.println("Επιτυχής επεξεργασία καταλύματος");
            }

        }

        if (!found){
            System.out.println("Δεν βρέθηκε κατάλυμα με το αυτό το id");
        }
    }

    public void addToMyReservations(Reservation r)
    {
        myReservations.add(r);
        reservationCounter++;
    }

    public void removeFromMyReservations(Reservation r)
    {
        myReservations.remove(r);
        reservationCancellations++;
    }

    public void viewProfileStatistics()
    {
        System.out.println("Όνομα: " + getUsername() + " Συνολικές κρατήσεις: " + reservationCounter + "Συνολικες ακυρώσεις: " + reservationCancellations + "\n");
        System.out.println("Λίστα με τις ενεργές κρατήσεις\n");

        for(Reservation r : myReservations)
        {
            r.displayReservation();
        }

    }

}
