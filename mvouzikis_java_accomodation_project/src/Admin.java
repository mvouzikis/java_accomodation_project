import java.util.*;

public class Admin extends User
{
    public HashMap<Integer, Collection<Reservation>> reservations = new HashMap<>();

    public Admin(String firstName, String lastName, String username, String password, String email, int userId)
    {
        super(firstName, lastName, username, password, email, userId);

    }


    public void createUser(HashMap<String, User> usersList, HashSet<Customer> customerList, HashSet<Provider> providerList, int userType, String firstName, String lastName, String userName, String email, String password) {
        User temp;
        boolean authorized;

        int userId;

        //Δημιουργία πελάτη
        if (userType == 1) {
            userId = (int) Math.floor(Math.random() * (2999 - 2000) + 2000);
            System.out.println("Your userId is: " + userId);
            temp = new Customer(firstName, lastName, userName, password, email, userId);
            authorized = AuthorizeUser(temp, usersList);
            if (authorized) {
                System.out.println("User created successfully");
                usersList.put(temp.getEmail(), temp);
                customerList.add((Customer) temp);
            } else {
                System.out.println("There was an error while signing up");
                System.out.println("Try again");
                temp = null;
            }
        } else if (userType == 2) {               //Δημιουργία Παρόχου
            userId = (int) Math.floor(Math.random() * (1999 - 1000) + 1000);
            System.out.println("Your userId is: " + userId);
            temp = new Provider(firstName, lastName, userName, password, email, userId, 0, 0);
            authorized = AuthorizeUser(temp, usersList);
            if (authorized) {
                System.out.println("User created successfully");
                usersList.put(temp.getEmail(), temp);
                providerList.add((Provider) temp);

            } else {
                System.out.println("There was an error while signing up");
                System.out.println("Try again");
                temp = null;
            }
        } else if (userType == 3) {
            userId = (int) Math.floor(Math.random() * (3999 - 3000) + 3000);
            System.out.println("Your userId is: " + userId);
            temp = new Admin(firstName, lastName, userName, password, email, userId);
            authorized = AuthorizeUser(temp, usersList);
            if (authorized) {
                System.out.println("User created successfully");
                usersList.put(temp.getEmail(), temp);
            } else {
                System.out.println("There was an error while signing up");
                System.out.println("Try again");
                temp = null;
            }
        }
    }

    //Εκτύπωση όλων των κρατήσεων για ένα συγκεκριμένο σπίτι
    public void displayAllReservations(HashSet<Reservation> reservations)
    {
        if(!reservations.isEmpty()){
            System.out.println("---All active reservations---");
            for (Reservation r : reservations) {
                System.out.println("Customer: " + r.getCustomer().getFirstName() + " " + r.getCustomer().getLastName() + "\nHouse ID: " + r.getHouse().getHouseId() + "\nCheck in: " + r.getCheckInDate() + "\nCheck out: " + r.getCheckOutDate() + "\nReservation ID: " + r.getReservationId());
            }
        } else {
            System.out.println("---No active reservations---");
        }
    }

    //Αναζήτηση για κράτηση με τις συγκεκριμένες ημερομηνίες σε ένα σπίτι

    public Reservation searchReservation(House house, Date checkInDate, Date checkOutDate)
    {
        Collection<Reservation> temp = reservations.get(house.getHouseId());

        for(Reservation r : temp)
        {
            if(r.getCheckInDate() == checkInDate && r.getCheckOutDate() == checkOutDate)
            {
                System.out.println("Βρέθηκε κράτηση με τις συγκεκριμένες ημερομηνίες για αυτό το σπίτι\n");
                return r;
            }
        }

        System.out.println("Δεν υπάρχει κράτηση με τις συγκεκριμένες ημερομηνίες για αυτό το σπίτι\n");
        return null;
    }

    //Εκτύπωση όλων των πελατών

    public void displayAllUsers(HashSet<Customer> customerList, HashSet<Provider> providerList)
    {
        System.out.println("\n---PROVIDERS---\n");
        for (Provider p : providerList){
            //p.showUser();
        }

        System.out.println("\n---CUSTOMERS---\n");
        for (Customer c : customerList){
            //c.showUser();
        }
    }

    //Αναζήτηση πελάτη με συγκεκριμένο username

    public Customer searchCustomer(String username, HashSet<Customer> customerList)
    {
        for(Customer c : customerList)
        {
            if(Objects.equals(c.getUsername(), username))
            {
                System.out.println("Βρέθηκε πελάτης με το username" + username);
                return c;
            }
        }

        System.out.println("Δε βέθηκε πελάτης με το username" + username);
        return null;
    }

    public boolean AuthorizeUser(User user, HashMap<String, User> usersList)
    {
        for(User u : usersList.values())
        {
            if(Objects.equals(user.getUsername(), u.getUsername()))
            {
                System.out.println("Υπάρχει χρήστης με το ίδιο username, επιλέξτε άλλο \n\n" + u.getUsername() + " " + user.getUsername());
                return false;
            }

            if(Objects.equals(user.getEmail(), u.getEmail()))
            {
                System.out.println("Υπάρχει χρήστης με το ίδιο email, επιλέξτε άλλο \n\n" + u.getEmail() + " " + user.getEmail());
                return false;
            }
        }
        if(user.getPassword().length() < 8)
        {
            System.out.println("Ο κωδικός χρήστη πρέπει να περιέχει τουλάχιστον 8 χαρακτήρες \n");
            return false;
        }

        return true;
    }




}
