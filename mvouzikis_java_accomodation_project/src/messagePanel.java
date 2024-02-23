import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class messagePanel extends JPanel implements Serializable {

    public messagePanel(HashMap<Integer, ArrayList<Message>> allMessages, int id){
        setLocation(170, 0);
        setSize(500, 705);
        setBackground(Color.blue.darker().darker().darker());
        setLayout(null);
        setVisible(true);

        JLabel messageHeader = new JLabel("Messages From Admin");
        messageHeader.setForeground(Color.CYAN);
        messageHeader.setFont(new Font("Calibri", Font.PLAIN, 40));
        messageHeader.setSize(380, 50);
        messageHeader.setLocation(70, 50);
        add(messageHeader);

        DefaultListModel<Message> messages = new DefaultListModel<>();

        JList<Message> messageList = new JList(messages);
        messageList.setSize(100, 400);
        messageList.setLocation(100, 200);
        messageList.setBackground(Color.blue.darker().darker().darker().darker());
        messageList.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        messageList.setFont(new Font("Calibri", Font.PLAIN, 18));
        messageList.setForeground(Color.cyan);
        add(messageList);

        for(int i : allMessages.keySet()){
            if(i == id){
                for(Message m : allMessages.get(id)){
                    messages.addElement(m);
                }
            }
        }

        JScrollPane sp = new JScrollPane(messageList);
        sp.setSize(100, 400);
        sp.setLocation(100, 200);
        sp.setBackground(Color.blue.darker().darker().darker().darker());
        sp.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        sp.setFont(new Font("Calibri", Font.PLAIN, 18));
        sp.setForeground(Color.cyan);
        add(sp);

        JPanel displayMessage = new JPanel(null);
        displayMessage.setSize(250,400);
        displayMessage.setLocation(250, 200);
        displayMessage.setBackground(Color.blue.darker().darker().darker().darker());
        displayMessage.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        add(displayMessage);

        JLabel message = new JLabel();
        message.setSize(240, 380);
        message.setLocation(20, 20);
        message.setForeground(Color.CYAN);
        message.setFont(new Font("Calibri", Font.PLAIN, 20));
        message.setVisible(true);
        message.setHorizontalAlignment(SwingConstants.LEFT);
        displayMessage.add(message);

        messageList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Message selectedMessage = messageList.getSelectedValue();
                message.setText(selectedMessage.getMessage());
            }
        });
    }

}
