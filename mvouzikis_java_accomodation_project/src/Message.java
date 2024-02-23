import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;


public class Message implements Serializable {


    private int senderId;
    private int receiverId;
    private String message;
    private LocalDateTime dateOfMessage;
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public Message(){
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    public void setSenderId(int senderId){
        this.senderId = senderId;
    }

    public int getSenderId(){
        return this.senderId;
    }

    public void setReceiverId(int receiverId){
        this.receiverId = receiverId;
    }

    public int getReceiverId(){
        return this.receiverId;
    }

    public void setDateOfMessage(LocalDateTime date){
        this.dateOfMessage = date;
    }

    public LocalDateTime getDateOfMessage() {
        return dateOfMessage;
    }

    public String toString(){
        return "Date :" + dtf.format(getDateOfMessage()) + " -> " + message;
    }
}
