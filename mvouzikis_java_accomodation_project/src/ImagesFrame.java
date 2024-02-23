import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

public class ImagesFrame extends JFrame {

    public ImagesFrame(HashMap<Integer, HashSet<ImageIcon>> allImages, House selectedHouse){
        setSize(600,400);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.blue.darker().darker().darker().darker());
        setVisible(true);

        DefaultListModel<ImageIcon> icons = new DefaultListModel<>();
        JList<ImageIcon> iconsList = new JList<>(icons);
        iconsList.setSize(500,400);
        iconsList.setLocation(0,0);
        add(iconsList);

        JScrollPane sp = new JScrollPane(iconsList);
        sp.setSize(500,400);
        sp.setLocation(0,0);
        add(sp);

        for(ImageIcon ii : selectedHouse.housePhotos){
            Image img = ii.getImage();
            BufferedImage bi = new BufferedImage(500,400, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            g.drawImage(img, 0,0,500,400, null);
            ImageIcon newIcon = new ImageIcon(bi);

            icons.addElement(newIcon);
        }

    }

}
