import javax.swing.*;
import java.io.Serializable;
import java.util.HashSet;

public class House implements Serializable
{
    private int beds;
    private int rooms;
    private int price;
    private int rating;
    private String city;
    private String address;
    private int houseId;
    public HashSet<ImageIcon> housePhotos;

    public House() {}

    public House(int beds, int rooms, int rating, String city, String address, int price)
    {
        this.beds = beds;
        this.rooms = rooms;
        this.price = price;
        this.rating = rating;
        this.city = city;
        this.address = address;
        this.houseId = (int) Math.floor(Math.random() * (4999 - 4000) + 4000);
        this.housePhotos = new HashSet<javax.swing.ImageIcon>();
    }

    //setters-getter

    public int getBeds() {return beds;}

    public int getRooms() {return rooms;}

    public int getPrice() {return price;}

    public int getRating() {return rating;}

    public String getCity() {return city;}

    public String getAddress() {return address;}

    public int getHouseId() {return houseId;}

    public void setBeds(int beds) {this.beds= beds;}

    public void setRooms(int rooms) {this.rooms = rooms;}

    public void setPrice(int price) {this.price = price;}

    public void setRating(int rating) {this.rating = rating;}

    public void setCity(String city) {this.city = city;}

    public void setHouseId(int houseId) {this.houseId = houseId;}

    public void setAddress(String address) {this.address = address;}

    public void copy(House x)
    {
        this.setBeds(x.getBeds());
        this.setRooms(x.getRooms());
        this.setPrice(x.getPrice());
        this.setRating(x.getRating());
    }

}
