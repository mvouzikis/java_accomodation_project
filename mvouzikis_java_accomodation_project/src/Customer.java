import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.*;


public class Customer extends User{
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

    public HashSet<Reservation> myReservations;
    public HashSet<Reservation> myCanceledReservations;
    public ArrayList<Message> incomingMessages;

    public Customer() {
        super();
    }

    public Customer(String firstName, String lastName, String username, String password, String email, int userId) {
        super(firstName, lastName, username, password, email, userId);
        myReservations = new HashSet<>();
        incomingMessages = new ArrayList<>();
    }

    public void displayCustomer() {
        System.out.println("Αριθμός χρήστη : " + getUserId() + "Όνομα : " + getFirstName() + " " + getLastName() + "email : " + getEmail() + "\n");
    }

    public void viewProfileStatistics() {
        System.out.println("Όνομα: " + getFirstName() + " " + getLastName() + "\n");
        System.out.println("Λίστα με τις ενεργές κρατήσεις\n");

        for (Reservation r : myReservations) {
            r.displayReservation();
        }

        System.out.println("Λίστα με τις ακυρώσεις\n");

        for (Reservation r : myCanceledReservations) {
            r.displayReservation();
        }

    }

    public boolean overlaps(House house, Date checkInDate, Date checkOutDate, HashSet<Reservation> reservations) {

        boolean overlaps = false;
        for (Reservation r : reservations) {
            if (house.getHouseId() == r.getHouse().getHouseId()) {
                if (checkInDate.before(r.getCheckInDate()) && checkOutDate.after(r.getCheckOutDate())) {
                    overlaps = true;
                } else if (checkInDate.equals(r.getCheckInDate()) || checkOutDate.equals(r.getCheckOutDate())) {
                    overlaps = true;
                } else if (checkInDate.after(r.getCheckInDate()) && checkInDate.before(r.getCheckOutDate())) {
                    overlaps = true;
                } else if (checkOutDate.after(r.getCheckInDate()) && checkOutDate.before(r.getCheckOutDate())) {
                    overlaps = true;
                }
            }
        }

        return overlaps;

    }

    public Reservation reserveHouse(Customer customer, House house, Date checkInDate, Date checkOutDate, int people, HashSet<Reservation> reservations, JLabel message) {
        Reservation reservation = null;
        if (reservations != null) {

            if(people > house.getBeds()){
                message.setText("Too many people for this house");
            } else if (!overlaps(house, checkInDate, checkOutDate, reservations)) {

                reservation = new Reservation(customer, house, checkInDate, checkOutDate, people);
                myReservations.add(reservation);
                reservations.add(reservation);
                message.setText("House successfully booked");
                System.out.println(myReservations);
                System.out.println(reservation);


            } else {
                message.setText("There other bookings on the given dates");
            }


        } else {
            if(people > house.getBeds()){
                message.setText("Too many people for this house");
            } else {
                reservation = new Reservation(customer, house, checkInDate, checkOutDate, people);
                myReservations.add(reservation);
                assert reservations != null;
                reservations.add(reservation);
                message.setText("House successfully booked");
                System.out.println(myReservations);
                System.out.println(reservation);
            }
        }
        return reservation;
    }

    public void deleteReservation(int tempid, HashSet<Reservation> reservations) throws ParseException {

        for (Reservation r : reservations) {
            if (myReservations.contains(r)) {
                if (tempid == r.getReservationId()) {
                    myReservations.remove(r);
                    reservations.remove(r);
                }
            }
        }

    }

    public HashSet<House> searchHouses(HashMap<Integer, HashSet<House>> houseList, String city, int rooms, int beds, int maxPrice, int rating) {

        HashSet<House> desiredHouses = new HashSet<>();
        for (HashSet<House> houses : houseList.values()) {
            for (House h : houses) {
                if (beds == h.getBeds() && rooms == h.getRooms() && maxPrice >= h.getPrice() && rating == h.getRating() && Objects.equals(city, h.getCity())) {
                    desiredHouses.add(h);
                }
            }
        }
        return desiredHouses;
    }

    public void freeDates(House tempHouse, Date checkInDate, Date checkOutDate, HashSet<Reservation> reservations, JComboBox<String> box1, JComboBox<String> box2) {
        HashSet<String> freeD = new HashSet<>();
        String sdate = null;

        assert checkInDate != null;
        assert checkOutDate != null;

        int checkInMonth = checkInDate.getMonth();
        int checkInYear = (checkInDate.getYear() - 100);

        YearMonth ymin, ymout;
        ymin = YearMonth.of(1900 + checkInYear, checkInMonth + 1);

        System.out.println("MONTH: " + ymin.getMonth());
        int i = 0;

        for (Reservation r : reservations) {
            if(tempHouse.getHouseId() == r.getHouse().getHouseId()) {
                for (int day = 1; day < (ymin.lengthOfMonth() + 1); day++) {

                    boolean capable = false;
                    if (day < 10) {
                        String d = "0" + String.valueOf(day);
                        String month = String.valueOf(ymin.getMonthValue());
                        if (Integer.parseInt(month) < 10) {
                            sdate = d + "/0" + month + "/" + checkInYear;
                        } else {
                            sdate = d + "/" + month + "/" + checkInYear;
                        }

                        Date tempDateIn = null;
                        try {
                            tempDateIn = sdf.parse(sdate);

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        assert tempDateIn != null;

                        if (!(tempDateIn.after(r.getCheckInDate()) && tempDateIn.before(r.getCheckOutDate()))) {
                            if (!tempDateIn.equals(r.getCheckInDate()) && !tempDateIn.equals(r.getCheckOutDate())) {

                            } else {
                                box1.removeItem(sdate);
                                box2.removeItem(sdate);
                            }
                        } else {
                            box1.removeItem(sdate);
                            box2.removeItem(sdate);
                        }

                    } else {
                        String d = String.valueOf(day);
                        String month = String.valueOf(ymin.getMonthValue());

                        sdate = d + "/0" + month + "/" + checkInYear;

                        Date tempDateIn = null;

                        try {
                            tempDateIn = sdf.parse(sdate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        assert tempDateIn != null;

                        if (!(tempDateIn.after(r.getCheckInDate()) && tempDateIn.before(r.getCheckOutDate()))) {
                            if (!tempDateIn.equals(r.getCheckInDate()) && !tempDateIn.equals(r.getCheckOutDate())) {


                            } else {
                                box1.removeItem(sdate);
                                box2.removeItem(sdate);
                            }
                        } else {
                            box1.removeItem(sdate);
                            box2.removeItem(sdate);
                        }

                    }

                }
            }
        }

    }

}
