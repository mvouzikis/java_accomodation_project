import java.io.Serializable;

public class User implements Serializable {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private int userId;

    //Constructors
    public User(){

    }

    public User(String firstName, String lastName, String username, String password, String email, int userId)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userId = userId;
    }

    public String getFirstName() {return firstName;}

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {return password;}

    public int getUserId() {
        return userId;
    }

    public String toString(){
        String type = "";

        if(userId >= 1000 && userId <= 1999){
            type = "Provider";
        } else if (userId >= 2000 && userId <= 2999){
            type = "Customer";
        }

        String returned = "Name: " + firstName + " " + lastName +"|" + "Type: " + type + "|" + "ID: " + userId;

        return returned;
    }
}
