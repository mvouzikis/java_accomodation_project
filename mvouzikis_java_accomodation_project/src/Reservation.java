import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation implements Serializable
{
    private final Customer customer;
    private final House house;
    private final Date checkInDate;
    private final Date checkOutDate;
    private final int people;
    private final int reservationId;

    public Reservation(Customer customer, House house,
                       Date checkInDate, Date checkOutDate, int people){
        this.customer = customer;
        this.house = house;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.people = people;
        this.reservationId = (int) Math.floor(Math.random() * (5999 - 5000) + 5000);
    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

    public Customer getCustomer() {
        return this.customer;
    }

    public House getHouse(){
        return this.house;
    }

    public Date getCheckInDate(){
        return this.checkInDate;
    }

    public Date getCheckOutDate(){
        return this.checkOutDate;
    }

    public int getReservationId() { return this.reservationId;}

    public int getPeople() { return this.people; }

    public void displayReservation(){
        System.out.println("Όνομα: " + customer.getFirstName() + " " + customer.getLastName() + "\nHouse ID: " + getHouse() + "\nCheck In: " + sdf.format(this.getCheckInDate()) + "\nCheck out: " + sdf.format(this.getCheckOutDate()) + "\nID Κράτησης: " + getReservationId());
    }

}
